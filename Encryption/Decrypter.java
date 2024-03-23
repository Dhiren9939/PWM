package Encryption;

public class Decrypter {
	private int key;
	private String msg = "";
	private String encrypted = "";
	private boolean flag = false;

	public Decrypter(int key, String encrypted) {
		this.encrypted = encrypted;
		this.key = key;
	}

	private int checkSum(String s, int l) {
		int sum = 0;
		for (int i = 0; i < l; i++) {
			sum += (int) s.charAt(i);
		}
		return sum;
	}

	public String getMessage() {
		return this.msg;
	}

	private String hexToAscii(String from) {
		String to = "";
		for (int i = 0; i < from.length(); i += 2) {
			char n1 = from.charAt(i + 1);
			char n2 = from.charAt(i);
			char holder = 0;
			if ('0' <= n1 && n1 <= '9') {
				holder = (char) (n1 - '0');
			} else if ('a' <= n1 && n1 <= 'f') {
				holder = (char) (n1 - 'a' + 10);
			}

			if ('0' <= n2 && n2 <= '9') {
				holder += 16 * (n2 - '0');
			} else if ('a' <= n2 && n2 <= 'f') {
				holder += 16 * (n2 - 'a' + 10);
			}
			to += holder;
		}
		return to;
	}

	public String getDecrypted() throws InvalidStringException {
		if (encrypted.length() % 2 != 0) {
			InvalidStringException.createStackTrace();
			throw new InvalidStringException();
		}
		if (!flag) {
			String encryptedA = hexToAscii(encrypted);
			for (int i = 0; i < encryptedA.length(); i++) {
				PseudoRandom pr = new PseudoRandom(key, 90);
				char holder = (char) (-pr.nextInt() + encryptedA.charAt(i));
				msg += holder;
				key += checkSum(msg, i + 1);
			}
			flag = false;
		}
		return msg;
	}
}
