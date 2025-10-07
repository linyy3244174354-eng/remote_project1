/**
 * 菱形类（简化参数：仅边长，默认内角60°计算面积）
 */
public class Rhombus extends Shape {
    private double side;  // 边长
    private double d1;
    private double d2;
    private static final double INNER_ANGLE = 60; // 默认内角60°（简化计算）

    public Rhombus(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("边长必须大于0");
        }
        this.side = side;
        this.name = "菱形";
    }

    @Override
    public double calculatePerimeter() {
        return round(4 * side); // 周长=4×边长
    }

    @Override
    public double calculateArea() {
        // 面积=边长²×sin(内角)（内角默认60°，sin60°≈0.866）
        return round(Math.pow(side, 2) * Math.sin(Math.toRadians(INNER_ANGLE)));
    }

    @Override
    public String getInfo() {
        return String.format("%s - 边长: %.2f, 内角: %.0f°, 周长: %.2f, 面积: %.2f",
                name, side, INNER_ANGLE, calculatePerimeter(), calculateArea());
    }

    public double getSide() {
        return side;
    }
}