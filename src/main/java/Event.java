public class Event extends Task{
    protected String startDate;
    protected String endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startDate + " to " + endDate + ")";
    }

    @Override
    public String convertToFileString() {
        return "E | " + (done ? "1" : "0") + " | " + description + " | " + startDate + " | " + endDate;
    }
}
