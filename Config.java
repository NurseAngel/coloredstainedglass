package com.github.nurseangel.coloredstainedglass;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config {

	/**
	 * コンストラクタ
	 *
	 * @param cfg
	 * @return
	 */
	public Config(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		readConfig(cfg);
	}

	public static boolean isTest = false;

	public static boolean useGlass;
	public static boolean useGlassLighting;
	public static boolean useGlassHemming;
	public static boolean useGlassLandH;

	public static boolean useColoredLightApi;

	public static int craftQuantity = 6;

	/**
	 * コンフィグファイルから読み込み
	 *
	 * @param cfg
	 */
	private void readConfig(Configuration cfg) {

		String comment1 = "Normal stainedglass.";
		String comment2 = "Lighting stainedglass.";
		String comment3 = "Hemming stainedglass.";
		String comment4 = "Lighting and Hemming stainedglass.";
		String comment5 = "Use Color Light.";
		String comment6 = "Craft coloredGlass Quantity. default:6";

		try {
			cfg.load();
			useGlass = cfg.get(Configuration.CATEGORY_GENERAL, "useGlass", true, comment1).getBoolean(true);
			useGlassLighting = cfg.get(Configuration.CATEGORY_GENERAL, "useGlassLighting", true, comment2).getBoolean(true);
			useGlassHemming = cfg.get(Configuration.CATEGORY_GENERAL, "useGlassHemming", true, comment3).getBoolean(true);
			useGlassLandH = cfg.get(Configuration.CATEGORY_GENERAL, "useGlassLandH", true, comment4).getBoolean(true);
			useColoredLightApi = cfg.get(Configuration.CATEGORY_GENERAL, "useColoredLightApi", true, comment5).getBoolean(true);
			isTest = cfg.get(Configuration.CATEGORY_GENERAL, "isTest", false, "Always false").getBoolean(false);
			craftQuantity = cfg.get(Configuration.CATEGORY_GENERAL, "craftQuantity", 6, comment6).getInt(6);

		} catch (Exception e) {
			FMLLog.log(Level.INFO, Reference.MOD_NAME + " configuration loadding failed");
		} finally {
			cfg.save();
		}
	}

	/**
	 * 英名と色。<br />
	 * 名前/R/G/B
	 * 実は名前は使ってないので不要だが、無くすとわからなくなるので置いてる。<br />
	 * XXX 色はintにするべきだがまあいいや。
	 */
	public static final String[][] colorListListEn = {
			{ "black", "0", "0", "0" },
			{ "dimgray", "6", "6", "6" },
			{ "gray", "8", "8", "8" },
			{ "darkgray", "10", "10", "10" },
			{ "silver", "12", "12", "12" },
			{ "lightgrey", "13", "13", "13" },
			{ "gainsboro", "13", "13", "13" },
			{ "whitesmoke", "15", "15", "15" },
			{ "white", "15", "15", "15" },
			{ "snow", "15", "15", "15" },
			{ "ghostwhite", "15", "15", "15" },
			{ "floralwhite", "15", "15", "15" },
			{ "linen", "15", "15", "14" },
			{ "antiquewhite", "15", "14", "13" },
			{ "papayawhip", "15", "14", "13" },
			{ "blanchedalmond", "15", "14", "12" },
			{ "bisque", "15", "14", "12" },
			{ "moccasin", "15", "14", "11" },
			{ "navajowhite", "15", "13", "10" },
			{ "peachpuff", "15", "13", "11" },
			{ "mistyrose", "15", "14", "14" },
			{ "lavenderblush", "15", "15", "15" },
			{ "seashell", "15", "15", "14" },
			{ "oldlace", "15", "15", "14" },
			{ "ivory", "15", "15", "15" },
			{ "honeydew", "15", "15", "15" },
			{ "mintcream", "15", "15", "15" },
			{ "azure", "15", "15", "15" },
			{ "aliceblue", "15", "15", "15" },
			{ "lavender", "14", "14", "15" },
			{ "lightsteelblue", "11", "12", "13" },
			{ "lightslategray", "7", "8", "9" },
			{ "slategray", "7", "8", "9" },
			{ "steelblue", "4", "8", "11" },
			{ "royalblue", "4", "6", "14" },
			{ "midnightblue", "1", "1", "7" },
			{ "navy", "0", "0", "8" },
			{ "darkblue", "0", "0", "8" },
			{ "mediumblue", "0", "0", "12" },
			{ "blue", "0", "0", "15" },
			{ "dodgerblue", "1", "9", "15" },
			{ "cornflowerblue", "6", "9", "14" },
			{ "deepskyblue", "0", "11", "15" },
			{ "lightskyblue", "8", "12", "15" },
			{ "skyblue", "8", "12", "14" },
			{ "lightblue", "10", "13", "14" },
			{ "powderblue", "11", "14", "14" },
			{ "paleturquoise", "10", "14", "14" },
			{ "lightcyan", "14", "15", "15" },
			{ "cyan/aqua", "0", "15", "15" },
			{ "turquoise", "4", "14", "13" },
			{ "mediumturquoise", "4", "13", "12" },
			{ "darkturquoise", "0", "12", "13" },
			{ "lightseagreen", "2", "11", "10" },
			{ "cadetblue", "5", "9", "10" },
			{ "darkcyan", "0", "8", "8" },
			{ "teal", "0", "8", "8" },
			{ "darkslategray", "2", "4", "4" },
			{ "darkgreen", "0", "6", "0" },
			{ "green", "0", "8", "0" },
			{ "forestgreen", "2", "8", "2" },
			{ "seagreen", "2", "8", "5" },
			{ "mediumseagreen", "3", "11", "7" },
			{ "mediumaquamarine", "6", "12", "10" },
			{ "darkseagreen", "8", "11", "8" },
			{ "aquamarine", "7", "15", "13" },
			{ "palegreen", "9", "15", "9" },
			{ "lightgreen", "9", "14", "9" },
			{ "springgreen", "0", "15", "7" },
			{ "mediumspringgreen", "0", "15", "9" },
			{ "lawngreen", "7", "15", "0" },
			{ "chartreuse", "7", "15", "0" },
			{ "greenyellow", "10", "15", "2" },
			{ "lime", "0", "15", "0" },
			{ "limegreen", "3", "12", "3" },
			{ "yellowgreen", "9", "12", "3" },
			{ "darkolivegreen", "5", "6", "2" },
			{ "olivedrab", "6", "8", "2" },
			{ "olive", "8", "8", "0" },
			{ "darkkhaki", "11", "11", "6" },
			{ "palegoldenrod", "14", "14", "10" },
			{ "cornsilk", "15", "15", "13" },
			{ "beige", "15", "15", "13" },
			{ "lightyellow", "15", "15", "14" },
			{ "lightgoldenrodyellow", "15", "15", "13" },
			{ "lemonchiffon", "15", "15", "12" },
			{ "wheat", "15", "13", "11" },
			{ "burlywood", "13", "11", "8" },
			{ "tan", "13", "11", "8" },
			{ "khaki", "15", "14", "8" },
			{ "yellow", "15", "15", "0" },
			{ "gold", "15", "13", "0" },
			{ "orange", "15", "10", "0" },
			{ "sandybrown", "15", "10", "6" },
			{ "darkorange", "15", "8", "0" },
			{ "goldenrod", "13", "10", "2" },
			{ "peru", "12", "8", "3" },
			{ "darkgoldenrod", "11", "8", "0" },
			{ "chocolate", "13", "6", "1" },
			{ "sienna", "10", "5", "2" },
			{ "saddlebrown", "8", "4", "1" },
			{ "maroon", "8", "0", "0" },
			{ "darkred", "8", "0", "0" },
			{ "brown", "10", "2", "2" },
			{ "firebrick", "11", "2", "2" },
			{ "indianred", "12", "5", "5" },
			{ "rosybrown", "11", "8", "8" },
			{ "darksalmon", "14", "9", "7" },
			{ "lightcoral", "15", "8", "8" },
			{ "salmon", "15", "8", "7" },
			{ "lightsalmon", "15", "10", "7" },
			{ "coral", "15", "7", "5" },
			{ "tomato", "15", "6", "4" },
			{ "orangered", "15", "4", "0" },
			{ "red", "15", "0", "0" },
			{ "crimson", "13", "1", "3" },
			{ "mediumvioletred", "12", "1", "8" },
			{ "deeppink", "15", "1", "9" },
			{ "hotpink", "15", "6", "11" },
			{ "palevioletred", "13", "7", "9" },
			{ "pink", "15", "12", "12" },
			{ "lightpink", "15", "11", "12" },
			{ "thistle", "13", "11", "13" },
			{ "magenta/fuchsia", "15", "0", "15" },
			{ "violet", "14", "8", "14" },
			{ "plum", "13", "10", "13" },
			{ "orchid", "13", "7", "13" },
			{ "mediumorchid", "11", "5", "13" },
			{ "darkorchid", "9", "3", "12" },
			{ "darkviolet", "9", "0", "13" },
			{ "darkmagenta", "8", "0", "8" },
			{ "purple", "8", "0", "8" },
			{ "indigo", "4", "0", "8" },
			{ "darkslateblue", "4", "3", "8" },
			{ "blueviolet", "8", "2", "14" },
			{ "mediumpurple", "9", "7", "13" },
			{ "slateblue", "6", "5", "12" },
			{ "mediumslateblue", "7", "6", "14" },
	};

}
