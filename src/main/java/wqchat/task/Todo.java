package wqchat.task;
public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description);
    }
    public void setDone(boolean done) {
        isDone = done;
    }

    public String getType() {
        return "T";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}