import java.io.BufferedReader;
import java.io.FileReader;

public class MapReader {
    public static Map readMapFromFile(String filename) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String dimLine = reader.readLine();
        if (dimLine == null) {
            reader.close();
            throw new Exception("File is empty.");
        }
        MapDimensions dim = MapDimensions.fromString(dimLine);

        Map m = new Map(dim);
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
    }
}
