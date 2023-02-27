package com.esed.easybridge.webservice.config;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum PropKeys {
	DEFAULT_NODE,
	
	JINDI_CONTEXT,
	JINDI_PROVIDER,
	//inizio LP PG1900XX_001
	DATABASE_DRIVER,
	DATABASE_URL,
	DATABASE_USERNAME,
	DATABASE_PASSWORD,
	DATABASE_AUTOCOMMIT,
	DATABASE_SCHEMA
	//fine LP PG1900XX_001
	
	;

    private static ResourceBundle rb;

    public String format( Object... args ) {
        synchronized(PropKeys.class) {
            if(rb==null)
            	rb = ResourceBundle.getBundle(PropKeys.class.getName());
            return MessageFormat.format(rb.getString(name()),args);
        }
    }
}