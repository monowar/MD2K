package org.md2k.phoneframework.logger;

public class Log {

	public static final boolean DEBUG = true;
	public static final boolean I=false;
	public static final boolean VERBOSE = false;

	public static void d(String TAG, String logmessage) {
		if (DEBUG) {
			android.util.Log.d("MD2K_"+TAG, logmessage);
		}
	}

	public static void v(String TAG, String logmessage) {
		if (VERBOSE) {
			android.util.Log.v(TAG, logmessage);
		}
	}

	public static void w(String TAG, String logmessage) {
		android.util.Log.w(TAG,logmessage);
	}

	public static void e(String TAG, String logmessage) {
		android.util.Log.e(TAG,logmessage);
	}
	
	public static void i(String TAG, String logmessage) {
		if(I)
		android.util.Log.i(TAG, logmessage);
	}
}
