package gptzerofive.command;

import gptzerofive.exception.GptException;
import gptzerofive.task.Deadline;
import gptzerofive.task.Event;
import gptzerofive.task.Todo;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses the input command.
     *
     * @param input The user input.
     * @return The command to be executed.
     * @throws GptException If the command is invalid.
     */
    public static Command parse(String input) throws GptException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String details = parts.length > 1 ? parts[1] : "";

        switch (command) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(Integer.parseInt(details));
        case "todo":
            if (details.isEmpty()) {
                throw new GptException("Please supply a description. Format: todo <description>");
            }
            return new AddCommand(new Todo(details));
        case "deadline":
            String[] deadlineSections = details.split(" /by ");
            if (deadlineSections.length < 2) {
                throw new GptException(
                        "Please supply a description and a deadline. Format: deadline <description> /by <deadline>");
            }
            return new AddCommand(new Deadline(deadlineSections[0], deadlineSections[1]));
        case "event":
            String[] eventSections = details.split(" /from | /to ");
            if (eventSections.length < 3) {
                String exceptionMessage = "Please supply a description, a /from time, and a /to time. "
                        + "Format: event <description> /from <start time> /to <end time>";
                throw new GptException(exceptionMessage);
            }
            return new AddCommand(new Event(eventSections[0], eventSections[1], eventSections[2]));
        case "find":
            return new FindCommand(details);
        case "delete":
            return new DeleteCommand(Integer.parseInt(details));
        default:
            throw new GptException("No such command found.");
        }
    }
}
