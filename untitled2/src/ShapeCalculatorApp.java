/**
 * 平面图形计算器主程序
 * 第14天任务：实现用户交互界面和程序入口
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ShapeCalculatorApp {
    private static Scanner scanner = new Scanner(System.in);
    private static ShapeManager shapeManager = new ShapeManager();

    public static void main(String[] args) {
        System.out.println("===== 平面图形计算器 =====");
        boolean running = true;

        while (running) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addNewShape();
                    break;
                case 2:
                    viewAllShapes();
                    break;
                case 3:
                    viewStatistics();
                    break;
                case 4:
                    running = false;
                    System.out.println("感谢使用，再见！");
                    break;
                default:
                    System.out.println("无效选择，请重试");
            }
        }
        scanner.close();
    }

    // 打印主菜单
    private static void printMenu() {
        System.out.println("\n请选择操作:");
        System.out.println("1. 添加新图形");
        System.out.println("2. 查看所有图形");
        System.out.println("3. 查看统计信息");
        System.out.println("4. 退出程序");
        System.out.print("请输入选择: ");
    }

    // 获取用户选择
    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // 添加新图形
    private static void addNewShape() {
        System.out.println("\n===== 添加新图形 =====");
        System.out.println("请选择图形类型:");
        System.out.println("1. 长方形    2. 圆形      3. 三角形    4. 平行四边形");
        System.out.println("5. 梯形      6. 菱形      7. 正多边形  8. 椭圆");
        System.out.println("9. 扇形      10. 圆环     11. 10边星形");
        System.out.print("请输入选择: ");

        try {
            int shapeType = Integer.parseInt(scanner.nextLine().trim());
            Shape shape = createShapeByType(shapeType);

            if (shape != null) {
                shapeManager.addShape(shape);
                System.out.println("图形添加成功！");
                System.out.println(shape.getInfo());
            }
        } catch (Exception e) {
            System.out.println("添加失败: " + e.getMessage());
        }
    }

    // 根据类型创建图形
    private static Shape createShapeByType(int type) {
        switch (type) {
            case 1:
                return createRectangle();
            case 2:
                return createCircle();
            case 3:
                return createTriangle();
            case 4:
                return createParallelogram();
            case 5:
                return createTrapezoid();
            case 6:
                return createRhombus();
            case 7:
                return createRegularPolygon();
            case 8:
                return createEllipse();
            case 9:
                return createSector();
            case 10:
                return createAnnulus();
            case 11:
                return createDecagonStar();
            default:
                System.out.println("无效的图形类型");
                return null;
        }
    }

    // 创建长方形
    private static Rectangle createRectangle() {
        System.out.print("请输入长度: ");
        double length = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入宽度: ");
        double width = Double.parseDouble(scanner.nextLine().trim());
        return new Rectangle(length, width);
    }

    // 创建圆形
    private static Circle createCircle() {
        System.out.print("请输入半径: ");
        double radius = Double.parseDouble(scanner.nextLine().trim());
        return new Circle(radius);
    }

    // 创建三角形
    private static Triangle createTriangle() {
        System.out.print("请输入第一条边长: ");
        double a = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入第二条边长: ");
        double b = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入第三条边长: ");
        double c = Double.parseDouble(scanner.nextLine().trim());
        return new Triangle(a, b, c);
    }

    // 创建平行四边形
    private static Parallelogram createParallelogram() {
        System.out.print("请输入底边长: ");
        double base = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入高: ");
        double height = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入侧边长度: ");
        double side = Double.parseDouble(scanner.nextLine().trim());
        return new Parallelogram(base, height, side);
    }

    // 创建梯形
    private static Trapezoid createTrapezoid() {
        System.out.print("请输入上底: ");
        double top = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入下底: ");
        double bottom = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入高: ");
        double height = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入左侧边: ");
        double left = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入右侧边: ");
        double right = Double.parseDouble(scanner.nextLine().trim());
        return new Trapezoid(top, bottom, height, left, right);
    }

    // 创建菱形
    private static Rhombus createRhombus() {
        System.out.print("请输入边长: ");
        double side = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入第一条对角线: ");
        double d1 = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入第二条对角线: ");
        double d2 = Double.parseDouble(scanner.nextLine().trim());
        return new Rhombus(side, d1, d2);
    }

    // 创建正多边形
    private static RegularPolygon createRegularPolygon() {
        System.out.print("请输入边长: ");
        double side = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入边数(>=3): ");
        int count = Integer.parseInt(scanner.nextLine().trim());
        return new RegularPolygon(side, count);
    }

    // 创建椭圆
    private static Ellipse createEllipse() {
        System.out.print("请输入长半轴: ");
        double major = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入短半轴: ");
        double minor = Double.parseDouble(scanner.nextLine().trim());
        return new Ellipse(major, minor);
    }

    // 创建扇形
    private static Sector createSector() {
        System.out.print("请输入半径: ");
        double radius = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入圆心角(度): ");
        double angle = Double.parseDouble(scanner.nextLine().trim());
        return new Sector(radius, angle);
    }

    // 创建圆环
    private static Annulus createAnnulus() {
        System.out.print("请输入内半径: ");
        double inner = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入外半径: ");
        double outer = Double.parseDouble(scanner.nextLine().trim());
        return new Annulus(inner, outer);
    }

    // 创建10边星形
    private static DecagonStar createDecagonStar() {
        System.out.print("请输入外接圆半径: ");
        double radius = Double.parseDouble(scanner.nextLine().trim());
        return new DecagonStar(radius);
    }

    // 查看所有图形
    private static void viewAllShapes() {
        System.out.println("\n===== 所有图形 =====");
        List<Shape> shapes = shapeManager.getAllShapes();

        if (shapes.isEmpty()) {
            System.out.println("暂无图形，请先添加");
            return;
        }

        for (int i = 0; i < shapes.size(); i++) {
            System.out.println((i + 1) + ". " + shapes.get(i).getInfo());
        }
    }

    // 查看统计信息
    private static void viewStatistics() {
        System.out.println("\n===== 统计信息 =====");
        System.out.println(shapeManager.getShapeCountStatistics());
        System.out.printf("所有图形总周长: %.2f\n", shapeManager.getTotalPerimeter());
        System.out.printf("所有图形总面积: %.2f\n", shapeManager.getTotalArea());
    }
}
