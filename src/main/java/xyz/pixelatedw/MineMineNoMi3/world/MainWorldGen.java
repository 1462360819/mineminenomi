package xyz.pixelatedw.MineMineNoMi3.world;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.Schematic;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WySchematicHelper;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedWorldData;
import xyz.pixelatedw.MineMineNoMi3.lists.ListExtraStructures;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketWorldData;

public class MainWorldGen implements IWorldGenerator 
{
	//-8290517664781417306
	//1682725888991043500
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch (world.provider.dimensionId)
		{
			case 0: generateSurface(world, random, chunkX * 16, chunkZ * 16);
		}
	}	
	
	private void generateSurface(World world, Random random, int i, int j) 
	{
		this.addOreSpawn(ListMisc.KairosekiOre, world, random, i, j, 16, 16, 4 + random.nextInt(3), 10, 15, 50);	
				
		if(MainConfig.enableShips)
		{
			this.addStructureSpawn(WySchematicHelper.load("marineShip"), world, random, i, j, 1, 1, 1.5 * MainConfig.modifierShipsSpawn);
			this.addStructureSpawn(WySchematicHelper.load("pyrateShip"), world, random, i, j, 1, 1, 1.8 * MainConfig.modifierShipsSpawn);
			this.addStructureSpawn(WySchematicHelper.load("pyrateLargeShip"), world, random, i, j, 1, 1, 1.3 * MainConfig.modifierShipsSpawn);
			this.addStructureSpawn(WySchematicHelper.load("marineLargeShip"), world, random, i, j, 1, 1, 1.4 * MainConfig.modifierShipsSpawn);
		}
		
		this.addStructureSpawn(WySchematicHelper.load("dojo"), world, random, i, j, 1, 1, 25);
		
		this.addDialSpawn(ListMisc.DialEisenBlock, world, random, i, j, 1, 1, 100);
		this.addDialSpawn(ListMisc.DialFireBlock, world, random, i, j, 1, 1, 70);
		this.addDialSpawn(ListMisc.DialAxeBlock, world, random, i, j, 1, 1, 70);
		this.addDialSpawn(ListMisc.DialMilkyBlock, world, random, i, j, 1, 1, 20);
		this.addDialSpawn(ListMisc.DialRejectBlock, world, random, i, j, 1, 1, 10);
		this.addDialSpawn(ListMisc.DialBreathBlock, world, random, i, j, 1, 1, 50);
		this.addDialSpawn(ListMisc.DialFlashBlock, world, random, i, j, 1, 1, 45);
		
		if(MainConfig.enableCamps)
		{
			this.addStructureSpawn(WySchematicHelper.load("marineCamp"), world, random, i, j, 1, 1, 1.2);
		}
		
		if(MainConfig.enableBases)
		{
			this.addStructureSpawn(WySchematicHelper.load("marineLargeBase"), world, random, i, j, 1, 1, 0.2);
		}
	}
	 
	public boolean addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
	{
		int maxPossY = minY + (maxY - 1);
		int diffBtwnMinMaxY = maxY - minY;
		
		for(int x = 0; x < chancesToSpawn; x++)
		{
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(diffBtwnMinMaxY);
			int posZ = blockZPos + random.nextInt(maxZ);
			BiomeGenBase biome = world.getBiomeGenForCoordsBody(posX, posZ);
			
			if(block == ListMisc.KairosekiOre)
			{
				if(biome.biomeName.equals("Ocean") || biome.biomeName.equals("Deep Ocean") || biome.biomeName.equals("Beach"))
				{
					new WorldGenMinable(block, maxVeinSize).generate(world, random, posX, posY, posZ);
					return true;
				}
			}
			//else
			//	new WorldGenMinable(block.getDefaultState(), maxVeinSize).generate(world, random, new BlockPos(posX, posY, posZ));
		}
		
		return false;
	}
	
	
	public boolean addDialSpawn(Block blockToSpawn, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, double rarity)
	{
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= rarity)
		{		
			int posX = blockXPos;
			int posY = random.nextInt(128);
			int posZ = blockZPos;
			BiomeGenBase biome = world.getBiomeGenForCoordsBody(posX, posZ);	
			
			if( (biome.biomeName.equals("Beach") || biome.biomeName.equals("Plains")) && (world.getBlock(posX, posY - 1, posZ) == Blocks.sand || world.getBlock(posX, posY - 1, posZ) == Blocks.grass) && world.getBlock(posX, posY + 1, posZ) == Blocks.air)
			{
				world.setBlock(posX, posY, posZ, blockToSpawn);
				
				if(WyDebug.isDebug())
					System.out.println("" + blockToSpawn.getLocalizedName() + " spawned at /tp @p " + posX + " " + (posY + 1) + " " + posZ);
				
		    	if(!ID.DEV_EARLYACCESS)
		    		WyTelemetry.addStat("spawnedDial_" + WyHelper.getFancyName(blockToSpawn.getLocalizedName()), 1);
		    	
		    	return true;
			}
			
		}
		
		return false;
	}
	
	public boolean addStructureSpawn(Schematic s, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, double rarity)
	{
		if(random.nextInt(100) + random.nextDouble() <= rarity)
		{
			int posX = blockXPos;// + random.nextInt(maxX);
			int posY = random.nextInt(128);
			int posZ = blockZPos;// + random.nextInt(maxZ);			
			BiomeGenBase biome = world.getBiomeGenForCoordsBody(posX, posZ);		

			if(s != null)
			{
				if(s.getName().toLowerCase().contains("ship"))
				{
					if( (biome == BiomeGenBase.ocean || biome == BiomeGenBase.deepOcean ) && checkForShipSpawn(s, world, posX, posY, posZ))
					{
						if(s.getName().equals("marineShip") || s.getName().equals("pyrateShip"))		
						{
							WySchematicHelper.build(s, world, posX, posY, posZ);
							ListExtraStructures.buildSmallShip(posX, posY, posZ, world, s.getName());
						}
						else if(s.getName().equals("marineLargeShip") || s.getName().equals("pyrateLargeShip"))	
						{ 
							WySchematicHelper.build(s, world, posX, posY - 4, posZ);	
							ListExtraStructures.buildLargeShip(posX, posY, posZ, world, s.getName());
						}
						
						if(WyDebug.isDebug())
							System.out.println("" + s.getName() + " spawned at /tp @p " + posX + " " + posY + " " + posZ);
	
				    	if(!ID.DEV_EARLYACCESS )
				    		WyTelemetry.addStat("spawnedStructure_" + s.getName(), 1);
				    	
				    	return true;
					}
				}
				else if(s.getName().toLowerCase().contains("dojo"))
				{
					ExtendedWorldData worldData = ExtendedWorldData.get(world);
					
					if(worldData.getTotalDojosSpawned() < MainConfig.maxDojoSpawn && posY > 50 && world.getBlockLightValue(posX, posY, posZ) > 10)
					{
						if( (biome == BiomeGenBase.beach || biome == BiomeGenBase.plains || biome == BiomeGenBase.desert || biome == BiomeGenBase.savanna
								|| biome == BiomeGenBase.icePlains || biome == BiomeGenBase.swampland || biome == BiomeGenBase.taiga) 
								&& checkCorners(s, world, posX, posY, posZ))
						{		
							WySchematicHelper.build(s, world, posX, posY, posZ);	
							ListExtraStructures.buildDojo(posX, posY, posZ, world);
							worldData.countUpDojoSpawned();
							WyNetworkHelper.sendToAll(new PacketWorldData(worldData));
							
							if(WyDebug.isDebug())
								System.out.println("" + s.getName() + " spawned at /tp @p " + posX + " " + posY + " " + posZ);
							
					    	if(!ID.DEV_EARLYACCESS)
					    		WyTelemetry.addStat("spawnedStructure_" + s.getName(), 1);
					    	
					    	return true;
						}
					}
				}
				else if(s.getName().toLowerCase().contains("camp"))
				{
					if( (biome == BiomeGenBase.plains || biome == BiomeGenBase.taiga || biome == BiomeGenBase.savanna || biome == BiomeGenBase.swampland || biome == BiomeGenBase.forest || biome == BiomeGenBase.birchForest) 
							&& checkCorners(s, world, posX, posY, posZ))
					{
						WySchematicHelper.build(s, world, posX, posY, posZ, Blocks.bedrock);
						//ListExtraStructures.buildSmallShip(posX, posY, posZ, world, s.getName());
						
						if(WyDebug.isDebug())
							System.out.println("" + s.getName() + " spawned at /tp @p " + posX + " " + posY + " " + posZ);
	
				    	if(!ID.DEV_EARLYACCESS )
				    		WyTelemetry.addStat("spawnedStructure_" + s.getName(), 1);
				    	
				    	return true;
					}				
				}
				else if(s.getName().toLowerCase().contains("base"))
				{
					if( (biome == BiomeGenBase.plains || biome == BiomeGenBase.taiga || biome == BiomeGenBase.savanna || biome == BiomeGenBase.swampland || biome == BiomeGenBase.forest || biome == BiomeGenBase.birchForest)
							&& checkCorners(s, world, posX, posY, posZ))
					{
						WySchematicHelper.build(s, world, posX, posY, posZ, Blocks.bedrock);
						//ListExtraStructures.buildSmallShip(posX, posY, posZ, world, s.getName());
						
						if(WyDebug.isDebug())
							System.out.println("" + s.getName() + " spawned at /tp @p " + posX + " " + posY + " " + posZ);
	
				    	if(!ID.DEV_EARLYACCESS )
				    		WyTelemetry.addStat("spawnedStructure_" + s.getName(), 1);
				    	
				    	return true;
					}
				}
			}
			else
			{
				Logger.getGlobal().log(Level.SEVERE, "Some schematic is null, this should never happen !");
			}
		}
		
		return false;
	}

	private boolean checkCorners(Schematic s, World world, int posX, int posY, int posZ)
	{
		boolean corner1 = world.getBlock(posX, posY - 1, posZ) != Blocks.air && world.getBlock(posX, posY - 1, posZ) != Blocks.water;
		boolean corner2 = world.getBlock(posX + s.getWidth(), posY - 1, posZ) != Blocks.air && world.getBlock(posX + s.getWidth(), posY - 1, posZ) != Blocks.water;
		boolean corner3 = world.getBlock(posX, posY - 1, posZ + s.getLength()) != Blocks.air && world.getBlock(posX, posY - 1, posZ + s.getLength()) != Blocks.water;
		boolean corner4 = world.getBlock(posX + s.getWidth(), posY - 1, posZ + s.getLength()) != Blocks.air && world.getBlock(posX + s.getWidth(), posY - 1, posZ + s.getLength()) != Blocks.water;		
		
		if(corner1 && corner2 && corner3 && corner4)
		{
			return true;
		}
		
		return false;
	}
	
	private boolean canReplace(World world, int posX, int posY, int posZ)
	{
		Block at = world.getBlock(posX, posY, posX);
		Material material = at.getMaterial();
		return material.isReplaceable() || material == Material.plants || material == Material.leaves;
	}
	
	private boolean checkForShipSpawn(Schematic s, World world, int posX, int posY, int posZ)
	{
		for(int i = 0; i < s.getWidth(); i++)
		for(int j = 0; j < s.getHeight(); j++)
		for(int k = 0; k < s.getLength(); k++)
		{			
			if(world.getBlock(posX + i, posY + j, posZ + k) == Blocks.air) //|| world.getBlock(posX, posY, posZ) == Blocks.water || world.getBlock(posX + i, posY + j, posZ + k) == Blocks.flowing_water)
			{
				if( world.getBlock(posX, posY - 1, posZ) == Blocks.water || world.getBlock(posX, posY - 1, posZ) == Blocks.flowing_water )
				{
					if( world.getBlock(posX, posY + 2, posZ) == Blocks.air)
						return true;
					else return false;
				}
				else return false;
			}
		}		
		return false;
	}

}
