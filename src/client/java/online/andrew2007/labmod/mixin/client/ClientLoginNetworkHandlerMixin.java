package online.andrew2007.labmod.mixin.client;

import net.minecraft.client.network.ClientLoginNetworkHandler;
import net.minecraft.network.ClientConnection;
import online.andrew2007.labmod.LabMod;
import online.andrew2007.labmod.network.v2.prototype.ClientLoginNetworkHandlerInjection;
import online.andrew2007.labmod.network.v2.prototype.CustomC2SPacket;
import online.andrew2007.labmod.network.v2.prototype.CustomS2CPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientLoginNetworkHandler.class)
public class ClientLoginNetworkHandlerMixin implements ClientLoginNetworkHandlerInjection {
    @Shadow @Final private ClientConnection connection;

    @Override
    public void labmod$onCustomS2CPacket(CustomS2CPacket packet) {
        LabMod.LOGGER.info("Received data: {}", packet.data);
        this.connection.send(new CustomC2SPacket("I hear you."));
    }
}
