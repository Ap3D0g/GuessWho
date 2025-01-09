import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

public class Leaderboard {

    public static void displayLeaderboard() {
        // Create the frame
        JFrame frame = new JFrame("Leaderboard");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Title panel with background color and title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(45, 85, 135)); // Blue background
        JLabel titleLabel = new JLabel("Leaderboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // Add title panel to frame
        frame.add(titlePanel, BorderLayout.NORTH);

        // Panel for leaderboard entries
        JPanel leaderboardPanel = new JPanel();
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
        leaderboardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        leaderboardPanel.setBackground(new Color(235, 240, 250)); // Light background

        // Get sorted guessess
        List<PlayerGuesses> sortedGuessess = getAllGuessessSorted();

        // Add each player's guesses with styled labels
        for (int i = 0; i < sortedGuessess.size(); i++) {
            PlayerGuesses ps = sortedGuessess.get(i);

            // Create a panel for each player entry
            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new BorderLayout());
            playerPanel.setBackground(new Color(255, 255, 255)); // White background for entries
            playerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1), // Light border
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Padding
            ));

            // Player's rank
            JLabel rankLabel = new JLabel((i + 1) + ". ", JLabel.LEFT);
            rankLabel.setFont(new Font("Arial", Font.BOLD, 18));
            rankLabel.setForeground(new Color(45, 85, 135)); // Blue color

            // Player's name
            JLabel nameLabel = new JLabel(ps.getPlayerName(), JLabel.LEFT);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            nameLabel.setForeground(Color.DARK_GRAY);

            // Player's guesses
            JLabel guessesLabel = new JLabel(String.valueOf(ps.getGuesses()), JLabel.RIGHT);
            guessesLabel.setFont(new Font("Arial", Font.BOLD, 16));
            guessesLabel.setForeground(new Color(34, 139, 34)); // Green color for guesses

            // Add labels to player panel
            playerPanel.add(rankLabel, BorderLayout.WEST);
            playerPanel.add(nameLabel, BorderLayout.CENTER);
            playerPanel.add(guessesLabel, BorderLayout.EAST);

            // Add player panel to leaderboard panel
            leaderboardPanel.add(playerPanel);
            leaderboardPanel.add(Box.createVerticalStrut(10)); // Add spacing between entries
        }

        // Add leaderboard panel to a scroll pane in case the list is long
        JScrollPane scrollPane = new JScrollPane(leaderboardPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        frame.add(scrollPane, BorderLayout.CENTER);

        // Footer panel with a close button
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(45, 85, 135)); // Blue background
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 16));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(new Color(200, 50, 50)); // Red background for button
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> frame.dispose());
        footerPanel.add(closeButton);

        // Add footer panel to frame
        frame.add(footerPanel, BorderLayout.SOUTH);

        // Show the frame
        frame.setVisible(true);

    }
    
    private static final String LEADERBOARD_FILE = "leaderboard.txt";
    
    // Maintain guessess in an ArrayList (no Map)
    private static ArrayList<PlayerGuesses> guessess = new ArrayList<>();

    /**
     * Loads guessess from a file using Scanner. If the file doesn't exist, do nothing.
     */
    public static void loadGuessess() {
        guessess.clear();
        File file = new File(LEADERBOARD_FILE);
        if (!file.exists()) {
            return;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    // Expecting "PlayerName Guesses"
                    String[] parts = line.split("\\s+");
                    if (parts.length == 2) {
                        String playerName = parts[0];
                        int guesses = Integer.parseInt(parts[1]);
                        guessess.add(new PlayerGuesses(playerName, guesses));
                    }
                }
            }
        } catch (Exception e) {  // Catch-all
            e.printStackTrace();
        }
    }

    /**
     * Saves guessess to a file using PrintWriter. Overwrites old data in the file.
     */
    public static void saveGuessess() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LEADERBOARD_FILE))) {
            for (PlayerGuesses playerGuesses : guessess) {
                writer.println(playerGuesses.getPlayerName() + " " + playerGuesses.getGuesses());
            }
        } catch (Exception e) {  // Catch-all
            e.printStackTrace();
        }
    }

    /**
     * Increment/decrement an existing player's guesses or create new if not found.
     */
    public static void updateGuesses(String playerName, int guesses) {
        for (PlayerGuesses playerGuesses : guessess) {
            if (playerGuesses.getPlayerName().equalsIgnoreCase(playerName)) {
                playerGuesses.setGuesses(playerGuesses.getGuesses() + guesses);
                saveGuessess();
                return;
            }
        }
        // Player not found, create a new entry
        guessess.add(new PlayerGuesses(playerName, guesses));
        saveGuessess();
    }

    /**
     * Returns a specific player's guesses, or 0 if not found.
     */
    public static int getGuesses(String playerName) {
        for (PlayerGuesses playerGuesses : guessess) {
            if (playerGuesses.getPlayerName().equalsIgnoreCase(playerName)) {
                return playerGuesses.getGuesses();
            }
        }
        return 0;
    }

    /**
     * Returns a sorted copy of the leaderboard in descending order by guesses.
     * Uses a separate Comparator (GuessesComparator), not an inline compare method.
     */
    public static List<PlayerGuesses> getAllGuessessSorted() {
        List<PlayerGuesses> sortedList = new ArrayList<>(guessess); // Create a copy of the guessess

        // Bubble Sort Algorithm to sort in ascending order
        for (int i = 0; i < sortedList.size() - 1; i++) {
            for (int j = 0; j < sortedList.size() - i - 1; j++) {
                if (sortedList.get(j).getGuesses() > sortedList.get(j + 1).getGuesses()) {
                    // Swap elements
                    PlayerGuesses temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }

        return sortedList;
    }
}


