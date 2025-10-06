/**
 * 菱形类
 * 第7天任务：实现菱形的周长和面积计算
 */
public class Rhombus extends Shape {
    private double side;     // 边长
    private double diagonal1; // 对角线1
    private double diagonal2; // 对角线2

    public Rhombus(double side, double diagonal1, double diagonal2) {
        if (side <= 0 || diagonal1 <= 0 || diagonal2 <= 0) {
            throw new IllegalArgumentException("边长和对角线必须大于0");
        }
        // 验证对角线与边长的关系
        if (4 * side * side != diagonal1 * diagonal1 + diagonal2 * diagonal2) {
            throw new IllegalArgumentException("对角线与边长不匹配");
        }
        this.side = side;
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
        this.shapeName = "菱形";
    }

    @Override
    public double calculatePerimeter() {
        return round(4 * side);
    }

    @Override
    public double calculateArea() {
        return round(diagonal1 * diagonal2 / 2);
    }

    @Override
    public String getInfo() {
        return String.format("%s - 边长: %.2f, 对角线1: %.2f, 对角线2: %.2f, 周长: %.2f, 面积: %.2f",
                shapeName, side, diagonal1, diagonal2, calculatePerimeter(), calculateArea());
    }
}
