package LocalStorage;

import java.io.*;
import java.util.Vector;

public class WriteManager {
    private File file;

    public WriteManager(File file) {
        this.file = file;
    }

    public void writeToFile(Vector<EncryptedEntry> encrypted_entries) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            int i;
            for (i = 0; i < encrypted_entries.size() - 1; i++) {
                bw.write(encrypted_entries.get(i).toString());
                bw.newLine();
            }
            if(encrypted_entries.size() >= 1){
                bw.write(encrypted_entries.get(i).toString());
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

}