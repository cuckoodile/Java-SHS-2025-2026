package game.pkg1;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Ian Sube
  
  1. Character movements
	- Horizontal 
		- Left {a || A || Left Arrow}
		- Right {d || D || Right Arrow}
	- Vertical
		- Jump (Up) {Space}
		- Fast fall (Only if mid air) {s || S}
2. Physics
	- Gravity
	- Velocity
	- Collisions
		- Collision Event 1: Platform to Character
		- COllision Event 2: Character to Food
                
 */
public class Form extends javax.swing.JFrame implements KeyListener {

    // === MOVEMENT VARIABLES ===
    private int playerX;    // Character Horizontal Postion
    private int playerY;    // Character Vertical Position
    
    // Starting position
    private final int originalPlayerY;  
    private final int originalPlayerX;

    private int vy = 0; // Vertical Velocity

    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean downPressed = false;
    private boolean onGround = false;   // Usually held on true

    private final int playerSpeed = 4;
    private final int gravity = 1;         // Strength of gravity
    private final int jumpStrength = -20;  // How powerful the jump is
    private final int fastFall = 5;        // Extra speed when holding S in air

    private final Timer gameTimer;

    private final List<Rectangle> platforms = new ArrayList<>();

    public Form() {
        initComponents();

        // Main Frame setup
        setTitle("Character Movement Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Character Position Setup
        playerX = playerLabel.getX();
        playerY = playerLabel.getY();
        
        /*
        playerX and playerY only change when:
            1. A movement key was pressed
            2. Due to gravity
        */
        
        originalPlayerX = playerLabel.getX();
        originalPlayerY = playerLabel.getY();

        // Platform Setup
        platforms.add(groundPlatform.getBounds());
        platforms.add(goalPlatform.getBounds());
        platforms.add(platform1.getBounds());
        platforms.add(platform2.getBounds());
        platforms.add(platform3.getBounds());
        platforms.add(platform4.getBounds());
        platforms.add(platform5.getBounds());
        platforms.add(platform6.getBounds());
        platforms.add(platform7.getBounds());
        platforms.add(platform8.getBounds());

        // Keyboard Setup
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        gamePanel.addKeyListener(this);

        // Timer for FPS Setup
        gameTimer = new Timer(16, e -> updateMovement());   // 16ms ≈ 60 FPS
            // time in ms, event or function (what to do when the time ends)
        gameTimer.start();
    }

    private void updateMovement() {
        // 1. Horizontal Movement (A / D)
        if (leftPressed) {
            playerX -= playerSpeed;
        }
        if (rightPressed) {
            playerX += playerSpeed;
        }
        // Keep player inside screen horizontally
        if (playerX < 0) {
            playerX = 0;
        }
        if (playerX > gamePanel.getWidth() - playerLabel.getWidth()) {
            playerX = gamePanel.getWidth() - playerLabel.getWidth();
        }

        // 2. Apply Gravity
        vy += gravity; // Gravity always pulls down
        /*
        >> vy = Vertical Velocity
        - Positive vy → character is moving down (falling)
        - Negative vy → character is moving up (jumping)
        - vy = 0 → character is not moving vertically (standing still or at the peak of a jump)
       
        >> Gravity
        - gravity is a small number (we set it to 1) that gets added to vy every single frame.
       
        > Because this line runs 60 times per second, gravity is constantly pushing vy to-
        become more positive → which makes the character fall faster and faster.
         */
        if (downPressed && !onGround) {
            vy += fastFall;
        }
        // Move player vertically using velocity
        playerY += vy;

        // 3. ADVANCED COLLISION WITH ALL PLATFORMS (Separate X and Y resolution)
        onGround = false;

        Rectangle playerRect = new Rectangle(playerX, playerY,
                playerLabel.getWidth(), playerLabel.getHeight());

        // First: Resolve Vertical Collisions (Top & Bottom)
        for (Rectangle plat : platforms) {
            if (playerRect.intersects(plat)) {

                // Landing on TOP of platform
                if (vy > 0 && (playerY + playerLabel.getHeight() - vy) <= plat.y + 10) {
                    playerY = plat.y - playerLabel.getHeight();
                    vy = 0;
                    onGround = true;
                } // Hitting HEAD on BOTTOM of platform (ceiling)
                else if (vy < 0 && playerY >= plat.y + plat.height - 15) {
                    playerY = plat.y + plat.height;
                    vy = 0;
                }
            }
        }

        // Update playerRect after vertical correction
        playerRect = new Rectangle(playerX, playerY,
                playerLabel.getWidth(), playerLabel.getHeight());

        // Second: Resolve Horizontal Collisions (Sides)
        for (Rectangle plat : platforms) {
            if (playerRect.intersects(plat)) {

                // Hit left side of platform
                if (playerX + playerLabel.getWidth() <= plat.x + 10) {
                    playerX = plat.x - playerLabel.getWidth();
                } // Hit right side of platform
                else if (playerX >= plat.x + plat.width - 10) {
                    playerX = plat.x + plat.width;
                }
            }
        }

        // Safety: prevent falling through the world
        if (playerY > gamePanel.getHeight()) {
            playerY = 100;
            vy = 0;
        }

        // 4. Update the yellow box position on screen
        playerLabel.setLocation(playerX, playerY);
        
        // 5. Reward/Finish Line
        Rectangle rewardRect = rewardLabel.getBounds();
        if (playerRect.intersects(rewardRect)) {
            gameTimer.stop();   // Pause the game while showing message

            JOptionPane.showMessageDialog(this,
                "CONGRATULATIONS! \n\nYou reached the gold reward!\n\nThe game will now reset.",
                "You Win!",
                JOptionPane.INFORMATION_MESSAGE);

            resetGame();
            resetKeyStates();
            gameTimer.start();
        }
    }
    
    private void resetGame() {
        playerX = originalPlayerX;
        playerY = originalPlayerY;
        vy = 0;
        onGround = false;
        playerLabel.setLocation(playerX, playerY);
    }
    
    private void resetKeyStates() {
        leftPressed = false;
        rightPressed = false;
        downPressed = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
//        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
//            upPressed = true;
//        }
        if (key == KeyEvent.VK_SPACE && onGround) {
            vy = jumpStrength;      // Negative value = jump upward
            onGround = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
//        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
//            upPressed = false;
//        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing - this prevents the exception
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gamePanel = new javax.swing.JPanel();
        playerLabel = new javax.swing.JLabel();
        groundPlatform = new javax.swing.JLabel();
        goalPlatform = new javax.swing.JLabel();
        platform1 = new javax.swing.JLabel();
        platform2 = new javax.swing.JLabel();
        platform3 = new javax.swing.JLabel();
        platform4 = new javax.swing.JLabel();
        platform5 = new javax.swing.JLabel();
        platform6 = new javax.swing.JLabel();
        platform7 = new javax.swing.JLabel();
        platform8 = new javax.swing.JLabel();
        rewardLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        gamePanel.setBackground(new java.awt.Color(153, 255, 255));
        gamePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        playerLabel.setBackground(new java.awt.Color(255, 255, 102));
        playerLabel.setMaximumSize(new java.awt.Dimension(40, 40));
        playerLabel.setMinimumSize(new java.awt.Dimension(40, 40));
        playerLabel.setOpaque(true);
        playerLabel.setPreferredSize(new java.awt.Dimension(40, 40));
        gamePanel.add(playerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 50, 46));

        groundPlatform.setBackground(new java.awt.Color(51, 255, 0));
        groundPlatform.setText("Floor Platform");
        groundPlatform.setOpaque(true);
        gamePanel.add(groundPlatform, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 573, 800, 27));

        goalPlatform.setBackground(new java.awt.Color(51, 255, 0));
        goalPlatform.setText("Goal Platform");
        goalPlatform.setOpaque(true);
        gamePanel.add(goalPlatform, new org.netbeans.lib.awtextra.AbsoluteConstraints(446, 79, 354, 27));

        platform1.setBackground(new java.awt.Color(51, 255, 0));
        platform1.setText("Platform 1");
        platform1.setOpaque(true);
        gamePanel.add(platform1, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 517, 143, 27));

