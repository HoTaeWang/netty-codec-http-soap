
package npetzall.sec;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the npetzall.sec package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Client_QNAME = new QName("http://npetzall/sec", "Client");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: npetzall.sec
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ClientType }
     * 
     */
    public ClientType createClientType() {
        return new ClientType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClientType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://npetzall/sec", name = "Client")
    public JAXBElement<ClientType> createClient(ClientType value) {
        return new JAXBElement<ClientType>(_Client_QNAME, ClientType.class, null, value);
    }

}
