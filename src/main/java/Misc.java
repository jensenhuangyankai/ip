import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Misc {
    public static void formattedPrint(String message) {
        Scanner scanner = new Scanner(message);
        String resultString = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            resultString += "\t" + line + "\n";
        }
        scanner.close();

        System.out.println("""
                \t---------------------------------------------------\n""" +
                resultString +
                "\t---------------------------------------------------");
    }

    private static void taskEchoPrint(Task task, List<Task> taskList) {
        formattedPrint(
                "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + taskList.size()
                        + " tasks in the list.");
    }

    public static void handleCommand(String input, List<Task> taskList) throws GPTException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String details = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "list" -> Task.printTaskList(taskList);
            case "mark" -> {
                int index = Integer.parseInt(details) - 1;
                Task task = taskList.get(index);
                task.markAsDone();
                formattedPrint("Nice! I've marked this task as done:\n" + task.toString());
                saveToFile(taskList);
            }
            case "todo" -> {
                if (details.isEmpty()) {
                    throw new GPTException("Please supply a description. Format: todo <description>");
                }
                Todo newTask = new Todo(details);
                taskList.add(newTask);
                taskEchoPrint(newTask, taskList);
                saveToFile(taskList);

            }
            case "deadline" -> {
                String[] deadlineSections = details.split(" /by ");
                if (deadlineSections.length < 2) {
                    throw new GPTException(
                            "Please supply a description and a deadline. Format: deadline <description> /by <deadline>");
                }
                Deadline newTask = new Deadline(deadlineSections[0], deadlineSections[1]);
                taskList.add(newTask);
                taskEchoPrint(newTask, taskList);
                saveToFile(taskList);

            }
            case "event" -> {
                String[] eventSections = details.split(" /from | /to ");
                if (eventSections.length < 3) {
                    throw new GPTException(
                            "Please supply a description, a /from time, and a /to time. Format: event <description> /from <start time> /to <end time>");
                }
                Event newTask = new Event(eventSections[0], eventSections[1], eventSections[2]);
                taskList.add(newTask);
                taskEchoPrint(newTask, taskList);
                saveToFile(taskList);
            }
            case "delete" -> {
                int index = Integer.parseInt(details) - 1;
                Task task = taskList.remove(index);
                Misc.formattedPrint("Noted. I've removed this task:\n" + task.toString() + "\nNow you have "
                        + taskList.size() + " tasks in the list.");
                saveToFile(taskList);

            }
            default -> throw new GPTException("No such command found.");
        }
    }

    public static void loadFromFile(List<Task> taskList) throws IOException, GPTException {
        try {
            File file = new File("data/tasks.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                Task task;
                switch (parts[0]) {
                    case "T" -> task = new Todo(parts[2]);
                    case "D" -> task = new Deadline(parts[2], parts[3]);
                    case "E" -> task = new Event(parts[2], parts[3], parts[4]);
                    default -> throw new GPTException("Unknown task type.");
                }
                task.done = Boolean.valueOf(parts[1]);
                taskList.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            File file = new File("data/tasks.txt");
            file.createNewFile();
            System.out.println("File not found.");
        }
    }

    public static void saveToFile(List<Task> taskList) {
        try {
            File file = new File("data/tasks.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList) {
                writer.write(task.convertToFileString() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
