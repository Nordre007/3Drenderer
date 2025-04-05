package renderer;


public class Main {
    public static void main(String[] args) {
        new Window();
        double[][] a = {
            {1, 2, 3},
        };
        double[][] b = {
            {2, 0, 0}, 
            {0, 2, 0},
            {0, 0, 2}
        };

        Matrix first = new Matrix(a);
        Matrix second = new Matrix(b);
        first.multiplication(second).printMatrix();

    }


}