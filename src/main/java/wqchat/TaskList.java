package wqchat;
import wqchat.task.Task;
import java.util.ArrayList;

/**
 * Represents a task list that contains the task list and has operations to add/modify tasks in the list.
 */
public class TaskList {
    private static final int WORDS_TO_FIND_INDEX = 5;

    /**
     * Checks whether the inputted index is valid.
     *
     * @param index index of task to be checked.
     * @param taskCount number of tasks added.
     * @throws WqchatException.InvalidIndexException If index is out of bound.
     * @throws WqchatException.NegativeIndexException If index < 0.
     */
    public void checkIndex(int index, int taskCount) throws WqchatException.InvalidIndexException, WqchatException.NegativeIndexException {
        if (index + 1 > taskCount) {
            throw new WqchatException.InvalidIndexException();
        }
        if (index < 0) {
            throw new WqchatException.NegativeIndexException();
        }
    }

    /**
     * Deletes a task from the list of tasks
     *
     * @param index index of task to be deleted.
     * @param tasks a list of tasks added.
     * @param taskCount number of tasks added.
     */
    public void deleteTask(int index, ArrayList<Task> tasks, int taskCount) throws WqchatException.InvalidIndexException, WqchatException.NegativeIndexException {
        checkIndex(index, taskCount);

        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index));
        if (taskCount == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + (taskCount - 1) + " tasks in the list.");
        }
        tasks.remove(index);
    }

    /**
     * Marks the task selected as done.
     *
     * @param index index of task to be marked as done.
     * @param taskCount number of tasks added.
     * @param tasks a list of tasks added.
     * @param ui an Ui object that manages UI.
     * @throws WqchatException.InvalidIndexException If index is out of bound.
     * @throws WqchatException.NegativeIndexException If index is < 0.
     */
    public void markTaskAsDone(int index, int taskCount, ArrayList<Task> tasks, Ui ui) throws WqchatException.InvalidIndexException, WqchatException.NegativeIndexException {
        checkIndex(index, taskCount);
        tasks.get(index).markAsDone();

        ui.printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("[X] ");
        System.out.println(tasks.get(index).getDescription());
        ui.printLine();
    }

    /**
     * Marks the selected task as not done.
     *
     * @param index index of task to be marked as not done.
     * @param taskCount number of tasks added.
     * @param tasks a list of tasks added.
     * @param ui an Ui object that manages UI.
     * @throws WqchatException.InvalidIndexException If index is out of bound.
     * @throws WqchatException.NegativeIndexException If index is < 0.
     */
    public void markTaskAsUndone(int index, int taskCount, ArrayList<Task> tasks, Ui ui) throws WqchatException.InvalidIndexException, WqchatException.NegativeIndexException {
        checkIndex(index, taskCount);
        tasks.get(index).markAsNotDone();

        ui.printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.print("[ ] ");
        System.out.println(tasks.get(index).getDescription());
        ui.printLine();
    }

    /**
     * Finds tasks that contains a certain keyword.
     *
     * @param tasks a list of tasks added.
     * @param line user input.
     */
    public void findTask(ArrayList<Task> tasks, String line) {
        System.out.println("Here are the matching tasks in your list:");
        String wordToFind = line.substring(WORDS_TO_FIND_INDEX).trim();
        boolean isFound = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(wordToFind)) {
                isFound = true;
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
        }
        if (!isFound) {
            System.out.println("No matching task :(");
        }
    }
}
