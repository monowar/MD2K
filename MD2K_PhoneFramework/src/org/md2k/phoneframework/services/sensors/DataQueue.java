package org.md2k.phoneframework.services.sensors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.md2k.phoneframework.logger.Log;
import org.md2k.phoneframework.logger.NetworkLogger;
import org.md2k.phoneframework.services.sensors.datatype.DataType;

public class DataQueue extends Thread{

private final BlockingQueue<DataType> queue;
private static DataQueue INSTANCE = null;
private volatile boolean keepAlive;


DataQueue() 
{ 
	Log.d("DataQueue","Constructor()");
	
	queue = new LinkedBlockingQueue<DataType>();
	keepAlive=true;
}
public static DataQueue getInstance()
{
	if(INSTANCE == null)
	{
		INSTANCE = new DataQueue();
		INSTANCE.start();
	}
	return INSTANCE;
}

public void run() 
{
	try 
	{
		while(keepAlive) 
		{ 
			dequeue();
			
		}
	} 
	catch (Exception e) 
	{
	}
}

public void enqueue(DataType b)
{
	try {
//		Log.d("DataQueue","enqueue: time="+b.getTimeStamp());
		queue.put(b);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		Log.d("ERROR","enqueue"+e.getMessage());
		e.printStackTrace();
	}
}
public void dequeue()
{
	DataType b = new DataType();
	try
	{		
		b = queue.take();		
		Log.d("DataQueue","Dequeue: time="+b.getTimeStamp());		
		NetworkLogger.getInstance().sendDataToNetwork(b);	
	}
	catch(Exception e)
	{
	} 
}
public  void kill()
{
	try{
		Log.d("DataQueue","kill()");
		
		keepAlive = false;
		interrupt();
		INSTANCE = null;
	}
	catch(Exception e){
	}
}
}

