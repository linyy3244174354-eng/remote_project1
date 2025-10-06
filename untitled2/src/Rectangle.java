/**
 * 长方形类
 * 第2天任务：实现长方形的周长和面积计算
 */
public class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        if (length <= 0 || width <= 0) {
            throw new IllegalArgumentException("长和宽必须大于0");
        }
        this.length = length;
        this.width = width;
        this.shapeName = "长方形";
    }

    @Override
    public double calculatePerimeter() {
        return round(2 * (length + width));
    }

    @Override
    public double calculateArea() {
        return round(length * width);
    }

    // 判断是否为正方形
    public boolean isSquare() {
        return Math.abs(length - width) < 0.0001;
    }

    @Override
    public String getInfo() {
        String type = isSquare() ? "正方形" : "长方形";
        return String.format("%s - 长: %.2f, 宽: %.2f, 周长: %.2f, 面积: %.2f",
                type, length, width, calculatePerimeter(), calculateArea());
    }
}
