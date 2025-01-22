package online.andrew2007.labmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import online.andrew2007.labmod.mwtConfigSystemProto.ConfigLoader;

public class LabModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientLifecycleEvents.CLIENT_STARTED.register(client -> ConfigLoader.onModInit());
	}
}