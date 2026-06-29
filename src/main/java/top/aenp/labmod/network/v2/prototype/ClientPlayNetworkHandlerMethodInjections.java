package top.aenp.labmod.network.v2.prototype;

import org.apache.commons.lang3.NotImplementedException;

public interface ClientPlayNetworkHandlerMethodInjections {
    default void labmod$onCustomS2C(TestCommonS2CPayload payload) {
        throw new NotImplementedException();
    }
}
