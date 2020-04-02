package xyz.pixelatedw.mineminenomi.abilities.ope;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class RoomAbility extends ContinuousAbility implements IParallelContinuousAbility
{
	public static final Ability INSTANCE = new RoomAbility();
	
	private List<int[]> blockList = new ArrayList<int[]>();

	public RoomAbility()
	{
		super("ROOM", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(1);
		this.setDescription("Creates a spherical space around the user in which they can manipulate anything or use other skills");

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if (this.blockList.isEmpty())
		{
			this.blockList.addAll(AbilityHelper.createEmptySphere(player.world, (int) player.posX, (int) player.posY, (int) player.posZ, 20, ModBlocks.OPE, "air", "foliage", "liquid", "nogrief"));
			player.world.setBlockState(new BlockPos(player.posX, player.posY, player.posZ), ModBlocks.OPE_MID.getDefaultState());
			this.blockList.add(new int[]
				{
					MathHelper.floor(player.posX), MathHelper.floor(player.posY), MathHelper.floor(player.posZ)
				});
		}
		
		return true;
	}
	
	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		for (int[] blockPos : this.blockList)
		{
			Block currentBlock = player.world.getBlockState(new BlockPos(blockPos[0], blockPos[1], blockPos[2])).getBlock();
			if (currentBlock == ModBlocks.OPE || currentBlock == ModBlocks.OPE_MID)
				player.world.setBlockState(new BlockPos(blockPos[0], blockPos[1], blockPos[2]), Blocks.AIR.getDefaultState());
		}
		this.blockList = new ArrayList<int[]>();
		
		return true;
	}
}