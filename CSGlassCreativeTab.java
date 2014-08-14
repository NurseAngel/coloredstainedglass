package com.github.nurseangel.coloredstainedglass;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CSGlassCreativeTab extends CreativeTabs {

	public CSGlassCreativeTab(String label) {
		super(label);
	}

	/**
	 * クリエイティブタブのラベル
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return Reference.MOD_NAME;
	}

	/**
	 * タブのアイコン
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return Item.getItemFromBlock(ColoredStainedGlass.csNormalGlassBlock[1]);
	}
}
