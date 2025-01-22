package online.andrew2007.labmod;

public class EnvironmentDetection {
    public static final boolean isYarn;
    public static final boolean isPhyClient;

    static {
        isYarn = determineYarn();
        isPhyClient = determinePhyClient();
    }

    private static boolean determineYarn() {
        try {
            Class.forName("net.minecraft.world.World");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static boolean determinePhyClient() {
        String mcClientClasspath = isYarn ? "net.minecraft.client.MinecraftClient" : "net.minecraft.class_310";
        try {
            Class.forName(mcClientClasspath);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}