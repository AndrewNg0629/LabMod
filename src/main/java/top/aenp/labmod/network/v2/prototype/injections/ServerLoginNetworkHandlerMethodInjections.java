package top.aenp.labmod.network.v2.prototype.injections;

import org.apache.commons.lang3.NotImplementedException;
import top.aenp.labmod.network.v2.prototype.payloads.LoginModVersionC2SPayload;
import top.aenp.labmod.network.v2.prototype.payloads.LoginModIdListC2SPayload;

public interface ServerLoginNetworkHandlerMethodInjections {
    default void labmod$onModVersion(LoginModVersionC2SPayload version) {
        throw new NotImplementedException();
    }
    default void labmod$onModIdList(LoginModIdListC2SPayload list) {
        throw new NotImplementedException();
    }
}
