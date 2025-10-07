/**
 * 梯形类（简化参数：上底、下底、高，默认两腰相等的等腰梯形）
 */
public class Trapezoid extends Shape {
    private double topBase;    // 上底
    private double bottomBase; // 下底
    private double height;     // 高

    public Trapezoid(double topBase, double bottomBase, double height) {
        // 校验：上底/下底/高>0，且下底>上底（符合梯形常识）
        if (topBase <= 0 || bottomBase <= 0 || height <= 0) {
            throw new IllegalArgumentException("上底、下底、高必须大于0");
        }
        if (bottomBase <= topBase) {
            throw new IllegalArgumentException("下底长度必须大于上底");
        }
        this.topBase = topBase;
        this.bottomBase = bottomBase;
        this.height = height;
        this.name = "梯形";
    }

    @Override
    public double calculatePerimeter() {
        // 等腰梯形周长=上底+下底+2×腰长，腰长=√[高² + ((下底-上底)/2)²]
        double waist = Math.sqrt(Math.pow(height, 2) + Math.pow((bottomBase - topBase) / 2, 2));
        return round(topBase + bottomBase + 2 * waist);
    }

    @Override
    public double calculateArea() {
        return round((topBase + bottomBase) * height / 2); // 面积=(上底+下底)×高/2
    }

    @Override
    public String getInfo() {
        return String.format("%s - 上底: %.2f, 下底: %.2f, 高: %.2f, 周长: %.2f, 面积: %.2f",
                name, topBase, bottomBase, height, calculatePerimeter(), calculateArea());
    }

    public double getTopBase() {
        return topBase;
    }

    public double getBottomBase() {
        return bottomBase;
    }

    public double getHeight() {
        return height;
    }
}