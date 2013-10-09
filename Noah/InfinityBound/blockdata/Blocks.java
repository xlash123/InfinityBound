package Noah.InfinityBound.blockdata;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import Noah.InfinityBound.InfinityBound;
import Noah.InfinityBound.blockdata.*;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

public class Blocks extends Block
{
    public Blocks(int id, Material material)
    {
        super(id, material);
    }
    public static final Block oreBedrock = (new BlockOre(600).setHardness(40F).setResistance(1000000F).setStepSound(soundStoneFootstep).setCreativeTab(InfinityBound.tabInfinityBound).setUnlocalizedName("oreBedrock"));
    public static final Block oreTfarcenim = (new BlockOre(601).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setCreativeTab(InfinityBound.tabInfinityBound).setUnlocalizedName("oreTfarcenim"));
    public static final Block blockBedrock = (new BlockOreStorage(602).setHardness(40F).setResistance(1000000F).setStepSound(soundMetalFootstep).setCreativeTab(InfinityBound.tabInfinityBound).setUnlocalizedName("blockBedrock"));
    public static final Block blockTfarcenim = (new BlockOreStorage(603).setHardness(3.0F).setResistance(5.0F).setStepSound(soundMetalFootstep).setCreativeTab(InfinityBound.tabInfinityBound).setUnlocalizedName("blockTfarcenim"));
    public static final BlockLeaves leaves = (BlockLeaves)(new BlockLeaves(604).setHardness(0.2F).setLightOpacity(1).setStepSound(soundGrassFootstep).setUnlocalizedName("leaves").setCreativeTab(InfinityBound.tabInfinityBound));
    public static final Block sapling = (new BlockSapling(605).setHardness(0.0F).setStepSound(soundGrassFootstep).setUnlocalizedName("sapling").setCreativeTab(InfinityBound.tabInfinityBound));
    public static final Block appleCrop = (new BlockCrops(606).setHardness(0.0F).setStepSound(soundGrassFootstep).setUnlocalizedName("appleCrop"));
    public static final BlockFlowerRainbow flowerRainbow = (BlockFlowerRainbow)(new BlockFlowerRainbow(650).setStepSound(soundGrassFootstep).setUnlocalizedName("flowerRainbow").setCreativeTab(InfinityBound.tabInfinityBound));
    public static final Block stoneNoHit = (new BlockNoHit(608, Material.rock, "stone", "Minecraft").setStepSound(soundStoneFootstep).setHardness(1.5F).setResistance(10.0F).setUnlocalizedName("stoneNoHit").setCreativeTab(InfinityBound.tabInfinityBound));
    public static final Block cobblestoneNoHit = (new BlockNoHit(609, Material.rock, "cobblestone", "Minecraft").setStepSound(soundStoneFootstep).setHardness(2.0F).setResistance(10.0F).setUnlocalizedName("cobblestoneNoHit").setCreativeTab(InfinityBound.tabInfinityBound));
    public static final Block cutter = (new BlockCutter(610).setHardness(3.5F).setResistance(5.5F).setStepSound(soundStoneFootstep).setUnlocalizedName("cutter").setCreativeTab(InfinityBound.tabInfinityBound));
    public static final Block woolRainbow = (new Blocks(611, Material.cloth).setHardness(0.8F).setStepSound(soundClothFootstep).setUnlocalizedName("woolRainbow").setCreativeTab(InfinityBound.tabInfinityBound));
    public static final Block oreIntangible = (new BlockOre(612).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreIntangible").setCreativeTab(InfinityBound.tabInfinityBound));
    public static final Block woolRainbowNoHit = (new BlockNoHit(613, Material.cloth, "woolRainbow", "InfinityBound").setHardness(0.8F).setStepSound(soundClothFootstep).setUnlocalizedName("woolRainbowNoHit").setCreativeTab(InfinityBound.tabInfinityBound));
    public static final Block glassNoHit = (new BlockGlassNoHit(614, Material.glass, "glass", "Minecraft", false).setHardness(0.3F).setStepSound(soundGlassFootstep).setUnlocalizedName("glassNoHit").setCreativeTab(InfinityBound.tabInfinityBound));
    public static final Block planksNoHit = (new BlockWoodNoHit(615, Material.wood, "planks").setHardness(2.0F).setResistance(5.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("planksNoHit").setCreativeTab(InfinityBound.tabInfinityBound));
    public static final BlockNetherChest chestNether = (BlockNetherChest)(new BlockNetherChest(616, 0).setHardness(2.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("chestNether"));
    public static final Block fuser = (new BlockFuser(617).setHardness(3.5F).setResistance(6.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("fuser").setCreativeTab(InfinityBound.tabInfinityBound));

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon("InfinityBound:" + (this.getUnlocalizedName().substring(5)));
    }
}