import java.util.ArrayList;
import java.util.Random;

public class AI {

    // Random generator for selection
    private Random random = new Random();

    private ArrayList<Question> questions; // List of questions

    // Constructor that accepts the list of questions
    public AI(ArrayList<Question> questions) {
        this.questions = questions; // Store questions
    }

    // Method to randomly select a character
    public Character aiCharacter(ArrayList<Character> characters) {
        int index = random.nextInt(characters.size()); // Random index
        return characters.get(index); // Return the selected character
    }

    // Method to randomly select a question and determine the related attribute
    public Question selectRandomFirstQuestion() {
        int index = random.nextInt(questions.size()); // Pick a random index
        return questions.get(index); // Return the question
        
    }
}