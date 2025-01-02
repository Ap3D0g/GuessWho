import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main (String[] args) {

        ArrayList<Character> characters = new ArrayList<>();
        
        characters.add(new Character("Amy", "Female", "black", "brown", true, false, false, false, false));
        characters.add(new Character("David", "male", "blonde", "brown", false, true, false, false, true));
        characters.add(new Character("Leo", "male", "white", "brown", false, false, false, false, true));
        characters.add(new Character("Gabe", "male", "brown", "brown", false, false, false, false, false));
        characters.add(new Character("Katie", "female", "blonde", "blue", false, true, false, false, false));
        characters.add(new Character("Olivia", "female", "brown", "brown", false, false, false, false, false));
        characters.add(new Character("Jordan", "male", "brown", "brown", false, false, true, true, true));
        characters.add(new Character("Carmen", "female", "white", "brown", false, false, true, false, false));
        characters.add(new Character("Laura", "female", "black", "green", false, false, true, false, false));
        characters.add(new Character("Joe", "male", "bald", "brown", true, false, false, false, true));
        characters.add(new Character("Mike", "male", "black", "brown", false, true, false, false, false));
        characters.add(new Character("Al", "male", "white", "green", true, false, false, true, true));
        characters.add(new Character("Daniel", "male", "ginger", "green", false, false, false, true, true));
        characters.add(new Character("Sophia", "female", "brown", "green", false, false, true, false, false));
        characters.add(new Character("Nick", "male", "blonde", "brown", false, false, true, false, false));
        characters.add(new Character("Lily", "female", "brown", "green", false, true, false, false, false));
        characters.add(new Character("Liz", "female", "white", "blue", true, false, false, false, false));
        characters.add(new Character("Mia", "female", "black", "brown", false, false, false, false, false));
        characters.add(new Character("Emma", "female", "ginger", "brown", false, false, false, false, false));
        characters.add(new Character("Rachel", "female", "brown", "blue", true, false, true, false, false));
        characters.add(new Character("Ben", "male", "brown", "brown", true, false, true, false, false));
        characters.add(new Character("Eric", "male", "blue", "blue", false, false, false, false, false));
        characters.add(new Character("Farah", "female", "black", "blue", false, false, false, false, false));
        characters.add(new Character("Sam", "male", "black", "green", false, true, false, false, false));
        
        // Initialize Questions
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("Is your character male?", "gender", "male"));
        questions.add(new Question("Is your character female?", "gender", "female"));
        questions.add(new Question("Does your character have black hair?", "hair", "black"));
        questions.add(new Question("Does your character have blonde hair?", "hair", "blonde"));
        questions.add(new Question("Does your character have white hair?", "hair", "white"));
        questions.add(new Question("Does your character have ginger hair?", "hair", "ginger"));
        questions.add(new Question("Does your character have blue hair?", "hair", "blue"));
        questions.add(new Question("Does your character have brown hair?", "hair", "brown"));
        questions.add(new Question("Does your character have brown eyes?", "eye", "brown"));
        questions.add(new Question("Does your character have blue eyes?", "eye", "blue"));
        questions.add(new Question("Does your character have green eyes?", "eye", "green"));
        questions.add(new Question("Does your character wear glasses?", "glasses", ""));
        questions.add(new Question("Is your character wearing a hat?", "hat", ""));
        questions.add(new Question("Is your character wearing jewelry?", "jewelry", ""));
        questions.add(new Question("Does your character have a beard?", "beard", ""));
        questions.add(new Question("Does your character have a mustache?", "mustache", ""));

        // Pass characters and questions to GUI
        new GUI(characters, questions); 
    }
}
