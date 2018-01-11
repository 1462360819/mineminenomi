package xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles;

import java.util.ArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.DokuProjectiles.Hydra;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class GasuProjectiles 
{

	public static ArrayList<Object[]> abilitiesClassesArray = new ArrayList();
	
	static
	{
		abilitiesClassesArray.add(new Object[] {Gastille.class, ListAttributes.GASTILLE});
		abilitiesClassesArray.add(new Object[] {GasRobe.class, ListAttributes.GASROBE});
	}
	
	public static class Gastille extends AbilityProjectile
	{
		public Gastille(World world)
		{super(world);}
		
		public Gastille(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public Gastille(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}	
	}
	
	public static class GasRobe extends AbilityProjectile
	{
		public GasRobe(World world)
		{super(world);}
		
		public GasRobe(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public GasRobe(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}	
	}
	
	
}
