package m10d20.project2;

/**
 * 服务演示类 - 接口+匿名内部类设计模式的核心示例
 * <p>
 * 本类演示了如何通过接口定义抽象行为，并使用匿名内部类实现具体功能。
 * 这种设计模式允许在不创建独立类文件的情况下，动态创建接口实现。
 * </p>
 */
public class ServiceDemo {
    
    /**
     * 获取一个打招呼服务（使用匿名内部类实现Service接口）
     * <p>
     * 【接口+匿名内部类应用示例1】
     * 这里创建了Service接口的第一个匿名实现。编译后，Java编译器会生成名为ServiceDemo$1.class的文件。
     * 匿名内部类的优势在于：无需创建单独的类文件，直接在需要的地方定义实现逻辑。
     * </p>
     * @return 打招呼服务实例（Service接口引用指向匿名内部类实现）
     */
    public static Service getGreetingService() {
        // 返回Service接口的匿名内部类实现
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
     * 获取一个反向服务（使用匿名内部类实现Service接口）
     * <p>
     * 【接口+匿名内部类应用示例2】
     * 这是Service接口的第二个匿名实现。编译后，会生成ServiceDemo$2.class文件。
     * 每个匿名内部类都是独立的实现，拥有自己的逻辑，但都遵循相同的接口约定。
     * </p>
     * @return 反向服务实例
     */
    public static Service getReverseService() {
        return new Service() {
            @Override
            public String execute(String input) {
                // 字符串反转逻辑
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
     * 获取一个重复服务（使用匿名内部类实现Service接口）
     * <p>
     * 【接口+匿名内部类应用示例3 - 带参数捕获】
     * 这个例子展示了匿名内部类的重要特性：访问外部的effectively final变量。
     * times参数被匿名内部类捕获并使用，这体现了闭包的概念。
     * 编译后，会生成ServiceDemo$3.class文件。
     * </p>
     * @param times 重复次数（effectively final，被匿名内部类捕获）
     * @return 重复服务实例
     */
    public static Service getRepeatService(final int times) {
        // 匿名内部类捕获了外部的times参数
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
    
    public static void main(String[] args) {
        System.out.println("===== 服务演示程序 =====");
        
        // 创建并测试打招呼服务
        Service greetingService = getGreetingService();
        System.out.println("\n服务名称: " + greetingService.getName());
        System.out.println("执行结果: " + greetingService.execute("Java Learner"));
        
        // 创建并测试反向服务
        Service reverseService = getReverseService();
        System.out.println("\n服务名称: " + reverseService.getName());
        System.out.println("执行结果: " + reverseService.execute("Hello World"));
        
        // 创建并测试重复服务
        Service repeatService = getRepeatService(3);
        System.out.println("\n服务名称: " + repeatService.getName());
        System.out.println("执行结果: " + repeatService.execute("Java"));
        
        // 【接口+匿名内部类应用示例4 - 内联使用】
        // 直接在表达式中使用匿名内部类，这是最简洁的用法
        // 编译后，会生成ServiceDemo$4.class文件
        System.out.println("\n===== 直接使用匿名内部类 =====");
        String result = new Service() {
            @Override
            public String execute(String input) {
                return input.toUpperCase();
            }
            
            @Override
            public String getName() {
                return "Uppercase Service";
            }
        }.execute("anonymous inner class example");
        
        System.out.println("大写转换结果: " + result);
    }
}