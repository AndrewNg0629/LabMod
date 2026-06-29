package top.aenp.labmod.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.common.CustomPayloadS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import top.aenp.labmod.network.v2.prototype.TestCommonS2CPayload;

public class DebuggerItem extends Item {
    public DebuggerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient()) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) user;
            serverPlayerEntity.networkHandler.send(new CustomPayloadS2CPacket(new TestCommonS2CPayload("Hello world!")), null);
        }
        return TypedActionResult.success(stack, world.isClient());
    }
}
