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
        backgroundMusic("backgroundMusic.wav"); //ENTER FILE PAATH AS STRING
        buttonClick("buttonClick.wav");
    }

    // Link to how to play audio: https://www.geeksforgeeks.org/play-audio-file-using-java/
    // Method to play background music
    public void backgroundMusic(String filePath) {
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

        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions
        }
    }

    public void buttonClick(String filePath) {
        try {
            // Load the sound file
            File musicFile = new File(filePath); // Path to the sound file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);

            // Create and open the audio clip
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions
        }
    } 
}


