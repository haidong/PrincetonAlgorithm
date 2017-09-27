import java.util.ArrayList;

public class Board {
    private final int n;
    private final int[][] internalB;

    public Board(int[][] blocks) {
        n = blocks.length;
        internalB = deepCopy(blocks);
    }

    private int[][] deepCopy(int[][] block) {
        int[][] tempBlock = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tempBlock[i][j] = block[i][j];
            }
        }
        return tempBlock;
    }

    public int dimension() {
        return n;
    }

    public int manhattan() {
        int m = 0;
        int supposedRow;
        int supposedCol;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (internalB[row][col] != 0) {
                    supposedRow = getRow(internalB[row][col]);
                    supposedCol = getCol(internalB[row][col]);
                    m += Math.abs(supposedRow - row) + Math.abs(supposedCol - col);
                }
            }
        }
        return m;
    }

    public int hamming() {
        int h = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (internalB[row][col] != 0) {
                    if (internalB[row][col] != (row * n + col + 1)) {
                        h++;
                    }
                }
            }
        }
        return h;
    }

    public boolean isGoal() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (internalB[row][col] != 0) {
                    if (internalB[row][col] != (row * n + col + 1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private int getCol(int cellValue) {
        if ((cellValue % n) != 0)
            return cellValue % n - 1;
        else
            return n - 1;
    }

    private int getRow(int cellValue) {
        if ((cellValue % n) != 0)
            return cellValue / n;
        else
            return cellValue / n - 1;
    }

    @Override
    public boolean equals(Object y) {
        if (this == y)
            return true;
        if (y instanceof Board) {
            Board that = (Board) y;
            if (this.dimension() != that.dimension())
                return false;
            for (int row = 0; row < n; row++) {
                if (this.internalB[row].length != that.internalB[row].length)
                    return false;
                for (int col = 0; col < n; col++) {
                    if (this.internalB[row][col] != that.internalB[row][col])
                        return false;
                }
            }
            return true;
        }
        return false;
    }

    public Board twin() { 
        int[][] twinBlocks = deepCopy(internalB);

        int i = 0;
        int j = 0;
        while (twinBlocks[i][j] == 0 || twinBlocks[i][j + 1] == 0) {
            j++;
            if (j >= twinBlocks.length - 1) {
                i++;
                j = 0;
            }
        }

        exchangeBlocks(twinBlocks, i, j, i, j + 1);
        return new Board(twinBlocks);
    }

    private void exchangeBlocks(int[][] blocks, int iFirstBlock, int jFirstBlock, int iSecondsBlock, int jSecondBlock) {
        int firstValue = blocks[iFirstBlock][jFirstBlock];
        blocks[iFirstBlock][jFirstBlock] = blocks[iSecondsBlock][jSecondBlock];
        blocks[iSecondsBlock][jSecondBlock] = firstValue;
    }

    private Board swap(int pair1Row, int pair1Col, int pair2Row, int pair2Col) {
        int[][] tempBlock = deepCopy(internalB);
        int temp = tempBlock[pair1Row][pair1Col];
        tempBlock[pair1Row][pair1Col] = tempBlock[pair2Row][pair2Col];
        tempBlock[pair2Row][pair2Col] = temp;
        return new Board(tempBlock);
    }

    public Iterable<Board> neighbors() {
        ArrayList<Board> friends = new ArrayList<Board>();
        int[] blankCell = getBlankCell();

        if (blankCell[0] - 1 >= 0) { // Cell above
            friends.add(swap(blankCell[0], blankCell[1], blankCell[0] - 1, blankCell[1]));
        }
        if (blankCell[0] + 1 < n) { // Cell below
            friends.add(swap(blankCell[0], blankCell[1], blankCell[0] + 1, blankCell[1]));
        }
        if (blankCell[1] - 1 >= 0) { // Cell left
            friends.add(swap(blankCell[0], blankCell[1], blankCell[0], blankCell[1] - 1));
        }
        if (blankCell[1] + 1 < n) { // Cell right
            friends.add(swap(blankCell[0], blankCell[1], blankCell[0], blankCell[1] + 1));
        }
        return friends;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(n + "\n");
        for (int row = 0; row < n; row++) {
            output.append(" ");
            for (int col = 0; col < n; col++) {
                output.append(internalB[row][col] + "  ");
            }
            output.append("\n");
        }
        return output.toString();
    }

    private int[] getBlankCell() {
        int[] blankCell = new int[2];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (internalB[row][col] == 0) {
                    blankCell[0] = row;
                    blankCell[1] = col;
                }
            }
        }
        return blankCell;
    }

    public static void main(String[] args) {

    }

}
