import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall {
    private int width;
    private Position position;

    public Wall(int width, int height) {
        this.width = width;
        this.position = new Position(width, height);
    }

    public void draw(TextGraphics txtGra) {
            txtGra.setForegroundColor(TextColor.Factory.fromString("#000000"));
            txtGra.enableModifiers(SGR.BOLD);
            txtGra.putString(new TerminalPosition(position.getX(), position.getY()), "*");

    }
}
