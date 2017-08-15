package xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles;

import java.util.ArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.HieProjectiles.IceBall;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.HieProjectiles.IceBlockPartisan;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class OpeProjectiles 
{

	public static ArrayList<Object[]> abilitiesClassesArray = new ArrayList();
	
	static
	{
		abilitiesClassesArray.add(new Object[] {GammaKnife.class, ListAttributes.GAMMAKNIFE});
		abilitiesClassesArray.add(new Object[] {CounterShock.class, ListAttributes.COUNTERSHOCK});
	}
	
	public static class CounterShock extends AbilityProjectile
	{
		public CounterShock(World world)
		{super(world);}
		
		public CounterShock(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public CounterShock(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
	}	
	
	public static class GammaKnife extends AbilityProjectile
	{
		public GammaKnife(World world)
		{super(world);}
		
		public GammaKnife(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public GammaKnife(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
	}	
	
	
}
