/**
 * 扇形类（参数：半径、圆心角）
 * 周长=弧长+2×半径，面积=（圆心角/360°）× 圆面积
 */
public class Sector extends Shape {
    private double radius; // 扇形半径（对应圆的半径）
    private double angle;  // 圆心角（单位：度，0 < 角度 ≤ 360）

    /**
     * 构造方法：校验半径和圆心角有效性
     * @param radius 半径（>0）
     * @param angle 圆心角（0 < 角度 ≤ 360，避免超出完整圆范围）
     */
    public Sector(double radius, double angle) {
        if (radius <= 0) {
            throw new IllegalArgumentException("半径必须大于0");
        }
        if (angle <= 0 || angle > 360) {
            throw new IllegalArgumentException("圆心角必须在(0, 360]度范围内");
        }
        this.radius = radius;
        this.angle = angle;
        this.name = "扇形";
    }

    /**
     * 计算周长：扇形周长=弧长 + 2×半径（弧长=圆周长×(圆心角/360°)）
     */
    @Override
    public double calculatePerimeter() {
        double arcLength = 2 * Math.PI * radius * (angle / 360); // 弧长
        double perimeter = arcLength + 2 * radius; // 加两条半径
        return round(perimeter);
    }

    /**
     * 计算面积：扇形面积=圆面积×(圆心角/360°)
     */
    @Override
    public double calculateArea() {
        double circleArea = Math.PI * Math.pow(radius, 2); // 对应圆的面积
        double sectorArea = circleArea * (angle / 360);
        return round(sectorArea);
    }

    /**
     * 输出扇形完整信息（参数+周长+面积）
     */
    @Override
    public String getInfo() {
        return String.format("%s - 半径: %.2f, 圆心角: %.1f°, 周长: %.2f, 面积: %.2f",
                name, radius, angle, calculatePerimeter(), calculateArea());
    }

    // Getter方法（供外部访问参数）
    public double getRadius() {
        return radius;
    }

    public double getAngle() {
        return angle;
    }
}