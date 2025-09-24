import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * 圆环类，计算圆环的周长和面积
 */
public class Annulus {
    private double innerRadius; // 内半径
    private double outerRadius; // 外半径
    private static final String SHAPE_NAME = "圆环";

    /**
     * 构造方法，初始化圆环的内半径和外半径
     * @param innerRadius 内半径（必须大于0）
     * @param outerRadius 外半径（必须大于内半径）
     * @throws IllegalArgumentException 当参数不满足要求时抛出
     */
    public Annulus(double innerRadius, double outerRadius) {
        if (innerRadius <= 0) {
            throw new IllegalArgumentException("内半径必须大于0");
        }
        if (outerRadius <= innerRadius) {
            throw new IllegalArgumentException("外半径必须大于内半径");
        }
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
    }

    /**
     * 计算圆环周长（内圆周长 + 外圆周长）
     * @return 周长，保留两位小数
     */
    public double calculatePerimeter() {
        double innerPerimeter = 2 * Math.PI * innerRadius;
        double outerPerimeter = 2 * Math.PI * outerRadius;
        return round(innerPerimeter + outerPerimeter);
    }

    /**
     * 计算圆环面积（外圆面积 - 内圆面积）
     * @return 面积，保留两位小数
     */
    public double calculateArea() {
        double innerArea = Math.PI * Math.pow(innerRadius, 2);
        double outerArea = Math.PI * Math.pow(outerRadius, 2);
        return round(outerArea - innerArea);
    }

    /**
     * 保留两位小数
     */
    private double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 返回圆环的基本信息和计算结果
     */
    @Override
    public String toString() {
        return String.format("%s - 内半径: %.2f, 外半径: %.2f, 周长: %.2f, 面积: %.2f",
                SHAPE_NAME, innerRadius, outerRadius, calculatePerimeter(), calculateArea());
    }

    /**
     * 控制台交互测试
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("===== 圆环计算 =====");
            System.out.print("请输入内半径: ");
            double inner = scanner.nextDouble();
            System.out.print("请输入外半径: ");
            double outer = scanner.nextDouble();

            Annulus annulus = new Annulus(inner, outer);
            System.out.println("\n计算结果:");
            System.out.println(annulus);
        } catch (IllegalArgumentException e) {
            System.out.println("错误: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("输入错误: 请输入有效的数字");
        } finally {
            scanner.close();
        }
    }
}
