import java.util.*;

public class DesignTaskManager {

    class TaskManager {
        class Task implements Comparable<Task> {
            int user;
            int task;
            int prio;

            public Task(int u, int t, int p) {
                user = u;
                task = t;
                prio = p;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Task task1 = (Task) o;
                return
                        task == task1.task &&
                                prio == task1.prio;
            }

            @Override
            public int hashCode() {
                return Objects.hash(task, prio);
            }

            @Override
            public int compareTo(Task o) {
                if (this.prio != o.prio) {
                    return Integer.compare(this.prio, o.prio);
                } else {
                    return Integer.compare(this.task, o.task);
                }
            }
        }

        Map<Integer, Task> tm = new HashMap<>();
        TreeSet<Task> set = new TreeSet<>();

        public TaskManager(List<List<Integer>> tasks) {
            for (List<Integer> ta : tasks) {
                Task t = new Task(ta.get(0), ta.get(1), ta.get(2));
                tm.put(ta.get(1), t);
                set.add(t);
            }

        }

        public void add(int userId, int taskId, int priority) {
            Task t = new Task(userId, taskId, priority);
            tm.put(taskId, t);
            set.add(t);
        }

        public void edit(int taskId, int newPriority) {
            Task exist = tm.get(taskId);
            set.remove(exist);
            exist.prio = newPriority;
            set.add(exist);
        }

        public void rmv(int taskId) {
            Task exist = tm.get(taskId);
            set.remove(exist);
            tm.remove(taskId);
        }

        public int execTop() {
            if (set.isEmpty()) {
                return -1;
            }
            Task last = set.pollLast();
            tm.remove(last.task);
            return last.user;
        }
    }


}
