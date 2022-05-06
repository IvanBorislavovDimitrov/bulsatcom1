import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class providers extends JDialog {
    private JTextField tfName;
    private JButton updateButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JTextField tfId;
    private JButton searchButton;
    private JPanel providers;
    private static JFrame providerFFrame;

    public static void main(String[] args) {
        JFrame frame = new JFrame("providers");
        frame.setContentPane(new providers().providers);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public providers(){
        Connect();
        providerFFrame = new JFrame();
        providerFFrame.setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name;

                name = tfName.getText();

                try {
                    pst = con.prepareStatement("insert into providers (name)values(?)");
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
                    JOptionPane.showMessageDialog(null,"This provider is added successfully!");
                }

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String providerId = tfId.getText();
                    pst = con.prepareStatement("select name from providers where id = ?");
                    pst.setString(1, providerId);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String name = rs.getString(1);
                        tfName.setText(name);

                        if(name.isEmpty()){
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
                String name, providerId;
                name = tfName.getText();
                providerId = tfId.getText();
                try {

                    pst = con.prepareStatement("update providers set name = ? where id = ?");
                    pst.setString(1, name);
                    pst.setString(2, providerId);

                    pst.executeUpdate();

                    tfName.setText("");
                    tfId.setText("");
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
                    JOptionPane.showMessageDialog(null, "The provider`s information is updated!");
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bid;
                bid = tfId.getText();
                try {
                    pst = con.prepareStatement("delete from providers  where id = ?");
                    pst.setString(1, bid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "This provider is deleted successfully!");

                    tfId.setText("");
                    tfName.setText("");
                    tfName.requestFocus();

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
            System.out.println("Successful connection to the database! - providers");
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
