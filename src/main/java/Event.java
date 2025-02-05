import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = parseDate(startDate);
        this.endDate = parseDate(endDate);
    }

    private LocalDateTime parseDate(String date) {
        try {
            return LocalDateTime.parse(date, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use d/M/yyyy HHmm.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startDate.format(OUTPUT_FORMATTER) + " to " + endDate.format(OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String convertToFileString() {
        return "E | " + (done ? "1" : "0") + " | " + description + " | " + startDate.format(INPUT_FORMATTER) + " | " + endDate.format(INPUT_FORMATTER);
    }
}
