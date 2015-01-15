package com.github.nurseangel.coloredstainedglass;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import coloredlightscore.src.api.CLApi;

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
	public static CSGlassBlock[] csNormalGlassBlock = new CSGlassBlock[139];
	public static CSGlassBlock[] csLightiongGlassBlock = new CSGlassBlock[139];

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
			// レシピ用にiを分割
			int wool1 = i / 16;
			int wool2 = i % 16;

			// ノーマルブロック
			// アイテム名は最後に勝手にメタデータが付くのでtile.coloredstainedglass_1_0.nameみたいになる
			csNormalGlassBlock[i] = new CSGlassBlock(Material.glass, i, config.useGlassHemming);
			csNormalGlassBlock[i].setBlockName("coloredstainedglass_" + i+ "_").setCreativeTab(creativeTab);
			GameRegistry.registerBlock(csNormalGlassBlock[i], CSGlassItemBlock.class, csNormalGlassBlock[i].getUnlocalizedName().replace("tile.", ""));
			// レシピ追加
			GameRegistry.addRecipe(new ItemStack(csNormalGlassBlock[i], config.craftQuantity, 0), new Object[] {"XYZ", 'X', itemGlass, 'Y', itemWool[wool1], 'Z', itemDye[wool2] });

			// とりあえずインスタンス保持
			ItemStack blockNormal = new ItemStack(csNormalGlassBlock[i], 1, 0);

			// 縁取りがあれば
			if(config.useGlassHemming){
				// 縁取りとノーマルは相互変換
				ItemStack blockHemming = new ItemStack(csNormalGlassBlock[i], 1, 1);
				GameRegistry.addRecipe(blockHemming, new Object[] { "X", 'X', blockNormal });
				GameRegistry.addRecipe(blockNormal, new Object[] { "X", 'X', blockHemming });
			}


			// 光源ブロック
			if (config.useGlassLighting) {
				csLightiongGlassBlock[i] = new CSGlassBlock(Material.glass, i, config.useGlassLandH);
				csLightiongGlassBlock[i].setBlockName("coloredstainedglass_l_" + i+ "_").setCreativeTab(creativeTab);
				GameRegistry.registerBlock(csLightiongGlassBlock[i], CSGlassItemBlock.class, csLightiongGlassBlock[i].getUnlocalizedName().replace("tile.", ""));
				ItemStack blockLighting = new ItemStack(csLightiongGlassBlock[i], 1, 0);

				// 色を追加
				if(config.useColoredLightApi){
					int colorR = Integer.parseInt(config.colorListListEn[i][1]);
					int colorG = Integer.parseInt(config.colorListListEn[i][2]);
					int colorB = Integer.parseInt(config.colorListListEn[i][3]);
					int colorM = Math.max(colorR, Math.max(colorG,colorB));
					CLApi.setBlockColorRGB(csLightiongGlassBlock[i],colorR,colorG, colorB, colorM);
				}else{
					// 色を使わない場合はデフォルトの光
					csLightiongGlassBlock[i].setLightLevel(1.0F);
				}

				// レシピ追加
				GameRegistry.addRecipe(blockLighting,	new Object[] { "XY", 'X', blockNormal, 'Y', itemGlowstoneDust });

				// 光源+縁取りがあれば
				if (config.useGlassLandH) {
					ItemStack blockLandH = new ItemStack(csLightiongGlassBlock[i], 1, 1);
					GameRegistry.addRecipe(blockLandH, new Object[] { "X", 'X', blockLighting });
					GameRegistry.addRecipe(blockLighting, new Object[] { "X", 'X', blockLandH });
				}
			}


		}
	}

}
