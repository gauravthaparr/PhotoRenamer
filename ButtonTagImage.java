package photo_renamer;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The ButtonTagImage class contains the GUI 
 * for tagging an Image with a tag that the 
 * user chooses.
 */
public class ButtonTagImage extends JFrame
{
	/**
	 * Create the frame.
	 */
	private JPanel contentPane;
	public JComboBox<String> comboBox;
	public JTextField textField;
	public static int selectedIndex;
	public static String enteredTag;

	/**
	 *  Creates a GUI which lets user choose the 
	 *  image file.
	 *  
	 *  @return nothing
	 */
	public void buildWindow()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(24, 24, 245, 20);
		contentPane.add(comboBox);

		populateComboBox(); // populates the combo box with names of the image files.
		
		JButton btnContinue = new JButton("Continue..");
		btnContinue.setBounds(306, 23, 89, 23);
		contentPane.add(btnContinue);

		if (Buttons.checkRevert) // checkRevert becomes true and hence the file revert method is called.
		{
			btnContinue.addActionListener(new ActionListener() 
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
					selectedIndex = comboBox.getSelectedIndex();
					ButtonRevertBack btn = new ButtonRevertBack();
					btn.buttonRevertBack();
					dispose();
					Buttons.checkRevert = false; 
				}
			});
		}
		else
		{
			btnContinue.addActionListener(new ActionListener() 
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
					selectedIndex = comboBox.getSelectedIndex();
					imageWindow();
				}
			});
		}
	}
	
	/**
	 * Creates GUI which enables user to add or 
	 * remove tags from image
	 * 
	 * @return nothing.
	 */
	public void imageWindow() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		String name = PhotoRenamer.imageList.get(selectedIndex).getPath();
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(name).getImage().getScaledInstance(236, 214, Image.SCALE_SMOOTH));
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(imageIcon);
		lblNewLabel.setBounds(10, 11, 236, 214);
		getContentPane().add(lblNewLabel);
		
		JLabel lblGlobalTagList = new JLabel("Global Tag List");
		lblGlobalTagList.setBounds(305, 11, 107, 14);
		getContentPane().add(lblGlobalTagList);
		
		JLabel lblTagsWithCurrent = new JLabel("Tags With Current Image");
		lblTagsWithCurrent.setBounds(266, 135, 150, 14);
		getContentPane().add(lblTagsWithCurrent);
		
		JButton btnRename = new JButton("Rename!");
		btnRename.setBounds(311, 238, 89, 23);
		getContentPane().add(btnRename);
		
		textField = new JTextField();
		textField.setBounds(167, 236, 134, 25);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterTagName = new JLabel("Enter Tag Name");
		lblEnterTagName.setBounds(73, 242, 95, 14);
		getContentPane().add(lblEnterTagName);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(256, 33, 168, 101);
		contentPane.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(256, 158, 168, 67);
		contentPane.add(textArea_1);
		TagImage a = new TagImage();
		String fileName = (String)comboBox.getSelectedItem();
		
		if(fileName.contains("@"))
		{
			String[] arr = a.getTags(fileName).split(" ");
			for(String tags: arr)
			{
				textArea_1.append(tags+'\n');
			}
		}
		else
		{
			textArea_1.append("No tag in the filename yet.");
		}	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(256, 33, 168, 101);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(256, 156, 168, 69);
		contentPane.add(scrollPane_1);
		scrollPane_1.setViewportView(textArea_1);
		
		TagList tl = new TagList();
		ArrayList<String> ar = tl.readFile();
		for (String tags : ar)
		{
			textArea.append(tags + "\n");
		}
		
		if (Buttons.check) 
		{
			btnRename.addActionListener(new ActionListener() {
				
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
					enteredTag = textField.getText();
					TagImage a = new TagImage();
					a.addTag(); // adds tag to the image.
					dispose();
				}
			});
		}
		else
		{
			btnRename.addActionListener(new ActionListener() 
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
					enteredTag = textField.getText();
					TagImage a = new TagImage();
					a.removeTag(); // removes tag from the image.
					dispose(); // closes the imageWindow.
				}
			});
		}
		textArea.setEditable(false);
		textArea_1.setEditable(false);
	}

	/**
	 * Helper function which populates the 
	 * combo box with names of the
	 * images.
	 * 
	 * @return nothing.
	 */
	private void populateComboBox()
	{		
		PhotoRenamer.imageList.clear();
		PhotoRenamer pr = new PhotoRenamer();
		pr.buildTree(PhotoRenamer.file);
		for (File filePath : pr )
		{
			comboBox.addItem(filePath.getName()); //Add all file names to imageNameList arrayList
		}
	}
}
