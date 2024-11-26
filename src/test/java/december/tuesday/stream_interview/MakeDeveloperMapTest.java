package december.tuesday.stream_interview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MakeDeveloperMapTest {

    private static List<MakeDeveloperMap.Developer> developers;
    private static List<MakeDeveloperMap.Assignment> assignments;
    private static List<MakeDeveloperMap.Task> tasks;
    private static final Map<String, List<String>> expected = new HashMap<>();

    @BeforeAll
    static void setUp() {
        /* Initialize developers */
        developers = List.of(
                new MakeDeveloperMap.Developer(1, "John Doe"),
                new MakeDeveloperMap.Developer(2, "Jane Doe"),
                new MakeDeveloperMap.Developer(3, "Jack Doe"),
                new MakeDeveloperMap.Developer(5, "New position")
        );

        /* Initialize assignments */
        assignments = List.of(
                new MakeDeveloperMap.Assignment(1, 1),
                new MakeDeveloperMap.Assignment(2, 1),
                new MakeDeveloperMap.Assignment(3, 1),
                new MakeDeveloperMap.Assignment(4, 2),
                new MakeDeveloperMap.Assignment(5, 2),
                new MakeDeveloperMap.Assignment(6, 2),
                new MakeDeveloperMap.Assignment(7, 2),
                new MakeDeveloperMap.Assignment(8, 3),
                new MakeDeveloperMap.Assignment(9, 3),
                new MakeDeveloperMap.Assignment(10, 3),
                new MakeDeveloperMap.Assignment(13, 1)
        );

        /* Task initialization */
        tasks = List.of(
                new MakeDeveloperMap.Task(1, "One"),
                new MakeDeveloperMap.Task(2, "Two"),
                new MakeDeveloperMap.Task(3, "Three"),
                new MakeDeveloperMap.Task(4, "Four"),
                new MakeDeveloperMap.Task(5, "Five"),
                new MakeDeveloperMap.Task(6, "Six"),
                new MakeDeveloperMap.Task(7, "Seven"),
                new MakeDeveloperMap.Task(8, "Eight"),
                new MakeDeveloperMap.Task(9, "Nine"),
                new MakeDeveloperMap.Task(10, "Ten"),
                new MakeDeveloperMap.Task(11, "Eleven"),
                new MakeDeveloperMap.Task(12, "Twelve")
        );

        // Expected results
        expected.put("John Doe", List.of("One", "Two", "Three"));
        expected.put("Jane Doe", List.of("Four", "Five", "Six", "Seven"));
        expected.put("Jack Doe", List.of("Eight", "Nine", "Ten"));
        expected.put("New position", Collections.emptyList());
    }

    @Test
    void checkReport() {
        var actual = MakeDeveloperMap.report(tasks, developers, assignments);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test
    void checkReportWithStreams() {
        var actual = MakeDeveloperMap.reportWithStreams(tasks, developers, assignments);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test
    void checkReportWithAlterStreams() {
        var actual = MakeDeveloperMap.reportAlterStreams(tasks, developers, assignments);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }
}