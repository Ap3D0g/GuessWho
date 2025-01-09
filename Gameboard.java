/*
 * Name: April, Lucas, Jerry, Ponnavaddn
 * Due Date: Jan 15, 2025 
 * Teacher: Mr. Chu
 * Course: ISC4U 
 * Assignemnt: Guess who ISP - Gameboard class
 */

import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Gameboard {
    
    //Variables 
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

    // End the Game / play again 
    public void endGame(String winner, Character ai, String selectedCharacter) {
        gameOver = true; // Mark the game as over
        String winMessage = "";
        //Show winning message 

        if (winner.equalsIgnoreCase("AI")) { // AI wins 
            winMessage = "AI wins! They gessed your character " + selectedCharacter;
        } else if (winner.equalsIgnoreCase("AI - wrong Guess")) {
            winMessage = "You guessed wrong! You lose!"; // AI wins because player guessed wrong character 
        } else if (winner.equalsIgnoreCase("Player")) { // Player wins 
            winMessage = "You win! You guessed the AI's character " + ai.getName();
        } 

        JOptionPane.showMessageDialog(null,
                winMessage,
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);

        // Option to restart or exit the game
        int restart = JOptionPane.showConfirmDialog(null,
                "Do you want to restart?",
                "Restart?",
                JOptionPane.YES_NO_OPTION);

        //If user clicks yes to restart game...         
        if (restart == JOptionPane.YES_OPTION) {
            // Close the current GUI window
            Window[] windows = Window.getWindows(); // Get all open windows
            for (int i = 0; i < windows.length; i++) { 
                if (windows[i] instanceof JFrame) {
                    windows[i].dispose(); // Close each JFrame (so that the game starts fresh)
                }
            }

            // Reinitialize characters and questions
            Main.initializeCharacters();
            Main.initializeQuestions();  

            // Restart the game - reinitialize GUI with the same characters and questions
            SwingUtilities.invokeLater(() -> {
                new GUI(Main.characters, Main.questions, Main.aiQuestions, Main.guessQuestions); // Reinitialize GUI using Main's lists
            });
        // If user clicks NO to playing again...
        } else {
            System.exit(0); // Exit the program
        }
    }

    // Check Win Condition
    public boolean checkWinCondition(ArrayList<Character> characters) {
        return characters.size() == 1; // True if only 1 character remains
    }

    // Method to get the chosen character's attributes (so that you can compare attributes with questions asked)
        //the chosen character is orignally stored in a String and this method returns its matching Character object 
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

    // Method to remove characters depending on the question asked and the characteristics of character chosen 
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
            }
        }
    
        // Remove all characters marked for removal
        characters.removeAll(toRemove);
    
        // Return the filtered list
        return characters;
    }

}




