package nioTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by 10564 on 2017-11-09.
 */
public class TransferToTest {
    private String srcPath;
    private String dstPath;

    public TransferToTest() {
        this.srcPath = CommonParameter.srcPath;
        this.dstPath = CommonParameter.dstPath;
    }

    public static void main(String[] args) {
        TransferToTest transferToTest = new TransferToTest();
        if (args.length > 0){
            transferToTest.srcPath = args[0];
        }

        FileChannel in = null, out = null;
        try {
            in = new FileInputStream(transferToTest.srcPath).getChannel();
            out = new FileOutputStream(transferToTest.dstPath).getChannel();
            long position = 0;
            long length, count;
            long start = System.currentTimeMillis();

            //一次传输1.2G
//            length = in.transferTo(0, 1258291200, out);
//            position += length;
            while (position < in.size()){
                count = CommonParameter.count < (in.size()-position) ? CommonParameter.count : (in.size()-position);
                length = in.transferTo(position, count, out);
                position += length;
            }
            long end = System.currentTimeMillis();
            System.out.println("TransferTo usage time: " + (end - start) + "ms, transfer size: " + position);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
