package xyz.pixelatedw.MineMineNoMi3.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;

public class Cola extends ItemFood
{

	public Cola()
	{
		super(0, false);
		this.maxStackSize = 16;  
	} 
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		player.setItemInUse(itemStack, itemUseDuration);
		return itemStack;
	}
	
	
	public void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player) 
	{
		if(!world.isRemote)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			if(props.getRace().equals(ID.RACE_CYBORG))
			{
				if(props.getCola() <= Values.MAX_COLA - 15) 
					props.addCola(15);
				else 
					props.setCola(Values.MAX_COLA);
			}
			
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		}			
	}
}
