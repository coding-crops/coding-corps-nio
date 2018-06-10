package coding.corps.nio.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2018/6/3.
 */
public class BufferDemo {
    public static void main(String[] args) {
        try {
            RandomAccessFile aFile = new RandomAccessFile("D://nio-data.txt", "rw");
            FileChannel inChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(48);
            int bytesRead = inChannel.read(buf);
            while(bytesRead != -1) {
                buf.flip();
                while(buf.hasRemaining()) {
                    System.out.println((char)buf.get());
                }
                buf.clear();
//                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
