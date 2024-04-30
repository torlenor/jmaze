import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map implements Graph {
    final private int width;
    final private int height;

    private HashMap<Vec2, Boolean> map = new HashMap<>();

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWall(Vec2 p) {
        map.put(p, true);
    }

    public void removeWall(Vec2 p) {
        map.remove(p);
    }

    private boolean isEmpty(Vec2 p) {
        if (!inDimensions(p)) {
            // Everything out of bounds is wall
            return false;
        }
        return !map.getOrDefault(p, false);
    }

    /**
     * List of neighbors for a given position if they are not walls and
     * within bounds.
     * 
     * We don't allow diagonal movement on our map.
     */
    @Override
    public List<Vec2> neighbors(Vec2 p) {
        List<Vec2> neighbors = new ArrayList<>();
        Vec2[] directions = { new Vec2(0, 1), new Vec2(0, -1), new Vec2(1, 0), new Vec2(-1, 0) };
        for (Vec2 dir : directions) {
            Vec2 next = p.add(dir);
            if (isEmpty(next)) {
                neighbors.add(next);
            }
        }

        return neighbors;
    }

    @Override
    public boolean inDimensions(Vec2 p) {
        return p.getX() >= 0 && p.getX() < width && p.getY() >= 0 && p.getY() < height;
    }

    /**
     * Euclidean distance between two positions on the map.
     */
    @Override
    public double distance(Vec2 start, Vec2 end) {
        return start.distance(end);
    }

    private String getTile(Vec2 p) {
        return map.getOrDefault(p, false) ? "X" : " ";
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Vec2 p = new Vec2(x, y);
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                    sb.append("#"); // Border
                } else {
                    sb.append(getTile(p));
                }
            }
            if (y < height - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
