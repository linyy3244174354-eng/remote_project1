import java.math.BigDecimal;
import java.math.RoundingMode;
import java.awt.*;

/**
 * 正多边形抽象基类，定义通用属性和方法
 */
public abstract class RegularPolygon {
    protected double sideLength; // 边长
    protected int sides; // 边数
    protected String name; // 图形名称

    /**
     * 构造方法
     * @param sideLength 边长
     * @param sides 边数
     * @param name 图形名称
     * @throws IllegalArgumentException 当边长小于等于0时抛出
     */
    public RegularPolygon(double sideLength, int sides, String name) {
        if (sideLength <= 0) {
            throw new IllegalArgumentException("边长必须大于0");
        }
        if (sides < 3) {
            throw new IllegalArgumentException("边数必须大于等于3");
        }
        this.sideLength = sideLength;
        this.sides = sides;
        this.name = name;
    }

    /**
     * 计算周长
     * @return 周长（保留两位小数）
     */
    public double calculatePerimeter() {
        double perimeter = sideLength * sides;
        return new BigDecimal(perimeter).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 计算面积（抽象方法，由子类实现）
     * @return 面积（保留两位小数）
     */
    public abstract double calculateArea();

    /**
     * 绘制图形（在GUI中显示）
     * @param g 绘图上下文
     * @param panelWidth 面板宽度
     * @param panelHeight 面板高度
     */
    public void draw(Graphics g, int panelWidth, int panelHeight) {
        // 计算绘制参数
        int centerX = panelWidth / 2;
        int centerY = panelHeight / 2;
        double radius = calculateCircumradius(); // 外接圆半径

        // 缩放以适应面板
        double scale = Math.min(panelWidth, panelHeight) * 0.4 / radius;
        int scaledRadius = (int)(radius * scale);

        // 计算多边形顶点坐标
        int[] xPoints = new int[sides];
        int[] yPoints = new int[sides];

        for (int i = 0; i < sides; i++) {
            // 计算每个顶点的角度（弧度）
            double angle = 2 * Math.PI * i / sides - Math.PI / 2;
            xPoints[i] = centerX + (int)(scaledRadius * Math.cos(angle));
            yPoints[i] = centerY + (int)(scaledRadius * Math.sin(angle));
        }

        // 绘制多边形
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawPolygon(xPoints, yPoints, sides);

        // 填充多边形（半透明）
        g2d.setColor(new Color(0, 0, 255, 30));
        g2d.fillPolygon(xPoints, yPoints, sides);

        // 标注边长
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("宋体", Font.PLAIN, 12));
        drawSideLabel(g2d, xPoints, yPoints, 0);

        // 标注图形名称
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("宋体", Font.BOLD, 14));
        g2d.drawString(name + " (边长: " + String.format("%.2f", sideLength) + ")",
                centerX - 60, centerY);
    }

    /**
     * 绘制一条边的长度标注
     */
    private void drawSideLabel(Graphics2D g2d, int[] xPoints, int[] yPoints, int sideIndex) {
        int x1 = xPoints[sideIndex];
        int y1 = yPoints[sideIndex];
        int x2 = xPoints[(sideIndex + 1) % sides];
        int y2 = yPoints[(sideIndex + 1) % sides];

        // 计算边的中点
        int midX = (x1 + x2) / 2;
        int midY = (y1 + y2) / 2;

        // 绘制长度文本
        g2d.drawString(String.format("%.2f", sideLength), midX - 15, midY - 5);
    }

    /**
     * 计算外接圆半径
     * @return 外接圆半径
     */
    protected double calculateCircumradius() {
        return sideLength / (2 * Math.sin(Math.PI / sides));
    }

    // Getter和Setter方法
    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        if (sideLength <= 0) {
            throw new IllegalArgumentException("边长必须大于0");
        }
        this.sideLength = sideLength;
    }

    public int getSides() {
        return sides;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s - 边长: %.2f, 边数: %d, 周长: %.2f, 面积: %.2f",
                name, sideLength, sides, calculatePerimeter(), calculateArea());
    }
}
