import java.io.IOException;
import java.util.*;

public class GPTzerofive {
    static List<Task> taskList = new ArrayList<>();

    

    public static void main(String[] args) throws GPTException, IOException {
        Misc.loadFromFile(taskList);
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
            Misc.handleCommand(input, taskList);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

        scanner.close();

        Misc.formattedPrint(goodbyeString);
    }

}
