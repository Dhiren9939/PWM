package LocalStorage;

public class EncryptedEntry {
    String encryptedentry;
    public EncryptedEntry(String encString){
        this.encryptedentry = encString;
    }

    public String toString(){
        return encryptedentry;
    }
}
