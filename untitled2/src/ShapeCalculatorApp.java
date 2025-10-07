/**
 * 平面图形计算器主程序
 * 第14天任务：实现用户交互界面和程序入口
 * 优化点：简化平行四边形（仅底+侧边）、梯形（仅上底+下底+高）、菱形（仅边长）的参数输入
 */
import java.util.Scanner;
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
                    System.out.println("无效选择，请重试（仅支持1-4）");
            }
        }
        scanner.close();
    }

    // 打印主菜单（清晰展示功能选项）
    private static void printMenu() {
        System.out.println("\n请选择操作:");
        System.out.println("1. 添加新图形");
        System.out.println("2. 查看所有图形");
        System.out.println("3. 查看统计信息");
        System.out.println("4. 退出程序");
        System.out.print("请输入选择(1-4): ");
    }

    // 获取用户选择（处理非数字输入异常）
    private static int getUserChoice() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("输入错误：请输入数字（1-4）");
            return -1; // 返回无效值，触发后续重试
        }
    }

    // 添加新图形（统一入口，调用类型创建方法）
    private static void addNewShape() {
        System.out.println("\n===== 添加新图形 =====");
        System.out.println("请选择图形类型:");
        System.out.println("1. 长方形    2. 圆形      3. 三角形    4. 平行四边形");
        System.out.println("5. 梯形      6. 菱形      7. 正多边形  8. 椭圆");
        System.out.println("9. 扇形      10. 圆环     11. 10边星形");
        System.out.print("请输入图形类型(1-11): ");

        try {
            int shapeType = Integer.parseInt(scanner.nextLine().trim());
            Shape shape = createShapeByType(shapeType);

            if (shape != null) {
                shapeManager.addShape(shape);
                System.out.println("\n✅ 图形添加成功！");
                System.out.println("📊 图形详情：" + shape.getInfo());
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ 添加失败：请输入数字（1-11）选择图形类型");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ 添加失败：" + e.getMessage()); // 捕获图形类的参数校验异常
        } catch (Exception e) {
            System.out.println("❌ 添加失败：未知错误，请重试");
        }
    }

    // 根据类型创建图形（分发到对应创建方法）
    private static Shape createShapeByType(int type) {
        switch (type) {
            case 1: return createRectangle();
            case 2: return createCircle();
            case 3: return createTriangle();
            case 4: return createParallelogram(); // 简化参数
            case 5: return createTrapezoid();     // 简化参数
            case 6: return createRhombus();       // 简化参数
            case 7: return createRegularPolygon();
            case 8: return createEllipse();
            case 9: return createSector();
            case 10: return createAnnulus();
            case 11: return createDecagonStar();
            default:
                System.out.println("❌ 无效的图形类型：仅支持1-11");
                return null;
        }
    }

    // 1. 创建长方形（参数：长、宽）
    private static Rectangle createRectangle() {
        System.out.print("请输入长方形的长: ");
        double length = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入长方形的宽: ");
        double width = Double.parseDouble(scanner.nextLine().trim());
        return new Rectangle(length, width);
    }

    // 2. 创建圆形（参数：半径）
    private static Circle createCircle() {
        System.out.print("请输入圆形的半径: ");
        double radius = Double.parseDouble(scanner.nextLine().trim());
        return new Circle(radius);
    }

    // 3. 创建三角形（参数：三边边长）
    private static Triangle createTriangle() {
        System.out.print("请输入三角形的第一条边长: ");
        double a = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入三角形的第二条边长: ");
        double b = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入三角形的第三条边长: ");
        double c = Double.parseDouble(scanner.nextLine().trim());
        return new Triangle(a, b, c);
    }

    // 4. 创建平行四边形（简化：仅底边长 + 侧边长，无需高）
    private static Parallelogram createParallelogram() {
        System.out.print("请输入平行四边形的底边长: ");
        double base = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入平行四边形的侧边长: ");
        double side = Double.parseDouble(scanner.nextLine().trim());
        return new Parallelogram(base, side); // 匹配简化后的Parallelogram类构造方法
    }

    // 5. 创建梯形（简化：仅上底 + 下底 + 高，无需侧边）
    private static Trapezoid createTrapezoid() {
        System.out.print("请输入梯形的上底长度: ");
        double topBase = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入梯形的下底长度: ");
        double bottomBase = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入梯形的高: ");
        double height = Double.parseDouble(scanner.nextLine().trim());
        return new Trapezoid(topBase, bottomBase, height); // 匹配简化后的Trapezoid类构造方法
    }

    // 6. 创建菱形（简化：仅边长，无需对角线）
    private static Rhombus createRhombus() {
        System.out.print("请输入菱形的边长: ");
        double side = Double.parseDouble(scanner.nextLine().trim());
        return new Rhombus(side); // 匹配简化后的Rhombus类构造方法
    }

    // 7. 创建正多边形（参数：边长 + 边数）
    private static RegularPolygon createRegularPolygon() {
        System.out.print("请输入正多边形的边长: ");
        double side = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入正多边形的边数(≥3): ");
        int count = Integer.parseInt(scanner.nextLine().trim());
        return new RegularPolygon(side, count);
    }

    // 8. 创建椭圆（参数：长半轴 + 短半轴）
    private static Ellipse createEllipse() {
        System.out.print("请输入椭圆的长半轴长度: ");
        double major = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入椭圆的短半轴长度: ");
        double minor = Double.parseDouble(scanner.nextLine().trim());
        return new Ellipse(major, minor);
    }

    // 9. 创建扇形（参数：半径 + 圆心角）
    private static Sector createSector() {
        System.out.print("请输入扇形的半径: ");
        double radius = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入扇形的圆心角(度，0<角度≤360): ");
        double angle = Double.parseDouble(scanner.nextLine().trim());
        return new Sector(radius, angle);
    }

    // 10. 创建圆环（参数：内半径 + 外半径）
    private static Annulus createAnnulus() {
        System.out.print("请输入圆环的内半径: ");
        double inner = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("请输入圆环的外半径: ");
        double outer = Double.parseDouble(scanner.nextLine().trim());
        return new Annulus(inner, outer);
    }

    // 11. 创建10边星形（参数：外接圆半径）
    private static DecagonStar createDecagonStar() {
        System.out.print("请输入10边星形的外接圆半径: ");
        double radius = Double.parseDouble(scanner.nextLine().trim());
        return new DecagonStar(radius);
    }

    // 查看所有已添加的图形
    private static void viewAllShapes() {
        System.out.println("\n===== 所有已添加图形 =====");
        List<Shape> shapes = shapeManager.getAllShapes();

        if (shapes.isEmpty()) {
            System.out.println("⚠️  暂无图形，请先通过【1. 添加新图形】功能添加");
            return;
        }

        System.out.println("序号 | 图形详情");
        System.out.println("-----|----------------------------------------");
        for (int i = 0; i < shapes.size(); i++) {
            System.out.printf("%2d   | %s\n", (i + 1), shapes.get(i).getInfo());
        }
    }

    // 查看图形统计信息（数量、总周长、总面积）
    private static void viewStatistics() {
        System.out.println("\n===== 图形统计信息 =====");
        List<Shape> shapes = shapeManager.getAllShapes();

        if (shapes.isEmpty()) {
            System.out.println("⚠️  暂无图形数据，请先添加图形");
            return;
        }

        System.out.println("1. 图形类型数量统计:");
        System.out.println(shapeManager.getShapeCountStatistics());
        System.out.printf("2. 所有图形总周长: %.2f\n", shapeManager.getTotalPerimeter());
        System.out.printf("3. 所有图形总面积: %.2f\n", shapeManager.getTotalArea());
    }
}