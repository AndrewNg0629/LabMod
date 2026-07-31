package top.aenp.labmod.network.v2.prototype.injections;

import org.apache.commons.lang3.NotImplementedException;
import top.aenp.labmod.config.v2.prototype.NetworkSyncedConfig;
import top.aenp.labmod.network.v2.prototype.test.TestLoginS2CPayload;

public interface ClientLoginNetworkHandlerMethodInjections {
    default void labmod$onTestLoginS2C(TestLoginS2CPayload payload) {
        throw new NotImplementedException();
    }
    default void labmod$onConfigPush(NetworkSyncedConfig config) {
        throw new NotImplementedException();
    }
}
