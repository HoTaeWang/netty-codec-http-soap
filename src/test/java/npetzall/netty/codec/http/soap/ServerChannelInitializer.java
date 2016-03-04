package npetzall.netty.codec.http.soap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;

public class ServerChannelInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        channelPipeline.addLast("decompressor", new HttpContentDecompressor());
        channelPipeline.addLast("requestDecoder", new HttpRequestDecoder());
        channelPipeline.addLast("aggregator", new HttpObjectAggregator(1048576));
    }

}
