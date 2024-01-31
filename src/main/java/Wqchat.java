import java.util.Scanner;
public class Wqchat {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Wqchat");
        System.out.println("What can I do for you?");
        printLine();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            printLine();
            System.out.println(line);
            printLine();
            line = in.nextLine();
        }
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
