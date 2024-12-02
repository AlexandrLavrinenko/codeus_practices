package december.tuesday.stream_interview;

import errors.TaskNotCompleteException;

import java.util.List;
import java.util.Map;

/**
 * The task from real interview on Middle Java Developer position.
 * Return Map where:
 * Key is Developer Name
 * Value is List of Tasks Title assigned to Developer
 *
 * Handle non-consistent cases:
 * 1. Developer::getId not present in Assignment::getDeveloperId - add Developer::getName with Collections::emptyList
 * 2. Assignment::getTaskId not present in Task::getId - Skip Assignment
 */
public class MakeDeveloperMap {

    /**
     * Not Stream API method for return Developer's Map.
     *
     * @param tasks       list of {@link Task}.
     * @param developers  list of {@link Developer}.
     * @param assignments list of {@link Assignment}
     * @return Map where:
     * - Key is Developer Name
     * - Value is List of Tasks Title assigned to Developer Handle.
     */
    public static Map<String, List<String>> report(
            List<Task> tasks,
            List<Developer> developers,
            List<Assignment> assignments) {
        throw new TaskNotCompleteException();
    }

    /**
     * Method with Stream API for return Developer's Map.
     * @param tasks list of {@link Task}.
     * @param developers list of {@link Developer}.
     * @param assignments list of {@link Assignment}
     * @return Map where:
     * - Key is Developer Name
     * - Value is List of Tasks Title assigned to Developer Handle.
     */
    public static Map<String, List<String>> reportWithStreams(
            List<Task> tasks,
            List<Developer> developers,
            List<Assignment> assignments) {

        throw new TaskNotCompleteException();
    }

    // A method to print a map to the console.
    // Maybe useful for testing and debugging.
    private void printMap(Map<String, List<String>> map) {
        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    public static class Task {
        int id;
        String title;

        public Task(int id, String title) {
            this.id = id;
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class Developer {
        int id;
        String name;

        public Developer(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Assignment {
        int taskId;
        int developerId;

        public Assignment(int taskId, int developerId) {
            this.taskId = taskId;
            this.developerId = developerId;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public int getDeveloperId() {
            return developerId;
        }

        public void setDeveloperId(int developerId) {
            this.developerId = developerId;
        }
    }
}
