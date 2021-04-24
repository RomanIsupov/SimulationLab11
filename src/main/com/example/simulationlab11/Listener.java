package main.com.example.simulationlab11;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Listener {

    public static void main(String args[]) {
        System.out.println("Hello. Please enter the quantity of events.");
        Scanner input = new Scanner(System.in);
        Experiment experiment = new Experiment(input.nextInt());
        experiment.start();
        printData(experiment);
    }

    private static void printData(Experiment experiment) {
        printProbabilities(experiment);
        printFrequencies(experiment);
        System.out.println();

        printAverage(experiment);
        printAverageError(experiment);
        System.out.println();

        printVariance(experiment);
        printVarianceError(experiment);
        System.out.println();

        printChiSquare(experiment);
    }

    private static void printProbabilities(Experiment experiment) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        double[] probabilities = experiment.getProbabilities();
        System.out.println("Theoretical probabilities:");
        for (double probability : probabilities) {
            System.out.print(decimalFormat.format(probability) + " ");
        }
        System.out.println();
    }

    private static void printFrequencies(Experiment experiment) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        double[] frequencies = experiment.getFrequencies();
        System.out.println("Empirical frequencies:");
        for (double frequency : frequencies) {
            System.out.print(decimalFormat.format(frequency) + " ");
        }
        System.out.println();
    }

    private static void printAverage(Experiment experiment) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        System.out.println("Average:");
        System.out.print(decimalFormat.format(experiment.getAverage()));
        System.out.println();
    }

    private static void printVariance(Experiment experiment) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        System.out.println("Variance:");
        System.out.print(decimalFormat.format(experiment.getVariance()));
        System.out.println();
    }

    private static void printAverageError(Experiment experiment) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        System.out.println("Average error:");
        System.out.print(decimalFormat.format(experiment.getAverageError()));
        System.out.println();
    }

    private static void printVarianceError(Experiment experiment) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        System.out.println("Variance error:");
        System.out.print(decimalFormat.format(experiment.getVarianceError()));
        System.out.println();
    }

    private static void printChiSquare(Experiment experiment) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        System.out.println("Chi squared:");
        System.out.print(decimalFormat.format(experiment.getChiSquared()));
        System.out.println();
    }
}
