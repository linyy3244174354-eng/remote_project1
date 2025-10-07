/**
 * 10边星形（十角星）类（参数：外接圆半径）
 * 基于正10边形变形，周长=10×星边长度，面积通过三角函数推导
 */
public class DecagonStar extends Shape {
    private double circumRadius; // 外接圆半径（包围星形的最小圆半径）
    private static final int POINT_COUNT = 10; // 固定10个顶点

    /**
     * 构造方法：校验外接圆半径有效性
     * @param circumRadius 外接圆半径（>0）
     */
    public DecagonStar(double circumRadius) {
        if (circumRadius <= 0) {
            throw new IllegalArgumentException("外接圆半径必须大于0");
        }
        this.circumRadius = circumRadius;
        this.name = "10边星形";
    }

    /**
     * 计算周长：10条星边长度之和（单条边长度=2R×sin(π/10)，π/10=18°）
     */
    @Override
    public double calculatePerimeter() {
        double singleSideLength = 2 * circumRadius * Math.sin(Math.PI / 10); // 单条星边长度
        double totalPerimeter = POINT_COUNT * singleSideLength; // 10条边总长
        return round(totalPerimeter);
    }

    /**
     * 计算面积：基于外接圆半径推导（公式：5R²×sin72°×sin36°×2）
     * 原理：将十角星拆分为10个等腰三角形，求和后简化公式
     */
    @Override
    public double calculateArea() {
        double rSquared = Math.pow(circumRadius, 2);
        double sin72 = Math.sin(Math.toRadians(72)); // sin72°≈0.9511
        double sin36 = Math.sin(Math.toRadians(36)); // sin36°≈0.5878
        double area = 5 * rSquared * sin72 * sin36 * 2;
        return round(area);
    }

    /**
     * 输出10边星形完整信息（参数+顶点数+周长+面积）
     */
    @Override
    public String getInfo() {
        return String.format("%s - 外接圆半径: %.2f, 顶点数: %d, 周长: %.2f, 面积: %.2f",
                name, circumRadius, POINT_COUNT, calculatePerimeter(), calculateArea());
    }

    // Getter方法（供外部访问参数）
    public double getCircumRadius() {
        return circumRadius;
    }

    public int getPointCount() {
        return POINT_COUNT;
    }
}