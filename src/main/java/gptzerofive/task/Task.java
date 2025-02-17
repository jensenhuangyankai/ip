package gptzerofive.task;

/**
 * Represents a task.
 */
public abstract class Task {
    enum Type {
        TODO, DEADLINE, EVENT
    }

    private Type type; 
    private String description; 
    private Boolean isDone; 

    /**
     * Constructs a new Task with the specified description. The task is initially
     * marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns "X" if the task is done, else returns " ".
     *
     * @return "X" if the task is done, else " ".
     */
    public String xIfDone() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task.
     */
    public Type getType() {
        return type;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, else false.
     */
    public Boolean isDone() {
        return isDone;
    }

    /**
     * Sets the type of the task.
     *
     * @param type The type to set.
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the task as done or not done.
     *
     * @param done The status to set.
     */
    public void setDone(Boolean done) {
        this.isDone = done;
    }

    /**
     * Converts the task to a file string format.
     *
     * @return The task in file string format.
     */
    public abstract String convertToFileString();

    @Override
    public String toString() {
        return "[" + this.xIfDone() + "] " + description;
    }
}
