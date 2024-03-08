package wqchat.task;

/**
 * Represents a todo task that has a description and whether it is done.
 */
public class Todo extends Task {
    /**
     * Constructor of Deadline class that contains description of the task.
     *
     * @param description description of the task.
     * @param isDone whether the task is done.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Gets the type of the task.
     *
     * @return the type of a to do task.
     */
    public String getType() {
        return "T";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}