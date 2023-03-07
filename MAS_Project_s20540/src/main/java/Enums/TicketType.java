package Enums;

public enum TicketType {
    STUDENT(20), ADULT(30);

    private double ticketPrice;

    TicketType(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
}
