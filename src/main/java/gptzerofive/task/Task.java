package gptzerofive.task;

public abstract class Task {
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

    public Type getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isDone() {
        return done;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public abstract String convertToFileString();

    @Override
    public String toString() {
        return "[" + this.xIfDone() + "] " + description;
    }
}