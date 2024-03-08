package wqchat.task;

/**
 * Represents a deadline task that has description, due time and whether it is done
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor of Deadline class that contains description of the task, the time it is due and whether it is done.
     *
     * @param description description of the task.
     * @param by the time the task is due.
     * @param isDone whether the task is done.
     */
    public Deadline(String description, String by, Boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Gets the time the task is due.
     *
     * @return a string that represents the time the task is due.
     */
    public String getBy() {
        return by;
    }

    /**
     * Gets the type of the task.
     *
     * @return the type of deadline task.
     */
    public String getType() {
        return "D";
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

