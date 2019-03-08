
public class RecursiveDeterminant {
  public static void main(String[] main) {
  //Test für die Methode detNxN mit einem 6x6 Matrix
   int[][] test1 = new int[][] {{3,4,1,9,14,18},{5,6,2,11,15,27},{1,3,4,12,17,21}
		                        ,{2,5,1,5,16,58},{2,15,18,91,107,501},{680,389,5,4,3,7}};
   int det = detNxN(test1);
   int expected1 = -68104560;
   if(det == expected1)
    System.out.println("Test für detNxN passed! Determinant: " +det);
   else
	System.out.println("Test für detNxN FAILED! "+det);
   
   //Test für die Methode detNxN mit einem 4x4 Matrix
   int[][] test2 = new int[][] {{11,2,5,4},{12,3,4,5},{5,1,3,7} ,{15,7,1,5}};
   int det2 = detNxN(test2);
   int expected2 = -112;
   if(det2 == expected2)
    System.out.println("Test für detNxN passed! Determinant: " +det2);
   else
    System.out.println("Test für detNxN FAILED! "+det2);
   
   //Test für die Methode det3x3
   int[][] test3 = new int[][] {{51,72,91},{17,56,12},{12,11,15}}; 
   int det3 = det3x3(test3);
   int expected3 = -16019;
   if(det3 == expected3)
	System.out.println("Test für det3x3 passed! Determinant: " +det3);
   else
	System.out.println("Test für det3x3 FAILED! "+det3);	   
   
   //Test für die Methode det2x2
   int[][] test4 = new int[][] {{51,75},{17,151}};
   int det4 = det2x2(test4);
   int expected4 = 6426;
   if(det4 == expected4)
     System.out.println("Test für det2x2 passed! Determinant: "+det4);
   else
	 System.out.println("Test für det2x2 FAILED! "+det4);  
   
  }
  public static int det2x2(int[][] matrix) {
    int a = matrix[0][0] * matrix[1][1];
    int b = matrix[0][1] * matrix[1][0];
    int det = a - b;
    return det;
  }
  public static int det3x3(int[][] matrix) {
   int det = 0;
   for(int j = 0; j < 3; j++) {
   int x = matrix[0][j];
   int[][] temp = new int[3][2];
   for(int a = 0; a < temp.length;a++) {
 	for(int b = 0; b < temp[0].length; b++)
 	  temp[a][b] = removeColumn(matrix,j)[a][b];
    }
   int[][] temp2 = new int[2][2];
   int z = 1;
   for(int e = 0; e < temp2.length; e++) {
    for(int f = 0; f < temp2.length; f++)
      temp2[e][f] = temp[z][f];
       z++;
     }
   if(j % 2 == 0)
    x = x* 1;
   else if(j % 2 != 0)
    x = x*(-1);
   det += x * det2x2(temp2);
   }
   return det;
  }
  public static int[][] removeRow(int[][] matrix, int rowIndex){
	int length = matrix.length;
    int[][] newMatrix = new int[length-1][length];
    int x = 0;
    for(int i = 0; i < length; i++) {	
      if(i == rowIndex) {
    	if(i == length -1)
    	  break;
        i++;
      }
      for(int j = 0; j < length; j++) {
        newMatrix[x][j] = matrix[i][j];	  
      }
      x++;
    }
    return newMatrix;
  }
  public static int[][] removeColumn(int[][] matrix, int colIndex){
    int length = matrix.length;
	int[][] newMatrix = new int[length][length-1];
	for(int i = 0; i < length; i++) {
	  int x = 0;
	  for(int j = 0; j < length; j++) {
	    if(j == colIndex) {
	      if(j == length-1)
	        break;
	     j++;	
	    }
	    newMatrix[i][x] = matrix[i][j];
	    x++;
	   }
	 }
	return newMatrix;  	  
  }
  //Um die Determinante einer n*n Matrix zu berechnen, benutze ich die detNxN Methode.
  //Ich berechne die Determinante, wie gleich ich bei 3*3-Matrix berechnet habe.
  //Aber diesmal wird eine n*n Matrix gegeben.
  //Die Methode berechnet jeweils 2x2-Matrixen für jede Zeile 0 Elemente, wenn die Methode die Matrix jemals
  //als n-1*n-1 Matrix verkleinert.
  public static int detNxN(int[][] matrix) {
	int det = 0;
	if(matrix.length > 2) {
	  int i = 0;	
	  while(i < matrix.length) {
	    int x = matrix[0][i];
        int[][] temp = new int[matrix.length][matrix.length-1];
        for(int a = 0; a < temp.length;a++) {
 	      for(int b = 0; b < temp[0].length; b++)
 	      temp[a][b] = removeColumn(matrix,i)[a][b];
         }
        int[][] temp2 = new int[matrix.length-1][matrix.length-1];
        int z = 1;
        for(int e = 0; e < temp2.length; e++) {
          for(int f = 0; f < temp2.length; f++)
            temp2[e][f] = temp[z][f];
             z++;
           }
        if(i % 2 == 0)
          x = x* 1;
        else if(i % 2 != 0)
          x = x*(-1);
        det += x * detNxN(temp2); //Rufe die Methode sich selbst
        i++;
	    }
	  }
	if(matrix.length == 2) {
	  det += det2x2(matrix); //Für jeder erste Zeile Elemente berechnet die Methode 2x2 Matrix Determinante	
	}
   return det;
  }
}
