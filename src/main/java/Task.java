
import java.util.List;

public class Task {
    enum Type {
        TODO, DEADLINE, EVENT
    }

    Type type;
    String description;
    Boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Returns "X" if the task is done, else returns " ".
     * 
     */
    public String xIfDone() {
        return this.done ? "X" : " ";
    }

    public void markAsDone() {
        this.done = true;
    }

    public static void printTaskList(List<Task> taskList) {
        String returnValue = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            returnValue += (i + 1) + "." + task.toString() + "\n";
        }
        Misc.formattedPrint("Here are the tasks in your list:\n" + returnValue);
    }

    @Override
    public String toString() {
        return "[" + this.xIfDone() + "] " + description;
    }
}
