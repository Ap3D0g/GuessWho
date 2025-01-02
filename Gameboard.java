import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Gameboard {
    
    private boolean isPlayerTurn = true; // Player starts first
    private boolean gameOver = false;   // Track if the game has ended

    // Constructor
    public Gameboard() {
        this.isPlayerTurn = true; // Player starts first
        this.gameOver = false;   // Game is not over initially
    }

    // Switch Turns
    public void switchTurn() {
        isPlayerTurn = !isPlayerTurn; // Toggle between player and AI
    }

    // Check if it's Player's Turn
    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    // End the Game
    public void endGame(String winner) {
        gameOver = true; // Mark the game as over
        JOptionPane.showMessageDialog(null,
                winner + " wins the game!",
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);

        // Option to restart or exit the game
        int restart = JOptionPane.showConfirmDialog(null,
                "Do you want to restart?",
                "Restart?",
                JOptionPane.YES_NO_OPTION);

        if (restart == JOptionPane.YES_OPTION) {
            // Restart the game
            System.exit(0); // Placeholder for restarting logic
        } else {
            System.exit(0); // Exit the program
        }
    }

    // Check Win Condition
    public boolean checkWinCondition(ArrayList<Character> characters) {
        return characters.size() == 1; // True if only 1 character remains
    }

    // Method to get the chosen character's attributes (so that you can compare attributes with questions asked)
    public static Character chosenCharacter(ArrayList<Character> characters, String selectedName) {
        // Loop through all characters using a counted for loop
        for (int i = 0; i < characters.size(); i++) { 
            Character c = characters.get(i); // Get character at index i
            
            // Check if the character's name matches the selected name
            if (c.getName().equalsIgnoreCase(selectedName)) { 
                return c; // Return the matched character
            }
        }
        return null; // Return null if no match is found
    } 

    //remove characters depending on the question asked and the characteristics of character chosen 
    public static ArrayList<Character> removeCharacter(ArrayList<Character> characters, String attribute, String value, boolean matches) {
        // Create a temporary list to store characters to remove
        ArrayList<Character> toRemove = new ArrayList<>();
    
        // Loop through all characters using a regular for loop
        for (int i = 0; i < characters.size(); i++) {
            Character c = characters.get(i); // Get the current character
            boolean match = false;
    
            // Check the attribute for each character
            if (attribute.equalsIgnoreCase("gender")) {
                match = c.getGender().equalsIgnoreCase(value);
            } else if (attribute.equalsIgnoreCase("hair")) {
                match = c.getHairColour().equalsIgnoreCase(value);
            } else if (attribute.equalsIgnoreCase("eye")) {
                match = c.getEyeColour().equalsIgnoreCase(value);
            } else if (attribute.equalsIgnoreCase("glasses")) {
                match = c.hasGlasses();
            } else if (attribute.equalsIgnoreCase("hat")) {
                match = c.hasHat();
            } else if (attribute.equalsIgnoreCase("jewelry")) {
                match = c.hasJewelry();
            } else if (attribute.equalsIgnoreCase("beard")) {
                match = c.hasBeard();
            } else if (attribute.equalsIgnoreCase("mustache")) {
                match = c.hasMustache();
            }
    
            // Remove characters that don't meet the match condition
            if ((matches && !match) || (!matches && match)) {

                toRemove.add(c); // Add to removal list
                // System.out.println("Removed " + c.getName());
            }
        }
    
        // Remove all characters marked for removal
        characters.removeAll(toRemove);
    
        // Return the filtered list
        return characters;
    }

}
