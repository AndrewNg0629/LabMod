package top.aenp.labmod.network.v2.prototype;

import com.google.common.collect.ImmutableSet;
import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.s2c.common.CustomPayloadS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import top.aenp.labmod.LabMod;
import top.aenp.labmod.config.v2.prototype.ConfigManager;
import top.aenp.labmod.config.v2.prototype.ModConfig;
import top.aenp.labmod.network.v2.prototype.payloads.*;
import top.aenp.labmod.network.v2.prototype.payloads.interfaces.MythicLoginC2SPayload;
import top.aenp.labmod.network.v2.prototype.payloads.interfaces.MythicLoginS2CPayload;

import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MythicNetwork {
    public static final MythicNetwork INSTANCE = new MythicNetwork();
    public static final int QUERY_ID = -2147483600;
    public static final ImmutableSet<String> ALL_MODS;
    public static final Set<String> NETWORK_COMPATIBLE_VERSIONS = Set.of(LabMod.MOD_VERSION);
    public final ConcurrentHashMap<Identifier, PacketCodec<PacketByteBuf, ? extends MythicLoginS2CPayload>> LOGIN_S2C_CODECS = new ConcurrentHashMap<>();
    public final ConcurrentHashMap<Identifier, PacketCodec<PacketByteBuf, ? extends MythicLoginC2SPayload>> LOGIN_C2S_CODECS = new ConcurrentHashMap<>();
    public final ConcurrentHashMap<Identifier, PacketCodec<PacketByteBuf, ? extends CustomPayload>> CUSTOM_PAYLOAD_CODECS = new ConcurrentHashMap<>();
    private boolean initialized = false;
    private volatile MinecraftServer currentServer = null;

    static {
        ImmutableSet.Builder<String> modIdSetBuilder = ImmutableSet.builder();
        FabricLoader fabricLoader = FabricLoader.getInstance();
        fabricLoader.getAllMods().forEach(modContainer -> modIdSetBuilder.add(modContainer.getMetadata().getId()));
        ALL_MODS = modIdSetBuilder.build();
    }

    public void initialize() {
        if (!this.initialized) {
            this.LOGIN_S2C_CODECS.put(LoginModVersionS2CPayload.ID, LoginModVersionS2CPayload.CODEC);
            this.LOGIN_S2C_CODECS.put(NetworkSyncedConfig.ID, NetworkSyncedConfig.PACKET_CODEC);
            this.LOGIN_S2C_CODECS.put(LoginModIdRequestS2CPayload.ID, LoginModIdRequestS2CPayload.CODEC);
            this.LOGIN_C2S_CODECS.put(LoginModVersionC2SPayload.ID, LoginModVersionC2SPayload.CODEC);
            this.LOGIN_C2S_CODECS.put(LoginModIdListC2SPayload.ID, LoginModIdListC2SPayload.CODEC);

            this.CUSTOM_PAYLOAD_CODECS.put(NetworkSyncedConfig.ID, NetworkSyncedConfig.PACKET_CODEC);
            this.CUSTOM_PAYLOAD_CODECS.put(BedIdleSignalPayload.ID.id(), BedIdleSignalPayload.CODEC);

            ServerLifecycleEvents.SERVER_STARTED.register(server -> this.currentServer = server);
            ServerLifecycleEvents.SERVER_STOPPING.register(server -> this.currentServer = null);

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
                player.networkHandler.send(new CustomPayloadS2CPacket(syncedConfig), null);
            }
        }
    }

    public enum NegotiationStates {
        VERSION_S2C, VERSION_C2S, MOD_LIST
    }
}
