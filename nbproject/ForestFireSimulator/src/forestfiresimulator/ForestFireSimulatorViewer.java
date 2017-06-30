package forestfiresimulator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import sun.applet.Main;

/**
 * A frame with a LifeComopnent and a number of GUI controls for watching Life
 * evolve.
 *
 * @author sm52192
 * @vesion Apr 15, 2014
 */
public class ForestFireSimulatorViewer extends JFrame {

    private final ForestFireSimulatorComponent fireComp;

    public ForestFireSimulatorViewer(int n, double g, double l) {
        fireComp = new ForestFireSimulatorComponent(n, g, l);
        fireComp.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLACK));
        add(fireComp, BorderLayout.CENTER);

        setTitle("Foerest Fire Simulator");
        add(getSouthPanel(), BorderLayout.SOUTH);
        add(getNorthPanel(), BorderLayout.NORTH);

        // menus 
        JMenuBar mBar = new JMenuBar();
        setJMenuBar(mBar);
        mBar.add(getInitConfigMenus());

    }

    public static void main(String[] args) {
        ForestFireSimulatorViewer viewer = new ForestFireSimulatorViewer(100, 0.5, 0.00005);
        viewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewer.setSize(750, 720);

        viewer.setAlwaysOnTop(true);
        viewer.setVisible(true);

    }

    /**
     * @return a panel with buttons
     */
    private Component getSouthPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(7, 0, 7, 0));
        final JButton runButton = new JButton("Run");
        final JButton stopButton = new JButton("Stop");
        final JButton clearButton = new JButton("Clear");
        panel.add(runButton);
        panel.add(stopButton);
        panel.add(clearButton);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object src = e.getSource();
                if (src == stopButton) {
                    fireComp.stop();
                }
                if (src == runButton) {
                    fireComp.run();
                }
                if (src == clearButton) {
                    fireComp.clear();
                }
            }

        };

        stopButton.addActionListener(listener);
        runButton.addActionListener(listener);
        clearButton.addActionListener(listener);

        return panel;
    }

    /**
     * @return a panel with controls
     */
    private Component getNorthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));
        //panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(new TitledBorder(
                new EtchedBorder(), "Probaility of Growth"));

        JPanel rightPanel = new JPanel();
        rightPanel.setBorder(new TitledBorder(
                new EtchedBorder(), "Probaility of Lightning"));

        String[] rs = {"0.1", "0.2", "0.3", "0.4", "0.5",
            "0.6", "0.7", "0.8", "0.9", "1.0"};
        final JComboBox rbox = new JComboBox(rs);

        String[] ls = {"0.00001", "0.00002", "0.00003", "0.00004",
            "0.00005", "0.00006", "0.00007", "0.00008", "0.00009"};
        final JComboBox lbox = new JComboBox(ls);

        panel.add(leftPanel);
        panel.add(rightPanel);
        leftPanel.add(rbox);
        rightPanel.add(lbox);

        lbox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) lbox.getSelectedItem();
                switch (s) {
                    case "0.00001":
                        fireComp.setpLightning(0.00001);
                        break;
                    case "0.00002":
                        fireComp.setpLightning(0.00002);
                        break;
                    case "0.00003":
                        fireComp.setpLightning(0.00003);
                        break;
                    case "0.00004":
                        fireComp.setpLightning(0.00004);
                        break;
                    case "0.00005":
                        fireComp.setpLightning(0.00005);
                        break;
                    case "0.00006":
                        fireComp.setpLightning(0.00006);
                        break;
                    case "0.00007":
                        fireComp.setpLightning(0.00007);
                        break;
                    case "0.00008":
                        fireComp.setpLightning(0.00008);
                        break;
                    case "0.00009":
                        fireComp.setpLightning(0.00009);
                        break;
                }
            }
        });

        rbox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) rbox.getSelectedItem();
                switch (s) {
                    case "0.1":
                        fireComp.setpGrowth(0.1);
                        break;
                    case "0.2":
                        fireComp.setpGrowth(0.2);
                        break;
                    case "0.3":
                        fireComp.setpGrowth(03);
                        break;
                    case "0.4":
                        fireComp.setpGrowth(0.4);
                        break;
                    case "0.5":
                        fireComp.setpGrowth(0.5);
                        break;
                    case "0.6":
                        fireComp.setpGrowth(0.6);
                        break;
                    case "0.7":
                        fireComp.setpGrowth(0.7);
                        break;
                    case "0.8":
                        fireComp.setpGrowth(0.8);
                        break;
                    case "0.9":
                        fireComp.setpGrowth(0.9);
                        break;
                    case "1.0":
                        fireComp.setpGrowth(1.0);
                        break;
                }
            }
        });
        return panel;
    }

    /**
     * @return a menu with one item
     */
    private JMenu getInitConfigMenus() {
        JMenu menu = new JMenu("Options");

        JMenuItem[] menuItems = {
            new JMenuItem("Save")};

        menu.add(menuItems[0]);

        menuItems[0].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "" + fireComp.getpGrowth()
                        + fireComp.getpLightning();
                ObjectOutputStream out;
                final String FILENAME = "MyFile.txt";

                try {
                    out = new ObjectOutputStream(new FileOutputStream(FILENAME));

                    out.writeObject(s);
                    out.close();

                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return menu;
    }

}
