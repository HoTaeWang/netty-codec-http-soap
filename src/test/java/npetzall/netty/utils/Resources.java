package npetzall.netty.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Resources {

    private Resources() {}

    public static ByteBuf MessageasByteBuf(String messageName) throws IOException {
        return Unpooled.wrappedBuffer(readResourceToByteArray("/messages/" + messageName + ".xml"));
    }

    private static byte[] readResourceToByteArray(String resourcePath) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(Resources.class.getResourceAsStream(resourcePath))) {
            byte[] buff = new byte[2048];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buff)) != -1) {
                byteArrayOutputStream.write(buff, 0, bytesRead);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}
