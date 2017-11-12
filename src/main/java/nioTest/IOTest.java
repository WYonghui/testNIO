package nioTest;

import java.io.*;

/**
 * Created by 10564 on 2017-11-09.
 * 传统IO实现文件拷贝
 */
public class IOTest {
    private String srcPath;
    private String dstPath;

    public IOTest() {
        this.srcPath = CommonParameter.srcPath;
        this.dstPath = CommonParameter.dstPath;
    }

    public static void main(String[] args) {
        IOTest ioTest = new IOTest();
        if (args.length > 0){
            ioTest.srcPath = args[0];
        }

        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        try {
            in = new BufferedInputStream(new FileInputStream(ioTest.srcPath));
            out = new BufferedOutputStream(new FileOutputStream(ioTest.dstPath));
            byte[] buf = new byte[CommonParameter.count];
            int length;
            long size = 0;
            long start = System.currentTimeMillis();
            while ((length = in.read(buf)) != -1){
                out.write(buf);
                size += length;
            }

            long end = System.currentTimeMillis();
            System.out.println("IO usage time: " + (end-start) + "ms, transfer size: " + size);

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                in.close();
                out.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
