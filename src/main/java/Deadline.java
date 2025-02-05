import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    public Deadline(String description, String by) {
        super(description);
        this.by = parseDate(by);
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
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String convertToFileString() {
        return "D | " + (done ? "1" : "0") + " | " + description + " | " + by.format(INPUT_FORMATTER);
    }

}
