package GUI;


import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import java.awt.Component;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import swing.FocusTraversalOnArray;

import model.Member;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//This will be an all encompassing tool, which supports editing and viewing
public class InputMember extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField phoneNumber;
	private JTextField email;
	private JTextField lName;
	private JTextField fName;
	private JTextField address;
	private JTextComponent[] fields;
	private Member member;	
	private JTextPane registeredFor;
	private JButton okButton;
	private JButton cancelButton;public Member getMember(){return member;}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InputMember dialog = new InputMember();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void readFields()
	{
		fName.setText(member.getfName());
		lName.setText(member.getlName());
		email.setText(member.getEmail());
		phoneNumber.setText(member.getPhone());
		address.setText(
				String.format("%s %s %s %s",
						member.getStreet(),
						member.getCity(),
						member.getState(),
						member.getZip())		
				);
//		try{
//		registeredFor.getDocument().insertString(0, member.getTrainerID(), null);
//		}
//		catch()
//		
		//TODO: resolve ID to name
		registeredFor.setText(member.getTrainerID());
	}
	
	private boolean writeFields()
	{//TODO parse fields, validate
		String addr = this.address.getText();
		int endStreet = addr.lastIndexOf(' ', 
						addr.lastIndexOf(' ',
						addr.lastIndexOf(' ')-1)-1);
		//preferably use regex to get match of last three elements, but this will work
		
		String err="";
		String street = null;
		String lastFields[] = null;
		if (-1 == endStreet) { err +="Address format needs to be\n" +
		                             "#### StreetName Ln/Blvd/St/etc City State Zipcode"; } 
		else{
			street = addr.substring(0, endStreet);
			lastFields = addr.substring(endStreet+1).split("\\s");
			if( null==lastFields ||  3 != lastFields.length ) {err +="\n needs city state and zipcode\n";}
		}
		
		if(err.length() == 0)	try{
			if(null==member)
			{
				member = new Member(
							fName.getText(),
							lName.getText(),
							email.getText(),
							phoneNumber.getText(),
							street,
							lastFields[0],
							lastFields[1],
							lastFields[2]
						);
			}else{
				err += member.setfName(fName.getText()) ? "" : "First Name not properly formatted\n";
				err += member.setlName(lName.getText()) ? "" : "Last Name not properly formatted\n" ;
				err += member.setEmail(email.getText()) ? "" : "Invalid email\n";
				err += member.setPhone(phoneNumber.getText()) ? "" : "incomplete phone number\n";
				err += member.setStreet(street) ? "" : "Street Address improperly formatted\n";
				err += member.setCity(lastFields[0]) ? "" : "City has non-alphabetical characaters\n";
				err += member.setZip(lastFields[2]) ? "" : "Not a valid zip code\n";
			}
		}catch
		(IllegalArgumentException e)
		{
			err += '\n' + e.getMessage();
		}
		if (err.length() > 0)
		{
			JOptionPane.showMessageDialog(this, err, this.getTitle(), JOptionPane.ERROR_MESSAGE);
			return false;
		}else{return true;}
	}
	
	public InputMember(Member member, boolean readOnly)
	{	this();
		this.member = member;
		if(null != member)	{readFields();}
		if(readOnly){ 
			for(JTextComponent t : fields)
				{t.setEditable(false);}
			cancelButton.setVisible(false);
			//okButton.removeActionListener(okButton.);
			okButton.setActionCommand("Cancel");
		}
	
		registeredFor.setVisible(true);
		//TODO: add registered trainer and classes to textpane

	}
	

	

	//button handler
	ActionListener buttonListener = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equals("Cancel"))
			{
				member=null;
			} else
			if(arg0.getActionCommand().equals("OK"))
			{
				if(!writeFields()){ return; }
			}
			setVisible(false);
			
		}
	};
	/**
	 * Create the dialog.
	 */
	public InputMember() {
		
		setModal(true);
		setBounds(100, 100, 450, 350);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		getContentPane().setLayout(borderLayout);
		contentPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("First Name:");
			lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			fName = new JTextField();
			GridBagConstraints gbc_fName = new GridBagConstraints();
			gbc_fName.insets = new Insets(0, 0, 5, 0);
			gbc_fName.fill = GridBagConstraints.HORIZONTAL;
			gbc_fName.gridx = 1;
			gbc_fName.gridy = 0;
			contentPanel.add(fName, gbc_fName);
		}
		{
			JLabel lblNewLabel = new JLabel("Last Name:");
			lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			lName = new JTextField();
			GridBagConstraints gbc_lName = new GridBagConstraints();
			gbc_lName.insets = new Insets(0, 0, 5, 0);
			gbc_lName.fill = GridBagConstraints.HORIZONTAL;
			gbc_lName.gridx = 1;
			gbc_lName.gridy = 1;
			contentPanel.add(lName, gbc_lName);
		}
		{
			JLabel lblNewLabel = new JLabel("Email:");
			lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 2;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			email = new JTextField();
			GridBagConstraints gbc_email = new GridBagConstraints();
			gbc_email.insets = new Insets(0, 0, 5, 0);
			gbc_email.fill = GridBagConstraints.HORIZONTAL;
			gbc_email.gridx = 1;
			gbc_email.gridy = 2;
			contentPanel.add(email, gbc_email);
		}
		{
			JLabel lblNewLabel = new JLabel("Phone Number:");
			lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 3;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		try{
		
		phoneNumber = new JFormattedTextField(
					new MaskFormatter("###-###-####")
				);
		}catch(ParseException e){System.err.println("failed to add formatter " + e.getMessage());}
		GridBagConstraints gbc_phoneNumber = new GridBagConstraints();
		gbc_phoneNumber.insets = new Insets(0, 0, 5, 0);
		gbc_phoneNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneNumber.gridx = 1;
		gbc_phoneNumber.gridy = 3;
		contentPanel.add(phoneNumber, gbc_phoneNumber);
		{
			JLabel lblNewLabel = new JLabel("Address:");
			lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 4;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			address = new JTextField();
			GridBagConstraints gbc_address = new GridBagConstraints();
			gbc_address.insets = new Insets(0, 0, 5, 0);
			gbc_address.fill = GridBagConstraints.HORIZONTAL;
			gbc_address.gridx = 1;
			gbc_address.gridy = 4;
			contentPanel.add(address, gbc_address);
		}
		{
			registeredFor = new JTextPane();
			registeredFor.setVisible(false);
			registeredFor.setEditable(false);
			GridBagConstraints gbc_registeredFor = new GridBagConstraints();
			gbc_registeredFor.gridwidth = 2;
			gbc_registeredFor.fill = GridBagConstraints.BOTH;
			gbc_registeredFor.gridx = 0;
			gbc_registeredFor.gridy = 5;
			contentPanel.add(registeredFor, gbc_registeredFor);
		}
		contentPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{fName, lName, email, phoneNumber, address}));

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(buttonListener);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(buttonListener);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		fields = new JTextComponent[]{fName, lName, email, phoneNumber, address};
	}

	
}
