import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * 正五边形类，继承自正多边形基类
 */
public class RegularPentagon extends RegularPolygon {

    /**
     * 构造方法
     * @param sideLength 边长
     */
    public RegularPentagon(double sideLength) {
        super(sideLength, 5, "正五边形");
    }

    /**
     * 计算正五边形面积
     * 公式：(5 × 边长²) / (4 × tan(π/5))
     * @return 面积（保留两位小数）
     */
    @Override
    public double calculateArea() {
        double area = (5 * sideLength * sideLength) / (4 * Math.tan(Math.PI / 5));
        return new BigDecimal(area).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
