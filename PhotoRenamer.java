package photo_renamer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The PhotoRenamer class is the main class
 * which is used to run the application.
 * It builds the JFileChooser which leads the
 * user in the program
 */
public class PhotoRenamer implements Iterable<File> // using the Iterable design pattern
{
	/** The window the button is in. */
	private JFrame directoryFrame;
	public static ArrayList<File> imageList = new ArrayList<>();
	public static File file;
	
	/**
	* Builds the file chooser window which enables
	* the user to chose directory from. This further leads
	* to Button class.
	* 
	* @return Nothing.
	*/
	public void buildWindow() 
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		directoryFrame = new JFrame();
		directoryFrame.getContentPane();
		
		int returnVal = fileChooser.showOpenDialog(directoryFrame.getContentPane());

		if (returnVal == JFileChooser.APPROVE_OPTION) 
		{
			file = fileChooser.getSelectedFile();
			if (file.exists()) 
			{
				PhotoRenamer photoRenamer = new PhotoRenamer();
				photoRenamer.buildTree(file); // selects the image files under the directory selected.
			}
		}

		if(!checkDuplicate()) // checks if there are two same file names in the ArrayList.
		{
			Buttons buttonWindow = Buttons.getInstance();
			buttonWindow.buildWindow();
		}
		else
		{	
			try 
			{
				throw new IOException();
			} 
			catch (IOException e) 
			{
				JOptionPane optionPane = new JOptionPane();
				optionPane.showMessageDialog(null, "There are two files with the same name, change the name and retry.");
			}
		}
	}
	
	@Override
    public Iterator<File> iterator() 
	{
        return new PhotoRenamerIterator();
    }
	
	private class PhotoRenamerIterator implements Iterator<File>
	{
		/** The index of the next Contact to return. */
        private int current = 0;

		@Override
		public boolean hasNext() 
		{
			// TODO Auto-generated method stub
			return current < imageList.size();
		}

		@Override
		public File next() 
		{
			File file;
			
            try 
            {
                file = imageList.get(current);
            } 
            catch (IndexOutOfBoundsException e) 
            {
                throw new NoSuchElementException();
            }
            current += 1;
            return file;
		}	
	}
	
	/**
	* Gathers all images under directory chosen
	* by the user. Declared static so that it can
	* be accessed by class name.
	* 
	* @param File file The file object of the directory chosen
	* used so program can get all its children. 
	* @return Nothing.
	*/
	public void buildTree(File file) 
	{
		
		File[] allFiles = file.listFiles(); // get all files of 'file' in an array of File

		for (File currentFile : allFiles) // Go through all the files in allFiles array
		{			
			if (currentFile.isDirectory()) // If the file is a directory, do recursion
			{
				buildTree(currentFile);		
			}
			else
			{				
				String path = currentFile.getAbsolutePath();
				String ext = "";
				
				if (path.contains("."))
				{
					ext = path.substring(path.lastIndexOf("."), path.length());
				}
				
				if (ext.equals(".jpg") || ext.equals(".jpeg") || ext.equals(".png")) // checking for image files by extensions.
				{
					imageList.add(currentFile);
				}
			}			
		}	
	}
	
	/**
	* Checks for duplicates in the array list. 
	* It is declared static so that it can be 
	* accessed by class name.
	* 
	* @return boolean This returns true if list has
	* a duplicate.
	*/
	public static boolean checkDuplicate() // duplicate checker for image file names.
	{
		boolean ret = false;
		for (int j=0;j<imageList.size();j++)
			{
			for (int k=j+1;k<imageList.size();k++)
			{
				if (k!=j && imageList.get(k).getName().equals(imageList.get(j).getName()))
				{	
					ret = true;
				}	
			}
		}
		return ret;
	}
	
	/**
	* This is the main method which demonstrates how 
	* a directory is chosen and if array list has 
	* duplicates.
	* 
	* @param args Unused.
	* @return Nothing.
	*/
	public static void main(String[] args)
	{
		PhotoRenamer fb = new PhotoRenamer();
		fb.buildWindow();
	}
}
