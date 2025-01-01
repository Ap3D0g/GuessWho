import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

public class GUI {

    // Variables
    private String[] characterNames = { // Character names matching image filenames
            "Amy", "David", "Leo", "Gabe", "Katie", "Olivia",
            "Jordan", "Carmen", "Laura", "Joe", "Mike", "Al",
            "Daniel", "Sophia", "Nick", "Lily", "Liz", "Mia",
            "Emma", "Rachel", "Ben", "Eric", "Farah", "Sam"
    };

    private ArrayList<Character> characters;
    private Gameboard gameboard;
    private AI aiInstance = new AI(); // Create an AI object
    private Character ai;
    private String selectedCharacter; // Stores player's selected character
    private JLabel selectedCharacterLabel; // Displays selected character image
    private boolean characterSelected = false; // Tracks whether a character has been selected
    private String question;
    private String getAttributeFromQuestion;  // Stores the selected attribute from question
    private JFrame boardFrame;

    // Constructor - Welcome Page
    public GUI(ArrayList<Character> characters) {
        this.characters = characters;
        gameboard = new Gameboard();

        welcomeScreen();
    }

    private void welcomeScreen() {
        // AI Selects a Character
        ai = aiInstance.aiCharacter(characters); // AI randomly selects a character
        //System.out.println("AI selected: " + ai.getName()); // Debugging output

        // Create the Welcome Frame
        JFrame frame = new JFrame("Guess Who - Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Welcome to Guess Who!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 20, 20));

        // Start Button
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonPanel.add(startButton);

        // Rules Button
        JButton rulesButton = new JButton("Rules / How to Play");
        rulesButton.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonPanel.add(rulesButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Start Button Action
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close welcome window
                openGameBoard(); // Open the gameboard
            }
        });

