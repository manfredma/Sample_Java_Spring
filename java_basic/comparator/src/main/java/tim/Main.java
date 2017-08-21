package tim;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Map<String, Object>> a = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("code", "");
            a.add(objectMap);
        }
        a.get(25).put("code", "xxx");

        Collections.sort(a, Comparator.nullsLast((o1, o2) -> {
            // 排序, 空排后面;
            return new CodeComparator().compare(o1.get("code").toString(), o2.get("code").toString());
        }));
    }

    public static class CodeComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if (null == o1 || "".equals(o1)) {
                return 1;
            } else if (null == o2 || "".equals(o2)) {
                return -1;
            } else {
                return o1.compareTo(o2);
            }
        }
    }
}
