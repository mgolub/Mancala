package mancala;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NameFrame extends JFrame{
	
	private String name1, name2;
	private JLabel enter1, enter2;
	private JTextField input1, input2;
	private JButton okay;
	
	public NameFrame(){
		setTitle("Add Names");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setLocationRelativeTo(null);
		
		
		enter1 = new JLabel("Enter player1 name:");
		enter2 = new JLabel("Enter player2 name:");
		
		
		input1 = new JTextField(200);
		input2 = new JTextField(200);
		
		okay = new JButton("Ready to play!");
		addListener();
		
		addComponents();		
		
	}

	private void addComponents() {
		enter1.setSize(50, 10);
		add(enter1);
		
		add(input1);
		add(enter2);
		enter2.setSize(50, 10);
		add(input2);
		add(okay);
	}

	private void addListener() {
		okay.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				name1 = input1.getText().trim();
				name2 = input2.getText().trim();
				dispose();
				GUI2 gui = new GUI2(name1, name2);
				gui.setVisible(true);
			}
			
		});
		
	}
	
	public static void main(String[] args) {
		new NameFrame().setVisible(true);
	}

}
