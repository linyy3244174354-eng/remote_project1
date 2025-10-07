/**
 * 平行四边形类（简化参数：底边长、侧边长，默认内角60°计算面积）
 */
public class Parallelogram extends Shape {
    private double base;  // 底边长
    private double side;  // 侧边长
    private static final double INNER_ANGLE = 60; // 默认内角60°（简化计算）

    public Parallelogram(double base, double side) {
        if (base <= 0 || side <= 0) {
            throw new IllegalArgumentException("底边长和侧边长必须大于0");
        }
        this.base = base;
        this.side = side;
        this.name = "平行四边形";
    }

    @Override
    public double calculatePerimeter() {
        return round(2 * (base + side)); // 周长=2(底+侧)
    }

    @Override
    public double calculateArea() {
        // 面积=底×高，高=侧×sin(内角)（内角默认60°，sin60°=√3/2≈0.866）
        double height = side * Math.sin(Math.toRadians(INNER_ANGLE));
        return round(base * height);
    }

    @Override
    public String getInfo() {
        return String.format("%s - 底边长: %.2f, 侧边长: %.2f, 内角: %.0f°, 周长: %.2f, 面积: %.2f",
                name, base, side, INNER_ANGLE, calculatePerimeter(), calculateArea());
    }

    public double getBase() {
        return base;
    }

    public double getSide() {
        return side;
    }
}