/**
 * 梯形类
 * 第6天任务：实现梯形的周长和面积计算
 */
public class Trapezoid extends Shape {
    private double topBase;    // 上底
    private double bottomBase; // 下底
    private double height;     // 高
    private double leftSide;   // 左侧边
    private double rightSide;  // 右侧边

    public Trapezoid(double topBase, double bottomBase, double height,
                     double leftSide, double rightSide) {
        if (topBase <= 0 || bottomBase <= 0 || height <= 0 ||
                leftSide <= 0 || rightSide <= 0) {
            throw new IllegalArgumentException("所有参数必须大于0");
        }
        this.topBase = topBase;
        this.bottomBase = bottomBase;
        this.height = height;
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.shapeName = "梯形";
    }

    @Override
    public double calculatePerimeter() {
        return round(topBase + bottomBase + leftSide + rightSide);
    }

    @Override
    public double calculateArea() {
        return round((topBase + bottomBase) * height / 2);
    }

    @Override
    public String getInfo() {
        return String.format("%s - 上底: %.2f, 下底: %.2f, 高: %.2f, 周长: %.2f, 面积: %.2f",
                shapeName, topBase, bottomBase, height, calculatePerimeter(), calculateArea());
    }
}
