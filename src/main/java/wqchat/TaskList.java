package wqchat;
import wqchat.task.Task;
import java.util.ArrayList;

public class TaskList {
    private static final int WORDS_TO_FIND_INDEX = 5;

    /**
     * Deletes task with a specific index from tasks
     *
     * @param index index of task to be deleted.
     * @param tasks a list of tasks added.
     * @param taskCount number of tasks added.
     */
    public void deleteTask(int index, ArrayList<Task> tasks, int taskCount) {

        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index));
        if (taskCount == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        }
        tasks.remove(index);
    }

    /**
     * Marks the task selected as done.
     *
     * @param index index of task to be marked as done.
     * @param taskCount number of tasks added.
     * @param tasks a list of tasks added.
     * @param ui a Ui object that manages UI.
     * @throws WqchatException.InvalidIndexException If index is out of bound.
     * @throws WqchatException.NegativeIndexException If index is < 0.
     */
    public void markTaskAsDone(int index, int taskCount, ArrayList<Task> tasks, Ui ui) throws WqchatException.InvalidIndexException, WqchatException.NegativeIndexException {
        if (index + 1 > taskCount) {
            throw new WqchatException.InvalidIndexException();
        }
        if (index < 0) {
            throw new WqchatException.NegativeIndexException();
        }
        tasks.get(index).markAsDone();

        ui.printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("[X] ");
        System.out.println(tasks.get(index).getDescription());
        ui.printLine();
    }

    /**
     * Marks the selected task as not done.
     * @param index index of task to be marked as not done.
     * @param taskCount number of tasks added.
     * @param tasks a list of tasks added.
     * @param ui a Ui object that manages UI.
     * @throws WqchatException.InvalidIndexException If index is out of bound.
     * @throws WqchatException.NegativeIndexException If index is < 0.
     */
    public void markTaskAsUndone(int index, int taskCount, ArrayList<Task> tasks, Ui ui) throws WqchatException.InvalidIndexException, WqchatException.NegativeIndexException {
        if (index + 1 > taskCount) {
            throw new WqchatException.InvalidIndexException();
        }
        if (index <= 0) {
            throw new WqchatException.NegativeIndexException();
        }
        tasks.get(index).markAsNotDone();

        ui.printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.print("[ ] ");
        System.out.println(tasks.get(index).getDescription());
        ui.printLine();
    }

    /**
     * Finds tasks that contains a certain keyword.
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
                System.out.println((i + 1) + "." + tasks.get(i).getStatusIcon() + tasks.get(i).toString());
            }
        }
        if (!isFound) {
            System.out.println("No matching task :(");
        }
    }
}
