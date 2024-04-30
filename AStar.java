import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A* algorithm, based on a Go implementation I did a while back for a game.
 * https://github.com/torlenor/asciiventure/blob/master/pathfinding/astar.go
 */
public class AStar {
    public static List<Vec2> determinePath(Graph graph, Vec2 start, Vec2 goal) {
        if (!graph.inDimensions(goal)) {
            return new ArrayList<>();
        }

        PriorityQueue<Item> open = new PriorityQueue<>();
        open.add(new Item(start, 0));

        HashMap<Vec2, Vec2> cameFrom = new HashMap<>();
        cameFrom.put(start, null);
        HashMap<Vec2, Double> costSoFar = new HashMap<>();
        costSoFar.put(start, 0.0);

        Vec2 current;
        while (!open.isEmpty()) {
            current = open.poll().value;

            if (current.equals(goal)) {
                break;
            }

            for (Vec2 next : graph.neighbors(current)) {
                double newCost = costSoFar.get(current) + calcCost(current, next);

                Double c = costSoFar.get(next);
                if (c == null || newCost < c) {
                    costSoFar.put(next, newCost);
                    double priority = newCost + graph.distance(next, goal);
                    open.add(new Item(next, priority));
                    cameFrom.put(next, current);
                }
            }
        }

        if (!cameFrom.containsKey(goal)) {
            return new ArrayList<>();
        }

        current = goal;
        List<Vec2> path = new ArrayList<>();
        while (current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }
        Collections.reverse(path);

        return path;
    }

    private static double calcCost(Vec2 current, Vec2 next) {
        // Diagonal movement is more expensive
        if (current.getX() != next.getX() && current.getY() != next.getY()) {
            return 3.0;
        }
        return 2.0;
    }

    private static class Item implements Comparable<Item> {
        private final Vec2 value;
        private final double priority;

        public Item(Vec2 value, double priority) {
            this.value = value;
            this.priority = priority;
        }

        @Override
        public int compareTo(Item other) {
            return Double.compare(this.priority, other.priority);
        }
    }
}
