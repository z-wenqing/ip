package wqchat.task;
public class Todo extends Task {
    protected boolean isDone;
    public Todo(String description) {
        super(description);
    }
    public void setDone(boolean done) {
        isDone = done;
    }
    public boolean isDone() {
        return isDone;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}