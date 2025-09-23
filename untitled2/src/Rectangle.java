import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * 长方形 （或正方形）周长面积求解
 */
public class Rectangle {
    private double length;
    private double width;
    private String shapeType;

    // 构造方法，初始化长和宽
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
        this.shapeType = determineShape();
    }

    // 判断是长方形还是正方形
    public String determineShape() {
        // 考虑浮点数精度问题，使用微小误差范围进行比较
        if (Math.abs(length - width) < 0.0001) {
            return "正方形";
        } else {
            return "长方形";
        }
    }

    // 计算周长
    public double calculatePerimeter() {
        double perimeter = 2 * (length + width);
        // 保留两位小数
        return new BigDecimal(perimeter).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    // 计算面积
    public double calculateArea() {
        double area = length * width;
        // 保留两位小数
        return new BigDecimal(area).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    // 打印图形
    public void printShape() {
        // 为了适合控制台显示，将尺寸转换为字符数（限制最大宽度和高度）
        int displayWidth = (int)Math.min(width, 20);  // 最大宽度20个字符
        int displayHeight = (int)Math.min(length, 10); // 最大高度10个字符

        System.out.println("\n图形预览:");

        // 打印顶部边框和宽度标注
        printHorizontalLine(displayWidth, "上", width);

        // 打印中间部分
        for (int i = 0; i < displayHeight - 2; i++) {
            System.out.print("|");
            for (int j = 0; j < displayWidth - 2; j++) {
                System.out.print(" ");
            }
            System.out.println("|");
        }

        // 打印底部边框
        if (displayHeight > 1) {
            printHorizontalLine(displayWidth, "下", width);
        }

        // 打印右侧长度标注（针对高度）
        System.out.printf("高: %.2f\n", length);
    }

    // 打印水平线并标注宽度
    private void printHorizontalLine(int displayWidth, String position, double actualWidth) {
        System.out.print("+");
        for (int j = 0; j < displayWidth - 2; j++) {
            System.out.print("-");
        }
        System.out.printf("+%s宽: %.2f\n", position, actualWidth);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // 获取用户输入
            System.out.print("请输入长度: ");
            double length = scanner.nextDouble();

            System.out.print("请输入宽度: ");
            double width = scanner.nextDouble();

            // 验证输入是否为正数
            if (length <= 0 || width <= 0) {
                System.out.println("错误: 长度和宽度必须为正数!");
                return;
            }

            // 创建计算器实例
            Rectangle printer = new Rectangle(length, width);

            // 计算并显示结果
            System.out.println("\n判断结果: 这是一个" + printer.shapeType);
            System.out.println("周长: " + printer.calculatePerimeter());
            System.out.println("面积: " + printer.calculateArea());

            // 打印图形
            printer.printShape();
        } catch (Exception e) {
            System.out.println("输入错误: 请输入有效的数字!");
        } finally {
            scanner.close();
        }
    }
}
