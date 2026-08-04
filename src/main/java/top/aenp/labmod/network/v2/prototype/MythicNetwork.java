package top.aenp.labmod.network.v2.prototype;

import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import top.aenp.labmod.config.v2.prototype.ConfigManager;
import top.aenp.labmod.config.v2.prototype.ModConfig;
import top.aenp.labmod.config.v2.prototype.NetworkSyncedConfig;
import top.aenp.labmod.network.v2.prototype.payloads.MythicLoginC2SPayload;
import top.aenp.labmod.network.v2.prototype.payloads.MythicLoginS2CPayload;
import top.aenp.labmod.network.v2.prototype.test.TestCommonS2CPayload;
import top.aenp.labmod.network.v2.prototype.test.TestLoginC2SPayload;
import top.aenp.labmod.network.v2.prototype.test.TestLoginS2CPayload;
import top.aenp.labmod.network.v2.prototype.test.TestPlayC2SPayload;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class MythicNetwork {
    public static final MythicNetwork INSTANCE = new MythicNetwork();
    public static final int QUERY_ID = -2147483600;
    public final ConcurrentHashMap<Identifier, PacketCodec<PacketByteBuf, ? extends MythicLoginS2CPayload>> LOGIN_S2C_CODECS = new ConcurrentHashMap<>();
    public final ConcurrentHashMap<Identifier, PacketCodec<PacketByteBuf, ? extends MythicLoginC2SPayload>> LOGIN_C2S_CODECS = new ConcurrentHashMap<>();
    public final ConcurrentHashMap<Identifier, PacketCodec<PacketByteBuf, ? extends CustomPayload>> CUSTOM_PAYLOAD_CODECS = new ConcurrentHashMap<>();
    private boolean initialized = false;
    private volatile MinecraftServer currentServer = null;

    public void initialize() {
        if (!this.initialized) {
            this.LOGIN_S2C_CODECS.put(TestLoginS2CPayload.ID, TestLoginS2CPayload.CODEC);
            this.LOGIN_C2S_CODECS.put(TestLoginC2SPayload.ID, TestLoginC2SPayload.CODEC);
            this.CUSTOM_PAYLOAD_CODECS.put(TestCommonS2CPayload.ID.id(), TestCommonS2CPayload.CODEC);
            this.CUSTOM_PAYLOAD_CODECS.put(TestPlayC2SPayload.ID.id(), TestPlayC2SPayload.CODEC);
            ServerLifecycleEvents.SERVER_STARTED.register(server -> {
                this.currentServer = server;
            });
            ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
                this.currentServer = null;
            });
            this.initialized = true;
        } else {
            throw new IllegalStateException();
        }
    }

    public void pushConfigDuringPlay() {
        if (this.currentServer != null) {
            LinkedList<ServerPlayerEntity> players = new LinkedList<>(currentServer.getPlayerManager().getPlayerList());
            String hostPlayerName;
            if (!this.currentServer.isDedicated()) {
                GameProfile hostProfile = this.currentServer.getHostProfile();
                if (hostProfile != null) {
                    hostPlayerName = hostProfile.getName();
                } else {
                    hostPlayerName = null;
                }
            } else {
                hostPlayerName = null;
            }
            players.removeIf(player -> player.getGameProfile().getName().equalsIgnoreCase(hostPlayerName));
            ModConfig modConfig = ConfigManager.getConfig();
            NetworkSyncedConfig syncedConfig = new NetworkSyncedConfig(modConfig.tweaks().syncedToggleTweaks1(), modConfig.tweaks().valueTweaks().wardenAttributesControl(), modConfig.itemEditorConfig());
            for (ServerPlayerEntity player : players) {
                //TODO: Add config push logic.
            }
        }
    }
}
