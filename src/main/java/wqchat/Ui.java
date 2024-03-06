package wqchat;

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
}
