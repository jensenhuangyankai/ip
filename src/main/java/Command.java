public abstract class Command {
    public abstract void exec(TaskList taskList, Ui ui, Storage storage) throws GPTException;
}

class ListCommand extends Command {
    @Override
    public void exec(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList.getTaskListString());
    }
}

class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void exec(TaskList taskList, Ui ui, Storage storage) throws GPTException {
        Task task = taskList.getTask(index - 1);
        task.markAsDone();
        ui.formattedPrint("Nice! I've marked this task as done:\n" + task.toString());
        storage.saveToFile(taskList);
    }
}

class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void exec(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.formattedPrint("Got it. I've added this task:\n" + task.toString() + "\nNow you have " + taskList.size()
                + " tasks in the list.");
        storage.saveToFile(taskList);
    }
}

class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void exec(TaskList taskList, Ui ui, Storage storage) throws GPTException {
        Task task = taskList.removeTask(index - 1);
        ui.formattedPrint("Noted. I've removed this task:\n" + task.toString() + "\nNow you have " + taskList.size()
                + " tasks in the list.");
        storage.saveToFile(taskList);
    }
}
