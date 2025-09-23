import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * 正多边形GUI展示程序
 */
public class PolygonGUI extends JFrame {
    private JComboBox<String> polygonType;
    private JTextField sideLengthField;
    private JLabel resultLabel;
    private DrawingPanel drawingPanel;
    private RegularPolygon currentPolygon;

    public PolygonGUI() {
        setTitle("正多边形计算器");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 创建输入面板
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        inputPanel.setBackground(new Color(240, 240, 240));

        inputPanel.add(new JLabel("选择正多边形:"));
        polygonType = new JComboBox<>(new String[]{"正五边形", "正六边形", "正八边形"});
        inputPanel.add(polygonType);

        inputPanel.add(new JLabel("边长:"));
        sideLengthField = new JTextField(10);
        inputPanel.add(sideLengthField);

        JButton calculateBtn = new JButton("计算并绘制");
        calculateBtn.addActionListener(new CalculateListener());
        inputPanel.add(calculateBtn);

        add(inputPanel, BorderLayout.NORTH);

        // 创建绘图面板
        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        // 创建结果面板
        resultLabel = new JLabel("请输入边长并计算", SwingConstants.CENTER);
        resultLabel.setFont(new Font("宋体", Font.PLAIN, 14));
        add(resultLabel, BorderLayout.SOUTH);
    }

    // 绘图面板内部类
    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);

            if (currentPolygon != null) {
                currentPolygon.draw(g, getWidth(), getHeight());
            } else {
                g.drawString("请输入参数并点击计算", getWidth()/2 - 60, getHeight()/2);
            }
        }
    }

    // 计算按钮监听器
    private class CalculateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double sideLength = Double.parseDouble(sideLengthField.getText().trim());

                if (sideLength <= 0) {
                    JOptionPane.showMessageDialog(PolygonGUI.this,
                            "边长必须大于0", "输入错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 根据选择创建相应的正多边形
                String selected = (String) polygonType.getSelectedItem();
                switch (selected) {
                    case "正五边形":
                        currentPolygon = new RegularPentagon(sideLength);
                        break;
                    case "正六边形":
                        currentPolygon = new RegularHexagon(sideLength);
                        break;
                    case "正八边形":
                        currentPolygon = new RegularOctagon(sideLength);
                        break;
                }

                // 更新结果标签
                resultLabel.setText(currentPolygon.toString());

                // 重绘图形
                drawingPanel.repaint();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(PolygonGUI.this,
                        "请输入有效的数字", "输入错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PolygonGUI().setVisible(true);
        });
    }
}
