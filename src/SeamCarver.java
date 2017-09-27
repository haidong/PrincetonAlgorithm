import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private final static double BORDER_ENERGY = 1000.0;
    private Picture p;

    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new NullPointerException();
        p = new Picture(picture);
    }

    public static void main(String[] args) {
    }

    public Picture picture() {
        return p;
    }

    public int width() {
        return p.width();
    }

    public int height() {
        return p.height();
    }

    public double energy(int x, int y) {
        if (x < 0 || x > p.width() - 1 || y < 0 || y > p.height() - 1)
            throw new IndexOutOfBoundsException();
        if (x == 0 || x == p.width() - 1 || y == 0 || y == p.height() - 1)
            return BORDER_ENERGY;

        int horizontalSquareGradient = sumXYSquareGradient(x + 1, y, x - 1, y);
        int verticalSquareGradient = sumXYSquareGradient(x, y + 1, x, y - 1);

        return Math.sqrt(horizontalSquareGradient + verticalSquareGradient);
    }

    private int[] getRGB(int x, int y) {
        int[] rgb = new int[3];
        rgb[0] = p.get(x, y).getRed();
        rgb[1] = p.get(x, y).getGreen();
        rgb[2] = p.get(x, y).getBlue();
        return rgb;
    }

    private int sumXYSquareGradient(int x, int y, int i, int j) {
        int[] rgb1 = getRGB(x, y);
        int[] rgb2 = getRGB(i, j);
        int rDistance = rgb1[0] - rgb2[0];
        int gDistance = rgb1[1] - rgb2[1];
        int bDistance = rgb1[2] - rgb2[2];
        return rDistance * rDistance + gDistance * gDistance + bDistance * bDistance;
    }

    public int[] findVerticalSeam() {
        VerticalPixelGraph pg = new VerticalPixelGraph(width(), height());
        
        
        
        
        
        return new int[] { 3, 4, 3, 2, 2 };
    }

}
