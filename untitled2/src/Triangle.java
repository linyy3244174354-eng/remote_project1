/**
 * 三角形类
 * 第4天任务：实现三角形的周长和面积计算（使用海伦公式）
 */
public class Triangle extends Shape {
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        // 验证三角形三边关系
        if (!isValidTriangle(sideA, sideB, sideC)) {
            throw new IllegalArgumentException("三边无法构成三角形");
        }
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.shapeName = "三角形";
    }

    // 验证三角形有效性
    private boolean isValidTriangle(double a, double b, double c) {
        return a > 0 && b > 0 && c > 0 &&
                (a + b > c) && (a + c > b) && (b + c > a);
    }

    @Override
    public double calculatePerimeter() {
        return round(sideA + sideB + sideC);
    }

    @Override
    public double calculateArea() {
        double s = (sideA + sideB + sideC) / 2; // 半周长
        double area = Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
        return round(area);
    }

    @Override
    public String getInfo() {
        return String.format("%s - 三边长: %.2f, %.2f, %.2f, 周长: %.2f, 面积: %.2f",
                shapeName, sideA, sideB, sideC, calculatePerimeter(), calculateArea());
    }
}
