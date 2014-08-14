package com.github.nurseangel.coloredstainedglass;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.github.nurseangel.coloredstainedglass.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
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
	@Mod.EventHandler
	public void modPreInit(FMLPreInitializationEvent event) {
		config = new Config(event);
	}

	/**
	 * メイン処理的なもの
	 *
	 * @param event
	 */
	@Mod.EventHandler
	public void modInit(FMLInitializationEvent event) {
		// ノーマルがなければ何もしない
		if (config.useGlass) {
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

		boolean isBlockLandH = config.useGlassLandH && config.useGlassLighting && config.useGlassHemming;

		// レシピ用にとりあえずnewしておく
		ItemStack itemGlass = new ItemStack(Blocks.glass, 1);
		ItemStack itemGlowstoneDust = new ItemStack(Items.glowstone_dust, 1);
		ItemStack[] itemDye = new ItemStack[16];
		ItemStack[] itemWool = new ItemStack[16];
		for (int loop = 0; loop < 16; loop++) {
			itemDye[loop] = new ItemStack(Items.dye, 1, loop);
			itemWool[loop] = new ItemStack(Blocks.wool, 1, loop);
		}

		// 全ブロックリストでループ
		for (int i = 0; i < config.colorListListEn.length; i++) {
			// ノーマルブロック
			csNormalGlassBlock[i] = new CSGlassBlock(Material.glass, i, config.colorListListEn[i].length, false);
			csNormalGlassBlock[i].setBlockName("coloredstainedglass_" + i + "_").setCreativeTab(creativeTab);
			GameRegistry.registerBlock(csNormalGlassBlock[i], CSGlassItemBlock.class,
					csNormalGlassBlock[i].getUnlocalizedName().replace("tile.", ""));

			// 光つき
			if (config.useGlassLighting) {

				csLightiongGlassBlock[i] = new CSGlassBlock(Material.glass, i, config.colorListListEn[i].length, false);
				csLightiongGlassBlock[i].setBlockName("coloredstainedglass_l_" + i + "_").setLightLevel(1.0F)
						.setCreativeTab(creativeTab);
				GameRegistry.registerBlock(csLightiongGlassBlock[i], CSGlassItemBlock.class,
						csLightiongGlassBlock[i].getUnlocalizedName().replace("tile.", ""));
			}

			// 縁取り
			if (config.useGlassHemming) {
				csHemmingGlassBlock[i] = new CSGlassBlock(Material.glass, i, config.colorListListEn[i].length, true);
				csHemmingGlassBlock[i].setBlockName("coloredstainedglass_h_" + i + "_").setCreativeTab(creativeTab);

				GameRegistry.registerBlock(csHemmingGlassBlock[i], CSGlassItemBlock.class,
						csHemmingGlassBlock[i].getUnlocalizedName().replace("tile.", ""));
			}

			// 光+縁取り
			if (isBlockLandH) {
				csLandHGlassBlock[i] = new CSGlassBlock(Material.glass, i, config.colorListListEn[i].length, true);
				csLandHGlassBlock[i].setBlockName("coloredstainedglass_l_h_" + i + "_").setLightLevel(1.0F)
						.setCreativeTab(creativeTab);
				GameRegistry.registerBlock(csLandHGlassBlock[i], CSGlassItemBlock.class,
						csLandHGlassBlock[i].getUnlocalizedName().replace("tile.", ""));
			}

			// メタデータごとの値を登録
			for (int j = 0; j < config.colorListListEn[i].length; j++) {
				// ノーマルブロック
				ItemStack blockNormal = new ItemStack(csNormalGlassBlock[i], 1, j);
				GameRegistry.addRecipe(new ItemStack(csNormalGlassBlock[i], 2, j), new Object[] {
						"XYZ", 'X', itemGlass, 'Y', itemWool[i], 'Z', itemDye[j] });

				// 光付き
				if (config.useGlassLighting) {
					ItemStack blockLighting = new ItemStack(csLightiongGlassBlock[i], 1, j);
					GameRegistry.addRecipe(blockLighting,
							new Object[] { "XY", 'X', blockNormal, 'Y', itemGlowstoneDust });

					// 光+縁取り
					if (isBlockLandH) {
						ItemStack blockLandH = new ItemStack(csLandHGlassBlock[i], 1, j);
						GameRegistry.addRecipe(blockLandH, new Object[] { "X", 'X', blockLighting }); // 縁取りとノーマルは相互変換
						GameRegistry.addRecipe(blockLighting, new Object[] { "X", 'X', blockLandH });
					}
				}

				// 縁取り
				if (config.useGlassHemming) {
					ItemStack blockHemming = new ItemStack(csHemmingGlassBlock[i], 1, j);
					GameRegistry.addRecipe(blockHemming, new Object[] { "X", 'X', blockNormal }); // 縁取りとノーマルは相互変換
					GameRegistry.addRecipe(blockNormal, new Object[] { "X", 'X', blockHemming });
				}

			}
		}
	}

}
