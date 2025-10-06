/**
 * 圆形类
 * 第3天任务：实现圆形的周长和面积计算
 */
public class Circle extends Shape {
    private double radius;
    private static final double PI = Math.PI;

    public Circle(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("半径必须大于0");
        }
        this.radius = radius;
        this.shapeName = "圆形";
    }

    @Override
    public double calculatePerimeter() {
        return round(2 * PI * radius);
    }

    @Override
    public double calculateArea() {
        return round(PI * radius * radius);
    }

    public double getDiameter() {
        return round(2 * radius);
    }

    @Override
    public String getInfo() {
        return String.format("%s - 半径: %.2f, 直径: %.2f, 周长: %.2f, 面积: %.2f",
                shapeName, radius, getDiameter(), calculatePerimeter(), calculateArea());
    }
}
