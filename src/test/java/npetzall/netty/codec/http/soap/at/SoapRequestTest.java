package npetzall.netty.codec.http.soap.at;

import io.netty.channel.embedded.EmbeddedChannel;
import npetzall.netty.codec.http.soap.ServerChannelInitializer;
import npetzall.netty.codec.http.soap.HttpSOAPStreamsMessageCodec;

public class SoapRequestTest {

    public void SoapMessageArriversAfterSoapDecoder() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new ServerChannelInitializer());
        embeddedChannel.pipeline().addLast(new HttpSOAPStreamsMessageCodec());

    }
}
