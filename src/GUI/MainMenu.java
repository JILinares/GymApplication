//@Author Jonathan Eddy jeddy6@gmu.edu G00801804
package GUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenu extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu dialog = new MainMenu();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private String action=null;
	
	/*
	 * returns the selected action, if any
	 * either Create, View, Update, Class, Trainer or Exit
	 */
	public String getAction(){return action;}
	
	private JButton[] buttons = new JButton[6];
	
	ActionListener listener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e) {
			action=e.getActionCommand();
			//System.out.println(action);
			//JButton button = (JButton)e.getSource();
			setVisible(false);
			
		}
	};
	

	  //Create the dialog.

	public MainMenu() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if(action==null){action ="Exit";}
			}
			@Override
			public void windowActivated(WindowEvent arg0)
			{
				action=null;
			}
		});
		setTitle("Gym");
		setModal(true);
		setBounds(100, 100, 260, 351);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		Component verticalGlue_7 = Box.createVerticalGlue();
		getContentPane().add(verticalGlue_7);
		
		JLabel lblPleaseChooseAn = new JLabel("Please choose an Option");
		lblPleaseChooseAn.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(lblPleaseChooseAn);
		
		Component verticalGlue = Box.createVerticalGlue();
		getContentPane().add(verticalGlue);
		
		JButton CreateMember = new JButton("Create Membership Account");
		CreateMember.setActionCommand("Create");
		buttons[0]=CreateMember;
		
		CreateMember.setBounds(new Rectangle(0, 0, 300, 0));
		CreateMember.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(CreateMember);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		getContentPane().add(verticalGlue_1);
		
		JButton ViewInfo = new JButton("View Member Information");
		ViewInfo.setActionCommand("View");
		ViewInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(ViewInfo);
		buttons[1]=ViewInfo;
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		getContentPane().add(verticalGlue_2);
		
		
		JButton UpdateMember = new JButton("Update Member Information");
		UpdateMember.setActionCommand("Update");
		UpdateMember.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(UpdateMember);
		buttons[2]=UpdateMember;
		
		Component verticalGlue_3 = Box.createVerticalGlue();
		getContentPane().add(verticalGlue_3);
		
		JButton ClassSignUp = new JButton("Sign-Up Member for Class");
		ClassSignUp.setActionCommand("Class");
		ClassSignUp.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(ClassSignUp);
		buttons[3]=ClassSignUp;
		
		Component verticalGlue_4 = Box.createVerticalGlue();
		getContentPane().add(verticalGlue_4);
		
		JButton TrainerSignUp = new JButton("Sign-Up Member for Trainer");
		TrainerSignUp.setActionCommand("Trainer");
		TrainerSignUp.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(TrainerSignUp);
		buttons[4]=TrainerSignUp;
		
		Component verticalGlue_5 = Box.createVerticalGlue();
		getContentPane().add(verticalGlue_5);
		
		JButton Exit = new JButton("Exit");
		Exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(Exit);
		buttons[5]=Exit;
		
		Component verticalGlue_6 = Box.createVerticalGlue();
		getContentPane().add(verticalGlue_6);
		
		for(JButton button : buttons){button.addActionListener(listener);}
		
	}

}
