package gptzerofive.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        assert tasks != null : "Task list should not be null";
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        assert task != null : "Task should not be null";
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Filters the tasks based on the keyword.
     *
     * @param keyword the keyword to filter by
     * @return TaskList containing tasks that contain the keyword
     */
    public TaskList filterTasks(String keyword) {
        assert keyword != null : "Keyword should not be null";
        List<Task> filteredTasks = tasks.stream().filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
        return new TaskList(filteredTasks);
    }

    public String getTaskListString() {
        StringBuilder returnValue = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            returnValue.append(i + 1).append(".").append(task.toString()).append("\n");
        }
        return returnValue.toString();
    }
}
