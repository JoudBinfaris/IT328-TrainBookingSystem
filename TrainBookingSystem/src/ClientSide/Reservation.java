package ClientSide;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class Reservation extends JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(Reservation.class.getName());

    private Client client;
    private Image brickImage;

    private JComboBox<String> route;  
    private JComboBox<String> Class;
    private JButton btnNext;

    public Reservation(Client client) {
        this.client = client;
        brickImage = loadBrickImage();
        initUI();
        fillCombos();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initUI() {
        setTitle("Express Railways Reservation System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);

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

        // ============== Header ==============
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

        JLabel lblTagline = new JLabel("Choose your route and class");
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

        // ============== Center card ==============
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);

        JPanel card = new JPanel();
        card.setBackground(new Color(23, 18, 20));
        card.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(120, 90, 70), 1, true),
                BorderFactory.createEmptyBorder(30, 50, 30, 50)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setMaximumSize(new Dimension(520, 280));

        JLabel lblTitle = new JLabel("Where you want to go?");
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


        JLabel lblRoute = new JLabel("Route:");
        lblRoute.setFont(new Font("Serif", Font.PLAIN, 16));
        lblRoute.setForeground(new Color(230, 230, 235));

        JLabel lblClass = new JLabel("Class:");
        lblClass.setFont(new Font("Serif", Font.PLAIN, 16));
        lblClass.setForeground(new Color(230, 230, 235));

        route = new JComboBox<>();
        Class = new JComboBox<>();

        Dimension comboSize = new Dimension(180, 28);
        route.setPreferredSize(comboSize);
        Class.setPreferredSize(comboSize);

        
        f.gridx = 0; f.gridy = 0;
        form.add(lblRoute, f);
        f.gridx = 1;
        form.add(route, f);

       
        f.gridx = 0; f.gridy = 1;
        form.add(lblClass, f);
        f.gridx = 1;
        form.add(Class, f);

        card.add(form);
        card.add(Box.createVerticalStrut(20));

        btnNext = new JButton("Next");
        stylePrimaryButton(btnNext);
        btnNext.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(btnNext);

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

        // ===== Next =====
        btnNext.addActionListener(e -> {
            String routeStr = String.valueOf(route.getSelectedItem());
            String[] parts = routeStr.split("→");
            
            String src = parts[0].trim();
            String des = parts[1].trim();

            String cls = String.valueOf(Class.getSelectedItem());

            
            client.sendLine(src);
            client.sendLine(des);
            client.sendLine(cls);

            new Availability1(client, false).setVisible(true);
            dispose();
        });

        addHoverEffect(btnNext);
    }

    private void fillCombos() {
       
        route.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{
                        "Riyadh → Jeddah",
                "Jeddah → Riyadh",
                "Riyadh → Dammam",
                "Dammam → Riyadh",
                "Riyadh → Alula",
                "Alula → Riyadh"
                }
        ));

        Class.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{"First", "Economy"}));
    }

    private ImageIcon loadLogoIcon() {
        String path = "/ClientSide/images/train_logo.png";
        try {
            URL url = getClass().getResource(path);
            if (url == null) {
                System.out.println("Logo not found at " + path);
                return null;
            }
            ImageIcon icon = new ImageIcon(url);
            Image img = icon.getImage();
            int targetHeight = 70;
            int originalWidth = img.getWidth(null);
            int originalHeight = img.getHeight(null);
            int targetWidth = originalWidth * targetHeight / originalHeight;
            Image scaled = img.getScaledInstance(
                    targetWidth, targetHeight, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
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
                System.out.println("Brick image not found at " + path);
                return null;
            }
            return new ImageIcon(url).getImage();
        } catch (Exception ex) {
            ex.printStackTrace();
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
