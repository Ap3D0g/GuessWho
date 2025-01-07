import java.util.Comparator;

/**
 * A standalone Comparator that orders PlayerScore objects by descending score.
 */
public class ScoreComparator implements Comparator<PlayerScore> {
    @Override
    public int compare(PlayerScore a, PlayerScore b) {
        // Higher score first (descending order)
        return b.getScore() - a.getScore();
    }
}

