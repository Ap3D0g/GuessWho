import javax.swing.*;                        // GUI components
import javax.sound.sampled.*;                 // Audio components
import java.io.File;                          // File handling
import java.io.IOException;                   // Exception handling

public class testSound {

    private Clip clip; // Declare Clip as a class-level variable for reuse

    // Constructor to set up the GUI
    public testSound() {
        // Create JFrame
        JFrame frame = new JFrame("Background Music Example");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Create Buttons
        JButton stopButton = new JButton("Stop Music");
        stopButton.setBounds(130, 50, 150, 50); // Position and size
        frame.add(stopButton);

        // Add ActionListener to stop music
        stopButton.addActionListener(e -> stopMusic());

        // Show frame
        frame.setVisible(true);
        frame.setResizable(false); // Disable resizing

        // **Start music automatically when the game launches**
        playMusic("applause_y.wav"); // Replace with the actual file path
    }

    // Method to play background music
    private void playMusic(String filePath) {
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

    // Method to stop music
    private void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop(); // Stop the music
            clip.close(); // Release resources
        }
    }

    // Main method
    public static void main(String[] args) {
        new testSound(); // Start the GUI and play music
    }
}