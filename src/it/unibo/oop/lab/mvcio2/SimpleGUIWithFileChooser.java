package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    
    private final JFrame frame = new JFrame();

    public SimpleGUIWithFileChooser() {
        final Controller controller = new Controller();
        controller.setCurrentFile("output.txt");
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SimpoleGUIWithFileChooser");
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextField browseTextField = new JTextField();
        browseTextField.setText(controller.getPath());
        browseTextField.setEditable(false);
        final JButton browse = new JButton("Browse");
        browse.setLayout(new BorderLayout());
        canvas.add(browseTextField, BorderLayout.CENTER);
        canvas.add(browse, BorderLayout.LINE_END);
        final JTextArea textArea = new JTextArea();
        final JButton save = new JButton("Save");
        save.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.NORTH);
        frame.add(textArea);
        frame.add(save, BorderLayout.SOUTH);
        /*
         * Handlers
         */
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.write(textArea.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                try {
                    if (fileChooser.showSaveDialog(browseTextField) == JFileChooser.APPROVE_OPTION) {
                        controller.setCurrentFile(fileChooser.getName(fileChooser.getSelectedFile()));
                        browseTextField.setText(controller.getPath());
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(fileChooser, e1);
                }
            }
        });
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    
    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser();
    }

}
