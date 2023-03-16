import java.util.*;

class TodoList {

    private class Task {
        int id;
        String desc;
        int due;
        Set<String> tags;

        public Task(int id, String desc, int due, Collection<String> tags) {
            this.id = id;
            this.desc = desc;
            this.due = due;
            this.tags = new HashSet<>(tags);
        }
    }

    private Map<Integer, Task> taskDetails = new HashMap<>();
    private Map<Integer, Set<Integer>> tm = new HashMap<>();
    private int nextTaskId = 1;

    public TodoList() {

    }

    public int addTask(int userId, String taskDescription, int dueDate, List<String> tags) {
        int rt = nextTaskId;
        taskDetails.put(rt, new Task(rt, taskDescription, dueDate, tags));
        tm.computeIfAbsent(userId, k -> new HashSet<>()).add(rt);
        ++nextTaskId;
        return rt;
    }

    public List<String> getAllTasks(int userId) {
        List<Task> res = getSortedIncompleteTask(userId);
        List<String> rres = new ArrayList<>();
        for (int i = 0; i < res.size(); ++i) {
            rres.add(res.get(i).desc);
        }
        return rres;
    }

    protected List<Task> getSortedIncompleteTask(int userId) {
        List<Task> res = new ArrayList<>();
        Set<Integer> cm = tm.getOrDefault(userId, new HashSet<>());
        for (int ck : cm) {
            res.add(taskDetails.get(ck));
        }
        Collections.sort(res, (x, y) -> Integer.compare(x.due, y.due));
        return res;
    }

    public List<String> getTasksForTag(int userId, String tag) {
        List<Task> res = getSortedIncompleteTask(userId);
        List<String> rres = new ArrayList<>();
        for (int i = 0; i < res.size(); ++i) {
            if (res.get(i).tags.contains(tag)) {
                rres.add(res.get(i).desc);
            }
        }
        return rres;
    }

    public void completeTask(int userId, int taskId) {
        Set<Integer> cm = tm.getOrDefault(userId, new HashSet<>());
        if (cm.contains(taskId)) {
            cm.remove(taskId);
            taskDetails.remove(taskId);
            tm.put(userId, cm);
        }
    }
}
