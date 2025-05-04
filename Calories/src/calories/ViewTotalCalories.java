package calories;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.event.*;
import java.util.EventObject;

public class ViewTotalCalories {
    JFrame f;
    JPanel gradientPanel;
    JTable table;
    DefaultTableModel model;
    JLabel totalLabel;

    ViewTotalCalories() {
        f = new JFrame("CALORIES TRACKER - VIEW TOTAL CALORIES");
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
        String[] columns = {"Food Name", "Calories", "Operation"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setRowHeight(30);
        table.getColumn("Operation").setCellRenderer(new ButtonRenderer());
        table.getColumn("Operation").setCellEditor(new ButtonEditor());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 30, 520, 250);
        gradientPanel.add(scrollPane);

        totalLabel = new JLabel();
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setBounds(30, 300, 300, 30);
        gradientPanel.add(totalLabel);

        loadData();

        f.setVisible(true);
    }

    private void loadData() {
        model.setRowCount(0); 
        CaloriesCart[] cartItems = CaloriesCart.getCartItems();
        int cartSize = CaloriesCart.getCartSize();
        int totalCalories = 0;

        for (int i = 0; i < cartSize; i++) {
            model.addRow(new Object[]{cartItems[i].foodname, cartItems[i].foodcalories, "Delete"});
            totalCalories += cartItems[i].foodcalories;
        }

        totalLabel.setText("Total Calories: " + totalCalories + " kcal");
    }

    // Renderer for JButton
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText("Delete");
            return this;
        }
    }

    // Editor for JButton
    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JButton btndelete;
        private int currentRow;

        public ButtonEditor() {
            btndelete = new JButton("Delete");

            btndelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CaloriesCart.deleteItem(currentRow);
                    fireEditingStopped(); 
                    loadData(); 
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.currentRow = row;
            return btndelete;
        }

        @Override
        public Object getCellEditorValue() {
            return "Delete";
        }

        @Override
        public boolean isCellEditable(EventObject e) {
            return true;
        }
    }
}
