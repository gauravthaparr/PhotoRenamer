package photo_renamer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * The RevertBack class is used to change name of the image
 * to the previous versions of itself.
 */
public class RevertBack 
{
	static int x = ButtonTagImage.selectedIndex;
	String filePath;
	ArrayList<String> fileVersion = new ArrayList<>();
	ArrayList<String> imageNameList = new ArrayList<String>();
	JOptionPane optionPane;
	
	/**
	* The main method which changes to the previous 
	* version of the file.
	* 
	* @return Nothing.
	* 
	*/
	public void revertbackHis()
	{
		ButtonRevertBack btRevBack = new ButtonRevertBack();
		int indexVal = btRevBack.FileNumber;
		try 
			{
				history();
				File chosen = new File(filePath);
				
				if (chosen.exists())
				{			
					history(); // the previous names of the image name are called from history.
					TagImage rev = new TagImage();
					
					String fileAbsolutePath = PhotoRenamer.imageList.get(x).getAbsolutePath(); // x is the index of the file the user selected.
					File myFile = new File(fileAbsolutePath);
					String myDir = myFile.getParent();
					
					rev.renameFile(fileAbsolutePath, myDir + "/" + fileVersion.get(indexVal));
					
					WritingLog wl = new WritingLog(); 
					String message = imageNameList.get(x) + " to " + fileVersion.get(indexVal);
					
					TagImage forGetTags = new TagImage();
					
					if (forGetTags.getTags(imageNameList.get(x)).contains("@"))//writing log for revert
					{
						wl.writeLog(message, imageNameList.get(x).substring(0,imageNameList.get(x).indexOf("@")-1)+".txt"); //appends log to the fileName
					}
					else
					{
						wl.writeLog(message, imageNameList.get(x).substring(0,imageNameList.get(x).lastIndexOf("."))+".txt");	
					}		
				}
				else
				{
					throw new FileNotFoundException();
				}
			}
		catch(NullPointerException | FileNotFoundException e)
		{
			JOptionPane optionPane = new JOptionPane();
			optionPane.showMessageDialog(null, "Input not in Range or Tag does not exist in the Tag List");
		}
	}
	
	/**
	 * Helper function which creates an array which
	 * contains the previous names of the image(when 
	 * it was renamed).
	 * 
	 * @return nothing.
	 */
	public void history()
	{
		for (File filePath : PhotoRenamer.imageList ) 
		{
			imageNameList.add(filePath.getName()); //Add all file names to imageNameList arrayList
		}
		
		if (imageNameList.get(x).contains("@"))
		{
			filePath = imageNameList.get(x).substring(0,imageNameList.get(x).indexOf("@")).trim() + ".txt";
		}
		else
		{
			filePath = imageNameList.get(x).substring(0,imageNameList.get(x).lastIndexOf(".")) + ".txt";
		}
		
		try 
		{
			BufferedReader bf = new BufferedReader(new FileReader(filePath));
			int counter = 0;
			String line = "";
			while((line = bf.readLine())!= null)
			{
				if (counter %2 != 0)
				{
					line = line.substring(line.indexOf(" to ") + 4, line.length()); // adding previous names to fileVersion using log of image file.
					fileVersion.add(line);
				}
				counter++;
			}
		} 
		catch (IOException e) 
		{
			optionPane.showMessageDialog(null, "No history found for file"); // there has been no renaming done to the image file.
		}
	}
	
	/**
	* This is the main method which demonstrates 
	* how to change the file name to its previous 
	* versions.
	* 
	* @param args Unused.
	* @return Nothing.
	*/
	/*public static void main(String[] args)
	{
		PhotoRenamer obj = new PhotoRenamer();
		obj.buildTree(new File("/Users/gauravthapar/Desktop/PICS"));
		RevertBack obj1 = new RevertBack();
		obj1.revertbackHis();	
	}*/
}

	
