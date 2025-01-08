import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Leaderboard {

    public static void displayLeaderboard() {
        // Create the frame
        JFrame frame = new JFrame("Leaderboard");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        // Table data
        String[] columnNames = {"Player Name", "Score"};
        List<PlayerScore> sortedScores = getAllScoresSorted();
        String[][] data = new String[sortedScores.size()][2];

        for (int i = 0; i < sortedScores.size(); i++) {
            PlayerScore playerScore = sortedScores.get(i);
            data[i][0] = playerScore.getPlayerName();
            data[i][1] = String.valueOf(playerScore.getScore());
        }

        // Create table and add it to a scroll pane
        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the table to the frame
        frame.add(scrollPane);
        frame.setVisible(true);
    }
    
    private static final String LEADERBOARD_FILE = "leaderboard.txt";
    
    // Maintain scores in an ArrayList (no Map)
    private static ArrayList<PlayerScore> scores = new ArrayList<>();

    /**
     * Loads scores from a file using Scanner. If the file doesn't exist, do nothing.
     */
    public static void loadScores() {
        scores.clear();
        File file = new File(LEADERBOARD_FILE);
        if (!file.exists()) {
            return;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    // Expecting "PlayerName Score"
                    String[] parts = line.split("\\s+");
                    if (parts.length == 2) {
                        String playerName = parts[0];
                        int score = Integer.parseInt(parts[1]);
                        scores.add(new PlayerScore(playerName, score));
                    }
                }
            }
        } catch (Exception e) {  // Catch-all
            e.printStackTrace();
        }
    }

    /**
     * Saves scores to a file using PrintWriter. Overwrites old data in the file.
     */
    public static void saveScores() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LEADERBOARD_FILE))) {
            for (PlayerScore playerScore : scores) {
                writer.println(playerScore.getPlayerName() + " " + playerScore.getScore());
            }
        } catch (Exception e) {  // Catch-all
            e.printStackTrace();
        }
    }

    /**
     * Increment/decrement an existing player's score or create new if not found.
     */
    public static void updateScore(String playerName, int increment) {
        for (PlayerScore playerScore : scores) {
            if (playerScore.getPlayerName().equalsIgnoreCase(playerName)) {
                playerScore.setScore(playerScore.getScore() + increment);
                saveScores();
                return;
            }
        }
        // Player not found, create a new entry
        scores.add(new PlayerScore(playerName, increment));
        saveScores();
    }

    /**
     * Returns a specific player's score, or 0 if not found.
     */
    public static int getScore(String playerName) {
        for (PlayerScore playerScore : scores) {
            if (playerScore.getPlayerName().equalsIgnoreCase(playerName)) {
                return playerScore.getScore();
            }
        }
        return 0;
    }

    /**
     * Returns a sorted copy of the leaderboard in descending order by score.
     * Uses a separate Comparator (ScoreComparator), not an inline compare method.
     */
    public static List<PlayerScore> getAllScoresSorted() {
        List<PlayerScore> sortedList = new ArrayList<>(scores);

        // Sort using our separate ScoreComparator class
        sortedList.sort(new ScoreComparator());

        return sortedList;
    }
}

