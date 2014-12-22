package org.md2k.phoneframework.services.sensors;

public interface ConnectionSubscriber {
	public void onReceiveData(int deviceid, int Sensorid, int[] data, long timestamp);
}
