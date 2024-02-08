import java.util.Scanner;
import java.util.ArrayList;
public class Wqchat {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printTaskCount() {
        if (taskCount == 0) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            int count = taskCount + 1;
            System.out.println("Now you have " + count + " tasks in the list");
        }
    }
    protected static int taskCount = 0;
    public static void main(String[] args) {
        /*ArrayList<Task> tasks = new ArrayList<>(); // a list of tasks*/
        Task[] tasks = new Task[100];


        printLine();
        System.out.println("Hello! I'm Wqchat");
        System.out.println("What can I do for you?");
        printLine();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printLine();
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < taskCount; i++) {
                    System.out.print(i + 1);
                    System.out.print(".");
                    System.out.println(tasks[i].toString());
                }
                printLine();
            } else if (line.startsWith("mark")) {
                String[] words = line.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                tasks[index].markAsDone();

                printLine();
                System.out.println("Nice! I've marked this task as done:");
                System.out.print("[X] ");
                System.out.println(tasks[index].getDescription());
                printLine();
            } else if (line.startsWith("unmark")) {
                String[] words = line.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                tasks[index].markAsNotDone();

                printLine();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.print("[ ] ");
                System.out.println(tasks[index].getDescription());
                printLine();
            } else if (line.startsWith("todo")) {
                String description = line.substring(5);
                tasks[taskCount] = new Todo(description);

                printLine();
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount]);
                printTaskCount();
                printLine();
                taskCount++;
            } else if (line.startsWith("deadline")) {
                if (line.contains("/by")) {
                    int indexOfSlash = line.indexOf("/");
                    int indexOfBy = indexOfSlash + 4;
                    String description = line.substring(9, indexOfSlash);
                    String by = line.substring(indexOfBy);
                    tasks[taskCount] = new Deadline(description, by);

                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount]);
                    printTaskCount();
                    printLine();
                    taskCount++;
                } else {
                    System.out.println("I'm sorry I can only understand if you talk to me in the following way: ");
                    System.out.println("deadline [task] /by [time]");
                }
            } else if (line.startsWith("event")) {
                if (line.contains("/from") && line.contains("/to")) {
                    int indexOfFrom = line.indexOf("/from") + 6;
                    int indexOfTo = line.indexOf("/to") + 4;
                    String description = line.substring(6, line.indexOf("/from"));
                    String from = line.substring(indexOfFrom, line.indexOf("/to") - 1);
                    String to = line.substring(indexOfTo);
                    tasks[taskCount] = new Event(description, from, to);

                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount]);
                    printTaskCount();
                    printLine();
                    taskCount++;
                }
            }
            line = in.nextLine();
        }

        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
