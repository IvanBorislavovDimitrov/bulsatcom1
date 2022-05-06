import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class channels extends JDialog{
    private JTextField tfName;
    private JTextField tfContract;
    private JTextField tfCategory;
    private JTextField tfPrice;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JTextField tfId;
    private JButton searchButton;
    private JPanel channelsPanel;
    private static JFrame channelFrame;

    public static void main(String[] args) {
//        JFrame channelFrame = new JFrame("channels");
//        channelFrame.setContentPane(new channels().channelsPanel);
//        channelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        channelFrame.pack();
//        channelFrame.setVisible(true);
    }
    public channels(){

        Connect();

        channelFrame = new JFrame("channels");
        channelFrame.setContentPane(channelsPanel);
        channelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        channelFrame.pack();
        channelFrame.setVisible(true);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name,contract,category, price;

                name = tfName.getText();
                contract = tfContract.getText();
                category = tfCategory.getText();
                price = tfPrice.getText();

                try {
                    pst = con.prepareStatement("insert into channels(name,contract_provider, category, price)values(?, ?,?,?)");
                    pst.setString(1, name);
                    pst.setString(2, contract);
                    pst.setString(3, category);
                    pst.setString(4, price);

                    pst.executeUpdate();

                    tfName.setText("");
                    tfContract.setText("");
                    tfCategory.setText("");
                    tfPrice.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
                if(name.isEmpty() || contract.isEmpty() || category.isEmpty() || price.isEmpty()){
                    JOptionPane.showMessageDialog(null,
                            "Please, enter all data!",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    JOptionPane.showMessageDialog(null,"This channel is added successfully!");
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String pid = tfId.getText();
                    pst = con.prepareStatement("select name, contract_provider, category, price from channels where id = ?");
                    pst.setString(1, pid);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String name = rs.getString(1);
                        String contract = rs.getString(2);
                        String category = rs.getString(3);
                        String price = rs.getString(4);

                        tfName.setText(name);
                        tfContract.setText(contract);
                        tfCategory.setText(category);
                        tfPrice.setText(price);
                        if(name.isEmpty() || contract.isEmpty() || category.isEmpty() || price.isEmpty()){
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
                        tfContract.setText("");
                        tfCategory.setText("");
                        tfPrice.setText("");
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
                String pid, name, contract, category, price;

                name = tfName.getText();
                contract = tfContract.getText();
                category = tfCategory.getText();
                price = tfPrice.getText();
                pid = tfId.getText();


                try {

                    pst = con.prepareStatement("update channels set name = ?, contract_provider = ?, category = ?, price = ? where id = ?");
                    pst.setString(1, name);
                    pst.setString(2, contract);
                    pst.setString(3, category);
                    pst.setString(4, price);
                    pst.setString(5, pid);

                    pst.executeUpdate();

                    tfName.setText("");
                    tfContract.setText("");
                    tfCategory.setText("");
                    tfPrice.setText("");
                    tfName.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
                if(name.isEmpty() || contract.isEmpty() || category.isEmpty() || price.isEmpty()){
                    JOptionPane.showMessageDialog(null,
                            "Please, enter all data!",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    JOptionPane.showMessageDialog(null, "The channel`s information is updated!");
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bid;

                bid = tfId.getText();


                try {
                    pst = con.prepareStatement("delete from channels  where id = ?");
                    pst.setString(1, bid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "This channel is deleted successfully!");

                    tfName.setText("");
                    tfContract.setText("");
                    tfCategory.setText("");
                    tfName.requestFocus();
                    tfPrice.setText("");

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

    public void Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bulsatcom", "root","RumenVr01");
            System.out.println("Successful connection to the database! - channels");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

}