package examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class PedestalBlock<EntityContext> extends Block implements BlockEntityProvider {
	public PedestalBlock(Settings settings) {
		super(settings);
	}

	@Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
	
        if (world.isClient) return ActionResult.PASS;
        Inventory blockEntity = (Inventory) world.getBlockEntity(pos);
 
 
        if (!player.getStackInHand(hand).isEmpty()) {
            // Check what is the first open slot and put an item from the player's hand there
            if (blockEntity.getStack(0).isEmpty()) {
                // Put the stack the player is holding into the inventory
                blockEntity.setStack(0, player.getStackInHand(hand).split(1));
				return ActionResult.SUCCESS;
            }
        } else {
            // Find the first slot that has an item and give it to the player
           if (!blockEntity.getStack(0).isEmpty()) {
               player.inventory.offerOrDrop(world, blockEntity.getStack(0));
               blockEntity.removeStack(0);
           }
       }

       return ActionResult.PASS;
   }
 


	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new PedestalEntity();
	}



}
