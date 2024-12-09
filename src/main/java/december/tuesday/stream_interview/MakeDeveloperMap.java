package december.tuesday.stream_interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Return Map where:
 * Key is Developer Name
 * Value is List of Task titles assigned to Developer
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
     * - Value is List of Task Title assigned to Developer Handle.
     */
    public static Map<String, List<String>> report(
            List<Task> tasks,
            List<Developer> developers,
            List<Assignment> assignments) {
        var reportMap = new HashMap<String, List<String>>();

        for (Developer developer : developers) {
            var assigments = assignments.stream()
                    .filter(as -> developer.id() == as.developerId())
                    .map(Assignment::taskId)
                    .toList();

            var tasksList = tasks.stream()
                    .filter(tsk -> assigments.contains(tsk.id()))
                    .map(Task::title)
                    .toList();

            reportMap.put(developer.name(), tasksList);
        }

        return reportMap;
    }

    /**
     * Method with Stream API for return Developer's Map.
     *
     * @param tasks       list of {@link Task}.
     * @param developers  list of {@link Developer}.
     * @param assignments list of {@link Assignment}
     * @return Map where:
     * - Key is Developer Name
     * - Value is List of Task Title assigned to Developer Handle.
     */
    public static Map<String, List<String>> reportWithStreams(
            List<Task> tasks,
            List<Developer> developers,
            List<Assignment> assignments) {

        Map<Integer, String> taskMap = tasks.stream()
                .collect(toMap(Task::id, Task::title));

        Map<Integer, String> developerMap = developers.stream()
                .collect(toMap(Developer::id, Developer::name));

        Map<String, List<String>> report = developers.stream()
                .collect(toMap(Developer::name, dev -> new ArrayList<>()));

        assignments.stream()
                .filter(assignment -> taskMap.containsKey(assignment.taskId()))
                .forEach(assignment -> {
                    String developerName = developerMap.get(assignment.developerId());
                    if (developerName != null) {
                        report.get(developerName).add(taskMap.get(assignment.taskId()));
                    }
                });

        return report;
    }

    public static Map<String, List<String>> reportAlterStreams(
            List<Task> tasks,
            List<Developer> developers,
            List<Assignment> assignments) {

        // Creating a task map for quick access to task titles by their identifiers
        Map<Integer, String> taskMap = tasks.stream()
                .collect(toMap(Task::id, Task::title));

        return developers.stream()
                .collect(toMap(
                        Developer::name,
                        dev -> assignments.stream()
                                .filter(a -> a.developerId() == dev.id())
                                .filter(a -> taskMap.containsKey(a.taskId()))
                                .map(a -> taskMap.get(a.taskId()))
                                .toList(),
                        // в разі конфлікту залишаємо наявне значення
                        (existing, replacement) -> existing
                ));
    }

    // A method to print a map to the console
    private void printMap(Map<String, List<String>> map) {
        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }

}
