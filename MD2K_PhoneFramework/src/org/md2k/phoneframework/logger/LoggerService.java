package org.md2k.phoneframework.logger;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LoggerService extends Service{
	final static String MY_ACTION = "MY_ACTION";

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	 // TODO Auto-generated method stub
	 
	 MyThread myThread = new MyThread();
	 myThread.start();
	 
	 return super.onStartCommand(intent, flags, startId);
	}
	 
	public class MyThread extends Thread{
	 
	 @Override
	 public void run() {
	  // TODO Auto-generated method stub
	  for(int i=0; i<10; i++){
	   try {
	    Thread.sleep(5000);
	    Intent intent = new Intent();
	       intent.setAction(MY_ACTION);
	      
	       intent.putExtra("DATAPASSED", i);
	      
	       sendBroadcast(intent);
	   } catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	   }
	  }
	  stopSelf();
	 }
	 
	}
}
