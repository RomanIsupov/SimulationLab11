package main.com.example.simulationlab11;

import java.util.concurrent.ThreadLocalRandom;

public class Experiment {

    private final int totalQuantity;
    private final int eventsAmount = 5;
    private final Event[] events;

    {
        events = new Event[eventsAmount];
        double maximumProbability = 1.0;
        for (int i = 0; i < eventsAmount - 1; i++) {
            events[i] = new Event(
                    ThreadLocalRandom.current().nextDouble(0.0, maximumProbability / 2.0)
            );
            maximumProbability -= events[i].getProbability();
        }
        events[eventsAmount - 1] = new Event(maximumProbability);
    }

    public Experiment(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public void start() {
        double[] probabilities = getProbabilities();
        for (int i = 0; i < totalQuantity; i++) {
            double alpha = ThreadLocalRandom.current().nextDouble(0.0, 1.0);

            int eventNumber = 0;
            for (; eventNumber < (eventsAmount - 1) && alpha > probabilities[eventNumber]; eventNumber++) {
                alpha -= probabilities[eventNumber];
            }
            events[eventNumber].increaseQuantity();
        }
    }

    public double[] getProbabilities() {
        double[] probabilities = new double[eventsAmount];
        for (int i = 0; i < eventsAmount; i++) {
            probabilities[i] = events[i].getProbability();
        }
        return probabilities;
    }

    public double[] getFrequencies() {
        double[] frequencies = new double[eventsAmount];
        for (int i = 0; i < eventsAmount; i++) {
            frequencies[i] = (double)events[i].getQuantity() / (double) totalQuantity;
        }
        return frequencies;
    }

    public double getVariance(double[] probabilities) {
        double variance = 0.0;
        for (int i = 0; i < eventsAmount; i++) {
            variance += i * i * probabilities[i];
        }
        variance -= Math.pow(getAverage(probabilities), 2);
        return variance;
    }

    public double getVariance() {
        return getVariance(getProbabilities());
    }

    public double getAverage(double[] probabilities) {
        double average = 0.0;
        for (int i = 0; i < eventsAmount; i++) {
            average += i * probabilities[i];
        }
        return average;
    }

    public double getAverage() {
        return getAverage(getProbabilities());
    }

    public double getAverageError() {
        return Math.abs(getAverage(getFrequencies()) - getAverage()) / (1.0 + getAverage());
    }

    public double getVarianceError() {
        return Math.abs(getVariance(getFrequencies()) - getVariance()) / getVariance();
    }

    public double getChiSquared() {
        double chi = 0.0;
        double[] frequencies = getFrequencies();
        double[] probabilities = getProbabilities();
        for (int i = 0; i < eventsAmount; i++) {
            chi += Math.pow(frequencies[i], 2) / probabilities[i];
        }
        chi = (chi - 1) * totalQuantity;
        return chi;
    }
}
