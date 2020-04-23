package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.Blocks;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class CoreBlockProtectionRule extends BlockProtectionRule
{
	public static final CoreBlockProtectionRule INSTANCE = new CoreBlockProtectionRule();

	public CoreBlockProtectionRule()
	{

		this.addApprovedBlocks(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.MYCELIUM, Blocks.GRAVEL, Blocks.SAND,
			Blocks.RED_SAND, Blocks.CLAY, Blocks.STONE, Blocks.COBBLESTONE, Blocks.SMOOTH_STONE, Blocks.GRANITE, Blocks.POLISHED_GRANITE,
			Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.OBSIDIAN, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.MOSSY_COBBLESTONE,
			Blocks.BRICKS, Blocks.BONE_BLOCK, Blocks.SANDSTONE, Blocks.TERRACOTTA, Blocks.SNOW, Blocks.SNOW_BLOCK, Blocks.ICE, Blocks.PACKED_ICE,
			Blocks.BLUE_ICE, Blocks.SEA_LANTERN, Blocks.PRISMARINE, Blocks.PRISMARINE_BRICKS, Blocks.DARK_PRISMARINE, Blocks.FARMLAND,
			Blocks.GRASS_PATH, Blocks.GLASS, Blocks.BOOKSHELF, Blocks.STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS,
			Blocks.CHISELED_STONE_BRICKS, Blocks.SPONGE, Blocks.SLIME_BLOCK, Blocks.CARVED_PUMPKIN, Blocks.HAY_BLOCK, Blocks.DRIED_KELP_BLOCK,
			Blocks.ACACIA_LOG, Blocks.BIRCH_LOG, Blocks.DARK_OAK_LOG, Blocks.JUNGLE_LOG, Blocks.OAK_LOG, Blocks.SPRUCE_LOG,
			Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_ACACIA_WOOD, Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_BIRCH_LOG,
			Blocks.STRIPPED_BIRCH_WOOD, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.STRIPPED_JUNGLE_LOG,
			Blocks.STRIPPED_JUNGLE_WOOD, Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_OAK_WOOD, Blocks.STRIPPED_SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_WOOD,
			Blocks.ACACIA_WOOD, Blocks.BIRCH_WOOD, Blocks.DARK_OAK_WOOD, Blocks.JUNGLE_WOOD, Blocks.OAK_WOOD, Blocks.SPRUCE_WOOD,
			Blocks.ACACIA_PLANKS, Blocks.BIRCH_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS,
			Blocks.FROSTED_ICE, Blocks.ACACIA_SLAB, Blocks.ANDESITE_SLAB, Blocks.BIRCH_SLAB, Blocks.BIRCH_SLAB, Blocks.BRICK_SLAB,
			Blocks.COBBLESTONE_SLAB, Blocks.DARK_OAK_SLAB, Blocks.DIORITE_SLAB, Blocks.DARK_PRISMARINE_SLAB, Blocks.END_STONE_BRICK_SLAB,
			Blocks.GRANITE_SLAB, Blocks.JUNGLE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB,
			Blocks.OAK_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.POLISHED_ANDESITE_SLAB, Blocks.POLISHED_DIORITE_SLAB, Blocks.POLISHED_GRANITE_SLAB,
			Blocks.PRISMARINE_BRICK_SLAB, Blocks.PRISMARINE_SLAB, Blocks.PURPUR_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_NETHER_BRICK_SLAB,
			Blocks.RED_SANDSTONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB,
			Blocks.SMOOTH_STONE_SLAB, Blocks.SPRUCE_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.STONE_SLAB, Blocks.ACACIA_STAIRS, Blocks.ANDESITE_STAIRS,
			Blocks.BIRCH_STAIRS, Blocks.BIRCH_STAIRS, Blocks.BRICK_STAIRS, Blocks.COBBLESTONE_STAIRS, Blocks.DARK_OAK_STAIRS,
			Blocks.DARK_PRISMARINE_STAIRS, Blocks.DIORITE_STAIRS, Blocks.END_STONE_BRICK_STAIRS, Blocks.GRANITE_STAIRS, Blocks.JUNGLE_STAIRS,
			Blocks.MOSSY_COBBLESTONE_STAIRS, Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.NETHER_BRICK_STAIRS, Blocks.OAK_STAIRS,
			Blocks.POLISHED_ANDESITE_STAIRS, Blocks.POLISHED_DIORITE_STAIRS, Blocks.POLISHED_GRANITE_STAIRS, Blocks.PRISMARINE_BRICK_STAIRS,
			Blocks.PRISMARINE_STAIRS, Blocks.PURPUR_STAIRS, Blocks.QUARTZ_STAIRS, Blocks.RED_NETHER_BRICK_STAIRS, Blocks.RED_SANDSTONE_STAIRS,
			Blocks.SANDSTONE_STAIRS, Blocks.SMOOTH_RED_SANDSTONE_STAIRS, Blocks.SMOOTH_RED_SANDSTONE_STAIRS, Blocks.SMOOTH_SANDSTONE_STAIRS,
			Blocks.SPRUCE_STAIRS, Blocks.STONE_BRICK_STAIRS, Blocks.STONE_STAIRS, Blocks.NETHERRACK, Blocks.SOUL_SAND, Blocks.GLOWSTONE,
			Blocks.MAGMA_BLOCK, Blocks.NETHER_BRICKS, Blocks.RED_NETHER_BRICKS, Blocks.NETHER_BRICK_FENCE, Blocks.END_STONE, Blocks.PURPUR_BLOCK,
			Blocks.PURPUR_PILLAR, Blocks.END_STONE_BRICKS, Blocks.END_ROD, Blocks.BLACK_WOOL, Blocks.BLUE_WOOL, Blocks.BROWN_WOOL, Blocks.CYAN_WOOL,
			Blocks.GRAY_WOOL, Blocks.GREEN_WOOL, Blocks.LIGHT_BLUE_WOOL, Blocks.LIGHT_GRAY_WOOL, Blocks.LIME_WOOL, Blocks.MAGENTA_WOOL,
			Blocks.ORANGE_WOOL, Blocks.PINK_WOOL, Blocks.PURPLE_WOOL, Blocks.RED_WOOL, Blocks.WHITE_WOOL, Blocks.YELLOW_WOOL, Blocks.CRAFTING_TABLE,
			Blocks.BLAST_FURNACE, Blocks.FURNACE, Blocks.CAMPFIRE, Blocks.SMOKER, Blocks.STONECUTTER, Blocks.BARREL, Blocks.ENCHANTING_TABLE,
			Blocks.LOOM, Blocks.FLETCHING_TABLE, Blocks.CARTOGRAPHY_TABLE, Blocks.ANVIL, Blocks.SMITHING_TABLE, Blocks.GRINDSTONE, Blocks.BEACON,
			Blocks.JUKEBOX, Blocks.SCAFFOLDING, Blocks.BELL, Blocks.LECTERN, Blocks.COMPOSTER, Blocks.BREWING_STAND, Blocks.CAULDRON,
			Blocks.NOTE_BLOCK, Blocks.HOPPER, Blocks.TNT, Blocks.DISPENSER, Blocks.DROPPER, Blocks.OBSERVER, Blocks.REDSTONE_LAMP, Blocks.PISTON,
			Blocks.STICKY_PISTON, Blocks.DAYLIGHT_DETECTOR, Blocks.REDSTONE_WIRE, Blocks.REPEATER, Blocks.COMPARATOR, Blocks.TRIPWIRE_HOOK,
			Blocks.REDSTONE_TORCH, Blocks.REDSTONE_WALL_TORCH, Blocks.TORCH, Blocks.WALL_TORCH, Blocks.ACACIA_TRAPDOOR, Blocks.BIRCH_TRAPDOOR,
			Blocks.DARK_OAK_TRAPDOOR, Blocks.IRON_TRAPDOOR, Blocks.JUNGLE_TRAPDOOR, Blocks.OAK_TRAPDOOR, Blocks.SPRUCE_TRAPDOOR, Blocks.ACACIA_DOOR,
			Blocks.BIRCH_DOOR, Blocks.DARK_OAK_DOOR, Blocks.IRON_DOOR, Blocks.JUNGLE_DOOR, Blocks.OAK_DOOR, Blocks.SPRUCE_DOOR, Blocks.RAIL,
			Blocks.POWERED_RAIL, Blocks.ACTIVATOR_RAIL, Blocks.DETECTOR_RAIL, Blocks.ACACIA_FENCE, Blocks.ACACIA_FENCE_GATE, Blocks.BIRCH_FENCE,
			Blocks.BIRCH_FENCE_GATE, Blocks.DARK_OAK_FENCE, Blocks.DARK_OAK_FENCE_GATE, Blocks.JUNGLE_FENCE, Blocks.JUNGLE_FENCE_GATE,
			Blocks.OAK_FENCE, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE, Blocks.SPRUCE_FENCE_GATE, Blocks.IRON_BARS, Blocks.GLASS_PANE,
			Blocks.LADDER, Blocks.COBWEB, Blocks.LANTERN, Blocks.SEA_PICKLE, Blocks.FLOWER_POT, Blocks.BRAIN_CORAL_BLOCK, Blocks.BUBBLE_CORAL_BLOCK,
			Blocks.DEAD_BRAIN_CORAL_BLOCK, Blocks.DEAD_BUBBLE_CORAL_BLOCK, Blocks.DEAD_HORN_CORAL_BLOCK, Blocks.DEAD_TUBE_CORAL_BLOCK,
			Blocks.FIRE_CORAL_BLOCK, Blocks.HORN_CORAL_BLOCK, Blocks.TUBE_CORAL_BLOCK);
	}
}