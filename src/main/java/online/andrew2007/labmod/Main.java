package online.andrew2007.labmod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.MathHelper;
import online.andrew2007.labmod.mwtConfigSystemProto.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(ClasspathValidationConfig.class, new ClasspathValidationConfig.Deserializer())
            .registerTypeAdapter(BinaryToggleTweaksConfig.class, new BinaryToggleTweaksConfig.Deserializer())
            .registerTypeAdapter(ParamsRequiredTweaksConfig.class, new ParamsRequiredTweaksConfig.Deserializer())
            .registerTypeAdapter(ParamsRequiredTweaksConfig.AutoDiscardingFireBallConfig.class, new ParamsRequiredTweaksConfig.AutoDiscardingFireBallConfig.Deserializer())
            .registerTypeAdapter(ParamsRequiredTweaksConfig.StuffedShulkerBoxStackLimitConfig.class, new ParamsRequiredTweaksConfig.StuffedShulkerBoxStackLimitConfig.Deserializer())
            .registerTypeAdapter(ParamsRequiredTweaksConfig.ShulkerBoxNestingLimitConfig.class, new ParamsRequiredTweaksConfig.ShulkerBoxNestingLimitConfig.Deserializer())
            .registerTypeAdapter(ParamsRequiredTweaksConfig.WardenAttributesWeakeningConfig.class, new ParamsRequiredTweaksConfig.WardenAttributesWeakeningConfig.Deserializer())
            .registerTypeAdapter(ParamsRequiredTweaksConfig.WardenSonicBoomWeakeningConfig.class, new ParamsRequiredTweaksConfig.WardenSonicBoomWeakeningConfig.Deserializer())
            .registerTypeAdapter(ModConfig.class, new ModConfig.Deserializer())
            .setPrettyPrinting()
            .create();
    public static void main (String[] args) throws Exception {
        System.out.println(MathHelper.clamp(300, 0, 255));
        System.out.println(MathHelper.clamp(-1, 0, 255));
    }
    public static void configSystemTestA() throws Exception {
        File s1 = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\testConfig\\test_config1.json");
        String s2 = readFile(s1);
        System.out.println(s2);
        ClasspathValidationConfig s3 = GSON.fromJson(s2 ,ClasspathValidationConfig.class);
        System.out.println(s3.toString());

        File s4 = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\testConfig\\test_config2.json");
        String s5 = readFile(s4);
        System.out.println(s5);
        BinaryToggleTweaksConfig s6 = GSON.fromJson(s5, BinaryToggleTweaksConfig.class);
        System.out.println(s6.toString());

        File s7 = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\testConfig\\test_config3.json");
        String s8 = readFile(s7);
        System.out.println(s8);
        ParamsRequiredTweaksConfig s9 = GSON.fromJson(s8, ParamsRequiredTweaksConfig.class);
        System.out.println(s9.toString());
    }
    public static void configSystemTestB() throws Exception {
        File s1 = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\testConfig\\config_proto.json");
        String s2 = readFile(s1);
        System.out.println(s2);
        ModConfig s3 = GSON.fromJson(s2, ModConfig.class);
        System.out.println(s3.toString());
    }
    public static String readFile(File fileObject) throws IOException {
        FileReader fileReader = new FileReader(fileObject);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int byteCount;
        char[] charBuffer = new char[256];
        StringBuilder fileIndex = new StringBuilder();
        while ((byteCount = bufferedReader.read(charBuffer)) != -1) {
            fileIndex.append(new String(charBuffer, 0, byteCount));
        }
        fileReader.close();
        bufferedReader.close();
        return fileIndex.toString();
    }
}
