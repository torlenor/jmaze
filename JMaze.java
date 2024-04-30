import java.io.BufferedReader;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;

public class JMaze {
    public static void main(String[] args) {
        Map m;
        Vec2 start, goal;
        if (args.length < 5) {
            System.out.println("Note:" +
                    " Use java JMaze <mapfile> <start_x> <start_y> <goal_x> <goal_y> to load a map from file." +
                    " Using demo map.\n");
            m = generateDemoMap();
            start = new Vec2(1, 1);
            goal = new Vec2(8, 8);
        } else {
            try {
                m = readMapFromFile(args[0]);
            } catch (Exception e) {
                System.out.println("Could not read map from file: " + e.getMessage());
                return;
            }
            start = new Vec2(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            goal = new Vec2(Integer.parseInt(args[3]), Integer.parseInt(args[4]));
        }

        printInitConditions(m, start, goal);

        printBFSPath(m, start, goal);
        printAStarPath(m, start, goal);
    }

    private static void printInitConditions(Map m, Vec2 start, Vec2 goal) {
        System.out.println("Map (" + m.getWidth() + "x" + m.getHeight() + "):");
        System.out.println(m);
        System.out.println("\n-----------------\n");

        System.out.println("Start: " + start);
        System.out.println("Goal: " + goal);
        System.out.println("\n-----------------\n");
    }

    private static void printAStarPath(Map m, Vec2 start, Vec2 goal) {
        List<Vec2> astar_path = AStar.determinePath(m, start, goal);
        System.out.println("Path from AStar (length: " + astar_path.size() + "):");
        System.out.println(astar_path);
    }

    private static void printBFSPath(Map m, Vec2 start, Vec2 goal) {
        List<Vec2> bfs_path = BFS.determinePath(m, start, goal);
        System.out.println("Path from BFS (length: " + bfs_path.size() + "):");
        System.out.println(bfs_path);
    }

    private static Map generateDemoMap() {
        int width = 24;
        int height = 16;
        Map m = new Map(width, height);

        // Add a wall to the map
        for (int i = 0; i < height; i++) {
            for (int n = -1; n <= 1; n++) {
                m.setWall(new Vec2(width / 2 + n, i));
            }
        }
        // ... with a hole in it
        m.removeWall(new Vec2(width / 2 - 1, 5));
        m.removeWall(new Vec2(width / 2, 5));
        m.removeWall(new Vec2(width / 2, 6));
        m.removeWall(new Vec2(width / 2 + 1, 6));

        return m;
    }

    private static Map readMapFromFile(String filename) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String dimensions = reader.readLine();
            if (dimensions == null) {
                reader.close();
                throw new Exception("File is empty.");
            }
            String[] dimensionsArr = dimensions.split("x");
            int width = Integer.parseInt(dimensionsArr[0]);
            int height = Integer.parseInt(dimensionsArr[1]);

            Map m = new Map(width, height);
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    if (line.charAt(col) == 'x') {
                        m.setWall(new Vec2(col, row));
                    }
                }
                row++;
            }

            reader.close();

            return m;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
