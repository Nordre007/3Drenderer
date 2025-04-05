package renderer;

public class Matrix {
    double[][] values;

    public Matrix(double[][] values) {
        this.values = values;
    }

    public Matrix multiplication(Matrix m) {

        if(values[0].length != m.values.length) {
            throw new IllegalArgumentException("Kolumnerna och raderna stämmer inte överens i matrixarna");
        }
        
        double[][] result = new double[values.length][m.values[0].length];

        for(int row = 0; row < values.length; row++) {
            for(int col = 0; col < m.values[0].length; col++ ) {
                for(int k = 0; k < values[0].length; k++) {
                    result[row][col] += values[row][k] * m.values[k][col];
                }
            }
        }

        values = result;
        return new Matrix(result);
    }

    public Vertex transForm(Vertex a) {
        if(values.length != 3 || values[0].length != 3) {
            throw new IllegalArgumentException("Måste vara en 3x3 matris");
        }
        return new Vertex(
            a.x * values[0][0] + a.y * values[1][0] + a.z * values[2][0],
            a.x * values[0][1] + a.y * values[1][1] + a.z * values[2][1],
            a.x * values[0][2] + a.y * values[1][2] + a.z * values[2][2]
         );
    }


    public void printMatrix() {
        for(double[] a: values) {
            for(double b: a) {
                System.out.print(b + ", ");
            }
            System.out.println();
        }   
    }
}
