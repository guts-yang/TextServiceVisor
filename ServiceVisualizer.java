package m10d20.project2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * 服务可视化平台，使用JavaFX创建图形用户界面
 */
public class ServiceVisualizer extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 创建主布局
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        
        // 创建标题
        Label titleLabel = new Label("服务可视化平台");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        titleLabel.setPadding(new Insets(10));
        root.setTop(titleLabel);
        
        // 创建输入区域
        VBox inputBox = new VBox(10);
        inputBox.setPadding(new Insets(10));
        
        Label inputLabel = new Label("输入文本:");
        TextField inputField = new TextField();
        inputField.setPromptText("请输入要处理的文本...");
        
        Label serviceLabel = new Label("选择服务:");
        ComboBox<String> serviceComboBox = new ComboBox<>();
        serviceComboBox.getItems().addAll(
            "打招呼服务",
            "反转服务",
            "重复服务（2次）",
            "重复服务（3次）",
            "重复服务（5次）",
            "大写转换服务"
        );
        serviceComboBox.getSelectionModel().selectFirst();
        
        Button executeButton = new Button("执行服务");
        executeButton.setPrefWidth(100);
        
        inputBox.getChildren().addAll(inputLabel, inputField, serviceLabel, serviceComboBox, executeButton);
        
        // 创建结果区域
        VBox resultBox = new VBox(10);
        resultBox.setPadding(new Insets(10));
        
        Label resultLabel = new Label("服务执行结果:");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefHeight(200);
        
        resultBox.getChildren().addAll(resultLabel, resultArea);
        
        // 设置中央布局
        VBox centerBox = new VBox(20);
        centerBox.getChildren().addAll(inputBox, resultBox);
        root.setCenter(centerBox);
        
        // 设置按钮事件处理
        executeButton.setOnAction(e -> {
            String input = inputField.getText();
            if (input.isEmpty()) {
                showAlert("提示", "请输入要处理的文本！");
                return;
            }
            
            String selectedService = serviceComboBox.getSelectionModel().getSelectedItem();
            String result = processWithService(input, selectedService);
            resultArea.setText(result);
        });
        
        // 创建场景并设置舞台
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("服务可视化平台");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * 根据选择的服务处理输入文本
     */
    private String processWithService(String input, String serviceType) {
        switch (serviceType) {
            case "打招呼服务":
                return ServiceDemo.getGreetingService().execute(input);
            case "反转服务":
                return ServiceDemo.getReverseService().execute(input);
            case "重复服务（2次）":
                return ServiceDemo.getRepeatService(2).execute(input);
            case "重复服务（3次）":
                return ServiceDemo.getRepeatService(3).execute(input);
            case "重复服务（5次）":
                return ServiceDemo.getRepeatService(5).execute(input);
            case "大写转换服务":
                // 使用匿名内部类直接实现
                return new Service() {
                    @Override
                    public String execute(String in) {
                        return in.toUpperCase();
                    }
                    
                    @Override
                    public String getName() {
                        return "大写转换服务";
                    }
                }.execute(input);
            default:
                return "未知服务类型";
        }
    }
    
    /**
     * 显示提示对话框
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}