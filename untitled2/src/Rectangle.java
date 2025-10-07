/**
 * 长方形类（参数：长、宽）
 */
public class Rectangle extends Shape {
    private double length; // 长
    private double width;  // 宽

    /**
     * 构造方法（校验参数有效性）
     * @param length 长（必须>0）
     * @param width  宽（必须>0）
     */
    public Rectangle(double length, double width) {
        if (length <= 0 || width <= 0) {
            throw new IllegalArgumentException("长和宽必须大于0");
        }
        this.length = length;
        this.width = width;
        this.name = "长方形";
    }

    @Override
    public double calculatePerimeter() {
        return round(2 * (length + width));
    }

    @Override
    public double calculateArea() {
        return round(length * width);
    }

    @Override
    public String getInfo() {
        return String.format("%s - 长: %.2f, 宽: %.2f, 周长: %.2f, 面积: %.2f",
                name, length, width, calculatePerimeter(), calculateArea());
    }

    // Getter（供外部访问参数）
    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }
}