/**
 * File: GameLoop.java
 * 
 * Copyright John Littlewood, 2015, all rights reserved. 
 */
package com.tgds.api2d.game;

/**
 * Manages the game loop. In order to implement a game loop, extend this class,
 * and implement {@link #gameLoop(long)}. Each iteration of the game loop, that
 * method will be called.
 * 
 * @author John Littlewood
 */
public abstract class GameLoop extends Thread {

	/** the game being looped */
	private final Game game;

	/** the maximum number of times the game loop should run each second */
	private int maximumFps = DEFAULT_MAX_FPS;

	/** the default maximum FPS */
	private static final int DEFAULT_MAX_FPS = 60;

	/** the current length of a frame, in milliseconds */
	private long frameLength;

	/** constructor */
	public GameLoop(Game game) {
		super("Game Loop");
		this.game = game;
		frameLength = calculateFrameLength(maximumFps);
	}

	/**
	 * set the game loop running
	 */
	@Override
	public void run() {
		while (true) {
			long prev = System.currentTimeMillis();
			long time = System.currentTimeMillis();
			long next = time + frameLength;
			if (game.isRunning()) {
				gameLoop(time - prev);
			}
			try {
				long wait = next - System.currentTimeMillis();
				if (wait > 0) {
					Thread.sleep(wait);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			prev = time;
		}
	}

	/**
	 * the method called each iteration of the game loop
	 * 
	 * @param stepTime the time, in milliseconds, since the last game loop.
	 */
	public abstract void gameLoop(long stepTime);

	/**
	 * @return the maximum number of frames per second of the game loop
	 */
	public int getMaximumFps() {
		return maximumFps;
	}

	/**
	 * @param maximumFps the maximum number of frames per second to set
	 */
	public void setMaximumFps(int maximumFps) {
		this.maximumFps = maximumFps;
		calculateFrameLength(maximumFps);
	}

	/**
	 * @param maximumFps2
	 * @return
	 */
	private long calculateFrameLength(int maximumFps) {
		return (long) (1000.0 / maximumFps);
	}
}
