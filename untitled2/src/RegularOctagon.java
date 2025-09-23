import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * 正八边形类，继承自正多边形基类
 */
public class RegularOctagon extends RegularPolygon {

    /**
     * 构造方法
     * @param sideLength 边长
     */
    public RegularOctagon(double sideLength) {
        super(sideLength, 8, "正八边形");
    }

    /**
     * 计算正八边形面积
     * 公式：2 × (1 + √2) × 边长²
     * @return 面积（保留两位小数）
     */
    @Override
    public double calculateArea() {
        double area = 2 * (1 + Math.sqrt(2)) * sideLength * sideLength;
        return new BigDecimal(area).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
