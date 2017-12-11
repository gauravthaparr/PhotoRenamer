package photo_renamer;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * TagListTest contains all the 
 * unit tests for the class TagList.
 */
public class TagListTest 
{
	TagList tagList;
	
	@Before
	public void defineInstanceVariable()
	{
		tagList = new TagList();
	}
	
	/**
	 * Checks if tag is successfully 
	 * added to the master tag list.
	 * 
	 * @return nothing.
	 */
	@Test
	public void addingTagToMasterListTest1()
	{
		tagList.addTag("Test 3");
		
		ArrayList<String> readFile = tagList.readFile();
	
		assertTrue(readFile.contains("@Test 3"));
	}
	
	/**
	 * Checks if a tag can be added
	 * twice to the master tag list.
	 * 
	 * @return nothing.
	 */
	@Test
	public void addTagTwiceToMasterListTest2()
	{
		ArrayList<String> readFile = tagList.readFile();
		
		tagList.addTag("Test 1");
		tagList.addTag("Test 1");
		
		ArrayList<String> readUpdatedFile = tagList.readFile();
		
		assertEquals(readUpdatedFile.size() - readFile.size(), 1);
	}
	
	/**
	 * Checks if tag is successfully 
	 * added and remove to and from 
	 * the master tag list.
	 * 
	 * @return nothing.
	 */
	@Test
	public void removeAndAddTagFromMasterListTest2()
	{
		tagList.addTag("Test 3");
		tagList.removeTag("Test 3");
		
		ArrayList<String> readFile = tagList.readFile();
	
		assertFalse(readFile.contains("@Test 3"));
	}
	
	/**
	 * Checks if tag is removed from the
	 * master tag list which doesn't contain
	 * it.
	 * 
	 * @return nothing.
	 */
	@Test
	public void removeTagFromMasterListTest3()
	{
		TagList tagList = new TagList();
		tagList.removeTag("Test 4");
		ArrayList<String> readFile = tagList.readFile();
		assertFalse(readFile.contains("@Test 4"));
	}
	
	/**
	 * Checks if tag is removed from the
	 * master tag list which doesn't contain
	 * anything(empty).
	 * 
	 * @return nothing.
	 */
	@Test
	public void removeTagFromEmptyMasterListTest4()
	{
		ArrayList<String> readFile = tagList.readFile();
		if (readFile.size() == 0)
		{
			tagList.removeTag("Test 2");
			assertEquals(readFile.size(), 0);
		}
		assertNotEquals(readFile.size(), 0);
	}
	
}
