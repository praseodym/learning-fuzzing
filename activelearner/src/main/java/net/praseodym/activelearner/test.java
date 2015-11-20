package net.praseodym.activelearner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.CharBuffer;

/**
 * Created by mark on 11/19/15.
 */
public class test {
    public static void main(String[] args) throws IOException {
//        ProcessBuilder pb = new ProcessBuilder("/home/mark/target/simpletarget");
        ProcessBuilder pb = new ProcessBuilder("/bin/cat");
        String output;

        Process p = pb.start();
        OutputStream stdin = p.getOutputStream();
        BufferedReader stdout =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        stdin.write("1".getBytes());
        stdin.write(System.lineSeparator().getBytes());
        stdin.flush();
//        CharBuffer buffer = CharBuffer.allocate(100);
//        stdout.read(buffer);
//        buffer.flip();
//        output = buffer.toString();
//        System.out.println(output);
        System.out.println(stdout.readLine());
    }

}