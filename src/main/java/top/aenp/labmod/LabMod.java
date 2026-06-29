package top.aenp.labmod;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.MinecraftVersion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.aenp.labmod.item.DebuggerItem;
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

	public static final Item DEBUGGER = registerItem("debugger", new DebuggerItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));

	@Override
	public void onInitialize() {
		MythicNetwork.LOGIN_S2C_DECODERS.put(TestLoginS2CPayload.ID, TestLoginS2CPayload::new);
		MythicNetwork.LOGIN_C2S_DECODERS.put(TestLoginC2SPayload.ID, TestLoginC2SPayload::new);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register((itemGroup) -> itemGroup.add(DEBUGGER));
		try {
			Class.forName("net.minecraft.network.packet.s2c.common.CustomPayloadS2CPacket");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Item registerItem(String itemKey, Item item) {
		return Registry.register(Registries.ITEM, Identifier.of(LabMod.MOD_ID, itemKey), item);
	}
}