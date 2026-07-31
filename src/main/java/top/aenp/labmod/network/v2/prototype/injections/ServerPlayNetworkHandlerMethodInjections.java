package top.aenp.labmod.network.v2.prototype.injections;

import org.apache.commons.lang3.NotImplementedException;
import top.aenp.labmod.network.v2.prototype.test.TestPlayC2SPayload;

public interface ServerPlayNetworkHandlerMethodInjections {
    default void labmod$onCustomC2S(TestPlayC2SPayload payload) {
        throw new NotImplementedException();
    }
}
