import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class contractProvider extends JDialog{
    private JTextField tfProviderId;
    private JPanel providerContractPanel;
    private JTextField tfPrice;
    private JTextField tfActivated;
    private JTextField tfExpires;
    private JButton updateButton;
    private JTextField tfContractNumber;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton searchButton;
    private static JFrame contractProviderFrame;

    public static void main(String[] args) {
        JFrame frame = new JFrame("contractProvider");
        frame.setContentPane(new contractProvider().providerContractPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public contractProvider(){
        Connect();
        contractProviderFrame = new JFrame();
        contractProviderFrame.setVisible(true);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String providerId, price, activated, expires;

                providerId = tfProviderId.getText();
                price = tfPrice.getText();
                activated = tfActivated.getText();
                expires = tfExpires.getText();

                try{
                    pst = con.prepareStatement("insert into contract_provider(provider_id, price, firstDate, lastDate)values(?,?,?,?)");
                    pst.setString(1, providerId);
                    pst.setString(2,price);
                    pst.setString(3, activated);
                    pst.setString(4, expires);

                    pst.executeUpdate();

                    tfProviderId.setText("");
                    tfPrice.setText("");
                    tfActivated.setText("");
                    tfExpires.setText("");
                }
                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
                if (providerId.isEmpty() || price.isEmpty() || activated.isEmpty() || expires.isEmpty()){
                    JOptionPane.showMessageDialog(null,
                            "Please, enter all data!",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    JOptionPane.showMessageDialog(null,"This contract is created successfully!");
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String contractId = tfContractNumber.getText();
                    pst = con.prepareStatement("select provider_id, price, firstDate, lastDate from contract_provider where id = ?");
                    pst.setString(1, contractId);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String providerId = rs.getString(1);
                        String price = rs.getString(2);
                        String activated = rs.getString(3);
                        String expires = rs.getString(4);

                        tfProviderId.setText(providerId);
                        tfPrice.setText(price);
                        tfActivated.setText(activated);
                        tfExpires.setText(expires);
                        if(providerId.isEmpty() || price.isEmpty() || activated.isEmpty() || expires.isEmpty()){
                            JOptionPane.showMessageDialog(null,
                                    "Please, enter all data!",
                                    "Try again",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    else
                    {
                        tfProviderId.setText("");
                        tfPrice.setText("");
                        tfActivated.setText("");
                        tfExpires.setText("");
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
                String providerId, price, activated, expires, contractId;
                providerId = tfProviderId.getText();
                price = tfPrice.getText();
                activated = tfActivated.getText();
                expires = tfExpires.getText();
                contractId = tfContractNumber.getText();

                try {

                    pst = con.prepareStatement("update contract_provider set provider_id = ?, price = ?, firstDate = ?, lastDate = ? where id = ?");
                    pst.setString(1, providerId);
                    pst.setString(2, price);
                    pst.setString(3, activated);
                    pst.setString(4, expires);
                    pst.setString(5, contractId);

                    pst.executeUpdate();

                    tfProviderId.setText("");
                    tfPrice.setText("");
                    tfActivated.setText("");
                    tfExpires.setText("");
                    tfProviderId.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
                if(providerId.isEmpty() || price.isEmpty() || activated.isEmpty() || expires.isEmpty()){
                    JOptionPane.showMessageDialog(null,
                            "Please, enter all data!",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    JOptionPane.showMessageDialog(null, "The contract`s information is updated!");
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String providerId, price, activated, expires, contractId;
                providerId = tfProviderId.getText();
                price = tfPrice.getText();
                activated = tfActivated.getText();
                expires = tfExpires.getText();
                contractId = tfContractNumber.getText();
                String bid;
                bid = tfContractNumber.getText();
                try {
                    pst = con.prepareStatement("delete from contract_provider  where id = ?");
                    pst.setString(1, bid);

                    pst.executeUpdate();
                    if(providerId.isEmpty() || price.isEmpty() || activated.isEmpty() || expires.isEmpty()){
                        JOptionPane.showMessageDialog(null,
                                "Please, enter all data!",
                                "Try again",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "This contract is deleted successfully!");
                    }

                    tfProviderId.setText("");
                    tfPrice.setText("");
                    tfActivated.setText("");
                    tfExpires.setText("");
                    tfProviderId.requestFocus();

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
            System.out.println("Successful connection to the database! - provider`s contract");
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
