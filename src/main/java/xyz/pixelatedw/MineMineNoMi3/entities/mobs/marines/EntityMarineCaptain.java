package xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.abilities.rokushiki.EntityAIRankyaku;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class EntityMarineCaptain extends MarineData
{ 
	private ItemStack swordStack;
	
	public EntityMarineCaptain(World world) 
	{
		super(world, new String[] {"marinec1", "marinec2", "marinec3", "marinec4", "marinec5"});
		/*this.tasks.addTask(0, new EntityAIHakiCombat(this));
		this.tasks.addTask(1, new EntityAIYakkodori(this));
		this.tasks.addTask(1, new EntityAIOTasumaki(this));
		this.tasks.addTask(1, new EntityAIGapCloser(this));
		this.tasks.addTask(1, new EntityAIHakaiHo(this));*/
		this.tasks.addTask(1, new EntityAIRankyaku(this));
 	}
	
	@Override
	public void applyEntityAttributes()
	{
		super.applyEntityAttributes(); 
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);

		this.setDoriki(15 + this.worldObj.rand.nextInt(50));
		this.setBelly(20 + this.worldObj.rand.nextInt(20));
		
		if(!this.worldObj.isRemote)
		{
			Item[] randomSword = new Item[] {ListMisc.MarineSword, null};
			if(this.rand.nextInt(100) <= 60)
			{
				this.setBusoHaki(true);

				Item sword = randomSword[this.rand.nextInt(randomSword.length)];			
				if(sword != null)
				{
					swordStack = new ItemStack(sword);
					swordStack.setTagCompound(new NBTTagCompound());
					swordStack.stackTagCompound.setInteger("metadata", 1);
				}
			}
			else
			{
				Item sword = randomSword[this.rand.nextInt(randomSword.length)];
				if(sword != null)
					swordStack = new ItemStack(sword);
			}
		}
	}

    @Override
	protected void addRandomArmor()
    {
    	if(swordStack != null)
    		this.setCurrentItemOrArmor(0, swordStack);
    }
    
	@Override
	public double[] itemOffset() 
	{
		return new double[] {0, 0, -0.1};
	}
}
