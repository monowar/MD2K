package org.md2k.phoneframework.services.sensors;

import org.md2k.phoneframework.logger.Log;
import org.md2k.phoneframework.services.sensors.external.AutoSenseChest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ConnectionService extends Service{
	AutoSenseChest autosensechest;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d("ConnectionService","OnStartCommand");
		autosensechest=new AutoSenseChest();
		autosensechest.start();
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	  public void onDestroy() {
		Log.d("ConnectionService","OnDestroy");		
		autosensechest.shutdown();
		DataQueue.getInstance().kill();
	  }	
}
