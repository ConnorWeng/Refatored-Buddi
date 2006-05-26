package org.homeunix.drummer.util;


/**
 * Simple logging system, modelled after Unix's syslog.  Lets you specifiy
 * priorities for messages, and then (at runtime) determine which are printed
 * out.  This version prints only to the console, but it may be beneficial 
 * to give the option to print to a file.  This class is released 
 * under the LGPL.
 * For a copy of the LGPL license, please see 
 *  http://www.gnu.org/copyleft/lesser.html
 *
 * Copyright (C) 2005 by Wyatt Olson
 * 
 */
public class Log {
	public static final int EMERGENCY = 0;
	public static final int ALERT = 1;
	public static final int CRITICAL = 2;
	public static final int ERROR = 3;
	public static final int WARNING = 4;
	public static final int NOTICE = 5;
	public static final int INFO = 6;
	public static final int DEBUG = 7;
	
	private static int logLevel = INFO;
	
	public static void emergency(Object message){
		if (logLevel >= EMERGENCY)
			log(message, "EMERGENCY");
	}

	public static void alert(Object message){
		if (logLevel >= ALERT)
			log(message, "ALERT");		
	}

	public static void critical(Object message){
		if (logLevel >= CRITICAL)
			log(message, "CRITICAL");		
	}

	public static void error(Object message){
		if (logLevel >= ERROR)
			log(message, "ERROR");		
	}
	
	public static void warning(Object message){
		if (logLevel >= WARNING)
			log(message, "WARNING");		
	}

	public static void notice(Object message){
		if (logLevel >= NOTICE)
			log(message, "NOTICE");	
	}

	public static void info(Object message){
		if (logLevel >= INFO)
			log(message, "INFO");		
	}
	
	public static void debug(Object message){
		if (logLevel >= DEBUG)
			log(message, "DEBUG");		
	}

	private static void log(Object message, String level){
		System.err.println(level + ": " + message);
	}
	
	/**
	 * @return Returns the logLevel.
	 */
	public static int getLogLevel() {
		return logLevel;
	}
	/**
	 * @param logLevel The logLevel to set.
	 */
	public static void setLogLevel(int logLevel) {
		if (logLevel > DEBUG)
			logLevel = DEBUG;
		else if (logLevel < EMERGENCY)
			logLevel = EMERGENCY;
		Log.logLevel = logLevel;
	}
}