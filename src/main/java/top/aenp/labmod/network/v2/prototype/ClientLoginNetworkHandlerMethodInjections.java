package top.aenp.labmod.network.v2.prototype;

import org.apache.commons.lang3.NotImplementedException;

public interface ClientLoginNetworkHandlerMethodInjections {
    default void labmod$onTestLoginS2C(TestLoginS2CPayload payload) {
        throw new NotImplementedException();
    }
}
