package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Main {

    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Gameplay gamePlay = new Gameplay();

        obj.setBounds(10, 10, 800, 630);
        obj.setTitle("Tank Wars Game");
        obj.setBackground(Color.black);
        obj.setResizable(false);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Show the welcome dialog
        showWelcomeDialog(obj);

        obj.add(gamePlay);
        obj.setVisible(true);
    }
    
    public static void showWelcomeDialog(JFrame parentFrame) {
        final JDialog dialog = new JDialog(parentFrame, "Welcome", true);
        dialog.setContentPane(new BackgroundPanel()); // Use the custom JPanel as content pane
        dialog.setLayout(null);

        String message = "Welcome to Tank Wars Game!";
        JLabel label = new JLabel(message);
        label.setForeground(Color.white); // Set the message color to white
        label.setFont(new Font("Arial", Font.PLAIN, 21)); // Set the font size to 24
        label.setBounds(250, 290, 300, 50); // Adjust the position to center the label
        dialog.add(label);

        // Set the dialog position and size
        dialog.setBounds(10, 10, 800, 630); // Set the dimensions to 800x630

        // Add KeyListener to the dialog to dispose it when any key is pressed
        dialog.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Close the dialog when any key is pressed
                dialog.dispose();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });

        dialog.setFocusable(true); // Make sure the dialog can receive focus for key events

        // Create a Timer to close the dialog after 5 seconds
        int delay = 5000; // 5 seconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Close the dialog after 5 seconds
                dialog.dispose();
            }
        };
        Timer timer = new Timer(delay, taskPerformer);
        timer.setRepeats(false); // Only fire once
        timer.start();

        dialog.setVisible(true);
    }


 // Custom JPanel for displaying the background image
    @SuppressWarnings("serial")
	static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                // Provide the complete path to the image file
//            	backgroundImage = ImageIO.read(new File("Recourses\\best-tank-games.jpg"));
            	backgroundImage = ImageIO.read(getClass().getResource("/best-tank-games.jpg"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(800, 630); // Set the preferred dimension for the panel
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                // Calculate the position to center the image
                int x = (getWidth() - backgroundImage.getWidth(this)) / 2;
                int y = (getHeight() - backgroundImage.getHeight(this)) / 2;
                g.drawImage(backgroundImage, x, y, this);
            }
        }
}}


