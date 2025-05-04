package calories;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;

public class Menu {
    JFrame f;
    JTextField searchField;
    JPanel mainPanel;
    ArrayList<JPanel> foodPanels = new ArrayList<>();
    Food[] foods = {
        new Food("Mee Goreng", 50, 15, 18, 83, "meegoreng.jpg"),
        new Food("Nasi Goreng", 60, 12, 15, 87, "nasigoreng.jpg"),
        new Food("Nasi Lemak", 60, 13, 25, 98, "nasilemak.jpeg"),
        new Food("Nasi Tomyam", 55, 18, 12, 85, "nasitomyam.jpg"),
        new Food("Nasi Ayam", 55, 22, 12, 92, "nasiayam.jpg"),
        new Food("Burger", 35, 18, 20, 73, "burger.jpg")
    };
    
    

    Menu() {
        f = new JFrame("CALORIES TRACKER");
        f.setSize(800, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);

        JPanel gradientPanel = new JPanel() {
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
        gradientPanel.setBounds(0, 0, 800, 500);
        f.add(gradientPanel);

        // Search Panel
        searchField = new JTextField();
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBounds(20, 10, 740, 30);
        gradientPanel.add(searchField);

        // Scrollable mainPanel for food items
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(20, 80, 740, 400);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        gradientPanel.add(scrollPane);

        int y = 0;
        for (Food food : foods) {
            JPanel foodPanel = createFoodPanel(food);
            foodPanel.setBounds(0, y, 720, 160);
            foodPanels.add(foodPanel);
            mainPanel.add(foodPanel);
            y += 170;
        }

        mainPanel.setPreferredSize(new Dimension(720, y));

        // Add listener for search
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filterFoods(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filterFoods(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filterFoods(); }
        });
        
        JButton viewCartButton = new JButton("View Cart");
        viewCartButton.setBounds(20, 50, 120, 30);

        viewCartButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        viewCartButton.setBackground(Color.WHITE);
        viewCartButton.setForeground(new Color(72, 153, 90));
        viewCartButton.addActionListener(e -> {
            new ViewTotalCalories();
        });
        gradientPanel.add(viewCartButton);

        f.setVisible(true);
    }

    private JPanel createFoodPanel(Food food) {
        JPanel foodPanel = new JPanel(null);
        foodPanel.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 1, true), new EmptyBorder(10, 10, 10, 10)));
        foodPanel.setBackground(new Color(255, 255, 255, 180));
        foodPanel.setName(food.name.toLowerCase());

        // Image
        ImageIcon icon = new ImageIcon(getClass().getResource("/" + food.img));
        Image image = icon.getImage();
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        imagePanel.setBounds(10, 10, 150, 140);
        foodPanel.add(imagePanel);

     
        JLabel name = new JLabel("Name: " + food.name);
        JLabel carbs = new JLabel("Carbs: " + food.carbs + "g");
        JLabel protein = new JLabel("Protein: " + food.protein + "g");
        JLabel fat = new JLabel("Fat: " + food.fat + "g");
        JLabel total = new JLabel("Total Calories: " + food.totalcalories + " kcal");

        name.setBounds(170, 10, 300, 20);
        carbs.setBounds(170, 35, 300, 20);
        protein.setBounds(170, 60, 300, 20);
        fat.setBounds(170, 85, 300, 20);
        total.setBounds(170, 110, 300, 20);

        foodPanel.add(name);
        foodPanel.add(carbs);
        foodPanel.add(protein);
        foodPanel.add(fat);
        foodPanel.add(total);
        
        


        JButton addButton = new JButton("Add");
        addButton.setBounds(500, 55, 100, 30);
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        addButton.setBackground(Color.WHITE);
        addButton.setForeground(new Color(72, 153, 90));
        addButton.addActionListener(e -> {
    

        boolean isAdded = CaloriesCart.Add(food.name, food.totalcalories); // Call Add method on the cart instance

        if (isAdded) {
            JOptionPane.showMessageDialog(f, food.name + " added to your list!"); // Show success message
        } else {
            JOptionPane.showMessageDialog(f, "Cart is full!"); // Show cart full message
        }
    });

        foodPanel.add(addButton);
        
        

        return foodPanel;
        
        
    }

    private void filterFoods() {
        String searchText = searchField.getText().trim().toLowerCase();
        mainPanel.removeAll();

        int y = 0;
        for (JPanel panel : foodPanels) {
            if (panel.getName().contains(searchText)) {
                panel.setBounds(0, y, 720, 160);
                mainPanel.add(panel);
                y += 170;
            }
        }

        mainPanel.setPreferredSize(new Dimension(720, y));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        new Menu();
    }
}
