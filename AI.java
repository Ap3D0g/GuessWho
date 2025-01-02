import java.util.ArrayList;
import java.util.Random;

public class AI {

    // Random generator for selection
    private Random random = new Random();

    // Method to randomly select a character
    public Character aiCharacter(ArrayList<Character> characters) {
        int index = random.nextInt(characters.size()); // Random index
        return characters.get(index); // Return the selected character
    }

    // Method to randomly select a question and determine the related attribute
    public String selectRandomFirstQuestion() {
        // List of possible questions
        String[] questions = {
                "Is your character male?",
                "Is your character female?",
                "Does your character have black hair?",
                "Does your character have blonde hair?",
                "Does your character have white hair?",
                "Does your character have ginger hair?",
                "Does your character have blue hair?",
                "Does your character have brown hair?",
                "Does your character have brown eyes?",
                "Does your character have blue eyes?",
                "Does your character have green eyes?",
                "Does your character wear glasses?",
                "Is your character wearing a hat?",
                "Is your character wearing jewelry?",
                "Does your character have a beard?",
                "Does your character have a mustache?"
        };

        
        // Randomly select and return a question
        return questions[random.nextInt(questions.length)]; // Return the question
        
    }
}