package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.helpers.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

import java.util.ArrayList;
import java.util.Arrays;

public class OriAbilities {

    public static Ability[] abilitiesArray = new Ability[]{new GreatCage(), new PrisonBreak(), new AwaseBaori()};

    public static ArrayList<int[]> makeShapeSquare(EntityPlayer player, int blockX, int blockY, int blockZ, Block blockCheck, Block blockReplace) {

        ArrayList<int[]> blockList = new ArrayList<>();

        for (int count = blockX - 1; count < blockX + 2; count++) {
            Block blockToReplace = player.worldObj.getBlock(count, blockY, blockZ);
            if (blockToReplace == blockCheck) {
                player.worldObj.setBlock(count, blockY, blockZ, blockReplace);
                blockList.add(new int[]{count, blockY, blockZ});
            }

        }
        for (int count = blockY - 1; count < blockY + 2; count++) {
            Block blockToReplace = player.worldObj.getBlock(blockX, count, blockZ);
            if (blockToReplace == blockCheck) {
                player.worldObj.setBlock(blockX, count, blockZ, blockReplace);
                blockList.add(new int[]{blockX, count, blockZ});
            }

        }
        for (int count = blockZ - 1; count < blockZ + 2; count++) {
            Block blockToReplace = player.worldObj.getBlock(blockX, blockY, count);
            if (blockToReplace == blockCheck) {
                player.worldObj.setBlock(blockX, blockY, count, blockReplace);
                blockList.add(new int[]{blockX, blockY, count});

            }

        }
        return blockList;
    }

    public static class GreatCage extends Ability {
        public GreatCage() {
            super(ListAttributes.GREATCAGE);
        }

        public void use(EntityPlayer player) {
            if (!isOnCooldown) {
                if (MainConfig.enableGriefing) {
                	WyHelper.createEmptyCube(player, new int[] {5, 3, 5}, Blocks.iron_bars, "air");
                }

                super.use(player);
            }
        }
    }

    public static class PrisonBreak extends Ability {

        private ArrayList<int[]> blockList;

        public PrisonBreak() {
            super(ListAttributes.PRISONBREAK);
        }

        public void passive(EntityPlayer player) {
            MovingObjectPosition point = WyHelper.rayTraceBlocks(player);
            if (!this.isOnCooldown && point != null) {
                if (this.blockList == null) {
                    this.blockList = makeShapeSquare(player,point.blockX,point.blockY,point.blockZ,Blocks.iron_bars,Blocks.air);
                }
                super.passive(player);
            }
        }

        public void endPassive(EntityPlayer player) {
            for (int count = 0; count < blockList.size(); count++) {
                int[] currentArray = blockList.get(count);
                if (player.worldObj.getBlock(currentArray[0], currentArray[1], currentArray[2]) == Blocks.air) {
                    player.worldObj.setBlock(currentArray[0],currentArray[1],currentArray[2], Blocks.iron_bars);
                }
            }
            this.blockList = null;
            this.startCooldown();
            this.startExtUpdate(player);
        }
    }

    public static class AwaseBaori extends Ability {


        public AwaseBaori() {
            super(ListAttributes.AWASEBAORI);
        }

        public void hitEntity(EntityPlayer player, EntityLivingBase target) {

        	super.hitEntity(player,target);
        	WyHelper.createEmptyCube(target, new int[] {2, 3, 2}, Blocks.iron_bars, "air");

            target.addPotionEffect(new PotionEffect(2,  20 * 7, 15));
        }
    }
}