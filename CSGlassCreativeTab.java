package mods.nurseangel.coloredstainedglass;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CSGlassCreativeTab extends CreativeTabs {

	public CSGlassCreativeTab(String label) {
		super(label);
	}

	/**
	 * 使用するアイコンのBlockID
	 * @return int
	 */
	public int getTabIconItemIndex() {
		return Config.blockIdNormal+1;
	}

	/**
	 *クリエイティブタブのラベル
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return Reference.MOD_NAME;
	}
}
