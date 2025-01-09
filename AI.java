/*
 * Name: April, Lucas, Jerry, Ponnavaddn
 * Due Date: Jan 15, 2025 
 * Teacher: Mr. Chu
 * Course: ISC4U 
 * Assignemnt: Guess who ISP - AI class
 */

import java.util.ArrayList;
import java.util.Random;

public class AI {

   // Variables 
    private Random random = new Random(); // Random generator for question selection
    private ArrayList<Question> aiQuestions; // List of AI questions
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

    // Method to select the AI Questions
    public Question selectQuestion(ArrayList<Character> aiCharacters, Question lastQuestion, boolean answer) {
        // DEBUG - print 
        System.out.println("Current questions:");
        for (int i = 0; i < aiQuestions.size(); i++) {
            System.out.println(aiQuestions.get(i).getQuestion()); // Print each question
        }
        System.out.println();
        

        // Select random first question 
        if (isFirstQuestion) {
            isFirstQuestion = false; // Mark first question as used
            int index = random.nextInt(aiQuestions.size()); // Random index
            Question firstQuestion = aiQuestions.get(index); // Pick first question
            aiQuestions.remove(firstQuestion); // Remove asked question
            return firstQuestion; // Return question 
        }

        // For all other questions...

        // ArrayList to store removed questions 
        ArrayList<Question> toRemove = new ArrayList<>();

        // Case 1: remove questions if the attribute value no longer exists within the remaining characters 
            // e.g. No black haired characters remain => remove "Does your character have black hair?"
            for (int i = 0; i < aiQuestions.size(); i++) { // Loop through all questions 
            Question q = aiQuestions.get(i); // Get the question 
            boolean attributeExists = false; 
            
            // Loop through all the characters 
            for (int j = 0; j < aiCharacters.size(); j++) {
                Character c = aiCharacters.get(j); // Get the character 
                if (matchesAttribute(c, q)) { // Check attribute existence
                    attributeExists = true;
                    break; // Exit loop if match found
                }
            }

            // If attribute does not exist...
            if (!attributeExists) {
                toRemove.add(q); // Mark question for removal
            }
        }

         
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
        

        // Case 2: Remove redundant questions based on answer to questions  
            // E.g. If AI asks "Does your character have blue eyes?", if YES => remove all eye questions, if NO => remove only blue eyes quetion
        for (int i = 0; i < aiQuestions.size(); i++) { // Loop through all questions 
            Question q = aiQuestions.get(i); //Get the question 

            // Gender-specific filtering
            if (lastQuestion.getAttribute().equals("gender")) { // If a gender question is asked...
                if (q.getAttribute().equals("gender")) {
                    toRemove.add(q); // Remove all gender questions 
                }
                if (lastQuestion.getValue().equals("male") && answer) { // If the question is asking if the character is male and the answer is no...
                    if (q.getAttribute().equals("beard") || q.getAttribute().equals("mustache")) {
                        toRemove.add(q); // Remove male-specific features (No female has a beard or mustache so remove those questions)
                    }
                }
            }

            // Eye color filtering
            if (lastQuestion.getAttribute().equals("eye")) { // If the questions is about eyes and the answer is yes...
                if (answer && q.getAttribute().equals("eye")) {
                    toRemove.add(q); // Remove other eye color questions
                }
            }

            // Hair color filtering
            if (lastQuestion.getAttribute().equals("hair")) { // If the questions is about hair and the answer is yes...
                if (answer && q.getAttribute().equals("hair")) {
                    toRemove.add(q); // Remove other hair color questions
                }
            }
        }

         
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
        

        // Case 3: Remove Questions if all characters have the same attribute value (This covers very few specific cases)
            // E.g. if only male characters remain, remove male characters question 
        for (int i = 0; i < aiQuestions.size(); i++) { // Loop through all questions 
            Question q = aiQuestions.get(i); // Get question 
            boolean allSameValue = true; // Assume all have the same value initially
            String firstValue = ""; // To store the first character's value for comparison

            // Loop through remaining characters
            for (int j = 0; j < aiCharacters.size(); j++) {
                Character c = aiCharacters.get(j); // Get character 
                String value = ""; // Attribute value for comparison

                // Get attribute value based on question
                if (q.getAttribute().equals("gender")) {
                    value = c.getGender();
                } else if (q.getAttribute().equals("hair")) {
                    value = c.getHairColour();
                } else if (q.getAttribute().equals("eye")) {
                    value = c.getEyeColour();
                } else if (q.getAttribute().equals("glasses")) {
                    if (c.hasGlasses()) {
                        value = "yes";
                    } else {
                        value = "no";
                    }
                } else if (q.getAttribute().equals("hat")) {
                    if (c.hasHat()) {
                        value = "yes";
                    } else {
                        value = "no";
                    }
                } else if (q.getAttribute().equals("jewelry")) {
                    if (c.hasJewelry()) {
                        value = "yes";
                    } else {
                        value = "no";
                    }
                } else if (q.getAttribute().equals("beard")) {
                    if (c.hasBeard()) {
                        value = "yes";
                    } else {
                        value = "no";
                    }
                } else if (q.getAttribute().equals("mustache")) {
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
                    allSameValue = false; // If they do not all have the same attribute value, break 
                    break; 
                }
            }

            // If all characters have the same value, remove the question
            if (allSameValue) {
                toRemove.add(q);
            }
        }

         
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
    
    // Helper Method: this method checks if a given character matches the attribute and value specified in a question
    private boolean matchesAttribute(Character c, Question q) {
        // If the question is about gender... 
            // The same follows for all other attributes  
        if (q.getAttribute().equals("gender")) {
            // Compare the character's gender to the value in the question 
            return c.getGender().equalsIgnoreCase(q.getValue());
        } else if (q.getAttribute().equals("hair")) {
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
        
        return false; // Default case (should not run)
    } 

}