        // Rules Button Action
        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,
                        "How to Play:\n\n1. Choose a character for the computer to guess.\n" +
                                "2. Take turns asking yes/no questions to eliminate characters.\n" +
                                "3. The first to correctly guess the opponent's character wins!",
                        "Game Rules",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Display the Welcome Frame
        frame.setVisible(true);
    }

    // Gameboard Window
    private void openGameBoard() {
        // Create the Board Frame
        boardFrame = new JFrame("Guess Who - Gameboard");
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.setSize(1200, 800); // Enlarged window size
        boardFrame.setLayout(new BorderLayout());

        // Title Label
        JLabel title = new JLabel("Guess Who - Gameboard", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        boardFrame.add(title, BorderLayout.NORTH);

        // Gameboard Panel - 6x4 Grid (6 columns, 4 rows)
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4, 6, 5, 5)); // 4 rows, 6 columns

        // Side Panel for displaying the selected character
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BorderLayout());
        sidePanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding around the side panel

        selectedCharacterLabel = new JLabel(); // Placeholder for selected character image
        selectedCharacterLabel.setHorizontalAlignment(JLabel.CENTER);
        sidePanel.add(selectedCharacterLabel, BorderLayout.CENTER);
        boardFrame.add(sidePanel, BorderLayout.EAST);

        // Add buttons with images for each character
        for (int i = 0; i < characterNames.length; i++) { 
            String name = characterNames[i]; // Access each name by index
            JButton charButton = new JButton();

            // Load and resize the image for each character
            ImageIcon icon = new ImageIcon("Characters/" + name + ".png"); // Load image (e.g., "Amy.png")
            Image img = icon.getImage().getScaledInstance(120, 150, Image.SCALE_SMOOTH); // Larger size
            charButton.setIcon(new ImageIcon(img)); // Set the resized image as the button icon
            charButton.setToolTipText(name); // Tooltip with character name

            // Style the button (no border for cleaner look)
            charButton.setBorderPainted(false);
            charButton.setFocusPainted(false);
            charButton.setContentAreaFilled(false);

            // Add button to grid panel
            gridPanel.add(charButton);

            // Action Listener for Button Click
            charButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { 
                    // If this button is already disabled, exit early (no error message)
                    if (!charButton.isEnabled()) {
                        return; // Simply ignore further clicks
                    }

                    /* SHOWS ERROR MESSAGE INSTEAD OF JUST DISABLING THE BUTTONS
                    // Prevent selection if a character has already been chosen
                    if (characterSelected) {
                        JOptionPane.showMessageDialog(boardFrame,
                                "You have already selected a character!",
                                "Selection Error",
                                JOptionPane.ERROR_MESSAGE);
                        return; // Exit early
                    }
                    */
                    
                    // Save the selected character
                    selectedCharacter = name;
                    characterSelected = true; // Mark a character as selected

                    // Display the selected character's image on the right panel
                    ImageIcon selectedIcon = new ImageIcon("Characters/" + name + ".png");
                    Image selectedImg = selectedIcon.getImage().getScaledInstance(200, 250, Image.SCALE_SMOOTH); // Larger image
                    selectedCharacterLabel.setIcon(new ImageIcon(selectedImg));

                    // Confirmation popup
                    JOptionPane.showMessageDialog(boardFrame,
                            "You chose: " + selectedCharacter,
                            "Character Selected",
                            JOptionPane.INFORMATION_MESSAGE);

                    aiTurn();
                     
                    // Disable all buttons after selection
                    for (int i = 0; i < gridPanel.getComponentCount(); i++) {
                        JButton button = (JButton) gridPanel.getComponent(i); // Directly cast to JButton
                        button.setDisabledIcon(button.getIcon()); // Keep the icon visible
                        button.setEnabled(false); // Disable the button
                    }

                    // Next turn popup 
                    JOptionPane.showMessageDialog(boardFrame, // Next turn popup
                            "It's your turn! Choose a question from the dropdown to ask.",
                            "Your Turn",
                            JOptionPane.INFORMATION_MESSAGE); 
                }
            });
        }

        // Add grid panel to the board frame
        boardFrame.add(gridPanel, BorderLayout.CENTER);
        
        // Dropdown for Questions
        JPanel bottomPanel = new JPanel();
        JLabel questionLabel = new JLabel("Ask a Question:");
        JComboBox<String> questionDropdown = new JComboBox<>(new String[]{
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
        });
        JButton askButton = new JButton("Ask");

        bottomPanel.add(questionLabel);
        bottomPanel.add(questionDropdown);
        bottomPanel.add(askButton);

        boardFrame.add(bottomPanel, BorderLayout.SOUTH);

        // Player turn asking a question
        askButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                question = (String) questionDropdown.getSelectedItem(); // Store selected question
                JOptionPane.showMessageDialog(boardFrame,
                        "You asked: " + question,
                        "Question Asked",
                        JOptionPane.INFORMATION_MESSAGE);
                
                // Switch to AI's turn
                gameboard.switchTurn();
                aiTurn();
            }
        });

        // Display Board Frame
        boardFrame.setVisible(true);

        // Popup for initial character selection
        JOptionPane.showMessageDialog(boardFrame,
                "Please choose a character for the computer to guess!",
                "Character Selection",
                JOptionPane.INFORMATION_MESSAGE);

    }

    // AI Turn
    private void aiTurn() {
        if (!gameboard.isPlayerTurn()) { // AI's turn
            // AI selects a question
            String aiQuestion = aiInstance.selectRandomFirstQuestion();

            // Keep showing the dialog until the correct answer is selected
            boolean validAnswer = false; // Tracks whether the answer is valid

            while (!validAnswer) { // Loop until valid input
                // Show a dialog with the AI's question and Yes/No buttons
                int answer = JOptionPane.showOptionDialog(
                        boardFrame,
                        "AI asks: " + aiQuestion, // AI's question
                        "AI's Turn", // Title
                        JOptionPane.YES_NO_OPTION, // Button options
                        JOptionPane.QUESTION_MESSAGE, // Icon type
                        null, // Default icon
                        null, // Default Button labels
                        null // Default button
                );

                // Check if the player's response is valid
                validAnswer = isValidAnswer(aiQuestion, answer);

                // If invalid, stay in the loop (dialog won't close until valid)
            }

            // Once a valid answer is selected, switch back to the player's turn
            gameboard.switchTurn();
        }
}

    // Validate AI Question
    private boolean isValidAnswer(String question, int answer) {
        Character playerCharacter = Gameboard.chosenCharacter(characters, selectedCharacter);
        String value = Gameboard.getAttributeFromQuestion(question, playerCharacter);
        boolean expectedAnswer = Boolean.parseBoolean(value);
        return (answer == JOptionPane.YES_OPTION) == expectedAnswer;
    }
}