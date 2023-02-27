/**
 * EasybridgeSOAPBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.esed.easybridge.webservice.source;

import gov.telematici.pagamenti.ws.FaultBean;
import gov.telematici.pagamenti.ws.NodoInviaCarrelloRPT;
import gov.telematici.pagamenti.ws.TipoElementoListaRPT;
import gov.telematici.pagamenti.ws.ppthead.IntestazioneCarrelloPPT;
import it.gov.spcoop.NodoPagamentiSPC.servizi.PagamentiTelematiciRPT.PagamentiTelematiciRPT;
import it.gov.spcoop.NodoPagamentiSPC.servizi.PagamentiTelematiciRPT.PagamentiTelematiciRPTbindingStub;
import it.gov.spcoop.NodoPagamentiSPC.servizi.PagamentiTelematiciRPT.PagamentiTelematiciRPTserviceLocator;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.axis.types.URI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.esed.easybridge.core.dao.EyBridgeDao;
import com.esed.easybridge.core.model.CuteCute;
import com.esed.easybridge.core.model.RegistroIdSession;
import com.esed.easybridge.core.utility.EyBridgeUtility;
import com.esed.easybridge.webservice.config.PropKeys;
import com.esed.easybridge.webservice.dati.InviaCarrelloRTPResponse;
import com.esed.easybridge.webservice.exception.DataBaseException;
import com.esed.easybridge.webservice.helper.EasybridgeHelper;
import com.esed.nodospc.wisp2.webservice.client.WsNodoRpcHandler;

public class EasybridgeSOAPBindingImpl extends com.esed.easybridge.webservice.handler.WebServiceHandler implements com.esed.easybridge.webservice.source.EasybridgeInterface{

	/**
	 * identificativoCarrello : parametro nell'XML in input
	 * CUTECUTE : parametro nell'XML in input o parametro string di input
	 * 
	 * !!ATTENZIONE!!: Ogni parametro nel file properties deve essere relativo ad un CUTECUTE
	 * 
	 * Parametri necessari alla chiamata verso il nodo:
	 * nodoSpcPortDomain : prendere da properties
	 * identificativoIntermediarioPA : prendere da properties?
	 * identificativoStazioneIntermediarioPA : prendere da properties?
	 * username x stub : prendere da properties
	 * password x stub : prendere da properties
	 * 
	 * dati x proxy : prendere da properties
	 * 
	 * passwordPsp : prendere da properties
	 * identificativoPSP = "AGID_01"; prendere da properties
	 * identificativoIntermediarioPSP = "97735020584"; prendere da properties
	 * identificativoCanale = "97735020584_02"; prendere da properties
	 * 
	 */
	
	String cuteCute = "";
	//inizio LP PG210130 Step04
	boolean multiBeneficiario = false;
	//fine LP PG210130 Step04
	String nodoSpcPortDomain = "";
	String usernameStub = "";
	String passwordStub = "";
	
	Boolean proxyEnabled = false;
	String proxyHost = "";
	String proxyPort = "";
	String proxyUser = "";
	String proxyPassword = "";
	
	String identificativoIntermediarioPA = "";
	String identificativoStazioneIntermediarioPA = "";
	
	String identificativoPSP = "AGID_01";
	String identificativoIntermediarioPSP = "97735020584";
	String identificativoCanale = "97735020584_02";
	String passwordPsp = "";
	//inizio LP PG1900XX_001
	String nodospcTipologia = "N";
	String schema = "";
	String driver = "";
 	String url = "";
 	String username = "";
 	String password = "";
 	String autocommit = "";
	//fine LP PG1900XX_001
	
	public com.esed.easybridge.webservice.dati.InviaCarrelloRTPResponse inviaCarrelloRTP(com.esed.easybridge.webservice.dati.InviaCarrelloRTPRequest in) throws java.rmi.RemoteException, com.esed.easybridge.webservice.srv.FaultType {

		//inizio LP PG1900XX_001
		schema = propertiesTree().getProperty(PropKeys.DATABASE_SCHEMA.format());
		driver = propertiesTree().getProperty(PropKeys.DATABASE_DRIVER.format());
	 	url = propertiesTree().getProperty(PropKeys.DATABASE_URL.format());
	 	username = propertiesTree().getProperty(PropKeys.DATABASE_USERNAME.format());
	    password = propertiesTree().getProperty(PropKeys.DATABASE_PASSWORD.format());
	 	autocommit = propertiesTree().getProperty(PropKeys.DATABASE_AUTOCOMMIT.format());

	 	EyBridgeDao ebDao = null;
	 	RegistroIdSession regIdSession = null; 
	 	//fine LP PG1900XX_001

		//String xmlCarrelloRPT = in.getXmlCarrelloRTP();
		String xmlCarrelloRPT;
	    try {
			xmlCarrelloRPT = new String(in.getXmlCarrelloRTP().getBytes(),"UTF-8");
			System.out.println("xmlCarrelloRPT: " + xmlCarrelloRPT);

//        if(true) { //Forzatura per testare una risposta positiva dal Nodo
//        	InviaCarrelloRTPResponse easybridgeInviaCarrelloRTPResponse = new InviaCarrelloRTPResponse();
//
//    		easybridgeInviaCarrelloRTPResponse.setEsitoComplessivoOperazione("OK");
//   			easybridgeInviaCarrelloRTPResponse.setUrl("http://www.e-sed.it/?url-wisp-2");					
//			return easybridgeInviaCarrelloRTPResponse;			
//        }
        
			//inizio LP PG1900XX_001
			//inizio per utilizzare da Web Service Explorer
			/*
			if(xmlCarrelloRPT.substring(0, 1).equals("&")) {
				xmlCarrelloRPT = xmlCarrelloRPT.replaceAll("&lt;", "<");				
				xmlCarrelloRPT = xmlCarrelloRPT.replaceAll("&gt;", ">");				
				System.out.println(" ==> xmlCarrelloRPT: " + xmlCarrelloRPT);
			}
			//fine utilizzare da Web Service Explorer
		    */			
			//fine LP PG1900XX_001
			
 			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true);
	
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document doc = builder.parse( new ByteArrayInputStream(xmlCarrelloRPT.getBytes()));
			doc.getDocumentElement().normalize();
			
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			
	        this.cuteCute = "";
	        String identificativoCarrello = String.valueOf(System.currentTimeMillis()); //Assegno un valore univoco all'identificativoCarrello

	        Node cuteCuteNode = (Node)(xpath.evaluate("/carrelloRPT/cutecute", doc, XPathConstants.NODE));
	        this.cuteCute = cuteCuteNode != null ?  cuteCuteNode.getTextContent() : "";
	        
	        if(this.cuteCute.equals("")){
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
	    	   	
			this.identificativoIntermediarioPA = propertiesTree().getProperty(this.cuteCute + ".nodospc.identificativoIntermediarioPA");
			this.identificativoStazioneIntermediarioPA = propertiesTree().getProperty(this.cuteCute + ".nodospc.identificativoStazioneIntermediarioPA");
			
	
			this.identificativoPSP = propertiesTree().getProperty(this.cuteCute + ".nodospc.identificativoPSP"); // "AGID_01";
			this.identificativoIntermediarioPSP = propertiesTree().getProperty(this.cuteCute + ".nodospc.identificativoIntermediarioPSP"); // "97735020584";
			this.identificativoCanale = propertiesTree().getProperty(this.cuteCute + ".nodospc.identificativoCanale"); // "97735020584_02";
			this.passwordPsp = propertiesTree().getProperty(this.cuteCute + ".nodospc.passwordPsp");

			//inizio LP PG1900XX_001
			if(propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcTipologia") != null) {
				this.nodospcTipologia = propertiesTree().getProperty(this.cuteCute + ".nodospc.nodospcTipologia");
			} else {
				this.nodospcTipologia = "N";
			}
			
			ebDao = EyBridgeUtility.getDao(schema, driver, url, username, password, autocommit);
			if(ebDao == null) {
	        	throw new DataBaseException("");
			}
			//fine LP PG1900XX_001
			
			
			
			System.out.println("this.nodoSpcPortDomain =" + this.nodoSpcPortDomain);
			System.out.println("this.usernameStub = "+this.usernameStub);
			System.out.println("this.passwordStub = " + this.passwordStub);
	    	
			System.out.println("this.identificativoIntermediarioPA = "+ this.identificativoIntermediarioPA);
			System.out.println("this.identificativoStazioneIntermediarioPA = "+ this.identificativoIntermediarioPA);
			
	
			System.out.println("this.identificativoPSP = "+this.identificativoPSP);
			
			//Richiamare il WS del Nodo nodoInviaCarrelloRPT()
			PagamentiTelematiciRPTserviceLocator pagamentiTelematiciRPTserviceLocator = new PagamentiTelematiciRPTserviceLocator();
			//inizio LP PG1900XX_001
			//PagamentiTelematiciRPT pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(nodoSpcPortDomain + "/nodoInviaCarrelloRPT")); 
			PagamentiTelematiciRPT pagamentiTelematiciRPT = null;
			
			if(this.nodospcTipologia.equals("N")) {
				pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(nodoSpcPortDomain + "/nodoInviaCarrelloRPT"));
			} else {
				pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(nodoSpcPortDomain)); 
			}
			//fine LP PG1900XX_001
			
			PagamentiTelematiciRPTbindingStub _stub = (PagamentiTelematiciRPTbindingStub)pagamentiTelematiciRPT;
			//inizio LP PG1900XX_001
			if(this.nodospcTipologia.equals("N")) {
			//fine LP PG1900XX_001
				_stub.setUsername(usernameStub);
				_stub.setPassword(passwordStub);
			//inizio LP PG1900XX_001
			}
			//fine LP PG1900XX_001
			
			HandlerRegistry hr = pagamentiTelematiciRPTserviceLocator.getHandlerRegistry();
			QName  portName = _stub.getPortName();
			List handlerChain = hr.getHandlerChain(portName);
			
			HandlerInfo hi = new HandlerInfo();
			hi.setHandlerClass(WsNodoRpcHandler.class);
			handlerChain.add(hi);
	
			if(proxyEnabled) {
				System.getProperties().put("http.proxyHost", proxyHost);
				System.getProperties().put("http.nonProxyHosts", "localhost");
				System.getProperties().put("http.proxyPort", proxyPort);
				System.getProperties().put("http.proxyUser", proxyUser);
				System.getProperties().put("http.proxyPassword", proxyPassword);
			}
			
			URI actor = null;
			Boolean mustUnderstand = null;		
			String identificativoDominio = "";
				
			//-----------------------------------------------------------------------------
	        //--- Ciclo su ogni RPT contenuto nell'xml del carrello ricevuto
			//TipoElementoListaRPT[] listaRPTs = {elementoListaRPT};
			List<TipoElementoListaRPT> listaRPTs = new ArrayList<TipoElementoListaRPT>();
			
			NodeList rptNodeList = doc.getElementsByTagName("RPT");
	        //inizio LP PG210130 Step04
	        Node multiBeneficiarioNode = (Node) (xpath.evaluate("/carrelloRPT/multibeneficiario", doc, XPathConstants.NODE));
	        if(multiBeneficiarioNode != null) {
	        	this.multiBeneficiario = multiBeneficiarioNode.getTextContent().equals("S");
				if(this.multiBeneficiario) {
	    			System.out.println("carrello con set attributo multiBeneficiario: true");
			        for(int i = 0; i < rptNodeList.getLength(); i++) {
			        	Node rptNode = rptNodeList.item(i);
			        	
			        	if (rptNode.getNodeType() == Node.ELEMENT_NODE) {
			        		Element eElement = (Element) rptNode;
			        		
				    		String codiceContestoPagamento = "";
				    		try {
				        		if(eElement.getElementsByTagName("datiVersamento") != null) {
				        			codiceContestoPagamento =((Element)eElement.getElementsByTagName("datiVersamento").item(0)).getElementsByTagName("codiceContestoPagamento").item(0).getTextContent();
			        				identificativoCarrello = codiceContestoPagamento;
			        				break;
				        		}
				    		}
				    		catch (Exception e) {
				    			System.out.println("codiceContestoPagamento non trovato nella RPT [" + i + "]: " + e.getMessage());
				    			throw new Exception("codiceContestoPagamento non trovato nella RPT [" + i + "]", e);
							}
		
			        	}
			        }
				}
	        }
			//fine LP PG210130 Step04
	        //NodeList rptNodeList = (NodeList)(xpath.evaluate("//*[local-name()='rpt']", doc, XPathConstants.NODESET));
	        for(int i = 0; i < rptNodeList.getLength(); i++) {
	        	Node rptNode = rptNodeList.item(i);
	        	
	        	if (rptNode.getNodeType() == Node.ELEMENT_NODE) {
	        		Element eElement = (Element) rptNode;
	        		
		    		String identificativoUnivocoVersamento = "";
		    		try {
		        		if(eElement.getElementsByTagName("datiVersamento") != null) {
		        			identificativoUnivocoVersamento =((Element)eElement.getElementsByTagName("datiVersamento").item(0)).getElementsByTagName("identificativoUnivocoVersamento").item(0).getTextContent();
		        		}
		    		}
		    		catch (Exception e) {
		    			System.out.println("identificativoUnivocoVersamento non trovato nella RPT [" + i + "]: " + e.getMessage());
		    			throw new Exception("identificativoUnivocoVersamento non trovato nella RPT [" + i + "]", e);
					}
		    		String codiceContestoPagamento = "";
		    		try {
		        		if(eElement.getElementsByTagName("datiVersamento") != null) {
		        			codiceContestoPagamento =((Element)eElement.getElementsByTagName("datiVersamento").item(0)).getElementsByTagName("codiceContestoPagamento").item(0).getTextContent();
		        		}
		    		}
		    		catch (Exception e) {
		    			System.out.println("codiceContestoPagamento non trovato nella RPT [" + i + "]: " + e.getMessage());
		    			throw new Exception("codiceContestoPagamento non trovato nella RPT [" + i + "]", e);
					}
		    		
		    		identificativoDominio = "";
		    		try {
		        		if(eElement.getElementsByTagName("dominio") != null) {
		        			identificativoDominio =((Element)eElement.getElementsByTagName("dominio").item(0)).getElementsByTagName("identificativoDominio").item(0).getTextContent();
		        		}
		    		}
		    		catch (Exception e) {
		    			System.out.println("identificativoDominio non trovato nella RPT [" + i + "]: " + e.getMessage());
		    			throw new Exception("identificativoDominio non trovato nella RPT [" + i + "]", e);
					}
		    		
		    		
		    		
		       		byte[] rptByte = EasybridgeHelper.getNodeString(rptNode).getBytes("UTF-8");
		    		
		    		String tipoFirma = ""; //Parametro Deprecato
		    		
		    		//---TUTTI I DATI RELATIVI AD UN ELEMENTO LISTA RPT (quindi ad UNA RPT) arrivano dall'XML come parametro di input
		    		TipoElementoListaRPT elementoListaRPT = new TipoElementoListaRPT(identificativoDominio, identificativoUnivocoVersamento, codiceContestoPagamento, tipoFirma, rptByte);
		    		listaRPTs.add(elementoListaRPT);
	        	}//Fine ciclo nodi RPT
	        }
			//-----------------------------------------------------------------------------
			
	        if (propertiesTree().getProperty(this.cuteCute + ".nodospc.identificativoStazioneIntermediarioPA."+identificativoDominio)!=null) {
	        	this.identificativoStazioneIntermediarioPA = propertiesTree().getProperty(this.cuteCute + ".nodospc.identificativoStazioneIntermediarioPA."+identificativoDominio);
	        }
	        if (propertiesTree().getProperty(this.cuteCute + ".nodospc.passwordPsp."+identificativoDominio)!=null) {
	        	this.passwordPsp = propertiesTree().getProperty(this.cuteCute + ".nodospc.passwordPsp."+identificativoDominio);
	        }

	        
			
	        IntestazioneCarrelloPPT header = new IntestazioneCarrelloPPT(identificativoIntermediarioPA, identificativoStazioneIntermediarioPA, identificativoCarrello, actor, mustUnderstand);
	        
			NodoInviaCarrelloRPT bodyrichiesta = new NodoInviaCarrelloRPT(passwordPsp, identificativoPSP, identificativoIntermediarioPSP , identificativoCanale, listaRPTs.toArray(new TipoElementoListaRPT[listaRPTs.size()]));
	    	//inizio LP PG210130 Step04
	        if(this.multiBeneficiario) {
	        	bodyrichiesta.setMultiBeneficiario(true);
	        }
			StringWriter sw = new StringWriter();
			JAXB.marshal(header, sw);
			String xmlString = sw.toString();
			System.out.println("IntestazioneCarrelloPPT =\r\n" + xmlString);
			sw = new StringWriter();
			JAXB.marshal(bodyrichiesta, sw);
			xmlString = sw.toString();
			System.out.println("NodoInviaCarrelloRPT =\r\n" + xmlString);
	    	//fine LP PG210130 Step04
			gov.telematici.pagamenti.ws.NodoInviaCarrelloRPTRisposta nodoInviaCarrelloRPTRisposta = pagamentiTelematiciRPT.nodoInviaCarrelloRPT(bodyrichiesta, header);
			System.out.println("ESITO nodoInviaCarrelloRPT = " + nodoInviaCarrelloRPTRisposta.getEsitoComplessivoOperazione());
			//inizio LP PG1900XX_001
			//if(nodoInviaCarrelloRPTRisposta.getUrl() != null)
			//  System.out.println("URL Redirect = " + nodoInviaCarrelloRPTRisposta.getUrl());
			//fine LP PG1900XX_001
			
			if (nodoInviaCarrelloRPTRisposta.getEsitoComplessivoOperazione().equalsIgnoreCase("KO")){
				
				String err_msg = "";
				
				if(nodoInviaCarrelloRPTRisposta.getFault() != null) {
					err_msg = "Fault code: " + nodoInviaCarrelloRPTRisposta.getFault().getFaultCode() + " Fault String: " + nodoInviaCarrelloRPTRisposta.getFault().getFaultString() + "Fault Description: " + nodoInviaCarrelloRPTRisposta.getFault().getDescription();
					System.out.println(err_msg);
				}
				
				//inizio LP PG190220
				//if(nodoInviaCarrelloRPTRisposta.getListaErroriRPT().length > 0) {
				if(nodoInviaCarrelloRPTRisposta.getListaErroriRPT() != null && nodoInviaCarrelloRPTRisposta.getListaErroriRPT().length > 0) {
				//fine LP PG190220
					System.out.println("Lista Errori RPT: #" + nodoInviaCarrelloRPTRisposta.getListaErroriRPT().length);
					for (FaultBean faultBean : nodoInviaCarrelloRPTRisposta.getListaErroriRPT()) {
						err_msg = "Fault code: " + faultBean.getFaultCode() + " Fault String: " + faultBean.getFaultString() + "Fault Description: " + faultBean.getDescription();
						System.out.println(err_msg);
					}
				}
				
			//inizio LP PG1900XX_001
			//}
			} else if(nodoInviaCarrelloRPTRisposta.getUrl() != null) {
				System.out.println("URL Redirect = " + nodoInviaCarrelloRPTRisposta.getUrl());
				
				if(ebDao != null) {
					String urlRedirect = nodoInviaCarrelloRPTRisposta.getUrl();
					String match = "?idSession=";
					int pIdSession = urlRedirect.indexOf(match) + match.length();
					String idSessioneRedirect = urlRedirect.substring(pIdSession);
					System.out.println("inviaCarrelloRPT idSession: " + idSessioneRedirect);
				 	regIdSession = new RegistroIdSession();
				 	regIdSession.setCuteCute(cuteCute);
					regIdSession.setIdSessione(idSessioneRedirect);
				}
			}
			//fine LP PG1900XX_001
			
			InviaCarrelloRTPResponse easybridgeInviaCarrelloRTPResponse = EasybridgeHelper.bindingInviaCarrelloRTPResponseFromNodoCarrelloRPTResponse(nodoInviaCarrelloRPTRisposta);

			//inizio LP PG1900XX_001
	    	if(ebDao != null && regIdSession != null) {
				try {
					if(!ebDao.insertRegistroIdSession(regIdSession)) {
						String messOut = "Operation inviaCarrelloRPT not execute save EYRPTTB";
						System.out.println(messOut);
					} else {
						System.out.println("inviaCarrelloRPT execute save EYRPTTB (idSession: " + regIdSession.getIdSessione() + ")");
					}
				} catch (Exception e) {
					e.printStackTrace();
		        	System.out.println("In save EYRPTTB inviaCarrelloRPT: " + e.getMessage());
				}
	    	}
			//fine LP PG1900XX_001

			return easybridgeInviaCarrelloRTPResponse;
        }
        //inizio LP PG1900XX_001
        catch (DataBaseException e) {
    	 	String code = DataBaseException.code;
    	 	String description = DataBaseException.description;
			if (!e.getMessage().equals("")) {
				description += " (" + e.getMessage() + ")";
			}
			System.out.println("inviaCarrelloRPT: " + description);

			InviaCarrelloRTPResponse easybridgeInviaCarrelloRTPResponse = new InviaCarrelloRTPResponse();

    		easybridgeInviaCarrelloRTPResponse.setEsitoComplessivoOperazione("KO");
    		
   			com.esed.easybridge.webservice.dati.FaultBean fault = new com.esed.easybridge.webservice.dati.FaultBean();
   			fault.setId(code);
   			fault.setFaultCode(code);
   			fault.setFaultString("Errore nella chiamata easybridge.inviaCarrelloRTP: " + description);
   			fault.setDescription(e.getMessage());

        	System.out.println(fault.getFaultString() + " - " + fault.getDescription());

   			easybridgeInviaCarrelloRTPResponse.setFault(fault );					
			return easybridgeInviaCarrelloRTPResponse;			
        }
        //fine LP PG1900XX_001
        catch (Exception e) {
        	e.printStackTrace();

        	InviaCarrelloRTPResponse easybridgeInviaCarrelloRTPResponse = new InviaCarrelloRTPResponse();

    		easybridgeInviaCarrelloRTPResponse.setEsitoComplessivoOperazione("KO");
    		
   			com.esed.easybridge.webservice.dati.FaultBean fault = new com.esed.easybridge.webservice.dati.FaultBean();
   			fault.setId("99");
   			fault.setFaultCode("99");
   			fault.setFaultString("Errore nella chiamata easybridge.inviaCarrelloRTP: " + e.getMessage());
   			fault.setDescription(e.getMessage());

        	System.out.println(fault.getFaultString() + " - " + fault.getDescription());

   			easybridgeInviaCarrelloRTPResponse.setFault(fault );					
			return easybridgeInviaCarrelloRTPResponse;			
		}
	    //inizio LP 20190611 BUG connection open
	    finally {
			if(ebDao != null) {
				ebDao.CloseConnection();
			}
	    }
	    //fine LP 20190611
    }
}
