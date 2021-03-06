package xyz.pixelatedw.mineminenomi.events.devilfruits;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class DFUserDeathEvents
{
	// Cloning the player data to the new entity based on the config option
	@SubscribeEvent
	public static void onClonePlayer(PlayerEvent.Clone event)
	{
		if (CommonConfig.instance.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.CUSTOM)
		{
			for (String stat : CommonConfig.instance.getStatsToKeep())
			{
				if (WyHelper.getResourceName(stat).equals("devil_fruit"))
					return;
			}
		}
		else if (CommonConfig.instance.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.FULL)
			return;

		if (event.isWasDeath())
		{
			double droppedChance = WyHelper.randomWithRange(1, 100);
			double chance = WyHelper.randomWithRange(1, 10);
			PlayerEntity original = event.getOriginal();
			IDevilFruit oldDevilFruit = DevilFruitCapability.get(event.getOriginal());
			ExtendedWorldData worldData = ExtendedWorldData.get(original.world);

			if (oldDevilFruit.hasDevilFruit())
			{
				List<ItemEntity> dropList = WyHelper.getEntitiesNear(event.getOriginal().getPosition(), event.getOriginal().world, 30, ItemEntity.class);
				List<PlayerEntity> players = WyHelper.getEntitiesNear(original.getPosition(), original.world, 30, PlayerEntity.class);
				List<VillagerEntity> villagers = WyHelper.getEntitiesNear(original.getPosition(), original.world, 30, VillagerEntity.class);
				List<BlockPos> blockPosList = WyHelper.getNearbyBlocks(original, 30);

				dropList.removeIf(entry -> entry.getItem().getItem() != Items.APPLE);
				players.removeIf(entry -> !entry.inventory.hasItemStack(new ItemStack(Items.APPLE)));
				Set<Item> set = ImmutableSet.of(Items.APPLE);
				Iterator<BlockPos> iterator = blockPosList.iterator();
				while (iterator.hasNext())
				{
					BlockPos pos = iterator.next();
					IInventory inven = ChestBlock.getInventory(original.world.getBlockState(pos), original.world, pos, false);

					if (inven != null)
					{
						if (!inven.hasAny(set))
						{
							iterator.remove();
						}
					}
					else
					{
						iterator.remove();
					}
				}
				villagers.removeIf(entry -> !entry.getVillagerInventory().hasAny(set));
				if (!dropList.isEmpty() && droppedChance <= CommonConfig.instance.getChanceForDroppedAppleReincarnation())
				{
					if(CommonConfig.instance.hasOneFruitPerWorldSimpleLogic())
					{
						String oldFruit = oldDevilFruit.getDevilFruit();
						if(worldData.isDevilFruitInWorld(oldFruit))
						{
							AkumaNoMiItem randomFruit = DevilFruitHelper.rouletteDevilFruits(original.getRNG().nextInt(3));
							boolean isAvailable = DevilFruitHelper.oneFruitPerWorldCheck(original.world, randomFruit);
							
							if(isAvailable)
								dropList.get(0).setItem(new ItemStack(randomFruit));
						}
						else
							dropList.get(0).setItem(DevilFruitHelper.getDevilFruitItem(oldFruit));
					}
					else
						dropList.get(0).setItem(DevilFruitHelper.getDevilFruitItem(oldDevilFruit.getDevilFruit()));
				}
				else if (!players.isEmpty() && chance <= CommonConfig.instance.getChanceForInventoryAppleReincarnation())
				{
					int stackIndex = WyHelper.getIndexOfItemStack(new ItemStack(Items.APPLE), players.get(0).inventory);

					if (stackIndex != -1)
						tryReplaceApple(original, worldData, players.get(0).inventory, stackIndex, oldDevilFruit.getDevilFruit());	
				}
				else if (!villagers.isEmpty() && chance <= CommonConfig.instance.getChanceForInventoryAppleReincarnation())
				{
					int stackIndex = WyHelper.getIndexOfItemStack(new ItemStack(Items.APPLE), villagers.get(0).getVillagerInventory());
					if (stackIndex != -1)
						tryReplaceApple(original, worldData, players.get(0).inventory, stackIndex, oldDevilFruit.getDevilFruit());					
				}
				else if (!blockPosList.isEmpty() && chance <= CommonConfig.instance.getChanceForChestAppleReincarnation())
				{
					BlockState state = original.world.getBlockState(blockPosList.get(0));
					IInventory inven = ChestBlock.getInventory(state, original.world, blockPosList.get(0), false);
					int stackIndex = WyHelper.getIndexOfItemStack(new ItemStack(Items.APPLE), inven);

					if (stackIndex != -1)
						tryReplaceApple(original, worldData, inven, stackIndex, oldDevilFruit.getDevilFruit());
				}
			}
		}
	}
	
	private static void tryReplaceApple(PlayerEntity original, ExtendedWorldData worldData, IInventory inven, int stackIndex, String oldFruit)
	{
		if(CommonConfig.instance.hasOneFruitPerWorldSimpleLogic())
		{
			if(worldData.isDevilFruitInWorld(oldFruit))
			{
				AkumaNoMiItem randomFruit = DevilFruitHelper.rouletteDevilFruits(original.getRNG().nextInt(3));
				boolean isAvailable = DevilFruitHelper.oneFruitPerWorldCheck(original.world, randomFruit);
				
				if(isAvailable)
					inven.setInventorySlotContents(stackIndex, new ItemStack(randomFruit));
			}
			else
				inven.setInventorySlotContents(stackIndex, DevilFruitHelper.getDevilFruitItem(oldFruit));
		}
		else
			inven.setInventorySlotContents(stackIndex, DevilFruitHelper.getDevilFruitItem(oldFruit));	
	}

}
