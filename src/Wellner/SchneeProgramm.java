package Wellner;

import java.util.ArrayList;

import processing.core.PApplet;

public class SchneeProgramm extends PApplet {

	public static void main(String[] args) {
		main("Wellner.SchneeProgramm");
	}

	ArrayList<Snowflake> snowflakes;
	public static int count = 0;

	public void settings() {
		size(640, 480);
	}

	public void setup() {
		snowflakes = new ArrayList<Snowflake>(); // Arraylist that contains
		noStroke();
		createSnow(10); // creates snow
		letItSnow(); // starts the snow-threads
		// thread("printCount");
	}

	public void draw() {
		background(0);

		int rnd = (int) random(1, 20);

		show(rnd); // draws the particles

		if (noSnow()) {
			System.out.println("count: " + count);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
		}

		for (int i = 0; i < snowflakes.size(); i++) {
			if (!snowflakes.get(i).isAlive()) {
				System.out.println("Snowflake " + snowflakes.get(i).getNumber() + " is dead!");
				snowflakes.remove(i);
			}
		}

	}

	/**
	 * Spawns snow at the top of the screen
	 * 
	 * @param howmuch
	 *            specifies the amount of snow
	 */
	public void createSnow(int amount) {
		for (int i = 0; i < amount; i++) {
			snowflakes.add(new Snowflake(this, random(15, width - 15), i + 1));
		}
	}

	/**
	 * Displays the snow, updates it and moves it sideways (horizontally)
	 * 
	 * @param rnd
	 *            the chance for a snowflake to move left or right
	 */
	public void show(int rnd) {
		for (Snowflake snow : snowflakes) {
			snow.display();
			if (rnd == 1) {
				snow.setXSpeed(random(-0.3f, 0.3f));
			}
		}
	}

	/**
	 * Checks if snowflakes are not there
	 * 
	 * @return true or false
	 */
	public boolean noSnow() {
		return (snowflakes.size() == 0);
	}

	/**
	 * Starts the snowflakes (Threads)
	 */
	public void letItSnow() {
		for (Snowflake s : snowflakes) {
			s.setDaemon(true);
			s.start();
		}
	}

	/**
	 * Counts up the global variable count
	 */
	public synchronized static void countUp() {
		count += 1;
	}

	/**
	 * Prints the global variable count as long as the program is not finished
	 */
	public void printCount() {
		while (!this.finished) {

			System.out.println(count);
		}
	}

}
