package LocalStorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class ReadManager {
    private File file;

    public ReadManager(File file) {
        this.file = file;
    }

    public Vector<String> getDataVector() {
        System.gc();
        Vector<String> vec = new Vector<String>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                vec.add(scanner.nextLine());
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return vec;
    }
}
