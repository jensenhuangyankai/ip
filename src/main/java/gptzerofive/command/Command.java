package gptzerofive.command;

import gptzerofive.exception.GptException;
import gptzerofive.storage.Storage;
import gptzerofive.task.Task;
import gptzerofive.task.TaskList;
import gptzerofive.ui.Ui;

/**
 * Represents a command to be executed.
 */
public abstract class Command {
    public abstract String exec(TaskList taskList, Ui ui, Storage storage) throws GptException;
}

class ListCommand extends Command {
    @Override
    public String exec(TaskList taskList, Ui ui, Storage storage) {
        return ui.showTaskList(taskList.getTaskListString());
    }
}

class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String exec(TaskList taskList, Ui ui, Storage storage) throws GptException {
        Task task = taskList.getTask(index - 1);
        task.markAsDone();
        storage.saveToFile(taskList);
        return ui.formattedPrint("Nice! I've marked this task as done:\n" + task.toString());

    }
}

class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String exec(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);

        storage.saveToFile(taskList);
        return ui.formattedPrint("Got it. I've added this task:\n" + task.toString() + "\nNow you have "
                + taskList.size() + " tasks in the list.");
    }
}

class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String exec(TaskList taskList, Ui ui, Storage storage) throws GptException {
        Task task = taskList.removeTask(index - 1);

        storage.saveToFile(taskList);
        return ui.formattedPrint("Noted. I've removed this task:\n" + task.toString() + "\nNow you have "
                + taskList.size() + " tasks in the list.");
    }
}

class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String exec(TaskList taskList, Ui ui, Storage storage) {
        TaskList filteredTaskList = taskList.filterTasks(keyword);
        return ui.printFilteredTaskList(filteredTaskList.getTaskListString());
    }
}

class ErrorCommand extends Command {
    private final String err;

    public ErrorCommand(String err) {
        this.err = err;
    }

    @Override
    public String exec(TaskList taskList, Ui ui, Storage storage) {
        return ui.formattedPrint(err);
    }
}
