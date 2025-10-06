/**
 * 10边星形类
 * 第12天任务：实现10边星形的周长和面积计算
 */
public class DecagonStar extends Shape {
    private double radius; // 外接圆半径
    private static final int POINT_COUNT = 10; // 10个顶点

    public DecagonStar(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("外接圆半径必须大于0");
        }
        this.radius = radius;
        this.shapeName = "10边星形";
    }

    @Override
    public double calculatePerimeter() {
        // 10边星形周长 = 10 × 单边长
        // 单边长 = 2 × R × sin(π/10)
        double sideLength = 2 * radius * Math.sin(Math.PI / 10);
        return round(POINT_COUNT * sideLength);
    }

    @Override
    public double calculateArea() {
        // 10边星形面积公式推导
        double area = 5 * Math.pow(radius, 2)
                * Math.sin(Math.toRadians(72))
                * Math.sin(Math.toRadians(36))
                * 2;
        return round(area);
    }

    @Override
    public String getInfo() {
        return String.format("%s - 外接圆半径: %.2f, 顶点数: %d, 周长: %.2f, 面积: %.2f",
                shapeName, radius, POINT_COUNT, calculatePerimeter(), calculateArea());
    }
}
