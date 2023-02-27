package com.esed.easybridge.webservice.exception;

public class DataBaseException  extends Exception {

	private static final long serialVersionUID = 1L;
	public static String code = "01";
	public static String description = "Mancata connessione al DataBase.";

	/**
	 * Constructor of ConfigurazioneDataBaseException.
	 * @param message - The message that we pass in case of this exception.
	 * @param cause - The {@link Throwable} class is the superclass of all errors and exceptions in the Java language.
	 */
	public DataBaseException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * Constructor of ConfigurazioneDataBaseException.
	 * @param message - The message that we pass in case of this exception.
	 */
	public DataBaseException(String message) {
		super(message);
	}
	/**
	 * Constructor of ConfigurazioneDataBaseException.
	 * @param cause - The {@link Throwable} class is the superclass of all errors and exceptions in the Java language.
	 */
	public DataBaseException(Throwable cause) {
		super(cause);
	}

}
