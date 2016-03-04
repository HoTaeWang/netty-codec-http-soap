package npetzall.netty.codec.http.soap;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;

public class HttpSOAPStreamsMessageCodec extends MessageToMessageCodec<FullHttpRequest, HttpSOAPStreamsMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, HttpSOAPStreamsMessage msg, List<Object> out) throws Exception {

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, FullHttpRequest msg, List<Object> out) throws Exception {

    }
}
