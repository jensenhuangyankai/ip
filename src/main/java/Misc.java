import java.util.Scanner;


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
                    "\n\t---------------------------------------------------");
        }
}
