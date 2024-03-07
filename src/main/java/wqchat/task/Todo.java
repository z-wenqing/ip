package wqchat.task;

/**
 * Represents a todo task that has a description and whether it is done.
 */
public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }
    public String getType() {
        return "T";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}