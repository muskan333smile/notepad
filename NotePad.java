
package notepad;
import java.awt.Font;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;




public class NotePad  extends JFrame implements ActionListener{
    
    JMenuBar menubar = new JMenuBar();
    
    JMenu file=new JMenu("File");
    JMenu edit=new JMenu("Edit");
    JMenu help=new JMenu("Help");
    
    JMenuItem newfile = new JMenuItem("New");
    JMenuItem openfile = new JMenuItem("Open");
    JMenuItem savefile = new JMenuItem("Save");
    JMenuItem printfile = new JMenuItem("Print");
    JMenuItem exitfile = new JMenuItem("Exit");
    
    JMenuItem cut = new JMenuItem("Cut");
    JMenuItem copy = new JMenuItem("Copy");
    JMenuItem paste = new JMenuItem("Paste");
    JMenuItem selectall = new JMenuItem("SelectAll");
    
     JMenuItem about = new JMenuItem("About");
     JTextArea textarea = new JTextArea();
    NotePad()
    {
        setTitle("NotePad");
        setBounds(100,100,800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getResource("icons8-notepad-50.png"));
        setIconImage(icon.getImage());
        
        setJMenuBar(menubar);
        
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);
        
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);
        file.add(printfile);
        file.add(exitfile);
        
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        
        help.add(about);
        
        JScrollPane scrollpane = new JScrollPane(textarea);
        add(scrollpane);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());
        textarea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);
        printfile.addActionListener(this);
        
        exitfile.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        about.addActionListener(this);
        
        
//        shortcut keys
        newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N , KeyEvent.CTRL_DOWN_MASK));
        openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O , KeyEvent.CTRL_DOWN_MASK));
        savefile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S , KeyEvent.CTRL_DOWN_MASK));
        printfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P , KeyEvent.CTRL_DOWN_MASK));
        exitfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z , KeyEvent.CTRL_DOWN_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X , KeyEvent.CTRL_DOWN_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C , KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V , KeyEvent.CTRL_DOWN_MASK));
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A , KeyEvent.CTRL_DOWN_MASK));
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J , KeyEvent.CTRL_DOWN_MASK));
        
        
        
        
    }
    
//    main function
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new NotePad().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equalsIgnoreCase("New"))
        {
            textarea.setText(null);
        }else if(e.getActionCommand().equalsIgnoreCase("Open"))
        {
            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter textfilter = new FileNameExtensionFilter("only text files(.txt)","txt");
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.addChoosableFileFilter(textfilter);
            
            int action = filechooser.showOpenDialog(null);
            
            if(action!=JFileChooser.APPROVE_OPTION)
            {
                return;
            }
            else
            {
                try{
                BufferedReader reader = new BufferedReader(new FileReader(filechooser.getSelectedFile()));
                textarea.read(reader, null);
                   }catch(IOException ex)
                    {
                    ex.printStackTrace();
                    }
            }
            
            
            
        }else if(e.getActionCommand().equalsIgnoreCase("Save"))
        {
            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter textfilter = new FileNameExtensionFilter("only text files(.txt)","txt");
            filechooser.addChoosableFileFilter(textfilter);
            
            int action = filechooser.showSaveDialog(null);
            if(action!=JFileChooser.APPROVE_OPTION)
            {
                return;
            }else {
                String filename = filechooser.getSelectedFile().getAbsolutePath().toString();
                if(!filename.contains(".txt"))
                {
                    filename = filename + ".txt";
                }
                try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                textarea.write(writer);
                   }catch(IOException ex)
                    {
                    ex.printStackTrace();
                    }
            }
            
        }else if(e.getActionCommand().equalsIgnoreCase("Exit"))
        {
            System.exit(0);
        }else if(e.getActionCommand().equalsIgnoreCase("Cut"))
        {
            textarea.cut();
        }else if(e.getActionCommand().equalsIgnoreCase("Copy"))
        {
            textarea.copy();
        }else if(e.getActionCommand().equalsIgnoreCase("Print"))
        {
            try {
                textarea.print();
            } catch (PrinterException ex) {
                Logger.getLogger(NotePad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(e.getActionCommand().equalsIgnoreCase("Paste"))
        {
                textarea.paste();
        }else if(e.getActionCommand().equalsIgnoreCase("selectAll"))
        {
            textarea.selectAll();
        }else if(e.getActionCommand().equalsIgnoreCase("About"))
        {
            new about().setVisible(true);
        }
        
    }
    
}
