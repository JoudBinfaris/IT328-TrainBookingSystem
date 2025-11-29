package ClientSide;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;

public class CancelFrame extends JFrame {

    private Client client;
    private Image brickImage;

    private JComboBox<String> routeCb;
    private JComboBox<String> classCb;
    private JComboBox<String> dayCb;
    private JComboBox<String> seatNumCb;

    private JButton btnCancel;
    private JButton btnBack;

    public CancelFrame(Client client) {
        this.client = client;
        brickImage = loadBrickImage();
        initUI();
        fillCombos();
    }

    private void initUI() {
        setTitle("Express Railways Reservation System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(25, 40, 20, 40));

        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = loadLogoIcon();
        if (logoIcon != null) logoLabel.setIcon(logoIcon);
        header.add(logoLabel, BorderLayout.LINE_START);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel lblProjectName = new JLabel("Express Railways Reservation System");
        lblProjectName.setFont(new Font("Serif", Font.BOLD, 28));
        lblProjectName.setForeground(Color.WHITE);

        JLabel lblTagline = new JLabel("Cancel your reservation");
        lblTagline.setFont(new Font("Serif", Font.PLAIN, 15));
        lblTagline.setForeground(new Color(230, 230, 235));

        titlePanel.add(lblProjectName);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(lblTagline);

        header.add(titlePanel, BorderLayout.CENTER);

        JPanel headerBottomLine = new JPanel();
        headerBottomLine.setPreferredSize(new Dimension(0, 3));
        headerBottomLine.setBackground(new Color(166, 77, 45));

        JPanel headerWrapper = new JPanel(new BorderLayout());
        headerWrapper.setBackground(new Color(25, 17, 17));
        headerWrapper.add(header, BorderLayout.CENTER);
        headerWrapper.add(headerBottomLine, BorderLayout.SOUTH);

        root.add(headerWrapper, BorderLayout.NORTH);

        // CENTER CARD
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);

        JPanel card = new JPanel();
        card.setBackground(new Color(23, 18, 20));
        card.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(120, 90, 70), 1, true),
                BorderFactory.createEmptyBorder(30, 60, 30, 60)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setMaximumSize(new Dimension(520, 340));

        JLabel lblTitle = new JLabel("Cancel Reservation");
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setFont(new Font("Serif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        card.add(lblTitle);
        card.add(Box.createVerticalStrut(25));

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints f = new GridBagConstraints();
        f.insets = new Insets(8, 8, 8, 8);
        f.anchor = GridBagConstraints.WEST;

        // LABELS
        JLabel lblRoute = new JLabel("Route:");
        JLabel lblClass = new JLabel("Class:");
        JLabel lblDay = new JLabel("Day:");
        JLabel lblSeat = new JLabel("Seat Number:");

        for (JLabel lbl : new JLabel[]{lblRoute, lblClass, lblDay, lblSeat}) {
            lbl.setFont(new Font("Serif", Font.PLAIN, 16));
            lbl.setForeground(new Color(230, 230, 235));
        }

        // COMBOBOXES
        routeCb = new JComboBox<>();
        classCb = new JComboBox<>();
        dayCb = new JComboBox<>();
        seatNumCb = new JComboBox<>();

        Dimension comboSize = new Dimension(160, 28);
        routeCb.setPreferredSize(comboSize);
        classCb.setPreferredSize(comboSize);
        dayCb.setPreferredSize(comboSize);
        seatNumCb.setPreferredSize(comboSize);

        // FORM LAYOUT
        int row = 0;

        f.gridx = 0; f.gridy = row; form.add(lblRoute, f);
        f.gridx = 1; form.add(routeCb, f); row++;

        f.gridx = 0; f.gridy = row; form.add(lblClass, f);
        f.gridx = 1; form.add(classCb, f); row++;

        f.gridx = 0; f.gridy = row; form.add(lblDay, f);
        f.gridx = 1; form.add(dayCb, f); row++;

        f.gridx = 0; f.gridy = row; form.add(lblSeat, f);
        f.gridx = 1; form.add(seatNumCb, f);

        card.add(form);
        card.add(Box.createVerticalStrut(20));

        btnCancel = new JButton("Cancel Reservation");
        btnBack = new JButton("Back to Main Menu");

        styleButton(btnCancel);
        styleButton(btnBack);

        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(btnCancel);
        card.add(Box.createVerticalStrut(10));
        card.add(btnBack);
        card.add(Box.createVerticalStrut(10));

        JLabel lblFooter = new JLabel("© 2025 Train Booking System");
        lblFooter.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFooter.setFont(new Font("Serif", Font.PLAIN, 11));
        lblFooter.setForeground(new Color(210, 210, 220));
        card.add(lblFooter);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        centerWrapper.add(card, gbc);
        root.add(centerWrapper, BorderLayout.CENTER);

        btnCancel.addActionListener(e -> doCancel());
        btnBack.addActionListener(e -> {
            new MenuFrame(client).setVisible(true);
            dispose();
        });
    }

    private void fillCombos() {

        
        routeCb.setModel(new DefaultComboBoxModel<>(new String[]{
                "Riyadh → Jeddah",
                "Jeddah → Riyadh",
                "Riyadh → Dammam",
                "Dammam → Riyadh",
                "Riyadh → Alula"
                
        }));

        classCb.setModel(new DefaultComboBoxModel<>(new String[]{
                "First", "Economy"
        }));

        dayCb.setModel(new DefaultComboBoxModel<>(new String[]{
                "Sunday", "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday"
        }));

        DefaultComboBoxModel<String> seatModel = new DefaultComboBoxModel<>();
        for (int i = 1; i <= 4; i++) {
            seatModel.addElement("Seat " + i);
        }
        seatNumCb.setModel(seatModel);
    }

    private void doCancel() {
        try {
            String route = String.valueOf(routeCb.getSelectedItem());
            String[] parts = route.split("→");

            String src = parts[0].trim();
            String dest = parts[1].trim();

            String cls = String.valueOf(classCb.getSelectedItem());
            int dayIdx = dayCb.getSelectedIndex();
            String seat = String.valueOf(seatNumCb.getSelectedItem());

            
            client.sendLine(src);
            client.sendLine(dest);
            client.sendLine(cls);
            client.sendLine(String.valueOf(dayIdx));
            client.sendLine(seat);

            String reply = client.readLine();

            if ("OK".equalsIgnoreCase(reply)) {
                CustomPopup.showSuccess(this, "Reservation cancelled successfully.");
                new MenuFrame(client).setVisible(true);
                dispose();
            } else {
                CustomPopup.showError(this, "No matching reservation under your name.");
            }

        } catch (Exception e) {
            CustomPopup.showError(this, "Error: " + e.getMessage());
        }
    }

    private ImageIcon loadLogoIcon() {
        try {
            URL url = getClass().getResource("/ClientSide/images/train_logo.png");
            if (url == null) return null;
            Image img = new ImageIcon(url).getImage();
            img = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception ex) {
            return null;
        }
    }

    private Image loadBrickImage() {
        try {
            URL url = getClass().getResource("/ClientSide/images/brick.jpg");
            if (url == null) return null;
            return new ImageIcon(url).getImage();
        } catch (Exception ex) {
            return null;
        }
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(30, 136, 229));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 24, 8, 24));
        btn.setFont(new Font("Serif", Font.BOLD, 14));
    }
}
