import java.util.ArrayList;
import java.util.Random;

public class AI {

    // Random generator for selection
    private Random random = new Random();

    private ArrayList<Question> aiQuestions; // List of questions

    private boolean isFirstQuestion = true; // Tracks if AI is asking the first question

    // Constructor that accepts the list of questions
    public AI(ArrayList<Question> aiQuestions) {
        this.aiQuestions = new ArrayList<>(aiQuestions); // Store questions
    }

    // Method to randomly select a character
    public Character aiCharacter(ArrayList<Character> characters) {
        int index = random.nextInt(characters.size()); // Random index
        return characters.get(index); // Return the selected character
    }

    // Method to Select the AI Questions
    public Question selectQuestion(ArrayList<Character> aiCharacters, Question lastQuestion, boolean answer) {
        /* 
        System.out.println("Current questions:");
        for (int i = 0; i < aiQuestions.size(); i++) {
            System.out.println(aiQuestions.get(i).getQuestion()); // Print each question
        }
        System.out.println();
        */

        // Select random first question 
        if (isFirstQuestion) {
            isFirstQuestion = false; // Mark first question as used
            int index = random.nextInt(aiQuestions.size()); // Random index
            Question firstQuestion = aiQuestions.get(index); // Pick first question
            aiQuestions.remove(firstQuestion); // Remove asked question
            return firstQuestion;
        }

        // ArrayList to store removed questions 
        ArrayList<Question> toRemove = new ArrayList<>();

        // Case 1: remove questions if the attribute value no longer exists within the remaining characters 
        for (int i = 0; i < aiQuestions.size(); i++) {
            Question q = aiQuestions.get(i);
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

        /* 
        // DEBUG: Print Remaining Questions**
        System.out.println("removed based on attribute values:");
        for (int i = 0; i < toRemove.size(); i++) {
            System.out.println(toRemove.get(i).getQuestion()); // Print each question
        }
        System.out.println("Questions left based on attribute values:");
        for (int i = 0; i < aiQuestions.size(); i++) {
            System.out.println(aiQuestions.get(i).getQuestion()); // Print each question
        }
        System.out.println();
        */

        // Case 2: Remove redundant questions based on answer to questions  
        for (int i = 0; i < aiQuestions.size(); i++) {
            Question q = aiQuestions.get(i);

            // Gender-specific filtering
            if (lastQuestion.getAttribute().equals("gender")) {
                if (q.getAttribute().equals("gender")) {
                    toRemove.add(q); // Remove all gender questions
                }
                if (lastQuestion.getValue().equals("male") && answer) {
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

        /* 
        // DEBUG: Print Remaining Questions
        System.out.println("Removed based on answer:");
        for (int i = 0; i < toRemove.size(); i++) {
            System.out.println(toRemove.get(i).getQuestion()); // Print each question
        }

        System.out.println("questions left based on answer:");
        for (int i = 0; i < aiQuestions.size(); i++) {
            System.out.println(aiQuestions.get(i).getQuestion()); // Print each question
        }
        System.out.println();
        */

        // Case 3: Remove Questions if all characters have the same attribute value 
        for (int i = 0; i < aiQuestions.size(); i++) {
            Question q = aiQuestions.get(i);
            boolean allSameValue = true; // Assume all have the same value initially
            String firstValue = ""; // To store the first character's value for comparison

            // Loop through remaining characters
            for (int j = 0; j < aiCharacters.size(); j++) {
                Character c = aiCharacters.get(j);
                String value = ""; // Attribute value for comparison

                // Get attribute value based on question
                if (q.getAttribute().equals("gender")) {
                    value = c.getGender();
                } else if (q.getAttribute().equals("hair")) {
                    value = c.getHairColour();
                } else if (q.getAttribute().equals("eye")) {
                    value = c.getEyeColour();
                } else if (q.getAttribute().equals("glasses")) {
                    // Replace ternary operator with if-else
                    if (c.hasGlasses()) {
                        value = "yes";
                    } else {
                        value = "no";
                    }
                } else if (q.getAttribute().equals("hat")) {
                    // Replace ternary operator with if-else
                    if (c.hasHat()) {
                        value = "yes";
                    } else {
                        value = "no";
                    }
                } else if (q.getAttribute().equals("jewelry")) {
                    // Replace ternary operator with if-else
                    if (c.hasJewelry()) {
                        value = "yes";
                    } else {
                        value = "no";
                    }
                } else if (q.getAttribute().equals("beard")) {
                    // Replace ternary operator with if-else
                    if (c.hasBeard()) {
                        value = "yes";
                    } else {
                        value = "no";
                    }
                } else if (q.getAttribute().equals("mustache")) {
                    // Replace ternary operator with if-else
                    if (c.hasMustache()) {
                        value = "yes";
                    } else {
                        value = "no";
                    }
                }

                // Compare values
                if (j == 0) {
                    firstValue = value; // Set first value
                } else if (!firstValue.equals(value)) { // Compare all other values to first value 
                    allSameValue = false;
                    break; // Exit early
                }
            }

            // If all characters have the same value, remove the question
            if (allSameValue) {
                toRemove.add(q);
            }
        }

        /* 
        // DEBUG: Print Questions After New Case
        System.out.println("Removed based on same attribute values:");
        for (int i = 0; i < toRemove.size(); i++) {
            System.out.println(toRemove.get(i).getQuestion());
        }
        System.out.println("Questions left based on same attribute values:");
        for (int i = 0; i < aiQuestions.size(); i++) {
            System.out.println(aiQuestions.get(i).getQuestion());
        }
        System.out.println();
        */

        // Remove all questions marked for deletion from AI Questions list 
        aiQuestions.removeAll(toRemove);

        // Pick the Next Question Randomly
        if (!aiQuestions.isEmpty()) {
            int index = random.nextInt(aiQuestions.size()); // Random index
            Question nextQuestion = aiQuestions.get(index); // Select question
            aiQuestions.remove(nextQuestion); // Remove the selected question
            return nextQuestion; // Return the next question
        }

        return null; // Return null if no valid questions remain (this should never exeucte)
    }
    
    // Helper Method: Match Attribute Values
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