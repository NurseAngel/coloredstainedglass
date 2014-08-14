package com.github.nurseangel.coloredstainedglass;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * カラー
 *
 */
public class CSGlassBlock extends BlockBreakable {

	/** メタデータ使用数 */
	private int colorListLength;
	/** テクスチャファイル名1文字目 */
	private int textureIndex;
	/** glassかhemmingか */
	private boolean isHemming;
	/** アイコン */
	IIcon[] icon = new IIcon[16];

	/**
	 * コンストラクタ
	 *
	 * @param material
	 *            素材
	 * @param textureIndex
	 *            使用するテクスチャ番号1
	 * @param colorListLength
	 *            メタデータ使用数=使用するテクスチャ番号2
	 * @param isHemming
	 *            縁取りあり
	 */
	public CSGlassBlock(Material material, int textureIndex, int colorListLength, boolean isHemming) {
		// dummyはregisterBlockIconsでしか使ってないのでダミーで問題ない
		super("dummy", material, false);
		setStepSound(Block.soundTypeGlass);
		setHardness(0.2F);

		this.textureIndex = textureIndex;
		this.colorListLength = colorListLength;
		this.isHemming = isHemming;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * 使用するアイコンを返す
	 * @param 取得する方角
	 * @param メタデータ
	 */
	public IIcon getIcon(int par1, int par2) {
		return icon[par2];
	}

	/**
	 * 使用するアイコンをセット
	 *
	 * @param iconRegister
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		int loop = 0;
		for (int i = 0; i < colorListLength; i++) {
			// 素材名からアイコンファイル名を作成
			String texturePath = isHemming ? (Reference.TEXTURE_PATH + "hemming-" + textureIndex + "-" + i)
					: (Reference.TEXTURE_PATH + "glass-" + textureIndex + "-" + i);
			icon[i] = iconRegister.registerIcon(texturePath);
		}
	}

	/**
	 * ドロップアイテムのメタデータを有効に
	 */
	@Override
	public int damageDropped(int par1) {
		return par1;
	}

	/**
	 * 通常はドロップしない
	 */
	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}

	/**
	 * レンダリング方法を変更する?<br />
	 * 1で透明、0で不透明になる<br />
	 * しかし何故かバニラのガラスは0。何故?
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 1;
	}

	/**
	 * 1m四方のブロックであるか。<br />
	 * 透過ブロックはfalse
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * 普通じゃないブロックはfalseを返す
	 */
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/**
	 * シルクタッチ回収
	 */
	@Override
	protected boolean canSilkHarvest()
	{
		return true;
	}

	/**
	 * クリエイティブタブに表示
	 *
	 * @param Item
	 *            アイテム
	 * @param タブ
	 * @param 追加するリスト
	 */
	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int var4 = 0; var4 < colorListLength; ++var4) {
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}

}
