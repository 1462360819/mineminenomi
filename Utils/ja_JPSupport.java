package MineMineNoMi3.Utils;

import net.minecraft.util.EnumChatFormatting;

public class ja_JPSupport 
{

	public static String fromKanjiToHiragana(String name)
	{	
		return EnumChatFormatting.GOLD + name.replace("実", "�?�").replace("�?�拳", "�?��?�ん").replace("�?�銃", "�?��?�ん").replace("大炎戒 炎�?", "�?��?��?�ん�?��?� �?�ん�?��?�").replace("�?��?�磨", "�?��?�る�?�")
				.replace("陽炎", "�?��?��?�?�").replace("炎上網", "�?�ん�?�ょ�?�も�?�");
	}
	
}
