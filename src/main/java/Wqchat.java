import java.util.Scanner;
import java.util.ArrayList;
public class Wqchat {
    private static Task[] tasks = new Task[100];
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printTaskCount() {
        if (taskCount == 0) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            int count = taskCount + 1;
            System.out.println("Now you have " + count + " tasks in the list");
        }
    }
    private static void printGreetings() {
        printLine();
        System.out.println("Hello! I'm Wqchat");
        System.out.println("What can I do for you?");
        printLine();
    }
    private static void printList() {
        printLine();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < taskCount; i++) {
            System.out.print(i + 1);
            System.out.print(".");
            System.out.println(tasks[i].toString());
        }
        printLine();
    }

    private static void printAddedTask() {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount]);
        printTaskCount();
        printLine();
        taskCount++;

    }

    protected static int taskCount = 0;
    private static final int EVENT_DESCRIPTION_INDEX = 6;
    private static final int EVENT_TO_INDEX_INCREMENT = 4;
    private static final int DEADLINE_DESCRIPTION_INDEX = 9;
    private static final int DEADLINE_BY_INDEX_INCREMENT = 4;
    private static final int TODO_DESCRIPTION_INDEX = 5;
    public static void main(String[] args) {
        /*ArrayList<Task> tasks = new ArrayList<>(); // a list of tasks*/


        printGreetings();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printList();
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
                try {
                    String description = line.substring(TODO_DESCRIPTION_INDEX).trim();
                    tasks[taskCount] = new Todo(description);
                    printAddedTask();
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("You never say what you want to do...");
                }
            } else if (line.startsWith("deadline")) {
                if (line.contains("/by")) {
                    int indexOfSlash = line.indexOf("/");
                    int indexOfBy = indexOfSlash + DEADLINE_BY_INDEX_INCREMENT;
                    String description = line.substring(DEADLINE_DESCRIPTION_INDEX, indexOfSlash);
                    String by = line.substring(indexOfBy);
                    tasks[taskCount] = new Deadline(description, by);

                    printAddedTask();
                } else {
                    System.out.println("Invalid command. If you want to add a deadline, you can type: ");
                    System.out.println("deadline [task] /by [time]");
                }
            } else if (line.startsWith("event")) {
                if (line.contains("/from") && line.contains("/to")) {
                    int indexOfFrom = line.indexOf("/from") + EVENT_DESCRIPTION_INDEX;
                    int indexOfTo = line.indexOf("/to") + EVENT_TO_INDEX_INCREMENT;
                    String description = line.substring(EVENT_DESCRIPTION_INDEX, line.indexOf("/from"));
                    String from = line.substring(indexOfFrom, line.indexOf("/to") - 1); // -1 to exclude the space
                    String to = line.substring(indexOfTo);
                    tasks[taskCount] = new Event(description, from, to);

                    printAddedTask();
                }
            } else {
                System.out.println("Sorry I don't understand :(");
            }
            line = in.nextLine();
        }

        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
