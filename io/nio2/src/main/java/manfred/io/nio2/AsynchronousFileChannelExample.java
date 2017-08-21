package manfred.io.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AsynchronousFileChannelExample {

    public static void main(String[] args) throws Exception {
        new AsynchronousFileChannelExample();
    }

    public AsynchronousFileChannelExample() throws Exception {
        System.out.println(Thread.currentThread() + ": " + "Opening file channel for reading and writing");
        final AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(Paths.get("afile"),
                StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption
                        .DELETE_ON_CLOSE);

        CompletionHandler<Integer, Object> handler = new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println(Thread.currentThread() + ": " + attachment + " completed with " + result + " bytes" +
                        " written");
            }

            @Override
            public void failed(Throwable e, Object attachment) {
                if (e instanceof AsynchronousCloseException) {
                    System.out.println(Thread.currentThread() + ": " + "File was closed before " + attachment + " " +
                            "executed");
                } else {
                    System.err.println(Thread.currentThread() + ": " + attachment + " failed with:");
                    e.printStackTrace();
                }
            }
        };

        byte[] contents = "hello  ".getBytes();
        System.out.println(Thread.currentThread() + ": " + "Initiating write operation 1");
        fileChannel.write(ByteBuffer.wrap(contents), 0, "Write operation 1", handler);
        contents = "goodbye".getBytes();
        System.out.println(Thread.currentThread() + ": " + "Initiating write operation 2");
        fileChannel.write(ByteBuffer.wrap(contents), 0, "Write operation 2", handler);

        final ByteBuffer buffer = ByteBuffer.allocate(contents.length);
        System.out.println(Thread.currentThread() + ": " + "Initiating read operation");
        fileChannel.read(buffer, 0, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println(Thread.currentThread() + ": " + "Read operation completed, file contents is: " +
                        new String(buffer.array()));
                clearUp();
            }

            @Override
            public void failed(Throwable e, Object attachment) {
                System.err.println(Thread.currentThread() + ": " + "Exception performing write");
                e.printStackTrace();
                clearUp();
            }

            private void clearUp() {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread.sleep(1000L);

    }
}
