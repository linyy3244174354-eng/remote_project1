import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * 10边星形类（十角星），计算 周长和面积
 */
public class DecagonStar {
    private double radius; // 外接圆半径
    private static final int POINT_COUNT = 10; // 10个顶点
    private static final String SHAPE_NAME = "10边星形";

    /**
     * 构造方法，初始化十角星的外接圆半径
     * @param radius 外接圆半径（必须大于0）
     * @throws IllegalArgumentException 当半径不满足要求时抛出
     */
    public DecagonStar(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("外接圆半径必须大于0");
        }
        this.radius = radius;
    }

    /**
     * 计算10边星形周长
     * 公式：10 × 星边长度，其中星边长度 = 2 × R × sin(π/10)
     * @return 周长，保留两位小数
     */
    public double calculatePerimeter() {
        double sideLength = 2 * radius * Math.sin(Math.PI / 10); // 单条边长度
        double perimeter = POINT_COUNT * sideLength; // 10条边总长
        return round(perimeter);
    }

    /**
     * 计算10边星形面积
     * 公式：(5 × R²) × sin(72°) × sin(36°) × 2
     * @return 面积，保留两位小数
     */
    public double calculateArea() {
        double area = 5 * Math.pow(radius, 2)
                * Math.sin(Math.toRadians(72))
                * Math.sin(Math.toRadians(36))
                * 2;
        return round(area);
    }

    /**
     * 保留两位小数
     */
    private double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 返回10边星形的基本信息和计算结果
     */
    @Override
    public String toString() {
        return String.format("%s - 外接圆半径: %.2f, 顶点数: %d, 周长: %.2f, 面积: %.2f",
                SHAPE_NAME, radius, POINT_COUNT, calculatePerimeter(), calculateArea());
    }

    /**
     * 控制台交互测试
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("===== 10边星形计算 =====");
            System.out.print("请输入外接圆半径: ");
            double radius = scanner.nextDouble();

            DecagonStar star = new DecagonStar(radius);
            System.out.println("\n计算结果:");
            System.out.println(star);
        } catch (IllegalArgumentException e) {
            System.out.println("错误: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("输入错误: 请输入有效的数字");
        } finally {
            scanner.close();
        }
    }
}
