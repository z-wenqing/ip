import java.util.Scanner;
import java.util.ArrayList;
public class Wqchat {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

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
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.print(i + 1);
                    System.out.print(task.isDone() ? ".[X] " : ".[ ] ");
                    System.out.println(task.getDescription());
                }
                printLine();
            } else if (line.startsWith("mark")) {
                String[] words = line.split(" ");
                int index = Integer.parseInt(words[1]);
                Task task = tasks.get(index - 1);
                task.markAsDone();

                printLine();
                System.out.println("Nice! I've marked this task as done:");
                System.out.print("[X] ");
                System.out.println(task.getDescription());
                printLine();
            } else if (line.startsWith("unmark")) {
                String[] words = line.split(" ");
                int index = Integer.parseInt(words[1]);
                Task task = tasks.get(index - 1);
                task.markAsNotDone();

                printLine();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.print("[ ] ");
                System.out.println(task.getDescription());
                printLine();
            } else {
                printLine();
                System.out.println("added: " + line);
                printLine();

                Task newTask = new Task(line);
                tasks.add(newTask);
            }
            line = in.nextLine();
        }

        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
