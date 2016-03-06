package npetzall.netty.codec.http.soap;

import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import npetzall.netty.utils.Resources;
import npetzall.sec.ClientType;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpSOAPStreamMessageTest {

    private static final JAXBContext clientJAXBContext;
    private static final JAXBContext searchJAXBContext;

    static {

        JAXBContext clientJAXBContextTmp;
        try {
            clientJAXBContextTmp = JAXBContext.newInstance("npetzall.sec");
        } catch (JAXBException e) {
            clientJAXBContextTmp = null;
        }
        clientJAXBContext = clientJAXBContextTmp;

        JAXBContext searchJAXBContextTmp;
        try {
            searchJAXBContextTmp = JAXBContext.newInstance("npetzall.search");
        } catch (JAXBException e) {
            searchJAXBContextTmp = null;
        }
        searchJAXBContext = searchJAXBContextTmp;
    }

    @Test
    public void create() throws IOException {
        DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, "/simpleService", Resources.MessageAsByteBuf("SoapPricing"));
        defaultFullHttpRequest.headers().add("x-custom-action", "search");
        HttpSOAPStreamsMessage httpSOAPStreamsMessage = new HttpSOAPStreamsMessage(defaultFullHttpRequest);
        assertThat(httpSOAPStreamsMessage).isNotNull();
    }

    @Test
    public void hasHttpHeaders() throws IOException {
        DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, "/simpleService", Resources.MessageAsByteBuf("SoapPricing"));
        defaultFullHttpRequest.headers().add("x-custom-action", "search");
        HttpSOAPStreamsMessage httpSOAPStreamsMessage = new HttpSOAPStreamsMessage(defaultFullHttpRequest);
        assertThat(httpSOAPStreamsMessage.getHeader("x-custom-action")).isEqualTo("search");
    }

    @Test
    public void canReadHeaderUsingUnmarshaller() throws JAXBException, IOException {
        DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, "/simpleService", Resources.MessageAsByteBuf("SoapPricing"));
        defaultFullHttpRequest.headers().add("x-custom-action", "search");
        HttpSOAPStreamsMessage httpSOAPStreamsMessage = new HttpSOAPStreamsMessage(defaultFullHttpRequest);

        assertThat(httpSOAPStreamsMessage.hasSOAPHead()).isTrue();

        ClientType client = httpSOAPStreamsMessage.getSOAPHeaderAsObject(clientJAXBContext.createUnmarshaller());

        assertThat(client.getClientCode()).isEqualTo("ABC123");
        assertThat(client.getClientSecret()).isEqualTo("321CBA");
        assertThat(client.getSite()).isEqualTo("http://cheapstu.ff");
    }
}
