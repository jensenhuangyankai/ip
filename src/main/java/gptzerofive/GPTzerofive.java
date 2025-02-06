package gptzerofive;

import java.io.IOException;

import gptzerofive.command.Command;
import gptzerofive.command.Parser;
import gptzerofive.exception.GptException;
import gptzerofive.storage.Storage;
import gptzerofive.task.TaskList;
import gptzerofive.ui.Ui;

/**
 * Main class for GPTzerofive application.
 */
public class Gptzerofive {
    private static final String FILE_PATH = "data/tasks.txt";
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    /**
    * Inits a new Gptzerofive instance.
    */
    public Gptzerofive() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.taskList = new TaskList();
    }

    /**
     * Runs the application.
     *
     * @throws GptException If there is an error during execution.
     */
    public void run() throws GptException {
        ui.showWelcome();
        try {
            storage.loadFromFile(taskList);
        } catch (IOException | GptException e) {
            ui.showError(e.getMessage());
        }

        String input = ui.readCommand();
        while (!"bye".equals(input)) {
            Command command = Parser.parse(input);
            command.exec(taskList, ui, storage);
            input = ui.readCommand();
        }
        ui.showGoodbye();
    }

    /**
     * Main method to run the application.
     *
     * @param args Command line arguments.
     * @throws GptException If there is an error during execution.
     */
    public static void main(String[] args) throws GptException {
        new Gptzerofive().run();
    }
}
