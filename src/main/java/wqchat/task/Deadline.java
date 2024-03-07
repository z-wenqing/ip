package wqchat.task;

/**
 * Represents a deadline task that has description, due time and whether it is done
 */
public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by, Boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }
    public String getBy() {
        return by;
    }
    public String getType() {
        return "D";
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

