package photo_renamer;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;

/**
* Buttons program implements an application that
* displays Graphical User Interface which has buttons
* which user can use to.
*/
public class Buttons
{	
	private static Buttons buttons = null;
	public static boolean check = false; 
	public static boolean check1 = false; 
	public static boolean checkRevert = false; 
	
	/**
	 * Private constructor so no instance of 
	 * class Buttons can be created from 
	 * constructor.
	 */
	private Buttons()
	{
		
	}
	
	/**
	 * Ensures Buttons class implements singleton
	 * pattern(cannot have more than one instance).
	 * @return Buttons|null
	 */
	public static Buttons getInstance() // Implementation of singleton design pattern.
	{
	     if(buttons == null) 
	     {
	        buttons = new Buttons();
	        return buttons;
	     }
	     return null;
	 }
	
	/**
	* Builds the GUI window by returning the JFrame.
	* The window contains five buttons with distinct 
	* functionalities.
	* 
	* @return JFrame JFrame is a window consisting of buttons.
	*/
	public JFrame buildWindow()
	{
		JFrame window = new JFrame();		
		
		window.setTitle("Please work");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(350, 110);
		window.setVisible(true);
		
		JButton addTag = new JButton("Add Tag to Image");
		JButton removeTag = new JButton("Remove Tag from Image");
		JButton addTagList = new JButton("Add Tag List");
		JButton removeTagList = new JButton("Remove Tag List");
		JButton imageHistory = new JButton("Revert to Previous versions");
		
		addTagList.addActionListener(new ActionListener()
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
				check1 = true;
				ButtonTagList bt = new ButtonTagList();			
			}
		});
		
		removeTagList.addActionListener(new ActionListener()
		{
			@Override
			/**
			* Performs action when Remove Tag List button is 
			* clicked.
			* 
			* @param ActionEvent e This parameter detects event experienced by button.
			* @return nothing.
			*/
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				check1 = false;
				ButtonTagList bt1 = new ButtonTagList();
			
			}
		});
		
		addTag.addActionListener(new ActionListener()
		{
			@Override
			/**
			* Performs action when Add Tag to Image button is 
			* clicked.
			* 
			* @param ActionEvent e This parameter detects event experienced by button.
			* @return nothing.
			*/
			public void actionPerformed(ActionEvent e)
			{
				check = true;
				ButtonTagImage at = new ButtonTagImage();
				at.buildWindow();
				
			}
		});
		
		removeTag.addActionListener(new ActionListener()
		{
			@Override
			/**
			* Performs action when Remove Tag from Image button is 
			* clicked.
			* 
			* @param ActionEvent e This parameter detects event experienced by button.
			* @return Nothing.
			*/
			public void actionPerformed(ActionEvent e)
			{
				check = false;
				ButtonTagImage at = new ButtonTagImage();
				at.buildWindow();
			}
		});
		
		imageHistory.addActionListener(new ActionListener()
		{
			@Override
			/**
			* Performs action when Revert back to previous button is 
			* clicked.
			* 
			* @param ActionEvent e This parameter detects event experienced by button.
			* @return nothing.
			*/
			public void actionPerformed(ActionEvent e)
			{
				checkRevert = true;
				ButtonTagImage at = new ButtonTagImage();
				at.buildWindow();
			}
		});
		
		Container container = window.getContentPane();
		container.add(addTag, BorderLayout.EAST);
		container.add(removeTag, BorderLayout.WEST);
		container.add(addTagList, BorderLayout.NORTH);
		container.add(removeTagList, BorderLayout.SOUTH);
		container.add(imageHistory, BorderLayout.CENTER);
		window.pack();
		return window;
	}
	
	/**
	* This is the main method which creates the window of buttons and
	* runs when the program runs.
	* 
	* @param args Unused.
	* @return Nothing.
	*/
	/*public static void main(String[] args)
	{
		Buttons obj = new Buttons();
		obj.buildWindow();
	}*/
}
