package org.md2k.phoneframework.services.sensors.datatype;

public class DataType extends Object{
	int datatypeID;
	long timestamp;	
	
	public DataType()
	{
		datatypeID = -1;
	}
	public int getTypeID() {
		return datatypeID;
	}	
	public long getTimeStamp() {
		return timestamp;
	}

	public void setTimeStamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
