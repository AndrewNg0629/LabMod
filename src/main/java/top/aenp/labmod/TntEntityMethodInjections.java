package top.aenp.labmod;

import org.apache.commons.lang3.NotImplementedException;

public interface TntEntityMethodInjections {
    default int labMod$getRemainingChainLength() {
        throw new NotImplementedException();
    }
    default void labMod$setRemainingChainLength(int chain) {
        throw new NotImplementedException();
    }
    default boolean labmod$canIgnite() {
        throw new NotImplementedException();
    }
    default void labmod$onIgnition() {
        throw new NotImplementedException();
    }
}
