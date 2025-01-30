import java.util.*;

public class GPTzerofive {
    static List<Task> taskList = new ArrayList<>();

    private static void taskEchoPrint(Task task) {
        Misc.formattedPrint(
                "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    public static void main(String[] args) throws GPTException {
        String helloString = """
                Hello! I'm GPT0.5.
                What can I do for you today?
                """;
        String goodbyeString = """
                Goodbye! Have a nice day!""";
        Misc.formattedPrint(helloString);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!"bye".equals(input)) {
            handleCommand(input);
            input = scanner.nextLine();

        }
        System.out.println("Bye. Hope to see you again soon!");

        scanner.close();

        Misc.formattedPrint(goodbyeString);
    }

    private static void handleCommand(String input) throws GPTException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String details = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "list" -> Task.printTaskList(taskList);
            case "todo" -> {
                if (details.isEmpty()) {
                    throw new GPTException("Please supply a description. Format: todo <description>");
                }
                Todo newTask = new Todo(details);
                taskList.add(newTask);
                taskEchoPrint(newTask);
            }
            case "deadline" -> {
                String[] deadlineParts = details.split(" /by ");
                if (deadlineParts.length < 2) {
                    throw new GPTException("Pleass supply a description and a deadline. Format: deadline <description> /by <deadline>");
                }
                Deadline newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
                taskList.add(newTask);
                taskEchoPrint(newTask);
            }
            case "event" -> {
                String[] eventParts = details.split(" /from | /to ");
                if (eventParts.length < 3) {
                    throw new GPTException("Please supply a description, a /from time, and a /to time. Format: event <description> /from <start time> /to <end time>");
                }
                Event newTask = new Event(eventParts[0], eventParts[1], eventParts[2]);
                taskList.add(newTask);
                taskEchoPrint(newTask);
            }
            case "delete" -> {
                int index = Integer.parseInt(details) - 1;
                Task task = taskList.remove(index);
                Misc.formattedPrint("Noted. I've removed this task:\n" + task.toString() + "\nNow you have " + taskList.size() + " tasks in the list.");
            }
            default -> throw new GPTException("No such command found.");
        }
    }
}
