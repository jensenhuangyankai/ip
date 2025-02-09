package gptzerofive;

import gptzerofive.command.*;
import gptzerofive.exception.GPTException;
import gptzerofive.storage.Storage;
import gptzerofive.task.TaskList;
import gptzerofive.ui.Ui;
import java.io.IOException;

public class GPTzerofive {
    private static final String FILE_PATH = "data/tasks.txt";
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    public GPTzerofive() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.taskList = new TaskList();
    }

    public void run() throws GPTException {
        ui.showWelcome();
        try {
            storage.loadFromFile(taskList);
        } catch (IOException | GPTException e) {
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

    public static void main(String[] args) throws GPTException {
        new GPTzerofive().run();
    }
}
