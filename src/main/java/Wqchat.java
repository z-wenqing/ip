import java.util.Scanner;
public class Wqchat {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        String[] tasks = new String[100];
        int taskCount = 0;

        printLine();
        System.out.println("Hello! I'm Wqchat");
        System.out.println("What can I do for you?");
        printLine();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (!line.equals("list")) {
                printLine();
                System.out.println("added: " + line);
                printLine();

                tasks[taskCount] = line;
                taskCount++;
            } else {
                printLine();
                for (int i = 0; i < 100; i++) {
                    if (tasks[i] != null) {
                        System.out.print(i + 1);
                        System.out.println(". " + tasks[i]);
                    }
                }
                printLine();

            }

            line = in.nextLine();
        }

        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
