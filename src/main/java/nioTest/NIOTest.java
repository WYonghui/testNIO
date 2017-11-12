package nioTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by 10564 on 2017-11-09.
 * NIO实现文件拷贝
 */
public class NIOTest {
    private String srcPath;
    private String dstPath;

    public NIOTest() {
        this.srcPath = CommonParameter.srcPath;
        this.dstPath = CommonParameter.dstPath;
    }

    public static void main(String[] args) {
        NIOTest nioTest = new NIOTest();

        if (args.length > 0){
            nioTest.srcPath = args[0];
        }

        FileChannel srcChannel = null, dstChannel = null;

        try {
            srcChannel = new FileInputStream(nioTest.srcPath).getChannel();
            dstChannel = new FileOutputStream(nioTest.dstPath).getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(CommonParameter.count);
            long length, size = 0;
            long start = System.currentTimeMillis();
            while ((length = srcChannel.read(buffer)) != -1){
                buffer.flip();
                dstChannel.write(buffer);
                size += length;
                buffer.clear();
            }

            long end = System.currentTimeMillis();
            System.out.println("NIO usage time: " + (end - start) + "ms, transfer size: " + size);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                srcChannel.close();
                dstChannel.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
