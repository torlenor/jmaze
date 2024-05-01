public class MapDimensions {
    final private int width;
    final private int height;

    public MapDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    static MapDimensions fromString(String dimensions) throws Exception {
        String[] dimensionsArr = dimensions.split("x");
        if (dimensionsArr.length != 2) {
            throw new IllegalArgumentException("Invalid dimensions format." +
                    " Must be <width>x<height> in the first line of the map file.");
        }
        int width = Integer.parseInt(dimensionsArr[0]);
        int height = Integer.parseInt(dimensionsArr[1]);
        return new MapDimensions(width, height);
    }
}
