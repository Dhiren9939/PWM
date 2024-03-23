package Encryption;

public class PseudoRandom {
    private int seed = 2589;
	private int max = 1000000;

	PseudoRandom(int seed) {
		this.seed = seed;
	}

	PseudoRandom(int seed, int max) {
		this.max = max + 1;
		this.seed = seed;
	}

	public int nextInt() {
		int rand = 377 * seed + (seed!=0?(461 / seed):0) + seed / 47 - seed * 7;
		seed = rand * 59 / (43);
		rand %= max;
		return rand > 0 ? rand : -rand;
	}
}
