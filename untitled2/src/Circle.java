import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
//789456123
public class Circle {
    private double radius; // 半径
    private static final double PI = Math.PI; // 圆周率常量

    /**
     * 构造方法，初始化圆的半径
     * @param radius 圆的半径，必须大于0
     * @throws IllegalArgumentException 当半径小于等于0时抛出
     */
    public Circle(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("圆的半径必须大于0");
        }
        this.radius = radius;
    }

    /**
     * 计算圆的周长
     * 周长公式：2 × π × 半径
     * @return 周长，保留两位小数
     */
    public double calculatePerimeter() {
        double perimeter = 2 * PI * radius;
        return new BigDecimal(perimeter).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 计算圆的面积
     * 面积公式：π × 半径²
     * @return 面积，保留两位小数
     */
    public double calculateArea() {
        double area = PI * radius * radius;
        return new BigDecimal(area).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 计算直径
     * 直径公式：2 × 半径
     * @return 直径，保留两位小数
     */
    public double calculateDiameter() {
        return new BigDecimal(2 * radius).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 按指定顺序显示圆形信息：半径 直径 周长 面积
     */
    public void displayInfo() {
        System.out.printf("半径: %.2f 直径: %.2f 周长: %.2f 面积: %.2f\n",
                radius, calculateDiameter(), calculatePerimeter(), calculateArea());
    }

    /**
     * 获取半径
     * @return 圆的半径
     */
    public double getRadius() {
        return radius;
    }

    /**
     * 设置新的半径
     * @param radius 新的半径值，必须大于0
     * @throws IllegalArgumentException 当半径小于等于0时抛出
     */
    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("圆的半径必须大于0");
        }
        this.radius = radius;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("请输入圆的半径: ");
            double radius = scanner.nextDouble();

            Circle circle = new Circle(radius);
            circle.displayInfo();

            // 测试修改半径
            System.out.print("请输入新的半径: ");
            double newRadius = scanner.nextDouble();
            circle.setRadius(newRadius);
            circle.displayInfo();
        } catch (IllegalArgumentException e) {
            System.out.println("错误: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("输入错误: 请输入有效的数字!");
        } finally {
            scanner.close();
        }
    }
}
