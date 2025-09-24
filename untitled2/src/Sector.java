import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * 扇形类，计算扇形的周长和面积
 */
public class Sector {
    private double radius; // 半径
    private double angle; // 圆心角（单位：度）
    private static final String SHAPE_NAME = "扇形";

    /**
     * 构造方法，初始化扇形的半径和圆心角
     * @param radius 半径（必须大于0）
     * @param angle 圆心角（必须大于0且小于等于360）
     * @throws IllegalArgumentException 当参数不满足要求时抛出
     */
    public Sector(double radius, double angle) {
        if (radius <= 0) {
            throw new IllegalArgumentException("半径必须大于0");
        }
        if (angle <= 0 || angle > 360) {
            throw new IllegalArgumentException("圆心角必须大于0且小于等于360度");
        }
        this.radius = radius;
        this.angle = angle;
    }

    /**
     * 计算扇形周长（弧长 + 2×半径）
     * 弧长公式：(圆心角/360) × 2πr
     * @return 周长，保留两位小数
     */
    public double calculatePerimeter() {
        double arcLength = (angle / 360) * 2 * Math.PI * radius;
        double perimeter = arcLength + 2 * radius; // 加上两条半径
        return round(perimeter);
    }

    /**
     * 计算扇形面积
     * 公式：(圆心角/360) × πr²
     * @return 面积，  保留两位小数
     */
    public double calculateArea() {
        double area = (angle / 360) * Math.PI * Math.pow(radius, 2);
        return round(area);
    }

    /**
     * 保留两位小数
     */
    private double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 返回扇形的基本信息和计算结果
     */
    @Override
    public String toString() {
        return String.format("%s - 半径: %.2f, 圆心角: %.2f度, 周长: %.2f, 面积: %.2f",
                SHAPE_NAME, radius, angle, calculatePerimeter(), calculateArea());
    }

    /**
     * 控制台交互测试
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("===== 扇形计算 =====");
            System.out.print("请输入半径: ");
            double radius = scanner.nextDouble();
            System.out.print("请输入圆心角(度): ");
            double angle = scanner.nextDouble();

            Sector sector = new Sector(radius, angle);
            System.out.println("\n计算结果:");
            System.out.println(sector);
        } catch (IllegalArgumentException e) {
            System.out.println("错误: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("输入错误: 请输入有效的数字");
        } finally {
            scanner.close();
        }
    }
}
