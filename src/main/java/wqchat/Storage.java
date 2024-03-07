package wqchat;

import wqchat.task.Deadline;
import wqchat.task.Event;
import wqchat.task.Task;
import wqchat.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a storage manager that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    public static String filePath;
    private static final int TYPE_INDEX_IN_FILE = 0;
    private static final int IS_DONE_INDEX_IN_FILE = 1;
    private static final int DESCRIPTION_INDEX_IN_FILE = 2;
    private static final int TIME_INDEX_IN_FILE = 3;
    private static final int DEADLINE_BY_INDEX_INCREMENT = 3;

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Loads data from the data file and add tasks in the tasks ArrayList
     *
     * @param tasks a list of tasks added.
     */
    public void loadData(ArrayList<Task> tasks) {
        File f = new File(filePath);
        try {
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String taskInFile = s.nextLine();
                    tasks.add(extractTaskInfo(taskInFile));
                }
            }

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Processes text in data file and extracts task information including description, from, to and by
     *
     * @param line user input.
     * @return a task object represented by the current line in the data file
     */
    public Task extractTaskInfo(String line) {
        String[] words = line.split("\\|");
        String type = words[TYPE_INDEX_IN_FILE].trim();
        boolean isDone = words[IS_DONE_INDEX_IN_FILE].trim().equals("1");
        String description = words[DESCRIPTION_INDEX_IN_FILE].trim();
        String time = "";

        if (type.equals("D") || type.equals("E")) {
            time = words[TIME_INDEX_IN_FILE].trim();
        }
        Task t = null;

        switch (type) {
        case "T": {
            t = new Todo(description, isDone);
            break;
        }
        case "D": {
            String by = time.substring(DEADLINE_BY_INDEX_INCREMENT).trim();
            t = new Deadline(description, by, isDone);
            break;
        }
        case "E": {
            int indexOfTo = time.indexOf("-");
            String from = time.substring(0, indexOfTo).trim();
            String to = time.substring(indexOfTo + 1).trim();
            t = new Event(description, from, to, isDone);
            break;
        }
        }
        return t;
    }

    /**
     * Writes the text to data file
     *
     * @param filePath a relative path giving the location of the data file, relative to the current working directory.
     * @param textToAdd text to write to the file.
     * @param isAppend whether to append the text or overwrite the whole file.
     * @throws IOException If there is something wrong.
     */
    public static void writeToFile(String filePath, String textToAdd, boolean isAppend) throws IOException{
        FileWriter fw = new FileWriter(filePath, isAppend);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Gets the format of the todo task in the file
     *
     * @param tasks a list of tasks added.
     * @param index index of the task to be formatted.
     * @return a string that represent the task
     */
    public static String getTodo(ArrayList<Task> tasks, int index) {
        return tasks.get(index).getType() + " | " + tasks.get(index).getIsDone() + " | " + tasks.get(index).getDescription();
    }

    /**
     * Gets the format of the deadline task in the file
     *
     * @param tasks a list of tasks added.
     * @param index index of the task to be formatted.
     * @return a string that represent the task
     */

    public static String getDeadline(ArrayList<Task> tasks, int index) {
        Deadline d = (Deadline) tasks.get(index);
        return tasks.get(index).getType() + " | " + tasks.get(index).getIsDone() + " | " + tasks.get(index).getDescription()
                + " | " + "by " + d.getBy();
    }

    /**
     * Gets the format of the event task in the file
     *
     * @param tasks a list of tasks added.
     * @param index index of the task to be formatted.
     * @return a string that represent the task
     */
    public static String getEvent(ArrayList<Task> tasks, int index) {
        Event e = (Event) tasks.get(index);
        return tasks.get(index).getType() + " | " + tasks.get(index).getIsDone() + " | " + tasks.get(index).getDescription()
                + " | " + e.getFrom() + " - " + e.getTo();
    }

    /**
     * Writes the task in the file
     *
     * @param index index of the task to be added.
     * @param tasks a list of tasks added.
     */
    public void addTaskInFile(int index, ArrayList<Task> tasks) {
        try {
            String type = tasks.get(index).getType();
            switch (type) {
            case "T": {
                writeToFile(filePath, getTodo(tasks, index) + System.lineSeparator(), true);
                break;
            }
            case "D": {
                writeToFile(filePath, getDeadline(tasks, index) + System.lineSeparator(), true);
                break;
            }
            case "E": {

                writeToFile(filePath, getEvent(tasks, index) + System.lineSeparator(), true);
                break;
            }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong...");
        }
    }

    /**
     * Deletes a task in the data file.
     *
     * @param index index of the task to be deleted in the file
     * @param taskCount number of tasks added.
     * @throws IOException If there is something wrong.
     */
    public void deleteTaskInFile(int index, int taskCount) throws IOException, WqchatException.InvalidIndexException, WqchatException.NegativeIndexException {
        if (index + 1 > taskCount) {
            throw new WqchatException.InvalidIndexException();
        }
        if (index < 0) {
            throw new WqchatException.NegativeIndexException();
        }
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        List<String> lines = Files.readAllLines(Path.of(filePath));
        lines.remove(index);
        for (int i = 0; i < taskCount - 1; i++) {
            writeToFile(filePath, lines.get(i) + System.lineSeparator(), i != 0);
        }
    }

    /**
     *  Updates the status of the task after marking / unmarking a task
     *
     * @param index index of the task to be updated.
     * @param tasks a list of tasks added.
     * @param taskCount number of tasks added.
     * @throws IOException If there is something wrong.
     */
    public static void updateTaskStatusInFile(int index, ArrayList<Task> tasks, int taskCount) throws IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        List<String> lines = Files.readAllLines(Path.of(filePath));

        String type = tasks.get(index).getType();
        String newText = "";
        switch (type) {
        case "T":
            newText = getTodo(tasks, index);
            break;
        case "D":
            newText = getDeadline(tasks, index);
            break;
        case "E":
            newText = getEvent(tasks, index);
            break;
        }
        lines.set(index, newText);

        for (int i = 0; i < taskCount; i++) {
            writeToFile(filePath, lines.get(i) + System.lineSeparator(), i != 0);
        }
    }
}
