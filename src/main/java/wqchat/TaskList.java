package wqchat;

import wqchat.logic.Wqchat;
import wqchat.task.Task;

import java.util.ArrayList;

public class TaskList {
    public void deleteTask(int index, Ui ui, ArrayList<Task> tasks, int taskCount) {
        ui.printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index));
        if (taskCount == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        }
        tasks.remove(index);
    }

    public void markTaskAsDone(int index, int taskCount, ArrayList<Task> tasks, Ui ui) throws Wqchat.InvalidIndexException, Wqchat.NegativeIndexException {
        if (index + 1 > taskCount) {
            throw new Wqchat.InvalidIndexException();
        }
        if (index < 0) {
            throw new Wqchat.NegativeIndexException();
        }
        tasks.get(index).markAsDone();

        ui.printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("[X] ");
        System.out.println(tasks.get(index).getDescription());
        ui.printLine();
    }

    public void markTaskAsUndone(int index, int taskCount, ArrayList<Task> tasks, Ui ui) throws Wqchat.InvalidIndexException, Wqchat.NegativeIndexException {
        if (index + 1 > taskCount) {
            throw new Wqchat.InvalidIndexException();
        }
        if (index <= 0) {
            throw new Wqchat.NegativeIndexException();
        }
        tasks.get(index).markAsNotDone();

        ui.printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.print("[ ] ");
        System.out.println(tasks.get(index).getDescription());
        ui.printLine();
    }
}
