package renderer;

import java.awt.Color;

public class Triangle {
    Vertex v1;
    Vertex v2;
    Vertex v3;
    Color color;

    public Triangle(Vertex x, Vertex y, Vertex z, Color color) {
        this.v1 = x;
        this.v2 = y;
        this.v3 = z;
        this.color = color;
    }
}
