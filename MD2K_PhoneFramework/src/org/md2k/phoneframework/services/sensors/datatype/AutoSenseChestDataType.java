package org.md2k.phoneframework.services.sensors.datatype;

import org.md2k.phoneframework.Constant;

public class AutoSenseChestDataType extends DataType{
	int deviceID;
	int sensorID;
	int data[];
	public static final int DATA_INT_SIZE = 5;
	
	public AutoSenseChestDataType()
	{
		datatypeID=Constant.AUTOSENSECHEST;
		deviceID = -1;
		sensorID = -1;
		data = new int[DATA_INT_SIZE];
		for(int i=0; i < DATA_INT_SIZE; i++)
			data[i] = -1;
	}
	public int getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(int deviceID) {
		this.deviceID = deviceID;
	}

	public int getSensorID() {
		return sensorID;
	}

	public void setSensorID(int sensorid) {
		sensorID = sensorid;
	}

	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}
	
	public int getDATA_INT_SIZE() {
		return DATA_INT_SIZE;
	}
}
