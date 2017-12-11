package photo_renamer;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The WritingLog class is the class for 
 * writing log text file which contains time and message
 */
public class WritingLog
{	
	
	/**
	* Writes the log to the file
	* 
	* @param String log The message which will be 
	* recorded in the log file
	* @param String fileName The name of the file
	* to which the message will be recorded
	* @return Nothing.
	*/
	public void writeLog(String log, String fileName)
	{
		writeToFile(getTimeStamp(), fileName);
		writeToFile(log, fileName);
	}
	
	/**
	* Gets the Current date and time.
	* 
	* @return String The time stamp to be recorded in log file.
	*/
	public String getTimeStamp()
	{		
		return "Time Stamp : " + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	}
	
	/**
	* Method which write to the file
	* 
	* @param String message The message which needs to be
	* recorded
	* @param String fileName The name of the file to which
	* message needs to be recorded at
	* @return Nothing.
	*/
	public void writeToFile(String message, String fileName)
	{
		try
		{
			FileWriter fw = new FileWriter(fileName, true); 
			fw.write(message + System.lineSeparator());
			fw.flush();
			fw.close();
		}
		catch ( IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	* This is the main method which demonstrates
	* log is written to specific file
	* 
	* @param args Unused.
	* @return Nothing.
	*/
	/*public static void main(String[] args)
	{
		WritingLog wl = new WritingLog();
		String message = "Hey there";
		wl.writeLog(message, "data.txt");
	}*/
}
