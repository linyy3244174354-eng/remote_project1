/**
 * 图形抽象基类
 * 第1天任务：定义所有图形的通用接口
 */
import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Shape {
    protected String shapeName; // 图形名称

    // 计算周长
    public abstract double calculatePerimeter();

    // 计算面积
    public abstract double calculateArea();

    // 获取图形信息
    public abstract String getInfo();

    // 保留两位小数工具方法
    protected double round(double value) {
        return new BigDecimal(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public String getShapeName() {
        return shapeName;
    }
}
