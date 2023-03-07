package Enums;

public enum CinemaSeatType {
    NORMAL(0, "Normal seat without amenities"),
    DELUXE(25, "They are distinguished by more space and better material"),
    DREAM(50, "Fully electric chairs that can be folded out into a lying position");

    private double additionalCost;
    private String description;

    CinemaSeatType(double additionalCost, String description) {
        this.additionalCost = additionalCost;
        this.description = description;
    }

    public double getAdditionalCost() {
        return additionalCost;
    }

    public String getDescription() {
        return description;
    }
}
