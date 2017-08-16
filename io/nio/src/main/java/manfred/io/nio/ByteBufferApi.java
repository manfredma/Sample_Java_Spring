package manfred.io.nio;

import java.nio.ByteBuffer;

public class ByteBufferApi {
    private static final int BUFFER_CAPACITY = 8;

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_CAPACITY);

        // 放入操作不能超过 buffer 的大小
        for (int i = 0; i < BUFFER_CAPACITY; i++) {
            byteBuffer.put((byte) (i + 1));
        }

        System.out.println(byteBuffer);
        // get
        System.out.println(byteBuffer.get(2));
        byteBuffer.flip();
        byte[] copy = new byte[2];
        byteBuffer.get(copy);
        System.out.println(byteBuffer);
        byte[] copy2 = new byte[4];
        byteBuffer.get(copy2, 2, 2);
        System.out.println(byteBuffer);
        System.out.print("copy = ");
        for (byte b : copy) {
            System.out.print(" | " + b);
        }
        System.out.println(" |");
        System.out.print("copy2 = ");
        for (byte b : copy2) {
            System.out.print(" | " + b);
        }
        System.out.println(" |");
        System.out.print("ByteBuffer remaining: ");
        while (byteBuffer.hasRemaining()) {
            System.out.print(" | " + byteBuffer.get());
        }
        System.out.println(" |");

        System.out.println(byteBuffer.asReadOnlyBuffer());
    }
}
