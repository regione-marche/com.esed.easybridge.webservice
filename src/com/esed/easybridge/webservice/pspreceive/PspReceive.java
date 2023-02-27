package com.esed.easybridge.webservice.pspreceive;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esed.easybridge.core.dao.EyBridgeDao;
import com.esed.easybridge.core.model.CuteCute;
import com.esed.easybridge.core.model.RegistroIdSession;
import com.esed.easybridge.core.utility.EyBridgeUtility;
import com.esed.easybridge.webservice.config.PropKeys;
import com.esed.easybridge.webservice.exception.DataBaseException;
import com.seda.commons.properties.tree.PropertiesTree;

//D O M A N D A  D O M A N D A  D O M A N D A  D O M A N D A  D O M A N D A  D O M A N D A  D O M A N D A 
//TODO: la servlet viene invocata solo se nodospcTipologia != "N"
//D O M A N D A  D O M A N D A  D O M A N D A  D O M A N D A  D O M A N D A  D O M A N D A  D O M A N D A 

public class PspReceive extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
	String schema = "";
	String driver = "";
	String url = "";
	String username = "";
	String password = "";
	String autocommit = "";
	
	public PspReceive() {
	    super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String esito = (String) request.getParameter("esito");
		String idSession = (String) request.getParameter("idSession");
		String cuteCute = "";
		String urlCuteCute = "";
		String code;
		String description;
		
		if(esito == null) {
			System.out.println("PspReceive doGet: Errore non è presente il parametro esito.");
			throw new ServletException("Errore non è presente il parametro esito.");
		}
		if(idSession == null) {
			System.out.println("PspReceive doGet: Errore non è presente il parametro idSession.");
			throw new ServletException("Errore non è presente il parametro idSession.");
		}
		
		EyBridgeDao ebDao = null;
		//Leggo informazioni da file di config
		PropertiesTree configuration;
		try {
			String rootPath = System.getenv("EASYBRIDGE_WSROOT");
			if (rootPath == null){
				throw new Exception("Variabile di sistema EASYBRIDGE_WSROOT non definita");
			}
			configuration = new PropertiesTree(rootPath);
		} catch (Exception e) {
			System.out.println("PspReceive doGet: " + e.getMessage());
			throw new ServletException("Errore durante il caricamento del file di configurazione EASYBRIDGE_WSROOT. " + e.getMessage(),e);
		}
			
		schema = configuration.getProperty(PropKeys.DATABASE_SCHEMA.format());
		driver = configuration.getProperty(PropKeys.DATABASE_DRIVER.format());
		url = configuration.getProperty(PropKeys.DATABASE_URL.format());
		username = configuration.getProperty(PropKeys.DATABASE_USERNAME.format());
		password = configuration.getProperty(PropKeys.DATABASE_PASSWORD.format());
		autocommit = configuration.getProperty(PropKeys.DATABASE_AUTOCOMMIT.format());
			
		try {
			ebDao = EyBridgeUtility.getDao(schema, driver, url, username, password, autocommit);
			if(ebDao == null) {
	        	throw new DataBaseException("");
			}
		} catch (DataBaseException e) {
			code = DataBaseException.code;
			description = DataBaseException.description;
			if (!e.getMessage().equals("")) {
				description += " (" + e.getMessage() + ")";
			}
			System.out.println("PspReceive doGet: " + description);
			throw new ServletException("Errore durante il collegamento al DataBase. (" + code + " - " + description + ")",e);
		} catch (Exception e) {
			System.out.println("PspReceive doGet: " + e.getMessage());
			throw new ServletException("Errore generico DataBase. (" + e.getMessage() + ")",e);
		}
		//inizio LP PG190220
		//Non si riesce a deployare su ver. < 1.8
		//Non cancellare RegistroIdSession dummy = new RegistroIdSession();
		@SuppressWarnings("unused")
		RegistroIdSession dummy = new RegistroIdSession();
		//fine LP PG190220
		try {
			RegistroIdSession regRPT = ebDao.selectRegistroIdSession(idSession);
			if(regRPT == null) {
				throw new Exception("Non è presente sul DB il record RegistroIdSession (idSession = '" + idSession + "').");
			}
			cuteCute = regRPT.getCuteCute();
			CuteCute loadCuteCute = ebDao.selectCuteCute(cuteCute);
			if(loadCuteCute == null) {
				throw new Exception("Non è presente sul DB il record CuteCute '" + cuteCute + "'.");
			}
			urlCuteCute = loadCuteCute.getUrlsPSPReturnURL();
			if(urlCuteCute == null || urlCuteCute.equals("")) {
				throw new Exception("Non è valorizzata sul DB la colonna 'Urls PSPReturnURL' '" + cuteCute + "'.");
			}
		} catch (Exception e) {
			System.out.println("PspReceive doGet: " + e.getMessage());
			throw new ServletException("Errore generico doGet. " + e.getMessage(),e);
		}

		//inizio LP 20190611 BUG connection open
		if(ebDao != null) {
			ebDao.CloseConnection();
		}
		//fine LP 20190611

		System.out.println("PspReceive doGet urlEnte: " + urlCuteCute + " redirect (" + idSession + ", " + esito + ")");
		response.sendRedirect(urlCuteCute + "?idSession=" + idSession + "&esito=" + esito + "&TipoGateway=N");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String esito = (String) request.getParameter("esito");
		String idSession = (String) request.getParameter("idSession");
		String cuteCute = "";
		String urlCuteCute = "";
		String code;
		String description;
		
		if(esito == null) {
			System.out.println("PspReceive doPost: Errore non è presente il parametro esito.");
			throw new ServletException("Errore non è presente il parametro esito.");
		}
		if(idSession == null) {
			System.out.println("PspReceive doPost: Errore non è presente il parametro idSession.");
			throw new ServletException("Errore non è presente il parametro idSession.");
		}
		
		EyBridgeDao ebDao = null;
		//Leggo informazioni da file di config
		PropertiesTree configuration;
		try {
			String rootPath = System.getenv("EASYBRIDGE_WSROOT");
			if (rootPath == null){
				throw new Exception("Variabile di sistema EASYBRIDGE_WSROOT non definita");
			}
			configuration = new PropertiesTree(rootPath);
		} catch (Exception e) {
			System.out.println("PspReceive doPost: " + e.getMessage());
			throw new ServletException("Errore durante il caricamento del file di configurazione EASYBRIDGE_WSROOT. " + e.getMessage(),e);
		}
			
		schema = configuration.getProperty(PropKeys.DATABASE_SCHEMA.format());
		driver = configuration.getProperty(PropKeys.DATABASE_DRIVER.format());
		url = configuration.getProperty(PropKeys.DATABASE_URL.format());
		username = configuration.getProperty(PropKeys.DATABASE_USERNAME.format());
		password = configuration.getProperty(PropKeys.DATABASE_PASSWORD.format());
		autocommit = configuration.getProperty(PropKeys.DATABASE_AUTOCOMMIT.format());
			
		try {
			ebDao = EyBridgeUtility.getDao(schema, driver, url, username, password, autocommit);
			if(ebDao == null) {
	        	throw new DataBaseException("");
			}
		} catch (DataBaseException e) {
			code = DataBaseException.code;
			description = DataBaseException.description;
			if (!e.getMessage().equals("")) {
				description += " (" + e.getMessage() + ")";
			}
			System.out.println("PspReceive doPost: " + description);
			throw new ServletException("Errore durante il collegamento al DataBase. (" + code + " - " + description + ")",e);
		} catch (Exception e) {
			System.out.println("PspReceive doPost: " + e.getMessage());
			throw new ServletException("Errore generico DataBase. (" + e.getMessage() + ")",e);
		}
		//inizio LP PG190220
		//Non si riesce a deployare su ver. < 1.8
		//Non cancellare RegistroIdSession dummy = new RegistroIdSession();
		@SuppressWarnings("unused")
		RegistroIdSession dummy = new RegistroIdSession();
		//fine LP PG190220
		try {
			RegistroIdSession regIdSession = ebDao.selectRegistroIdSession(idSession);
			if(regIdSession == null) {
				throw new Exception("Non è presente sul DB il record RegistroIdSession (idSession = '" + idSession + "').");
			}
			cuteCute = regIdSession.getCuteCute();
			CuteCute loadCuteCute = ebDao.selectCuteCute(cuteCute);
			if(loadCuteCute == null) {
				throw new Exception("Non è presente sul DB il record CuteCute '" + cuteCute + "'.");
			}
			urlCuteCute = loadCuteCute.getUrlsPSPReturnURL();
			if(urlCuteCute == null || urlCuteCute.equals("")) {
				throw new Exception("Non è valorizzata sul DB la colonna 'Urls PSPReturnURL' '" + cuteCute + "'.");
			}
		} catch (Exception e) {
			System.out.println("PspReceive doPost: " + e.getMessage());
			throw new ServletException("Errore generico doPost. " + e.getMessage(),e);
		}

		//inizio LP 20190611 BUG connection open
		if(ebDao != null) {
			ebDao.CloseConnection();
		}
		//fine LP 20190611

		System.out.println("PspReceive doPost urlEnte: " + urlCuteCute + " redirect (" + idSession + ", " + esito + ")");
		response.sendRedirect(urlCuteCute + "?idSession=" + idSession + "&esito=" + esito + "&TipoGateway=N");
	}

}
