# Tuesday Stream Task

Check your Stream API skills!<br>
Go
to: [Git_StreamTask](https://github.com/AlexandrLavrinenko/codeus_practices/tree/master/src/main/java/december/tuesday/warmup)
pull the project.<br>
Implement [MakeDeveloperMap.java](MakeDeveloperMap.java) according to
comments.

Check your implementation
with [MakeDeveloperMapTest.java](../../../../../test/java/december/tuesday/stream_interview/MakeDeveloperMapTest.java).

***Words of encouragement:*** <br>
This is a pretty tricky task, so it's not time to relax.
That's what we did our warm-up for.

**Time - 30 min.**

## Important to know:

_All of these classes also have getters/setters._<br>
[Task.java](Task.java):

```
public record Task(int id,String title) {
}
```

[Developer.java](Developer.java):

```
public record Developer(int id,String name) {
}
```

[Assignment.java](Assignment.java):

```
public record Assignment(int taskId,int developerId) {
}
```
