package top.aenp.labmod;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.MinecraftVersion;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.aenp.labmod.network.v2.prototype.MythicNetwork;
import top.aenp.labmod.network.v2.prototype.TestLoginC2SPayload;
import top.aenp.labmod.network.v2.prototype.TestLoginS2CPayload;

import java.util.ArrayList;
import java.util.Objects;

public class LabMod implements ModInitializer {
	public static final String MOD_ID = "labmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ArrayList<String> allMods = new ArrayList<>();
	public static final String MOD_VERSION = Objects.requireNonNull(FabricLoader.getInstance().getModContainer(MOD_ID).orElse(null)).getMetadata().getVersion().getFriendlyString();
	public static final String GAME_VERSION = MinecraftVersion.CURRENT.getName();
	public static final ImmutableSet<String> NETWORK_COMPATIBLE_VERSIONS = ImmutableSet.of(MOD_ID);

	public static final Identifier TEST_CHANNEL = Identifier.of("mw", "test0");

	@Override
	public void onInitialize() {
		MythicNetwork.registerLoginS2CPayload(TestLoginS2CPayload.ID, TestLoginS2CPayload::new);
		MythicNetwork.registerLoginC2SPayload(TestLoginC2SPayload.ID, TestLoginC2SPayload::new);
	}
}