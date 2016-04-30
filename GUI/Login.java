package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.AuthLogin;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUserName;
	private JPasswordField passwordField;
	private AuthLogin[] logins;	//logins read from file on construction
	private AuthLogin returnedLogin = null; //which user was logged in, will be NULL if user chooses to close the dialog

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		AuthLogin staffmember = null;

		try {
			Login l = new Login();
		
			l.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//dialog.setModal(true);
			l.setVisible(true);
			staffmember = l.getLogin();
			//JOptionPane.showInputDialog(dialog);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(null != staffmember)
		{System.out.printf("User %s validated", staffmember.getUsername());} else
		{System.out.println("no login selected; user closed");}
		
	}
	
	
	/**
	 * Create the dialog.
	 */
	public Login() {
		setModal(true);
		logins = AuthLogin.readFile();
		
		setTitle("Gym");
		setBounds(100, 100, 419, 179);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{87, 255, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Staff Member Login");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
			gbc_lblNewLabel.gridwidth = 2;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lblUsername = new JLabel("Username:");
			GridBagConstraints gbc_lblUsername = new GridBagConstraints();
			gbc_lblUsername.anchor = GridBagConstraints.EAST;
			gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
			gbc_lblUsername.gridx = 0;
			gbc_lblUsername.gridy = 1;
			contentPanel.add(lblUsername, gbc_lblUsername);
		}
		{
			txtUserName = new JTextField();
			GridBagConstraints gbc_txtUserName = new GridBagConstraints();
			gbc_txtUserName.insets = new Insets(0, 0, 5, 5);
			gbc_txtUserName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtUserName.gridx = 1;
			gbc_txtUserName.gridy = 1;
			contentPanel.add(txtUserName, gbc_txtUserName);
			txtUserName.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password:");
			lblPassword.setVerticalAlignment(SwingConstants.BOTTOM);
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.anchor = GridBagConstraints.EAST;
			gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
			gbc_lblPassword.gridx = 0;
			gbc_lblPassword.gridy = 2;
			contentPanel.add(lblPassword, gbc_lblPassword);
		}
		{
			passwordField = new JPasswordField();
			GridBagConstraints gbc_passwordField = new GridBagConstraints();
			gbc_passwordField.insets = new Insets(0, 0, 0, 5);
			gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
			gbc_passwordField.gridx = 1;
			gbc_passwordField.gridy = 2;
			contentPanel.add(passwordField, gbc_passwordField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Login");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						login(e);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			
			
		}
		
		
	}
	
	public AuthLogin getLogin(){return this.returnedLogin;}

	
	/*
	 * Verifies Login information
	 */
	private void login(ActionEvent e)
	{	
		if (e.getActionCommand() != "OK") return;
		
		String u = this.txtUserName.getText(), pw = new String(this.passwordField.getPassword());
		returnedLogin = AuthLogin.checkLogin(logins, u, pw); //verify staff member
		
		if(null == returnedLogin)
		{
			JOptionPane.showMessageDialog(this, "Error in user name or password", this.getTitle(),
					JOptionPane.ERROR_MESSAGE);
		}
		else{	
			this.setVisible(false);
			this.dispose();
		}
	}
}
