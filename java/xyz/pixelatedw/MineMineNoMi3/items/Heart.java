package xyz.pixelatedw.MineMineNoMi3.items;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;

public class Heart extends Item
{
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if(world.getEntityByID(itemStack.getTagCompound().getInteger("owner")) != null)
		{
			world.getEntityByID(itemStack.getTagCompound().getInteger("owner")).attackEntityFrom(DamageSource.magic, 5);
			((EntityLivingBase) world.getEntityByID(itemStack.getTagCompound().getInteger("owner"))).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 250, 1));
			((EntityLivingBase) world.getEntityByID(itemStack.getTagCompound().getInteger("owner"))).addPotionEffect(new PotionEffect(Potion.confusion.id, 250, 1));
		}
		else
			WyHelper.removeStackFromInventory(player, itemStack);			
		return itemStack;
	}
	
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		if(player.worldObj.getEntityByID(itemStack.getTagCompound().getInteger("owner")) != null)
		{
			list.add(EnumChatFormatting.GOLD + "[Owner] " + EnumChatFormatting.RESET + player.worldObj.getEntityByID(itemStack.getTagCompound().getInteger("owner")).getCommandSenderName());
			list.add(EnumChatFormatting.GOLD + "[HP] " + EnumChatFormatting.RESET + ((EntityLivingBase) player.worldObj.getEntityByID(itemStack.getTagCompound().getInteger("owner"))).getHealth());
			list.add(EnumChatFormatting.GOLD + "[Location] " + EnumChatFormatting.RESET + (int)player.worldObj.getEntityByID(itemStack.getTagCompound().getInteger("owner")).posX + "X " + (int)player.worldObj.getEntityByID(itemStack.getTagCompound().getInteger("owner")).posY + "Y " + (int)player.worldObj.getEntityByID(itemStack.getTagCompound().getInteger("owner")).posZ +"Z");
		}
		else
			list.add(EnumChatFormatting.GOLD + itemStack.getDisplayName().replace("'s Heart", "") + " is dead !");
	}
	
	public void setHeartOwner(ItemStack itemStack, EntityLivingBase e)
	{
		itemStack.setTagCompound(new NBTTagCompound());
		itemStack.getTagCompound().setInteger("owner", e.getEntityId());
	}
	
}
