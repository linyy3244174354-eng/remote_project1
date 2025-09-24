import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * 椭圆类，计算椭圆的周长和 面积
 */
public class Ellipse {
    private double semiMajorAxis; // 长半轴
    private double semiMinorAxis; // 短半轴
    private static final String SHAPE_NAME = "椭圆";

    /**
     * 构造方法，初始化椭圆的长半轴和短半轴
     * @param semiMajorAxis 长半轴（必须大于0）
     * @param semiMinorAxis 短半轴（必须大于0且不大于长半轴）
     * @throws IllegalArgumentException 当参数不满足要求时抛出
     */
    public Ellipse(double semiMajorAxis, double semiMinorAxis) {
        if (semiMajorAxis <= 0 || semiMinorAxis <= 0) {
            throw new IllegalArgumentException("长半轴和短半轴必须大于0");
        }
        if (semiMinorAxis > semiMajorAxis) {
            // 确保长半轴始终大于等于短半轴
            double temp = semiMajorAxis;
            semiMajorAxis = semiMinorAxis;
            semiMinorAxis = temp;
        }
        this.semiMajorAxis = semiMajorAxis;
        this.semiMinorAxis = semiMinorAxis;
    }

    /**
     * 计算椭圆周长（采用Ramanujan近似公式，精度较高）
     * 公式：π × [3(a + b) - √((3a + b)(a + 3b))]
     * @return 周长，保留两位小数
     */
    public double calculatePerimeter() {
        double a = semiMajorAxis;
        double b = semiMinorAxis;
        double perimeter = Math.PI * (3 * (a + b) - Math.sqrt((3 * a + b) * (a + 3 * b)));
        return round(perimeter);
    }

    /**
     * 计算椭圆面积
     * 公式：π × 长半轴 × 短半轴
     * @return 面积，保留两位小数
     */
    public double calculateArea() {
        double area = Math.PI * semiMajorAxis * semiMinorAxis;
        return round(area);
    }

    /**
     * 保留两位小数
     */
    private double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 返回椭圆的基本信息和计算结果
     */
    @Override
    public String toString() {
        return String.format("%s - 长半轴: %.2f, 短半轴: %.2f, 周长: %.2f, 面积: %.2f",
                SHAPE_NAME, semiMajorAxis, semiMinorAxis, calculatePerimeter(), calculateArea());
    }

    /**
     * 控制台交互测试
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("===== 椭圆计算 =====");
            System.out.print("请输入长半轴长度: ");
            double major = scanner.nextDouble();
            System.out.print("请输入短半轴长度: ");
            double minor = scanner.nextDouble();

            Ellipse ellipse = new Ellipse(major, minor);
            System.out.println("\n计算结果:");
            System.out.println(ellipse);
        } catch (IllegalArgumentException e) {
            System.out.println("错误: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("输入错误: 请输入有效的数字");
        } finally {
            scanner.close();
        }
    }
}
