package progetto;

import javafx.application.Platform;

public class Timeout
{	
	public static void setTimeout(Runnable runnable, int delay, Runnable runLater)
	{
		runnable.run() ;
	    new Thread(() -> {
	        try 
	        {
	            Thread.sleep(delay) ;
	            Platform.runLater(runLater) ;
	        }
	        catch (Exception e)
	        {
	            System.err.println(e) ;
	        }
	    }).start() ;
	}
}
