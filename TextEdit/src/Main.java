import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.*;

class editor extends JFrame implements  ActionListener {
     //create the text area

    JTextArea t;
    //creating the frame to accoadate the text area and the menubar

    JFrame f;

    editor(){

          //initializing the frame
        f = new JFrame("TextEdit");

          //setting hte overall theme of the application
        try{
            UIManager.setLookAndFeel("java.swing.plaf.metal.MetallicLookandFeel");

            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch(Exception e){

        }
        //initializing the text area
        t = new JTextArea();

        //initializing the menubar
        JMenuBar m = new JMenuBar();

        //initializing file menu
        JMenu f1 = new JMenu("file");

        //create the individual menu item
        JMenuItem m1 = new JMenuItem("New");
        JMenuItem m2 = new JMenuItem("Open");
        JMenuItem m3 = new JMenuItem("Save");
        JMenuItem m4 = new JMenuItem("Print");

        //adding the action listner
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);

        //adding the menu items to the file menu
        f1.add(m1);
        f1.add(m2);
        f1.add(m3);
        f1.add(m4);

        JMenu f2 = new JMenu("Edit");

        JMenuItem m5 = new JMenuItem("Cut");
        JMenuItem m6 = new JMenuItem("Copy");
        JMenuItem m7 = new JMenuItem("Paste");

        m5.addActionListener(this);
        m5.addActionListener(this);
        m5.addActionListener(this);

        f2.add(m5);
        f2.add(m6);
        f2.add(m7);

        JMenuItem c = new JMenuItem("Exit");
        c.addActionListener(this);

        m.add(f1);
        m.add(f2);
        m.add(c);

        f.add(t);
        f.setJMenuBar(m);
        f.setSize(500,500);
        f.show();



    }
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();

        if(s.equals("New")){
            t.setText("");
        }
        else if(s.equals("Open")){
            JFileChooser j = new JFileChooser("c:");
            int r = j.showOpenDialog(null);

            if(r == JFileChooser.APPROVE_OPTION){

                File fi = new File(j.getSelectedFile().getAbsolutePath());
                try{
                    String s1 ="", s2 = "";

                    FileReader fr = new FileReader(fi);
                    BufferedReader br = new BufferedReader(fr);

                    s2 = br.readLine();

                    while((s1 = br.readLine())!= null){
                        s2 = s2 + "\n" + s1;
                    }

                    t.setText(s2);

                }
                catch(Exception et){
                    JOptionPane.showMessageDialog(f,et.getMessage());
                }
            }
            else{
                JOptionPane.showMessageDialog(f,"operation cancelled");
            }
        }
        else if(s.equals("Save")){
            JFileChooser j = new JFileChooser("c:");
            int r = j.showSaveDialog(null);

            if(r == JFileChooser.APPROVE_OPTION){

                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try{
                    FileWriter wr = new FileWriter(fi);

                    BufferedWriter bw = new BufferedWriter(wr);

                    bw.write(t.getText());

                    bw.flush();
                    bw.close();
                }
                catch(Exception et){
                    JOptionPane.showMessageDialog(f,et.getMessage());
                }
            }
            else{
                JOptionPane.showMessageDialog(f,"operation cancelled");
            }
        }
        else if(s.equals("Print")){
            try{
                t.print();
            }
            catch(Exception et){
                JOptionPane.showMessageDialog(f,et.getMessage());
            }
        }
        else if(s.equals("Cut")){
            t.cut();
        }
        else if(s.equals("Copy")){
            t.copy();
        }
        else if(s.equals("Paste")){
            t.paste();
        }
        else if(s.equals("Exit")){
            f.setVisible(false);
        }
    }

    public static void main(String args[]){
        editor e = new editor();
    }
}

