package calories;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.event.*;
import java.util.EventObject;

public class Admin {
    JFrame f;
    JPanel gradientPanel;
    JTable table;
    DefaultTableModel model;
    JTextField searchField;
    JLabel lbllogin;

    Admin() {
        f = new JFrame("CALORIES TRACKER - ADMIN");
        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(null);

        gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, new Color(86, 180, 211), 0, h, new Color(72, 153, 90));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        gradientPanel.setLayout(null);
        gradientPanel.setBounds(0, 0, 600, 400);
        f.add(gradientPanel);

        // Table setup
        String[] columns = {"Username", "Password", "Delete", "Update"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setRowHeight(30);
        table.getColumn("Delete").setCellRenderer(new ButtonRenderer("Delete"));
        table.getColumn("Delete").setCellEditor(new ButtonEditor("Delete"));
        table.getColumn("Update").setCellRenderer(new ButtonRenderer("Update"));
        table.getColumn("Update").setCellEditor(new ButtonEditor("Update"));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 90, 520, 250);
        gradientPanel.add(scrollPane);

        searchField = new JTextField();
        searchField.setBounds(30, 30, 520, 30);
        gradientPanel.add(searchField);

        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                searchUser(searchField.getText().trim());
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                searchUser(searchField.getText().trim());
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                searchUser(searchField.getText().trim());
            }
        });
        
        lbllogin = new JLabel("<HTML><U>Login</U></HTML>");
        lbllogin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbllogin.setForeground(Color.WHITE);
        lbllogin.setBounds(50, 340, 250, 25);
        lbllogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gradientPanel.add(lbllogin);
        
        lbllogin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                f.dispose();
                Calories c = new Calories();
            }
        });

        loadData();
        f.setVisible(true);
    }

    private void loadData() {
        model.setRowCount(0);
        User[] user = User.getUsers();
        int userSize = User.getUserSize();

        for (int i = 0; i < userSize; i++) {
            model.addRow(new Object[]{user[i].username, user[i].password, "Delete", "Update"});
        }
    }

    private void searchUser(String keyword) {
        model.setRowCount(0);
        User[] user = User.getUsers();
        int userSize = User.getUserSize();

        for (int i = 0; i < userSize; i++) {
            if (user[i].username.toLowerCase().contains(keyword.toLowerCase())) {
                model.addRow(new Object[]{user[i].username, user[i].password, "Delete", "Update"});
            }
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer(String text) {
            setOpaque(true);
            setText(text);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value != null ? value.toString() : "");
            return this;
        }
    }

    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JButton button;
        private String label;
        private int currentRow;

        public ButtonEditor(String label) {
            this.label = label;
            button = new JButton(label);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (label.equals("Delete")) {
                        User.deleteItem(currentRow);
                        loadData();
                    } else if (label.equals("Update")) {
                        updateUser(currentRow);
                    }
                    fireEditingStopped();
                }
            });
        }

        private void updateUser(int row) {
            String currentUsername = (String) table.getValueAt(row, 0);
            String currentPassword = (String) table.getValueAt(row, 1);

            JTextField newUsernameField = new JTextField(currentUsername);
            JTextField newPasswordField = new JTextField(currentPassword);
            Object[] message = {
                "New Username:", newUsernameField,
                "New Password:", newPasswordField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Update User", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String newUsername = newUsernameField.getText().trim();
                String newPassword = newPasswordField.getText().trim();
                if (!newUsername.isEmpty() && !newPassword.isEmpty()) {
                    User.updateItem(row, newUsername, newPassword);
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(null, "Username and password cannot be empty.");
                }
            }
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.currentRow = row;
            button.setText(label);
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }

        @Override
        public boolean isCellEditable(EventObject e) {
            return true;
        }
    }
}
