package com.virginia.db.wine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenAIInterface {
	// OpenAI API URL
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    // 将你的API密钥替换到这里，建议使用环境变量而非硬编码
    private static final String API_KEY = "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";//System.getenv("OPENAI_API_KEY");

    public static void main(String[] args) {
        String prompt = "Generate a Marp-compatible Markdown presentation for a research paper on 'Hierarchical Load Forecast Aggregation for Distribution Transformers'.";
        String response = generateResponse(prompt);
        System.out.println("ChatGPT Response:\n" + response);
    }

    public static String generateResponse(String prompt) {
        try {
            // 创建URL对象
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求头和方法
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // 创建JSON请求体
            String jsonInputString = String.format(
                "{\"model\": \"gpt-4\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}", 
                prompt.replace("\"", "\\\"")
            );
            
//            String jsonInputString = String.format(
//            	    "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}], \"max_tokens\": 100}",
//            	    prompt.replace("\"", "\\\"")
//            	);



            // 发送请求体数据
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

         // 检查响应代码，如果不是200则读取错误流
            int responseCode = connection.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "utf-8"));
            }

            // 读取响应数据
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
