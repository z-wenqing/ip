package wqchat.logic;
import wqchat.task.Task;
import wqchat.task.Event;
import wqchat.Ui;
import wqchat.Storage;
import wqchat.Parser;
import wqchat.TaskList;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Wqchat {
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private final TaskList taskList;

    public Wqchat(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        taskList = new TaskList();
    }

    public static class NoTaskException extends Exception {

    }

    private static final ArrayList<Task> tasks = new ArrayList<>();

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


    public void run() {
        ui.printGreetings();
        storage.loadData(tasks);
        taskCount = tasks.size();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                try {
                    ui.printList(taskCount, tasks);
                } catch (NoTaskException e) {
                    System.out.println("Good job! There is no pending tasks");
                }
            } else if (line.startsWith("mark")) {
                String[] words = line.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                try {
                    taskList.markTaskAsDone(index, taskCount, tasks, ui);
                } catch (InvalidIndexException e) {
                    handleInvalidIndexError();
                } catch (NegativeIndexException e) {
                    System.out.println("Positive number please.");
                }

            } else if (line.startsWith("unmark")) {
                String[] words = line.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                try {
                    taskList.markTaskAsUndone(index, taskCount, tasks, ui);
                } catch (InvalidIndexException e) {
                    handleInvalidIndexError();
                } catch (NegativeIndexException e) {
                    System.out.println("I want a positive number!");
                }

            } else if (line.startsWith("todo")) {
                try {
                    parser.addTodo(line, tasks, taskCount, storage);
                    ui.printAddedTask(tasks, taskCount);
                    taskCount++;
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("You never say what you want to do...");
                }
            } else if (line.startsWith("deadline")) {
                try {
                    parser.addDeadline(line, tasks, taskCount, storage);
                    ui.printAddedTask(tasks, taskCount);
                    taskCount++;
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
                    storage.addTaskInFile(taskCount, tasks);
                    ui.printAddedTask(tasks, taskCount);
                    taskCount++;
                }
            } else if (line.startsWith("delete")) {
                String[] words = line.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                try {
                    storage.deleteTaskInFile(index, taskCount);
                } catch (IOException e) {
                    System.out.println("Something went wrong...");
                }
                taskList.deleteTask(index, ui, tasks, taskCount);
                taskCount--;
            } else {
                System.out.println("Sorry I don't understand :(");
            }
            line = in.nextLine();
        }

        ui.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        ui.printLine();
    }
    public static void main(String[] args) {
        new Wqchat("tasks.txt").run();
    }
}
