package top.aenp.labmod.network.v2.prototype.injections;

import org.apache.commons.lang3.NotImplementedException;
import top.aenp.labmod.network.v2.prototype.payloads.NetworkSyncedConfig;
import top.aenp.labmod.network.v2.prototype.payloads.LoginModVersionS2CPayload;

public interface ClientLoginNetworkHandlerMethodInjections {
    default void labmod$onConfigPush(NetworkSyncedConfig config) {
        throw new NotImplementedException();
    }
    default void labmod$onModVersion(LoginModVersionS2CPayload version) {
        throw new NotImplementedException();
    }
    default void labmod$onModIdRequest() {
        throw new NotImplementedException();
    }
}
