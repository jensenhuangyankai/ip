package gptzerofive.task;

public class Todo extends Task{
    
    public Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String convertToFileString() {
        return "T | " + (done ? "1" : "0") + " | " + description;
    }
    
}