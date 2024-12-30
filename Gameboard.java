import java.util.ArrayList;

public class Gameboard {
    
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

    // Method to get attribute from question selected 
    public static String selectedQuestion(String question) {
        String selectedAttribute = "";

        //store attribute from question 
        if (question.contains("male")) {
            selectedAttribute = "gender"; // Attribute is gender
        } else if (question.contains("female")) {
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
