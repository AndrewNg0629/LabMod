package top.aenp.labmod;

import com.google.common.collect.ImmutableSet;
import com.google.gson.*;
import com.mojang.serialization.JsonOps;
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
import top.aenp.labmod.config.v2.prototype.ModConfig;
import top.aenp.labmod.experiment.ReorderedJsonOps;
import top.aenp.labmod.experiment.TestRecords;
import top.aenp.labmod.item.DebuggerItem;
import top.aenp.labmod.network.v2.prototype.*;
import top.aenp.labmod.network.v2.prototype.test.TestCommonS2CPayload;
import top.aenp.labmod.network.v2.prototype.test.TestLoginC2SPayload;
import top.aenp.labmod.network.v2.prototype.test.TestLoginS2CPayload;
import top.aenp.labmod.network.v2.prototype.test.TestPlayC2SPayload;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	public static final ModConfig TEST_CONFIG = new ModConfig(
			true,
			false,
			true,
			new ModConfig.ModIdValidationConfig(false, List.of(), List.of()),
			new ModConfig.Tweaks(
					new ModConfig.ToggleTweaksSection1(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false),
					new ModConfig.ToggleTweaksSection2(false, false, false),
					new ModConfig.ValueTweaks(
							new ModConfig.ValueTweaks.FireballAutoDiscarding(false, 200),
							new ModConfig.ValueTweaks.StuffedShulkerBoxStacking(false, 1),
							new ModConfig.ValueTweaks.ShulkerBoxNesting(true, 2),
							new ModConfig.ValueTweaks.WardenAttributesControl(false, 500.0, 1.0, 30.0, 1.5, 1.2, 18),
							new ModConfig.ValueTweaks.WardenSonicBoomControl(false, true, 10.0, 1.0, 34),
							new ModConfig.ValueTweaks.PlayerDeathItemProtection(false, 12000, false, false)
					)
			),
			new ModConfig.ItemEditorConfig(false, List.of())
	);

	@Override
	public void onInitialize() {
		MythicNetwork.LOGIN_S2C_CODECS.put(TestLoginS2CPayload.ID, TestLoginS2CPayload.CODEC);
		MythicNetwork.LOGIN_C2S_CODECS.put(TestLoginC2SPayload.ID, TestLoginC2SPayload.CODEC);
		MythicNetwork.CUSTOM_PAYLOAD_CODECS.put(TestCommonS2CPayload.ID.id(), TestCommonS2CPayload.CODEC);
		MythicNetwork.CUSTOM_PAYLOAD_CODECS.put(TestPlayC2SPayload.ID.id(), TestPlayC2SPayload.CODEC);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register((itemGroup) -> itemGroup.add(DEBUGGER));
		JsonElement result = ModConfig.CODEC.encodeStart(ReorderedJsonOps.INSTANCE, TEST_CONFIG).result().orElseThrow();
		LabMod.LOGGER.info(LabMod.GSON.toJson(result));
		//TestRecords.test();
		/*
		String content = readResourceFile("test.json");
		JsonElement jsonObject = JsonParser.parseString(content);
		LOGGER.info(jsonObject.toString());
		ModConfig config = ModConfig.CODEC.parse(JsonOps.INSTANCE, jsonObject).getOrThrow();
		JsonElement encodedConfig = ModConfig.CODEC.encodeStart(JsonOps.INSTANCE, config).getOrThrow();
		LOGGER.info(GSON.toJson(encodedConfig));
		List<ModConfig.ItemEditorConfig.ItemEditorUnit> units = config.itemEditorConfig().units();
		for (ModConfig.ItemEditorConfig.ItemEditorUnit unit : units) {
			ItemEditor editor = ItemEditor.getInstance(unit.itemEntry().value());
			editor.loadFromConfigUnit(unit);
			editor.apply();
		}

		 */
	}

	public static Item registerItem(String itemKey, Item item) {
		return Registry.register(Registries.ITEM, Identifier.of(LabMod.MOD_ID, itemKey), item);
	}

	public static String readResourceFile(String name) {
		try(InputStream inputStream = Objects.requireNonNull(LabMod.class.getClassLoader().getResourceAsStream(name));
			Reader reader = new InputStreamReader(inputStream)) {
			StringBuilder stringBuilder = new StringBuilder();
			int readCharCount;
			char[] charBuf = new char[128];
			while ((readCharCount = reader.read(charBuf)) != -1) {
				 stringBuilder.append(charBuf, 0, readCharCount);
			}
			return stringBuilder.toString();
		} catch (IOException e) {
			LabMod.LOGGER.error("Whoops!", e);
			return "";
		}
	}
}