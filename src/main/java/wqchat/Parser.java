package wqchat;

import wqchat.task.Deadline;
import wqchat.task.Event;
import wqchat.task.Task;
import wqchat.task.Todo;

import java.util.ArrayList;

public class Parser {
    private static final int DEADLINE_DESCRIPTION_INDEX = 9;
    private static final int DEADLINE_BY_INDEX_INCREMENT = 4;
    private static final int TODO_DESCRIPTION_INDEX = 5;
    private static final int EVENT_DESCRIPTION_INDEX = 6;
    private static final int EVENT_TO_INDEX_INCREMENT = 4;
    private static final int Event_FROM_INDEX_INCREMENT = 6;

    public Parser() {
    }

    /**
     * Processes deadline from user input.
     *
     * @param line user input.
     * @param tasks a list of tasks added.
     * @param taskCount number of tasks added.
     * @throws WqchatException.MissingDueTimeException If "/by" is not found.
     * @throws WqchatException.MissingDescriptionException If description of the deadline is not found.
     */
    public void addDeadline(String line, ArrayList<Task> tasks, int taskCount) throws WqchatException.MissingDueTimeException, WqchatException.MissingDescriptionException {
        int indexOfSlash = line.indexOf("/by");
        if (indexOfSlash == -1) {
            throw new WqchatException.MissingDueTimeException();
        }

        String description = line.substring(DEADLINE_DESCRIPTION_INDEX, indexOfSlash).trim();
        if (description.isEmpty()) {
            throw new WqchatException.MissingDescriptionException();
        }
        int indexOfBy = indexOfSlash + DEADLINE_BY_INDEX_INCREMENT;
        String by = line.substring(indexOfBy).trim();

        tasks.add(taskCount, new Deadline(description, by));
    }

    /**
     * Processes todo from user input.
     *
     * @param line user input.
     * @param tasks a list of tasks added.
     * @param taskCount number of tasks added.
     */
    public void addTodo(String line, ArrayList<Task> tasks, int taskCount) {
        String description = line.substring(TODO_DESCRIPTION_INDEX).trim();
        tasks.add(taskCount, new Todo(description, false));
    }

    /**
     * Processes event from user input.
     *
     * @param line user input.
     * @param tasks a list of tasks added.
     * @param taskCount number of tasks added.
     * @throws WqchatException.MissingDueTimeException If "/from" or "/to" is not found.
     * @throws WqchatException.MissingDescriptionException If description of the event is not found.
     */
    public void addEvent(String line, ArrayList<Task> tasks, int taskCount) throws WqchatException.MissingDueTimeException, WqchatException.MissingDescriptionException {
        int indexOfFrom = line.indexOf("/from");
        if (indexOfFrom == -1) {
            throw new WqchatException.MissingDueTimeException();
        }
        int indexOfTo = line.indexOf("/to");
        if (indexOfTo == -1) {
            throw new WqchatException.MissingDueTimeException();
        }

        String description = line.substring(EVENT_DESCRIPTION_INDEX, line.indexOf("/from"));
        if (description.isEmpty()) {
            throw new WqchatException.MissingDescriptionException();
        }
        String from = line.substring(indexOfFrom + Event_FROM_INDEX_INCREMENT, indexOfTo).trim(); // -1 to exclude the space
        String to = line.substring(indexOfTo + EVENT_TO_INDEX_INCREMENT);

        tasks.add(taskCount, new Event(description, from, to));
    }
}
