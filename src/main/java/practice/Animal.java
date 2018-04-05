package practice;

/**
 * Created by paramonovss on 29.03.17.
 */
public abstract class Animal {
    private int weight;

    public Animal(int weight) {
        this.weight = weight;
    }

    abstract boolean isPredator();

    protected int getWeight() {
        return weight;
    }

    public boolean isDangerous() {
        return isPredator() || getWeight() > 15;
    }
}
