package io.github.enzankiars.botbattle.other;

public class Bullet {
	private int x;
	private int y;
	private int dir;
	

	public Bullet(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDir() {
		return dir;
	}
}
