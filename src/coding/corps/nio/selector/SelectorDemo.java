package coding.corps.nio.selector;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * A demo show how to use the selector of NIO
 * Created by xin.liu on 2018/6/10.
 */
public class SelectorDemo {
    public static void main(String[]args) {
        // if you want to link multi-event
        int multiSelectionKey = SelectionKey.OP_ACCEPT | SelectionKey.OP_CONNECT;
        System.out.println(multiSelectionKey);
        try {
            // 1.Set up a channel except fileChannel, because it will always blocking
            SocketChannel socketChannel = SocketChannel.open();
            // 2.Configure the blocking setting is false
            socketChannel.configureBlocking(false);
            // 3.Set up a selector
            Selector selector = Selector.open();
            // 4.Register with selector and selectionKey, and then return a SelectionKey object
            SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
            Boolean isReady = selectionKey.isReadable();
            System.out.println(isReady);
            TheObject theObjectBeforeAttach = new TheObject();
            theObjectBeforeAttach.setName("this is name");
            selectionKey.attach(theObjectBeforeAttach);
            TheObject theObjectAfterAttach = (TheObject)selectionKey.attachment();
            System.out.print(theObjectAfterAttach.toString());

            while(true) {
                int readChannels = selector.select();
                if (readChannels == 0) continue;
                Set selectSet = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectSet.iterator();
                if (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if(key.isAcceptable()) {
                        // a connection was accepted by a ServerSocketChannel.
                        System.out.println("1");
                    } else if (key.isConnectable()) {
                        // a connection was established with a remote server.
                        System.out.println("2");
                    } else if (key.isReadable()) {
                        // a channel is ready for reading
                        System.out.println("3");
                    } else if (key.isWritable()) {
                        // a channel is ready for writing
                        System.out.println("4");
                    }
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
