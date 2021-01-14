package examplemod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MainClass implements ModInitializer {
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier("examplemod", "general"),
			() -> new ItemStack(Items.BOOK));
	 

    public static final PedestalBlock PEDESTAL_BLOCK = new PedestalBlock(Block.Settings.of(Material.STONE).strength(4.0f, 4.0f));
    public static BlockEntityType<PedestalEntity> PEDESTAL_BLOCK_ENTITY;
    
        @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("examplemod", "pedestal"), PEDESTAL_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("examplemod", "pedestal"), new BlockItem(PEDESTAL_BLOCK, new Item.Settings().group(MainClass.ITEM_GROUP)));

        PEDESTAL_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "examplemod:pedestal_entity", BlockEntityType.Builder.create(PedestalEntity::new, PEDESTAL_BLOCK).build(null));
        BlockEntityRendererRegistry.INSTANCE.register(PEDESTAL_BLOCK_ENTITY, PedestalRenderer::new);

        
        
    } 
}
