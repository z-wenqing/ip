package wqchat.logic;

import wqchat.task.Task;
import wqchat.Ui;
import wqchat.Storage;
import wqchat.Parser;
import wqchat.TaskList;
import wqchat.WqchatException;

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
    private static final ArrayList<Task> tasks = new ArrayList<>();
    protected static int taskCount = 0;

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
                } catch (WqchatException.NoTaskException e) {
                    System.out.println("Good job! There is no pending tasks");
                }
            } else if (line.startsWith("mark")) {
                String[] words = line.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                try {
                    taskList.markTaskAsDone(index, taskCount, tasks, ui);
                } catch (WqchatException.InvalidIndexException e) {
                    WqchatException.handleInvalidIndexError(taskCount);
                } catch (WqchatException.NegativeIndexException e) {
                    ui.printNegativeIndexException();
                }

            } else if (line.startsWith("unmark")) {
                String[] words = line.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                try {
                    taskList.markTaskAsUndone(index, taskCount, tasks, ui);
                } catch (WqchatException.InvalidIndexException e) {
                    WqchatException.handleInvalidIndexError(taskCount);
                } catch (WqchatException.NegativeIndexException e) {
                    ui.printNegativeIndexException();
                }

            } else if (line.startsWith("todo")) {
                try {
                    parser.addTodo(line, tasks, taskCount);
                    storage.addTaskInFile(taskCount, tasks);
                    ui.printAddedTask(tasks, taskCount);
                    taskCount++;
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("You never say what you want to do...");
                }
            } else if (line.startsWith("deadline")) {
                try {
                    parser.addDeadline(line, tasks, taskCount);
                    storage.addTaskInFile(taskCount, tasks);
                    ui.printAddedTask(tasks, taskCount);
                    taskCount++;
                } catch (WqchatException.MissingDueTimeException e) {
                    ui.printMissingDueTimeException(tasks, taskCount);
                } catch (WqchatException.MissingDescriptionException e) {
                    ui.printMissingDescriptionException(tasks, taskCount);
                }
            } else if (line.startsWith("event")) {
                try {
                    parser.addEvent(line, tasks, taskCount);
                    storage.addTaskInFile(taskCount, tasks);
                    ui.printAddedTask(tasks, taskCount);
                    taskCount++;
                } catch (WqchatException.MissingDueTimeException e) {
                    ui.printMissingDueTimeException(tasks, taskCount);
                } catch (WqchatException.MissingDescriptionException e) {
                    ui.printMissingDescriptionException(tasks, taskCount);
                }
            } else if (line.startsWith("delete")) {
                String[] words = line.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                try {
                    storage.deleteTaskInFile(index, taskCount);
                } catch (IOException e) {
                    System.out.println("Something went wrong...");
                }
                ui.printLine();
                taskList.deleteTask(index, tasks, taskCount);
                ui.printLine();
                taskCount--;
            } else if (line.startsWith("find")) {
                ui.printLine();
                taskList.findTask(tasks, line);
                ui.printLine();
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
