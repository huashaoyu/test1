package com.hsy.java8.chap3;

import java.io.*;

/**
 * @author huashaoyu
 * @title: ExecuteAround1111
 * @projectName HsyTest
 * @description: TODO
 * @date 2019/11/4 11:13
 */
public class ExecuteAround1111 {
    
    public static String processFile() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return br.readLine();
        }
    }
    
    public static String processFile(BufferedReaderProcessor p) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }
    }

    public static void main(String[] args) throws IOException {
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        String twoLine = processFile((BufferedReader br) -> br.readLine() + br.readLine());
    }
    
    @FunctionalInterface
    public interface BufferedReaderProcessor{
        String process(BufferedReader b) throws IOException;
    }
}
