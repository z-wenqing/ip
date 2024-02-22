package wqchat.logic;
import wqchat.task.Task;
import wqchat.task.Deadline;
import wqchat.task.Todo;
import wqchat.task.Event;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;

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

    private static void deleteTask(int index) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index));
        taskCount--;
        if (taskCount == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        }
        tasks.remove(index);
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

    private static void loadData() {
        taskCount = 0;
        File f = new File("tasks.txt");
        try {
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String taskInFile = s.nextLine();
                    tasks.add(taskCount, extractTaskInfo(taskInFile));
                    taskCount++;
                }
            }

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static Task extractTaskInfo(String line) {
        String[] words = line.split("\\|");
        String type = words[TYPE_INDEX_IN_FILE].trim();
        boolean isDone = words[IS_DONE_INDEX_IN_FILE].trim().equals("1");
        String description = words[DESCRIPTION_INDEX_IN_FILE].trim();
        String time = "";

        if (type.equals("D") || type.equals("E")) {
            time = words[TIME_INDEX_IN_FILE];
        }
        Task t = null;

        switch (type) {
        case "T": {
            t = new Todo(description, isDone);
            break;
        }
        case "D": {
            t = new Deadline(description, time);
            break;
        }
        case "E": {
            int indexOfTo = time.indexOf("-");
            String from = time.substring(0, indexOfTo).trim();
            String to = time.substring(indexOfTo + 1).trim();
            t = new Event(description, from, to);
            break;
        }
        }
        return t;
    }

    private static void writeToFile(String filePath, String textToAdd, boolean isAppend) throws IOException{
        FileWriter fw = new FileWriter(filePath, isAppend);
        fw.write(textToAdd);
        fw.close();
    }

    private static void addTaskInFile(int index) {
        try {
            String type = tasks.get(index).getType();
            switch (type) {
            case "T": {
                writeToFile("tasks.txt", tasks.get(index).getType() + " | " + tasks.get(index).getIsDone() + " | " + tasks.get(index).getDescription() + System.lineSeparator(), true);
                break;
            }
            case "D": {
                Deadline d = (Deadline) tasks.get(index);
                writeToFile("tasks.txt", tasks.get(index).getType() + " | " + tasks.get(index).getIsDone() + " | " + tasks.get(index).getDescription()
                        + " | " + "by " + d.getBy() + System.lineSeparator(), true);
                break;
            }
            case "E": {
                Event e = (Event) tasks.get(index);
                writeToFile("tasks.txt", tasks.get(index).getType() + " | " + tasks.get(index).getIsDone() + " | " + tasks.get(index).getDescription()
                        + " | " + e.getFrom() + " - " + e.getTo() + System.lineSeparator(), true);
                break;
            }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong...");
        }
    }

    private static void deleteTaskInFile(int index) throws IOException {
        File f = new File("tasks.txt");
        Scanner s = new Scanner(f);

        List<String> lines = Files.readAllLines(Path.of("tasks.txt"));
        lines.remove(index);
        for (int i = 0; i < taskCount - 1; i++) {
            writeToFile("tasks.txt", lines.get(i) + System.lineSeparator(), i != 0);
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
    private static final int TYPE_INDEX_IN_FILE = 0;
    private static final int IS_DONE_INDEX_IN_FILE = 1;
    private static final int DESCRIPTION_INDEX_IN_FILE = 2;
    private static final int TIME_INDEX_IN_FILE = 3;
    public static void main(String[] args) throws InvalidIndexException, MissingDueTimeException {
        printGreetings();
        loadData();

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
                    addTaskInFile(taskCount);
                    printAddedTask();
                }
            } else if (line.startsWith("delete")) {
                String[] words = line.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                try {
                    deleteTaskInFile(index);
                } catch (IOException e) {
                    System.out.println("Something went wrong...");
                }
                deleteTask(index);
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
        addTaskInFile(taskCount);
    }

    private static void addTodo(String line) {
        String description = line.substring(TODO_DESCRIPTION_INDEX).trim();
        tasks.add(taskCount, new Todo(description, false));
        addTaskInFile(taskCount);
    }
}
