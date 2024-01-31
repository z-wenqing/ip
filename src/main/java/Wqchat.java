import java.util.Scanner;
public class Wqchat {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        //System.out.println("____________________________________________________________");
        printLine();
        System.out.println("Hello! I'm wqchat");
        System.out.println("What can I do for you?");
        //System.out.println("____________________________________________________________");
        printLine();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        if (line.equals("bye")) {
            printLine();
            System.out.println("Bye. Hope to see you again soon!");
            printLine();
        } else {
            printLine();
            System.out.println(line);
            printLine();
        }



    }
}
