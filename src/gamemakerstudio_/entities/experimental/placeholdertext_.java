package gamemakerstudio_.entities.experimental;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.ID;
import gamemakerstudio_.misc.gameobject_;
import gamemakerstudio_.misc.textstuff.TextAlignment;
import gamemakerstudio_.misc.textstuff.TextFormat;
import gamemakerstudio_.misc.textstuff.TextRenderer;

import java.awt.*;

public class placeholdertext_ extends gameobject_ {
    public String text;
    private TextAlignment align;
    public placeholdertext_(float x, float y, ID id, String text) {
        super(x, y, id);
        this.text = text;
        width = game_.WIDTH;
        height = game_.HEIGHT;
        align = TextAlignment.TOP_LEFT;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setFont(new Font("Consolas", 0, 16));
        g.setColor(Color.GREEN);
        g.drawRect((int)x, (int)y, width, height);

        Rectangle bounds;

        bounds = new Rectangle(10, 10, getWidth() - 20, getHeight() - 20);
        bounds = TextRenderer.drawString(g, this.text, g.getFont(), Color.GREEN, bounds, align, TextFormat.FIRST_LINE_VISIBLE);

//        g.setColor(Color.red);
//        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }
}
