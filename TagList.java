package photo_renamer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * The TagList class adds and removes
 * tags from the master tag list
 */
public class TagList 
{
	/**
	* Adds to tags to the master tag list
	* 
	* @param String tag The tag the user wants to add
	* to the master tag list.
	* @return Nothing.
	*/
	JOptionPane optionPane;
	public void addTag(String tag)
	{
		try
		{
			
			if(readFile().contains("@" + tag)) //check if the global list contains the tag or not.
			{
				throw new IOException();
			}
			FileWriter pw = new FileWriter(new File("Tag List.txt"), true);
			pw.append("@" + tag + System.lineSeparator()); //Write to file
			pw.close();
			
			WritingLog wl = new WritingLog(); //Write to log that new tag has been added
			wl.writeLog("Tag appended to Tag List: " + tag, "Tag List Log.txt");
			
			optionPane.showMessageDialog(null, tag + " : Added to the list!");	// pop-up confirming the addition of tag.
		}
		catch (IOException e)
		{
			optionPane.showMessageDialog(null, "Something went wrong in writing to Tag List file or Tag Already Exists!");
		}
	}
	
	/**
	* Removes the tags from the master
	* tag list
	* 
	* @param String tag The tag the user wants to
	* remove from the master tag list
	* @return Nothing.
	*/
	public void removeTag(String tag)
	{
		File inputFile = new File("Tag List.txt");
		File tempFile = new File("temp Tag List.txt");
		
		BufferedReader reader = null;
		BufferedWriter writer = null;
		
		try
		{
			 reader = new BufferedReader(new FileReader(inputFile));
			 writer = new BufferedWriter(new FileWriter(tempFile));
		}
		catch (IOException e)
		{
			optionPane.showMessageDialog(null, "Not Working while working with buffered reader and writer");
		}
		
		String currentLine;
		
		try
		{
			if(!readFile().contains("@" + tag))
			{
				throw new IOException();
			}
			while((currentLine = reader.readLine()) != null)
			{
				String trimmedLine = currentLine.trim();
			    if(trimmedLine.equals("@"+tag)) continue;
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			
			writer.close(); 
			reader.close(); 
			
			inputFile.delete(); // deletes the TagList.txt and in the next step renames the TempTagList.txt to TagList.txt
			tempFile.renameTo(inputFile);
			
			WritingLog wl = new WritingLog();
			wl.writeLog("Tag removed from Tag List: " + tag, "Tag List Log.txt");
			
			optionPane.showMessageDialog(null, tag + " : Removed"); // pop-up confirms the removal of tag from global tag list.
		}
		catch (IOException e)
		{
			inputFile.delete();
			tempFile.renameTo(inputFile);
			optionPane.showMessageDialog(null,"something went wrong during while loop in Tag List file");
		}
	}
	
	/**
	* Reads the file which contains the
	* master tag list
	* 
	* @return ArrayList<String> The list of tags in 
	* master tag list.
	*/
	public ArrayList<String> readFile()
	{
		try
		{
			if (!new File("Tag List.txt").exists())
			{
				FileWriter pw = new FileWriter(new File("Tag List.txt"),true);
			}
			Scanner scanner = new Scanner(new File("Tag List.txt"));
			ArrayList<String> ar = new ArrayList<>();
			
			while(scanner.hasNext()) // reads the file TagList.txt and adds the tags to the ArrayList.
			{
				ar.add(scanner.nextLine());
			}
			
			scanner.close();
			return ar; 
		}
		catch(IOException e)
		{
			optionPane.showMessageDialog(null, "Something went wrong in reading tag list");
		}
		return null;	
	}

	/**
	* This is the main method which demonstrates
	* the addition and removal of tags from
	* master tag list and shoes how to read
	* tags
	* 
	* @param args Unused.
	* @return Nothing.
	*/
	/*public static void main(String[] args)
	{
		TagList obj = new TagList();
		obj.addTag("gullu");
		obj.addTag("gtgt");
		obj.removeTag("gt");
		obj.removeTag("gullu");
		obj.removeTag("gtgt");
		System.out.println(obj.readFile().toString());
	}*/
}
