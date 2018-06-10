package coding.corps.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectableChannel;

/**
 * Created by liuxin on 2018/6/4 23:06:00.
 */
public class FileChannelDemo {
    public static void main(String[] args) {
        try {
            transferTo();
            transferFrom();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void transferTo() throws IOException {
        // If this file is not exist, the randomAccessFile will create this file.
        RandomAccessFile fromFile = new RandomAccessFile("D://files//fromFile1.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile("D://files//toFile1.txt", "rw");
        FileChannel toChannel = toFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        // This count is mean the size of channel rather than size of file
        fromChannel.transferTo(position, count, toChannel);
    }

    private static void transferFrom() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("D://files//fromFile2.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile("D://files//toFile2.txt", "rw");
        FileChannel toChannel = toFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        //There something different with the demo of "ifeve.com", the channel's position is changed from 3 to 1.
        toChannel.transferFrom(fromChannel, position, count);
    }
}
