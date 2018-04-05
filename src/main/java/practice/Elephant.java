package practice;

/**
 * Created by paramonovss on 29.03.17.
 */
public class Elephant extends Animal implements Jumpable {
    private boolean canJump = false;

    public Elephant() {
        super(2);
    }

    public boolean isPredator() {
        return false;
    }

    public boolean canJump() {
        return canJump;
    }
}
