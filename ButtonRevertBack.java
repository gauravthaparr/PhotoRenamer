package photo_renamer;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * The ButtonRevertBack class contains the GUI 
 * for reverting back to previous
 * names of the file the user chooses.
 */
public class ButtonRevertBack extends JFrame {

	private JPanel contentPane;
	private JOptionPane optionPane;
	/**
	 * Create the frame.
	 */
	JComboBox<String> comboBox;
	public static int FileNumber;
	
	/** Constructor.
	 * 
	 *  Builds the GUI whenever the object of
	 *  ButtonRevertBack is created.
	 */
	public void buttonRevertBack() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		String name = PhotoRenamer.imageList.get(ButtonTagImage.selectedIndex).getPath();
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(name).getImage().getScaledInstance(253, 239, Image.SCALE_SMOOTH));
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(imageIcon);
		lblNewLabel.setBounds(6, 16, 253, 239);
		contentPane.add(lblNewLabel);
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(271, 123, 176, 27);
		contentPane.add(comboBox);
		
		RevertBack rv = new RevertBack();
		rv.history();
		
		for(String versions : rv.fileVersion)
		{
			comboBox.addItem(versions);
		}
		
		JLabel lblNewLabel_1 = new JLabel("Select the previous name");
		lblNewLabel_1.setBounds(274, 84, 176, 27);
		contentPane.add(lblNewLabel_1);
		
		JButton btnRevertBack = new JButton("Revert back..");
		btnRevertBack.setBounds(333, 243, 117, 29);
		contentPane.add(btnRevertBack);
	
		btnRevertBack.addActionListener(new ActionListener(){
			
			@Override
			/**
			* Performs action when Add Tag List button is 
			* clicked.
			* 
			* @param ActionEvent e This parameter detects events experienced by button.
			* @return nothing.
			*/
			public void actionPerformed(ActionEvent e) // The button is clicked for reverting back to previous versions.
			{
				FileNumber = comboBox.getSelectedIndex();
				RevertBack rv = new RevertBack(); // object created so that the method revertbackhis is called.
				rv.revertbackHis(); 
				
				optionPane = new JOptionPane();
				optionPane.showMessageDialog(null, "Image reverted back"); // Shows a pop-up which confirms that the image is reverted.
				dispose();
			}
		});
		
	}
}
