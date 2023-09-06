package milo.opcua.server;

public class CoordinateMapper {
    // The size of each sector in the coordinate system (10x10)
    private static final int SECTOR_SIZE = 1000;

    public static void main(String[] args) {
        int[][] mazeGraph = {
                // The maze graph as you provided it
        };

        // Test mapping from coordinate system to matrix
        int xCoord = 1;
        int yCoord = 2;
        int[] matrixCoords = mapCoordinatesToMatrix(xCoord, yCoord);
        System.out.println("Coordinate System to Matrix: (" + xCoord + ", " + yCoord + ") -> (" + matrixCoords[0] + ", " + matrixCoords[1] + ")");

        // Test mapping from matrix to coordinate system
        int xMatrix = 30;
        int yMatrix = 50;
        int[] coords = mapMatrixToCoordinates(xMatrix, yMatrix);
        System.out.println("Matrix to Coordinate System: (" + xMatrix + ", " + yMatrix + ") -> (" + coords[0] + ", " + coords[1] + ")");
    }

    // Function to map coordinates from the coordinate system to the matrix
    public static int[] mapCoordinatesToMatrix(int x, int y) {
        int[] matrixCoords = new int[2];
        // Divide the coordinates by the sector size to find the correct sector part
        matrixCoords[0] = x / SECTOR_SIZE;
        matrixCoords[1] = y / SECTOR_SIZE;
        return matrixCoords;
    }

    // Function to map coordinates from the matrix to the coordinate system
    public static int[] mapMatrixToCoordinates(int x, int y) {
        int[] coords = new int[2];
        // Each sector in the matrix corresponds to a 10x10 area in the coordinate system
        coords[0] = x * SECTOR_SIZE + SECTOR_SIZE / 2;
        coords[1] = y * SECTOR_SIZE + SECTOR_SIZE / 2;
        return coords;
    }


}