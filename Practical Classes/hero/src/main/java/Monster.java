import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster {

    private int width;
    private int height;
    private Position position;

    public Monster(int width, int height) {
        this.width = width;
        this.position = new Position(width, height);
    }

    public Position getPosition() {
        return position;
    }

    public Position move() {
        Position pos = getPosition();
        Random random = new Random();
        int toSelect = random.nextInt(5);
        switch (toSelect) {
            case 1:
                if (pos.getX() < 78) pos.setX(pos.getX()+1);
                break;
            case 2:
                if (pos.getX() > 1) pos.setX(pos.getX()-1);
                break;
            case 3:
                if (pos.getY() < 22) pos.setY(pos.getY()+1);
                break;
            case 4:
                if (pos.getY() > 1) pos.setY(pos.getY()-1);
                break;
            default:
                break;
        }
        return pos;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void draw(TextGraphics txtGra) {
        txtGra.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        txtGra.enableModifiers(SGR.BOLD);
        txtGra.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }
}
