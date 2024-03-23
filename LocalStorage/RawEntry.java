package LocalStorage;

public class RawEntry {
    String key;
    String password;

    public RawEntry(String key, String password) {
        this.key = key;
        this.password = password;
    }

    public String toString() {
        return key + password;
    }
}
