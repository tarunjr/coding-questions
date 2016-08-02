package org.learning.solutions;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> output = new ArrayList<Integer>();
        spiralOrderRecursive(matrix, 0,matrix.length-1, matrix[0].length-1,0, output); 
        return output;
    }
    private void spiralOrderRecursive(int[][] matrix, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY, List<Integer> output){
       
        // Nothing to display. Stop the traversal
        if(topLeftX > bottomRightX || topLeftY < bottomRightY)
            return;
            
        if(topLeftX == bottomRightX && topLeftY == bottomRightY){   // Special case 1: singel cell
            output.add(matrix[topLeftY][topLeftX]);
            return;
        }
        else if(topLeftY == bottomRightY) {                         // Special case 2: single row
            for(int i=topLeftX; i<=bottomRightX; i++)
                output.add(matrix[topLeftY][i]);
            return;
        }
        else if(topLeftX == bottomRightX) {                         // Special case 3: single column
            for(int j=topLeftY; j>=bottomRightY; j--)
                output.add(matrix[j][topLeftX]);
            return;
        }
        
        // case 1: top row printed left to right
        for(int i=topLeftX; i< bottomRightX; i++)
            output.add(matrix[topLeftY][i]);
        
        // case 2: Right column printed top to bottom
        for(int j=topLeftY; j> bottomRightY; j--)
            output.add(matrix[j][bottomRightX]);
        
        // case 3: Bottom row printed right to left
        for(int i=bottomRightX; i > topLeftX; i--)
            output.add(matrix[bottomRightY][i]);
        
        // case 4: Left columnn printed bottom to top
        for(int j=bottomRightY; j < topLeftY; j++)
            output.add(matrix[j][topLeftX]);
            
        spiralOrderRecursive(matrix, topLeftX+1, topLeftY-1, bottomRightX-1, bottomRightY+1, output);
        return;
    }
}