public class PlayerGuesses {
    private String playerName;
    private int guesses;

    public PlayerGuesses(String playerName, int guesses) {
        this.playerName = playerName;
        this.guesses = guesses;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getGuesses() {
        return guesses;
    }

    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }
}


