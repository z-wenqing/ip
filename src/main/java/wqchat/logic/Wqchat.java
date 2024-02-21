package wqchat.logic;
import wqchat.task.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Wqchat {
    public static class NoTaskException extends Exception {

    }

    private static ArrayList<Task> tasks = new ArrayList<>();
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
    private static void printList() throws NoTaskException {
        printLine();

        if (taskCount == 0) {
            throw new NoTaskException();
        }
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < taskCount; i++) {
            System.out.print(i + 1);
            System.out.print(".");
            System.out.println(tasks.get(i).toString());
        }
        printLine();
    }

    private static void printAddedTask() {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(taskCount));
        printTaskCount();
        printLine();
        taskCount++;

    }

    private static void markTaskAsDone(int index) throws InvalidIndexException, NegativeIndexException{
        if (index + 1 > taskCount) {
            throw new InvalidIndexException();
        }
        if (index < 0) {
            throw new NegativeIndexException();
        }
        tasks.get(index).markAsDone();

        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("[X] ");
        System.out.println(tasks.get(index).getDescription());
        printLine();
    }

    private static void markTaskAsUndone(int index) throws InvalidIndexException, NegativeIndexException {
        if (index + 1 > taskCount) {
            throw new InvalidIndexException();
        }
        if (index <= 0) {
            throw new NegativeIndexException();
        }
        tasks.get(index).markAsNotDone();

        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.print("[ ] ");
        System.out.println(tasks.get(index).getDescription());
        printLine();
    }

    private static void handleInvalidIndexError() {
        if (taskCount == 1) {
            System.out.println("You only have 1 task!");
        } else {
            System.out.println("You only have " + taskCount + " tasks!");
        }
    }

    //exception classes
    public static class InvalidIndexException extends Exception {

    }
    public static class NegativeIndexException extends Exception {

    }
    public static class MissingDueTimeException extends Exception {

    }
    public static class MissingDescriptionException extends Exception {

    }

    protected static int taskCount = 0;
    private static final int EVENT_DESCRIPTION_INDEX = 6;
    private static final int EVENT_TO_INDEX_INCREMENT = 4;
    private static final int DEADLINE_DESCRIPTION_INDEX = 9;
    private static final int DEADLINE_BY_INDEX_INCREMENT = 4;
    private static final int TODO_DESCRIPTION_INDEX = 5;
    public static void main(String[] args) throws InvalidIndexException, MissingDueTimeException {
        printGreetings();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                try {
                    printList();
                } catch (NoTaskException e) {
                    System.out.println("Good job! There is no pending tasks");
                }
            } else if (line.startsWith("mark")) {
                String[] words = line.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                try {
                    markTaskAsDone(index);
                } catch (InvalidIndexException e) {
                    handleInvalidIndexError();
                } catch (NegativeIndexException e) {
                    System.out.println("Positive number please.");
                }

            } else if (line.startsWith("unmark")) {
                String[] words = line.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                try {
                    markTaskAsUndone(index);
                } catch (InvalidIndexException e) {
                    handleInvalidIndexError();
                } catch (NegativeIndexException e) {
                    System.out.println("I want a positive number!");
                }

            } else if (line.startsWith("todo")) {
                try {
                    addTodo(line);
                    printAddedTask();
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("You never say what you want to do...");
                }
            } else if (line.startsWith("deadline")) {
                try {
                    addDeadline(line);
                    printAddedTask();
                } catch (MissingDueTimeException e) {
                    System.out.println("When is it due?");
                    System.out.println("Tell me more information in the format of: deadline [task] /by [time]");
                } catch (MissingDescriptionException e) {
                    System.out.println("Missing task description.");
                    System.out.println("Tell me more information in the format of: deadline [task] /by [time]");
                }
            } else if (line.startsWith("event")) {
                if (line.contains("/from") && line.contains("/to")) {
                    int indexOfFrom = line.indexOf("/from") + EVENT_DESCRIPTION_INDEX;
                    int indexOfTo = line.indexOf("/to") + EVENT_TO_INDEX_INCREMENT;
                    String description = line.substring(EVENT_DESCRIPTION_INDEX, line.indexOf("/from"));
                    String from = line.substring(indexOfFrom, line.indexOf("/to") - 1); // -1 to exclude the space
                    String to = line.substring(indexOfTo);
                    tasks.add(taskCount, new Event(description, from, to));

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

    private static void addDeadline(String line) throws MissingDueTimeException, MissingDescriptionException {
        int indexOfSlash = line.indexOf("/by");
        if (indexOfSlash == -1) {
            throw new MissingDueTimeException();
        }

        String description = line.substring(DEADLINE_DESCRIPTION_INDEX, indexOfSlash).trim();
        if (description.isEmpty()) {
            throw new MissingDescriptionException();
        }
        int indexOfBy = indexOfSlash + DEADLINE_BY_INDEX_INCREMENT;
        String by = line.substring(indexOfBy).trim();
        tasks.add(taskCount, new Deadline(description, by));
    }

    private static void addTodo(String line) {
        String description = line.substring(TODO_DESCRIPTION_INDEX).trim();
        tasks.add(taskCount, new Todo(description));
    }
}
