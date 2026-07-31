package top.aenp.labmod.network.v2.prototype.injections;

import org.apache.commons.lang3.NotImplementedException;
import top.aenp.labmod.network.v2.prototype.test.TestLoginC2SPayload;

public interface ServerLoginNetworkHandlerMethodInjections {
    default void labmod$onTestLoginC2S(TestLoginC2SPayload payload) {
        throw new NotImplementedException();
    }
}
