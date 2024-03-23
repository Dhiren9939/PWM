package Encryption;

import LocalStorage.EncryptedEntry;

public class Decrypter {
	private int key;
	private String msg = "";
	private String encrypted = "";
	private boolean flag = false;

	public Decrypter(){}

	public Decrypter(int key, String encrypted) {
		this.encrypted = encrypted;
		this.key = key;
	}

	public Decrypter(int key,EncryptedEntry eEntry){
		this.key = key;
		this.encrypted = eEntry.toString();
	}

	public void setKey(int key){
		this.key = key;
	}

	public void setEncrypted(String encrypted){
		this.encrypted = encrypted;
	}

	private int checkSum(String s, int l) {
		int sum = 0;
		for (int i = 0; i < l; i++) {
			sum += (int) s.charAt(i);
		}
		return sum;
	}

	public String getMessage(){
		return this.msg;
	}

	private String hexToAscii(String from) {
		String to = "";
		for (int i = 0; i < from.length(); i += 2) {
			char n1 = from.charAt(i + 1);
			char n2 = from.charAt(i);
			char holder = 0;
			switch (n1) {
				case '1':
					holder = 1;
					break;
				case '2':
					holder = 2;
					break;
				case '3':
					holder = 3;
					break;
				case '4':
					holder = 4;
					break;
				case '5':
					holder = 5;
					break;
				case '6':
					holder = 6;
					break;
				case '7':
					holder = 7;
					break;
				case '8':
					holder = 8;
					break;
				case '9':
					holder = 9;
					break;
				case 'a':
					holder = 10;
					break;
				case 'b':
					holder = 11;
					break;
				case 'c':
					holder = 12;
					break;
				case 'd':
					holder = 13;
					break;
				case 'e':
					holder = 14;
					break;
				case 'f':
					holder = 15;
					break;
			}
			switch (n2) {
				case '1':
					holder += 1 * 16;
					break;
				case '2':
					holder += 2 * 16;
					break;
				case '3':
					holder += 3 * 16;
					break;
				case '4':
					holder += 4 * 16;
					break;
				case '5':
					holder += 5 * 16;
					break;
				case '6':
					holder += 6 * 16;
					break;
				case '7':
					holder += 7 * 16;
					break;
				case '8':
					holder += 8 * 16;
					break;
				case '9':
					holder += 9 * 16;
					break;
				case 'a':
					holder += 10 * 16;
					break;
				case 'b':
					holder += 11 * 16;
					break;
				case 'c':
					holder += 12 * 16;
					break;
				case 'd':
					holder += 13 * 16;
					break;
				case 'e':
					holder += 14 * 16;
					break;
				case 'f':
					holder += 15 * 16;
					break;
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
