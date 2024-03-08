package wqchat.task;

/**
 * Represents a task that has a description.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of task class that contains description of the task.
     *
     * @param description description of the task.
     */

    public Task(String description) {
        this.description = description;
    }

    /**
     * Gets the status icon of a task.
     *
     * @return a string that represents the status of a task, "X" means done, " " means not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return a string that represents the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets whether task is done.
     *
     * @return 1 if the task is done, 0 if the task is not done.
     */
    public int getIsDone() {
        return isDone ? 1 : 0;
    }

    /**
     * Gets the type of the task.
     * @return type of the task, "T" for a to do task, "D" for a deadline, "E" for an event.
     */
    public abstract String getType();
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
