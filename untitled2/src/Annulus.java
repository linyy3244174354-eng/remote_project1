/**
 * 圆环类
 * 第11天任务：实现圆环的周长和面积计算
 */
public class Annulus extends Shape {
    private double innerRadius; // 内半径
    private double outerRadius; // 外半径

    public Annulus(double innerRadius, double outerRadius) {
        if (innerRadius <= 0 || outerRadius <= 0) {
            throw new IllegalArgumentException("半径必须大于0");
        }
        if (innerRadius >= outerRadius) {
            throw new IllegalArgumentException("内半径必须小于外半径");
        }
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
        this.shapeName = "圆环";
    }

    @Override
    public double calculatePerimeter() {
        // 圆环周长 = 内圆周长 + 外圆周长
        double innerPerimeter = 2 * Math.PI * innerRadius;
        double outerPerimeter = 2 * Math.PI * outerRadius;
        return round(innerPerimeter + outerPerimeter);
    }

    @Override
    public double calculateArea() {
        // 圆环面积 = 外圆面积 - 内圆面积
        double innerArea = Math.PI * innerRadius * innerRadius;
        double outerArea = Math.PI * outerRadius * outerRadius;
        return round(outerArea - innerArea);
    }

    // 获取环宽
    public double getRingWidth() {
        return round(outerRadius - innerRadius);
    }

    @Override
    public String getInfo() {
        return String.format("%s - 内半径: %.2f, 外半径: %.2f, 环宽: %.2f, 周长: %.2f, 面积: %.2f",
                shapeName, innerRadius, outerRadius, getRingWidth(),
                calculatePerimeter(), calculateArea());
    }
}
