package model;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author whiwf
 */
public class DrawPoint implements Serializable{
    
    private static final long serialVersionUID = 6529685098267757690L;
    
    int tool, x1, y1, x2, y2;
    Color color;

    public DrawPoint(int tool, int x1, int y1, int x2, int y2, Color color) {
        this.tool = tool;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public int getTool() {
        return tool;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public Color getColor() {
        return color;
    }

    
}
