package online.andrew2007.labmod.network.v2.prototype;

import org.apache.commons.lang3.NotImplementedException;

public interface ServerLoginNetworkHandlerInjection {
    default void labmod$onCustomC2SPacket(CustomC2SPacket packet) {
        throw new NotImplementedException("Method must be overridden to be used.");
    }
}
