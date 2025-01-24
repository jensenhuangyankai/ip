import java.util.Scanner;

public class GPTzerofive {
    static String[] taskList = new String[100];
    static int taskCount = 0;

    private static void addToList(String task) {
        taskList[taskCount] = task;
        taskCount++;
    }

    private static void printTaskList() {
        String returnValue = "";
        for (int i = 0; i < taskCount; i++) {
            returnValue += (i + 1) + ". " + taskList[i] + "\n";
        }
        formattedPrint(returnValue);
    }

    private static void formattedPrint(String message) {
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
                "\n\t---------------------------------------------------");
    }

    private static void taskEchoPrint(String task){
        formattedPrint("added: " + task);
    }

    public static void main(String[] args) {
        String helloString = """
                Hello! I'm GPT0.5.
                What can I do for you today?
                """;
        String goodbyeString = """
                Goodbye! Have a nice day!""";
        formattedPrint(helloString);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!"list".equals(input)) {
            taskEchoPrint(input);
            addToList(input);
            input = scanner.nextLine();

        }
        if (input.equals("list")) {
            printTaskList();
        }

        scanner.close();

        formattedPrint(goodbyeString);
    }
}
