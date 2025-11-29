package ClientSide;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;

public class MainMenu extends JFrame {

    private JButton btnSignUp;
    private JButton btnLogin;
    private JButton btnExit;

    private Image brickImage;

    public MainMenu() {
        brickImage = loadBrickImage();
        initUI();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainMenu frame = new MainMenu();
            frame.setVisible(true);
        });
    }

    //The core code for the UI:
    private void initUI() {
        setTitle("Train Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        
        JPanel root = new JPanel(new BorderLayout(0, 0)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (brickImage != null) {
                    //  For full screen
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
        ImageIcon logoIcon = loadLogoIcon();
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        }
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

        JLabel lblTagline = new JLabel("Plan your journey with us!");
        lblTagline.setFont(taglineFont);
        lblTagline.setForeground(new Color(235, 235, 240));

        titlePanel.add(lblProjectName);
        titlePanel.add(Box.createVerticalStrut(6));
        titlePanel.add(lblTagline);

        header.add(titlePanel, BorderLayout.CENTER);

        //Line
        JPanel headerBottomLine = new JPanel();
        headerBottomLine.setPreferredSize(new Dimension(0, 3));
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

        JPanel booth = new JPanel();
        booth.setOpaque(false);
        booth.setLayout(new BoxLayout(booth, BoxLayout.Y_AXIS));

        
        JPanel ticketBar = new JPanel(new BorderLayout());
        ticketBar.setBackground(new Color(30, 21, 20));
        ticketBar.setMaximumSize(new Dimension(520, 40));
        ticketBar.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(166, 77, 45), 1, true),
                BorderFactory.createEmptyBorder(5, 20, 5, 20)
        ));

        JLabel ticketLabel = new JLabel("TRAIN TICKETS", SwingConstants.CENTER);
        ticketLabel.setForeground(new Color(245, 245, 245));
        ticketLabel.setFont(new Font("Serif", Font.BOLD, 17));
        ticketLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ticketBar.add(ticketLabel, BorderLayout.CENTER);

        ticketBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        booth.add(ticketBar);
        booth.add(Box.createVerticalStrut(10));

        JPanel card = new JPanel();
        card.setBackground(new Color(23, 18, 20));
        card.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(120, 90, 70), 1, true),
                BorderFactory.createEmptyBorder(35, 55, 35, 55)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.setMaximumSize(new Dimension(520, 330));

        Font bodyTitleFont = new Font("Serif", Font.BOLD, 24);
        Font bodyTextFont = new Font("Serif", Font.PLAIN, 15);

        JLabel lblWelcome = new JLabel("Welcome!");
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblWelcome.setFont(new Font("Serif", Font.PLAIN, 20));
        lblWelcome.setForeground(new Color(235, 235, 240));

        JLabel lblBigTitle = new JLabel("Express Railways Reservation System");
        lblBigTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblBigTitle.setFont(bodyTitleFont);
        lblBigTitle.setForeground(Color.WHITE);

        JLabel lblDesc = new JLabel("Sign up or log in to manage your trips");
        lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDesc.setFont(bodyTextFont);
        lblDesc.setForeground(new Color(220, 220, 230));

        card.add(lblWelcome);
        card.add(Box.createVerticalStrut(8));
        card.add(lblBigTitle);
        card.add(Box.createVerticalStrut(12));
        card.add(lblDesc);
        card.add(Box.createVerticalStrut(28));

        JPanel buttonRow = new JPanel();
        buttonRow.setOpaque(false);
        buttonRow.setLayout(new FlowLayout(FlowLayout.CENTER, 22, 0));

        btnSignUp = new JButton("Sign Up");
        btnLogin = new JButton("Log In");
        stylePrimaryButton(btnSignUp);
        stylePrimaryButton(btnLogin);

        buttonRow.add(btnSignUp);
        buttonRow.add(btnLogin);

        card.add(buttonRow);
        card.add(Box.createVerticalStrut(22));

        btnExit = new JButton("Exit");
        styleSecondaryButton(btnExit);
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(btnExit);

        card.add(Box.createVerticalStrut(18));

        JLabel lblFooter = new JLabel("© 2025 Train Booking System");
        lblFooter.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFooter.setFont(new Font("Serif", Font.PLAIN, 11));
        lblFooter.setForeground(new Color(210, 210, 220));
        card.add(lblFooter);

        booth.add(card);
        centerWrapper.add(booth, gbc);

        root.add(centerWrapper, BorderLayout.CENTER);
//==================================================== OUR CODES: =====================================
        // Conication begins here=================
        btnSignUp.addActionListener(e -> {
            Client client = new Client();
            try {
                client.connect("localhost", 9090);
                signup1 signobj = new signup1(client);
                signobj.setVisible(true);
                dispose();
                CustomPopup.showSuccess(this, "Connection Done Successfully!");
            } catch (IOException ex) {
                CustomPopup.showError(this, "Connection Failed " + ex.getMessage());
            }
        });

        btnLogin.addActionListener(e -> {
            Client client = new Client();
            try {
                client.connect("localhost", 9090);
                login1 logobj = new login1(client);
                logobj.setVisible(true);
                dispose();
            } catch (IOException ex) {
                CustomPopup.showError(this, "Connection Failed " + ex.getMessage());
            }
        });
//==================================================== OUR CODES  =====================================

        btnExit.addActionListener(e -> System.exit(0));

        addHoverEffect(btnSignUp);
        addHoverEffect(btnLogin);
    }

    /////The images 
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

    private void stylePrimaryButton(JButton btn) {
        btn.setBackground(new Color(30, 136, 229));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 24, 10, 24));
        btn.setFont(new Font("Serif", Font.BOLD, 14));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void styleSecondaryButton(JButton btn) {
        btn.setBackground(new Color(40, 30, 32));
        btn.setForeground(new Color(235, 235, 240));
        btn.setFocusPainted(false);
        btn.setBorder(new javax.swing.border.LineBorder(new Color(120, 90, 70), 1, true));
        btn.setFont(new Font("Serif", Font.PLAIN, 13));
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
