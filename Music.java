/*
 * Name: April, Lucas, Jerry, Ponnavaddn
 * Due Date: Jan 15, 2025 
 * Teacher: Mr. Chu
 * Course: ISC4U 
 * Assignemnt: Guess who ISP - Music class
 */

import javax.sound.sampled.*;                 // Audio components
import java.io.File;                          // File handling
import java.io.IOException;                   // Exception handling

public class Music {
    private Clip clip; // Declare Clip as a class-level variable for reuse

    public Music() {
        backgroundMusic("applause_y.wav"); //ENTER FILE PAATH AS STRING
    }

    // Method to play background music
    private void backgroundMusic(String filePath) {
        try {
            // Load the sound file
            File musicFile = new File(filePath); // Path to the sound file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);

            // Create and open the audio clip
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Loop the clip continuously
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start(); // Start playing the music

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace(); // Handle exceptions
        }
    }
}