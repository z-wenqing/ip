package wqchat.task;
public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to, Boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
    public String getType() {
        return "E";
    }
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}