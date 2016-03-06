package npetzall.netty.learning;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import npetzall.netty.utils.Resources;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.util.Arrays;

import static javax.xml.stream.XMLInputFactory.newFactory;
import static org.assertj.core.api.Assertions.assertThat;

public class FindSoapPartsInByteBufTest {

    private static String SOAP_ENVELOPE_NS_URI = "http://schemas.xmlsoap.org/soap/envelope/";
    private static QName SOAP_ENVELOPE = new QName(SOAP_ENVELOPE_NS_URI,"Envelope");

    @Test
    public void makeSureSOAPEnvelopeAndGetPreFix() throws IOException, XMLStreamException {
        ByteBuf byteBuf = Resources.MessageasByteBuf("SoapPricing");
        String prefix = getPrefix(byteBuf);
        assertThat(prefix).isEqualTo("soapenv");
        assertThat(byteBuf.readerIndex()).isEqualTo(0);
    }

    private String getPrefix(ByteBuf byteBuf) {
        ByteBufInputStream byteBufInputStream = new ByteBufInputStream(byteBuf.duplicate());
        XMLStreamReader xmlStreamReader = null;
        try {
            xmlStreamReader = newFactory().createXMLStreamReader(new ByteBufInputStream(byteBuf.duplicate()));
            xmlStreamReader.next();
            if (xmlStreamReader.getName().equals(SOAP_ENVELOPE)){
                return xmlStreamReader.getName().getPrefix();
            }
            throw new RuntimeException("Is not a soap envelope!");
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

    @Test
    public void findSOAPHeaderRange() throws IOException {
        ByteBuf byteBuf = Resources.MessageasByteBuf("SoapPricing");
        String prefix = getPrefix(byteBuf);

    }

    private void findEndOfTag(String tagName) {

    }

    private int findIndex(byte[] bytes, byte find) {
        return findIndex(bytes, find, 0);
    }

    private int findIndex(byte[] bytes, byte find, int start) {
        for (int i = start; i < bytes.length; i ++) {
            if (bytes[i] == find) {
                return i;
            }
        }
        return -1;
    }
}
