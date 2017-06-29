package map;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Formatter f = new Formatter(System.out);
        Map<String, Double> items = new HashMap<>();
        items.put("西瓜", 23.0);
        items.put("木炭", 25.0);
        items.put("大虾", 54.0);
        items.put("鸡翅", 30.0);
        items.put("毛豆", 8.0);
        items.put("矿泉水", 31.0);
        items.put("脆骨类-1", 50.0);
        items.put("脆骨类-2", 20.0);
        items.put("豆皮、金针菇", 10.5);
        items.put("茄子、辣椒", 8.5);
        items.put("红薯", 15.5);
        items.put("玉米", 16.0);
        items.put("酱料", 20.0);
        items.put("鱼豆腐", 20.0);
        items.put("羊肉", 150.0);

        Map<String, List<String>> person2item = new HashMap<>();
        person2item.put("yanbo", new ArrayList<>());
        person2item.get("yanbo").add("玉米");
        person2item.get("yanbo").add("鱼豆腐");
        person2item.get("yanbo").add("红薯");

        person2item.put("yujiao", new ArrayList<>());
        person2item.get("yujiao").add("大虾");
        person2item.get("yujiao").add("鸡翅");
        person2item.get("yujiao").add("毛豆");

        person2item.put("xiurong", new ArrayList<>());
        person2item.get("xiurong").add("西瓜");
        person2item.get("xiurong").add("木炭");
        person2item.get("xiurong").add("酱料");
        person2item.get("xiurong").add("茄子、辣椒");
        person2item.get("xiurong").add("豆皮、金针菇");
        person2item.get("xiurong").add("脆骨类-2");


        person2item.put("maxf", new ArrayList<>());
        person2item.get("maxf").add("矿泉水");

        person2item.put("yingchao", new ArrayList<>());
        person2item.get("yingchao").add("脆骨类-1");
        person2item.get("yingchao").add("羊肉");


        System.out.println("周末烧烤活动愉快结束，明细如下（除玉蛟外，其他人按2人金额转账给我）：");
        items.entrySet().forEach(a -> f.format("%-15s%5.2f\n", a.getKey(), a.getValue()));
        Double totalSum = items.entrySet().stream().mapToDouble(a -> a.getValue()).sum();
        int totalPerson = 11;
        Double onePersonFee = scale2digit(totalSum / totalPerson);
        Double twoPersonFee = scale2digit(onePersonFee * 2);

        System.out.println("\n总数：" + totalSum + "; " + "人数：" + totalPerson + "，" + onePersonFee + "元/人; " +
                twoPersonFee + "元/2人");

        Map<String, Double> person2fee = new HashMap<>();
        person2item.entrySet().forEach(a -> {
            List<String> itmesForPerson = a.getValue();
            Double totalForPerson = itmesForPerson.stream().mapToDouble(b -> items.get(b)).sum();
            person2fee.put(a.getKey(), totalForPerson);
        });

        System.out.println("\n已知出资如下：------------");
        person2item.entrySet().forEach(a -> {
            f.format("%-15s %-15s %5.2f\n", a.getKey(), a.getValue(), scale2digit(person2fee.get(a
                    .getKey())));
        });

        System.out.println("未知出资如下请确认！");
        List<String> all2Person = person2item.entrySet().stream().map(a -> a.getValue()).flatMap(l -> l.stream())
                .collect(Collectors.toList());

        items.entrySet().forEach(a -> {
            if (!all2Person.contains(a.getKey())) {
                f.format("%-15s%5.2f\n", a.getKey(), a.getValue());
            }
        });

        System.out.println(92 - onePersonFee);

    }

    private static Double scale2digit(Double src) {
        return new BigDecimal(src).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
