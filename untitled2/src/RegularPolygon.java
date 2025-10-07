/**
 * 正多边形类（参数：边长、边数）
 */
public class RegularPolygon extends Shape {
    private double side;  // 边长
    private int sideCount; // 边数（≥3）

    public RegularPolygon(double side, int sideCount) {
        if (side <= 0) {
            throw new IllegalArgumentException("边长必须大于0");
        }
        if (sideCount < 3) {
            throw new IllegalArgumentException("边数必须≥3（最少3条边构成多边形）");
        }
        this.side = side;
        this.sideCount = sideCount;
        this.name = "正" + sideCount + "边形"; // 动态生成名称（如"正五边形"）
    }

    @Override
    public double calculatePerimeter() {
        return round(side * sideCount); // 周长=边长×边数
    }

    @Override
    public double calculateArea() {
        // 面积公式：(边数×边长²) / (4×tan(π/边数))
        double area = (sideCount * Math.pow(side, 2))
                / (4 * Math.tan(Math.PI / sideCount));
        return round(area);
    }

    @Override
    public String getInfo() {
        return String.format("%s - 边长: %.2f, 边数: %d, 周长: %.2f, 面积: %.2f",
                name, side, sideCount, calculatePerimeter(), calculateArea());
    }

    public double getSide() {
        return side;
    }

    public int getSideCount() {
        return sideCount;
    }
}