        platform2.setBackground(new java.awt.Color(51, 255, 0));
        platform2.setText("Platform 2");
        platform2.setOpaque(true);
        gamePanel.add(platform2, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 457, 143, 30));

        platform3.setBackground(new java.awt.Color(51, 255, 0));
        platform3.setText("Platform 3");
        platform3.setOpaque(true);
        gamePanel.add(platform3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 395, 143, 27));

        platform4.setBackground(new java.awt.Color(51, 255, 0));
        platform4.setText("Platform 4");
        platform4.setOpaque(true);
        gamePanel.add(platform4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, 143, 27));

        platform5.setBackground(new java.awt.Color(51, 255, 0));
        platform5.setText("Platform 5");
        platform5.setOpaque(true);
        gamePanel.add(platform5, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 240, 143, 27));

        platform6.setBackground(new java.awt.Color(51, 255, 0));
        platform6.setText("Platform 6");
        platform6.setOpaque(true);
        gamePanel.add(platform6, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 166, 143, 27));

        platform7.setBackground(new java.awt.Color(51, 255, 0));
        platform7.setText("Platform 7");
        platform7.setOpaque(true);
        gamePanel.add(platform7, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 112, 143, 27));

        platform8.setBackground(new java.awt.Color(51, 255, 0));
        platform8.setText("Platform 8");
        platform8.setOpaque(true);
        gamePanel.add(platform8, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, 140, 27));

        rewardLabel.setBackground(new java.awt.Color(255, 0, 0));
        rewardLabel.setOpaque(true);
        gamePanel.add(rewardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 50, 20, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gamePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gamePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Form.class.getName());

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Form().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel gamePanel;
    private javax.swing.JLabel goalPlatform;
    private javax.swing.JLabel groundPlatform;
    private javax.swing.JLabel platform1;
    private javax.swing.JLabel platform2;
    private javax.swing.JLabel platform3;
    private javax.swing.JLabel platform4;
    private javax.swing.JLabel platform5;
    private javax.swing.JLabel platform6;
    private javax.swing.JLabel platform7;
    private javax.swing.JLabel platform8;
    private javax.swing.JLabel playerLabel;
    private javax.swing.JLabel rewardLabel;
    // End of variables declaration//GEN-END:variables

}
