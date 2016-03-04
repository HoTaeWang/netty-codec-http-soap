
package npetzall.search;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the npetzall.search package. 
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

    private final static QName _Result_QNAME = new QName("http://npetzall/search", "Result");
    private final static QName _Search_QNAME = new QName("http://npetzall/search", "Search");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: npetzall.search
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SearchType }
     * 
     */
    public SearchType createSearchType() {
        return new SearchType();
    }

    /**
     * Create an instance of {@link ResultType }
     * 
     */
    public ResultType createResultType() {
        return new ResultType();
    }

    /**
     * Create an instance of {@link ItemType }
     * 
     */
    public ItemType createItemType() {
        return new ItemType();
    }

    /**
     * Create an instance of {@link PriceRange }
     * 
     */
    public PriceRange createPriceRange() {
        return new PriceRange();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://npetzall/search", name = "Result")
    public JAXBElement<ResultType> createResult(ResultType value) {
        return new JAXBElement<ResultType>(_Result_QNAME, ResultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://npetzall/search", name = "Search")
    public JAXBElement<SearchType> createSearch(SearchType value) {
        return new JAXBElement<SearchType>(_Search_QNAME, SearchType.class, null, value);
    }

}
