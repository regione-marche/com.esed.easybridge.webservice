package com.esed.easybridge.webservice.helper;

import gov.telematici.pagamenti.ws.FaultBean;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;

import com.esed.easybridge.webservice.dati.InviaCarrelloRTPResponse;

public class EasybridgeHelper {

	public static final String DBSCHEMACODSOCIETA = "dbSchemaCodSocieta";

	public static InviaCarrelloRTPResponse bindingInviaCarrelloRTPResponseFromNodoCarrelloRPTResponse(gov.telematici.pagamenti.ws.NodoInviaCarrelloRPTRisposta nodoInviaCarrelloRPTRisposta) {
		InviaCarrelloRTPResponse easybridgeInviaCarrelloRTPResponse = new InviaCarrelloRTPResponse();

		easybridgeInviaCarrelloRTPResponse.setEsitoComplessivoOperazione(nodoInviaCarrelloRPTRisposta.getEsitoComplessivoOperazione());
		easybridgeInviaCarrelloRTPResponse.setUrl(nodoInviaCarrelloRPTRisposta.getUrl());
		
		if(nodoInviaCarrelloRPTRisposta.getFault() != null){
			easybridgeInviaCarrelloRTPResponse.setFault(bindingFaultBeanFromNodoFaultBean(nodoInviaCarrelloRPTRisposta.getFault()));					
		}
		
		if(nodoInviaCarrelloRPTRisposta.getListaErroriRPT() != null){
			List<com.esed.easybridge.webservice.dati.FaultBean> listaErroriRPT = new ArrayList<com.esed.easybridge.webservice.dati.FaultBean>();
			for (FaultBean erroreRPT : nodoInviaCarrelloRPTRisposta.getListaErroriRPT()) {
				listaErroriRPT.add(bindingFaultBeanFromNodoFaultBean(erroreRPT));
			}
			easybridgeInviaCarrelloRTPResponse.setListaErroriRPT(listaErroriRPT.toArray(new com.esed.easybridge.webservice.dati.FaultBean[listaErroriRPT.size()]));
		}

		return easybridgeInviaCarrelloRTPResponse;
	}
	
	private static com.esed.easybridge.webservice.dati.FaultBean bindingFaultBeanFromNodoFaultBean(FaultBean nodoFaultBean) {
		com.esed.easybridge.webservice.dati.FaultBean fault = new com.esed.easybridge.webservice.dati.FaultBean();
		fault.setId(nodoFaultBean.getId());
		fault.setFaultCode(nodoFaultBean.getFaultCode());
		fault.setFaultString(nodoFaultBean.getFaultString());
		fault.setDescription(nodoFaultBean.getDescription());
		fault.setOriginalFaultCode(nodoFaultBean.getOriginalFaultCode());
		fault.setOriginalFaultString(nodoFaultBean.getOriginalFaultString());
		fault.setOriginalDescription(nodoFaultBean.getOriginalDescription());
		
		return fault;
	}
	
	
	public static String getNodeString(Node node) {
	    try {
	        StringWriter writer = new StringWriter();
	        Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        transformer.transform(new DOMSource(node), new StreamResult(writer));
	        String output = writer.toString();
	        return output.substring(output.indexOf("?>") + 2);//remove <?xml version="1.0" encoding="UTF-8"?>
	    } catch (TransformerException e) {
	        e.printStackTrace();
	    }
	    return node.getTextContent();
	}	

}
