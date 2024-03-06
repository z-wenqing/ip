package wqchat;

import wqchat.logic.Wqchat;
import wqchat.task.Task;

import java.util.ArrayList;

public class Ui {
    public Ui() {
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printTaskCount(int taskCount) {
        if (taskCount == 0) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            int count = taskCount + 1;
            System.out.println("Now you have " + count + " tasks in the list");
        }
    }

    public void printAddedTask(ArrayList<Task> tasks, int taskCount) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(taskCount));
        printTaskCount(taskCount);
        printLine();
    }

    public void printGreetings() {
        printLine();
        System.out.println("Hello! I'm Wqchat");
        System.out.println("What can I do for you?");
        printLine();
    }

    public void printList(int taskCount, ArrayList<Task> tasks) throws Wqchat.NoTaskException {
        printLine();

        if (taskCount == 0) {
            throw new Wqchat.NoTaskException();
        }
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < taskCount; i++) {
            System.out.print(i + 1);
            System.out.print(".");
            System.out.println(tasks.get(i).toString());
        }
        printLine();
    }
}
