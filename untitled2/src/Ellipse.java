/**
 * 椭圆类
 * 第9天任务：实现椭圆的周长和面积计算
 */
public class Ellipse extends Shape {
    private double semiMajorAxis; // 长半轴
    private double semiMinorAxis; // 短半轴

    public Ellipse(double semiMajorAxis, double semiMinorAxis) {
        if (semiMajorAxis <= 0 || semiMinorAxis <= 0) {
            throw new IllegalArgumentException("半轴长度必须大于0");
        }
        // 确保长半轴大于等于短半轴
        if (semiMajorAxis < semiMinorAxis) {
            double temp = semiMajorAxis;
            semiMajorAxis = semiMinorAxis;
            semiMinorAxis = temp;
        }
        this.semiMajorAxis = semiMajorAxis;
        this.semiMinorAxis = semiMinorAxis;
        this.shapeName = "椭圆";
    }

    @Override
    public double calculatePerimeter() {
        // 使用Ramanujan近似公式计算椭圆周长
        double a = semiMajorAxis;
        double b = semiMinorAxis;
        double h = Math.pow((a - b), 2) / Math.pow((a + b), 2);

        double perimeter = Math.PI * (a + b) * (1 + 3 * h / (10 + Math.sqrt(4 - 3 * h)));
        return round(perimeter);
    }

    @Override
    public double calculateArea() {
        // 椭圆面积公式: π × a × b
        return round(Math.PI * semiMajorAxis * semiMinorAxis);
    }

    @Override
    public String getInfo() {
        return String.format("%s - 长半轴: %.2f, 短半轴: %.2f, 周长: %.2f, 面积: %.2f",
                shapeName, semiMajorAxis, semiMinorAxis, calculatePerimeter(), calculateArea());
    }
}
