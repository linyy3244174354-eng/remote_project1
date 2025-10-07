/**
 * 圆形类（参数：半径）
 */
public class Circle extends Shape {
    private double radius; // 半径

    public Circle(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("半径必须大于0");
        }
        this.radius = radius;
        this.name = "圆形";
    }

    @Override
    public double calculatePerimeter() {
        return round(2 * Math.PI * radius); // 周长=2πr
    }

    @Override
    public double calculateArea() {
        return round(Math.PI * radius * radius); // 面积=πr²
    }

    @Override
    public String getInfo() {
        return String.format("%s - 半径: %.2f, 周长: %.2f, 面积: %.2f",
                name, radius, calculatePerimeter(), calculateArea());
    }

    public double getRadius() {
        return radius;
    }
}