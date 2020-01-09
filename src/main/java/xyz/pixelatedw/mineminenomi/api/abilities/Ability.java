package xyz.pixelatedw.mineminenomi.api.abilities;

import java.io.Serializable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.server.SUpdateHotbarStatePacket;

public abstract class Ability implements Serializable
{
	private String name = "";
	private String desc = "";
	private int cooldown;
	private int maxCooldown;
	private Category category = Category.DEVIL_FRUIT;
	private State state = State.STANDBY;
	
	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IOnUse onUseEvent = (player, ability) -> {};
	protected IDuringCooldown duringCooldownEvent = (player, ability, cooldown) -> {};
	
	public Ability(String name, Category category)
	{
		this.name = name;
		this.category = category;
	}
	
	/*
	 * 	Event Starters
	 */
	public void use(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
		
		if(!this.isOnStandby())
			return;			
		
		this.onUseEvent.onUse(player, this);

		this.startCooldown();
		ModNetwork.sendTo(new SUpdateHotbarStatePacket(AbilityDataCapability.get(player)), (ServerPlayerEntity)player);
	}
	
	
	/*
	 * 	States
	 */
	public boolean isOnStandby()
	{
		return this.state == State.STANDBY;
	}
	
	public boolean isOnCooldown()
	{
		return this.state == State.COOLDOWN;
	}

	public boolean isPassiveActive()
	{
		return this.state == State.PASSIVE;
	}
	
	public boolean isCharging()
	{
		return this.state == State.CHARGING;
	}

	public boolean isDisabled()
	{
		return this.state == State.DISABLED;
	}
	
	public void startStandby()
	{
		this.state = State.STANDBY;
	}
	
	public void startCooldown()
	{
		this.state = State.COOLDOWN;
	}
	
	public void startPassive()
	{
		this.state = State.PASSIVE;
	}
	
	public void startCharging()
	{
		this.state = State.CHARGING;
	}
	
	public void setState(State state)
	{
		this.state = state;
	}
	
	public State getState()
	{
		return this.state;
	}
	
	
	/*
	 * 	Setters/Getters
	 */
	public void setMaxCooldown(int cooldown)
	{
		this.maxCooldown = cooldown * 20;
		this.cooldown = this.maxCooldown;
	}
	
	public void setCooldown(int cooldown)
	{
		this.cooldown = cooldown * 20;
	}
	
	public void setDescription(String desc)
	{
		this.desc = desc;
	}
	
	public String getDescription()
	{
		return this.desc;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Category getCategory()
	{
		return this.category;
	}
	
	
	/*
	 * 	Methods
	 */
	public void cooldown(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
				
		if(this.isOnCooldown() && this.cooldown > 0)
		{
			this.cooldown--;
			this.duringCooldownEvent.duringCooldown(player, this, this.cooldown);
		}
		else if(this.isOnCooldown() && this.cooldown <= 0)
		{
			this.cooldown = this.maxCooldown;
			this.state = State.STANDBY;
			ModNetwork.sendTo(new SUpdateHotbarStatePacket(AbilityDataCapability.get(player)), (ServerPlayerEntity)player);
		}
	}
	
	private Ability getOriginalAbility(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		return props.getAbilities(Category.ALL).parallelStream().filter(ability -> ability.getName().equalsIgnoreCase(this.getName())).findFirst().orElse(null);
	}
	
	private Ability getSavedAbility(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		Ability abl = props.getAbility(this.getOriginalAbility(player));
		return abl;
	}
	
	@Override
	public boolean equals(Object abl)
	{
		if(!(abl instanceof Ability))
			return false;
		
		return this.getName().equalsIgnoreCase(((Ability) abl).getName());
	}
	
	
	
	/*
	 *	Enums
	 */
	public enum State
	{
		STANDBY,
		COOLDOWN,
		PASSIVE,
		CHARGING,
		DISABLED
	}
	
	public enum Category
	{
		ALL,
		
		DEVIL_FRUIT,
		RACIAL,
		HAKI,
	}
	
	
	
	/*
	 *	Interfaces
	 */
	public interface IOnUse extends Serializable
	{
		void onUse(PlayerEntity player, Ability ability);
	}
	
	public interface IDuringCooldown extends Serializable
	{
		void duringCooldown(PlayerEntity player, Ability ability, int cooldown);
	}
}
