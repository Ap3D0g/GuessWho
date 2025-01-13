/*
 * Name: April, Lucas, Jerry, Ponnavaddn
 * Due Date: Jan 15, 2025 
 * Teacher: Mr. Chu
 * Course: ISC4U 
 * Assignemnt: Guess who ISP - PlayerGuesses class
 */
   
// CODED BY: JERRY 

public class PlayerGuesses {
    private String playerName; // Player name 
    private int guesses; // Number of player guesses / questions asked 

    // Constructor 
    public PlayerGuesses(String playerName, int guesses) {
        this.playerName = playerName; // Initialize the player's name
        this.guesses = guesses; // Initialize the player's guess count 
    }

    // Getter methods 
    public String getPlayerName() {
        return playerName;
    }

    public int getGuesses() {
        return guesses;
    }

    // Setter method 
    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }
}


