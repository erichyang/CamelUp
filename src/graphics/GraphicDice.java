package graphics;

import core.Dice;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;


public class GraphicDice
{
    private Point pos;
    private Dice dice;
    private HashMap<String, Color> colorHashMap;

    public GraphicDice(Point point, Dice dice)
    {
        pos = point;
        this.dice = dice;
        colorHashMap = new HashMap<>();
        colorHashMap.put("yellow", Color.yellow);
        colorHashMap.put("green", Color.green);
        colorHashMap.put("blue", Color.blue);
        colorHashMap.put("orange", Color.orange);
        colorHashMap.put("white", Color.white);


    }

    public void draw(Graphics2D graphics2D) {

        int x = pos.x;
        int y = pos.y;

        graphics2D.setColor(colorHashMap.get(dice.getColor()));
        graphics2D.fill(new Rectangle(x, y, 100, 100));
        graphics2D.setPaint(Color.BLACK);
        int thickness = 3;
        Stroke oldStroke = graphics2D.getStroke();
        graphics2D.setStroke(new BasicStroke(thickness));
        graphics2D.drawRect(x, y, 100, 100);



        switch (dice.getDieFace()) {

            case 1:

                graphics2D.fillOval(x + 43, y + 43, 15, 15);
                break;

            case 2:

                graphics2D.fillOval(x+18, y+68, 15, 15);
                graphics2D.fillOval(x+68, y+18, 15, 15);
                break;

            case 3:

                graphics2D.fillOval(x+18, y+68, 15, 15);
                graphics2D.fillOval(x+43, y+43, 15, 15);
                graphics2D.fillOval(x+68, y+18, 15, 15);
                break;

            default:
                break;
        }
        graphics2D.setStroke(oldStroke);
    }
}