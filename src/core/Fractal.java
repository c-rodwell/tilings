package core;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

//part for a fractal iteration: can be drawn, or split into smaller parts
public interface Fractal extends Drawable{
    HashMap getParts();
}
