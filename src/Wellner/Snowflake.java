package Wellner;

import processing.core.PApplet;

public class Snowflake extends Thread {
	PApplet p;
	private float x;
	private float y;
	private float r;
	private float xspeed;
	private float yspeed;
	private int tnumber;

	public Snowflake(PApplet p, float x, int tnumber) {
		this.p = p;
		this.x = x;
		this.y = this.p.random(-200f, -20f);
		this.r = this.p.random(8f, 13f);

		this.xspeed = this.p.random(-0.2f, 0.2f);
		this.yspeed = this.p.random(1, 3);

		this.tnumber = tnumber;
	}

	/**
	 * Makes white snow particles
	 */
	public void display() {
		this.p.fill(255);
		this.p.ellipse(this.x, this.y, this.r, this.r);
	}

	@Override
	public void run() {
		if (this.isAlive())
			System.out.println("Snowflake " + this.tnumber + " started!");
		while (this.y <= this.p.height + 4) {
			try {
				sleep(12);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.x += this.xspeed;
			this.y += this.yspeed;
		}
		SchneeProgramm.countUp();
	}

	/**
	 * Sets the horizontal speed
	 * 
	 * @param x
	 *            the new speed
	 */
	public void setXSpeed(float x) {
		this.xspeed = x;
	}

	/**
	 * Sets the vertical speed
	 * 
	 * @param y
	 *            the new speed
	 */
	public void setYSpeed(float y) {
		this.yspeed = y;
	}

	/**
	 * Gets the vertcial position
	 * 
	 * @return the vertical position
	 */
	public float getY() {
		return this.y;
	}

	/**
	 * Gets the number of the thread
	 * 
	 * @return the number of the thread
	 */
	public int getNumber() {
		return this.tnumber;
	}
}
