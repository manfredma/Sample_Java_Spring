package manfred.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFile {
    static public void main(String args[]) throws Exception {
        // System.getProperties().forEach((a, b) -> System.out.println(a + "=" + b));
        // System.getenv().forEach((a, b) -> System.out.println(a + "=" + b));
        if (args.length < 2) {
            System.err.println("Usage: java CopyFile infile outfile");
            System.exit(1);
        }

        String infile = args[0];
        String outfile = args[1];

        FileInputStream fin = new FileInputStream(infile);
        FileOutputStream fout = new FileOutputStream(outfile);

        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Long begin = System.currentTimeMillis();
        while (true) {
            buffer.clear();
            int r = fcin.read(buffer);
            if (r == -1) {
                break;
            }
            buffer.flip();
            fcout.write(buffer);
        }
        System.out.println("Cost " + (System.currentTimeMillis() - begin) + " ms!");
    }
}
