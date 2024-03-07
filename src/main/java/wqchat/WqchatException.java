package wqchat;

/**
 * Represents exceptions that are thrown in Wqchat.
 */
public class WqchatException {
    public static class InvalidIndexException extends Exception {

    }
    public static class NegativeIndexException extends Exception {

    }
    public static class MissingInformationException extends Exception {

    }
    public static class NoTaskException extends Exception {

    }

    /**
     * Prints error messages when a index that is out of bound is inputted.
     *
     * @param taskCount number of tasks added.
     */
    public static void handleInvalidIndexError(int taskCount) {
        if (taskCount == 1) {
            System.out.println("You only have 1 task!");
        } else {
            System.out.println("You only have " + taskCount + " tasks!");
        }
    }
}
