package online.andrew2007.labmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.MinecraftVersion;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Objects;

public class LabMod implements ModInitializer {
	public static final String MOD_ID = "labmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ArrayList<String> allMods = new ArrayList<>();
	public static final TrackedData<Boolean> IS_FAKE = DataTracker.registerData(ServerPlayerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	public static final String MOD_VERSION = Objects.requireNonNull(FabricLoader.getInstance().getModContainer(MOD_ID).orElse(null)).getMetadata().getVersion().getFriendlyString();
	public static final String GAME_VERSION = MinecraftVersion.CURRENT.getName();

	@Override
	public void onInitialize() {

	}
}