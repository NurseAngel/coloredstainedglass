package mods.nurseangel.coloredstainedglass;

import mods.nurseangel.coloredstainedglass.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ColoredStainedGlass {
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	@Mod.Instance(Reference.MOD_ID)
	public static ColoredStainedGlass instance;

	// ブロック
	public static CSGlassBlock[] csNormalGlassBlock = new CSGlassBlock[9];
	public static CSGlassBlock[] csLightiongGlassBlock = new CSGlassBlock[9];
	public static CSGlassBlock[] csHemmingGlassBlock = new CSGlassBlock[9];
	public static CSGlassBlock[] csLandHGlassBlock = new CSGlassBlock[9];

	public static Config config;
	/** クリエイティブタブ */
	public static final CreativeTabs creativeTab = new CSGlassCreativeTab(Reference.MOD_NAME);

	/**
	 * コンストラクタ的なもの
	 *
	 * @param event
	 */
	@Mod.PreInit
	public void modPreInit(FMLPreInitializationEvent event) {
		config = new Config(event);
	}

	/**
	 * メイン処理的なもの
	 *
	 * @param event
	 */
	@Mod.Init
	public void modInit(FMLInitializationEvent event) {
		// ノーマルIDがなければ何もしない
		if (config.blockIdNormal > 1) {
			setBlock();
		}

	}

	/**
	 * ブロック登録
	 *
	 * @param blockName
	 * @param textureName
	 * @param blockIdStart
	 * @param isLighting
	 * @param isHemming
	 */
	private void setBlock() {
		/**
		 * TODO 各BlockIDの有効無効でなんかこううまくやりたい
		 */

		int blockIdNormal = config.blockIdNormal;
		int blockIdLighting = config.blockIdLighting;
		int blockIdHemming = config.blockIdHemming;
		int blockIdLandH = config.blockIdLandH;
		// 光+縁取りがOKであるフラグ
		boolean isBlockLandH = (blockIdLighting > 1) && (blockIdHemming > 1) && (blockIdLandH > 1);

		// レシピ用にとりあえずnewしておく
		ItemStack itemGlass = new ItemStack(Block.glass, 1);
		ItemStack itemGlowstoneDust = new ItemStack(Item.lightStoneDust, 1);
		ItemStack[] itemDye = new ItemStack[16];
		ItemStack[] itemWool = new ItemStack[16];
		for (int loop = 0; loop < 16; loop++) {
			itemDye[loop] = new ItemStack(Item.dyePowder, 1, loop);
			itemWool[loop] = new ItemStack(Block.cloth, 1, loop);
		}

		// 全ブロックリストでループ
		for (int i = 0; i < config.colorListListEn.length; i++) {
			// ノーマルブロック
			csNormalGlassBlock[i] = new CSGlassBlock(blockIdNormal++, Material.glass, i, config.colorListListEn[i].length, false);
			csNormalGlassBlock[i].setUnlocalizedName("blockColoredStainedGlass" + i).setCreativeTab(creativeTab);
			GameRegistry.registerBlock(csNormalGlassBlock[i], CSGlassItem.class, "blockColoredStainedGlass" + i);

			// 光つき
			if (blockIdLighting > 1) {
				csLightiongGlassBlock[i] = new CSGlassBlock(blockIdLighting++, Material.glass, i, config.colorListListEn[i].length, false);
				csLightiongGlassBlock[i].setUnlocalizedName("blockColoredStainedGlassLighting" + i).setLightValue(1).setCreativeTab(creativeTab);
				GameRegistry.registerBlock(csLightiongGlassBlock[i], CSGlassItem.class, "blockColoredStainedGlassLighting" + i);
			}
			// 縁取り
			if (blockIdHemming > 1) {
				csHemmingGlassBlock[i] = new CSGlassBlock(blockIdHemming++, Material.glass, i, config.colorListListEn[i].length, true);
				csHemmingGlassBlock[i].setUnlocalizedName("blockColoredStainedGlassHemming" + i).setCreativeTab(creativeTab);
				GameRegistry.registerBlock(csHemmingGlassBlock[i], CSGlassItem.class, "blockColoredStainedGlassHemming" + i);
			}
			// 光+縁取り
			if (isBlockLandH) {
				csLandHGlassBlock[i] = new CSGlassBlock(blockIdLandH++, Material.glass, i, config.colorListListEn[i].length, true);
				csLandHGlassBlock[i].setUnlocalizedName("blockColoredStainedGlassLandH" + i).setLightValue(1).setCreativeTab(creativeTab);
				GameRegistry.registerBlock(csLandHGlassBlock[i], CSGlassItem.class, "blockColoredStainedGlassLandH" + i);
			}

			// メタデータごとの値を登録
			for (int j = 0; j < config.colorListListEn[i].length; j++) {
				// ノーマルブロック
				ItemStack blockNormal = new ItemStack(csNormalGlassBlock[i], 1, j);
				LanguageRegistry.addName(blockNormal, config.colorListListEn[i][j] + " Glass");
				GameRegistry.addRecipe(blockNormal, new Object[] { "XYZ", 'X', itemGlass, 'Y', itemWool[i], 'Z', itemDye[j] });

				// 光付き
				if (blockIdLighting > 1) {
					ItemStack blockLighting = new ItemStack(csLightiongGlassBlock[i], 1, j);
					LanguageRegistry.addName(blockLighting, config.colorListListEn[i][j] + " Glass L");
					GameRegistry.addRecipe(blockLighting, new Object[] { "XY", 'X', blockNormal, 'Y', itemGlowstoneDust });

					// 光+縁取り
					if (isBlockLandH) {
						ItemStack blockLandH = new ItemStack(csLandHGlassBlock[i], 1, j);
						LanguageRegistry.addName(blockLandH, config.colorListListEn[i][j] + " Glass LH");
						GameRegistry.addRecipe(blockLandH, new Object[] { "X", 'X', blockLighting }); // 縁取りとノーマルは相互変換
						GameRegistry.addRecipe(blockLighting, new Object[] { "X", 'X', blockLandH });
					}
				}

				// 縁取り
				if (blockIdHemming > 1) {
					ItemStack blockHemming = new ItemStack(csHemmingGlassBlock[i], 1, j);
					LanguageRegistry.addName(blockHemming, config.colorListListEn[i][j] + " Glass H");
					GameRegistry.addRecipe(blockHemming, new Object[] { "X", 'X', blockNormal }); // 縁取りとノーマルは相互変換
					GameRegistry.addRecipe(blockNormal, new Object[] { "X", 'X', blockHemming });
				}

			}
		}
	}

}
