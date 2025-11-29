package ClientSide;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class signup1 extends JFrame {

    private Client client;

    private Image brickImage;

    private JTextField email;
    private JTextField passward;
    private JButton jButton1;

    String passward_;
    String username;

    public signup1() {
        brickImage = loadBrickImage();
        initUI();
    }

    public signup1(Client client) {
        this.client = client;
        brickImage = loadBrickImage();
        initUI();
    }

    private void initUI() {
        setTitle("Express Railways Reservation System");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout()) {
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
        header.setBorder(BorderFactory.createEmptyBorder(30, 40, 10, 40));

        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = loadLogoIcon();
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
            logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
            header.add(logoLabel, BorderLayout.LINE_START);
        }

        JPanel titleBox = new JPanel();
        titleBox.setOpaque(false);
        titleBox.setLayout(new BoxLayout(titleBox, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Express Railways Reservation System");
        title.setFont(new Font("Serif", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Create your account to start booking");
        subtitle.setFont(new Font("Serif", Font.PLAIN, 14));
        subtitle.setForeground(new Color(235, 235, 240));

        titleBox.add(title);
        titleBox.add(Box.createVerticalStrut(4));
        titleBox.add(subtitle);

        header.add(titleBox, BorderLayout.CENTER);

        JPanel headerLine = new JPanel();
        headerLine.setPreferredSize(new Dimension(0, 3));
        headerLine.setBackground(new Color(166, 77, 45));

        JPanel headerWrapper = new JPanel(new BorderLayout());
        headerWrapper.setOpaque(true);
        headerWrapper.setBackground(new Color(25, 17, 17));
        headerWrapper.add(header, BorderLayout.CENTER);
        headerWrapper.add(headerLine, BorderLayout.SOUTH);

        root.add(headerWrapper, BorderLayout.NORTH);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);

        JPanel card = new JPanel();
        card.setBackground(new Color(23, 18, 20));
        card.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(120, 90, 70), 1, true),
                BorderFactory.createEmptyBorder(30, 50, 35, 50)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setMaximumSize(new Dimension(520, 340));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblCreate = new JLabel("Create Account");
        lblCreate.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblCreate.setFont(new Font("Serif", Font.BOLD, 30));
        lblCreate.setForeground(Color.WHITE);

        card.add(lblCreate);
        card.add(Box.createVerticalStrut(30));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);

        GridBagConstraints fg = new GridBagConstraints();
        fg.insets = new Insets(5, 10, 5, 10);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Serif", Font.PLAIN, 16));
        lblEmail.setForeground(new Color(235, 235, 240));

        fg.gridx = 0;
        fg.gridy = 0;
        fg.anchor = GridBagConstraints.EAST;
        formPanel.add(lblEmail, fg);

        email = new JTextField();
        email.setPreferredSize(new Dimension(260, 30));
        email.setFont(new Font("Serif", Font.PLAIN, 15));

        fg.gridx = 1;
        fg.gridy = 0;
        fg.anchor = GridBagConstraints.WEST;
        formPanel.add(email, fg);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Serif", Font.PLAIN, 16));
        lblPass.setForeground(new Color(235, 235, 240));

        fg.gridx = 0;
        fg.gridy = 1;
        fg.anchor = GridBagConstraints.EAST;
        formPanel.add(lblPass, fg);

        passward = new JTextField();
        passward.setPreferredSize(new Dimension(260, 30));
        passward.setFont(new Font("Serif", Font.PLAIN, 15));

        fg.gridx = 1;
        fg.gridy = 1;
        fg.anchor = GridBagConstraints.WEST;
        formPanel.add(passward, fg);

        card.add(formPanel);
        card.add(Box.createVerticalStrut(25));

        jButton1 = new JButton("Sign Up");
        stylePrimaryButton(jButton1);
        jButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(jButton1);

        card.add(Box.createVerticalStrut(10));

        JLabel footer = new JLabel("© 2025 Train Booking System");
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        footer.setFont(new Font("Serif", Font.PLAIN, 11));
        footer.setForeground(new Color(210, 210, 220));
        card.add(Box.createVerticalStrut(10));
        card.add(footer);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        centerWrapper.add(card, gbc);

        root.add(centerWrapper, BorderLayout.CENTER);

        jButton1.addActionListener(e -> onSignUp());

        addHoverEffect(jButton1);

        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }

    private void onSignUp() {
        try {
            //Take the values
            username = email.getText();
            passward_ = passward.getText();
            if (username.isEmpty() || passward_.isEmpty()) {
                CustomPopup.showError(this, "Please fill the blanks");
                return;
            }
            //To the server:-
            client.sendLine("SIGNUP");
            client.sendLine(username + "-" + passward_);
            String check = client.readLine();
            if (check.equals("UserName already in use")) {
                CustomPopup.showError(this, "UserName already in use");
                new signup1(client).setVisible(true);
                dispose();
            }
            
            //Open the next frame
            else{
            new MenuFrame(client).setVisible(true);
            CustomPopup.showSuccess(this, "sign-up done successfully!");
            dispose();}
        } catch (IOException ex) {
            Logger.getLogger(signup1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Image loadBrickImage() {
        String path = "src/ClientSide/images/brick.jpg";
        try {
            ImageIcon icon = new ImageIcon(path);
            return icon.getImage();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private ImageIcon loadLogoIcon() {
        String path = "src/ClientSide/images/train_logo.png";
        try {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage();
            int targetHeight = 90;
            int originalWidth = img.getWidth(null);
            int originalHeight = img.getHeight(null);
            int targetWidth = originalWidth * targetHeight / originalHeight;
            Image scaled = img.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void stylePrimaryButton(JButton btn) {
        btn.setBackground(new Color(30, 136, 229));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 28, 10, 28));
        btn.setFont(new Font("Serif", Font.BOLD, 15));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void addHoverEffect(JButton btn) {
        Color normal = btn.getBackground();
        Color hover = normal.brighter();
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(normal);
            }
        });
    }
}
