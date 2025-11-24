package ClientSide;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;

public class CancelFrame extends JFrame {

    private Client client;
    private Image brickImage;

    private JComboBox<String> sourceCb;
    private JComboBox<String> destCb;
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

        // ============== HEADER ==============
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(25, 40, 20, 40));

        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = loadLogoIcon();
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        }
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 18));
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
        headerWrapper.setOpaque(true);
        headerWrapper.setBackground(new Color(25, 17, 17));
        headerWrapper.add(header, BorderLayout.CENTER);
        headerWrapper.add(headerBottomLine, BorderLayout.SOUTH);

        root.add(headerWrapper, BorderLayout.NORTH);

        // ============== CARD ==============
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);

        JPanel card = new JPanel();
        card.setBackground(new Color(23, 18, 20));
        card.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(120, 90, 70), 1, true),
                BorderFactory.createEmptyBorder(30, 60, 30, 60)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setMaximumSize(new Dimension(520, 320));

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

// ==== labels ====
JLabel lblSource = new JLabel("Source:");
lblSource.setFont(new Font("Serif", Font.PLAIN, 16));
lblSource.setForeground(new Color(230, 230, 235));

JLabel lblDest = new JLabel("Destination:");
lblDest.setFont(new Font("Serif", Font.PLAIN, 16));
lblDest.setForeground(new Color(230, 230, 235));

JLabel lblClass = new JLabel("Class:");
lblClass.setFont(new Font("Serif", Font.PLAIN, 16));
lblClass.setForeground(new Color(230, 230, 235));

JLabel lblDay = new JLabel("Day:");
lblDay.setFont(new Font("Serif", Font.PLAIN, 16));
lblDay.setForeground(new Color(230, 230, 235));

JLabel lblSeat = new JLabel("Seat Number:");
lblSeat.setFont(new Font("Serif", Font.PLAIN, 16));
lblSeat.setForeground(new Color(230, 230, 235));

// ==== combo boxes (إنشاء قبل الاستخدام) ====
sourceCb  = new JComboBox<>();
destCb    = new JComboBox<>();
classCb   = new JComboBox<>();
dayCb     = new JComboBox<>();
seatNumCb = new JComboBox<>();

// ==== الأحجام ====
Dimension comboSize = new Dimension(150, 28);
sourceCb.setPreferredSize(comboSize);
destCb.setPreferredSize(comboSize);
classCb.setPreferredSize(comboSize);
dayCb.setPreferredSize(comboSize);
seatNumCb.setPreferredSize(comboSize);

// ==== ترتيب الفورم ====
f.gridx = 0; f.gridy = 0;
form.add(lblSource, f);
f.gridx = 1;
form.add(sourceCb, f);

f.gridx = 0; f.gridy = 1;
form.add(lblDest, f);
f.gridx = 1;
form.add(destCb, f);

f.gridx = 0; f.gridy = 2;
form.add(lblClass, f);
f.gridx = 1;
form.add(classCb, f);

f.gridx = 0; f.gridy = 3;
form.add(lblDay, f);
f.gridx = 1;
form.add(dayCb, f);

f.gridx = 0; f.gridy = 4;
form.add(lblSeat, f);
f.gridx = 1;
form.add(seatNumCb, f);

// إضافة الفورم للكارد
card.add(form);
card.add(Box.createVerticalStrut(20));


        btnCancel = new JButton("Cancel Reservation");
        btnBack   = new JButton("Back to Main Menu");

        stylePrimaryButton(btnCancel);
        stylePrimaryButton(btnBack);

        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(btnCancel);
        card.add(Box.createVerticalStrut(10));
        card.add(btnBack);
        card.add(Box.createVerticalStrut(15));

        JLabel lblFooter = new JLabel("© 2025 Train Booking System");
        lblFooter.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFooter.setFont(new Font("Serif", Font.PLAIN, 11));
        lblFooter.setForeground(new Color(210, 210, 220));
        card.add(lblFooter);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        centerWrapper.add(card, gbc);

        root.add(centerWrapper, BorderLayout.CENTER);

        // ====== الأحداث ======
        btnCancel.addActionListener(e -> doCancel());
        btnBack.addActionListener(e -> {
            new MenuFrame(client).setVisible(true);
            dispose();
        });
    }

    private void fillCombos() {
        sourceCb.setModel(new DefaultComboBoxModel<>(new String[]{
                "Riyadh", "Jeddah", "Dammam"
        }));

        destCb.setModel(new DefaultComboBoxModel<>(new String[]{
                "Riyadh", "Jeddah", "Dammam", "Alula"
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
            String src  = String.valueOf(sourceCb.getSelectedItem());
            String dest = String.valueOf(destCb.getSelectedItem());
            String cls  = String.valueOf(classCb.getSelectedItem());
            int dayIdx  = dayCb.getSelectedIndex();
            String seat = String.valueOf(seatNumCb.getSelectedItem());


           
            if (src.equals(dest)) {
                CustomPopup.showError(this, "Source and destination cannot be the same.");
                return;
            }

            
            client.sendLine("CANCEL_TRIP");      
            client.sendLine(src);
            client.sendLine(dest);
            client.sendLine(cls);
            client.sendLine(Integer.toString(dayIdx));
            client.sendLine(seat);
            

            String reply = client.readLine();     

            if ("OK".equalsIgnoreCase(reply)) {
                CustomPopup.showSuccess(this, "Reservation cancelled successfully.");
                new MenuFrame(client).setVisible(true);
                dispose();
            } else if ("NOT_FOUND".equalsIgnoreCase(reply)) {
                CustomPopup.showError(this, "No reservation found for these details.");
                new MenuFrame(client).setVisible(true);
                dispose();
            } else {
                CustomPopup.showError(this, "Unexpected server reply: " + reply);
            }

        } catch (IOException ex) {
            CustomPopup.showError(this, "Cancel error: " + ex.getMessage());
        }
    }

    private ImageIcon loadLogoIcon() {
        try {
            URL url = getClass().getResource("/ClientSide/images/train_logo.png");
            if (url == null) return null;
            Image img = new ImageIcon(url).getImage();
            int h = 70;
            int w = img.getWidth(null) * h / img.getHeight(null);
            img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
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

    private void stylePrimaryButton(JButton btn) {
        btn.setBackground(new Color(30, 136, 229));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 24, 8, 24));
        btn.setFont(new Font("Serif", Font.BOLD, 14));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
