package ClientSide;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class MenuFrame extends JFrame {

    private Client client;  // مهم — نفس الـ client اللي يتنقل بين الكلاسات
    private Image brickImage;

    private JButton btnNewReservation;
    private JButton btnCancelReservation;
    private JButton btnViewHistory;
    private JButton btnExit;          // 👈 أضيفي هذا

    public MenuFrame(Client client) {
        this.client = client;   // حفظ الـ client
        brickImage = loadBrickImage();
        initUI();
    }

    private void initUI() {
        setTitle("Express Railways Reservation System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        // ---------------- HEADER ----------------
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(35, 45, 25, 45));

        // Logo
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = loadLogoIcon();
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        }
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        header.add(logoLabel, BorderLayout.LINE_START);

        // Title
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel lblProjectName = new JLabel("Express Railways Reservation System");
        lblProjectName.setFont(new Font("Serif", Font.BOLD, 30));
        lblProjectName.setForeground(Color.WHITE);

        JLabel lblTagline = new JLabel("Choose what you would like to do");
        lblTagline.setFont(new Font("Serif", Font.PLAIN, 15));
        lblTagline.setForeground(new Color(235, 235, 240));

        titlePanel.add(lblProjectName);
        titlePanel.add(Box.createVerticalStrut(6));
        titlePanel.add(lblTagline);
        header.add(titlePanel, BorderLayout.CENTER);

        JPanel headerBottomLine = new JPanel();
        headerBottomLine.setPreferredSize(new Dimension(0, 3));
        headerBottomLine.setBackground(new Color(166, 77, 45));

        JPanel headerWrapper = new JPanel(new BorderLayout());
        headerWrapper.setOpaque(true);
        headerWrapper.setBackground(new Color(25, 17, 17));
        headerWrapper.add(header, BorderLayout.CENTER);
        headerWrapper.add(headerBottomLine, BorderLayout.SOUTH);

        root.add(headerWrapper, BorderLayout.NORTH);

        // ---------------- CENTER CARD ----------------
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);

        JPanel card = new JPanel();
        card.setBackground(new Color(23, 18, 20));
        card.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(120, 90, 70), 1, true),
                BorderFactory.createEmptyBorder(35, 80, 35, 80)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.setMaximumSize(new Dimension(520, 380));

        JLabel lblTitle = new JLabel("Main Menu");
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setFont(new Font("Serif", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        card.add(lblTitle);
        card.add(Box.createVerticalStrut(30));

        // Buttons
        btnNewReservation = new JButton("New Reservation");
        btnCancelReservation = new JButton("Cancel Reservation");
        btnViewHistory = new JButton("View My Reservations");
        btnExit = new JButton("Exit");

        btnCancelReservation.addActionListener(e -> {
            client.sendLine("CANCEL");
            new CancelFrame(client).setVisible(true);
            dispose();
        });
        btnViewHistory.addActionListener(e -> {
            client.sendLine("HISTORY");
            new ViewHistoryFrame(client).setVisible(true);
            dispose();
        });

        btnNewReservation.addActionListener(e -> {
            client.sendLine("NEW");
            new Reservation(client).setVisible(true);
            dispose();
        });

        Dimension btnSize = new Dimension(220, 40);

        btnNewReservation.setPreferredSize(btnSize);
        btnNewReservation.setMaximumSize(btnSize);

        btnCancelReservation.setPreferredSize(btnSize);
        btnCancelReservation.setMaximumSize(btnSize);

        btnViewHistory.setPreferredSize(btnSize);
        btnViewHistory.setMaximumSize(btnSize);

        btnExit.setPreferredSize(btnSize);      // 👈
        btnExit.setMaximumSize(btnSize);       // 👈

        // === Force same button size ===
        btnNewReservation.setPreferredSize(btnSize);
        btnCancelReservation.setPreferredSize(btnSize);
        btnViewHistory.setPreferredSize(btnSize);

        stylePrimaryButton(btnNewReservation);
        stylePrimaryButton(btnCancelReservation);
        stylePrimaryButton(btnViewHistory);
        stylePrimaryButton(btnExit);    // 👈

        btnNewReservation.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCancelReservation.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnViewHistory.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);   // 👈

        card.add(btnNewReservation);
        card.add(Box.createVerticalStrut(15));
        card.add(btnCancelReservation);
        card.add(Box.createVerticalStrut(15));
        card.add(btnViewHistory);
        card.add(Box.createVerticalStrut(15));   // مسافة قبل Exit
        card.add(btnExit);
        card.add(Box.createVerticalStrut(25));

        JLabel lblFooter = new JLabel("© 2025 Train Booking System");
        lblFooter.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFooter.setFont(new Font("Serif", Font.PLAIN, 11));
        lblFooter.setForeground(new Color(210, 210, 220));
        card.add(lblFooter);

        centerWrapper.add(card, new GridBagConstraints());
        root.add(centerWrapper, BorderLayout.CENTER);

        btnExit.addActionListener(e -> {
            try {
                if (client != null) {
                    client.disconnec();
                }
            } catch (Exception ex) {
                // لو حابة تجاهليه أو تسوين لوق
                ex.printStackTrace();
            }
            dispose(); // يقفل المنيو
        });

    }

    // ---------------- UTILITIES ----------------
    private ImageIcon loadLogoIcon() {
        String path = "/ClientSide/images/train_logo.png"; // المسار الصحيح
        try {
            URL url = getClass().getResource(path);
            if (url == null) {
                System.out.println("Logo not found at " + path);
                return null;
            }
            ImageIcon icon = new ImageIcon(url);
            Image img = icon.getImage();
            int targetHeight = 90;
            int targetWidth = img.getWidth(null) * targetHeight / img.getHeight(null);
            return new ImageIcon(img.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private Image loadBrickImage() {
        String path = "/ClientSide/images/brick.jpg";
        try {
            URL url = getClass().getResource(path);
            if (url == null) {
                return null;
            }
            return new ImageIcon(url).getImage();
        } catch (Exception ex) {
            return null;
        }
    }

    private void stylePrimaryButton(JButton btn) {
        btn.setBackground(new Color(30, 136, 229));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(null); // أو خليه LineBorder بسيط لو حابة
        btn.setFont(new Font("Serif", Font.BOLD, 14));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

}
