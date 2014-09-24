package magicsquare;

/*
 * @author Tabetha Boushey
 * Date: 7/19/2013
 * CSCI232 Lab 5
 * Assignment 2
 * Class: MagicSquare
 */
public class MagicSquare {

    int n = 1; // used to pint the solution number
    private int totalSquares; // total number of squares in the overall square
    private int sum; // the value the rows/columns/diagonals individially added up
    private int numberOfSquares;
    private int[][] squareArray; // holds the numbers we place in it
    private boolean[] possibleArray; // holds the numbers that have not been used yet

    public static void main(String[] args) {
        System.out.println("Here are the solutions for this size square. \n");
        MagicSquare mySquare = new MagicSquare(3);
        mySquare.fillSquares(0, 0);
        System.out.println("There were "+mySquare.getTotalSolutions()+" number of solutions.");
    }

    public MagicSquare(int n) {
        squareArray = new int[n][n]; // fill an empty square; 0 means its empty
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                squareArray[x][y] = 0;
            }
        }
        totalSquares = n * n; // makes the total number of squares symetrical 
        possibleArray = new boolean[totalSquares];
        for (int i = 0; i < totalSquares; i++) {
            possibleArray[i] = true;
        }
        sum = n * (n * n + 1) / 2; // in the 3x3 case it equals 15 (3*3 = 9 + 1 = 10 / 2 = 5 * 3 = 15)
        numberOfSquares = 0;
    }

    public void fillSquares(int row, int column) { // recursive method to fill the squares
        if (!checkRows() || !checkColumns() || !checkDiagonals()) {
            return;
        }
        // if everything is filled, print
        if (row == squareArray.length) {
            System.out.println(this);
            numberOfSquares++;
            return;
        }
        // try each possible number for the square
        for (int i = 0; i < totalSquares; i++) {
            if (possibleArray[i]) {
                squareArray[row][column] = i + 1;
                possibleArray[i] = false;
                // go to the next square
                int newColumn = column + 1;
                int newRow = row;
                if (newColumn == squareArray.length) {
                    newRow++;
                    newColumn = 0;
                }
                // recursively fill the squares
                fillSquares(newRow, newColumn);
                // undo this square, it doesn't work
                squareArray[row][column] = 0; // sets that square back to empty
                possibleArray[i] = true;
            }
        }
    }

    public boolean checkRows() {
        for (int x = 0; x < squareArray.length; x++) {// try each row
            int checkSum = 0; // initialize checkSum
            boolean empty = false;

            for (int y = 0; y < squareArray[x].length; y++) { // add up values in the row
                checkSum += squareArray[x][y]; // add that square to the sum of that row
                if (squareArray[x][y] == 0) { // if that square is 0 make it empty
                    empty = true;
                }
            }
            if (!empty && checkSum != sum) { // if the row is filled and doesn't equal the sum it's invalid
                return false;
            }
        }
        return true; // didn't find proof on an invalid row
    }

    public boolean checkColumns() {
        for (int y = 0; y < squareArray[0].length; y++) { // try each column
            int checkSum = 0; // initialize checkSum
            boolean empty = false;

            for (int x = 0; x < squareArray.length; x++) { // add up values in the column
                checkSum += squareArray[x][y]; // add that square to the sum of that column
                if (squareArray[x][y] == 0) { // if the square is 0 make it empty
                    empty = true;
                }
            }
            if (!empty && checkSum != sum) { // if the column is filled and doesn't equalthe sum it's invalid
                return false;
            }
        }
        return true; // didn't find proof on an invalid column
    }

    public boolean checkDiagonals() {
        // Checks forward diagonal first
        int checkSum = 0; // initialize checkSum
        boolean empty = false;

        for (int x = 0; x < squareArray.length; x++) { // add up values of the forward diagonal
            checkSum += squareArray[x][x]; // add that square to the sum of that diagonal
            if (squareArray[x][x] == 0) { // if the square is 0 make it empty
                empty = true;
            }
        }
        if (!empty && checkSum != sum) { // if the diagonal is filled and doesn't equal the sum it's invalid
            return false;
        }

        // Checks backward diagonal second
        checkSum = 0; // set checkSum back to 0
        empty = false; // set empty back to fales
        for (int x = 0; x < squareArray.length; x++) { // add up values of the backward diagonal
            checkSum += squareArray[x][squareArray.length - 1 - x]; // add that square to the sum of that diagonal
            if (squareArray[x][squareArray.length - 1 - x] == 0) { // if the square is 0 make it empty
                empty = true;
            }
        }
        if (!empty && checkSum != sum) { // if the diagonal is filled and doesn't equal the sum its invalid
            return false;
        }
        return true; // didn't find proof on an invalid diagonal
    }

    public int getTotalSolutions() { // returns total number of solutions
        return numberOfSquares;
    }

    public String toString() {
        String answer = "";

        System.out.println("Solution " + n);
        for (int x = 0; x < squareArray.length; x++) {
            for (int y = 0; y < squareArray[x].length; y++) {
                answer = answer + squareArray[x][y] + " ";
            }
            answer = answer + "\n";
        }
        n++;
        return answer;
    }
}
