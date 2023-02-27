package com.esed.easybridge.webservice.handler;

import java.util.Iterator;
import java.util.Properties;
import javax.naming.Context;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.rpc.server.ServletEndpointContext;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;

import com.seda.commons.properties.tree.PropertiesTree;
import com.seda.j2ee5.webservice.spi.JaxRpc10WebServiceHandler;
import com.esed.easybridge.webservice.config.PrintStrings;
import com.esed.easybridge.webservice.config.PropKeys;

public abstract class WebServiceHandler extends JaxRpc10WebServiceHandler {

	public static PropertiesTree configuration;
	
	protected String loggerContextName = PrintStrings.LOGGER_CONTEXT_NAME.format();
	protected String treeContextName = PrintStrings.TREE_CONTEXT_NAME.format();
	protected Properties env;
	protected String dbSchemaCodSocieta; 

	@Override
	public void init(Object endPointContext) throws ServiceException {
		super.init(endPointContext);
		logger(loggerContextName);
		
    	try {
    		propertiesTree(treeContextName);
    		configuration = propertiesTree();
    	} catch (Exception e) {
			e.printStackTrace();
			info("errore = "+ e.getMessage());
		}
    
		if (configuration != null){
			info("configurazione del file properties trovata");

			setDbSchemaCodSocieta(endPointContext);
			
	    	env = new Properties();
	    	env.put(Context.INITIAL_CONTEXT_FACTORY, propertiesTree().getProperty(PropKeys.JINDI_CONTEXT.format(PropKeys.DEFAULT_NODE.format())));
	    	env.put(Context.PROVIDER_URL, propertiesTree().getProperty(PropKeys.JINDI_PROVIDER.format(PropKeys.DEFAULT_NODE.format())));
	
		}
    	
	}
	
	@SuppressWarnings("unchecked")
	private void setDbSchemaCodSocieta (Object endPointContext) {
		ServletEndpointContext ctx=null;
		
		if (javax.xml.rpc.server.ServletEndpointContext.class.isInstance(endPointContext))
			ctx = (ServletEndpointContext) endPointContext;

		if (ctx != null) {
			SOAPMessageContext mc = (SOAPMessageContext)ctx.getMessageContext();
			// process SOAP header as shown in the message handler
			try {
				SOAPHeader header = mc.getMessage().getSOAPPart().getEnvelope().getHeader();

				Iterator headers = header.examineAllHeaderElements(); //header.extractHeaderElements("http://schemas.xmlsoap.org/soap/actor/next");
				while (headers.hasNext()) {
					SOAPHeaderElement he = (SOAPHeaderElement)headers.next();
					if(he.getNodeName().equals("dbSchemaCodSocieta"))
						dbSchemaCodSocieta = new String(he.getValue());
				} 
			} catch (SOAPException e) {
				e.printStackTrace();
			}
		}
	}
}