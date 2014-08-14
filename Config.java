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

		try {
			cfg.load();
			useGlass = cfg.get(Configuration.CATEGORY_GENERAL, "useGlass", true, comment1).getBoolean(true);
			useGlassLighting = cfg.get(Configuration.CATEGORY_GENERAL, "useGlassLighting", true, comment2).getBoolean(
					true);
			useGlassHemming = cfg.get(Configuration.CATEGORY_GENERAL, "useGlassHemming", true, comment3).getBoolean(
					true);
			useGlassLandH = cfg.get(Configuration.CATEGORY_GENERAL, "useGlassLandH", true, comment4).getBoolean(true);
			isTest = cfg.get(Configuration.CATEGORY_GENERAL, "isTest", false, "Always false").getBoolean(false);

		} catch (Exception e) {
			FMLLog.log(Level.INFO, Reference.MOD_NAME + " configuration loadding failed");
		} finally {
			cfg.save();
		}
	}

	/**
	 * 英名<br />
	 * registerBlockで使っているだけで、表示名はlangから取ってくる
	 */
	public static final String[][] colorListListEn = {
			{ "black", "dimgray", "gray", "darkgray", "silver", "lightgray", "gainsboro", "whitesmoke", "white",
					"snow", "ghostwhite", "floralwhite", "linen",
					"antiquewhite", "papayawhip", "blanchedalmond" },
			{ "bisque", "moccasin", "navajowhite", "peachpuff", "mistyrose", "lavenderblush", "seashell", "oldlace",
					"ivory", "honydew", "mintcream", "azure",
					"aliceblue", "lavender", "lightsteelblue", "lightslategray" },
			{ "slategray", "steelblue", "royalblue", "midnightblue", "navy", "darkblue", "mediumblue", "blue",
					"dodgerblue", "cornflowerblue", "deepskyblue",
					"lightskyblue", "skyblue", "lightblue", "powderblue", "paleturquoise" },
			{ "lightcyan", "cyan/aqua", "turquoise", "mediumturquoise", "darkturquoise", "lightseagreen", "cadetblue",
					"darkcyan", "teal", "daekslategray",
					"darkgreen", "green", "forestgreen", "seagreen", "mediumseagreen", "midiumaquamarine" },
			{ "darkseagreen", "aquamarine", "palegreen", "lightgreen", "springgreen", "mediumspringgreen", "lawngreen",
					"chartreuse", "greenyellow", "lime",
					"limegreen", "yellowgreen", "darkolivegreen", "olivedrab", "olive", "darkkhaki" },
			{ "palegoldenrod", "cornsilk", "beige", "lightyellow", "lightgoldenrodyellow", "lemonchiffon", "wheat",
					"burlywwod", "tan", "khaki", "yellow",
					"gold", "orange", "sandybrown", "darkorange", "goldenrod" },
			{ "peru", "darkgoldenrod", "chocolate", "sienna", "saddlebrown", "maroon", "darkred", "brown", "firebrick",
					"indianred", "rosybrown", "darksalmon",
					"lightcoral", "salmon", "lightsalmon", "coral" },
			{ "tomato", "orangered", "red", "crimson", "mediumvioletred", "deeppink", "hotpink", "paleviletred",
					"pink", "lightpink", "thistle",
					"magenta/fuchsia", "violet", "plum", "orchid", "mediumorchid" },
			{ "darkorchid", "darkviolet", "darkmagenta", "purple", "indigo", "darkslateblue", "blueviolet",
					"mediumpurple", "slateblue", "mediumslateblue" }

	};

}
