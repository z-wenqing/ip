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

    public void printNegativeIndexException() {
        System.out.println("I want a positive number! :(");
    }

    public void printMissingDueTimeException(ArrayList<Task> tasks, int taskCount) {
        String type = tasks.get(taskCount - 1).getType();

        switch (type) {
        case "D":
            System.out.println("When is it due?");
            System.out.println("Tell me more information in the format of: deadline [task] /by [time]");
            break;
        case "E":
            System.out.println("The time of the event is not complete");
            System.out.println("Tell me more information in the format of: event [task] /from [time] /to [time]");
        }
    }

    public void printMissingDescriptionException(ArrayList<Task> tasks, int taskCount) {
        System.out.println("Missing task description.");
        String type = tasks.get(taskCount - 1).getType();

        switch (type) {
        case "T":
            System.out.println("Tell me more information in the format of: todo [task]");
            break;
        case "D":
            System.out.println("Tell me more information in the format of: deadline [task] /by [time]");
            break;
        case "E":
            System.out.println("Tell me more information in the format of: event [task] /from [time] /to [time]");
        }





    }
}
