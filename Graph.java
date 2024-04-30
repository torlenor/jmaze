import java.util.List;

public interface Graph {
    List<Vec2> neighbors(Vec2 p);

    boolean inDimensions(Vec2 goal);

    double distance(Vec2 next, Vec2 goal);
}
