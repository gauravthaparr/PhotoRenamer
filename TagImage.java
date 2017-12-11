package photo_renamer;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 * The TagImage class is the class for 
 * adding and removing tags to and from 
 * the image file
 */
public class TagImage
{
	private TagList tagList = new TagList();
	JOptionPane optionPane;
	
	ButtonTagImage btTagImage = new ButtonTagImage();
	int x = btTagImage.selectedIndex;
	String tag = btTagImage.enteredTag;
	
	/**
	* Changes the name of the file by
	* adding tags to image
	* 
	* @return Nothing.
	*/
	public void addTag()
	{
		ArrayList<String> imageNameList = new ArrayList<String>();
		
		PhotoRenamer.imageList.clear();
		PhotoRenamer pr = new PhotoRenamer();
		pr.buildTree(PhotoRenamer.file);
		
		for (File filePath : pr )
		{
			//Add all file names to imageNameList arrayList
			imageNameList.add(filePath.getName());
		}
		
		try 
		{		
			if (tagList.readFile().contains("@" + tag) && !getTags(imageNameList.get(x)).contains(tag))
				{
					//Make strings for renaming file
					String fileExt = imageNameList.get(x).substring(imageNameList.get(x).lastIndexOf("."), imageNameList.get(x).length());
					String fileAbsolutePath = PhotoRenamer.imageList.get(x).getAbsolutePath();
					
					String original = fileAbsolutePath.substring(0,fileAbsolutePath.lastIndexOf("."));				
					String newName = original + " @" + tag + fileExt;
					
					//Rename file
					renameFile(original+fileExt, newName);
					String message = imageNameList.get(x) + " to " + imageNameList.get(x).substring(0, imageNameList.get(x).lastIndexOf("."))+" @"+tag+fileExt;
					
					WritingLog wl = new WritingLog();
	
					//Write log
					if (getTags(imageNameList.get(x)).contains("@"))
					{
						wl.writeLog(message, imageNameList.get(x).substring(0,imageNameList.get(x).indexOf("@")-1)+".txt");
					}
					else
					{
						wl.writeLog(message, imageNameList.get(x).substring(0,imageNameList.get(x).lastIndexOf("."))+".txt");	
					}	
					optionPane.showMessageDialog(null, "Tag added to the image successfully.");
				}		
				else
				{
					throw new TagNotAvailableException();
				}

		}	
		catch(TagNotAvailableException | NullPointerException e)
		{
			optionPane.showMessageDialog(null, "Input not in Range or Tag does not exist in the Tag List");
		}
	}

	/**
	* Changes the name of the file
	* by removing the tags from the image
	* 
	* @return Nothing.
	*/
	public void removeTag()
	{
		ArrayList<String> imageNameList = new ArrayList<String>();
		
		PhotoRenamer.imageList.clear();
		PhotoRenamer pr = new PhotoRenamer();
		pr.buildTree(PhotoRenamer.file);
		
		for (File filePath : pr )
		{
			//Add all file names to imageNameList arrayList
			imageNameList.add(filePath.getName());
		}
		
		try 
		{
			if (tagList.readFile().contains("@" + tag) && getTags(imageNameList.get(x)).contains(tag))
				{
					String fileExt = imageNameList.get(x).substring(imageNameList.get(x).lastIndexOf("."), imageNameList.get(x).length());
					String fileAbsolutePath = PhotoRenamer.imageList.get(x).getAbsolutePath();
					
					String original = fileAbsolutePath.substring(0,fileAbsolutePath.lastIndexOf("."));
					String withoutTag = fileAbsolutePath.substring(0,fileAbsolutePath.indexOf("@"));	
					String[] imageTags = getTags(imageNameList.get(x)).split(" ");
					ArrayList<String> temp = new ArrayList<>(Arrays.asList(imageTags));
	
					temp.remove("@" + tag);
				
					String newRem = withoutTag.trim();
					for(String imgtag: temp)
					{
						newRem += " " + imgtag;
					}
					newRem = newRem.trim();
					newRem += fileExt;
					
					renameFile(original+fileExt,newRem);
		
					optionPane.showMessageDialog(null, "Tag successfully removed from file.");
					
					File f = new File(newRem);
					String newName = f.getName();
					
					String message = imageNameList.get(x) + " to " + newName;
					WritingLog wl = new WritingLog();
					wl.writeLog(message, imageNameList.get(x).substring(0,imageNameList.get(x).indexOf("@")-1)+".txt");
				}
				else
				{
					throw new TagNotAvailableException();
				}
		}
		catch(TagNotAvailableException | NullPointerException e)
		{
			optionPane.showMessageDialog(null, "Input not in Range or Tag does not exist in the Tag List");
		}
	}
	
	/**
	* Renames the image files used by both
	* add tag method and remove tag method
	* 
	* @param String originalName The name which is the
	* current name of the file.
	* @param String newName The name the originalName 
	* will be changed to.
	* @return Nothing.
	*/
	public void renameFile(String originalName, String newName)
	{
		File originalFile = new File(originalName);
		originalFile.renameTo(new File(newName));
	}

	/**
	* Returns all the tags the parameter variable has
	* 
	* @param String fileName The name of the file to 
	* get the tags from.
	* @return String All the tags of the file name.
	*/
	public String getTags(String fileName)
	{
		if (fileName.contains("@"))
		{
			return fileName.substring(fileName.indexOf("@"), fileName.lastIndexOf("."));
		}
		else
		{
			return fileName;
		}
	}
	
	/**
	* This is the main method which demonstrates
	* the addition and removal of tags from
	* images, rename file and how to get tags from a string
	* 
	* @param args Unused.
	* @return Nothing.
	*/
	/*public static void main(String[] args)
	{
		PhotoRenamer obj = new PhotoRenamer();
		obj.buildTree(new File("/Users/gauravthapar/Desktop/PICS"));
		TagImage obj1 = new TagImage();
		obj1.addTag();
		obj.buildTree(new File("/Users/gauravthapar/Desktop/PICS"));
		obj1.removeTag();
		obj1.renameFile("/Users/gauravthapar/Desktop/PICS/Name @prav.jp", "/Users/gauravthapar/Desktop/PICS/Name @gul.jpg");
		System.out.println(obj1.getTags("Name @gul @prav @gt.jpeg"));
	}*/
}