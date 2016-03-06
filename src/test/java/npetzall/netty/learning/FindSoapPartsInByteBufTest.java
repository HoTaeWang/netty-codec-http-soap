package npetzall.netty.learning;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import npetzall.netty.utils.Resources;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;

import static javax.xml.stream.XMLInputFactory.newFactory;
import static org.assertj.core.api.Assertions.assertThat;

public class FindSoapPartsInByteBufTest {

    private static String SOAP_NS_URI = "http://schemas.xmlsoap.org/soap/envelope/";
    private static QName SOAP_ENVELOPE = new QName(SOAP_NS_URI,"Envelope");
    private static QName SOAP_HEADER = new QName(SOAP_NS_URI, "Header");
    private static QName SOAP_BODY = new QName(SOAP_NS_URI, "Body");

    @Test
    public void makeSureSOAPEnvelopeAndGetPreFix() throws IOException, XMLStreamException {
        ByteBuf byteBuf = Resources.MessageAsByteBuf("SoapPricing");
        ByteBufInputStream byteBufInputStream = new ByteBufInputStream(byteBuf.duplicate());
        XMLStreamReader xmlStreamReader = null;
        int headerStart = -1;
        int headerEnd = -1;
        int bodyStart = -1;
        int bodyEnd = -1;
        try {
            xmlStreamReader = newFactory().createXMLStreamReader(new ByteBufInputStream(byteBuf.duplicate()));
            xmlStreamReader.next();
            if (!xmlStreamReader.isStartElement() || !xmlStreamReader.getName().equals(SOAP_ENVELOPE)) {
                throw new RuntimeException("Is not a soap envelope!");
            }
            while(xmlStreamReader.hasNext()) {
                xmlStreamReader.next();
                if(xmlStreamReader.isStartElement()) {
                    if (xmlStreamReader.getName().equals(SOAP_HEADER)) {
                        headerStart = xmlStreamReader.getLocation().getCharacterOffset();
                    } else if (xmlStreamReader.getName().equals(SOAP_BODY)) {
                        bodyStart = xmlStreamReader.getLocation().getCharacterOffset();
                    }
                } else if (xmlStreamReader.isEndElement()) {
                    if (xmlStreamReader.getName().equals(SOAP_HEADER)) {
                        headerEnd = xmlStreamReader.getLocation().getCharacterOffset();
                        headerEnd -= getSize(xmlStreamReader.getName());
                    } else if (xmlStreamReader.getName().equals(SOAP_BODY)) {
                        bodyEnd = xmlStreamReader.getLocation().getCharacterOffset();
                        bodyEnd -= getSize(xmlStreamReader.getName());
                    }
                }
            }
            System.err.println("Header range: " + headerStart + " to " + headerEnd);
            System.err.println("Body range: " + bodyStart + " to " + bodyEnd);
        } catch (XMLStreamException e) {
            throw new RuntimeException("Is not a soap envelope!", e);
        } finally {
            try {
                xmlStreamReader.close();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
            try {
                byteBufInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int getSize(QName name) {
        int additional = "</>".length();
        if (name.getPrefix().length() > 0) {
            additional += 1;
        }
        return name.getLocalPart().length() + name.getPrefix().length() + additional;
    }
}
