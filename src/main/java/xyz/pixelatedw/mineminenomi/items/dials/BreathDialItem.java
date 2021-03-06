package xyz.pixelatedw.mineminenomi.items.dials;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

public class BreathDialItem extends BlockItem
{

	public BreathDialItem()
	{
		super(ModBlocks.BREATH_DIAL, new Properties().group(ModCreativeTabs.MISC).maxStackSize(16));
	}

	@Override
	protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state)
	{
		return super.onBlockPlaced(pos, world, player, itemStack, state);
	}
}
