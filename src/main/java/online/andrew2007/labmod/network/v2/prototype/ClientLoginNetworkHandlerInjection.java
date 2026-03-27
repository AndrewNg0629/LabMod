package online.andrew2007.labmod.network.v2.prototype;

import org.apache.commons.lang3.NotImplementedException;

public interface ClientLoginNetworkHandlerInjection {
    default void labmod$onCustomS2CPacket(CustomS2CPacket packet) {
        throw new NotImplementedException("Method must be overridden to be used.");
    }
}
