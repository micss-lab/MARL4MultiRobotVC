package milo.opcua.server;

public class MazeSolver {

    private static int[][] mazeGraph = {
            {0, 2, 2, 1, 0, 2, 0, 2, 1, 0},
            {0, 2, 1, 1, 2, 1, 1, 1, 1, 0},
            {0, 1, 1, 0, 0, 1, 0, 1, 2, 1},
            {0, 2, 1, 0, 0, 1, 0, 1, 0, 2},
            {1, 1, 1, 0, 1, 0, 2, 1, 0, 2},
            {2, 0, 1, 0, 2, 1, 1, 1, 0, 0},
            {1, 0, 0, 2, 0, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 1, 1, 0, 0, 0},
            {1, 0, 0, 1, 1, 2, 0, 2, 2, 2},
            {1, 1, 1, 2, 1, 1, 1, 2, 0, 1}
    };

    //private static double[][] qTable = { // multiply with 1000
    //        {0, 39470.9, 38829.5, 42019.2, 0, 47418.9, 0, 36127.6, 25396.8, 0},
    //        {0, 46733.8, 42873.6, 37372.7, 44409.1, 38945, 40248.6, 34278.9, 39381.5, 0},
    //        {12937.1, 33673.7, 41569.4, 0, 21285.1, 43606.4, 7991.43, 40507.4, 28964.3, 0},
    //        {19000.3, 42910, 39075.5, 0, 0, 31958, 21458.4, 39320.3, 0, 0},
    //        {45307.4, 49579.3, 35731.2, 0, 29510.6, 0, 41340.4, 35475, 0, 0},
    //        {38116.8, 0, 44799.8, 0, 38330.7, 39470.6, 42011.1, 36941.3, 0, 0},
    //        {37171, 0, 0, 42635, 12577.4, 11232.9, 36088.7, 0, 0, 0},
    //        {0, 0, 38543.9, 0, 0, 25510.4, 36508.7, 0, 0, 0},
    //        {0, 0, 0, 43320, 26862, 39348.4, 0, 29102.5, 0, 0},
    //        {0, 0, 16428.5, 42054.2, 28468.9, 29513.8, 0, 0, 0, 0}
    //};

    private static double[][] qTable = { // multiply with 1000
            {0, 39470.9, 38829.5, 42019.2, 0, 47418.9, 0, 36127.6, 25396.8, 0},
            {0, 46733.8, 42873.6, 37372.7, 44409.1, 38945, 40248.6, 34278.9, 39381.5, 0},
            {12937.1, 33673.7, 41569.4, 0, 21285.1, 43606.4, 7991.43, 40507.4, 28964.3, 0},
            {19000.3, 50000, 39075.5, 0, 0, 31958, 21458.4, 39320.3, 0, 0},
            {45307.4, 49579.3, 35731.2, 0, 29510.6, 0, 41340.4, 35475, 0, 0},
            {38116.8, 0, 44799.8, 0, 38330.7, 39470.6, 42011.1, 36941.3, 0, 0},
            {37171, 0, 0, 42635, 12577.4, 11232.9, 36088.7, 0, 0, 0},
            {0, 0, 38543.9, 0, 0, 25510.4, 36508.7, 0, 0, 0},
            {0, 0, 0, 43320, 26862, 39348.4, 0, 29102.5, 0, 0},
            {0, 0, 16428.5, 42054.2, 28468.9, 29513.8, 0, 0, 0, 0}
    };


    /*public static int[] getNextSector(int currentRow, int currentCol) {
        int[] nextSector = {-1, -1, mazeGraph[currentRow][currentCol]}; // Default value if all Q-values are negative

        double highestQValue = Double.NEGATIVE_INFINITY;
        int numRows = qTable.length;
        int numCols = qTable[0].length;

        for (int row = currentRow - 1; row <= currentRow + 1; row++) {
            for (int col = currentCol - 1; col <= currentCol + 1; col++) {
                if (row >= 0 && row < numRows && col >= 0 && col < numCols) {
                    double qValue = qTable[row][col];
                    if (qValue > highestQValue) {
                        highestQValue = qValue;
                        nextSector[0] = row;
                        nextSector[1] = col;
                    }
                }
            }
        }

        return nextSector;
    }*/

    public static int[] getNextSector(int currentRow, int currentCol) {
        int[] nextSector = {-1, -1, mazeGraph[currentRow][currentCol]}; // Default value if all Q-values are negative

        double highestQValue = Double.NEGATIVE_INFINITY;
        int numRows = qTable.length;
        int numCols = qTable[0].length;

        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // Up, Down, Right, Left

        for (int[] direction : directions) {
            int newRow = currentRow + direction[0];
            int newCol = currentCol + direction[1];

            if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols) {
                double qValue = qTable[newRow][newCol];
                if (qValue > highestQValue) {
                    highestQValue = qValue;
                    nextSector[0] = newRow;
                    nextSector[1] = newCol;
                    nextSector[2] = mazeGraph[newRow][newCol];
                }
            }
        }

        return nextSector;
    }








}
