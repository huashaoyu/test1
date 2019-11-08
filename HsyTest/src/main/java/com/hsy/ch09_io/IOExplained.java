package com.hsy.ch09_io;

import com.google.common.io.ByteStreams;
import org.junit.Test;

import java.io.*;

/**
 * @author huashaoyu
 * @title: IOExplained
 * @projectName GuavaTest
 * @description: TODO
 * @date 2019/10/24 14:35
 */
public class IOExplained {
    
    @Test
    public void byteStreamsAndCharStreams() throws IOException {
        InputStream is = new FileInputStream("src/main/java/com/hsy/ch09_io/test.txt");
        OutputStream os = new FileOutputStream("src/main/java/com/hsy/ch09_io/new.txt");
        byte[] bytes = ByteStreams.toByteArray(is);
        System.out.println(new String(bytes));
        is = new FileInputStream("src/main/java/com/hsy/ch09_io/test.txt");
        long copy = ByteStreams.copy(is, os);
        System.out.println(copy);

        is = new FileInputStream("src/main/java/com/hsy/ch09_io/test.txt");
        byte[] b = new byte[10];
        ByteStreams.readFully(is,b);

        is = new FileInputStream("src/main/java/com/hsy/ch09_io/test.txt");
        ByteStreams.skipFully(is,5);
        System.out.println(new String(ByteStreams.toByteArray(is)));
        os = ByteStreams.nullOutputStream();
        is.close();
        os.close();
    }
    
    
}
