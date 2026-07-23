import model.CarObservation;
import model.CarType;
import rules.PrivateSpeedRule;
import rules.SeatbeltRule;
import rules.TruckSpeedRule;
import service.QRadar;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //Initialize Radar Service
        QRadar radar = new QRadar();

        //Register Rules
        radar.addRule(new PrivateSpeedRule());
        radar.addRule(new TruckSpeedRule());
        radar.addRule(new SeatbeltRule());

        //Create mock observations
        CarObservation obs1 = new CarObservation("ABC1234", "2026-07-23", CarType.PRIVATE, 94, false);
        CarObservation obs2 = new CarObservation("ADD9876", "2026-07-23", CarType.TRUCK, 65, true);
        CarObservation obs3 = new CarObservation("CCf5678", "2026-07-23", CarType.PRIVATE, 70, true);
        CarObservation obs4 = new CarObservation("ABC1234", "2026-07-24", CarType.PRIVATE, 70, false);

        //Process observations
        radar.processObservation(obs1); // Violates speed and seatbelt
        radar.processObservation(obs2); // Violates truck speed
        radar.processObservation(obs3); // No violations (nothing printed)
        radar.processObservation(obs4); // Violates seatbelt only

        //Test getAllPossibleFines
        System.out.println("--- All Possible Fines ---");
        Map<String, Integer> allFines = radar.getAllPossibleFines();
        allFines.forEach((plate, amount) -> System.out.println(plate + " : " + amount + " EGP"));

        //Test getViolatedRulesCount
        System.out.println("\n--- Violated Rules Count ---");
        Map<String, Integer> ruleCounts = radar.getViolatedRulesCount();
        ruleCounts.forEach((rule, count) -> System.out.println(rule + " : " + count));
    }
}