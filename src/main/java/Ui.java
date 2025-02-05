import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String helloString = """
                Hello! I'm GPT0.5.
                What can I do for you today?
                """;
        formattedPrint(helloString);
    }

    public void showGoodbye() {
        String goodbyeString = """
                Goodbye! Have a nice day!""";
        formattedPrint(goodbyeString);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("\t---------------------------------------------------");
    }

    public void showError(String message) {
        formattedPrint(message);
    }

    public void showTaskList(String taskList) {
        formattedPrint("Here are the tasks in your list:\n" + taskList);
    }

    public void formattedPrint(String message) {
        Scanner scanner = new Scanner(message);
        String resultString = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            resultString += "\t" + line + "\n";
        }
        scanner.close();

        System.out.println("\t---------------------------------------------------\n" + resultString + "\t---------------------------------------------------");
    }
}
