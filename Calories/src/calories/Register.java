package calories;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
public class Register {
    JFrame f;
    JPanel p1,p2;
    
    JLabel lblLog,lblusername,lblpassword,lblregister;
    JTextField txtusername,txtpassword;
    JButton btnregister;
    ImageIcon icon;
    Image image;
   
    
    Register() {
        f = new JFrame("CALORIES TRACKER");
        f.setSize(800, 500); // Landscape mode
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        icon = new ImageIcon(getClass().getResource("/logofood.jpg"));
        image = icon.getImage();
        p1 = new JPanel() { 
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    };
        p1.setBounds(0, 0, 350, 500);
        p1.setLayout(null);
        f.add(p1);
        
        
        

        p2 = new JPanel(){
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
        p2.setBounds(350, 0, 450, 500);
        p2.setLayout(null);
        f.add(p2);
        
        lblLog = new JLabel("Register");
        lblLog.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblLog.setForeground(Color.WHITE);
        lblLog.setBounds(160, 50, 150, 45);
        p2.add(lblLog);

        lblusername = new JLabel("Username:");
        lblusername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblusername.setForeground(Color.WHITE);
        lblusername.setBounds(80, 120, 100, 25);
        p2.add(lblusername);

        txtusername = new JTextField();
        txtusername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtusername.setBounds(80, 150, 280, 30);
        p2.add(txtusername);

        lblpassword = new JLabel("Password:");
        lblpassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblpassword.setForeground(Color.WHITE);
        lblpassword.setBounds(80, 200, 100, 25);
        p2.add(lblpassword);

        txtpassword = new JPasswordField();
        txtpassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtpassword.setBounds(80, 230, 280, 30);
        p2.add(txtpassword);


        btnregister = new JButton("Register");
        btnregister.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnregister.setBackground(new Color(255, 255, 255));
        btnregister.setForeground(new Color(72, 153, 90)); 
        btnregister.setBounds(80, 290, 280, 40);
        p2.add(btnregister);
        
        btnregister.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String username = txtusername.getText();
                String password = txtpassword.getText();

                String result = User.register(username, password);

                if (result.equals("SUCCESS")) {
                    JOptionPane.showMessageDialog(null, "User registered successfully", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, result, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        lblregister = new JLabel("<HTML><U>Have an account? Login</U></HTML>");
        lblregister.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblregister.setForeground(Color.WHITE);
        lblregister.setBounds(130, 340, 250, 25);
        lblregister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        p2.add(lblregister);
        
        lblregister.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                f.dispose();// to close current window
                Calories c = new Calories();
            }
        });

        f.setVisible(true);
    }
    public static void main(String[] args) {
       new Register();
    }
}
