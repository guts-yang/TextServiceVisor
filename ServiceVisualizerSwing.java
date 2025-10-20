package m10d20.project2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 基于Swing的服务可视化平台 - 接口+匿名内部类设计模式的综合应用
 * <p>
 * 本类是"接口+匿名内部类"设计模式的完整实现示例。通过定义统一的Service接口，
 * 并使用匿名内部类实现多种不同的文本处理服务，展示了Java面向对象编程的灵活性。
 * </p>
 */
public class ServiceVisualizerSwing extends JFrame {
    private JTextField inputField;
    private JTextArea resultArea;
    private JComboBox<String> serviceComboBox;
    
    /**
     * 服务接口 - 所有文本处理服务的统一抽象
     * <p>
     * 这个接口定义了文本处理服务必须实现的两个核心方法：
     * 1. execute() - 执行具体的文本处理逻辑
     * 2. getName() - 返回服务的名称标识
     * 通过这个接口，我们可以统一调用不同的服务实现，体现了多态特性。
     * </p>
     */
    interface Service {
          /**
           * 执行文本处理服务
           * @param input 输入的文本数据
           * @return 处理后的文本结果
           */
          String execute(String input);
          
          /**
           * 获取服务的名称标识
           * @return 服务的名称
           */
          String getName();
      }
    
    public ServiceVisualizerSwing() {
        super("服务可视化平台");
        initUI();
    }
    
    private void initUI() {
        // 设置布局
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        
        // 创建标题
        JLabel titleLabel = new JLabel("服务可视化平台");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titleLabel, BorderLayout.NORTH);
        
