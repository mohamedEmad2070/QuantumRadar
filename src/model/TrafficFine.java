package model;

import java.util.List;

public class TrafficFine {
    private String plateNumber;
    private String date;
    private List<Violation> violations;
    private int totalAmount;

    public TrafficFine(String plateNumber, String date, List<Violation> violations) {
        this.plateNumber = plateNumber;
        this.date = date;
        this.violations = violations;
        this.totalAmount = 0;
        for (Violation violation : violations) {
            this.totalAmount += violation.getFee();
        }
    }

    public String getPlateNumber() { return plateNumber; }
    public String getDate() { return date; }
    public List<Violation> getViolations() { return violations; }
    public int getTotalAmount() { return totalAmount; }
}
