/**
 * 扇形类
 * 第10天任务：实现扇形的周长和面积计算
 */
public class Sector extends Shape {
    private double radius;   // 半径
    private double angle;    // 圆心角（度）

    public Sector(double radius, double angle) {
        if (radius <= 0) {
            throw new IllegalArgumentException("半径必须大于0");
        }
        if (angle <= 0 || angle > 360) {
            throw new IllegalArgumentException("圆心角必须在(0, 360]范围内");
        }
        this.radius = radius;
        this.angle = angle;
        this.shapeName = "扇形";
    }

    @Override
    public double calculatePerimeter() {
        // 扇形周长 = 弧长 + 2 × 半径
        double arcLength = 2 * Math.PI * radius * (angle / 360);
        return round(arcLength + 2 * radius);
    }

    @Override
    public double calculateArea() {
        // 扇形面积 = 圆面积 × (角度/360)
        double circleArea = Math.PI * radius * radius;
        return round(circleArea * (angle / 360));
    }

    @Override
    public String getInfo() {
        return String.format("%s - 半径: %.2f, 圆心角: %.2f°, 周长: %.2f, 面积: %.2f",
                shapeName, radius, angle, calculatePerimeter(), calculateArea());
    }
}
