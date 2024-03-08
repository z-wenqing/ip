package wqchat.task;

/**
 * Represents an event task that has description, starting time, end time and whether its is done.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor of Event class that contains description of the task, starting time, end time and whether it is done.
     * @param description description of the task.
     * @param from starting time of the task.
     * @param to end time of the task.
     * @param isDone whether the task is done.
     */
    public Event(String description, String from, String to, Boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    /**
     *  Gets the starting time of the event.
     *
     * @return a string that represents the starting time of the event.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the end time of the event.
     *
     * @return a string that represents the end time of the event.
     */
    public String getTo() {
        return to;
    }

    /**
     * Gets the type of the task.
     *
     * @return the type of event task.
     */
    public String getType() {
        return "E";
    }
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}