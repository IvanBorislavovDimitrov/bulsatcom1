import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class packages {
    private JTextField tfName;
    private JPanel packagesPanel;
    private JButton saveButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextField tfId;
    private JButton searchButton;
    private static JFrame packagesFrame;


    public static void main(String[] args) {
        JFrame frame = new JFrame("packages");
        frame.setContentPane(new packages().packagesPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public packages(){
        Connect();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name;

                name = tfName.getText();
                try{
                    pst = con.prepareStatement("insert into packages(name)values(?)");
                    pst.setString(1, name);
                    pst.executeUpdate();
                    tfName.setText("");
                }
                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
                if (name.isEmpty()){
                    JOptionPane.showMessageDialog(null,
                            "Please, enter all data!",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    JOptionPane.showMessageDialog(null,"This package is created successfully!");
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String id = tfId.getText();
                    pst = con.prepareStatement("select name from packages where id = ?");
                    pst.setString(1, id);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String name = rs.getString(1);
                        tfName.setText(name);
                        if(name.isEmpty() ){
                            JOptionPane.showMessageDialog(null,
                                    "Please, enter all data!",
                                    "Try again",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    else
                    {
                        tfName.setText("");
                        JOptionPane.showMessageDialog(null,"This ID does not exists!");
                    }

                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, id;
                name = tfName.getText();
                id = tfId.getText();

                try {

                    pst = con.prepareStatement("update packages set name = ? where id = ?");
                    pst.setString(1, name);
                    pst.setString(2, id);

                    pst.executeUpdate();

                    tfName.setText("");
                    tfId.setText("");

                    tfName.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
                if(name.isEmpty()){
                    JOptionPane.showMessageDialog(null,
                            "Please, enter all data!",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    JOptionPane.showMessageDialog(null, "The package`s information is updated!");
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, bid;
                name = tfName.getText();
                bid = tfId.getText();
                try {
                    pst = con.prepareStatement("delete from packages  where id = ?");
                    pst.setString(1, bid);

                    pst.executeUpdate();
                    if(name.isEmpty()){
                        JOptionPane.showMessageDialog(null,
                                "Please, enter all data!",
                                "Try again",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "This contract is deleted successfully!");
                    }

                    tfName.setText("");
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }

            }
        });
    }
    Connection con;
    PreparedStatement pst;

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bulsatcom", "root", "RumenVr01");
            System.out.println("Successful connection to the database! - packages");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}