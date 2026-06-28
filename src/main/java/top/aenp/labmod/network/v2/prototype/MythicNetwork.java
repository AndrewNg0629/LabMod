package top.aenp.labmod.network.v2.prototype;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.function.Function;

public class MythicNetwork {
    public static final int QUERY_ID = -2147483600;
    public static final HashMap<Identifier, Function<PacketByteBuf, MythicLoginS2CPayload>> LOGIN_S2C_DECODERS = new HashMap<>();
    public static final HashMap<Identifier, Function<PacketByteBuf, MythicLoginC2SPayload>> LOGIN_C2S_DECODERS = new HashMap<>();

    public static void registerLoginS2CPayload(Identifier id, Function<PacketByteBuf, MythicLoginS2CPayload> decoder) {
        LOGIN_S2C_DECODERS.put(id, decoder);
    }
    public static void registerLoginC2SPayload(Identifier id, Function<PacketByteBuf, MythicLoginC2SPayload> decoder) {
        LOGIN_C2S_DECODERS.put(id, decoder);
    }
}
