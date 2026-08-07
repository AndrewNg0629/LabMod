package top.aenp.labmod.network.v2.prototype.injections;

import org.apache.commons.lang3.NotImplementedException;
import top.aenp.labmod.network.v2.prototype.payloads.NetworkSyncedConfig;
import top.aenp.labmod.network.v2.prototype.test.TestCommonS2CPayload;

public interface ClientPlayNetworkHandlerMethodInjections {
    default void labmod$onCustomS2C(TestCommonS2CPayload payload) {
        throw new NotImplementedException();
    }
    default void labmod$onConfigPush(NetworkSyncedConfig config) {
        throw new NotImplementedException();
    }
}
