import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * 正六边形类，继承自正多边形基类
 */
public class RegularHexagon extends RegularPolygon {

    /**
     * 构造方法
     * @param sideLength 边长
     */
    public RegularHexagon(double sideLength) {
        super(sideLength, 6, "正六边形");
    }

    /**
     * 计算正六边形面积
     * 公式：(3√3 × 边长²) / 2
     * @return 面积（保留两位小数）
     */
    @Override
    public double calculateArea() {
        double area = (3 * Math.sqrt(3) * sideLength * sideLength) / 2;
        return new BigDecimal(area).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
