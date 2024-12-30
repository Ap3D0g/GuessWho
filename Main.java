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
        characters.add(new Character ("Laura", "female", "black", "green", false, false, true, false, false));
        characters.add(new Character("Joe", "male", "bald", "brown", true, false, true, false, false));
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

        //Launch the GUI 
        new GUI(); // Start with the welcome page
    }
}
