package it.unibo.oop.lab.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();

    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 1) I has a main method that starts the graphical application
     * 
     * 2) In its constructor, sets up the whole view
     * 
     * 3) The graphical interface consists of a JTextField in the upper part of the frame, 
     * a JTextArea in the center and two buttons below it: "Print", and "Show history". 
     * SUGGESTION: Use a JPanel with BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The behavior of the program is that, if "Print" is pressed, the
     * controller is asked to show the string contained in the text field on standard output. 
     * If "show history" is pressed instead, the GUI must show all the prints that
     * have been done to this moment in the text area.
     * 
     */

    /**
     * builds a new {@link SimpleGUI}.
     */
    public SimpleGUI() {

        /*
         * Make the frame half the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected.
         * 
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        final Controller controller = new ControllerImpl();
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        JTextField inputString = new JTextField();
        inputString.setLayout(new BorderLayout());
        JTextArea output = new JTextArea();
        output.setLayout(new BorderLayout());
        final JPanel buttonsCanvas = new JPanel();
        buttonsCanvas.setLayout(new BorderLayout());
        final JButton print = new JButton("Print");
        print.setLayout(new BorderLayout());
        final JButton history = new JButton("Show History");
        history.setLayout(new BorderLayout());
        buttonsCanvas.add(print, BorderLayout.CENTER);
        buttonsCanvas.add(history, BorderLayout.EAST);
        canvas.add(inputString, BorderLayout.NORTH);
        canvas.add(output, BorderLayout.CENTER);
        canvas.add(buttonsCanvas, BorderLayout.SOUTH);
        frame.add(canvas);
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.setString(inputString.getText().isEmpty() ? null : inputString.getText());
                    controller.printString();
                } catch (IllegalStateException e1) {
                    System.out.println("Can't pass an empty string");
                }
                
            }
        });
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                for (int i = 0; i < controller.getHistory().size(); i++) {
                    output.append(i + ") " + controller.getHistory().get(i) + "\n");
                }
            }
        });
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        new SimpleGUI();
    }

}
