package wqchat;

import wqchat.logic.Wqchat;
import wqchat.task.Deadline;
import wqchat.task.Task;
import wqchat.task.Todo;

import java.util.ArrayList;

public class Parser {
    private static final int DEADLINE_DESCRIPTION_INDEX = 9;
    private static final int DEADLINE_BY_INDEX_INCREMENT = 4;
    private static final int TODO_DESCRIPTION_INDEX = 5;

    public Parser() {
    }

    public void addDeadline(String line, ArrayList<Task> tasks, int taskCount, Storage storage) throws Wqchat.MissingDueTimeException, Wqchat.MissingDescriptionException {
        int indexOfSlash = line.indexOf("/by");
        if (indexOfSlash == -1) {
            throw new Wqchat.MissingDueTimeException();
        }

        String description = line.substring(DEADLINE_DESCRIPTION_INDEX, indexOfSlash).trim();
        if (description.isEmpty()) {
            throw new Wqchat.MissingDescriptionException();
        }
        int indexOfBy = indexOfSlash + DEADLINE_BY_INDEX_INCREMENT;
        String by = line.substring(indexOfBy).trim();

        tasks.add(taskCount, new Deadline(description, by));
        storage.addTaskInFile(taskCount, tasks);
    }

    public void addTodo(String line, ArrayList<Task> tasks, int taskCount, Storage storage) {
        String description = line.substring(TODO_DESCRIPTION_INDEX).trim();
        tasks.add(taskCount, new Todo(description, false));
        storage.addTaskInFile(taskCount, tasks);
    }
}
