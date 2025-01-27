package online.andrew2007.labmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.server.network.ServerPlayerEntity;
import online.andrew2007.labmod.mwtConfigSystemProto.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class LabMod implements ModInitializer {
	public static final String MOD_ID = "labmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ArrayList<String> allMods = new ArrayList<>();
	public static final TrackedData<Boolean> IS_FAKE = DataTracker.registerData(ServerPlayerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	public static final Class<?> carpetFPClass;

	static {
		Class<?> gotFPClass = null;
		try {
			gotFPClass = Class.forName("carpet.patches.EntityPlayerMPFake");
		} catch (ClassNotFoundException ignored) {}
		carpetFPClass = gotFPClass;
	}

	public static boolean determineFake(ServerPlayerEntity serverPlayerEntity) {
		if (carpetFPClass == null) {
			return false;
		} else {
			return carpetFPClass.isAssignableFrom(serverPlayerEntity.getClass());
		}
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		if (!EnvironmentDetection.isPhyClient) {
			ServerLifecycleEvents.SERVER_STARTING.register(server -> ConfigLoader.onModInit());
		}
		LOGGER.info("Environment info: Yarn: {}, PhyClient: {}", EnvironmentDetection.isYarn, EnvironmentDetection.isPhyClient);
		test0();
	}

	public static void test0() {
		FabricLoader loader = FabricLoader.getInstance();
		loader.getAllMods().forEach(modContainer -> allMods.add(modContainer.getMetadata().getId()));
	}
}