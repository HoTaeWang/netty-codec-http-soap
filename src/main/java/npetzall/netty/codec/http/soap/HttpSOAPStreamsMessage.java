package npetzall.netty.codec.http.soap;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

public class HttpSOAPStreamsMessage {

    private final FullHttpRequest request;
    private final InputStream headerStream = null;
    private final InputStream bodyStream = null;

    public HttpSOAPStreamsMessage(FullHttpRequest request) {
        this.request = request;
    }

    public String getHeader(String headerName) {
        return request.headers().get(headerName);
    }

    public String getTrailingHeader(String headerName) {
        return request.trailingHeaders().get(headerName);
    }

    public boolean hasSOAPHead() {
        return true;
    }

    public <T> T getSOAPHeaderAsObject(Unmarshaller unmarshaller) throws JAXBException {
        return (T) unmarshaller.unmarshal(headerStream);
    }

}
