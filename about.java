package notepad;
import javax.swing.*;



public class about extends JFrame{
    about()
    {
        setBounds(100,100,500,400);
        setTitle("About NotePad");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        ImageIcon icon = new ImageIcon(getClass().getResource("icons8-notepad-50.png"));
        setIconImage(icon.getImage());
        
        setLayout(null);
        
        JLabel iconLable = new JLabel(new ImageIcon(getClass().getResource("icons8-notepad-50.png")));
        iconLable.setBounds(100,50,80,80);
        add(iconLable);
        
        JLabel textLabel = new JLabel("<html> Welcome to NotePad<br>NotePad is simple text editor for Microsoft Window and basic text-editing program which enables computer user to create documents. This one is created by Muskan Singh for project purpose<br>All rights reserved@2023</html>");
        textLabel.setBounds(100,50,350,300);
        
        add(textLabel);
        
        
    }
    
    public static void main(String args[])
    {
        new about().setVisible(true);
    }
}
