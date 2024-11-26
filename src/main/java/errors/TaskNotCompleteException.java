package errors;

public class TaskNotCompleteException extends RuntimeException {

    public TaskNotCompleteException() {
        super("Task not complete.");
    }
}
