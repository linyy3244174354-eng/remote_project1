/**
 * 平行四边形类
 * 第5天任务：实现平行四边形的周长和面积计算
 */
public class Parallelogram extends Shape {
    private double base;      // 底边长
    private double height;    // 高
    private double side;      // 侧边长度

    public Parallelogram(double base, double height, double side) {
        if (base <= 0 || height <= 0 || side <= 0) {
            throw new IllegalArgumentException("底、高和侧边必须大于0");
        }
        this.base = base;
        this.height = height;
        this.side = side;
        this.shapeName = "平行四边形";
    }

    @Override
    public double calculatePerimeter() {
        return round(2 * (base + side));
    }

    @Override
    public double calculateArea() {
        return round(base * height);
    }

    @Override
    public String getInfo() {
        return String.format("%s - 底: %.2f, 高: %.2f, 侧边: %.2f, 周长: %.2f, 面积: %.2f",
                shapeName, base, height, side, calculatePerimeter(), calculateArea());
    }
}
