package gamemakerstudio_.misc;

import javax.swing.*;
import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class RecursiveDrawTest {
    private static final Color BACKGRND_1 = Color.green;
    private static final Color BACKGRND_2 = Color.MAGENTA;
    private static final Color FOREGRND_1 = Color.blue;
    private static final Color FOREGRND_2 = Color.RED;

    private static void createAndShowGui() {
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JSlider slider = new JSlider(50, 90, 65);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);

        JPanel southPanel = new JPanel();
        southPanel.add(new JLabel("Percent Size Reduction:"));
        southPanel.add(slider);
        southPanel.add(new JButton(new AbstractAction("Create Recursive Image") {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    double scale = slider.getValue() / 100.0;
                    BufferedImage img = createRecursiveImg(scale);
                    ImageIcon icon = new ImageIcon(img);
                    JLabel label = new JLabel(icon);

                    Window win = SwingUtilities.getWindowAncestor(mainPanel);
                    JDialog dialog = new JDialog(win, "Image", ModalityType.MODELESS);
                    dialog.getContentPane().add(label);
                    dialog.pack();
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        }));

        mainPanel.add(new JScrollPane(new JLabel(new ImageIcon(createLabelImg()))),
                BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.PAGE_END);

        JFrame frame = new JFrame("RecursiveDrawTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // create a background image to display in a JLabel so that the GUI
    // won't be boring.
    private static BufferedImage createLabelImg() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (5 * d.width) / 6;
        int height = (5 * d.height) / 6;
        BufferedImage img = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(new GradientPaint(0, 0, BACKGRND_1, 40, 40, BACKGRND_2, true));
        g2.fillRect(0, 0, width, height);
        g2.setPaint(new GradientPaint(0, height, FOREGRND_1, 40, height - 40, FOREGRND_2, true));
        g2.fillOval(0, 0, 2 * width, 2 * height);
        g2.dispose();
        return img;
    }

    // non-recursive image to get the initial image that will be drawn recursively
    public static BufferedImage createRecursiveImg(double scale) throws AWTException {
        Robot robot = new Robot();
        Dimension screenSz = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRect = new Rectangle(screenSz);
        BufferedImage img = robot.createScreenCapture(screenRect);
        Graphics g = img.getGraphics();
        recursiveDraw(img, g, scale); // call recursive method
        g.dispose();
        return img;
    }

    // recursive method to draw image inside of image
    private static void recursiveDraw(BufferedImage img, Graphics g, double scale) {
        int w = img.getWidth();
        int h = img.getHeight();

        int smlW = (int)(w * scale);
        int smlH = (int)(h * scale);
        // criteria to end recursion
        if (smlW <= 1 || smlH <= 1) {
            return;
        }

        BufferedImage smlImg = new BufferedImage(smlW, smlH, BufferedImage.TYPE_INT_ARGB);
        Graphics smlG = smlImg.getGraphics();
        // draw big image in little image, scaled to little image
        smlG.drawImage(img, 0, 0, smlW, smlH, null);

        // recursive call
        recursiveDraw(smlImg, smlG, scale);
        smlG.dispose(); // clear resources when done with them

        // these guys center the smaller img on the bigger
        int smlX = (w - smlW) / 2;
        int smlY = (h - smlH) / 2;
        // draw small img on big img
        g.drawImage(smlImg, smlX, smlY, smlW, smlH, null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}
