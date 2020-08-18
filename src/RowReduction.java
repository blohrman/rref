import java.util.Arrays;

public class RowReduction {

    public static void main(String[] args) {
        double[][] test = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        test = rref(test, 0, 0);

        System.out.println(Arrays.deepToString(test));
    }

    public static double[][] rref(double[][] toReduce, int currRow, int currCol) {

        if (currRow == toReduce.length || currCol == toReduce[0].length) {

            // reduce up
            reduceUp(toReduce, --currRow, --currCol);

            return toReduce;
        } else {
            if (toReduce[currRow][currCol] == 1) {
                // be happy
            } else if (toReduce[currRow][currCol] == 0){
                int zeroCount = 0;

                for (int i = currRow; i < toReduce.length; i++) {
                    if (toReduce[i][currCol] != 0) {
                        swapRow(toReduce, i, currRow);
                    } else {
                        zeroCount++;
                    }
                }

                if (zeroCount == toReduce.length - currRow) {
                    return rref(toReduce, ++currRow, ++currCol);
                }
            } else {
                scaleRow(toReduce, currRow, (1 / toReduce[currRow][currCol]));
            }

            System.out.println(Arrays.deepToString(toReduce));

            // reduce down
            for (int i = 1; i < toReduce.length - currRow; i++) {
                scaleAndAddRows(toReduce, currRow, currRow + i, -(toReduce[currRow + i][currCol]));
                System.out.println(Arrays.deepToString(toReduce));
            }

            System.out.println(Arrays.deepToString(toReduce));

            return rref(toReduce, ++currRow, ++currCol);
        }
    }

    private static double[][] reduceUp(double[][] toReduce, int currRow, int currCol) {
        if (currRow == 0 || currCol == 0) {
            return toReduce;
        } else {
            for (int i = 1; i < toReduce.length - currRow; i++) {
                scaleAndAddRows(toReduce, currRow, currRow - i, -(toReduce[currRow - i][currCol]));
                System.out.println(Arrays.deepToString(toReduce));
            }

            return reduceUp(toReduce, --currRow, --currCol);
        }
    }

    private static double[][] swapRow(double[][] toSwap, int index1, int index2) {

        double[] placeholder = toSwap[index1];
        toSwap[index1] = toSwap[index2];
        toSwap[index2] = placeholder;

        return toSwap;
    }

    private static double[][] scaleRow(double[][] toScale, int index, double scalar) {

        for (int i = 0; i < toScale[index].length; i++) {
            toScale[index][i] *= scalar;
        }

        return toScale;
    }

    private static double[][] addRows(double[][] toCombine, int index1, int index2) {
        for (int i = 0; i < toCombine[index1].length; i++) {
            toCombine[index1][i] += toCombine[index2][i];
        }

        return toCombine;
    }

    private static double[][] scaleAndAddRows(double[][] toCombine, int scaleRowIndex, int addRowIndex, double scalar) {
        double[] scaledRow = toCombine[scaleRowIndex];

        double[] scaledRow2 = new double[toCombine[scaleRowIndex].length];

        for (int i = 0; i < scaledRow2.length; i++) {
            scaledRow2[i] = toCombine[scaleRowIndex][i];
        }

        for (int i = 0; i < scaledRow2.length; i++) {
            scaledRow2[i] *= scalar;
        }

        for (int i = 0; i < toCombine[addRowIndex].length; i++) {
            toCombine[addRowIndex][i] += scaledRow2[i];
        }

        return toCombine;
    }

}
