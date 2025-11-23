/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClientSide;

/**
 *
 * @author sarah
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CustomPopup extends JDialog {

    // ====== واجهات جاهزة للاستعمال ======

    public static void showSuccess(Window parent, String message) {
        show(parent, "Success", message, true);
    }

    public static void showError(Window parent, String message) {
        show(parent, "Error", message, false);
    }

    private static void show(Window parent, String title, String message, boolean success) {
        CustomPopup dialog = new CustomPopup(parent, title, message, success);
        dialog.setVisible(true); // modal
    }

    // ====== تصميم البوب-اب نفسه ======

    private CustomPopup(Window parent, String title, String message, boolean success) {
        super(parent, title, ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        // الحجم
        setSize(420, 200);
        setLocationRelativeTo(parent);

        // ألوان نفس الكارد حقك
        Color bgColor      = new Color(23, 18, 20);   // خلفية البوب-اب
        Color borderColor  = new Color(120, 90, 70);  // إطار بني
        Color textColor    = new Color(235, 235, 240);

        Color accentColor  = success
                ? new Color(30, 136, 229)   // أزرق للإشعار الناجح
                : new Color(200, 60, 60);   // أحمر للـ error

        JPanel root = new JPanel(new BorderLayout(0, 0));
        root.setBackground(bgColor);
        root.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(borderColor, 1, true),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        setContentPane(root);

        // ===== أيقونة صغيرة + عنوان =====
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        top.setOpaque(false);

        // دائرة ملوّنة تمثل نوع الرسالة
        JLabel iconCircle = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(accentColor);
                g2.fillOval(0, 0, getWidth(), getHeight());
            }
        };
        iconCircle.setPreferredSize(new Dimension(18, 18));

        JLabel lblTitle = new JLabel(success ? "Success" : "Error");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 18));
        lblTitle.setForeground(textColor);

        top.add(iconCircle);
        top.add(lblTitle);

        root.add(top, BorderLayout.NORTH);

        // ===== نص الرسالة (HTML عشان سطرين وثلاثة بدون مشاكل) =====
        JLabel lblMessage = new JLabel("<html>" + message + "</html>");
        lblMessage.setForeground(textColor);
        lblMessage.setFont(new Font("Serif", Font.PLAIN, 15));
        lblMessage.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel center = new JPanel(new BorderLayout());
        center.setOpaque(false);
        center.add(lblMessage, BorderLayout.CENTER);
        center.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        root.add(center, BorderLayout.CENTER);

        // ===== زر OK =====
        JButton btnOk = new JButton("OK");
        btnOk.setBackground(accentColor);
        btnOk.setForeground(Color.WHITE);
        btnOk.setFocusPainted(false);
        btnOk.setBorder(BorderFactory.createEmptyBorder(8, 25, 8, 25));
        btnOk.setFont(new Font("Serif", Font.BOLD, 13));
        btnOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnOk.addActionListener(e -> dispose());

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottom.setOpaque(false);
        bottom.add(btnOk);

        root.add(bottom, BorderLayout.SOUTH);

        // اغلاق بالـ Esc
        getRootPane().registerKeyboardAction(
                e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW
        );
    }
}
