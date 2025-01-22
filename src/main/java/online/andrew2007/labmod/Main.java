package online.andrew2007.labmod;

import online.andrew2007.labmod.mwtConfigSystemProto.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main (String[] args) throws Exception {
    }
    public static void configSystemTestA() throws Exception {
        File s1 = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\testConfig\\test_config1.json");
        String s2 = readFile(s1);
        System.out.println(s2);
        ClasspathValidationConfig s3 = ConfigLoader.GSON.fromJson(s2 ,ClasspathValidationConfig.class);
        System.out.println(s3.toString());

        File s4 = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\testConfig\\test_config2.json");
        String s5 = readFile(s4);
        System.out.println(s5);
        BinaryToggleTweaksConfig s6 = ConfigLoader.GSON.fromJson(s5, BinaryToggleTweaksConfig.class);
        System.out.println(s6.toString());

        File s7 = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\testConfig\\test_config3.json");
        String s8 = readFile(s7);
        System.out.println(s8);
        ParamsRequiredTweaksConfig s9 = ConfigLoader.GSON.fromJson(s8, ParamsRequiredTweaksConfig.class);
        System.out.println(s9.toString());
    }
    public static void configSystemTestB() throws Exception {
        File s1 = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\testConfig\\config_proto.json");
        String s2 = readFile(s1);
        System.out.println(s2);
        ModConfig s3 = ConfigLoader.GSON.fromJson(s2, ModConfig.class);
        System.out.println(s3.toString());
    }
    public static String readFile(File fileObject) throws IOException {
        FileReader fileReader = new FileReader(fileObject);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int byteCount;
        char[] charBuffer = new char[128];
        StringBuilder fileIndex = new StringBuilder();
        while ((byteCount = bufferedReader.read(charBuffer)) != -1) {
            fileIndex.append(new String(charBuffer, 0, byteCount));
        }
        bufferedReader.close();
        fileReader.close();
        return fileIndex.toString();
    }
}
