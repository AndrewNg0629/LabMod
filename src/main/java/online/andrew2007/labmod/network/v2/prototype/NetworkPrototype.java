package online.andrew2007.labmod.network.v2.prototype;

import net.minecraft.network.NetworkSide;
import net.minecraft.network.packet.PacketType;
import net.minecraft.util.Identifier;

public class NetworkPrototype {
    public static final PacketType<CustomS2CPacket> CUSTOM_S2C = new PacketType<>(NetworkSide.CLIENTBOUND, Identifier.of("labmod", "custom_s2c"));
    public static final PacketType<CustomC2SPacket> CUSTOM_C2S = new PacketType<>(NetworkSide.SERVERBOUND, Identifier.of("labmod", "custom_c2s"));
}
