package gptzerofive.ui;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a new Ui instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        String helloString = """
                Hello! I'm GPT0.5.
                What can I do for you today?
                """;
        formattedPrint(helloString);
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        String goodbyeString = """
                Goodbye! Have a nice day!""";
        formattedPrint(goodbyeString);
    }

    /**
     * Reads the user input command.
     *
     * @return The user input command.
     */
    public String readCommand() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Displays a line separator.
     */
    public void showLine() {
        System.out.println("\t---------------------------------------------------");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        formattedPrint(message);
    }

    /**
     * Displays the task list.
     *
     * @param taskList The task list to display.
     */
    public void showTaskList(String taskList) {
        formattedPrint("Here are the tasks in your list:\n" + taskList);
    }

    /**
     * Formats and prints a message.
     *
     * @param message The message to format and print.
     */
    public void formattedPrint(String message) {
        Scanner scanner = new Scanner(message);
        String resultString = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            resultString += "\t" + line + "\n";
        }
        scanner.close();

        System.out.println("\t---------------------------------------------------\n" + resultString
                + "\t---------------------------------------------------");
    }

    /**
     * Formats and prints a message.
     *
     * @param taskList The message to format and print. Take note that the parameter
     *                 is named tasklist, but is actually a string.
     */
    public void printFilteredTaskList(String taskList) {
        System.out.println("Here are the matching tasks in your list:\n"
                + "\t---------------------------------------------------\n" + taskList
                + "\t---------------------------------------------------");
    }
}
