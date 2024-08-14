package game;

import java.io.InputStream;

import javazoom.jl.player.Player;

public class MusicPlayer extends Thread {
    private String musicFilePath;

	// Create a separate thread for playing bullet firing sounds
	static Thread bulletSoundThread;
	
    public MusicPlayer(String musicFilePath) {
        this.musicFilePath = musicFilePath;
    }

    @Override
    public void run() {
        try {
            // Load the background music as an input stream using the class loader
            InputStream inputStream = getClass().getResourceAsStream(musicFilePath);
            if (inputStream != null) {
                Player player = new Player(inputStream);

                // Start playing the audio in a loop
                new Thread(() -> {
                    try {
                        while (true) {
                            player.play();
                            inputStream.reset(); // Reset the stream to play the audio again
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            } else {
                System.err.println("Unable to find background music file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    static void playCollisionSound() {
        Thread collisionSoundThread = new Thread(() -> {
            try {
                InputStream inputStream = MusicPlayer.class.getResourceAsStream("/hit1.mp3");
                Player player = new Player(inputStream);
                player.play();
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        collisionSoundThread.start();
    }
	
	public void playBackgroundMusic() {
	    try {
	        // Load the background music as an input stream using the classpath
	        InputStream inputStream = getClass().getResourceAsStream("/background_music.mp3");
	        if (inputStream != null) {
	            Player player = new Player(inputStream);

	            // Start playing the audio in a loop
	            new Thread(() -> {
	                try {
	                    while (true) {
	                        player.play();
	                        inputStream.reset(); // Reset the stream to play the audio again
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }).start();
	        } else {
	            System.err.println("Unable to find background music file.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	
//	static void playBulletFiringSound() {
//		// Stop the previous sound thread if it is running
//		stopBulletSoundThread();
//
//		// Create a new thread to play the sound
//		bulletSoundThread = new Thread(() -> {
//			try {
//				InputStream inputStream = new FileInputStream(
//						"C:\\Users\\Usman Babar\\Desktop\\JAVA\\tank2D_Updated_complete\\src\\shooot.mp3");
//				Player player = new Player(inputStream);
//				player.play();
//				inputStream.close(); // Close the stream after the sound is played
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//
//		// Start the new sound thread
//		bulletSoundThread.start();
//	}
	
	public void playBulletFiringSound() {
	    // Stop the previous sound thread if it is running
	    stopBulletSoundThread();

	    // Create a new thread to play the sound
	    bulletSoundThread = new Thread(() -> {
	        try {
	            // Load the sound as an input stream using the classpath
	            InputStream inputStream = getClass().getResourceAsStream("/shooot.mp3");
	            if (inputStream != null) {
	                Player player = new Player(inputStream);
	                player.play();
	                inputStream.close(); // Close the stream after the sound is played
	            } else {
	                System.err.println("Unable to find sound file.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });

	    // Start the new sound thread
	    bulletSoundThread.start();
	}


	
	 static void stopBulletSoundThread() {
			// Check if the previous sound thread is running and interrupt it to stop the
			// sound
			if (bulletSoundThread != null && bulletSoundThread.isAlive()) {
				bulletSoundThread.interrupt();
			}
		}
	
}
