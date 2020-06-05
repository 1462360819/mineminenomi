package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenNewCrewScreenPacket;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class SakeBottleItem extends Item
{

	public SakeBottleItem()
	{
		super(new Properties().group(ModCreativeTabs.MISC).maxStackSize(16).food(Foods.APPLE));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack itemStack = player.getHeldItemMainhand();
		IEntityStats props = EntityStatsCapability.get(player);
		if(player.isSneaking() && !props.isInCrew())
		{
			Crew crew = new Crew("", player.getUniqueID());
			ExtendedWorldData worldProps = ExtendedWorldData.get(world);
			worldProps.addCrew(crew);
			itemStack.getOrCreateTag().putBoolean("crewReady", true);
			WyNetwork.sendTo(new SOpenNewCrewScreenPacket(), player);				
		}
		else
		{
			itemStack.getOrCreateTag().putBoolean("crewReady", false);
			player.setActiveHand(hand);
		}
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity)
	{
		if (!world.isRemote && entity instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) entity;

			if (entity.isPotionActive(ModEffects.DRUNK))
			{
				EffectInstance effect = entity.getActivePotionEffect(ModEffects.DRUNK);

				int amp = effect.getAmplifier();
				int duration = effect.getDuration();

				if (amp < 4)
					amp += 1;

				entity.removePotionEffect(ModEffects.DRUNK);
				entity.addPotionEffect(new EffectInstance(ModEffects.DRUNK, duration + 200, amp));
			}
			else
			{
				entity.addPotionEffect(new EffectInstance(ModEffects.DRUNK, 400, 0));
			}

			if (!player.isCreative())
				itemStack.shrink(1);
		}

		return itemStack;
	}

	@Override
	public UseAction getUseAction(ItemStack stack)
	{
		return UseAction.DRINK;
	}
}
