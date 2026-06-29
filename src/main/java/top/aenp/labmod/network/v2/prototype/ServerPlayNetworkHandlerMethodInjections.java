package top.aenp.labmod.network.v2.prototype;

import org.apache.commons.lang3.NotImplementedException;

public interface ServerPlayNetworkHandlerMethodInjections {
    default void labmod$onCustomC2S(TestPlayC2SPayload payload) {
        throw new NotImplementedException();
    }
}
