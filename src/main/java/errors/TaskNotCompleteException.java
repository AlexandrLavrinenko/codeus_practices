package errors;

public class TaskNotCompleteException extends Throwable {
    public TaskNotCompleteException(String message) {
        super(message);
    }
}
