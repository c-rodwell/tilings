package core;

import display.GraphicsPanel;
import java.awt.*;

public interface Drawable {
    void draw(Graphics g, GraphicsPanel gp); //not sure if it should take a color arg, or use color attribute of the object
}
