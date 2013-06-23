package mods.nurseangel.coloredstainedglass;

import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
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

	public static int blockIdNormal;
	public static int blockIdLighting;
	public static int blockIdHemming;
	public static int blockIdLandH;

	/**
	 * コンフィグファイルから読み込み
	 *
	 * @param cfg
	 */
	private void readConfig(Configuration cfg) {

		int blockIdStart1 = 1330;
		int blockIdStart2 = 1339;
		int blockIdStart3 = 1348;
		int blockIdStart4 = 1357;

		String comment1 = "Normal. Use next 9 BlockID";
		String comment2 = "Lighting. Use next 9 BlockID";
		String comment3 = "Hemming. Use next 9 BlockID";
		String comment4 = "Lighting and Hemming. Use next 9 BlockID";

		try {
			cfg.load();
			blockIdNormal = cfg.getBlock("Normal", blockIdStart1, comment1).getInt();
			blockIdLighting = cfg.getBlock("Lighting", blockIdStart2, comment2).getInt();
			blockIdHemming = cfg.getBlock("Hemming", blockIdStart3, comment3).getInt();
			blockIdLandH = cfg.getBlock("LightingHemming", blockIdStart4, comment4).getInt();
			isTest = cfg.get(Configuration.CATEGORY_GENERAL, "isTest", false, "Always false").getBoolean(false);

		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, Reference.MOD_NAME + " configuration loadding failed");
		} finally {
			cfg.save();
		}
	}

	/**
	 * 英名<br />
	 * 和名はどうやら無いみたい?
	 */
	public static final String[][] colorListListEn = {
			{ "black", "dimgray", "gray", "darkgray", "silver", "lightgray", "gainsboro", "whitesmoke", "white", "snow", "ghostwhite", "floralwhite", "linen",
					"antiquewhite", "papayawhip", "blanchedalmond" },
			{ "bisque", "moccasin", "navajowhite", "peachpuff", "mistyrose", "lavenderblush", "seashell", "oldlace", "ivory", "honydew", "mintcream", "azure",
					"aliceblue", "lavender", "lightsteelblue", "lightslategray" },
			{ "slategray", "steelblue", "royalblue", "midnightblue", "navy", "darkblue", "mediumblue", "blue", "dodgerblue", "cornflowerblue", "deepskyblue",
					"lightskyblue", "skyblue", "lightblue", "powderblue", "paleturquoise" },
			{ "lightcyan", "cyan/aqua", "turquoise", "mediumturquoise", "darkturquoise", "lightseagreen", "cadetblue", "darkcyan", "teal", "daekslategray",
					"darkgreen", "green", "forestgreen", "seagreen", "mediumseagreen", "midiumaquamarine" },
			{ "darkseagreen", "aquamarine", "palegreen", "lightgreen", "springgreen", "mediumspringgreen", "lawngreen", "chartreuse", "greenyellow", "lime",
					"limegreen", "yellowgreen", "darkolivegreen", "olivedrab", "olive", "darkkhaki" },
			{ "palegoldenrod", "cornsilk", "beige", "lightyellow", "lightgoldenrodyellow", "lemonchiffon", "wheat", "burlywwod", "tan", "khaki", "yellow",
					"gold", "orange", "sandybrown", "darkorange", "goldenrod" },
			{ "peru", "darkgoldenrod", "chocolate", "sienna", "saddlebrown", "maroon", "darkred", "brown", "firebrick", "indianred", "rosybrown", "darksalmon",
					"lightcoral", "salmon", "lightsalmon", "coral" },
			{ "tomato", "orangered", "red", "crimson", "mediumvioletred", "deeppink", "hotpink", "paleviletred", "pink", "lightpink", "thistle",
					"magenta/fuchsia", "violet", "plum", "orchid", "mediumorchid" },
			{ "darkorchid", "darkviolet", "darkmagenta", "purple", "indigo", "darkslateblue", "blueviolet", "mediumpurple", "slateblue", "mediumslateblue" }

	};

}
