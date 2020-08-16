import java.util.List;
import java.util.ArrayList;

public class RowReduction {

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    // just realized that none of this works because of integer division
    // so gonna just have to rewrite all if the int[][] shit as doubles
    public static int[][] rref(int[][] toReduce, int currRow, int currCol) {
        //int currRow = 0; <- implied at the start, leaving so i remember or if
        //recursion doesn't work
        //int currCol = 0;

        // checks if swapping a row will produce a one
        if (toReduce[currRow][currCol] == 1) {
            // be happy
        } else {
            int nextRow = currRow + 1;
            List<Integer> rowNums = new ArrayList<>();
            for (int i = nextRow; i < toReduce[currRow].length; i++) {
                if (toReduce[i][currCol] == 1) {
                    swapRow(toReduce, currRow, nextRow);
                    break;
                } else {
                    rowNums.add(toReduce[i][currCol]);
                }
            }

            // add functionality to check if adding two rows will lead to a one

            // checks if scaling a row will produce a one forwards and backwards
            if (toReduce[currRow][currCol] == 1) {
                // be happy
            } else {
                for (int i = 0; i < rowNums.size(); i++) {
                    if (toReduce[currRow][currCol] % rowNums.get(i) == 0) {
                        scaleRow(toReduce, currRow, (1 / rowNums.get(i)));
                        break;
                    } else if (rowNums.get(i) % toReduce[currRow][currCol] == 0){
                        // i - 1 accounts for the fact that rowNums excludes
                        // first row in iteration
                        swapRow(toReduce, currRow, i - 1);
                        scaleRow(toReduce, currRow, (1 / rowNums.get(i)));
                        break;
                    }
                }
            }

            // worst case scale and combine rows to produce a one. this will
            // be the trickiest case to do
            if (toReduce[currRow][currCol] == 1) {
                // be happy
            } else {

            }
        }

        return null;
    }

    private static int[][] swapRow(int[][] toSwap, int index1, int index2) {

        int[] placeholder = toSwap[index1];
        toSwap[index1] = toSwap[index2];
        toSwap[index2] = placeholder;

        return toSwap;
    }

    private static int[][] scaleRow(int[][] toScale, int index, int scalar) {

        for (int i = 0; i < toScale[index].length; i++) {
            toScale[index][i] *= scalar;
        }

        return toScale;
    }

    private static int[][] addRows(int[][] toCombine, int index1, int index2) {
        for (int i = 0; i < toCombine[index1].length; i++) {
            toCombine[index1][i] += toCombine[index2][i];
        }

        return toCombine;
    }

}
