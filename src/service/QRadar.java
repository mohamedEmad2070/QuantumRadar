package service;

import model.CarObservation;
import model.TrafficFine;
import model.Violation;
import rules.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QRadar {
    private List<Rule> rules;
    private List<TrafficFine> issuedFines;

    public QRadar() {
        this.rules = new ArrayList<>();
        this.issuedFines = new ArrayList<>();
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public void processObservation(CarObservation observation) {
        List<Violation> violations = new ArrayList<>();

        for (Rule rule : rules) {
            Violation v = rule.evaluate(observation);
            if (v != null) {
                violations.add(v);
            }
        }

        if (!violations.isEmpty()) {
            TrafficFine fine = new TrafficFine(observation.getPlateNumber(), observation.getDate(), violations);
            issuedFines.add(fine);
            printFine(fine);
        }
    }

    private void printFine(TrafficFine fine) {
        System.out.println("Traffic for car " + fine.getPlateNumber());
        System.out.println("Total amount: " + fine.getTotalAmount() + " EGP");
        System.out.println("Violations:");
        for (Violation v : fine.getViolations()) {
            System.out.println("- " + v.getDescription() + " : " + v.getFee() + " EGP");
        }
        System.out.println();
    }

    public Map<String, Integer> getAllPossibleFines() {
        Map<String, Integer> plateFines = new HashMap<>();
        for (TrafficFine fine : issuedFines) {
            plateFines.put(
                    fine.getPlateNumber(),
                    plateFines.getOrDefault(fine.getPlateNumber(), 0) + fine.getTotalAmount()
            );
        }
        return plateFines;
    }

    public Map<String, Integer> getViolatedRulesCount() {
        Map<String, Integer> ruleCounts = new HashMap<>();
        for (TrafficFine fine : issuedFines) {
            for (Violation v : fine.getViolations()) {
                ruleCounts.put(
                        v.getRuleName(),
                        ruleCounts.getOrDefault(v.getRuleName(), 0) + 1
                );
            }
        }
        return ruleCounts;
    }
}
