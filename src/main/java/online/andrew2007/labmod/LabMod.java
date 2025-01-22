package online.andrew2007.labmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import online.andrew2007.labmod.mwtConfigSystemProto.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class LabMod implements ModInitializer {
	public static final String MOD_ID = "labmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		if (!EnvironmentDetection.isPhyClient) {
			ServerLifecycleEvents.SERVER_STARTING.register(server -> ConfigLoader.onModInit());
		}
		LOGGER.info("Environment info: Yarn: {}, PhyClient: {}", EnvironmentDetection.isYarn, EnvironmentDetection.isPhyClient);
	}
}