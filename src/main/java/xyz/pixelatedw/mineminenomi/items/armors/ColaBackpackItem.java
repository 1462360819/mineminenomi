package xyz.pixelatedw.mineminenomi.items.armors;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class ColaBackpackItem extends ArmorItem
{
	public ColaBackpackItem()
	{
		super(ModArmors.COLA_BACKPACK_MATERIAL, EquipmentSlotType.CHEST, (new Item.Properties()).group(ModCreativeTabs.WEAPONS));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	@Nullable
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default)
	{
		A armorModel = (A) new BipedModel(0.1F);

		return armorModel;
	}

	@Override
	@Nullable
	public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type)
	{
		return String.format("%s:textures/models/armor/cola_backpack.png", APIConfig.PROJECT_ID);
	}

	@Override
	public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		if(entity instanceof PlayerEntity && !world.isRemote)
		{
			PlayerEntity player = (PlayerEntity) entity;
			IEntityStats props = EntityStatsCapability.get(player);
			ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
		
			if(props.isCyborg())
			{
				if(!itemStack.hasTag())
					itemStack.setTag(new CompoundNBT());
				
				if(chest.getItem() == ModArmors.COLA_BACKPACK)
				{					
					if(!itemStack.getTag().getBoolean("HasBackpackEquipped"))
					{
						itemStack.getTag().putBoolean("HasBackpackEquipped", true);
					
						props.setMaxCola(props.getMaxCola() + 400);
					}
					
					if(player.ticksExisted % 40 == 0)
					{
						if(props.getCola() + 10 <= props.getMaxCola())
						{
							props.alterCola(10);
							WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), (ServerPlayerEntity) player);
						}
						else
						{
							props.setCola(props.getMaxCola());
							WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), (ServerPlayerEntity) player);
						}
					}
				}
				else
				{
					if(itemStack.getTag().getBoolean("HasBackpackEquipped"))
					{
						itemStack.getTag().putBoolean("HasBackpackEquipped", false);
						
						props.setMaxCola(props.getMaxCola() - 400);

						if (props.getCola() >= props.getMaxCola())
							props.setCola(props.getMaxCola());
						
						WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), (ServerPlayerEntity) player);
					}
				}
			}
		}
	}
}
