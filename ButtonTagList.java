package photo_renamer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

/**
 * The ButtonTagList class contains the GUI 
 * for adding or removing tags from the global
 * list of tags.
 */
public class ButtonTagList extends JFrame
{
	/**
	 * Create the frame.
	 */
	private JPanel contentPane;
	private JTextField textField;
	public String enteredTagName;
	JOptionPane optionPane = new JOptionPane();
	
	/**
	 * Constructor.
	 * 
	 *  Builds the GUI whenever the object of
	 *  ButtonTagList is created.
	 */
	public ButtonTagList()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 33, 153, 186);
		TagList tl = new TagList();
		ArrayList<String> ar = tl.readFile();
		for (String tags : ar)
		{
			textArea.append(tags + "\n");
		}
		textArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 33, 153, 186);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setBounds(213, 129, 132, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterTagName = new JLabel("Enter tag name from the global list");
		lblEnterTagName.setBounds(188, 92, 199, 26);
		contentPane.add(lblEnterTagName);
		
		JButton btnEditGlobalList = new JButton("Edit Global List");
		btnEditGlobalList.setBounds(301, 227, 120, 23);
		contentPane.add(btnEditGlobalList);
		
		TagList tagList = new TagList();
		
		if (Buttons.check1)
		{
			btnEditGlobalList.addActionListener(new ActionListener() 
			{
				
				@Override
				/**
				* Performs action when Add Tag List button is 
				* clicked.
				* 
				* @param ActionEvent e This parameter detects events experienced by button.
				* @return nothing.
				*/
				public void actionPerformed(ActionEvent e)
				{
					enteredTagName = textField.getText();
					if (!tagList.readFile().toString().contains(enteredTagName+"@"))
						tagList.addTag(enteredTagName); // adds tag to the global tag list.
					else
						optionPane.showMessageDialog(null, "Tag already exists!");
					dispose();
				}
			});
		}
		else
		{
			btnEditGlobalList.addActionListener(new ActionListener() 
			{
				
				@Override
				/**
				* Performs action when Add Tag List button is 
				* clicked.
				* 
				* @param ActionEvent e This parameter detects events experienced by button.
				* @return nothing.
				*/
				public void actionPerformed(ActionEvent e)
				{
					enteredTagName = textField.getText();
					if (tagList.readFile().toString().contains(enteredTagName))
						tagList.removeTag(enteredTagName); // removes tag from the global tag list.
					else
						
						optionPane.showMessageDialog(null, "Tag doesn't exists in the Tag List!"); 
					dispose();
				}
			});
		}
	}
}
