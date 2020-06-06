package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.SpecialFlyAbility;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class ConfigEvents
{

	@SubscribeEvent
	public static void onClonePlayer(PlayerEvent.Clone event) 
	{
		if (event.isWasDeath()) {
			IDevilFruit oldPlayerProps = DevilFruitCapability.get(event.getOriginal());
			IDevilFruit newPlayerProps = DevilFruitCapability.get(event.getPlayer());

			if (CommonConfig.instance.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.FULL) {
				INBT nbt = new CompoundNBT();

				// Keep the entity stats
				IEntityStats oldEntityStats = EntityStatsCapability.get(event.getOriginal());
				oldEntityStats.setCola(oldEntityStats.getMaxCola());
				nbt = EntityStatsCapability.INSTANCE.writeNBT(oldEntityStats, null);
				IEntityStats newEntityStats = EntityStatsCapability.get(event.getPlayer());
				EntityStatsCapability.INSTANCE.readNBT(newEntityStats, null, nbt);

				// Keep the DF stats
				oldPlayerProps.setZoanPoint("");
				nbt = DevilFruitCapability.INSTANCE.writeNBT(oldPlayerProps, null);
				DevilFruitCapability.INSTANCE.readNBT(newPlayerProps, null, nbt);

				// Keep the abilities
				IAbilityData oldAbilityData = AbilityDataCapability.get(event.getOriginal());
				nbt = AbilityDataCapability.INSTANCE.writeNBT(oldAbilityData, null);
				IAbilityData newAbilityData = AbilityDataCapability.get(event.getPlayer());
				AbilityDataCapability.INSTANCE.readNBT(newAbilityData, null, nbt);
			} else if (CommonConfig.instance.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.AUTO) {
				IEntityStats oldEntityStats = EntityStatsCapability.get(event.getOriginal());

				String faction = oldEntityStats.getFaction();
				String race = oldEntityStats.getRace();
				String fightStyle = oldEntityStats.getFightingStyle();
				// String crew = oldEntityStats.getCrew();
				int doriki = oldEntityStats.getDoriki() / 3;

				IEntityStats newEntityStats = EntityStatsCapability.get(event.getPlayer());
				newEntityStats.setFaction(faction);
				newEntityStats.setRace(race);
				newEntityStats.setFightingStyle(fightStyle);
				newEntityStats.setMaxCola(100);
				newEntityStats.setCola(oldEntityStats.getMaxCola());
				newEntityStats.setDoriki(doriki);
			} else if (CommonConfig.instance.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.CUSTOM) {
				IEntityStats oldEntityStats = EntityStatsCapability.get(event.getOriginal());
				IEntityStats newEntityStats = EntityStatsCapability.get(event.getPlayer());

				IDevilFruit oldDevilFruit = DevilFruitCapability.get(event.getOriginal());
				IDevilFruit newDevilFruit = DevilFruitCapability.get(event.getPlayer());

				INBT nbt;


				for (String stat : CommonConfig.instance.getStatsToKeep()) {
					switch (WyHelper.getResourceName(stat)) {
					case "doriki":
						newEntityStats.setDoriki(oldEntityStats.getDoriki());
						break;
					case "bounty":
						newEntityStats.setBounty(oldEntityStats.getBounty());
						break;
					case "belly":
						newEntityStats.setBelly(oldEntityStats.getBelly());
						break;
					case "race":
						newEntityStats.setRace(oldEntityStats.getRace());
						break;
					case "faction":
						newEntityStats.setFaction(oldEntityStats.getFaction());
						break;
					case "fighting_style":
						newEntityStats.setFightingStyle(oldEntityStats.getFightingStyle());
						break;
					case "devil_fruit":
                        oldPlayerProps.setZoanPoint("");
                        nbt = DevilFruitCapability.INSTANCE.writeNBT(oldPlayerProps, null);
						DevilFruitCapability.INSTANCE.readNBT(newPlayerProps, null, nbt);
						break;
					}
				}
			}

			if(CommonConfig.instance.isSpecialFlyingEnabled() && AbilityDataCapability.get(event.getPlayer()).hasUnlockedAbility(SpecialFlyAbility.INSTANCE) && !event.getPlayer().isCreative() && !event.getPlayer().isSpectator())
			{
				event.getPlayer().abilities.allowFlying = false;
				event.getPlayer().abilities.isFlying = false;
				((ServerPlayerEntity)event.getPlayer()).connection.sendPacket(new SPlayerAbilitiesPacket(event.getPlayer().abilities));
			}		
		}
	}
}
