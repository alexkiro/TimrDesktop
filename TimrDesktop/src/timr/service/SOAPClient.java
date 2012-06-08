package timr.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.soap.*;

/**
 * Wrapper for javax.xml.soap
 * Usage example:
 * <pre>
 * String response = new SOAPClient("http://ms.info.uaic.ro/webservice/TimrService.asmx")
		.addCallName("GetTimetableForBachelorYear","http://tempuri.org/")
		.addParameter("year", year)
		.addParameter("halfYear", halfYear).call();
 * </pre>
 */
public class SOAPClient {
	
	
	private SOAPConnection connection;
	private SOAPFactory factory;
	private SOAPMessage message;
	private SOAPHeader header;
	private SOAPBody body;
	private SOAPPart part;
	private SOAPBodyElement callName = null;
	private URL endpoint;
	
	/**
	 * Creates a SOAP connection and prepares a message
	 * @throws UnsupportedOperationException
	 * @throws SOAPException
	 */
	public SOAPClient() throws UnsupportedOperationException, SOAPException{
			 connection = SOAPConnectionFactory.newInstance().createConnection();
			 factory = SOAPFactory.newInstance();
			 message = MessageFactory.newInstance().createMessage();
			 
			 header = message.getSOAPHeader();
			 body = message.getSOAPBody();
			 
			 part = message.getSOAPPart();

			 part.setMimeHeader("Content-Type","text/xml");
			 header.detachNode();
	}
	
	/**
	 * Creates a SOAP connection and prepares a message and sets the end point
	 * to the given url
	 * @param url
	 * @throws UnsupportedOperationException
	 * @throws SOAPException
	 */
	public SOAPClient(URL url) throws UnsupportedOperationException, SOAPException{
		this();
		endpoint = url;
	}
	
	/**
	 * Creates a SOAP connection and prepares a message and sets the end point
	 * to the given url
	 * @param url
	 * @throws UnsupportedOperationException
	 * @throws MalformedURLException
	 * @throws SOAPException
	 */
	public SOAPClient(String url) throws UnsupportedOperationException, MalformedURLException, SOAPException{
		this(new URL(url));	
	}
	
	/**
	 * Defines the function to call from the service
	 * @param name	- name of the function
	 * @param namespace - namespace of the function
	 * @param xlmns	- the xlmns field
	 * @return
	 * @throws SOAPException
	 */
	public SOAPClient addCallName(String name,String namespace,String xlmns) throws SOAPException{
		Name bodyName = factory.createName(name, namespace, xlmns);
		callName = body.addBodyElement(bodyName);
		return this;
	}
	
	/**
	 * Defines the function to call from the service but with no namespace
	 * @param name	- name of the function
	 * @param xlmns	- the xlmns field
	 * @return
	 * @throws SOAPException
	 */
	public SOAPClient addCallName(String name,String xlmns) throws SOAPException{
		Name bodyName = factory.createName(name, "", xlmns);
		callName = body.addBodyElement(bodyName);
		return this;
	}
	
	/**
	 * Adds a parameter to the function call (request)
	 * @param tag - the name of the tag
	 * @param text - the value of the parameter
	 * @return
	 * @throws SOAPException
	 */
	public SOAPClient addParameter(String tag, String text) throws SOAPException{
		callName.addChildElement(factory.createName(tag)).addTextNode(text);
		return this;
	}
	
	public URL getEndpoint() {
		return endpoint;
	}

	public SOAPClient setEndpoint(URL endpoint) {
		this.endpoint = endpoint;
		return this;
	}
	
	public SOAPClient setEndpoint(String endpoint) throws MalformedURLException {
		this.endpoint = new URL(endpoint);
		return this;
	}
	
	/**
	 * Calls the previously defined function with the given parameters.
	 * @return - the soap body or null if no call name has been defined
	 * @throws SOAPException
	 * @throws IOException
	 */
	public String call() throws SOAPException, IOException{
		if (callName!=null){
			SOAPMessage response = connection.call(message, endpoint);
			
			String str = response.getSOAPBody().getTextContent();
	
			connection.close();
			return str;
		} else {
			return null;
		}
	}

	



}