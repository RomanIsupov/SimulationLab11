package main.com.example.simulationlab11;

public class Event {

    private int quantity;
    private final double probability;

    public Event(double probability) {
        this.quantity = 0;
        this.probability = probability;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public double getProbability() {
        return probability;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
