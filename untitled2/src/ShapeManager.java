/**
 * 图形管理器类
 * 第13天任务：管理多个图形，提供统计功能
 */
import java.util.ArrayList;
import java.util.List;

public class ShapeManager {
    private List<Shape> shapes;

    public ShapeManager() {
        shapes = new ArrayList<>();
    }

    // 添加图形
    public void addShape(Shape shape) {
        if (shape != null) {
            shapes.add(shape);
        }
    }

    // 移除图形
    public boolean removeShape(Shape shape) {
        return shapes.remove(shape);
    }

    // 获取所有图形
    public List<Shape> getAllShapes() {
        return new ArrayList<>(shapes); // 返回副本，防止外部修改
    }

    // 计算所有图形的总周长
    public double getTotalPerimeter() {
        double total = 0;
        for (Shape shape : shapes) {
            total += shape.calculatePerimeter();
        }
        return new java.math.BigDecimal(total)
                .setScale(2, java.math.RoundingMode.HALF_UP)
                .doubleValue();
    }

    // 计算所有图形的总面积
    public double getTotalArea() {
        double total = 0;
        for (Shape shape : shapes) {
            total += shape.calculateArea();
        }
        return new java.math.BigDecimal(total)
                .setScale(2, java.math.RoundingMode.HALF_UP)
                .doubleValue();
    }

    // 按图形类型统计数量
    public String getShapeCountStatistics() {
        java.util.Map<String, Integer> countMap = new java.util.HashMap<>();

        for (Shape shape : shapes) {
            String name = shape.getShapeName();
            countMap.put(name, countMap.getOrDefault(name, 0) + 1);
        }

        StringBuilder sb = new StringBuilder("图形数量统计:\n");
        for (java.util.Map.Entry<String, Integer> entry : countMap.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("个\n");
        }
        return sb.toString();
    }
}
