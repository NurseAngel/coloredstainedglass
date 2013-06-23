package mods.nurseangel.coloredstainedglass;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * カラー
 *
 */
public class CSGlassBlock extends Block {

	/** メタデータ使用数 */
	private int colorListLength;
	/** テクスチャファイル名1文字目 */
	private int textureIndex;
	/** glassかhemmingか */
	private boolean isHemming;
	/** アイコン */
	Icon[] icon = new Icon[16];

	public CSGlassBlock(int i, Material material, int textureIndex, int colorListLength, boolean isHemming) {
		super(i, material);
		setStepSound(Block.soundGlassFootstep);
		setHardness(0.3F);

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
	public Icon getIcon(int par1, int par2) {
		return icon[par2];
	}

	/**
	 * 使用するアイコンをセット
	 *
	 * @param iconRegister
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		int loop = 0;
		for (int i = 0; i < colorListLength; i++) {
			// 素材名からアイコンファイル名を作成
			String texturePath = isHemming ? (Reference.TEXTURE_PATH + "hemming-" + textureIndex + "-" + i) : (Reference.TEXTURE_PATH + "glass-" + textureIndex + "-" + i);
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
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    /**
     * レンダリング方法を変更する?<br />
     * 1にすると透明になる
     */
    public int getRenderBlockPass()
    {
        return 1;
    }
    /**
     * falseだと透明?
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * 普通じゃないブロックはfalseを返す
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * シルクタッチ回収
     */
    protected boolean canSilkHarvest()
    {
        return true;
    }

	/**
	 * クリエイティブタブに表示
	 *
	 * @param int ブロックID
	 * @param タブ
	 * @param 追加するリスト
	 */
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int var4 = 0; var4 < colorListLength; ++var4) {
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}
}
