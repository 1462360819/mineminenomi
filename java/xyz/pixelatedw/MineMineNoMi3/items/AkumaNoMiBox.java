package xyz.pixelatedw.MineMineNoMi3.items;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.EnumFruitType;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListDevilFruits;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class AkumaNoMiBox extends Item
{
	
	private int tier;
	private AkumaNoMi[] tier1Fruits = new AkumaNoMi[] 
			{ListDevilFruits.BaneBaneNoMi, ListDevilFruits.SukeSukeNoMi, ListDevilFruits.NoroNoroNoMi, ListDevilFruits.DoruDoruNoMi};
	private AkumaNoMi[] tier2Fruits = new AkumaNoMi[] 
			{ListDevilFruits.OpeOpeNoMi, ListDevilFruits.MokuMokuNoMi, ListDevilFruits.NikyuNikyuNoMi, ListDevilFruits.BomuBomuNoMi, ListDevilFruits.GuraGuraNoMi, ListDevilFruits.KageKageNoMi, ListDevilFruits.DokuDokuNoMi,
			ListDevilFruits.YukiYukiNoMi};
	private AkumaNoMi[] tier3Fruits = new AkumaNoMi[] 
			{ListDevilFruits.MeraMeraNoMi, ListDevilFruits.MaguMaguNoMi, ListDevilFruits.HieHieNoMi, ListDevilFruits.PikaPikaNoMi, ListDevilFruits.OpeOpeNoMi, ListDevilFruits.GoroGoroNoMi, ListDevilFruits.SunaSunaNoMi,
			ListDevilFruits.GasuGasuNoMi};
		
	public AkumaNoMiBox(int tier)
	{
		this.tier = tier;
	} 

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if(!world.isRemote)
		{
			if(player.inventory.hasItemStack(new ItemStack(ListMisc.Key)))
			{
				for(int i = 0; i < player.inventory.mainInventory.length; i++)
				{
					if(player.inventory.getStackInSlot(i) != null && player.inventory.getStackInSlot(i).getItem() == ListMisc.Key)
					{
						player.inventory.decrStackSize(i, 1);
						break;
					}
				}
				WyHelper.removeStackFromInventory(player, itemStack);
				
				player.inventory.addItemStackToInventory(new ItemStack(roulette(), 1, 0));
				
			}
			else
				WyHelper.sendMsgToPlayer(player, "You need a key !");
		}

		return itemStack;
	}
	
	
	private AkumaNoMi roulette()
	{
		Random rand = new Random();
		
		if(rand.nextInt(100) + rand.nextDouble() <= 95)
		{
			if(tier == 1)
			{
				if(rand.nextInt(100) + rand.nextDouble() < 1)
					return tier2Fruits[rand.nextInt(tier2Fruits.length - 1)];				
				else
					return tier1Fruits[rand.nextInt(tier1Fruits.length - 1)];
			}
			else if(tier == 2)
			{
				if(rand.nextInt(100) + rand.nextDouble() < 1)
					return tier3Fruits[rand.nextInt(tier3Fruits.length - 1)];				
				else
					return tier2Fruits[rand.nextInt(tier2Fruits.length - 1)];
			}
			else if(tier == 3)
			{
				return tier3Fruits[rand.nextInt(tier3Fruits.length - 1)];
			}
		}
		
/*		int fruitListId = (int) WyMathHelper.randomWithRange(0, Values.devilfruits.size() - 1);
		
		if(rand.nextInt(100) + rand.nextDouble() <= 95)
		{
			if(tier == 1)
			{
				if( ((AkumaNoMi)Values.devilfruits.get( fruitListId )).getType() == EnumFruitType.PARAMECIA )
					return ((AkumaNoMi)Values.devilfruits.get( fruitListId ));
				else
					return roulette();
			}
			else if(tier == 2)
			{
				if( ((AkumaNoMi)Values.devilfruits.get( fruitListId )).getType() == EnumFruitType.ZOAN )
					return ((AkumaNoMi)Values.devilfruits.get( fruitListId ));
				else
					return roulette();
			}
			else if(tier == 3)
			{
				if( ((AkumaNoMi)Values.devilfruits.get( fruitListId )).getType() == EnumFruitType.LOGIA )
					return ((AkumaNoMi)Values.devilfruits.get( fruitListId ));
				else
					return roulette();
			}
		}*/
		
		return null;
	}

}
