package forestfiresimulator;

import java.util.Random;

/**
 *
 * @author Sadan Mallhi
 * @version May 5, 2014
 */
public class ForestFireSimulator{

    private final int world[][];
    private final int cols;
    private final int rows;
    private double pGrowth;
    private double pLightning;
    private double random;

    public ForestFireSimulator(int worldSize, double pGrowth, double pLightning) {
        this.world = new int[worldSize][worldSize];
        this.cols = world[0].length;
        this.rows = world.length;
        this.pGrowth = pGrowth;
        this.pLightning = pLightning;

        initializeArray();

        //next();
    }

    public double getpGrowth() {
        return pGrowth;
    }

    public void setpGrowth(double pGrowth) {
        this.pGrowth = pGrowth;
    }

    public double getpLightning() {
        return pLightning;
    }

    public void setpLightning(double pLightning) {
        this.pLightning = pLightning;
    }

    private void initializeArray() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                world[i][j] = 0;
            }
        }
    }

    public double random() {
        Random rand = new Random();
        random = rand.nextDouble();
        return random;
    }

    public void next() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (random() < pGrowth) {
                    world[i][j] = 1;
                }
                if (world[i][j] == 1 && random() < pLightning) {
                    world[i][j] = 2;
                }
                if (world[i][j] == 2) {
                    world[i][j] = 0;
                }
                if (numberBurningNeighbors(i, j) > 0) {
                    world[i][j] = 2;
                }
                //System.out.println(random());
            }
        }

    }

    private int numberBurningNeighbors(int x, int y) {
        int count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (isBurning(i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getSize() {
        return world.length;
    }

    public void clear() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                world[i][j] = 0;
            }
        }
    }

    public boolean isBurning(int x, int y) {
        int n = world.length;
        if (x < 0 || x >= n || y < 0 || y >= n) {
            return false;
        }

        return world[x][y] == 2;
    }

    public boolean isEmpty(int x, int y) {
        return world[x][y] == 0;
    }

    public boolean isTree(int x, int y) {
        return world[x][y] == 1;
    }
    public boolean isFire(int x, int y) {
        return world[x][y] == 2;
    }
}
