import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.*;

/**
 * An operator that contains all of the linear
 * algebra operations used in calculations.
 *
 * @author Nicolette Fink
 * @version 3.1
 */
public class Procedures {

    private Random rand = new Random();
    private Double[][] r;
    private Double[][] j;

    /**
     * Runs Gauss-Newton for quadratic equations.
     * @param points The data points.
     * @param guess The initial guess.
     * @param iterations The number of iterations to run.
     * @param qr The QR factorization method to use.
     * @return The approximate values for a, b, and c
     * in the quadratic equation.
     */
    public Double[][] gn_qua(Double[][] points, Double[][] guess, int iterations, String qr) {
        Double[] x = new Double[points[0].length];
        x = points[0];
        Double[] y = new Double[points[1].length];
        y = points[1];
        Double b1 = guess[0][0];
        Double b2 = guess[1][0];
        Double b3 = guess[2][0];
        int n = iterations;
    
        r = new Double[x.length][1];
        for (int i = 0; i < x.length; i++) {
            r[i][0] = y[i] - (b1 * (x[i] * x[i]) + b2 * x[i] + b3);
        }
        j = new Double[x.length][3];
        for (int i = 0; i < j.length; i++) {
            j[i][0] = -x[i] * x[i];
            j[i][1] = -x[i];
            j[i][2] = -1.0;
        }
        for (int k = 0; k < n; k++) {
            
            ArrayList<Double[][]> factors = null;
            if (qr.equals("h")) {
                factors = qr_fact_househ(j);
            } else if (qr.equals("g")) {
                factors = qr_fact_givens(j);
            }
            Double[][] Q = factors.get(0);
            Double[][] Q2 = new Double[Q.length][3];
            for (int i = 0; i < Q.length; i++) {
                for (int j = 0; j < 3; j++) {
                    Q2[i][j] = Q[i][j];
                }
            }
            Double[][] R = factors.get(1);
            Double[][] R2 = new Double[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    R2[i][j] = R[i][j];
                }
            }
            Double[][] QTr = multiply(transpose(Q2), r);
            QTr[2][0] = QTr[2][0] / R2[2][2];
            QTr[0][0] = QTr[0][0] - QTr[2][0] * R2[0][2];
            QTr[1][0] = QTr[1][0] - QTr[2][0] * R2[1][2];
            QTr[1][0] = QTr[1][0] / R2[1][1];
            QTr[0][0] = QTr[0][0] - QTr[1][0] * R2[0][1];
            QTr[0][0] = QTr[0][0] / R2[0][0];
            b1 = b1 - QTr[0][0];
            b2 = b2 - QTr[1][0];
            b3 = b3 - QTr[2][0];
            
            r = new Double[x.length][1];
            for (int i = 0; i < x.length; i++) {
                r[i][0] = y[i] - (b1 * (x[i] * x[i]) + b2 * x[i] + b3);
            }
            j = new Double[x.length][3];
            for (int i = 0; i < j.length; i++) {
                j[i][0] = -x[i] * x[i];
                j[i][1] = -x[i];
                j[i][2] = -1.0;
            }
        }
        Double[][] estimate = {{b1},{b2},{b3}};
        return estimate;
    }
    
    /**
     * Runs Gauss-Newton for exponential equations.
     * @param points The data points.
     * @param guess The initial guess.
     * @param iterations The number of iterations to run.
     * @param qr The QR factorization method to use.
     * @return The approximate values for a, b, and c
     * in the exponential equation.
     */
    public Double[][] gn_exp(Double[][] points, Double[][] guess, int iterations, String qr) {
        Double[] x = new Double[points[0].length];
        x = points[0];
        Double[] y = new Double[points[1].length];
        y = points[1];
        Double b1 = guess[0][0];
        Double b2 = guess[1][0];
        Double b3 = guess[2][0];
        int n = iterations;
    
        r = new Double[x.length][1];
        for (int i = 0; i < x.length; i++) {
            r[i][0] = y[i] - (Double) (b1 * Math.exp(b2 * x[i]) + b3);
        }
        j = new Double[x.length][3];
        for (int i = 0; i < j.length; i++) {
            j[i][0] = -Math.exp(b2 * x[i]);
            j[i][1] = -b1 * x[i] * (Double) Math.exp(b2 * x[i]);
            j[i][2] = -1.0;
        }
        for (int k = 0; k < n; k++) {
        
            ArrayList<Double[][]> factors = null;
            if (qr.equals("h")) {
                factors = qr_fact_househ(j);
            } else if (qr.equals("g")) {
                factors = qr_fact_givens(j);
            }
            Double[][] Q = factors.get(0);
            Double[][] Q2 = new Double[Q.length][3];
            for (int i = 0; i < Q.length; i++) {
                for (int j = 0; j < 3; j++) {
                    Q2[i][j] = Q[i][j];
                }
            }
            Double[][] R = factors.get(1);
            Double[][] R2 = new Double[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    R2[i][j] = R[i][j];
                }
            }
            Double[][] QTr = multiply(transpose(Q2), r);
            QTr[2][0] = QTr[2][0] / R2[2][2];
            QTr[0][0] = QTr[0][0] - QTr[2][0] * R2[0][2];
            QTr[1][0] = QTr[1][0] - QTr[2][0] * R2[1][2];
            QTr[1][0] = QTr[1][0] / R2[1][1];
            QTr[0][0] = QTr[0][0] - QTr[1][0] * R2[0][1];
            QTr[0][0] = QTr[0][0] / R2[0][0];
            b1 = b1 - QTr[0][0];
            b2 = b2 - QTr[1][0];
            b3 = b3 - QTr[2][0];
            
            r = new Double[x.length][1];
            for (int i = 0; i < x.length; i++) {
                r[i][0] = y[i] - (b1 * Math.exp(b2 * x[i]) + b3);
            }
            j = new Double[x.length][3];
            for (int i = 0; i < j.length; i++) {
                j[i][0] = -Math.exp(b2 * x[i]);
                j[i][1] = -b1 * x[i] * Math.exp(b2 * x[i]);
                j[i][2] = -1.0;
            }
        }
        Double[][] estimate = {{b1},{b2},{b3}};
        return estimate;
    }
    
    /**
     * Runs Gauss-Newton for logarithmic equations.
     * @param points The data points.
     * @param guess The initial guess.
     * @param iterations The number of iterations to run.
     * @param qr The QR factorization method to use.
     * @return The approximate values for a, b, and c
     * in the logarithmic equation.
     */
    public Double[][] gn_log(Double[][] points, Double[][] guess, int iterations, String qr) {
        Double[] x = new Double[points[0].length];
        x = points[0];
        Double[] y = new Double[points[1].length];
        y = points[1];
        Double b1 = guess[0][0];
        Double b2 = guess[1][0];
        Double b3 = guess[2][0];
        int n = iterations;
    
        r = new Double[x.length][1];
        for (int i = 0; i < x.length; i++) {
            r[i][0] = y[i] - (b1 * Math.log(x[i] + b2) + b3);
        }
        j = new Double[x.length][3];
        for (int i = 0; i < j.length; i++) {
            j[i][0] = -Math.log(x[i] + b2);
            j[i][1] = -b1 / (x[i] + b2);
            j[i][2] = -1.0;
        }
        for (int k = 0; k < n; k++) {
        
            ArrayList<Double[][]> factors = null;
            if (qr.equals("h")) {
                factors = qr_fact_househ(j);
            } else if (qr.equals("g")) {
                factors = qr_fact_givens(j);
            }
            Double[][] Q = factors.get(0);
            Double[][] Q2 = new Double[Q.length][3];
            for (int i = 0; i < Q.length; i++) {
                for (int j = 0; j < 3; j++) {
                    Q2[i][j] = Q[i][j];
                }
            }
            Double[][] R = factors.get(1);
            Double[][] R2 = new Double[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    R2[i][j] = R[i][j];
                }
            }
            Double[][] QTr = multiply(transpose(Q2), r);
            QTr[2][0] = QTr[2][0] / R2[2][2];
            QTr[0][0] = QTr[0][0] - QTr[2][0] * R2[0][2];
            QTr[1][0] = QTr[1][0] - QTr[2][0] * R2[1][2];
            QTr[1][0] = QTr[1][0] / R2[1][1];
            QTr[0][0] = QTr[0][0] - QTr[1][0] * R2[0][1];
            QTr[0][0] = QTr[0][0] / R2[0][0];
            b1 = b1 - QTr[0][0];
            b2 = b2 - QTr[1][0];
            b3 = b3 - QTr[2][0];
            if (k == 2 || k == 3) {
                System.out.println(b1 + " " + b2 + " " + b3);
            }
            r = new Double[x.length][1];
            for (int i = 0; i < x.length; i++) {
                r[i][0] = y[i] - (b1 * Math.log(x[i] + b2) + b3);
            }
            j = new Double[x.length][3];
            for (int i = 0; i < j.length; i++) {
                j[i][0] = -Math.log(x[i] + b2);
                j[i][1] = -b1 / (x[i] + b2);
                j[i][2] = -1.0;
            }
        }
        Double[][] estimate = {{b1},{b2},{b3}};
        return estimate;
    }
    
    /**
     * Runs Gauss-Newton for rational equations.
     * @param points The data points.
     * @param guess The initial guess.
     * @param iterations The number of iterations to run.
     * @param qr The QR factorization method to use.
     * @return The approximate values for a, b, and c
     * in the rational equation.
     */
    public Double[][] gn_rat(Double[][] points, Double[][] guess, int iterations, String qr) {
        Double[] x = new Double[points[0].length];
        x = points[0];
        Double[] y = new Double[points[1].length];
        y = points[1];
        Double b1 = guess[0][0];
        Double b2 = guess[1][0];
        Double b3 = guess[2][0];
        int n = iterations;
    
        r = new Double[x.length][1];
        for (int i = 0; i < x.length; i++) {
            r[i][0] = y[i] - ((b1 * x[i]) / (x[i] + b2)) - b3;
        }
        j = new Double[x.length][3];
        for (int i = 0; i < j.length; i++) {
            j[i][0] = -x[i] / (b2 + x[i]);
            j[i][1] = (b1 * x[i]) / Math.pow(b2 + x[i],2);
            j[i][2] = -1.0;
        }
        for (int k = 0; k < n; k++) {
        
            ArrayList<Double[][]> factors = null;
            if (qr.equals("h")) {
                factors = qr_fact_househ(j);
            } else if (qr.equals("g")) {
                factors = qr_fact_givens(j);
            }            
            Double[][] Q = factors.get(0);
            Double[][] Q2 = new Double[Q.length][3];
            for (int i = 0; i < Q.length; i++) {
                for (int j = 0; j < 3; j++) {
                    Q2[i][j] = Q[i][j];
                }
            }
            Double[][] R = factors.get(1);
            Double[][] R2 = new Double[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    R2[i][j] = R[i][j];
                }
            }
            Double[][] QTr = multiply(transpose(Q2), r);
            QTr[2][0] = QTr[2][0] / R2[2][2];
            QTr[0][0] = QTr[0][0] - QTr[2][0] * R2[0][2];
            QTr[1][0] = QTr[1][0] - QTr[2][0] * R2[1][2];
            QTr[1][0] = QTr[1][0] / R2[1][1];
            QTr[0][0] = QTr[0][0] - QTr[1][0] * R2[0][1];
            QTr[0][0] = QTr[0][0] / R2[0][0];
            b1 = b1 - QTr[0][0];
            b2 = b2 - QTr[1][0];
            b3 = b3 - QTr[2][0];
            if (k == 2 || k == 3) {
                System.out.println(b1 + " " + b2 + " " + b3);
            }
            r = new Double[x.length][1];
            for (int i = 0; i < x.length; i++) {
                r[i][0] = y[i] - ((b1 * x[i]) / (x[i] + b2)) - b3;
            }
            j = new Double[x.length][3];
            for (int i = 0; i < j.length; i++) {
                j[i][0] = -x[i] / (b2 + x[i]);
                j[i][1] = (b1 * x[i]) / Math.pow(b2 + x[i],2);
                j[i][2] = -1.0;
            }
        }
        Double[][] estimate = {{b1},{b2},{b3}};
        return estimate;
    }
    
    /**
     * Calculates QR using Householder Reflections.
     * @param matrix The matrix to decompose.
     * @return A list where the first element is Q, and the second
     * element is R.
     */
    public ArrayList<Double[][]> qr_fact_househ(Double[][] matrix) {
        int start = 0;
        int vecLength = matrix.length;
        Double[][] I = new Double[matrix.length][matrix.length];
        for (int i = 0; i < I.length; i++) {
            I[i][i] = 1.0;
        }
        I = fillInZeros(I, I.length);
        Double[][] Q = I;
        Double[][] x;
        for (int i = 0; i < matrix[0].length; i++) {
            if (vecLength > 1) {
                x = new Double[vecLength][1];
                int k = 0;
                for (int j = start; j < matrix.length; j++) {
                    x[k][0] = matrix[j][i];
                    k++;
                }
                Double[][] v = x;
                v[0][0] = v[0][0] + norm(x);
                Double[][] u = divideByConstant(v, norm(v));
                Double[][] H = multiply(u, transpose(u));
                H = multiplyByConstant(H, 2.0);
                H = fillInZeros(H, matrix.length);
                H = subtract(I, H);
                Q = multiply(Q, H);
                matrix = multiply(H, matrix);
                vecLength--;
                start++;
            }
        }
        ArrayList<Double[][]> factorization = new ArrayList<Double[][]>(2);
        factorization.add(Q);
        factorization.add(matrix);
        
        return factorization;
    }
    
    /**
     * Fills in zeros anywhere a matrix is null.
     * @param matrix The matrix to fill in zeros for.
     * @param size To resize the matrix as needed.
     * @return The updated matrix.
     */
    private Double[][] fillInZeros(Double[][] matrix, int size) {
        int difference = size - matrix.length;
        Double[][] nmatrix = new Double[size][size];
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[i].length - 1; j >= 0; j--) {
                nmatrix[i + difference][j + difference] = matrix[i][j];
            }
        }
        for (int i = 0; i < nmatrix.length; i++) {
            for (int j = 0; j < nmatrix[0].length; j++) {
                if (nmatrix[i][j] == null) {
                    nmatrix[i][j] = 0.0;
                }
            }
        }
        return nmatrix;
    }
    
    /**
     * Calculates QR using Given's Rotations.
     * @param matrix The matrix to decompose.
     * @return A list where the first element is Q, and the second
     * element is R.
     */
    public ArrayList<Double[][]> qr_fact_givens(Double[][] matrix) {
        Double[][] I = new Double[matrix.length][matrix.length];
        for (int i = 0; i < I.length; i++) {
            I[i][i] = 1.0;
        }
        I = fillInZeros(I, I.length);
        Double[][] Q = I;
        Double[][] x = new Double[2][1];
        for (int i = 0; i < matrix[0].length; i++) {            
            for (int j = matrix.length - 1; j > i; j--) {
                x[0][0] = matrix[j - 1][i];
                x[1][0] = matrix[j][i];
                double cos = x[0][0] / norm(x);
                double sin = -x[1][0] / norm(x);
                Double[][] G = new Double[matrix.length][matrix.length];
                for (int k = 0; k < G.length; k++) {
                    G[k][k] = 1.0;
                }
                G = fillInZeros(G, G.length);
                G[j][j] = cos;
                G[j][j-1] = sin;
                G[j-1][j] = -sin;
                G[j-1][j-1] = cos;
                Q = multiply(Q, transpose(G));
                matrix = multiply(G, matrix);
            }
        }
        ArrayList<Double[][]> factorization = new ArrayList<Double[][]>(2);
        factorization.add(Q);
        factorization.add(matrix);
        return factorization;
    }
    
    /**
     * Fills in 1s along the main diagonal if the
     * diagonal is zero.
     * @param matrix The matrix to fill in 1s in.
     * @return The updated matrix.
     */
    private Double[][] fillInOnes(Double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][i] == 0.0) {
                matrix[i][i] = 1.0;
            }
        }
        return matrix;
    }
    
    /**
     * Runs the power method on a matrix to estimate the
     * largest eigenvalue and it's corresponding eigenvector.
     * @param matrix The matrix to run the power method on.
     * @param guess An initial guess (must be nonzero).
     * @param iterations The number of iterations to run.
     * @param param The tolerance parameter.
     * @return The eigenvalue and eigenvector stored in an array.
     */
    public Double[][] power_method(Double[][] matrix,Double[][] guess, int iterations, double param) {
        Double[] eigenvalue = new Double[1];
        Double[][] x0 = guess;
        Double[][] x1 = guess;
        Double[][] x2 = new Double[guess.length][1];
        int n = 0;
        do {
            x2 = multiply(matrix, x1);
            eigenvalue[0] = x2[0][0];
            x0 = x1;
            x1 = divideByConstant(x2, eigenvalue[0]);
            n++;
        } while (relError(x1, x0) > param && n <= iterations);
        if (n > iterations) {
            eigenvalue[0] = null;
            for (int k = 0; k < x1.length; k++) {
                x1[k][0] = null;
            }
        }
        Double[] eigenvector = new Double[x1.length];
        for (int i = 0; i < x1.length; i++) {
            eigenvector[i] = x1[i][0];
        }
        Double[] iter = new Double[1];
        iter[0] = (double) n - 1;
        Double[][] toReturn = new Double[3][];
        toReturn[0] = eigenvalue;
        toReturn[1] = eigenvector;
        toReturn[2] = iter;
        return toReturn;
    }
    
    /**
     * Runs the power method on a large number of matrices
     * and plots the data collected.
     * @return An array containing the data collected by
     * running the power method and inverse power method
     * on each matrix generated.
     */
    public Double[][] graph_power_method() {
        // eigenvalues[0]: largest eigenvalue
        // eigenvalues[1]: smallest eigenvalue
        // eigenvalues[2]: trace of matrix
        // eigenvalues[3]: determinant of matrix
        // eigenvalues[4]: iterations for power method
        // eigenvalues[5]: iterations for inverse power method
        // eigenvalues[6]: trace of the inverse of the matrix
        // eigenvalues[7]: determinant of the inverse of the matrix
        Double[][] eigenvalues = new Double[8][1000];
        for (int i = 0; i < 1000; i++) {
            Double[][] matrix = new Double[2][2];
            matrix[0][0] = -2 + rand.nextDouble() * 4;
            matrix[0][1] = -2 + rand.nextDouble() * 4;
            matrix[1][0] = -2 + rand.nextDouble() * 4;
            matrix[1][1] = -2 + rand.nextDouble() * 4;
            int n = 0;
            Double[][] x0 = {{1.0},{1.0}};
            Double[][] x1 = {{1.0},{1.0}};
            Double[][] x2 = new Double[2][1];
            Double eigenvalue = 0.0;
            do {
                x2 = multiply(matrix, x1);
                eigenvalue = x2[0][0];
                x0 = x1;
                x1 = divideByConstant(x2, eigenvalue);
                n++;
            } while (relError(x1, x0) > 0.00005 && n <= 100);
            if (n > 100) {
                eigenvalue = null;
            }
            eigenvalues[0][i] = eigenvalue;
            Double min = inverse_pm(matrix, eigenvalues, i);
            eigenvalues[1][i] = min;
            eigenvalues[2][i] = matrix[0][0] + matrix[1][1];
            eigenvalues[3][i] = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
            eigenvalues[4][i] = (double) n;
        }
        return eigenvalues;
    }
    
    /**
     * Runs the inverse power method on a provided matrix to estimate
     * the smallest eigenvalue of the original matrix.
     * @param matrix The original matrix.
     * @param data The array of data.
     * @param index The current index within the array of data.
     * @return The smallest eigenvalue of the original matrix.
     */
    private Double inverse_pm(Double[][] matrix, Double[][] data, int index) {
        Double[][] matrixInv = inverse(matrix);
        data[6][index] = matrixInv[0][0] + matrixInv[1][1];
        data[7][index] = matrixInv[0][0] * matrixInv[1][1] - matrixInv[0][1] * matrixInv[1][0];
        Double eigenvalue = 0.0;
        int n = 0;
        Double[][] x0 = {{1.0},{1.0}};
        Double[][] x1 = {{1.0},{1.0}};
        Double[][] x2 = new Double[2][1];
        do {
            x2 = multiply(matrixInv, x1);
            eigenvalue = x2[0][0];
            x0 = x1;
            x1 = divideByConstant(x2, eigenvalue);
            n++;
        } while (relError(x1, x0) > 0.00005 && n <= 100);
        eigenvalue = 1 / eigenvalue;
        if (n > 100) {
            eigenvalue = null;
        }
        data[5][index] = (double) n;
        return eigenvalue;
    }
    
    /**
     * Calculates the relative error between two vectors.
     * @param x1 The first vector.
     * @param x0 The second vector.
     * @return The relative error.
     */
    private double relError(Double[][] x1, Double[][] x0) {
        Double[][] difference = subtract(x1, x0);
        double normDiff = norm(difference);
        double normx1 = norm(x1);
        return Math.abs(normDiff / normx1);
    }
    
    /**
     * Calculates the norm of a vector.
     * @param x The vector.
     * @return The norm of x.
     */
    private double norm(Double[][] x) {
        Double length = 0.0;
        for (int i = 0; i < x.length; i++) {
            length = length + x[i][0] * x[i][0];
        }
        return Math.sqrt(length);
    }
    
    /**
     * Divides a matrix by a scalar constant.
     * @param matrix The matrix to operate on.
     * @param constant The scalar to divide by.
     * @return The updated matrix.
     */
    private Double[][] divideByConstant(Double[][] matrix, Double constant) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = matrix[i][j] / constant;
            }
        }
        return matrix;
    }
    
    /**
     * Calculates the inverse of a 2x2 matrix.
     * @param matrix The matrix to calculate the inverse of.
     * @return The inverse of the matrix.
     */
    private Double[][] inverse(Double[][] matrix) {
        Double[][] inverse = new Double[matrix.length][matrix[0].length];
        inverse[0][0] = matrix[1][1];
        inverse[1][1] = matrix[0][0];
        inverse[0][1] = -matrix[0][1];
        inverse[1][0] = -matrix[1][0];
        Double coeff = 1 / (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]);
        inverse[0][0] = inverse[0][0] * coeff;
        inverse[0][1] = inverse[0][1] * coeff;
        inverse[1][0] = inverse[1][0] * coeff;
        inverse[1][1] = inverse[1][1] * coeff;
        return inverse;
    }
    
    /**
     * Multiplies two matrices together.
     * @param matrix1 The first matrix.
     * @param matrix2 The second matrix.
     * @return The product of the two matrices.
     */
    private Double[][] multiply(Double[][] matrix1, Double[][] matrix2) {
        Double[][] product = new Double[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                Double dotProduct = 0.0;
                for (int k = 0; k < matrix1[0].length; k++) {
                    dotProduct = dotProduct + matrix1[i][k] * matrix2[k][j];
                }
                product[i][j] = dotProduct;
            }
        }
        return product;
    }
    
    /**
     * Multiplies a matrix by a constant.
     * @param matrix The matrix to operate on.
     * @param constant The scalar constant to multiply by.
     * @return The updated matrix.
     */
    private Double[][] multiplyByConstant(Double[][] matrix, Double constant) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = matrix[i][j] * 2;
            }
        }
        return matrix;
    }
    
    /**
     * Transposes a matrix.
     * @param matrix The matrix to transpose.
     * @return The transpose of the matrix.
     */
    private Double[][] transpose(Double[][] matrix) {
        Double[][] transpose = new Double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }
        return transpose;
    }

    /**
     * Subtracts one vector from another.
     * @param matrix1 The first vector.
     * @param matrix2 The second vector.
     * @param The difference of matrix1 - matrix2.
     */
    private Double[][] subtract(Double[][] matrix1, Double[][] matrix2) {
        Double[][] difference = new Double[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                difference[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return difference;
    }
    
    /**
     * Prints a matrix or vector to the command line.
     * @param matrix The matrix to print.
     */
    public void print(Double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * Returns a string representation of the matrix.
     * @param matrix The matrix to write as a string.
     * @return The string representation of the matrix.
     */
    public String toString(Double[][] matrix) {
        String string = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                string = string + matrix[i][j] + " ";
            }
            string = string + "\n";
        }
        return string;
    }
    
    /**
     * Creates a scatter plot.
     * @param eigenvalues The data collected from the
     * power method and inverse power method run on a
     * large number of matrices.
     */
    public void createPlot(Double[][] eigenvalues) {
        double[] trace = new double[eigenvalues[2].length];
        for (int i = 0; i < eigenvalues[2].length; i++) {
            trace[i] = eigenvalues[2][i];
        }
        double[] determinant = new double[eigenvalues[3].length];
        for (int i = 0; i < eigenvalues[3].length; i++) {
            determinant[i] = eigenvalues[3][i];
        }
        
        Color color = new Color(0,0,0);
        Plot plot = Plot.plot(Plot.plotOpts().
            title("Trace vs. Determinant, Color Coded based on Iterations in Power Method")).
            xAxis("Determinant", Plot.axisOpts().
            range(-6,6)).
            yAxis("Trace", Plot.axisOpts().
            range(-5,5));
        for (int i = 0; i < eigenvalues[4].length; i++) {
            double iterations = eigenvalues[4][i];
            if (iterations <= 100) {
                if (iterations < 5.0) {
                    color = new Color(255,220,220);
                } else if (iterations < 10.0) {
                    color = new Color(255,204,204);
                } else if (iterations < 15.0) {
                    color = new Color(255,175,175);
                } else if (iterations < 20.0) {
                    color = new Color(255,153,153);
                } else if (iterations < 25.0) {
                    color = new Color(255,125,125);
                } else if (iterations < 30.0) {
                    color = new Color(255,102,102);
                } else if (iterations < 35.0) {
                    color = new Color(255,75,75);
                } else if (iterations < 40.0) {
                    color = new Color(255,51,51);
                } else if (iterations < 45.0) {
                    color = new Color(255,25,25);
                } else if (iterations < 50.0) {
                    color = new Color(255,0,0);
                } else if (iterations < 55.0) {
                    color = new Color(220,0,0);
                } else if (iterations < 60.0) {
                    color = new Color(204,0,0);
                } else if (iterations < 65.0) {
                    color = new Color(175,0,0);
                } else if (iterations < 70.0) {
                    color = new Color(153,0,0);
                } else if (iterations < 75.0) {
                    color = new Color(125,0,0);
                } else if (iterations < 80.0) {
                    color = new Color(102,0,0);
                } else if (iterations < 85.0) {
                    color = new Color(75,0,0);
                } else if (iterations < 90.0) {
                    color = new Color(51,0,0);
                } else if (iterations < 95.0) {
                    color = new Color(25,0,0);
                } else if (iterations <= 100.0) {
                    color = new Color(0,0,0);
                }
                plot.series(Double.toString(eigenvalues[0][i]), Plot.data().
                xy(determinant[i], trace[i]),
                Plot.seriesOpts().
                marker(Plot.Marker.CIRCLE).
                markerColor(color).
                markerSize(8).
                color(Color.BLACK).
                line(Plot.Line.NONE));
            }
        }
        try {    
            plot.save("Power_Method", "png");
        } catch (Exception e) {
            System.out.println("Could not save image.");
        }
        
        double[] traceInv = new double[eigenvalues[2].length];
        for (int i = 0; i < eigenvalues[2].length; i++) {
            traceInv[i] = eigenvalues[2][i];
        }
        double[] determinantInv = new double[eigenvalues[3].length];
        for (int i = 0; i < eigenvalues[3].length; i++) {
            determinantInv[i] = eigenvalues[3][i];
        }
        
        color = new Color(0,0,0);
        Plot plot2 = Plot.plot(Plot.plotOpts().
            title("Trace vs. Determinant, Color Coded based on Iterations in Inverse Power Method")).
            xAxis("Determinant", Plot.axisOpts().
            range(-6,6)).
            yAxis("Trace", Plot.axisOpts().
            range(-5,5));
        for (int i = 0; i < eigenvalues[4].length; i++) {
            double iterationsInv = eigenvalues[5][i];
            if (iterationsInv <= 100) {
                if (iterationsInv < 5.0) {
                    color = new Color(255,220,220);
                } else if (iterationsInv < 10.0) {
                    color = new Color(255,204,204);
                } else if (iterationsInv < 15.0) {
                    color = new Color(255,175,175);
                } else if (iterationsInv < 20.0) {
                    color = new Color(255,153,153);
                } else if (iterationsInv < 25.0) {
                    color = new Color(255,125,125);
                } else if (iterationsInv < 30.0) {
                    color = new Color(255,102,102);
                } else if (iterationsInv < 35.0) {
                    color = new Color(255,75,75);
                } else if (iterationsInv < 40.0) {
                    color = new Color(255,51,51);
                } else if (iterationsInv < 45.0) {
                    color = new Color(255,25,25);
                } else if (iterationsInv < 50.0) {
                    color = new Color(255,0,0);
                } else if (iterationsInv < 55.0) {
                    color = new Color(220,0,0);
                } else if (iterationsInv < 60.0) {
                    color = new Color(204,0,0);
                } else if (iterationsInv < 65.0) {
                    color = new Color(175,0,0);
                } else if (iterationsInv < 70.0) {
                    color = new Color(153,0,0);
                } else if (iterationsInv < 75.0) {
                    color = new Color(125,0,0);
                } else if (iterationsInv < 80.0) {
                    color = new Color(102,0,0);
                } else if (iterationsInv < 85.0) {
                    color = new Color(75,0,0);
                } else if (iterationsInv < 90.0) {
                    color = new Color(51,0,0);
                } else if (iterationsInv < 95.0) {
                    color = new Color(25,0,0);
                } else if (iterationsInv <= 100.0) {
                    color = new Color(0,0,0);
                }
                plot2.series(Double.toString(eigenvalues[1][i]), Plot.data().
                xy(determinantInv[i], traceInv[i]),
                Plot.seriesOpts().
                marker(Plot.Marker.CIRCLE).
                markerColor(color).
                markerSize(8).
                color(Color.BLACK).
                line(Plot.Line.NONE));
            }
        }
            
        try {
            plot2.save("Inverse_Power_Method", "png");
        } catch (Exception e) {
            System.out.println("Could not save image.");
        }
        
        Pic img1 = new Pic("Power_Method.png");
        Pic img2 = new Pic("Inverse_Power_Method.png");
        img1.show();
        img2.show();
    }
}