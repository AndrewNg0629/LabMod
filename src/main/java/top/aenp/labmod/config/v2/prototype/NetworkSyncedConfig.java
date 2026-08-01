package top.aenp.labmod.config.v2.prototype;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import top.aenp.labmod.network.v2.prototype.injections.ClientLoginNetworkHandlerMethodInjections;
import top.aenp.labmod.network.v2.prototype.injections.ClientPlayNetworkHandlerMethodInjections;
import top.aenp.labmod.network.v2.prototype.payloads.MythicLoginS2CPayload;
import top.aenp.labmod.network.v2.prototype.payloads.MythicPlayS2CPayload;

public record NetworkSyncedConfig(
        boolean largeFireCharge,
        boolean bedIdle,
        boolean suicideCommand,
        ModConfig.Tweaks.ValueTweaks.WardenAttributesControl wardenAttributesControl,
        ModConfig.ItemEditorConfig itemEditorConfig
) implements MythicLoginS2CPayload, MythicPlayS2CPayload {
    public static final Codec<NetworkSyncedConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.BOOL.fieldOf("large_fire_charge").forGetter(NetworkSyncedConfig::largeFireCharge),
                    Codec.BOOL.fieldOf("bed_idle").forGetter(NetworkSyncedConfig::bedIdle),
                    Codec.BOOL.fieldOf("suicide_command").forGetter(NetworkSyncedConfig::suicideCommand),
                    ModConfig.Tweaks.ValueTweaks.WardenAttributesControl.CODEC.fieldOf("warden_attributes_control").forGetter(NetworkSyncedConfig::wardenAttributesControl),
                    ModConfig.ItemEditorConfig.CODEC.fieldOf("item_editor_config").forGetter(NetworkSyncedConfig::itemEditorConfig)
            ).apply(instance, NetworkSyncedConfig::new)
    );

    public static final PacketCodec<PacketByteBuf, NetworkSyncedConfig> PACKET_CODEC = new PacketCodec<>() {
        @Override
        public NetworkSyncedConfig decode(PacketByteBuf buf) {
            NbtElement nbt = buf.readNbt();
            return NetworkSyncedConfig.CODEC.parse(NbtOps.INSTANCE, nbt).getOrThrow();
        }

        @Override
        public void encode(PacketByteBuf buf, NetworkSyncedConfig value) {
            NbtElement nbt = NetworkSyncedConfig.CODEC.encodeStart(NbtOps.INSTANCE, value).getOrThrow();
            buf.writeNbt(nbt);
        }
    };

    public static final Identifier ID = Identifier.of("labmod", "network_synced_config"); //TODO: Change identifier.
    public static final CustomPayload.Id<NetworkSyncedConfig> PAYLOAD_ID = new Id<>(ID);

    @Override
    public Id<? extends CustomPayload> getId() {
        return PAYLOAD_ID;
    }

    @Override
    public Identifier mythicId() {
        return ID;
    }

    @Override
    public void handle(ClientLoginNetworkHandlerMethodInjections handler) {
        handler.labmod$onConfigPush(this);
    }

    @Override
    public void handle(ClientPlayNetworkHandlerMethodInjections handler) {
        handler.labmod$onConfigPush(this);
    }
}
