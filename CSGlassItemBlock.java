package com.github.nurseangel.coloredstainedglass;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class CSGlassItemBlock extends ItemBlockWithMetadata {
	/**
	 * コンストラクタ
	 *
	 * @param Blockをそのまま渡す
	 */
	public CSGlassItemBlock(Block block) {
		super(block, block);
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

	/**
	 * TODO 光るブロックをわかりやすくするためポーションのようにアイテム欄でも光らせたい<br />
	 * hasEffectではなかった。<br />
	 */
}
