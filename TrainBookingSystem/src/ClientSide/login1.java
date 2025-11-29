package ClientSide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class login1 extends JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(login1.class.getName());

    private Client client;

    private JTextField Email;
    private JTextField passward;
    private JButton connect;

    private Image brickImage;

    String passward_;
    String username;

    public login1() {
        this(null);
    }

    public login1(Client client) {
        this.client = client;
        brickImage = loadBrickImage();
        initUI();
    }

    private void initUI() {
        setTitle("Express Railways Reservation System");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(0, 0)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (brickImage != null) {
                    g.drawImage(brickImage, 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.setColor(new Color(40, 26, 22));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        root.setOpaque(false);
        setContentPane(root);

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(35, 45, 25, 45));

        JLabel logoLabel = new JLabel();
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        header.add(logoLabel, BorderLayout.LINE_START);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        Font titleFont = new Font("Serif", Font.BOLD, 30);
        Font taglineFont = new Font("Serif", Font.PLAIN, 15);

        JLabel lblProjectName = new JLabel("Express Railways Reservation System");
        lblProjectName.setFont(titleFont);
        lblProjectName.setForeground(Color.WHITE);

        JLabel lblTagline = new JLabel("Log in to manage your trips");
        lblTagline.setFont(taglineFont);
        lblTagline.setForeground(new Color(235, 235, 240));

        titlePanel.add(lblProjectName);
        titlePanel.add(Box.createVerticalStrut(6));
        titlePanel.add(lblTagline);

        header.add(titlePanel, BorderLayout.CENTER);

        JPanel headerBottomLine = new JPanel();
        headerBottomLine.setPreferredSize(new java.awt.Dimension(0, 3));
        headerBottomLine.setBackground(new Color(166, 77, 45));

        JPanel headerWrapper = new JPanel(new BorderLayout());
        headerWrapper.setOpaque(true);
        headerWrapper.setBackground(new Color(25, 17, 17));
        headerWrapper.add(header, BorderLayout.CENTER);
        headerWrapper.add(headerBottomLine, BorderLayout.SOUTH);

        root.add(headerWrapper, BorderLayout.NORTH);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel card = new JPanel();
        card.setBackground(new Color(23, 18, 20));
        card.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(120, 90, 70), 1, true),
                BorderFactory.createEmptyBorder(35, 55, 35, 55)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.setMaximumSize(new java.awt.Dimension(520, 330));

        Font bodyTitleFont = new Font("Serif", Font.BOLD, 26);
        Font bodyTextFont = new Font("Serif", Font.PLAIN, 16);

        JLabel lblTitle = new JLabel("Log In");
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setFont(bodyTitleFont);
        lblTitle.setForeground(Color.WHITE);

        card.add(lblTitle);
        card.add(Box.createVerticalStrut(25));

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new java.awt.GridBagLayout());
        GridBagConstraints f = new GridBagConstraints();
        f.insets = new java.awt.Insets(5, 5, 5, 5);
        f.anchor = GridBagConstraints.WEST;

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(bodyTextFont);
        lblEmail.setForeground(new Color(230, 230, 235));

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(bodyTextFont);
        lblPassword.setForeground(new Color(230, 230, 235));

        Email = new JTextField(20);
        Email.setFont(new Font("Serif", Font.PLAIN, 15));

        passward = new JTextField(20);
        passward.setFont(new Font("Serif", Font.PLAIN, 15));

        f.gridx = 0;
        f.gridy = 0;
        formPanel.add(lblEmail, f);

        f.gridx = 1;
        formPanel.add(Email, f);

        f.gridx = 0;
        f.gridy = 1;
        formPanel.add(lblPassword, f);

        f.gridx = 1;
        formPanel.add(passward, f);

        card.add(formPanel);
        card.add(Box.createVerticalStrut(25));

        connect = new JButton("Log In");
        stylePrimaryButton(connect);
        connect.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(connect);

        card.add(Box.createVerticalStrut(18));

        JLabel lblFooter = new JLabel("© 2025 Train Booking System");
        lblFooter.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFooter.setFont(new Font("Serif", Font.PLAIN, 11));
        lblFooter.setForeground(new Color(210, 210, 220));
        card.add(lblFooter);

        centerWrapper.add(card, gbc);
        root.add(centerWrapper, BorderLayout.CENTER);

        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                doLogin();
            }
        });
    }
////===========================================our codes=======================
    private void doLogin() {
        try {
            username = Email.getText();
            passward_ = passward.getText();

            client.sendLine("LOGIN");
            client.sendLine(username + "-" + passward_);
            String cmd = client.readLine();
            if (cmd.equals("FAIL")) {
                CustomPopup.showError(this, "No User With This Username and Password Exists");
                new login1(client).setVisible(true);
                dispose();
            } else {
                CustomPopup.showSuccess(this, "Logged in successfully.");
                new MenuFrame(client).setVisible(true);
                dispose();
            }
        } catch (IOException ex) {
            Logger.getLogger(login1.class.getName()).log(Level.SEVERE, null, ex);
            CustomPopup.showError(this, "Connection error: " + ex.getMessage());
        }
    }
//============================================our codes =================================
    private Image loadBrickImage() {
        String path = "/ClientSide/images/brick.jpg";
        try {
            URL url = getClass().getResource(path);
            if (url == null) {
                System.out.println("Brick image not found at " + path);
                return null;
            }
            return new javax.swing.ImageIcon(url).getImage();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void stylePrimaryButton(JButton btn) {
        btn.setBackground(new Color(30, 136, 229));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 24, 10, 24));
        btn.setFont(new Font("Serif", Font.BOLD, 14));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
