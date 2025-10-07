/**
 * 椭圆类（参数：长半轴、短半轴）
 * 周长使用拉马努金近似公式（精度高，适合实际计算），面积使用标准公式
 */
public class Ellipse extends Shape {
    private double semiMajorAxis; // 长半轴（a，必须 ≥ 短半轴）
    private double semiMinorAxis; // 短半轴（b）

    /**
     * 构造方法：校验长/短半轴有效性
     * @param semiMajorAxis 长半轴（>0）
     * @param semiMinorAxis 短半轴（>0，且 ≤ 长半轴）
     */
    public Ellipse(double semiMajorAxis, double semiMinorAxis) {
        if (semiMajorAxis <= 0 || semiMinorAxis <= 0) {
            throw new IllegalArgumentException("长半轴和短半轴必须大于0");
        }
        if (semiMajorAxis < semiMinorAxis) {
            throw new IllegalArgumentException("长半轴必须大于或等于短半轴");
        }
        this.semiMajorAxis = semiMajorAxis;
        this.semiMinorAxis = semiMinorAxis;
        this.name = "椭圆";
    }

    /**
     * 计算周长：拉马努金近似公式（精度优于传统近似）
     * 公式：P ≈ π[3(a+b) - √((3a+b)(a+3b))]
     */
    @Override
    public double calculatePerimeter() {
        double a = semiMajorAxis;
        double b = semiMinorAxis;
        double part1 = 3 * (a + b);
        double part2 = Math.sqrt((3 * a + b) * (a + 3 * b));
        double perimeter = Math.PI * (part1 - part2);
        return round(perimeter);
    }

    /**
     * 计算面积：标准公式（椭圆面积=πab）
     */
    @Override
    public double calculateArea() {
        double area = Math.PI * semiMajorAxis * semiMinorAxis;
        return round(area);
    }

    /**
     * 输出椭圆完整信息（参数+周长+面积）
     */
    @Override
    public String getInfo() {
        return String.format("%s - 长半轴: %.2f, 短半轴: %.2f, 周长: %.2f, 面积: %.2f",
                name, semiMajorAxis, semiMinorAxis, calculatePerimeter(), calculateArea());
    }

    // Getter方法（供外部访问参数）
    public double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public double getSemiMinorAxis() {
        return semiMinorAxis;
    }
}