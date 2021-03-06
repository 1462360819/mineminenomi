package xyz.pixelatedw.mineminenomi.abilities.ushibison;

import java.util.UUID;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.BisonWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class BisonWalkPointAbility extends ZoanAbility
{
	public static final BisonWalkPointAbility INSTANCE = new BisonWalkPointAbility();

	private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(UUID.fromString("d141ef28-e77a-418d-927b-ca9a09417189"), "Walk Point Multiplier", 1.2f, AttributeModifier.Operation.MULTIPLY_BASE).setSaved(false);
	private static final AttributeModifier ARMOR_MODIFIER = new AttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), "Walk Point Multiplier", 12, AttributeModifier.Operation.ADDITION).setSaved(false);
	private static final AttributeModifier STRENGTH_MODIFIER = new AttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), "Walk Point Multiplier", 3, AttributeModifier.Operation.ADDITION).setSaved(false);
	private static final AttributeModifier ATTACK_SPEED_MODIFIER = new AttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), "Walk Point Multiplier", -1f, AttributeModifier.Operation.ADDITION).setSaved(false);
	private static final AttributeModifier JUMP_BOOST = new AttributeModifier(UUID.fromString("72ee5c43-a900-4545-883c-709d84ef1f9c"), "Walk Point Multiplier", 1, AttributeModifier.Operation.ADDITION).setSaved(false);
	private static final AttributeModifier STEP_HEIGHT = new AttributeModifier(UUID.fromString("1d68a133-8a0e-4b8f-8790-1360007d4741"), "Walk Point Multiplier", 1, AttributeModifier.Operation.ADDITION).setSaved(false);

	public BisonWalkPointAbility()
	{
		super("Bison Walk Point", AbilityCategory.DEVIL_FRUIT, BisonWalkZoanInfo.FORM);
		this.setDescription("Allows the user to transforms into a bison, which focuses on speed, Allows the user to use 'Fiddle Banff'");
		this.addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, SPEED_MODIFIER);
		this.addZoanModifier(SharedMonsterAttributes.ARMOR, ARMOR_MODIFIER);
		this.addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, STRENGTH_MODIFIER);
		this.addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER);
		this.addZoanModifier(ModAttributes.JUMP_HEIGHT, JUMP_BOOST);
		this.addZoanModifier(ModAttributes.STEP_HEIGHT, STEP_HEIGHT);
	}
}
