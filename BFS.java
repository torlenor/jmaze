import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    static List<Vec2> determinePath(Graph graph, Vec2 start, Vec2 goal) {
        Queue<Vec2> frontier = new LinkedList<>();
        frontier.add(start);
        HashMap<Vec2, Vec2> cameFrom = new HashMap<>();
        cameFrom.put(start, null);

        while (!frontier.isEmpty()) {
            Vec2 current = frontier.poll();

            if (current.equals(goal)) {
                break;
            }

            for (Vec2 next : graph.neighbors(current)) {
                if (!cameFrom.containsKey(next)) {
                    frontier.add(next);
                    cameFrom.put(next, current);
                }
            }
        }

        if (!cameFrom.containsKey(goal)) {
            return new ArrayList<>();
        }

        Vec2 current = goal;
        List<Vec2> path = new ArrayList<>();
        while (current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }
        Collections.reverse(path);

        return path;
    }
}
