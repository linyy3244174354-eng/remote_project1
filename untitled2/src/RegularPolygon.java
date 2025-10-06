/**
 * 正多边形类（支持正五边形、正六边形、正八边形等）
 * 第8天任务：实现正多边形的周长和面积计算
 */
public class RegularPolygon extends Shape {
    private double sideLength; // 边长
    private int sideCount;     // 边数
    private String polygonName; // 具体多边形名称

    // 支持的正多边形及其名称
    private static final String[] POLYGON_NAMES = {
            "", "", "", "", "正四边形", "正五边形",
            "正六边形", "正七边形", "正八边形", "正九边形", "正十边形"
    };

    public RegularPolygon(double sideLength, int sideCount) {
        if (sideLength <= 0 || sideCount < 3) {
            throw new IllegalArgumentException("边长必须大于0且边数至少为3");
        }
        this.sideLength = sideLength;
        this.sideCount = sideCount;
        this.polygonName = getPolygonName(sideCount);
        this.shapeName = polygonName;
    }

    // 获取正多边形名称
    private String getPolygonName(int sideCount) {
        if (sideCount >= POLYGON_NAMES.length) {
            return "正" + sideCount + "边形";
        }
        return POLYGON_NAMES[sideCount];
    }

    @Override
    public double calculatePerimeter() {
        return round(sideLength * sideCount);
    }

    @Override
    public double calculateArea() {
        // 正多边形面积公式: (n × s²) / (4 × tan(π/n))
        double area = (sideCount * Math.pow(sideLength, 2))
                / (4 * Math.tan(Math.PI / sideCount));
        return round(area);
    }

    @Override
    public String getInfo() {
        return String.format("%s - 边长: %.2f, 边数: %d, 周长: %.2f, 面积: %.2f",
                shapeName, sideLength, sideCount, calculatePerimeter(), calculateArea());
    }
}
