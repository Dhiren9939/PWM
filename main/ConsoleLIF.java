package main;

import java.io.File;
import java.nio.charset.CoderMalfunctionError;
import java.util.Vector;

import Encryption.Decrypter;
import Encryption.InvalidStringException;
import LocalStorage.EncryptedEntry;
import LocalStorage.FileStatus;
import LocalStorage.RawEntry;
import LocalStorage.ReadManager;
import LocalStorage.WriteManager;

public class ConsoleLIF {
    private ReadManager rManager;
    private WriteManager wManager;
    private File file;
    static String file_extension = ".ppwm";

    ConsoleLIF(File file) {
        this.file = file;
        this.rManager = new ReadManager(file);
        this.wManager = new WriteManager(file);
    }

    public static boolean createFile(String filename) {

        if (!filename.endsWith(file_extension)) {
            filename = filename + file_extension;
            PWM.filename = filename;
        }
        File file = new File(filename);
        try {
            file.createNewFile();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static FileStatus evaluateFile(String filename) {
        File file = new File(filename);
        if (!file.exists())
            return FileStatus.DoesNotExist;
        else if (!filename.endsWith(file_extension)) {
            return FileStatus.NotaPWMFile;
        }
        return FileStatus.Fine;
    }

    public Vector<EncryptedEntry> populateEncryptedEntries(int key) throws CorruptedFileExpception {
        Vector<EncryptedEntry> entries = new Vector<EncryptedEntry>();
        Vector<String> encrypted_data = rManager.getDataVector();
        for (int i = 0; i < encrypted_data.size(); i++) {
            String tempString = encrypted_data.get(i);
            String split_entry[] = tempString.split(" ");
            if (split_entry.length != 2) {
                throw new CorruptedFileExpception(false);
            }
            entries.add(new EncryptedEntry(tempString));
        }
        return entries;
    }

    public Vector<RawEntry> populateRawEntries(int key)
            throws CorruptedFileExpception {
        Vector<RawEntry> raw_entries = new Vector<RawEntry>();
        Vector<String> encrypted_data = rManager.getDataVector();
        for (int i = 0; i < encrypted_data.size(); i++) {
            String tempString = encrypted_data.get(i);
            String split_entry[] = tempString.split(" ");
            int space_index = tempString.indexOf(" ");
            int n = space_index / 2;
            Decrypter decrypter = new Decrypter(key, split_entry[0] + split_entry[1]);
            try {
                decrypter.getDecrypted();
            } catch (InvalidStringException e) {
                throw new CorruptedFileExpception(true);
            }
            String keyString = decrypter.getMessage().substring(0, n);
            String passString = decrypter.getMessage().substring(n);
            raw_entries.add(new RawEntry(keyString, passString));
        }
        return raw_entries;
    }

    public void populateFile(Vector<EncryptedEntry> encrypted_entries) {
        wManager.writeToFile(encrypted_entries);
    }
}
