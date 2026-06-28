package top.aenp.labmod.network.v2.prototype;

import org.apache.commons.lang3.NotImplementedException;

public interface ServerLoginNetworkHandlerMethodInjections {
    default void labmod$onTestLoginC2S(TestLoginC2SPayload payload) {
        throw new NotImplementedException();
    }
}
