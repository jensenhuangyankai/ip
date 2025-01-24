import java.util.Scanner;

public class GPTzerofive {
    private static void GPTprint(String message) {
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

    public static void main(String[] args) {
        String helloString = """
                Hello! I'm GPT0.5.
                What can I do for you today?
                """;
        String goodbyeString = """
                Goodbye! Have a nice day!""";
        GPTprint(helloString);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        GPTprint(input);

        GPTprint(goodbyeString);
    }
}
