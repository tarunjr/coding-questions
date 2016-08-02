package org.learning.solutions;

import java.util.HashSet;
import java.util.Set;

public class Matrix {
    public static void rotate(int[][] matrix) {
        int dim = matrix.length;
        if(dim == 0) {
            return;
        }
        int ox = 0;
        int oy = 0;
        for(int d=dim; d > 0; d-= 2 ) {
            int rotations = d -1;
            while(rotations-- > 0) {
            	perimeterRotate(matrix,ox ,oy, d);
            }
            ++ox;
            ++oy;
        }
    }
    private static void perimeterRotate(int[][] matrix, int ox, int oy, int dim) {
        if (dim == 1)
            return;
            
        int temp =  matrix[oy][ox+dim-1];
        
        for(int i = ox+dim-1; i > ox ; i--) {
            matrix[oy][i]= matrix[oy][i-1];
        }
        for(int j = oy; j < oy+dim-1 ; j++) {
            matrix[j][ox] = matrix[j+1][ox];
        }
        for(int i=ox; i < ox+dim-1;i++) {
            matrix[oy+dim-1][i]=  matrix[oy+dim-1][i+1];
        }
        for(int j = oy+dim-1; j > oy ; j--) {
            matrix[j][ox+dim-1] = matrix[j-1][ox+dim-1];
        }
        
        matrix[oy+1][ox+dim-1] = temp;
    }
    public static void setZeroes(int[][] matrix) {
        Set<Integer> zeroCols = new HashSet<Integer>();
        Set<Integer> zeroRows = new HashSet<Integer>();
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        for (int i=0; i < m;i++) {
            for (int j=0; j < n; j++) {
                if(matrix[i][j] == 0) {
                    zeroCols.add(j);
                    zeroRows.add(i);
                }
            }
        }
        for (Integer row: zeroRows) {
            setRowZeros(matrix, m, n, row);
        }
        for (Integer col: zeroCols) {
            setColumnZeros(matrix, m, n, col);
        }
    }
    private static void setColumnZeros(int[][] matrix, int m, int n, int col) {
        for (int i=0; i < m; i++) {
            matrix[i][col] = 0;
        }
    }
    private static void setRowZeros(int[][] matrix, int m, int n, int row) {
        for (int i=0; i < n; i++) {
            matrix[row][i] = 0;
        }
    }
}