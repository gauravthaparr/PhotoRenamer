package photo_renamer;
/**
 * The TagNotAvailableException class represents a exception
 * thrown when tag searched is not available in the master
 * tag list
 */
public class TagNotAvailableException extends Exception
{

	/**
	  * Constructor.
	  * Calls the constructor of Exception class.
	  */
	public TagNotAvailableException()
	{
		super();
	}
	
	/**
	  * Constructor.
	  * Calls the constructor of Exception class.
	  * 
	  * @param String message Message given by the user
	  * to specify the reason for exception.
	  */
	public TagNotAvailableException(String message)
	{
		super(message);
	}

	/**
	* This is the main method which demonstrates the use of exception.
	* 
	* @param args Unused.
	* @return Nothing.
	*/
	/*public static void main(String[] args)
	{
		try
		{
			if(true)
			{
				throw new TagNotAvailableException("exception found");
			}
		}
		catch(TagNotAvailableException e)
		{
			System.out.println(e);
		}
	}*/
}
