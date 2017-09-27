public class VerticalPixelGraph {
    private int width;
    private int height;

    public VerticalPixelGraph(int w, int h) {
        width = w;
        height = h;
    }

    public int verticesForX(int x, int y, int vertexCount) {
        if (y == 0)
            return vertexCount;
        vertexCount++;
        for (int i = 0; i < y; i++) {
            vertexCount = vertexCount + verticesForX(x, y - 1, vertexCount);
            if (x - 1 >= 0 && x - 1 < width) {
//                vertexCount++;
                vertexCount = vertexCount + verticesForX(x - 1, y - 1, vertexCount);
            }
            if (x + 1 >= 0 && x + 1 < width) {
//                vertexCount++;
                vertexCount = vertexCount + verticesForX(x + 1, y - 1, vertexCount);
            }
        }
        return vertexCount;
    }

    // public int verticesForX(int x, int y) {
    // for (int i = 0; i < y; i++) {
    // if (y + 1 == height)
    // return vertexCount;
    // vertexCount++;
    // if (x - 1 >= 0 && x - 1 < width)
    // vertexCount++;
    // if (x + 1 >= 0 && x + 1 < width)
    // vertexCount++;
    // }
    // return 0;
    // }

    public static void main(String[] args) {
    }

}
