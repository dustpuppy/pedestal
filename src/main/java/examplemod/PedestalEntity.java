package examplemod;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;

public class PedestalEntity extends BlockEntity implements Inventory {

    public PedestalEntity() {
 	   super(MainClass.PEDESTAL_BLOCK_ENTITY);
	}

	private final DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);

	public DefaultedList<ItemStack> getItems() {
//		System.out.println("GetStack is called!" + items);
		return items;
	}

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag,items);
    }
 
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag,items);
        return super.toTag(tag);
    }


    public int[] getInvAvailableSlots(Direction var1) {
        // Just return an array of all slots
        int[] result = new int[getItems().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }
 
        return result;
    }
 
    public boolean canInsert(int slot, ItemStack stack, Direction direction) {
        return direction != Direction.UP;
    }
 
    public boolean canExtract(int slot, ItemStack stack, Direction direction) {
        return true;
    }

	@Override
	public void clear() {
        getItems().clear();
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
        for (int i = 0; i < size(); i++) {
            ItemStack stack = getStack(i);
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
	}

	@Override
	public ItemStack getStack(int slot) {

        return getItems().get(slot);
	}

	@Override
	public ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(getItems(), slot, amount);
        if (!result.isEmpty()) {
            markDirty();
        }
        return result;
	}

	@Override
	public ItemStack removeStack(int slot) {
        return Inventories.removeStack(getItems(), slot);
	}

	@Override
	public void setStack(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
	}

	@Override
	public boolean canPlayerUse(PlayerEntity player) {
		return true;
	}


}





