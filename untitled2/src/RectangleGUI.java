import java.math.BigDecimal;
import java.math.RoundingMode;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 长方形（或正方形）周长面积求解（带GUI界面）
 */
public class RectangleGUI extends JFrame {
    private JTextField lengthField;
    private JTextField widthField;
    private JLabel resultLabel;
    private ShapePanel shapePanel;

    // 图形参数
    private double length;
    private double width;
    private String shapeType;

    public RectangleGUI() {
        // 设置窗口基本属性
        setTitle("长方形/正方形计算器");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 创建输入面板
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        // 创建图形绘制面板
        shapePanel = new ShapePanel();
        add(shapePanel, BorderLayout.CENTER);

        // 创建结果面板
        JPanel resultPanel = new JPanel();
        resultLabel = new JLabel("请输入长度和宽度，然后点击计算");
        resultPanel.add(resultLabel);
        add(resultPanel, BorderLayout.SOUTH);

        // 居中显示
        setLocationRelativeTo(null);
    }

    // 创建输入面板
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // 长度输入
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("长度:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        lengthField = new JTextField(10);
        panel.add(lengthField, gbc);

        // 宽度输入
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("宽度:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        widthField = new JTextField(10);
        panel.add(widthField, gbc);

        // 计算按钮
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton calculateBtn = new JButton("计算并绘制图形");
        calculateBtn.addActionListener(new CalculateListener());
        panel.add(calculateBtn, gbc);

        return panel;
    }

    // 计算按钮监听器
    private class CalculateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // 获取输入值
                length = Double.parseDouble(lengthField.getText().trim());
                width = Double.parseDouble(widthField.getText().trim());

                // 验证输入
                if (length <= 0 || width <= 0) {
                    JOptionPane.showMessageDialog(RectangleGUI.this,
                            "长度和宽度必须为正数!", "输入错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 判断图形类型
                shapeType = determineShape();

                // 计算周长和面积
                double perimeter = calculatePerimeter();
                double area = calculateArea();

                // 更新结果标签
                resultLabel.setText(String.format(
                        "%s - 周长: %.2f, 面积: %.2f  |  长度: %.2f, 宽度: %.2f",
                        shapeType, perimeter, area, length, width));

                // 重绘图形
                shapePanel.repaint();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(RectangleGUI.this,
                        "请输入有效的数字!", "输入错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // 自定义面板用于绘制图形
    private class ShapePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);

            // 如果还没有有效输入，不绘制图形
            if (length <= 0 || width <= 0) {
                g.drawString("请输入长度和宽度并计算", 20, 20);
                return;
            }

            // 计算绘制尺寸（按比例缩放以适应面板）
            int panelWidth = getWidth() - 100;
            int panelHeight = getHeight() - 100;
            double scaleX = panelWidth / Math.max(length, width);
            double scaleY = panelHeight / Math.max(length, width);
            double scale = Math.min(scaleX, scaleY); // 取较小的缩放比例

            int drawWidth = (int)(width * scale);
            int drawHeight = (int)(length * scale);

            // 计算绘制位置（居中）
            int x = (getWidth() - drawWidth) / 2;
            int y = (getHeight() - drawHeight) / 2;

            // 绘制图形
            g.setColor(Color.BLUE);
            g.drawRect(x, y, drawWidth, drawHeight);

            // 填充内部（半透明）
            g.setColor(new Color(0, 0, 255, 30));
            g.fillRect(x, y, drawWidth, drawHeight);

            // 绘制边长标注
            g.setColor(Color.RED);
            Font font = new Font("宋体", Font.BOLD, 12);
            g.setFont(font);

            // 标注宽度
            String widthText = String.format("宽: %.2f", width);
            int widthTextX = x + (drawWidth - g.getFontMetrics().stringWidth(widthText)) / 2;
            g.drawString(widthText, widthTextX, y - 10);

            // 标注长度
            String lengthText = String.format("长: %.2f", length);
            // 旋转90度绘制长度标签
            Graphics2D g2d = (Graphics2D) g;
            g2d.rotate(Math.toRadians(-90), x - 10, y + drawHeight / 2);
            g2d.drawString(lengthText, x - 10, y + drawHeight / 2);
            g2d.rotate(Math.toRadians(90)); // 恢复旋转

            // 标注图形类型
            g.setColor(Color.BLACK);
            String typeText = shapeType;
            int typeTextX = x + (drawWidth - g.getFontMetrics().stringWidth(typeText)) / 2;
            g.drawString(typeText, typeTextX, y + drawHeight / 2);
        }
    }

    // 判断是长方形还是正方形
    public String determineShape() {
        if (Math.abs(length - width) < 0.0001) {
            return "正方形";
        } else {
            return "长方形";
        }
    }

    // 计算周长
    public double calculatePerimeter() {
        double perimeter = 2 * (length + width);
        return new BigDecimal(perimeter).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    // 计算面积
    public double calculateArea() {
        double area = length * width;
        return new BigDecimal(area).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        // 在事件调度线程中运行GUI
        SwingUtilities.invokeLater(() -> {
            new RectangleGUI().setVisible(true);
        });
    }
}
