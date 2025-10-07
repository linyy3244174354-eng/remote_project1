/**
 * 三角形类（参数：三边边长，需满足三角不等式）
 */
public class Triangle extends Shape {
    private double sideA; // 边1
    private double sideB; // 边2
    private double sideC; // 边3

    public Triangle(double sideA, double sideB, double sideC) {
        // 校验边长有效性+三角不等式
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            throw new IllegalArgumentException("边长必须大于0");
        }
        if (!isValidTriangle(sideA, sideB, sideC)) {
            throw new IllegalArgumentException("三边不满足三角不等式（任意两边和>第三边）");
        }
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.name = "三角形";
    }

    /**
     * 验证三角不等式
     */
    private boolean isValidTriangle(double a, double b, double c) {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }

    @Override
    public double calculatePerimeter() {
        return round(sideA + sideB + sideC); // 周长=三边和
    }

    @Override
    public double calculateArea() {
        // 海伦公式：面积=√[p(p-a)(p-b)(p-c)]，p=半周长
        double p = (sideA + sideB + sideC) / 2;
        double area = Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
        return round(area);
    }

    @Override
    public String getInfo() {
        return String.format("%s - 边长1: %.2f, 边长2: %.2f, 边长3: %.2f, 周长: %.2f, 面积: %.2f",
                name, sideA, sideB, sideC, calculatePerimeter(), calculateArea());
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getSideC() {
        return sideC;
    }
}