# Tuesday Stream Task

Check your Stream API skills!<br>
Go
to: [Git_StreamTask](https://github.com/AlexandrLavrinenko/codeus_practices/tree/master/src/main/java/december/tuesday/warmup)
pull the project.<br>
Implement [MakeDeveloperMap](src/main/java/december/tuesday/stream_interview/MakeDeveloperMap.java) according to
comments.

Check your implementation
with [MakeDeveloperMapTest](src/test/java/december/tuesday/stream_interview/MakeDeveloperMapTest.java).

***Words of encouragement:*** <br>
This is a pretty tricky task, so it's not time to relax.
That's what we did our warm-up for.

**Time - 30 min.**

## Important to know:

_All of this classes also has getters/setters._<br>
[Task](src/main/java/december/tuesday/stream_interview/MakeDeveloperMap#Task.java)

```
public static class Task {
        int id;
        String title;

        public Task(int id, String title) {
            this.id = id;
            this.title = title;
        }
    }
```

[Developer](src/main/java/december/tuesday/stream_interview/MakeDeveloperMap#Developer.java)

```
    public static class Developer {
        int id;
        String name;

        public Developer(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
```

[Assignment](src/main/java/december/tuesday/stream_interview/MakeDeveloperMap#Assignment.java)

```
    public static class Assignment {
        int taskId;
        int developerId;

        public Assignment(int taskId, int developerId) {
            this.taskId = taskId;
            this.developerId = developerId;
        }
    }
```
