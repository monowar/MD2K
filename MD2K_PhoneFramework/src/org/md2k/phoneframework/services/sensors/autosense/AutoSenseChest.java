package org.md2k.phoneframework.services.sensors.autosense;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.md2k.phoneframework.MainActivity;
import org.md2k.phoneframework.logger.Log;
import org.md2k.phoneframework.services.sensors.ConnectionSubscriber;
import org.md2k.phoneframework.services.sensors.DataQueue;
import org.md2k.phoneframework.services.sensors.datatype.AutoSenseChestDataType;

import android.content.res.AssetManager;

public class AutoSenseChest extends Thread implements ConnectionSubscriber{
	DataQueue dq;
	volatile boolean keepAlive;
	InputStream in=null;
	BufferedReader reader=null;
	String line=null;
	AssetManager am;
	public AutoSenseChest() {
		Log.d("AutoSenseChest","constructor()");
		dq=DataQueue.getInstance();
		keepAlive=true;
		am=MainActivity.context.getAssets();
	}
	@Override
	public void run() {
		Log.d("AutoSenseChest","run()");
		
		String[] parts;
		int index;
		int sensorid, deviceid=0;
		long timestamp;
		int data[]=new int[5];
		int delay=0;
		while(keepAlive){
			try {
				if(in!=null) line=reader.readLine(); else line=null;
				if(line==null){
					if(reader!=null) reader.close();					
					if(in!=null) in.close();
					Log.d("AutoSenseChest","run(): openfile");
					
					in = am.open("autosense_chest.txt");
					reader = new BufferedReader(new InputStreamReader(in));
					line = reader.readLine();
				}
//				Log.d("AutoSenseChest","run(): line: "+line);
				parts = line.split(",");
				delay=Integer.parseInt(parts[0]);				
				sensorid=Integer.parseInt(parts[1].trim());
				for (index = 2; index < 7; ++index) data[index-2] = Integer.parseInt(parts[index].trim());
				sleep(delay);
				timestamp=System.currentTimeMillis();				
				onReceiveData(deviceid, sensorid,data,timestamp);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void onReceiveData(int deviceid, int sensorid, int[] data, long timestamp) {
		// TODO Auto-generated method stub
//		Log.d("AutoSenseChest","OnreceiveData() timestamp="+timestamp);
		AutoSenseChestDataType dt=new AutoSenseChestDataType();
		dt.setDeviceID(deviceid);
		dt.setSensorID(sensorid);
		dt.setData(data);
		dt.setTimeStamp(timestamp);
		dq.enqueue(dt);
	}
	public void shutdown()
	{
		keepAlive=false;
		interrupt();
	}
}
