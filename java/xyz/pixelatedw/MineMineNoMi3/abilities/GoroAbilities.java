package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.GoroProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class GoroAbilities
{

	public static Ability[] abilitiesArray = new Ability[] {new ElThor(), new Raigo(), new Kari(), new Sango()};
	
	public static class ElThor extends Ability
	{
		public ElThor() 
		{
			super(ListAttributes.ELTHOR); 
		}
		
		public void use(EntityPlayer player)
		{
			MovingObjectPosition mop = WyHelper.rayTrace(player);
			
			if(mop != null)
			{
				double i = mop.blockX;
				double j = mop.blockY;
				double k = mop.blockZ;
					
				if(!player.worldObj.isRemote)      		 
					player.worldObj.newExplosion(new EntityLightningBolt(player.worldObj, i, j, k), i, j, k, 6, true, true);
				player.worldObj.spawnEntityInWorld(new EntityLightningBolt(player.worldObj, i, j, k));
				player.worldObj.spawnEntityInWorld(new EntityLightningBolt(player.worldObj, i+1, j, k));
				player.worldObj.spawnEntityInWorld(new EntityLightningBolt(player.worldObj, i, j, k+1));
				player.worldObj.spawnEntityInWorld(new EntityLightningBolt(player.worldObj, i-1, j, k));
				player.worldObj.spawnEntityInWorld(new EntityLightningBolt(player.worldObj, i, j, k-1));
			}
			super.use(player);
		} 
	}
	
	public static class Raigo extends Ability
	{
		public Raigo() 
		{
			super(ListAttributes.RAIGO); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new GoroProjectiles.Raigo(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class Kari extends Ability
	{
		public Kari() 
		{
			super(ListAttributes.KARI); 
		}
	}
	
	public static class Sango extends Ability
	{
		public Sango() 
		{
			super(ListAttributes.SANGO); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new GoroProjectiles.Sango(player.worldObj, player, attr);
			super.use(player);
		} 
	}

}
