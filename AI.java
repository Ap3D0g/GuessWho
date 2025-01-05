import java.util.ArrayList;
import java.util.Random;

public class AI {

    // Random generator for selection
    private Random random = new Random();

    private ArrayList<Question> questions; // List of questions

    private boolean isFirstQuestion = true; // Tracks if AI is asking the first question

    // Constructor that accepts the list of questions
    public AI(ArrayList<Question> questions) {
        this.questions = questions; // Store questions
    }

    // Method to randomly select a character
    public Character aiCharacter(ArrayList<Character> characters) {
        int index = random.nextInt(characters.size()); // Random index
        return characters.get(index); // Return the selected character
    }

    // **Unified Method to Select the Next Question**
    public Question selectQuestion(ArrayList<Character> aiCharacters, Question lastQuestion, boolean answer) {
        // **First Question - Random Selection**
        if (isFirstQuestion) {
            isFirstQuestion = false; // Mark first question as used
            int index = random.nextInt(questions.size()); // Random index
            Question firstQuestion = questions.get(index); // Pick first question
            questions.remove(firstQuestion); // Remove asked question
            return firstQuestion;
        }

        // **Apply Filters for Remaining Questions**
        ArrayList<Question> toRemove = new ArrayList<>();

        // 1. **Filter by Remaining Attributes**
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            boolean attributeExists = false;

            for (int j = 0; j < aiCharacters.size(); j++) {
                Character c = aiCharacters.get(j);
                if (matchesAttribute(c, q)) { // Check attribute existence
                    attributeExists = true;
                    break; // Exit loop if match found
                }
            }

            if (!attributeExists) {
                toRemove.add(q); // Mark question for removal
            }
        }

        // 2. **Filter by Answer to Last Question**
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);

            // Gender-specific filtering
            if (lastQuestion.getAttribute().equals("gender")) {
                if (q.getAttribute().equals("gender")) {
                    toRemove.add(q); // Remove all gender questions
                }
                if (lastQuestion.getValue().equals("male") && !answer) {
                    if (q.getAttribute().equals("beard") || q.getAttribute().equals("mustache")) {
                        toRemove.add(q); // Remove male-specific features
                    }
                }
            }

            // Eye color filtering
            if (lastQuestion.getAttribute().equals("eye")) {
                if (answer && q.getAttribute().equals("eye")) {
                    toRemove.add(q); // Remove other eye color questions
                }
            }

            // Hair color filtering
            if (lastQuestion.getAttribute().equals("hair")) {
                if (answer && q.getAttribute().equals("hair")) {
                    toRemove.add(q); // Remove other hair color questions
                }
            }
        }

        // **Remove Questions Marked for Deletion**
        questions.removeAll(toRemove);

        // **DEBUG - Print Remaining Questions**
        System.out.println("Remaining Questions for AI:");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i).getQuestion()); // Print each question
        }

        // **Pick the Next Question Randomly**
        if (!questions.isEmpty()) {
            int index = random.nextInt(questions.size()); // Random index
            Question nextQuestion = questions.get(index); // Select question
            questions.remove(nextQuestion); // Remove the selected question
            return nextQuestion; // Return the next question
        }

        return null; // Return null if no valid questions remain
    }

    // **Helper Method: Match Attribute Values**
    private boolean matchesAttribute(Character c, Question q) {
        if (q.getAttribute().equals("gender")) {
            return c.getGender().equalsIgnoreCase(q.getValue());
        }
        for (int i = 0; i < 1; i++) { // Simplify attributes check using counted loop
            if (q.getAttribute().equals("hair")) {
                return c.getHairColour().equalsIgnoreCase(q.getValue());
            } else if (q.getAttribute().equals("eye")) {
                return c.getEyeColour().equalsIgnoreCase(q.getValue());
            } else if (q.getAttribute().equals("glasses")) {
                return c.hasGlasses();
            } else if (q.getAttribute().equals("hat")) {
                return c.hasHat();
            } else if (q.getAttribute().equals("jewelry")) {
                return c.hasJewelry();
            } else if (q.getAttribute().equals("beard")) {
                return c.hasBeard();
            } else if (q.getAttribute().equals("mustache")) {
                return c.hasMustache();
            }
        }
        return false; // Default case
    }

}