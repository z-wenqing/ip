package wqchat;

public class WqchatException {
    public static class InvalidIndexException extends Exception {

    }
    public static class NegativeIndexException extends Exception {

    }
    public static class MissingDueTimeException extends Exception {

    }
    public static class MissingDescriptionException extends Exception {

    }

    public static class NoTaskException extends Exception {

    }

    public static void handleInvalidIndexError(int taskCount) {
        if (taskCount == 1) {
            System.out.println("You only have 1 task!");
        } else {
            System.out.println("You only have " + taskCount + " tasks!");
        }
    }
}
