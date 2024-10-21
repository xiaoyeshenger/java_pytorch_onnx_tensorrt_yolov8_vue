package cn.kafuka.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ShellCommandExecutorUtil {

    /**
     * (1).通过Process调用cmd或者shell命令
     */
    public static Map<String,Object> callProcess(String wordDir, List<String> cmdList) {

        Map<String,Object> resultMap = new HashMap<>();


        try {
            //(1).初始化processBuilder
            ProcessBuilder processBuilder = new ProcessBuilder();

            //(2).添加命令的运行目录
            processBuilder.directory(new File(wordDir));

            //(3).添加执行的命令
            processBuilder.command(cmdList);

            //(4).设置cuda TensorRT的环境变量，供Shell脚本中使用
            String env_var = "/usr/local/cuda-11.8/lib64:/gsis_ai/nvidia/TensorRT-8.5.1.7/lib";
            processBuilder.environment().put("LD_LIBRARY_PATH", env_var);
            log.info("step1 ---> env_var: {}, wordDir: {}, shell/cmd: {}",env_var,wordDir,String.join(" ",cmdList));

            //(5).执行命令
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();


            StringBuilder output = new StringBuilder();
            String os = System.getProperty("os.name").toLowerCase();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (os.contains("win")){
                reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            }
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                log.info("step2 ---> 调用shell/cmd成功,code:{},msg:{}",exitCode,output);
            } else {
                log.info("step2 ---> 调用shell/cmd失败,code:{},msg:{}",exitCode,output);
            }
            resultMap.put("code",exitCode);
            resultMap.put("msg",output.toString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return resultMap;
    }


    /**
     * (2).通过Runtime调用cmd或者shell命令
     */
    public static void callRuntime(String wordDir,List<String> cmdList) {

        //(1).设置运行目录
        File workDirFile = new File(wordDir);

        //(2).命令列表
        String[] cmdArr = cmdList.toArray(new String[0]);

        try {
            Process process = Runtime.getRuntime().exec(cmdArr,null,workDirFile);
            process.waitFor();
            int exitValue = process.exitValue();
            if (exitValue != 0) {
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String line;
                while ((line = errorReader.readLine()) != null) {
                    System.out.println(line);
                }
                errorReader.close();
            }else {
                System.out.println("执行返回码:"+exitValue);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
