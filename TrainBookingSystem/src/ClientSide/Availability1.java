/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClientSide;

/**
 *
 * @author sarah
 */
 
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Availability1 extends JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(Availability1.class.getName());

    private Client client;
    private boolean changeMode;

    private Image brickImage;

    private JComboBox<String> Daycb;
    private JComboBox<String> seatcb;
    private JButton showAvalbtn;
    private JButton bookbtn;

    public Availability1() {
        this(null, false);
    }

    public Availability1(Client client, boolean changeMode) {
        this.client = client;
        this.changeMode = changeMode;
        brickImage = loadBrickImage();
        initUI();
        fillDayCombo();
        if (changeMode) {
            Daycb.setEnabled(false);
        }
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

        JLabel lblTagline = new JLabel("Trip seat booking");
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

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);

        JPanel card = new JPanel();
        card.setBackground(new Color(23, 18, 20));
        card.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(120, 90, 70), 1, true),
                BorderFactory.createEmptyBorder(30, 60, 30, 60)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setMaximumSize(new Dimension(520, 280));

        JLabel lblTitle = new JLabel("Trip seat booking");
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

        JLabel lblDay = new JLabel("1) Choose a day:");
        lblDay.setFont(new Font("Serif", Font.PLAIN, 16));
        lblDay.setForeground(new Color(230, 230, 235));

        JLabel lblSeat = new JLabel("2) Choose a seat:");
        lblSeat.setFont(new Font("Serif", Font.PLAIN, 16));
        lblSeat.setForeground(new Color(230, 230, 235));

        Daycb = new JComboBox<>();
        seatcb = new JComboBox<>();

        Dimension comboSize = new Dimension(150, 28);
        Daycb.setPreferredSize(comboSize);
        seatcb.setPreferredSize(comboSize);

        showAvalbtn = new JButton("Show available seats");
        bookbtn = new JButton("Book");
        stylePrimaryButton(showAvalbtn);
        stylePrimaryButton(bookbtn);

        f.gridx = 0; f.gridy = 0;
        form.add(lblDay, f);
        f.gridx = 1;
        form.add(Daycb, f);
        f.gridx = 2;
        form.add(showAvalbtn, f);

        f.gridx = 0; f.gridy = 1;
        form.add(lblSeat, f);
        f.gridx = 1;
        form.add(seatcb, f);

        card.add(form);
        card.add(Box.createVerticalStrut(20));

        bookbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(bookbtn);
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

        showAvalbtn.addActionListener(this::showAvalbtnActionPerformed);
        bookbtn.addActionListener(this::bookbtnActionPerformed);
        Daycb.addActionListener(this::DaycbActionPerformed);
//seatcb.addActionListener(this::seatcbActionPerformed);

        bookbtn.setEnabled(false);
    }

    private void fillDayCombo() {
        Daycb.setModel(new DefaultComboBoxModel<>(new String[]{
                "Sunday", "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday"
        }));
    }

    private void showAvalbtnActionPerformed(java.awt.event.ActionEvent evt) {
        loadAvailability();
        showAvalbtn.setEnabled(false);
        Daycb.setEnabled(false);
    }

    private void loadAvailability() {
        try {
            if (!changeMode) {
                int dayIndex = Daycb.getSelectedIndex();
                client.sendLine(Integer.toString(dayIndex));
            }

            ArrayList<Integer> seats = client.requestAvailability();

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (int s : seats) {
                if (s > 0) {
                    model.addElement("Seat Number " + s);
                }
            }
            seatcb.setModel(model);

            boolean hasAny = model.getSize() > 0;
            bookbtn.setEnabled(hasAny);

            if (!hasAny) {
                JOptionPane.showMessageDialog(this,
                        "No available trips for this class/route.");
                dispose();
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error loading availability: " + ex.getMessage());
        }
    }

    private void DaycbActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

    private void bookbtnActionPerformed(java.awt.event.ActionEvent evt) {                                        

    System.out.println("In bookbtnActionPerformed");

    String n = String.valueOf(seatcb.getSelectedItem());
    System.out.print("Seat number:");
    System.out.println(n);
    client.sendLine(n);

    try {
        String booked = client.readLine();
        System.out.println(booked);

        if (booked.equals("true")) {
            client.sendLine("CHANGE");
            CustomPopup.showError(this, "Seat already taken :(");

            Availability1.this.dispose();
            new Availability1(client, true).setVisible(true);
            return;

        } else {

            client.sendLine("CONFIRM");
            String check = client.readLine();

            if (check.equals("done")) {

                
                CustomPopup.showSuccess(this, "Booking confirmed successfully!");

                
                new MenuFrame(client).setVisible(true);
                dispose();
            }
        }

    } catch (IOException ex) {
        CustomPopup.showError(this, "Booking error: " + ex.getMessage());
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
            Image scaled = img.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
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

}

