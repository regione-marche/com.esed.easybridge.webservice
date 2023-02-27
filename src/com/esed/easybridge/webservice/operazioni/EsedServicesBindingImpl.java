/**
 * EsedServicesBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.esed.easybridge.webservice.operazioni;

import it.gov.spcoop.NodoPagamentiSPC.servizi.PagamentiTelematiciRPT.PagamentiTelematiciRPT;
import it.gov.spcoop.NodoPagamentiSPC.servizi.PagamentiTelematiciRPT.PagamentiTelematiciRPTbindingStub;
import it.gov.spcoop.NodoPagamentiSPC.servizi.PagamentiTelematiciRPT.PagamentiTelematiciRPTserviceLocator;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;

import org.apache.axis.types.PositiveInteger;

import com.esed.nodospc.wisp2.webservice.client.WsNodoRpcHandler;
import com.seda.commons.string.Pad;

import gov.telematici.pagamenti.ws.FaultBean;
import gov.telematici.pagamenti.ws.NodoInviaRPT;
import gov.telematici.pagamenti.ws.NodoInviaRPTRisposta;
import gov.telematici.pagamenti.ws.NodoChiediStatoRPT;
import gov.telematici.pagamenti.ws.NodoChiediStatoRPTRisposta;
import gov.telematici.pagamenti.ws.ppthead.IntestazionePPT;
import gov.telematici.pagamenti.ws.NodoChiediCopiaRTRisposta;
import gov.telematici.pagamenti.ws.NodoChiediCopiaRT;
import gov.telematici.pagamenti.ws.EsitoChiediStatoRPT;
import gov.telematici.pagamenti.ws.TipoStoricoRPT;
import gov.telematici.pagamenti.ws.TipoStoricoVersamento;
import gov.telematici.pagamenti.ws.NodoInviaRispostaRevoca;
import gov.telematici.pagamenti.ws.NodoInviaRispostaRevocaRisposta;
//inizio LP PG21XX07
import gov.telematici.pagamenti.ws.NodoPAChiediInformativaPA;
import gov.telematici.pagamenti.ws.NodoPAChiediInformativaPARisposta;
import gov.telematici.pagamenti.ws.NodoChiediListaPendentiRPT;
import gov.telematici.pagamenti.ws.NodoChiediListaPendentiRPTRisposta;
import gov.telematici.pagamenti.ws.TipoListaRPTPendenti;
import gov.telematici.pagamenti.ws.TipoRPTPendente;
//fine LP PG21XX07

public class EsedServicesBindingImpl extends com.esed.easybridge.webservice.handler.WebServiceHandler implements com.esed.easybridge.webservice.operazioni.EsedServicesEndpoint{

	String cuteCute = "";
	String nodospcGestore = "E";   //E == Esed (valore di default), M == Maggioli
	String nodospcTipologia = "N"; //N == NodoSPC (valore di default), G == GAD
	
	String nodoSpcPortDomain = "";
	String usernameStub = "";
	String passwordStub = "";
	
	Boolean proxyEnabled = false;
	String proxyHost = "";
	String proxyPort = "";
	String proxyUser = "";
	String proxyPassword = "";
	
    public com.esed.easybridge.webservice.operazioni.NodoInviaRPTRisposta nodoInviaRPT(com.esed.easybridge.webservice.operazioni.NodoInviaRPT bodyrichiesta, com.esed.easybridge.webservice.operazioni.head.IntestazionePPT header) throws java.rmi.RemoteException {

		String code = null;
		String description = null;
		com.esed.easybridge.webservice.operazioni.NodoInviaRPTRisposta nodoInviaRPTRispostaOut = null; 
		NodoInviaRPTRisposta nodoInviaRPTRisposta = null;
		String identificativoDominio = "";

		try {
			java.util.Date dataInizio = null;
			//lettura configurazione
			System.out.println("===========================> nodoInviaRPT - Parametro CuteCute su header: '" + header.getCutecute() + "'");
			if(header.getCutecute().trim().length() < 5) {
				this.cuteCute = Pad.left(header.getCutecute().trim(), 5, '0');
			} else {
				this.cuteCute = header.getCutecute().trim();
			}
			
			if(this.cuteCute.equals("00000")){
				System.out.println("Parametro CuteCute non presente nei parametri inviati");
				throw new Exception("Parametro CuteCute non presente nei parametri inviati");
			}
			
			this.nodoSpcPortDomain = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.url");
			this.usernameStub = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.usernameStub");
			this.passwordStub = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.passwordStub");
			
			this.proxyEnabled = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyenabled")!=null && propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyenabled").equals("1");
			this.proxyHost = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyhost");
			this.proxyPort = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyport");
			this.proxyUser = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyuser");
			this.proxyPassword = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxypassword");
				
			if(propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcGestore") != null) {
				this.nodospcGestore = propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcGestore");
			} else {
				this.nodospcGestore = "E";
			}
			
			if(propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcTipologia") != null) {
				this.nodospcTipologia = propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcTipologia");
			} else {
				this.nodospcTipologia = "N";
			}

			String identificativoIntermediarioPA = header.getIdentificativoIntermediarioPA();
			String identificativoStazioneIntermediarioPA = header.getIdentificativoStazioneIntermediarioPA();
			
			String passwordPsp = bodyrichiesta.getPassword();
			String identificativoPSP = bodyrichiesta.getIdentificativoPSP();
			String identificativoIntermediarioPSP = bodyrichiesta.getIdentificativoIntermediarioPSP();
			String identificativoCanalePSP = bodyrichiesta.getIdentificativoCanale();
			
			//inizio LP PG1900XX_029
			//23072019 GG - inizio
			//Dal 13/07/2019 i tag identificativoPSP, identificativoIntermediarioPSP e identificativoCanalePSP non possono essere valorizzati a ""
			if (identificativoPSP == null || identificativoPSP.trim().length() == 0) {
				System.out.println("nodoInviaRPT - IdentificativoPSP   <vuoto>    : '" + identificativoPSP + "'");
				identificativoPSP = propertiesTree().getProperty(this.cuteCute + ".nodospc.identificativoPSP"); // "AGID_01";
				System.out.println("nodoInviaRPT - IdentificativoPSP   <pieno>    : '" + identificativoPSP + "'");
			}
		
			if (identificativoIntermediarioPSP == null || identificativoIntermediarioPSP.trim().length() == 0) {
				System.out.println("nodoInviaRPT - IdentificativoIntermediarioPSP <vuoto> : '" + identificativoIntermediarioPSP + "'");
				identificativoIntermediarioPSP = propertiesTree().getProperty(this.cuteCute + ".nodospc.identificativoIntermediarioPSP"); // "97735020584";
				System.out.println("nodoInviaRPT - IdentificativoIntermediarioPSP <pieno> : '" + identificativoIntermediarioPSP + "'");
			}
		
			if (identificativoCanalePSP == null || identificativoCanalePSP.trim().length() == 0) {
				System.out.println("nodoInviaRPT - IdentificativoCanalePSP <vuoto> : '" + identificativoCanalePSP + "'");
				identificativoCanalePSP=propertiesTree().getProperty(this.cuteCute + ".nodospc.identificativoCanale"); // "97735020584_02";
				System.out.println("nodoInviaRPT - IdentificativoCanalePSP <pieno> : '" + identificativoCanalePSP + "'");
			}
			//23072019 GG - fine
			//fine LP PG1900XX_029
			
			identificativoDominio = header.getIdentificativoDominio();
			String codiceContestoPagamento = header.getCodiceContestoPagamento(); 
			String iuv = header.getIdentificativoUnivocoVersamento();
			String tipoFirma = bodyrichiesta.getTipoFirma();
			
			System.out.println("nodoInviaRPT %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println("nodoInviaRPT - CuteCute                       : '" + this.cuteCute + "'");
			System.out.println("nodoInviaRPT - IdentificativoDominio          : '" + identificativoDominio + "'");
			System.out.println("nodoInviaRPT - IdentificativoPSP              : '" + identificativoPSP + "'");
			System.out.println("nodoInviaRPT - IdentificativoIntermediarioPSP : '" + identificativoIntermediarioPSP + "'");
			System.out.println("nodoInviaRPT - IdentificativoCanalePSP        : '" + identificativoCanalePSP + "'");
			System.out.println("nodoInviaRPT - TipoFirma                      : '" + tipoFirma + "'");
			System.out.println("nodoInviaRPT - IdentificativoUnivocoVersamento: '" + iuv + "'");
			System.out.println("nodoInviaRPT - PasswordPsp                    : '" + passwordPsp + "'");

			//inizio LP PG190220
			//bug veniva controllato nodospcTipologia
			//if(this.nodospcTipologia.equals("M")) {
			if(this.nodospcGestore.equals("M")) {
			//fine LP PG190220
				System.out.println("nodoInviaRPT - Ambiente del gruppo            : 'Maggioli'");
				System.out.println("Operazione non eseguibile per Gruppo =/= E-sed");
	        	throw new Exception("Operazione non eseguibile per Gruppo =/= E-sed");
			}
			System.out.println("nodoInviaRPT - Ambiente del gruppo            : 'E-sed'");

			System.out.println("nodoInviaRPT - nodoSpcPortDomain              : '" + this.nodoSpcPortDomain + "'");
			System.out.println("nodoInviaRPT - usernameStub                   : '" + this.usernameStub + "'");
			System.out.println("nodoInviaRPT - passwordStub                   : '" + this.passwordStub + "'");

			System.out.println("nodoInviaRPT %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			
	    	PagamentiTelematiciRPTserviceLocator pagamentiTelematiciRPTserviceLocator = new PagamentiTelematiciRPTserviceLocator();
			System.out.println("========================================================================================================================================");
			System.out.println("nodoInviaRPT PRIMA DI pagamentiTelematiciRPTserviceLocator");
			PagamentiTelematiciRPT pagamentiTelematiciRPT = null;
			if(this.nodospcTipologia.equals("N")) {
				pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(this.nodoSpcPortDomain + "/nodoInviaRPT"));
			} else {
				pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(this.nodoSpcPortDomain));
			}
			System.out.println("nodoInviaRPT DOPO pagamentiTelematiciRPTserviceLocator");
			System.out.println("========================================================================================================================================");
			
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("nodoInviaRPT PRIMA DI SET di _stub");
			PagamentiTelematiciRPTbindingStub _stub = (PagamentiTelematiciRPTbindingStub) pagamentiTelematiciRPT;
			if(this.nodospcTipologia.equals("N")) {
				_stub.setUsername(this.usernameStub);
				_stub.setPassword(this.passwordStub);
			}
			System.out.println("nodoInviaRPT DOPO DI SET di _stub");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			HandlerRegistry hr = pagamentiTelematiciRPTserviceLocator.getHandlerRegistry();
			QName  portName = _stub.getPortName();
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("nodoInviaRPT portName::LocalPart: " + portName.getLocalPart());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			List handlerChain = hr.getHandlerChain(portName);
			
			HandlerInfo hi = new HandlerInfo();
			hi.setHandlerClass(WsNodoRpcHandler.class);
			handlerChain.add(hi);

			if(this.proxyEnabled) {
				System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
				System.out.println("nodoInviaRPT PROXY A B I L I T A T O");
				System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");

				System.getProperties().put("http.proxyHost", this.proxyHost);
				System.getProperties().put("http.nonProxyHosts", "localhost");
				System.getProperties().put("http.proxyPort", this.proxyPort);
				System.getProperties().put("http.proxyUser", this.proxyUser);
				System.getProperties().put("http.proxyPassword", this.proxyPassword);
			}
			
			System.out.println("RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 ");
			byte[] rpt = bodyrichiesta.getRpt();
			System.out.println("XML RPT nodoInviaRPT UTF-8 = " + rpt.toString());
			System.out.println("RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 RPT BYTE 64 ");
			System.out.println("NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT");
			
			System.out.println("nodoInviaRPT PRIMA DI new NodoInviaRPT");
			System.out.println("NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT");
			NodoInviaRPT bodyrichiestaNodoInviaRPT = new NodoInviaRPT(passwordPsp, identificativoPSP, identificativoIntermediarioPSP, identificativoCanalePSP, tipoFirma, rpt);
			System.out.println("NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT");
			System.out.println("nodoInviaRPT DOPO DI new NodoInviaRPT");
			System.out.println("NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT");

			System.out.println("nodoInviaRPT PRIMA DI new IntestazionePPT");
			System.out.println("NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT");
	    	IntestazionePPT headerNodoInviaRPT = new IntestazionePPT(identificativoIntermediarioPA, identificativoStazioneIntermediarioPA, identificativoDominio, iuv, codiceContestoPagamento, null, null);
			System.out.println("NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT");
			System.out.println("nodoInviaRPT DOPO DI new IntestazionePPT");
			System.out.println("NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT NodoInviaRPT");

			//inizio LP PG21XX07
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//fine LP PG21XX07
			dataInizio = new java.util.Date();
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			//inizio LP PG21XX07
			//System.out.println("nodoInviaRPT PRIMA DI nodoInviaRPT: " + dataInizio.getTime());
			System.out.println("nodoInviaRPT PRIMA DI nodoInviaRPT: " + format.format(dataInizio.getTime()));
			//fine LP PG21XX07
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			
	    	nodoInviaRPTRisposta = pagamentiTelematiciRPT.nodoInviaRPT(bodyrichiestaNodoInviaRPT, headerNodoInviaRPT);
    		dataInizio = new java.util.Date();

    		System.out.println("****************************************************************************************************************************************");
			//inizio LP PG21XX07
			//System.out.println("DOPO nodoInviaRPT ESITO = " + nodoInviaRPTRisposta.getEsito() + " "  + dataInizio.getTime());
			System.out.println("DOPO nodoInviaRPT ESITO = " + nodoInviaRPTRisposta.getEsito() + " "  + format.format(dataInizio.getTime()));
			//fine LP PG21XX07
			System.out.println("****************************************************************************************************************************************");
			
		}
	    catch (Exception e) {
	    	e.printStackTrace();
	
			code = "99";
			description = "Errore generico: " + e.getMessage();
			System.out.println("nodoInviaRPT to Esed: " + description);
		} finally {
			if(code != null && description != null) {
				nodoInviaRPTRisposta = new NodoInviaRPTRisposta();
				description = "nodoInviaRPT to Esed: " + code + ": " + description;
				FaultBean fOut = new FaultBean();
				fOut.setFaultCode("PPT_SYSTEM_ERROR"); //PAA_SYSTEM_ERROR
				fOut.setFaultString("Errore generico.");
				fOut.setId(identificativoDominio);
				fOut.setDescription(description);
				fOut.setSerial(1);
				nodoInviaRPTRisposta.setEsito("KO");
				nodoInviaRPTRisposta.setFault(fOut);
			}
		}
		if(nodoInviaRPTRisposta != null) {
			nodoInviaRPTRispostaOut = new com.esed.easybridge.webservice.operazioni.NodoInviaRPTRisposta();
			nodoInviaRPTRispostaOut.setEsito(nodoInviaRPTRisposta.getEsito());
			if(nodoInviaRPTRisposta.getEsito().equals("KO")) {
				FaultBean ff = nodoInviaRPTRisposta.getFault();
				com.esed.easybridge.webservice.operazioni.FaultBean ffOut = new com.esed.easybridge.webservice.operazioni.FaultBean(ff.getFaultCode(), ff.getFaultString(), ff.getId(), ff.getDescription(), ff.getSerial());
				nodoInviaRPTRispostaOut.setFault(ffOut);
			} else {
				nodoInviaRPTRispostaOut.setRedirect(nodoInviaRPTRisposta.getRedirect());
				nodoInviaRPTRispostaOut.setUrl(nodoInviaRPTRisposta.getUrl());
			}
		}
		return nodoInviaRPTRispostaOut;
    }

    public com.esed.easybridge.webservice.operazioni.NodoChiediStatoRPTRisposta nodoChiediStatoRPT(com.esed.easybridge.webservice.operazioni.NodoChiediStatoRPT bodyrichiesta, com.esed.easybridge.webservice.operazioni.head.ParametroCuteCute cutecute) throws java.rmi.RemoteException {

		String code = null;
		String description = null;
		com.esed.easybridge.webservice.operazioni.NodoChiediStatoRPTRisposta nodoChiediStatoRPTRispostaOut = null; 
		NodoChiediStatoRPTRisposta nodoChiediStatoRPTRisposta = null;
		String identificativoDominio = "";
		
		try {
			java.util.Date dataInizio = null;
			//lettura configurazione
			System.out.println("===========================> nodoChiediStatoRPT - Parametro CuteCute: '" + cutecute.getCutecute() + "'");
			if(cutecute.getCutecute().trim().length() < 5) {
				this.cuteCute = Pad.left(cutecute.getCutecute().trim(), 5, '0');
			} else {
				this.cuteCute = cutecute.getCutecute().trim();
			}
			
			if(this.cuteCute.equals("00000")){
				System.out.println("Parametro CuteCute non presente nei parametri inviati");
				throw new Exception("Parametro CuteCute non presente nei parametri inviati");
			}
			
			this.nodoSpcPortDomain = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.url");
			this.usernameStub = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.usernameStub");
			this.passwordStub = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.passwordStub");
			
			this.proxyEnabled = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyenabled")!=null && propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyenabled").equals("1");
			this.proxyHost = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyhost");
			this.proxyPort = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyport");
			this.proxyUser = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyuser");
			this.proxyPassword = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxypassword");
				
			if(propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcGestore") != null) {
				this.nodospcGestore = propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcGestore");
			} else {
				this.nodospcGestore = "E";
			}
			
			if(propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcTipologia") != null) {
				this.nodospcTipologia = propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcTipologia");
			} else {
				this.nodospcTipologia = "N";
			}
	 	
			identificativoDominio = bodyrichiesta.getIdentificativoDominio();
			String codiceContestoPagamento = bodyrichiesta.getCodiceContestoPagamento();
			String identificativoIntermediarioPA = bodyrichiesta.getIdentificativoIntermediarioPA();
			String identificativoStazioneIntermediarioPA = bodyrichiesta.getIdentificativoStazioneIntermediarioPA();
			String iuv = bodyrichiesta.getIdentificativoUnivocoVersamento();
			String passwordPsp = bodyrichiesta.getPassword();
		
			System.out.println("nodoChiediStatoRPT %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println("nodoChiediStatoRPT - CuteCute                             : '" + this.cuteCute + "'");
			System.out.println("nodoChiediStatoRPT - IdentificativoDominio                : '" + identificativoDominio + "'");
			System.out.println("nodoChiediStatoRPT - CodiceContestoPagamento              : '" + codiceContestoPagamento + "'");
			System.out.println("nodoChiediStatoRPT - IdentificativoIntermediarioPA        : '" + identificativoIntermediarioPA + "'");
			System.out.println("nodoChiediStatoRPT - IdentificativoStazioneIntermediarioPA: '" + identificativoStazioneIntermediarioPA + "'");
			System.out.println("nodoChiediStatoRPT - IdentificativoUnivocoVersamento      : '" + iuv + "'");
			System.out.println("nodoChiediStatoRPT - passwordPsp                          : '" + passwordPsp + "'");

			//inizio LP PG190220
			//bug veniva controllato nodospcTipologia
			//if(this.nodospcTipologia.equals("M")) {
			if(this.nodospcGestore.equals("M")) {
			//fine LP PG190220
				System.out.println("nodoChiediStatoRPT - ambiente del gruppo                  : 'Maggioli'");
				System.out.println("Operazione non eseguibile per Gruppo =/= E-sed");
	        	throw new Exception("Operazione non eseguibile per Gruppo =/= E-sed");
			}
			System.out.println("nodoChiediStatoRPT - ambiente del gruppo                  : 'E-sed'");

			System.out.println("nodoChiediStatoRPT - nodoSpcPortDomain                    : '" + this.nodoSpcPortDomain + "'");
			System.out.println("nodoChiediStatoRPT - usernameStub                         : '" + this.usernameStub + "'");
			System.out.println("nodoChiediStatoRPT - passwordStub                         : '" + this.passwordStub + "'");

			System.out.println("nodoChiediStatoRPT %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			
	    	PagamentiTelematiciRPTserviceLocator pagamentiTelematiciRPTserviceLocator = new PagamentiTelematiciRPTserviceLocator();
			System.out.println("========================================================================================================================================");
			System.out.println("nodoChiediStatoRPT PRIMA DI pagamentiTelematiciRPTserviceLocator");
			PagamentiTelematiciRPT pagamentiTelematiciRPT = null;
			if(this.nodospcTipologia.equals("N")) {
				pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(this.nodoSpcPortDomain + "/nodoChiediStatoRPT"));
			} else {
				pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(this.nodoSpcPortDomain));
			}
			System.out.println("nodoChiediStatoRPT DOPO pagamentiTelematiciRPTserviceLocator");
			System.out.println("========================================================================================================================================");
			
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("nodoChiediStatoRPT PRIMA DI SET di _stub");
			PagamentiTelematiciRPTbindingStub _stub = (PagamentiTelematiciRPTbindingStub) pagamentiTelematiciRPT;
			if(this.nodospcTipologia.equals("N")) {
				_stub.setUsername(this.usernameStub);
				_stub.setPassword(this.passwordStub);
			}
			System.out.println("nodoChiediStatoRPT DOPO DI SET di _stub");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			HandlerRegistry hr = pagamentiTelematiciRPTserviceLocator.getHandlerRegistry();
			QName  portName = _stub.getPortName();
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("nodoChiediStatoRPT portName::LocalPart: " + portName.getLocalPart());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			List handlerChain = hr.getHandlerChain(portName);
			
			HandlerInfo hi = new HandlerInfo();
			hi.setHandlerClass(WsNodoRpcHandler.class);
			handlerChain.add(hi);

			if(this.proxyEnabled) {
				System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
				System.out.println("nodoChiediStatoRPT PROXY A B I L I T A T O");
				System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");

				System.getProperties().put("http.proxyHost", this.proxyHost);
				System.getProperties().put("http.nonProxyHosts", "localhost");
				System.getProperties().put("http.proxyPort", this.proxyPort);
				System.getProperties().put("http.proxyUser", this.proxyUser);
				System.getProperties().put("http.proxyPassword", this.proxyPassword);
			}
			
			System.out.println("NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT");
			System.out.println("nodoChiediStatoRPT PRIMA DI new NodoChiediStatoRPT");
			System.out.println("NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT");
			NodoChiediStatoRPT bodyrichiestaNodoChiediStatoRPT = new NodoChiediStatoRPT(identificativoIntermediarioPA, identificativoStazioneIntermediarioPA, passwordPsp, identificativoDominio, iuv, codiceContestoPagamento);  
			System.out.println("NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT");
			System.out.println("nodoChiediStatoRPT DOPO DI new NodoChiediStatoRPT");
			System.out.println("NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT NodoChiediStatoRPT");
			
			//inizio LP PG21XX07
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//fine LP PG21XX07
			dataInizio = new java.util.Date();
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			//inizio LP PG21XX07
			//System.out.println("nodoChiediStatoRPT PRIMA DI nodoChiediStatoRPT: " + dataInizio.getTime());
			System.out.println("nodoChiediStatoRPT PRIMA DI nodoChiediStatoRPT: " + format.format(dataInizio.getTime()));
			//fine LP PG21XX07
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

    		nodoChiediStatoRPTRisposta = pagamentiTelematiciRPT.nodoChiediStatoRPT(bodyrichiestaNodoChiediStatoRPT);
    		dataInizio = new java.util.Date();

			System.out.println("****************************************************************************************************************************************");
			//inizio LP PG21XX07
			//System.out.println("DOPO nodoChiediStatoRPT ESITO = " + nodoChiediStatoRPTRisposta.getEsito() + " " + dataInizio.getTime());
			System.out.println("DOPO nodoChiediStatoRPT ESITO = " + nodoChiediStatoRPTRisposta.getEsito() + " " + format.format(dataInizio.getTime()));
			//fine LP PG21XX07
			System.out.println("****************************************************************************************************************************************");
			
		}
	    catch (Exception e) {
	    	e.printStackTrace();
	
			code = "99";
			description = "Errore generico: " + e.getMessage();
			System.out.println("nodoChiediStatoRPT to Esed: " + description);
		} finally {
			if(code != null && description != null) {
				nodoChiediStatoRPTRisposta = new NodoChiediStatoRPTRisposta();
				description = "nodoChiediStatoRPT to Esed: " + code + ": " + description;
				FaultBean fOut = new FaultBean();
				fOut.setFaultCode("PPT_SYSTEM_ERROR"); //PAA_SYSTEM_ERROR
				fOut.setFaultString("Errore generico.");
				fOut.setId(identificativoDominio);
				fOut.setDescription(description);
				fOut.setSerial(1);
				nodoChiediStatoRPTRisposta.setFault(fOut);
			}
		}
		if(nodoChiediStatoRPTRisposta != null) {
			nodoChiediStatoRPTRispostaOut = new com.esed.easybridge.webservice.operazioni.NodoChiediStatoRPTRisposta();
			if(nodoChiediStatoRPTRisposta.getFault() != null) {
				FaultBean ff = nodoChiediStatoRPTRisposta.getFault();
				com.esed.easybridge.webservice.operazioni.FaultBean ffOut = new com.esed.easybridge.webservice.operazioni.FaultBean(ff.getFaultCode(), ff.getFaultString(), ff.getId(), ff.getDescription(), ff.getSerial());
				nodoChiediStatoRPTRispostaOut.setFault(ffOut);
			} else {
				EsitoChiediStatoRPT esitoAgid = nodoChiediStatoRPTRisposta.getEsito();
				com.esed.easybridge.webservice.operazioni.EsitoChiediStatoRPT esitoOut = new com.esed.easybridge.webservice.operazioni.EsitoChiediStatoRPT();
				esitoOut.setStato(esitoAgid.getStato());
				esitoOut.setUrl(esitoAgid.getUrl());
				esitoOut.setRedirect(esitoAgid.getRedirect());
				TipoStoricoRPT[] listaTipoAgid = esitoAgid.getElementoStoricoRPT();
				if(listaTipoAgid != null) {
					int ik = listaTipoAgid.length;
					com.esed.easybridge.webservice.operazioni.TipoStoricoRPT[] listTipoOut = new com.esed.easybridge.webservice.operazioni.TipoStoricoRPT[ik];
					esitoOut.setElementoStoricoRPT(listTipoOut);
					int ii = 0;
					for (TipoStoricoRPT tipoStoricoRPT : listaTipoAgid) {
						com.esed.easybridge.webservice.operazioni.TipoStoricoRPT tipo = new com.esed.easybridge.webservice.operazioni.TipoStoricoRPT();
						tipo.setData(tipoStoricoRPT.getData());
						tipo.setDescrizione(tipoStoricoRPT.getDescrizione());
						tipo.setStato(tipoStoricoRPT.getStato());
						esitoOut.setElementoStoricoRPT(ii, tipo);
						ii++;
					}
				}
				TipoStoricoVersamento[] listaVersamentoAgid = esitoAgid.getElementoStoricoVersamento();
				if(listaVersamentoAgid != null) {
					int ik = listaVersamentoAgid.length;
					com.esed.easybridge.webservice.operazioni.TipoStoricoVersamento[] listVersOut = new com.esed.easybridge.webservice.operazioni.TipoStoricoVersamento[ik];
					esitoOut.setElementoStoricoVersamento(listVersOut);
					int ii = 0;
					for (TipoStoricoVersamento tipoVer : listaVersamentoAgid) {
						com.esed.easybridge.webservice.operazioni.TipoStoricoVersamento tipo = new com.esed.easybridge.webservice.operazioni.TipoStoricoVersamento();
						tipo.setData(tipoVer.getData());
						tipo.setDescrizione(tipoVer.getDescrizione());
						tipo.setStato(tipoVer.getStato());
						tipo.setProgressivo(tipoVer.getProgressivo());
						esitoOut.setElementoStoricoVersamento(ii, tipo);
						ii++;
					}
				}
				nodoChiediStatoRPTRispostaOut.setEsito(esitoOut);
			}
		}
		return nodoChiediStatoRPTRispostaOut;
    }

    public com.esed.easybridge.webservice.operazioni.NodoChiediCopiaRTRisposta nodoChiediCopiaRT(com.esed.easybridge.webservice.operazioni.NodoChiediCopiaRT bodyrichiesta, com.esed.easybridge.webservice.operazioni.head.ParametroCuteCute cutecute) throws java.rmi.RemoteException {

		String code = null;
		String description = null;
		com.esed.easybridge.webservice.operazioni.NodoChiediCopiaRTRisposta nodoChiediCopiaRTRispostaOut = null; 
		NodoChiediCopiaRTRisposta nodoChiediCopiaRTRisposta = null;
		String identificativoDominio = "";
		
		try {
			java.util.Date dataInizio = null;
			//lettura configurazione
			System.out.println("===========================> nodoChiediCopiaRT - Parametro CuteCute: '" + cutecute.getCutecute() + "'");
			if(cutecute.getCutecute().trim().length() < 5) {
				this.cuteCute = Pad.left(cutecute.getCutecute().trim(), 5, '0');
			} else {
				this.cuteCute = cutecute.getCutecute().trim();
			}
			
			if(this.cuteCute.equals("00000")){
				System.out.println("Parametro CuteCute non presente nei parametri inviati");
				throw new Exception("Parametro CuteCute non presente nei parametri inviati");
			}
			
			this.nodoSpcPortDomain = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.url");
			this.usernameStub = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.usernameStub");
			this.passwordStub = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.passwordStub");
			
			this.proxyEnabled = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyenabled")!=null && propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyenabled").equals("1");
			this.proxyHost = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyhost");
			this.proxyPort = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyport");
			this.proxyUser = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyuser");
			this.proxyPassword = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxypassword");

			if(propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcGestore") != null) {
				this.nodospcGestore = propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcGestore");
			} else {
				this.nodospcGestore = "E";
			}
			
			if(propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcTipologia") != null) {
				this.nodospcTipologia = propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcTipologia");
			} else {
				this.nodospcTipologia = "N";
			}
			
			String codiceContestoPagamento = bodyrichiesta.getCodiceContestoPagamento();
			identificativoDominio = bodyrichiesta.getIdentificativoDominio();
			String identificativoIntermediarioPA = bodyrichiesta.getIdentificativoIntermediarioPA();
			String identificativoStazioneIntermediarioPA = bodyrichiesta.getIdentificativoStazioneIntermediarioPA();
			String iuv = bodyrichiesta.getIdentificativoUnivocoVersamento();
			String passwordPsp = bodyrichiesta.getPassword();
		
			System.out.println("nodoChiediCopiaRT %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println("nodoChiediCopiaRT - CuteCute                             : '" + this.cuteCute + "'");
			System.out.println("nodoChiediCopiaRT - CodiceContestoPagamento              : '" + codiceContestoPagamento + "'");
			System.out.println("nodoChiediCopiaRT - IdentificativoDominio                : '" + identificativoDominio + "'");
			System.out.println("nodoChiediCopiaRT - IdentificativoIntermediarioPA        : '" + identificativoIntermediarioPA + "'");
			System.out.println("nodoChiediCopiaRT - IdentificativoStazioneIntermediarioPA: '" + identificativoStazioneIntermediarioPA + "'");
			System.out.println("nodoChiediCopiaRT - IdentificativoUnivocoVersamento      : '" + iuv + "'");
			System.out.println("nodoChiediCopiaRT - PasswordPsp                          : '" + passwordPsp + "'");

			//inizio LP PG190220
			//bug veniva controllato nodospcTipologia
			//if(this.nodospcTipologia.equals("M")) {
			if(this.nodospcGestore.equals("M")) {
			//fine LP PG190220
				System.out.println("nodoChiediCopiaRT - ambiente del gruppo                   : 'Maggioli'");
				System.out.println("Operazione non eseguibile per Gruppo =/= E-sed");
	        	throw new Exception("Operazione non eseguibile per Gruppo =/= E-sed");
			}
			System.out.println("nodoChiediCopiaRT - ambiente del gruppo                   : 'E-sed'");
			
			System.out.println("nodoChiediCopiaRT - nodoSpcPortDomain                     : '" + this.nodoSpcPortDomain + "'");
			System.out.println("nodoChiediCopiaRT - usernameStub                          : '" + this.usernameStub + "'");
			System.out.println("nodoChiediCopiaRT - passwordStub                          : '" + this.passwordStub + "'");

			System.out.println("nodoChiediCopiaRT %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			
	    	PagamentiTelematiciRPTserviceLocator pagamentiTelematiciRPTserviceLocator = new PagamentiTelematiciRPTserviceLocator();
			System.out.println("========================================================================================================================================");
			System.out.println("nodoChiediCopiaRT PRIMA DI pagamentiTelematiciRPTserviceLocator");
			PagamentiTelematiciRPT pagamentiTelematiciRPT = null;
			if(this.nodospcTipologia.equals("N")) {
				pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(this.nodoSpcPortDomain + "/nodoChiediCopiaRT"));
			} else {
				pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(this.nodoSpcPortDomain));
			}
			System.out.println("nodoChiediCopiaRT DOPO pagamentiTelematiciRPTserviceLocator");
			System.out.println("========================================================================================================================================");
			
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("nodoChiediCopiaRT PRIMA DI SET di _stub");
			PagamentiTelematiciRPTbindingStub _stub = (PagamentiTelematiciRPTbindingStub) pagamentiTelematiciRPT;
			if(this.nodospcTipologia.equals("N")) {
				_stub.setUsername(this.usernameStub);
				_stub.setPassword(this.passwordStub);
			}
			System.out.println("nodoChiediCopiaRT DOPO DI SET di _stub");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			HandlerRegistry hr = pagamentiTelematiciRPTserviceLocator.getHandlerRegistry();
			QName  portName = _stub.getPortName();
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("nodoChiediCopiaRT portName::LocalPart: " + portName.getLocalPart());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			List handlerChain = hr.getHandlerChain(portName);
			
			HandlerInfo hi = new HandlerInfo();
			hi.setHandlerClass(WsNodoRpcHandler.class);
			handlerChain.add(hi);

			if(this.proxyEnabled) {
				System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
				System.out.println("nodoChiediCopiaRT PROXY A B I L I T A T O");
				System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");

				System.getProperties().put("http.proxyHost", this.proxyHost);
				System.getProperties().put("http.nonProxyHosts", "localhost");
				System.getProperties().put("http.proxyPort", this.proxyPort);
				System.getProperties().put("http.proxyUser", this.proxyUser);
				System.getProperties().put("http.proxyPassword", this.proxyPassword);
			}
			
			System.out.println("nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT NodoChiediStatoRPT");
			//inizio LP PG21XX07
			//System.out.println("nodoChiediCopiaRT PRIMA DI new NodoChiediStatoRPT");
			System.out.println("nodoChiediCopiaRT PRIMA DI new nodoChiediCopiaRT");
			//fine LP PG21XX07
			System.out.println("nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT NodoChiediStatoRPT");
			NodoChiediCopiaRT bodyrichiestaNodoChiediCopiaRT = new NodoChiediCopiaRT(identificativoIntermediarioPA, identificativoStazioneIntermediarioPA,  passwordPsp, identificativoDominio, iuv, codiceContestoPagamento);  
			System.out.println("nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT NodoChiediStatoRPT");
			//inizio LP PG21XX07
			//System.out.println("nodoChiediCopiaRT DOPO DI new NodoChiediStatoRPT");
			System.out.println("nodoChiediCopiaRT DOPO DI new nodoChiediCopiaRT");
			//fine LP PG21XX07
			System.out.println("nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT nodoChiediCopiaRT NodoChiediStatoRPT");
			
			//inizio LP PG21XX07
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//fine LP PG21XX07
			dataInizio = new java.util.Date();
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			//inizio LP PG21XX07
			//System.out.println("nodoChiediCopiaRT PRIMA DI nodoChiediCopiaRT: " + dataInizio.getTime());
			System.out.println("nodoChiediCopiaRT PRIMA DI nodoChiediCopiaRT: " + format.format(dataInizio.getTime()));
			//fine LP PG21XX07
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

    		nodoChiediCopiaRTRisposta = pagamentiTelematiciRPT.nodoChiediCopiaRT(bodyrichiestaNodoChiediCopiaRT);
    		dataInizio = new java.util.Date();

			String sEsito = "OK";
			if (nodoChiediCopiaRTRisposta.getFault() != null) {
				FaultBean ff = nodoChiediCopiaRTRisposta.getFault();
				sEsito = "Fault code: '" + ff.getFaultCode() + "' FaultString = ' " + ff.getFaultString() + "' descrizione= " + ff.getDescription();
			}
			System.out.println("****************************************************************************************************************************************");
			//inizio LP PG21XX07
			//System.out.println("DOPO nodoChiediCopiaRT ESITO = " + sEsito + " " + dataInizio.getTime());
			System.out.println("DOPO nodoChiediCopiaRT ESITO = " + sEsito + " " + format.format(dataInizio.getTime()));
			//fine LP PG21XX07
			System.out.println("****************************************************************************************************************************************");
			
		}
	    catch (Exception e) {
	    	e.printStackTrace();
	
			code = "99";
			description = "Errore generico: " + e.getMessage();
			System.out.println("nodoChiediCopiaRT to Esed: " + description);
		} finally {
			if(code != null && description != null) {
				nodoChiediCopiaRTRisposta = new NodoChiediCopiaRTRisposta();
				description = "nodoChiediCopiaRT to Esed: " + code + ": " + description;
				FaultBean fOut = new FaultBean();
				fOut.setFaultCode("PPT_SYSTEM_ERROR"); //PAA_SYSTEM_ERROR
				fOut.setFaultString("Errore generico.");
				fOut.setId(identificativoDominio);
				fOut.setDescription(description);
				fOut.setSerial(1);
				nodoChiediCopiaRTRisposta.setFault(fOut);
			}
		}
		if(nodoChiediCopiaRTRisposta != null) {
			nodoChiediCopiaRTRispostaOut = new com.esed.easybridge.webservice.operazioni.NodoChiediCopiaRTRisposta();
			if(nodoChiediCopiaRTRisposta.getFault() != null) {
				FaultBean ff = nodoChiediCopiaRTRisposta.getFault();
				com.esed.easybridge.webservice.operazioni.FaultBean ffOut = new com.esed.easybridge.webservice.operazioni.FaultBean(ff.getFaultCode(), ff.getFaultString(), ff.getId(), ff.getDescription(), ff.getSerial());
				nodoChiediCopiaRTRispostaOut.setFault(ffOut);
			} else {
				nodoChiediCopiaRTRispostaOut.setRt(nodoChiediCopiaRTRisposta.getRt());
				nodoChiediCopiaRTRispostaOut.setTipoFirma(nodoChiediCopiaRTRisposta.getTipoFirma());
			}
		}
		return nodoChiediCopiaRTRispostaOut;
    }
    
    //inizio LP PG190220
    public com.esed.easybridge.webservice.operazioni.NodoInviaRispostaRevocaRisposta nodoInviaRispostaRevoca(com.esed.easybridge.webservice.operazioni.NodoInviaRispostaRevoca bodyrichiesta, com.esed.easybridge.webservice.operazioni.head.ParametroCuteCute cutecute) throws java.rmi.RemoteException {
		String code = null;
		String description = null;
		com.esed.easybridge.webservice.operazioni.NodoInviaRispostaRevocaRisposta nodoInviaRispostaRevocaRispostaOut = null; 
		NodoInviaRispostaRevocaRisposta nodoInviaRispostaRevocaRisposta = null;
		String identificativoDominio = "";
		
		try {
			java.util.Date dataInizio = null;
			//lettura configurazione
			System.out.println("===========================> nodoInviaRispostaRevoca - Parametro CuteCute: '" + cutecute.getCutecute() + "'");
			if(cutecute.getCutecute().trim().length() < 5) {
				this.cuteCute = Pad.left(cutecute.getCutecute().trim(), 5, '0');
			} else {
				this.cuteCute = cutecute.getCutecute().trim();
			}
			
			if(this.cuteCute.equals("00000")){
				System.out.println("Parametro CuteCute non presente nei parametri inviati");
				throw new Exception("Parametro CuteCute non presente nei parametri inviati");
			}
			
			this.nodoSpcPortDomain = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.url");
			this.usernameStub = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.usernameStub");
			this.passwordStub = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.passwordStub");
			
			this.proxyEnabled = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyenabled")!=null && propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyenabled").equals("1");
			this.proxyHost = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyhost");
			this.proxyPort = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyport");
			this.proxyUser = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyuser");
			this.proxyPassword = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxypassword");

			if(propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcGestore") != null) {
				this.nodospcGestore = propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcGestore");
			} else {
				this.nodospcGestore = "E";
			}
			
			if(propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcTipologia") != null) {
				this.nodospcTipologia = propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcTipologia");
			} else {
				this.nodospcTipologia = "N";
			}
			
			String codiceContestoPagamento = bodyrichiesta.getCodiceContestoPagamento();
			identificativoDominio = bodyrichiesta.getIdentificativoDominio();
			String identificativoIntermediarioPA = bodyrichiesta.getIdentificativoIntermediarioPA();
			String identificativoStazioneIntermediarioPA = bodyrichiesta.getIdentificativoStazioneIntermediarioPA();
			String iuv = bodyrichiesta.getIdentificativoUnivocoVersamento();
			String passwordPsp = bodyrichiesta.getPassword();
			byte[] er = bodyrichiesta.getEr();
			
			System.out.println("nodoInviaRispostaRevoca %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println("nodoInviaRispostaRevoca - CuteCute                             : '" + this.cuteCute + "'");
			System.out.println("nodoInviaRispostaRevoca - CodiceContestoPagamento              : '" + codiceContestoPagamento + "'");
			System.out.println("nodoInviaRispostaRevoca - IdentificativoDominio                : '" + identificativoDominio + "'");
			System.out.println("nodoInviaRispostaRevoca - IdentificativoIntermediarioPA        : '" + identificativoIntermediarioPA + "'");
			System.out.println("nodoInviaRispostaRevoca - IdentificativoStazioneIntermediarioPA: '" + identificativoStazioneIntermediarioPA + "'");
			System.out.println("nodoInviaRispostaRevoca - IdentificativoUnivocoVersamento      : '" + iuv + "'");
			System.out.println("nodoInviaRispostaRevoca - PasswordPsp                          : '" + passwordPsp + "'");
			System.out.println("ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ");
			System.out.println("XML ER nodoInviaRispostaRevoca UTF-8 = " + er.toString());
			System.out.println("ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ");

			if(this.nodospcGestore.equals("M")) {
				System.out.println("nodoInviaRispostaRevoca - ambiente del gruppo                   : 'Maggioli'");
				System.out.println("Operazione non eseguibile per Gruppo =/= E-sed");
	        	throw new Exception("Operazione non eseguibile per Gruppo =/= E-sed");
			}
			System.out.println("nodoInviaRispostaRevoca - ambiente del gruppo                   : 'E-sed'");
			
			System.out.println("nodoInviaRispostaRevoca - nodoSpcPortDomain                     : '" + this.nodoSpcPortDomain + "'");
			System.out.println("nodoInviaRispostaRevoca - usernameStub                          : '" + this.usernameStub + "'");
			System.out.println("nodoInviaRispostaRevoca - passwordStub                          : '" + this.passwordStub + "'");

			System.out.println("nodoInviaRispostaRevoca %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			
	    	PagamentiTelematiciRPTserviceLocator pagamentiTelematiciRPTserviceLocator = new PagamentiTelematiciRPTserviceLocator();
	    	System.out.println("========================================================================================================================================");
	    	System.out.println("nodoInviaRispostaRevoca PRIMA DI pagamentiTelematiciRPTserviceLocator");
			PagamentiTelematiciRPT pagamentiTelematiciRPT = null;
			if(this.nodospcTipologia.equals("N")) {
				pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(this.nodoSpcPortDomain + "/nodoInviaRispostaRevoca"));
			} else {
				pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(this.nodoSpcPortDomain));
			}
			System.out.println("nodoInviaRispostaRevoca DOPO pagamentiTelematiciRPTserviceLocator");
			System.out.println("========================================================================================================================================");
			
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("nodoInviaRispostaRevoca PRIMA DI SET di _stub");
			PagamentiTelematiciRPTbindingStub _stub = (PagamentiTelematiciRPTbindingStub) pagamentiTelematiciRPT;
			if(this.nodospcTipologia.equals("N")) {
				_stub.setUsername(this.usernameStub);
				_stub.setPassword(this.passwordStub);
			}
			info("nodoInviaRispostaRevoca DOPO DI SET di _stub");
			info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			HandlerRegistry hr = pagamentiTelematiciRPTserviceLocator.getHandlerRegistry();
			QName  portName = _stub.getPortName();
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("nodoInviaRispostaRevoca portName::LocalPart: " + portName.getLocalPart());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			List handlerChain = hr.getHandlerChain(portName);
			
			HandlerInfo hi = new HandlerInfo();
			hi.setHandlerClass(WsNodoRpcHandler.class);
			handlerChain.add(hi);

			if(this.proxyEnabled) {
				System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
				System.out.println("nodoInviaRispostaRevoca PROXY A B I L I T A T O");
				System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");

				System.getProperties().put("http.proxyHost", this.proxyHost);
				System.getProperties().put("http.nonProxyHosts", "localhost");
				System.getProperties().put("http.proxyPort", this.proxyPort);
				System.getProperties().put("http.proxyUser", this.proxyUser);
				System.getProperties().put("http.proxyPassword", this.proxyPassword);
			}
			
			System.out.println("nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca NodoChiediStatoRPT");
			System.out.println("nodoInviaRispostaRevoca PRIMA DI new NodoInviaRispostaRevoca");
			System.out.println("nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca NodoChiediStatoRPT");
			NodoInviaRispostaRevoca parameters = new NodoInviaRispostaRevoca(identificativoIntermediarioPA, identificativoStazioneIntermediarioPA, passwordPsp, identificativoDominio, iuv, codiceContestoPagamento, er);  
			System.out.println("nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca NodoChiediStatoRPT");
			System.out.println("nodoInviaRispostaRevoca DOPO DI new NodoInviaRispostaRevoca");
			System.out.println("nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca nodoInviaRispostaRevoca NodoChiediStatoRPT");
			
			
			
			//inizio LP PG21XX07
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//fine LP PG21XX07
			dataInizio = new java.util.Date();
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			//inizio LP PG21XX07
			//System.out.println("nodoInviaRispostaRevoca PRIMA DI nodoInviaRispostaRevoca: " + dataInizio.getTime());
			System.out.println("nodoInviaRispostaRevoca PRIMA DI nodoInviaRispostaRevoca: " + format.format(dataInizio.getTime()));
			//fine LP PG21XX07
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

    		nodoInviaRispostaRevocaRisposta = pagamentiTelematiciRPT.nodoInviaRispostaRevoca(parameters);
    		dataInizio = new java.util.Date();

			String sEsito = "OK";
			if (nodoInviaRispostaRevocaRisposta.getFault() != null) {
				FaultBean ff = nodoInviaRispostaRevocaRisposta.getFault();
				sEsito = "Fault code: '" + ff.getFaultCode() + "' FaultString = ' " + ff.getFaultString() + "' descrizione= " + ff.getDescription();
			}
			System.out.println("****************************************************************************************************************************************");
			//inizio LP PG21XX07
			//System.out.println("DOPO nodoInviaRispostaRevoca ESITO = " + sEsito + " " + dataInizio.getTime());
			System.out.println("DOPO nodoInviaRispostaRevoca ESITO = " + sEsito + " " + format.format(dataInizio.getTime()));
			//fine LP PG21XX07
			System.out.println("****************************************************************************************************************************************");
			
		}
	    catch (Exception e) {
	    	e.printStackTrace();
	
			code = "99";
			description = "Errore generico: " + e.getMessage();
			error("nodoInviaRispostaRevoca to Esed: " + description);
		} finally {
			if(code != null && description != null) {
				nodoInviaRispostaRevocaRisposta = new NodoInviaRispostaRevocaRisposta();
				description = "nodoInviaRispostaRevoca to Esed: " + code + ": " + description;
				FaultBean fOut = new FaultBean();
				fOut.setFaultCode("PPT_SYSTEM_ERROR"); //PAA_SYSTEM_ERROR
				fOut.setFaultString("Errore generico.");
				fOut.setId(identificativoDominio);
				fOut.setDescription(description);
				fOut.setSerial(1);
				nodoInviaRispostaRevocaRisposta.setFault(fOut);
			}
		}
		if(nodoInviaRispostaRevocaRisposta != null) {
			nodoInviaRispostaRevocaRispostaOut = new com.esed.easybridge.webservice.operazioni.NodoInviaRispostaRevocaRisposta();
			if(nodoInviaRispostaRevocaRisposta.getFault() != null) {
				FaultBean ff = nodoInviaRispostaRevocaRisposta.getFault();
				com.esed.easybridge.webservice.operazioni.FaultBean ffOut = new com.esed.easybridge.webservice.operazioni.FaultBean(ff.getFaultCode(), ff.getFaultString(), ff.getId(), ff.getDescription(), ff.getSerial());
				nodoInviaRispostaRevocaRispostaOut.setFault(ffOut);
			} else {
				nodoInviaRispostaRevocaRispostaOut.setEsito(nodoInviaRispostaRevocaRisposta.getEsito());
			}
		}
		return nodoInviaRispostaRevocaRispostaOut;
    }
    //fine LP PG190220
    //inizio LP PG21XX07
    public com.esed.easybridge.webservice.operazioni.NodoChiediListaPendentiRPTRisposta nodoChiediListaPendentiRPT(com.esed.easybridge.webservice.operazioni.NodoChiediListaPendentiRPT bodyrichiesta, com.esed.easybridge.webservice.operazioni.head.ParametroCuteCute cutecute) throws java.rmi.RemoteException {
		String code = null;
		String description = null;
		com.esed.easybridge.webservice.operazioni.NodoChiediListaPendentiRPTRisposta nodoChiediListaPendentiRPTRispostaOut = null; 
		NodoChiediListaPendentiRPTRisposta nodoChiediListaPendentiRPTRisposta = null;
		String identificativoDominio = "";
		com.esed.easybridge.webservice.operazioni.TipoListaRPTPendenti listaRPTPendentiOut = null;
		TipoListaRPTPendenti listaRPTPendenti = null;
		
		try {
			java.util.Date dataInizio = null;
			//lettura configurazione
			System.out.println("===========================> NodoChiediListaPendentiRPT - Parametro CuteCute: '" + cutecute.getCutecute() + "'");
			if(cutecute.getCutecute().trim().length() < 5) {
				this.cuteCute = Pad.left(cutecute.getCutecute().trim(), 5, '0');
			} else {
				this.cuteCute = cutecute.getCutecute().trim();
			}
			
			if(this.cuteCute.equals("00000")){
				System.out.println("Parametro CuteCute non presente nei parametri inviati");
				throw new Exception("Parametro CuteCute non presente nei parametri inviati");
			}
			
			this.nodoSpcPortDomain = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.url");
			this.usernameStub = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.usernameStub");
			this.passwordStub = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.passwordStub");
			
			this.proxyEnabled = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyenabled")!=null && propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyenabled").equals("1");
			this.proxyHost = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyhost");
			this.proxyPort = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyport");
			this.proxyUser = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyuser");
			this.proxyPassword = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxypassword");

			if(propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcGestore") != null) {
				this.nodospcGestore = propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcGestore");
			} else {
				this.nodospcGestore = "E";
			}
			
			if(propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcTipologia") != null) {
				this.nodospcTipologia = propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcTipologia");
			} else {
				this.nodospcTipologia = "N";
			}
			
			String identificativoIntermediarioPA = bodyrichiesta.getIdentificativoIntermediarioPA();
			String identificativoStazioneIntermediarioPA = bodyrichiesta.getIdentificativoStazioneIntermediarioPA();
			String passwordPsp = bodyrichiesta.getPassword();
			identificativoDominio = bodyrichiesta.getIdentificativoDominio();
			Calendar rangeDa = bodyrichiesta.getRangeDa();
			Calendar rangeA = bodyrichiesta.getRangeA();
			PositiveInteger dimensioneLista = bodyrichiesta.getDimensioneLista();
		
			System.out.println("NodoChiediListaPendentiRPT %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println("NodoChiediListaPendentiRPT - CuteCute                             : '" + this.cuteCute + "'");
			System.out.println("NodoChiediListaPendentiRPT - IdentificativoIntermediarioPA        : '" + identificativoIntermediarioPA + "'");
			System.out.println("NodoChiediListaPendentiRPT - IdentificativoStazioneIntermediarioPA: '" + identificativoStazioneIntermediarioPA + "'");
			System.out.println("NodoChiediListaPendentiRPT - PasswordPsp                          : '" + passwordPsp + "'");
			if(identificativoDominio != null)
				System.out.println("NodoChiediListaPendentiRPT - IdentificativoDominio                : '" + identificativoDominio + "'");
			else
				System.out.println("NodoChiediListaPendentiRPT - IdentificativoDominio                : -");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(rangeDa != null) {
				System.out.println("NodoChiediListaPendentiRPT - RangeDa                              : '" + format.format(rangeDa.getTime().getTime()) + "'");
			}
			else
				System.out.println("NodoChiediListaPendentiRPT - RangeDa                              : -");
			if(rangeA != null) {
				System.out.println("NodoChiediListaPendentiRPT - RangeA                               : '" + format.format(rangeA.getTime().getTime()) + "'");
			}
			else
				System.out.println("NodoChiediListaPendentiRPT - RangeA                               : -");
			System.out.println("NodoChiediListaPendentiRPT - DimensioneListaficativoDominio       : '" + dimensioneLista + "'");

			if(this.nodospcGestore.equals("M")) {
				System.out.println("NodoChiediListaPendentiRPT - ambiente del gruppo                   : 'Maggioli'");
				System.out.println("Operazione non eseguibile per Gruppo =/= E-sed");
	        	throw new Exception("Operazione non eseguibile per Gruppo =/= E-sed");
			}
			System.out.println("NodoChiediListaPendentiRPT - ambiente del gruppo                   : 'E-sed'");
			
			System.out.println("NodoChiediListaPendentiRPT - nodoSpcPortDomain                     : '" + this.nodoSpcPortDomain + "'");
			System.out.println("NodoChiediListaPendentiRPT - usernameStub                          : '" + this.usernameStub + "'");
			System.out.println("NodoChiediListaPendentiRPT - passwordStub                          : '" + this.passwordStub + "'");

			System.out.println("NodoChiediListaPendentiRPT %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			
	    	PagamentiTelematiciRPTserviceLocator pagamentiTelematiciRPTserviceLocator = new PagamentiTelematiciRPTserviceLocator();
			System.out.println("========================================================================================================================================");
			System.out.println("NodoChiediListaPendentiRPT PRIMA DI pagamentiTelematiciRPTserviceLocator");
			PagamentiTelematiciRPT pagamentiTelematiciRPT = null;
			if(this.nodospcTipologia.equals("N")) {
				pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(this.nodoSpcPortDomain + "/nodoChiediListaPendentiRPT"));
			} else {
				pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(this.nodoSpcPortDomain));
			}
			System.out.println("NodoChiediListaPendentiRPT DOPO pagamentiTelematiciRPTserviceLocator");
			System.out.println("========================================================================================================================================");
			
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("NodoChiediListaPendentiRPT PRIMA DI SET di _stub");
			PagamentiTelematiciRPTbindingStub _stub = (PagamentiTelematiciRPTbindingStub) pagamentiTelematiciRPT;
			if(this.nodospcTipologia.equals("N")) {
				_stub.setUsername(this.usernameStub);
				_stub.setPassword(this.passwordStub);
			}
			System.out.println("NodoChiediListaPendentiRPT DOPO DI SET di _stub");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			HandlerRegistry hr = pagamentiTelematiciRPTserviceLocator.getHandlerRegistry();
			QName  portName = _stub.getPortName();
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("NodoChiediListaPendentiRPT portName::LocalPart: " + portName.getLocalPart());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			List handlerChain = hr.getHandlerChain(portName);
			
			HandlerInfo hi = new HandlerInfo();
			hi.setHandlerClass(WsNodoRpcHandler.class);
			handlerChain.add(hi);

			if(this.proxyEnabled) {
				System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
				System.out.println("NodoChiediListaPendentiRPT PROXY A B I L I T A T O");
				System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");

				System.getProperties().put("http.proxyHost", this.proxyHost);
				System.getProperties().put("http.nonProxyHosts", "localhost");
				System.getProperties().put("http.proxyPort", this.proxyPort);
				System.getProperties().put("http.proxyUser", this.proxyUser);
				System.getProperties().put("http.proxyPassword", this.proxyPassword);
			}

			System.out.println("NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediStatoRPT");
			System.out.println("NodoChiediListaPendentiRPT PRIMA DI new NodoChiediListaPendentiRPT");
			System.out.println("NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediStatoRPT");
			NodoChiediListaPendentiRPT bodyrichiestaIN = new NodoChiediListaPendentiRPT(identificativoIntermediarioPA, identificativoStazioneIntermediarioPA, passwordPsp, identificativoDominio, rangeDa, rangeA, dimensioneLista);  
			System.out.println("NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediStatoRPT");
			System.out.println("NodoChiediListaPendentiRPT DOPO DI new NodoChiediListaPendentiRPT");
			System.out.println("NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediListaPendentiRPT NodoChiediStatoRPT");
			
			dataInizio = new java.util.Date();
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			System.out.println("NodoChiediListaPendentiRPT PRIMA DI NodoChiediListaPendentiRPT: " + format.format(dataInizio.getTime()));
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    		nodoChiediListaPendentiRPTRisposta = pagamentiTelematiciRPT.nodoChiediListaPendentiRPT(bodyrichiestaIN);
    		dataInizio = new java.util.Date();

			String sEsito = "OK";
			if (nodoChiediListaPendentiRPTRisposta.getFault() != null) {
				FaultBean ff = nodoChiediListaPendentiRPTRisposta.getFault();
				sEsito = "Fault code: '" + ff.getFaultCode() + "' FaultString = ' " + ff.getFaultString() + "' descrizione= " + ff.getDescription();
			} else {
				listaRPTPendenti = nodoChiediListaPendentiRPTRisposta.getListaRPTPendenti();
				listaRPTPendentiOut = new com.esed.easybridge.webservice.operazioni.TipoListaRPTPendenti();
				int quanti = listaRPTPendenti.getTotRestituiti();
				listaRPTPendentiOut.setTotRestituiti(quanti);
				System.out.println("ListaRPTPendenti ListaRPTPendenti ListaRPTPendenti ListaRPTPendenti ListaRPTPendenti ListaRPTPendenti ListaRPTPendenti ListaRPTPendenti ");
				System.out.println("NodoChiediListaPendentiRPT ListaRPTPendenti::TotRestituiti                             : " + quanti);
				if(quanti > 0) {
					//inizio LP PG21XX07 - 20210716
					System.out.println("NodoChiediListaPendentiRPT prima di new com.esed.easybridge.webservice.operazioni.TipoRPTPendente[" + quanti + "]");
					com.esed.easybridge.webservice.operazioni.TipoRPTPendente[] rptPendenteOut = new com.esed.easybridge.webservice.operazioni.TipoRPTPendente[quanti];
					System.out.println("NodoChiediListaPendentiRPT dopo di new com.esed.easybridge.webservice.operazioni.TipoRPTPendente[" + quanti + "]");
					listaRPTPendentiOut.setRptPendente(rptPendenteOut);
					System.out.println("NodoChiediListaPendentiRPT dopo listaRPTPendentiOut.setRptPendente(rptPendenteOut)");
					//fine LP PG21XX07 - 20210716
					for (int i = 0; i < quanti; i++) {
						System.out.println("NodoChiediListaPendentiRPT for #" + i);
						TipoRPTPendente tipoRptPendente = listaRPTPendenti.getRptPendente(i);
						System.out.println("NodoChiediListaPendentiRPT dopo listaRPTPendenti.getRptPendente(" + i + ")");
						if(tipoRptPendente == null) 
							System.out.println("NodoChiediListaPendentiRPT dopo tipoRptPendente #" + i + " non valorizzato");
						//inizio LP PG21XX07 - 20210716
						//com.esed.easybridge.webservice.operazioni.TipoRPTPendente tipoRptPendenteOut = new com.esed.easybridge.webservice.operazioni.TipoRPTPendente();
						//tipoRptPendenteOut.setCodiceContestoPagamento(tipoRptPendente.getCodiceContestoPagamento());
						//tipoRptPendenteOut.setIdentificativoDominio(tipoRptPendente.getIdentificativoDominio());
						//tipoRptPendenteOut.setIdentificativoUnivocoVersamento(tipoRptPendente.getIdentificativoUnivocoVersamento());
						//tipoRptPendenteOut.setStato(tipoRptPendente.getStato());
						//listaRPTPendentiOut.setRptPendente(i, tipoRptPendenteOut);
						//int j = i + 1;
						//System.out.println("NodoChiediListaPendentiRPT ListaRPTPendenti[" + j + "]::CodiceContestoPagamento        : " + tipoRptPendenteOut.getCodiceContestoPagamento());
						//System.out.println("NodoChiediListaPendentiRPT ListaRPTPendenti[" + j + "]::IdentificativoDominio          : " + tipoRptPendenteOut.getIdentificativoDominio());
						//System.out.println("NodoChiediListaPendentiRPT ListaRPTPendenti[" + j + "]::IdentificativoUnivocoVersamento: " + tipoRptPendenteOut.getIdentificativoUnivocoVersamento());
						//System.out.println("NodoChiediListaPendentiRPT ListaRPTPendenti[" + j + "]::Stato                          : " + tipoRptPendenteOut.getStato());
						System.out.println("NodoChiediListaPendentiRPT prima di new rptPendenteOut[" + i + "]");
						rptPendenteOut[i] = new com.esed.easybridge.webservice.operazioni.TipoRPTPendente();
						System.out.println("NodoChiediListaPendentiRPT dopo di new rptPendenteOut[" + i + "]");
						rptPendenteOut[i].setCodiceContestoPagamento(tipoRptPendente.getCodiceContestoPagamento());
						System.out.println("NodoChiediListaPendentiRPT ListaRPTPendenti[" + i + "]::CodiceContestoPagamento        : " + rptPendenteOut[i].getCodiceContestoPagamento());
						rptPendenteOut[i].setIdentificativoDominio(tipoRptPendente.getIdentificativoDominio());
						System.out.println("NodoChiediListaPendentiRPT ListaRPTPendenti[" + i + "]::IdentificativoDominio          : " + rptPendenteOut[i].getIdentificativoDominio());
						rptPendenteOut[i].setIdentificativoUnivocoVersamento(tipoRptPendente.getIdentificativoUnivocoVersamento());
						System.out.println("NodoChiediListaPendentiRPT ListaRPTPendenti[" + i + "]::IdentificativoUnivocoVersamento: " + rptPendenteOut[i].getIdentificativoUnivocoVersamento());
						rptPendenteOut[i].setStato(tipoRptPendente.getStato());
						System.out.println("NodoChiediListaPendentiRPT ListaRPTPendenti[" + i + "]::Stato                          : " + rptPendenteOut[i].getStato());
						//fine LP PG21XX07 - 20210716
					}
				}
				System.out.println("ListaRPTPendenti ListaRPTPendenti ListaRPTPendenti ListaRPTPendenti ListaRPTPendenti ListaRPTPendenti ListaRPTPendenti ListaRPTPendenti ");
			}
			System.out.println("****************************************************************************************************************************************");
			System.out.println("DOPO NodoChiediListaPendentiRPT ESITO = " + sEsito + " " + format.format(dataInizio.getTime()));
			System.out.println("****************************************************************************************************************************************");
		}
	    catch (Exception e) {
	    	e.printStackTrace();
	
			code = "99";
			description = "Errore generico: " + e.getMessage();
			System.out.println("NodoChiediListaPendentiRPT to Esed: " + description);
		} finally {
			if(code != null && description != null) {
				nodoChiediListaPendentiRPTRisposta = new NodoChiediListaPendentiRPTRisposta();
				description = "NodoChiediListaPendentiRPT to Esed: " + code + ": " + description;
				FaultBean fOut = new FaultBean();
				fOut.setFaultCode("PPT_SYSTEM_ERROR"); //PAA_SYSTEM_ERROR
				fOut.setFaultString("Errore generico.");
				fOut.setId(identificativoDominio);
				fOut.setDescription(description);
				fOut.setSerial(1);
				nodoChiediListaPendentiRPTRisposta.setFault(fOut);
			}
		}
		if(nodoChiediListaPendentiRPTRisposta != null) {
			nodoChiediListaPendentiRPTRispostaOut = new com.esed.easybridge.webservice.operazioni.NodoChiediListaPendentiRPTRisposta();
			if(nodoChiediListaPendentiRPTRisposta.getFault() != null) {
				FaultBean ff = nodoChiediListaPendentiRPTRisposta.getFault();
				com.esed.easybridge.webservice.operazioni.FaultBean ffOut = new com.esed.easybridge.webservice.operazioni.FaultBean(ff.getFaultCode(), ff.getFaultString(), ff.getId(), ff.getDescription(), ff.getSerial());
				nodoChiediListaPendentiRPTRispostaOut.setFault(ffOut);
			} else {
				nodoChiediListaPendentiRPTRispostaOut.setListaRPTPendenti(listaRPTPendentiOut);
			}
		}
		return nodoChiediListaPendentiRPTRispostaOut;
    }

    public com.esed.easybridge.webservice.operazioni.NodoPAChiediInformativaPARisposta nodoPAChiediInformativaPA(com.esed.easybridge.webservice.operazioni.NodoPAChiediInformativaPA bodyrichiesta, com.esed.easybridge.webservice.operazioni.head.ParametroCuteCute cutecute) throws java.rmi.RemoteException {
		String code = null;
		String description = null;
		com.esed.easybridge.webservice.operazioni.NodoPAChiediInformativaPARisposta nodoPAChiediInformativaPARispostaOut = null; 
		NodoPAChiediInformativaPARisposta nodoPAChiediInformativaPARisposta = null;
		String identificativoDominio = "";
		byte[] xmlInformativa = null;
		
		try {
			java.util.Date dataInizio = null;
			//lettura configurazione
			System.out.println("===========================> NodoPAChiediInformativaPA - Parametro CuteCute: '" + cutecute.getCutecute() + "'");
			if(cutecute.getCutecute().trim().length() < 5) {
				this.cuteCute = Pad.left(cutecute.getCutecute().trim(), 5, '0');
			} else {
				this.cuteCute = cutecute.getCutecute().trim();
			}
			
			if(this.cuteCute.equals("00000")){
				System.out.println("Parametro CuteCute non presente nei parametri inviati");
				throw new Exception("Parametro CuteCute non presente nei parametri inviati");
			}
			
			this.nodoSpcPortDomain = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.url");
			this.usernameStub = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.usernameStub");
			this.passwordStub = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.passwordStub");
			
			this.proxyEnabled = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyenabled")!=null && propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyenabled").equals("1");
			this.proxyHost = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyhost");
			this.proxyPort = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyport");
			this.proxyUser = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxyuser");
			this.proxyPassword = propertiesTree().getProperty(this.cuteCute + ".nodoSpcPortDomain.proxypassword");

			if(propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcGestore") != null) {
				this.nodospcGestore = propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcGestore");
			} else {
				this.nodospcGestore = "E";
			}
			
			if(propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcTipologia") != null) {
				this.nodospcTipologia = propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcTipologia");
			} else {
				this.nodospcTipologia = "N";
			}
			
			
			String identificativoIntermediarioPA = bodyrichiesta.getIdentificativoIntermediarioPA();
			String identificativoStazioneIntermediarioPA = bodyrichiesta.getIdentificativoStazioneIntermediarioPA();
			String passwordPsp = bodyrichiesta.getPassword();
			identificativoDominio = bodyrichiesta.getIdentificativoDominio();
		
			System.out.println("NodoPAChiediInformativaPA %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println("NodoPAChiediInformativaPA - CuteCute                             : '" + this.cuteCute + "'");
			System.out.println("NodoPAChiediInformativaPA - IdentificativoIntermediarioPA        : '" + identificativoIntermediarioPA + "'");
			System.out.println("NodoPAChiediInformativaPA - IdentificativoStazioneIntermediarioPA: '" + identificativoStazioneIntermediarioPA + "'");
			System.out.println("NodoPAChiediInformativaPA - PasswordPsp                          : '" + passwordPsp + "'");
			System.out.println("NodoPAChiediInformativaPA - IdentificativoDominio                : '" + identificativoDominio + "'");

			if(this.nodospcGestore.equals("M")) {
				System.out.println("NodoPAChiediInformativaPA - ambiente del gruppo                   : 'Maggioli'");
				System.out.println("Operazione non eseguibile per Gruppo =/= E-sed");
	        	throw new Exception("Operazione non eseguibile per Gruppo =/= E-sed");
			}
			System.out.println("NodoPAChiediInformativaPA - ambiente del gruppo                   : 'E-sed'");
			
			System.out.println("NodoPAChiediInformativaPA - nodoSpcPortDomain                     : '" + this.nodoSpcPortDomain + "'");
			System.out.println("NodoPAChiediInformativaPA - usernameStub                          : '" + this.usernameStub + "'");
			System.out.println("NodoPAChiediInformativaPA - passwordStub                          : '" + this.passwordStub + "'");

			System.out.println("NodoPAChiediInformativaPA %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			
	    	PagamentiTelematiciRPTserviceLocator pagamentiTelematiciRPTserviceLocator = new PagamentiTelematiciRPTserviceLocator();
			System.out.println("========================================================================================================================================");
			System.out.println("NodoPAChiediInformativaPA PRIMA DI pagamentiTelematiciRPTserviceLocator");
			PagamentiTelematiciRPT pagamentiTelematiciRPT = null;
			if(this.nodospcTipologia.equals("N")) {
				pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(this.nodoSpcPortDomain + "/nodoPAChiediInformativaPA"));
			} else {
				pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(this.nodoSpcPortDomain));
			}
			System.out.println("NodoPAChiediInformativaPA DOPO pagamentiTelematiciRPTserviceLocator");
			System.out.println("========================================================================================================================================");
			
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("NodoPAChiediInformativaPA PRIMA DI SET di _stub");
			PagamentiTelematiciRPTbindingStub _stub = (PagamentiTelematiciRPTbindingStub) pagamentiTelematiciRPT;
			if(this.nodospcTipologia.equals("N")) {
				_stub.setUsername(this.usernameStub);
				_stub.setPassword(this.passwordStub);
			}
			System.out.println("NodoPAChiediInformativaPA DOPO DI SET di _stub");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			HandlerRegistry hr = pagamentiTelematiciRPTserviceLocator.getHandlerRegistry();
			QName  portName = _stub.getPortName();
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("NodoPAChiediInformativaPA portName::LocalPart: " + portName.getLocalPart());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			List handlerChain = hr.getHandlerChain(portName);
			
			HandlerInfo hi = new HandlerInfo();
			hi.setHandlerClass(WsNodoRpcHandler.class);
			handlerChain.add(hi);

			if(this.proxyEnabled) {
				System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
				System.out.println("NodoPAChiediInformativaPA PROXY A B I L I T A T O");
				System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");

				System.getProperties().put("http.proxyHost", this.proxyHost);
				System.getProperties().put("http.nonProxyHosts", "localhost");
				System.getProperties().put("http.proxyPort", this.proxyPort);
				System.getProperties().put("http.proxyUser", this.proxyUser);
				System.getProperties().put("http.proxyPassword", this.proxyPassword);
			}

			System.out.println("NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoChiediStatoRPT");
			System.out.println("NodoPAChiediInformativaPA PRIMA DI new NodoPAChiediInformativaPA");
			System.out.println("NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoChiediStatoRPT");
			NodoPAChiediInformativaPA bodyrichiestaIN = new NodoPAChiediInformativaPA(identificativoIntermediarioPA, identificativoStazioneIntermediarioPA, passwordPsp, identificativoDominio);
			System.out.println("NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoChiediStatoRPT");
			System.out.println("NodoPAChiediInformativaPA DOPO DI new NodoPAChiediInformativaPA");
			System.out.println("NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoPAChiediInformativaPA NodoChiediStatoRPT");
			

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dataInizio = new java.util.Date();
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			System.out.println("NodoPAChiediInformativaPA PRIMA DI NodoPAChiediInformativaPA: " + format.format(dataInizio.getTime()));
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

    		nodoPAChiediInformativaPARisposta = pagamentiTelematiciRPT.nodoPAChiediInformativaPA(bodyrichiestaIN);
    		dataInizio = new java.util.Date();

			String sEsito = "OK";
			if (nodoPAChiediInformativaPARisposta.getFault() != null) {
				FaultBean ff = nodoPAChiediInformativaPARisposta.getFault();
				sEsito = "Fault code: '" + ff.getFaultCode() + "' FaultString = ' " + ff.getFaultString() + "' descrizione= " + ff.getDescription();
			} else {
				xmlInformativa = nodoPAChiediInformativaPARisposta.getXmlInformativa();
				System.out.println("ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ER BYTE 64 ");
				System.out.println("XMLINFORMATIVA BYTE 64 XMLINFORMATIVA BYTE 64 XMLINFORMATIVA BYTE 64 XMLINFORMATIVA BYTE 64 XMLINFORMATIVA BYTE 64 XMLINFORMATIVA BYTE 64 ");
				System.out.println("XML XMLINFORMATIVA nodoInviaRispostaRevoca UTF-8 =\r\n" + xmlInformativa.toString());
				System.out.println("XMLINFORMATIVA BYTE 64 XMLINFORMATIVA BYTE 64 XMLINFORMATIVA BYTE 64 XMLINFORMATIVA BYTE 64 XMLINFORMATIVA BYTE 64 XMLINFORMATIVA BYTE 64 ");
			}
			System.out.println("****************************************************************************************************************************************");
			System.out.println("DOPO NodoPAChiediInformativaPA ESITO = " + sEsito + " " + format.format(dataInizio.getTime()));
			System.out.println("****************************************************************************************************************************************");
		}
	    catch (Exception e) {
	    	e.printStackTrace();
	
			code = "99";
			description = "Errore generico: " + e.getMessage();
			System.out.println("NodoPAChiediInformativaPA to Esed: " + description);
		} finally {
			if(code != null && description != null) {
				nodoPAChiediInformativaPARisposta = new NodoPAChiediInformativaPARisposta();
				description = "NodoPAChiediInformativaPA to Esed: " + code + ": " + description;
				FaultBean fOut = new FaultBean();
				fOut.setFaultCode("PPT_SYSTEM_ERROR"); //PAA_SYSTEM_ERROR
				fOut.setFaultString("Errore generico.");
				fOut.setId(identificativoDominio);
				fOut.setDescription(description);
				fOut.setSerial(1);
				nodoPAChiediInformativaPARisposta.setFault(fOut);
			}
		}
		if(nodoPAChiediInformativaPARisposta != null) {
			nodoPAChiediInformativaPARispostaOut = new com.esed.easybridge.webservice.operazioni.NodoPAChiediInformativaPARisposta();
			if(nodoPAChiediInformativaPARisposta.getFault() != null) {
				FaultBean ff = nodoPAChiediInformativaPARisposta.getFault();
				com.esed.easybridge.webservice.operazioni.FaultBean ffOut = new com.esed.easybridge.webservice.operazioni.FaultBean(ff.getFaultCode(), ff.getFaultString(), ff.getId(), ff.getDescription(), ff.getSerial());
				nodoPAChiediInformativaPARispostaOut.setFault(ffOut);
			} else {
				nodoPAChiediInformativaPARispostaOut.setXmlInformativa(xmlInformativa);
			}
		}
		return nodoPAChiediInformativaPARispostaOut;
    }
    //fine LP PG21XX07
}
