package Encryption;

public class Encrypter {
	private int key;
	private String msg = "";
	private String encrypted = "";
	private boolean flag = false;

	public Encrypter(int key, String msg) {
		this.key = key;
		this.msg = msg;
	}

	private int checkSum(String s, int l) {
		int sum = 0;
		for (int i = 0; i < l; i++) {
			sum += (int) s.charAt(i);
		}
		return sum;
	}

	public String asciiToHex(String from) {
		String hex = "";
		for (int i = 0; i < from.length(); i++) {
			String holder = String.format("%02x", (int) from.charAt(i));
			hex += holder;
		}
		return hex;
	}

	public String getEncrypted(){
		if (!flag) {
			for (int i = 0; i < msg.length(); i++) {
				PseudoRandom pr = new PseudoRandom(key, 90);
				char holder = (char) (pr.nextInt() + msg.charAt(i));
				encrypted += holder;
				key += checkSum(msg, i + 1);
			}
			flag = true;
			encrypted = asciiToHex(encrypted);
		}
		return encrypted;
	}
}