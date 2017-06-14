import org.springframework.util.SerializationUtils;

import java.util.*;

public class MapMain {
    public static void main(String[] args) throws InterruptedException {
        MapMain mapMain = new MapMain();
        Map<String, String> map = new HashMap<>();
        new Thread(mapMain.r).start();
        for (int i = 0; i < 1000000000; i++) {
            System.out.println(mapMain.map.size());
            SerializationUtils.deserialize(SerializationUtils.serialize(mapMain.map));
//            map.putAll(mapMain.map);
//            Iterator<Map.Entry<String, String>> iterable = mapMain.map.entrySet().iterator();
//            while (iterable.hasNext()) {
//                Map.Entry<String, String> entry = iterable.next();
//                Thread.sleep(20L);
//                System.out.println(entry);
//            }
//            for (Map.Entry<String, String> stringStringEntry : mapMain.map.entrySet()) {
//                System.out.println(stringStringEntry.getKey() + "-" + stringStringEntry.getValue());
//            }
//            mapMain.map.entrySet().forEach(a -> {
//                System.out.println(a.getKey() + "-" + a.getValue());
//            });
        }
    }

    private java.util.Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
//    private java.util.Map<String, String> map = new HashMap<>();
    private Runnable r = new Runnable() {
        @Override
        public void run() {
            while (true) {
                map.put(new Double(new Random().nextDouble()).toString(), new Double(new Random().nextDouble()).toString());
            }
//            try {
//                Thread.sleep(1L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    };
}
