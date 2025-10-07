import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图形管理器，负责存储、统计所有图形
 */
public class ShapeManager {
    private List<Shape> shapeList; // 存储所有图形的列表

    public ShapeManager() {
        shapeList = new ArrayList<>();
    }

    /**
     * 添加图形到管理器
     * @param shape 要添加的图形
     */
    public void addShape(Shape shape) {
        if (shape != null) {
            shapeList.add(shape);
        }
    }

    /**
     * 获取所有图形
     * @return 图形列表
     */
    public List<Shape> getAllShapes() {
        return new ArrayList<>(shapeList); // 返回副本，避免外部修改
    }

    /**
     * 统计每种图形的数量
     * @return 键：图形名称，值：数量
     */
    public Map<String, Integer> getShapeCount() {
        Map<String, Integer> countMap = new HashMap<>();
        for (Shape shape : shapeList) {
            String name = shape.getName();
            countMap.put(name, countMap.getOrDefault(name, 0) + 1);
        }
        return countMap;
    }

    /**
     * 获取所有图形的总周长
     * @return 总周长（保留2位小数）
     */
    public double getTotalPerimeter() {
        double total = 0;
        for (Shape shape : shapeList) {
            total += shape.calculatePerimeter();
        }
        return new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 获取所有图形的总面积
     * @return 总面积（保留2位小数）
     */
    public double getTotalArea() {
        double total = 0;
        for (Shape shape : shapeList) {
            total += shape.calculateArea();
        }
        return new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 生成统计信息字符串
     * @return 格式化的统计信息
     */
    public String getShapeCountStatistics() {
        Map<String, Integer> countMap = getShapeCount();
        StringBuilder sb = new StringBuilder("图形类型统计:\n");
        if (countMap.isEmpty()) {
            sb.append("  暂无图形数据\n");
            return sb.toString();
        }
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            sb.append(String.format("  - %s: %d个\n", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}