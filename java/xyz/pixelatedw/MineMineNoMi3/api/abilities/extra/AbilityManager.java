package xyz.pixelatedw.MineMineNoMi3.api.abilities.extra;

import java.util.HashMap;

import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;

public class AbilityManager 
{
	private static AbilityManager instance;
	public static AbilityManager instance()
	{
		if(instance == null) instance = new AbilityManager();
		return instance;
	}
	
	private HashMap<String, Ability> registeredAbilities = new HashMap<String, Ability>();	
	
	public void registerAbility(Ability ab)
	{
		if(registeredAbilities.containsKey(WyHelper.getFancyName(ab.getAttribute().getAttributeName())))
			WyDebug.info(ab.getAttribute().getAttributeName() + " ability is already registered ! Ignoring request.");
		else
			registeredAbilities.put(WyHelper.getFancyName(ab.getAttribute().getAttributeName()), ab);
	}
	
	public Ability getAbilityByName(String key)
	{
		if(registeredAbilities.containsKey(key))
			return registeredAbilities.get(key);
		else
		{
			//WyDebug.info(key + " ability could not be found, returning null.");
			return null;
		}
	}
}
