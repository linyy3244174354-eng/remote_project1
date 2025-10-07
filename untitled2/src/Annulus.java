/**
 * 圆环类（参数：内半径、外半径）
 * 周长=内圆周长+外圆周长，面积=外圆面积-内圆面积
 */
public class Annulus extends Shape {
    private double innerRadius; // 内半径（r）
    private double outerRadius; // 外半径（R，必须 > 内半径）

    /**
     * 构造方法：校验内/外半径有效性
     * @param innerRadius 内半径（>0）
     * @param outerRadius 外半径（> 内半径）
     */
    public Annulus(double innerRadius, double outerRadius) {
        if (innerRadius <= 0 || outerRadius <= 0) {
            throw new IllegalArgumentException("内半径和外半径必须大于0");
        }
        if (outerRadius <= innerRadius) {
            throw new IllegalArgumentException("外半径必须大于内半径");
        }
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
        this.name = "圆环";
    }

    /**
     * 计算周长：圆环总周长=内圆周长 + 外圆周长（两者均为圆周长=2πr）
     */
    @Override
    public double calculatePerimeter() {
        double innerPerimeter = 2 * Math.PI * innerRadius; // 内圆周长
        double outerPerimeter = 2 * Math.PI * outerRadius; // 外圆周长
        double totalPerimeter = innerPerimeter + outerPerimeter;
        return round(totalPerimeter);
    }

    /**
     * 计算面积：圆环面积=外圆面积 - 内圆面积（圆面积=πr²）
     */
    @Override
    public double calculateArea() {
        double innerArea = Math.PI * Math.pow(innerRadius, 2); // 内圆面积
        double outerArea = Math.PI * Math.pow(outerRadius, 2); // 外圆面积
        double annulusArea = outerArea - innerArea;
        return round(annulusArea);
    }

    /**
     * 输出圆环完整信息（参数+周长+面积）
     */
    @Override
    public String getInfo() {
        return String.format("%s - 内半径: %.2f, 外半径: %.2f, 周长: %.2f, 面积: %.2f",
                name, innerRadius, outerRadius, calculatePerimeter(), calculateArea());
    }

    // Getter方法（供外部访问参数）
    public double getInnerRadius() {
        return innerRadius;
    }

    public double getOuterRadius() {
        return outerRadius;
    }
}