        // 创建输入面板
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 1, 5, 5));
        
        JLabel inputLabel = new JLabel("输入文本:");
        inputField = new JTextField(20);
        inputField.setToolTipText("请输入要处理的文本...");
        
        JLabel serviceLabel = new JLabel("选择服务:");
        String[] services = {
            "打招呼服务",
            "反转服务",
            "重复服务（2次）",
            "重复服务（3次）",
            "重复服务（5次）",
            "大写转换服务",
            "小写转换服务",
            "字数统计服务",
            "空格去除服务",
            "首字母大写服务",
            "加密服务（简单替换）",
            "随机打乱服务"
        };
        serviceComboBox = new JComboBox<>(services);
        
        JButton executeButton = new JButton("执行服务");
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeService();
            }
        });
        
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(serviceLabel);
        inputPanel.add(serviceComboBox);
        
        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(executeButton);
        
        // 创建结果面板
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout(5, 5));
        
        JLabel resultLabel = new JLabel("服务执行结果:");
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        
        resultPanel.add(resultLabel, BorderLayout.NORTH);
        resultPanel.add(scrollPane, BorderLayout.CENTER);
        
        // 创建中央面板
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(10, 10));
        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);
        centerPanel.add(resultPanel, BorderLayout.SOUTH);
        
        contentPane.add(centerPanel, BorderLayout.CENTER);
        
        // 设置窗口属性
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500); // 设置默认大小
        setLocationRelativeTo(null); // 居中显示
        
        // 添加窗口标题栏图标（可选）
        setTitle("文本服务可视化平台 - 支持多种文本处理功能");
    }
    
    /**
     * 执行选中的服务
     */
    private void executeService() {
        String input = inputField.getText().trim();
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入要处理的文本！", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String selectedService = (String) serviceComboBox.getSelectedItem();
        String result = processWithService(input, selectedService);
        resultArea.setText(result);
    }
    
    /**
     * 获取打招呼服务（使用匿名内部类实现Service接口）
     */
    private Service getGreetingService() {
        return new Service() {
            @Override
            public String execute(String input) {
                return "Hello, " + input + "! Welcome to our service!";
            }
            
            @Override
            public String getName() {
                return "Greeting Service";
            }
        };
    }
    
    /**
     * 获取反转服务（使用匿名内部类实现Service接口）
     */
    private Service getReverseService() {
        return new Service() {
            @Override
            public String execute(String input) {
                StringBuilder reversed = new StringBuilder(input);
                return reversed.reverse().toString();
            }
            
            @Override
            public String getName() {
                return "Reverse Service";
            }
        };
    }
    
    /**
     * 获取重复服务（使用匿名内部类实现Service接口）
     */
    private Service getRepeatService(final int times) {
        return new Service() {
            @Override
            public String execute(String input) {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < times; i++) {
                    result.append(input);
                    if (i < times - 1) {
                        result.append(" ");
                    }
                }
                return result.toString();
            }
            
            @Override
            public String getName() {
                return "Repeat Service (" + times + " times)";
            }
        };
    }
    
    /**
     * 根据选择的服务处理输入文本
     */
    /**
      * 使用选择的服务处理文本
      * <p>
      * 【接口+匿名内部类综合应用示例】
      * 这个方法是本项目中匿名内部类应用的核心展示。针对每种服务类型，
      * 我们都使用匿名内部类实现了Service接口，而不是创建单独的实现类。
      * </p>
      * <p>
      * <b>匿名内部类的底层实现原理：</b><br>
      * - 编译时，Java编译器会为每个匿名内部类生成独立的class文件（如ServiceVisualizerSwing$1.class）<br>
      * - 这些类都实现了Service接口，但逻辑各不相同<br>
      * - 每个匿名内部类都是一个独立的类，但在语法上直接定义在使用的地方<br>
      * </p>
      * @param input 输入文本
      * @param serviceType 服务类型
      * @return 处理后的文本结果
      */
    private String processWithService(String input, String serviceType) {
        switch (serviceType) {
            case "打招呼服务":
                return getGreetingService().execute(input);
            case "反转服务":
                return getReverseService().execute(input);
            case "重复服务（2次）":
                return getRepeatService(2).execute(input);
            case "重复服务（3次）":
                return getRepeatService(3).execute(input);
            case "重复服务（5次）":
                return getRepeatService(5).execute(input);
            case "大写转换服务":
                // 【匿名内部类实现示例1】
                // 此处创建了Service接口的匿名实现，直接在使用的地方定义了具体逻辑
                // 编译后，Java编译器会生成类似ServiceVisualizerSwing$1.class的文件
                return new Service() {
                    @Override
                    public String execute(String in) {
                        return in.toUpperCase(); // 将输入文本转换为大写
                    }
                    
                    @Override
                    public String getName() {
                        return "Uppercase Service";
                    }
                }.execute(input);
                case "小写转换服务":
                // 【匿名内部类实现示例2】
                // 类似地，这里实现了另一个Service接口的匿名内部类
                // 每个匿名内部类都有自己独特的execute()实现逻辑
                return new Service() {
                    @Override
                    public String execute(String in) {
                        return in.toLowerCase(); // 将输入文本转换为小写
                    }
                    
                    @Override
                    public String getName() {
                        return "Lowercase Service";
                    }
                }.execute(input);
                case "字数统计服务":
                // 【匿名内部类实现示例3】
                // 展示了更复杂的服务实现，包含统计逻辑
                // 这体现了匿名内部类可以封装复杂逻辑的能力
                return new Service() {
                    @Override
                    public String execute(String in) {
                        int charCount = in.length();
                        int wordCount = in.trim().isEmpty() ? 0 : in.trim().split("\\s+").length;
                        int lineCount = in.split("\\r?\\n").length;
                        return "字符数: " + charCount + "\n单词数: " + wordCount + "\n行数: " + lineCount;
                    }
                    
                    @Override
                    public String getName() {
                        return "Count Service";
                    }
                }.execute(input);
                case "空格去除服务":
                // 【匿名内部类实现示例4】
                // 展示了使用正则表达式进行文本处理的服务
                // 匿名内部类让我们可以在需要的地方直接实现功能
                return new Service() {
                    @Override
                    public String execute(String in) {
                        return in.replaceAll("\\s+", ""); // 使用正则表达式去除所有空格
                    }
                    
                    @Override
                    public String getName() {
                        return "Remove Spaces Service";
                    }
                }.execute(input);
                case "首字母大写服务":
                // 【匿名内部类实现示例5】
                // 展示了复杂的文本处理逻辑，包含条件判断和字符串操作
                // 这是匿名内部类强大功能的体现 - 可以封装完整的业务逻辑
                return new Service() {
                    @Override
                    public String execute(String in) {
                        if (in == null || in.isEmpty()) {
                            return in;
                        }
                        String[] words = in.split("\\s+");
                        StringBuilder result = new StringBuilder();
                        for (String word : words) {
                            if (!word.isEmpty()) {
                                // 将每个单词的首字母大写，其余字母小写
                                result.append(Character.toUpperCase(word.charAt(0)))
                                      .append(word.substring(1).toLowerCase())
                                      .append(" ");
                            }
                        }
                        return result.toString().trim();
                    }
                    
                    @Override
                    public String getName() {
                        return "Capitalize Service";
                    }
                }.execute(input);
                case "加密服务（简单替换）":
                // 【匿名内部类实现示例6】
                // 展示了字符加密功能，使用简单替换加密算法
                // 体现了接口+匿名内部类的扩展性 - 可以轻松添加新的服务实现
                return new Service() {
                    @Override
                    public String execute(String in) {
                        StringBuilder encrypted = new StringBuilder();
                        // 实现简单的字母替换加密（向后移动3位）
                        // 这是一种经典的凯撒加密算法的变体
                        for (char c : in.toCharArray()) {
                            if (Character.isLetter(c)) {
                                // 根据大小写分别处理
                                char base = Character.isUpperCase(c) ? 'A' : 'a';
                                // 对字母进行替换，保持在字母范围内循环
                                encrypted.append((char) ((c - base + 3) % 26 + base));
                            } else {
                                // 非字母字符保持不变
                                encrypted.append(c);
                            }
                        }
                        return encrypted.toString();
                    }
                    
                    @Override
                    public String getName() {
                        return "Encryption Service";
                    }
                }.execute(input);
                case "随机打乱服务":
                // 【匿名内部类实现示例7】
                // 展示了使用Fisher-Yates洗牌算法对字符进行随机打乱
                // 这是接口+匿名内部类设计模式在算法实现中的典型应用
                return new Service() {
                    @Override
                    public String execute(String in) {
                        // 将字符串转换为字符数组以便操作
                        char[] chars = in.toCharArray();
                        // Fisher-Yates 洗牌算法实现
                        // 这是一种高效且无偏的随机排列算法
                        for (int i = chars.length - 1; i > 0; i--) {
                            // 生成[0,i]范围内的随机索引
                            int j = (int) (Math.random() * (i + 1));
                            // 交换字符
                            char temp = chars[i];
                            chars[i] = chars[j];
                            chars[j] = temp;
                        }
                        // 将打乱后的字符数组转换回字符串
                        return new String(chars);
                    }
                    
                    @Override
                    public String getName() {
                        return "Shuffle Service";
                    }
                }.execute(input);
            default:
                return "未知服务类型";
        }
    }
    
    public static void main(String[] args) {
        // 在事件调度线程中运行GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServiceVisualizerSwing().setVisible(true);
            }
        });
    }
}