package gamemakerstudio_.entities.experimental;

import gamemakerstudio_.entities.basecircle_;
import gamemakerstudio_.entities.circlewithpatterns_;
import gamemakerstudio_.game_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.MathUtil;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class bullethellgenerator_ extends gameobject_ implements KeyListener, MouseListener, ActionListener {
    handler_ handler;

    int defaultSpawnTimer;
    Random r = new Random();

    // init math old codes
    public gameobject_ startPoint;
    public double numberOfProjectiles = 5;
    public double projectileSpeed = 3;
    private double radius = 1;

    // angle
    double angleStep, angle = 0;

    // rewrite the whole code

    // spin variables
    double spinRate = 0;
    double maxSpinRate = 0;
    double spinModificator = 1;
    boolean invertSpin = false;
    boolean tickOnce = false;

    // direction and curve
    double bulletCurve = 0;
    double direction;

    // dev stuff
    JFrame frameCustom = new JFrame();
    JPanel customizePanel;
    JLabel spawnTimerLabel, noProjLabel, projSpeedLabel, spinRateLabel,
            maxSpinRateLabel, spinModificatorLabel, invertSpinLabel, tickOnceLabel, nullLabel;
    JTextField st, np, ps, sr, msr, sm, is, to;
    JButton OK = new JButton("OK");

    private void performCommands() {
        String stBuffer = st.getText();
        String npBuffer = np.getText();
        String psBuffer = ps.getText();
        String srBuffer = sr.getText();
        String msrBuffer = msr.getText();
        String smBuffer = sm.getText();
        String isBuffer = is.getText();
        String toBuffer = to.getText();
        this.defaultSpawnTimer = Integer.parseInt(stBuffer);
        this.numberOfProjectiles = Double.parseDouble(npBuffer);
        this.projectileSpeed = Double.parseDouble(psBuffer);
        this.spinRate = Double.parseDouble(srBuffer);
        this.maxSpinRate = Double.parseDouble(msrBuffer);
        this.spinModificator = Double.parseDouble(smBuffer);
        this.invertSpin = Boolean.parseBoolean(isBuffer);
        this.tickOnce = Boolean.parseBoolean(toBuffer);
    }

    // FIXME: YOW THAT'S A LOTTA ARGS!
    public bullethellgenerator_(float x, float y, ID id,
                                handler_ handler, game_ game,
                                int spawnTimer, double numberOfProjectiles,
                                double projectileSpeed, double spinRate,
                                double maxSpinRate, double spinModificator,
                                boolean invertSpin, boolean tickOnce) {
        super(x, y, id);

        // init classes
        this.handler = handler;

        // init local vars
        this.defaultSpawnTimer = spawnTimer;
        this.spawnTimer = spawnTimer;
        this.numberOfProjectiles = numberOfProjectiles;
        this.projectileSpeed = projectileSpeed;
        this.spinRate = spinRate;
        this.maxSpinRate = maxSpinRate;
        this.spinModificator = spinModificator;
        this.invertSpin = invertSpin;
        this.tickOnce = tickOnce;

        // init frame labels
        spawnTimerLabel = new JLabel("Default Spawn Timer");
        noProjLabel = new JLabel("Number of Projectiles");
        projSpeedLabel = new JLabel("Projectile Speed");
        spinRateLabel = new JLabel("Spin Rate");
        maxSpinRateLabel = new JLabel("Max Spin Rate");
        spinModificatorLabel = new JLabel("Spin Modifier");
        invertSpinLabel = new JLabel("Invert Spin");
        tickOnceLabel = new JLabel("Tick Once");
        nullLabel = new JLabel("");

        // init text fields
        st = new JTextField(String.valueOf(this.defaultSpawnTimer));
        np = new JTextField(String.valueOf(this.numberOfProjectiles));
        ps = new JTextField(String.valueOf(this.projectileSpeed));
        sr = new JTextField(String.valueOf(this.spinRate));
        msr = new JTextField(String.valueOf(this.maxSpinRate));
        sm = new JTextField(String.valueOf(this.spinModificator));
        is = new JTextField(String.valueOf(this.invertSpin));
        to = new JTextField(String.valueOf(this.tickOnce));

        // set js
        customizePanel = new JPanel(new GridLayout(9, 0));
        customizePanel.add(spawnTimerLabel);
        customizePanel.add(st);
        customizePanel.add(noProjLabel);
        customizePanel.add(np);
        customizePanel.add(projSpeedLabel);
        customizePanel.add(ps);
        customizePanel.add(spinRateLabel);
        customizePanel.add(sr);
        customizePanel.add(maxSpinRateLabel);
        customizePanel.add(msr);
        customizePanel.add(spinModificatorLabel);
        customizePanel.add(sm);
        customizePanel.add(invertSpinLabel);
        customizePanel.add(is);
        customizePanel.add(tickOnceLabel);
        customizePanel.add(to);
        customizePanel.add(nullLabel);
        customizePanel.add(OK);

        // jframe init
        frameCustom.setTitle(String.valueOf(this).replace("gamemakerstudio_.entities.experimental.", ""));
        frameCustom.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameCustom.add(customizePanel);
        frameCustom.setSize(400, 300);

        // init input
        game.addKeyListener(this);
        game.addMouseListener(this);

        st.addActionListener(this);
        np.addActionListener(this);
        ps.addActionListener(this);
        sr.addActionListener(this);
        msr.addActionListener(this);
        sm.addActionListener(this);
        is.addActionListener(this);
        to.addActionListener(this);

        // summon base
        handler.addObject(new basecircle_(this.x, this.y, ID.BASECIRCLEGHOST, handler, 0, 0, 0));
        for (int i = 0; i < handler.object.size(); i++) {
            gameobject_ tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.BASECIRCLEGHOST)
                startPoint = tempObject;
        }
    }

    @Override
    public void tick() {
        nullLabel.setText("Spin Rate: " + spinRate);
        if (spawnTimer == 0) {
            spawnTimer = defaultSpawnTimer;
            angleStep = 360 / numberOfProjectiles;
            for (int i = 0; i <= numberOfProjectiles; i++) {
                double projectileDirXPosition = (((double) startPoint.getX() + 10) + Math.sin((angle * Math.PI) / 180) * radius);
                double projectileDirYPosition = (((double) startPoint.getY() + 10) + Math.cos((angle * Math.PI) / 180) * radius);

                double projectileMoveDirectionX = (projectileDirXPosition - ((double) startPoint.getX() + 10)) * projectileSpeed;
                double projectileMoveDirectionY = (projectileDirYPosition - ((double) startPoint.getY() + 10)) * projectileSpeed;

                // TODO: direction and curve, tbd
                // where's the god damn code???

                // spawn bullets
                handler.addObject(new circlewithpatterns_((int) projectileDirXPosition, (int) projectileDirYPosition,
                        ID.CircleWithPatterns, handler, (float)projectileMoveDirectionX, (float)projectileMoveDirectionY));
                angle += angleStep;
            }

            angle += spinRate; //Make the pattern spin
            spinRate += spinModificator; //Apply the spin modifier

            //Invert the spin
            if (invertSpin) {
                if (spinRate < -maxSpinRate || spinRate > maxSpinRate) {
                    spinModificator = -spinModificator;
                }
            }

            // clamp the spin rate to max spin rate
            if (maxSpinRate != 0)
                spinRate = MathUtil.clampDouble(spinRate, -maxSpinRate, maxSpinRate);

            if (tickOnce) {
                handler.removeObject(this.startPoint);
                frameCustom.dispose();
                handler.removeObject(this);
            }
        } else {
            spawnTimer--;
        }
    }
    
    // the impossible math, do not use this at all cost
    private void SpawnProjectile (int _numberOfProjectiles) {
        angleStep = 360f / _numberOfProjectiles;
        angle = 0f;

        for (int i = 0; i <= _numberOfProjectiles; i++) {
            double projectileDirXPosition = (float)((startPoint.getX() + 10) + Math.sin((angle * Math.PI) / 180) * radius);
            double projectileDirYPosition = (float)((startPoint.getY() + 10) + Math.cos((angle * Math.PI) / 180) * radius);

            double projectileMoveDirectionX = (projectileDirXPosition - (startPoint.getX() + 10)) * projectileSpeed;
            double projectileMoveDirectionY = (projectileDirYPosition - (startPoint.getY() + 10)) * projectileSpeed;

            handler.addObject(new circlewithpatterns_((int)projectileDirXPosition, (int)projectileDirYPosition,
                    ID.CircleWithPatterns, handler, (float)projectileMoveDirectionX, (float)projectileMoveDirectionY));
            angle += angleStep;
        }

    }

    @Override
    public void render(Graphics g) {}

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (mouseOver(mx, my, (int)startPoint.getX(), (int)startPoint.getY(), startPoint.getWidth(), startPoint.getHeight())) {
            this.frameCustom.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        performCommands();
    }
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;
    }
}
