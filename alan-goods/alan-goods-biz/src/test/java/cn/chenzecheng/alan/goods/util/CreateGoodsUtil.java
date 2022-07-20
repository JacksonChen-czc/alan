package cn.chenzecheng.alan.goods.util;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @author JacksonChen
 * @date 2022/7/19 22:27
 */
public class CreateGoodsUtil {

    public static final String[] BRANDS = {"联想（lenovo）", "ThinkPad", "戴尔（DELL）", "惠普（HP）", "华为（HUAWEI）", "Apple", "小米（MI）", "宏碁（acer）", "荣耀（HONOR）", "机械革命（MECHREVO）", "微软（Microsoft）", "LG", "神舟（HASEE）", "VAIO", "三星（SAMSUNG）"};

    public static final String[] GOODS = {"小背包", "双肩包", "胸包", "旅行箱", "运动鞋", "眼镜", "床垫", "枕头", "螺丝刀", "保温杯", "伞", "驱蚊器", "壁挂空调", "立式空调", "冰箱", "滚筒洗衣机", "波轮洗衣机", "净水器", "微蒸烤", "烟灶", "洗碗机", "扫地机器人", "吸尘器", "空气净化器", "电饭煲", "电磁炉", "水壶", "落地风扇", "投影仪", "灯具"};

    private static Random random = new Random();

    public static String getGoodsName() {
        return BRANDS[random.nextInt(BRANDS.length)] + "-"
                + GOODS[random.nextInt(GOODS.length)] + "-"
                + random.nextInt(10) + "代" + "-"
                + random.nextInt(1000);
    }

    public static BigDecimal getGoodsPrice() {
        return new BigDecimal(random.nextInt(15000) + "." + random.nextInt(100));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getGoodsName());
        }
    }
}
