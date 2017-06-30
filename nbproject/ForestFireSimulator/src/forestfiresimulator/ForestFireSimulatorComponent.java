package forestfiresimulator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author Sadan Mallhi
 * @version May 5, 2014
 */
public class ForestFireSimulatorComponent extends JComponent {

    private ForestFireSimulator game;

    // fields for continuouse updating
    private boolean inContinuouseMode;
    private int delay;
    public static final int SLOW = 500;
    public static final int FAST = 10;

    // for drawing
    private static final int LINE_THICKNESS = 1;
    private static final int OFFSET = 15;
    private static final Color bgColor = Color.WHITE;
    private static final Color deadColor = Color.WHITE;
    private static final Color treeColor = Color.GREEN;
    private static final Color fireColor = Color.RED;
    private static final Color lineColor = Color.GRAY;

    private int cellWidth;
    private int cellHeight;

    public ForestFireSimulatorComponent(int n, double g, double l) {
        game = new ForestFireSimulator(n, g, l);
        inContinuouseMode = false;
        delay = FAST;

        MouseAdapter myAdapter = getMyMouseAdapter();
        addMouseListener(myAdapter);
        addMouseMotionListener(myAdapter);
    }

    private MouseAdapter getMyMouseAdapter() {
        class Listener extends MouseAdapter {

            @Override
            public void mouseEntered(MouseEvent e) {
                delay = FAST;
                //System.out.println("Entered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                delay = SLOW;
                //System.out.println("Exited");
            }

        }
        return new Listener();
    }

    /**
     *
     * @param g the current graphics content
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // fill in background
        g2.setColor(bgColor);
        int w = getWidth();
        int h = getHeight();
        g2.fill(new Rectangle(w, h));

        // draw the grid
//        g2.setStroke(new BasicStroke(LINE_THICKNESS));
        final int N = game.getSize();
        cellWidth = (w - 2 * OFFSET) / N;
        cellHeight = (h - 2 * OFFSET) / N;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                // find top-left corner of cell(i,j)
                int a = OFFSET + j * cellWidth;
                int b = OFFSET + i * cellHeight;

                if (game.isBurning(i, j)) {
                    g2.setColor(fireColor);

                }
                if (game.isEmpty(i, j)) {
                    g2.setColor(deadColor);
                }
                if (game.isFire(i, j)) {
                    g2.setColor(fireColor);
                }
                if (game.isTree(i, j)) {
                    g2.setColor(treeColor);
                } else {
                    g2.setColor(bgColor);
                }
                g2.fillRect(a, b, cellWidth, cellHeight);
                //g2.setColor(lineColor);
                //g2.drawRect(a, b, cellWidth, cellHeight);

            }
        }

        if (inContinuouseMode) {
            try {
                game.next();
                Thread.sleep(delay);
                repaint();
            } catch (InterruptedException ex) {
                Logger.getLogger(
                        ForestFireSimulatorComponent.class.getName()).log(Level.SEVERE,
                                null, ex);
            }
        }
    }

    public void clear() {
        game.clear();
        inContinuouseMode = false;
        repaint();
    }

    public void run() {
        inContinuouseMode = true;
        game.next();
        repaint();
    }

    public void stop() {
        inContinuouseMode = false;
    }

    public void next() {
        game.next();
        inContinuouseMode = false;
        repaint();

    }

    public double getpGrowth() {
        return game.getpGrowth();

    }

    public double getpLightning() {
        return game.getpLightning();
    }

    public void setpGrowth(double pGrowth) {
        game.setpGrowth(pGrowth);
    }

    public void setpLightning(double pLightning) {
        game.setpLightning(pLightning);
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(750, 680);
//
//        frame.add(new ForestFireSimulatorComponent(100, 0.5, 0.00005));
//
//        frame.setAlwaysOnTop(true);
//        frame.setVisible(true);
//    }
}
