/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FarmInKedah;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public final class FarmInKedah {
    
    
 ArrayList<Cattle> cattlelist = new ArrayList<Cattle>();
    String header[] = new String[]{"Cattle ID","Weght(Kg)", "Breed Type", "Date Register"};
    DefaultTableModel dtm = new DefaultTableModel(header, 1);
    
    FarmInKedah() {

        JFrame frame = new JFrame("A Farm In Kedah");
        frame.setSize(450, 450);
        frame.getContentPane().setBackground( Color.lightGray );
        
        JLabel jlabel = new JLabel("Cattle ID");
        jlabel.setBounds(110, 10, 80, 20);
        frame.add(jlabel);
        
        
        JLabel jlabel2= new JLabel("Weght(Kg)");
        jlabel2.setBounds(110, 30, 80, 20);
        frame.add(jlabel2);
        
        JLabel jlabel3= new JLabel("Breed Type");
        jlabel3.setBounds(110, 50, 80, 20);
        frame.add(jlabel3);
        
        JLabel jlabel4 = new JLabel("Date Register");
        jlabel4.setBounds(110, 70, 80, 20);
        frame.add(jlabel4);
        
        JTextField jtfid = new JTextField();
        jtfid.setBounds(190, 10, 150, 20);
        frame.add(jtfid);
        
        JTextField jtfKg = new JTextField();
        jtfKg.setBounds(190, 30, 150, 20);
        frame.add(jtfKg);
        
        
        JTextField jtftype = new JTextField();
        jtftype.setBounds(190, 50, 150, 20);
        frame.add(jtftype);
        
        JTextField jtfdate = new JTextField();
        jtfdate.setBounds(190, 70, 150, 20);
        frame.add(jtfdate);
        
        JButton jbuttoninsert = new JButton("INSERT");
        jbuttoninsert.setBounds(190, 100, 90, 20);
        frame.add(jbuttoninsert);
        jbuttoninsert.setBackground(Color.WHITE);

     

        //table creation
        JTable jtable = new JTable();
        jtable.setBounds(45, 140, 350, 250);
        frame.add(jtable);
        jtable.setModel(dtm);
        JScrollPane scrollPane = new JScrollPane(jtable);
        scrollPane.setBounds(45, 140, 350, 250);
        frame.add(scrollPane);
        jtable.getColumnModel().getColumn(0).setPreferredWidth(80);
        jtable.getColumnModel().getColumn(1).setPreferredWidth(80);
        jtable.getColumnModel().getColumn(2).setPreferredWidth(120);
        jtable.getColumnModel().getColumn(3).setPreferredWidth(100);
        
        jtable.setBackground(Color.LIGHT_GRAY);
        
        
        jbuttoninsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                String id = jtfid.getText();
                
                if (!isInteger(id)) {
                    JOptionPane.showMessageDialog(frame, "Cattle ID should only contain integer!!!");
                    return;
                }
                String Kg = jtfKg.getText();
                
                if (!isInteger(Kg)) {
                    JOptionPane.showMessageDialog(frame, "Weight should only contain integer!!!");
                    return;
                }
                
                String type = jtftype.getText().toUpperCase();
                String date = jtfdate.getText();
                
               
                
                Cattle cattle = new Cattle( id,Kg,type, date);
                cattlelist.add(cattle);//create object list array
                writeData();
            }
        }); 
  
        

        readData();
        jtable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jtable.getSelectedRow();
                
                jtfid.setText(dtm.getValueAt(row, 0).toString());
                jtfKg.setText(dtm.getValueAt(row, 1).toString());
                jtftype.setText(dtm.getValueAt(row, 2).toString());
                jtfdate.setText(dtm.getValueAt(row, 3).toString());
            }
        });

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new FarmInKedah();

    }

    void readData() { 
        try {
            File file = new File("data.txt"); 
            file.createNewFile();
            FileReader f = new FileReader("data.txt");
            StringBuffer sb = new StringBuffer();
            while (f.ready()) {
                char c = (char) f.read();
                if (c == '-') {
                    System.out.println(sb);
                    String cattlearray[] = sb.toString().split(",");
                    Cattle cattle = new Cattle(cattlearray[0], cattlearray[1], cattlearray[2], cattlearray[3]);
                   cattlelist.add(cattle);
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
            dtm.setRowCount(0); 
            for (int i = 0; i < cattlelist.size(); i++) {
                Object[] objs = {cattlelist.get(i).getid(), cattlelist.get(i).getKg(), cattlelist.get(i).gettype(), cattlelist.get(i).getDate()};
                dtm.addRow(objs);
            }
        } catch (IOException e) {
        }
    }

    private void writeData() { 
        try (FileWriter f = new FileWriter("data.txt")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cattlelist.size(); i++) {
                sb.append(cattlelist.get(i).getid()+","+cattlelist.get(i).getKg()+","+cattlelist.get(i).gettype()+","+cattlelist.get(i).getDate()+"-");
            }
            f.write(sb.toString());
            f.close();
        } catch (IOException e) {
            return;
        }
        dtm.setRowCount(0); 
        for (int i = 0; i < cattlelist.size(); i++) {
            Object[] objs = {cattlelist.get(i).getid(),cattlelist.get(i).getKg(), cattlelist.get(i).gettype(), cattlelist.get(i).getDate()};
            dtm.addRow(objs);
        }
    }

    public boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
    
}

