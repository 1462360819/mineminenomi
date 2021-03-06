package xyz.pixelatedw.mineminenomi.data.entity.entitystats;

public interface IEntityStats
{

	boolean isInCombatMode();
	void setCombatMode(boolean value);
	
	int getDoriki();
	void alterDoriki(int value);
	void setDoriki(int value);

	long getBelly();
	void alterBelly(long value);
	void setBelly(long value);

	int getExtol();
	void alterExtol(int value);
	void setExtol(int value);

	long getBounty();
	void alterBounty(long value);
	void setBounty(long value);

	int getCola();
	void alterCola(int value);
	void setCola(int value);
	
	int getMaxCola();
	void alterMaxCola(int value);
	void setMaxCola(int value);
	
	int getUltraCola();
	void setUltraCola(int value);
	void addUltraCola(int value);
	
	boolean isPirate();
	boolean isMarine();
	boolean isBountyHunter();
	boolean isRevolutionary();
	boolean isBandit();
	boolean hasFaction();
	void setFaction(String value);
	String getFaction();
	
	boolean isHuman();
	boolean isFishman();
	boolean isCyborg();
	boolean hasRace();
	void setRace(String value);
	String getRace();
	
	boolean isSwordsman();
	boolean isSniper();
	boolean isDoctor();
	boolean isWeatherWizard();
	boolean hasFightingStyle();
	void setFightingStyle(String value);
	String getFightingStyle();
	
	boolean hasShadow();
	void setShadow(boolean value);
	
	boolean hasHeart();
	void setHeart(boolean value);

	double getDamageMultiplier();
	void setDamageMultiplier(double multiplier);
}
