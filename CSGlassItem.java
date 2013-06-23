package mods.nurseangel.coloredstainedglass;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class CSGlassItem extends ItemBlock {
	/**
	 * コンストラクタ
	 *
	 * @param アイテムID
	 */
	public CSGlassItem(int i) {
		super(i);
		setHasSubtypes(true);
		setMaxDamage(0);
		setMaxStackSize(64);
	}

	/**
	 * ブロック設置時のメタデータ。指定しないと0
	 *
	 * @param int アイテムのダメージ値
	 * @return int メタデータ
	 */
	@Override
	public int getMetadata(int i) {
		return i;
	}

	/**
	 * 内部的ブロックの名前を返す<br />
	 * オーバーライドしないと同じBlockIDのアイテムが全部同名になる。
	 *
	 * @param ItenStack
	 * @return String
	 */
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		int i = itemstack.getItemDamage();
		return (new StringBuilder(getUnlocalizedName())).append(i).toString();
	}
}
