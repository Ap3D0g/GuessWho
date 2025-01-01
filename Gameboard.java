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

    // Determine the attribute based on the question
    public static String getAttributeFromQuestion(String question, Character character) {
        if (question.contains("male") || question.contains("female")) { // Gender
            return character.getGender(); // Return gender directly
        } else if (question.contains("hair")) { // Hair color
            return character.getHairColour();
        } else if (question.contains("eye")) { // Eye color
            return character.getEyeColour();
        } else if (question.contains("glasses")) { // Glasses (boolean)
            return String.valueOf(character.hasGlasses());
        } else if (question.contains("hat")) { // Hat (boolean)
            return String.valueOf(character.hasHat());
        } else if (question.contains("jewelry")) { // Jewelry (boolean)
            return String.valueOf(character.hasJewelry());
        } else if (question.contains("beard")) { // Beard (boolean)
            return String.valueOf(character.hasBeard());
        } else if (question.contains("mustache")) { // Mustache (boolean)
            return String.valueOf(character.hasMustache());
        } else {
            return ""; // Return empty if the attribute doesn't match
        }
    }
    /* 
    // Method to get attribute from question selected 
    public static String selectedQuestion(String question) {
        String selectedAttribute = "";

        //store attribute from question 
        if (question.contains("male") || question.contains("female")) {
            selectedAttribute = "gender"; // Attribute is gender
        } else if (question.contains("hair")) {
            selectedAttribute = "hair"; // Attribute is hair
        } else if (question.contains("eye")) {
            selectedAttribute = "eye"; // Attribute is eye color
        } else if (question.contains("glasses")) {
            selectedAttribute = "glasses"; // Attribute is glasses
        } else if (question.contains("hat")) {
            selectedAttribute = "hat"; // Attribute is hat
        } else if (question.contains("jewelry")) {
            selectedAttribute = "jewelry"; // Attribute is jewelry
        } else if (question.contains("beard")) {
            selectedAttribute = "beard"; // Attribute is beard
        } else if (question.contains("mustache")) {
            selectedAttribute = "mustache"; // Attribute is mustache
        }

        return selectedAttribute;
    } */

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
                match = c.hasGlasses() == Boolean.parseBoolean(value);
            } else if (attribute.equalsIgnoreCase("hat")) {
                match = c.hasHat() == Boolean.parseBoolean(value);
            } else if (attribute.equalsIgnoreCase("jewelry")) {
                match = c.hasJewelry() == Boolean.parseBoolean(value);
            } else if (attribute.equalsIgnoreCase("beard")) {
                match = c.hasBeard() == Boolean.parseBoolean(value);
            } else if (attribute.equalsIgnoreCase("mustache")) {
                match = c.hasMustache() == Boolean.parseBoolean(value);
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
