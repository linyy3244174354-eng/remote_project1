import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 图形抽象基类，定义所有平面图形的通用接口
 */
public abstract class Shape {
    protected String name; // 图形名称（如"长方形"、"圆形"）

    /**
     * 计算周长
     * @return 保留2位小数的周长
     */
    public abstract double calculatePerimeter();

    /**
     * 计算面积
     * @return 保留2位小数的面积
     */
    public abstract double calculateArea();

    /**
     * 获取图形详细信息（参数+周长+面积）
     * @return 格式化的信息字符串
     */
    public abstract String getInfo();

    /**
     * 获取图形名称
     * @return 图形名称
     */
    public String getName() {
        return name;
    }

    /**
     * 通用小数格式化工具（四舍五入保留2位）
     * @param value 原始数值
     * @return 格式化后的值
     */
    protected double round(double value) {
        return new BigDecimal(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}