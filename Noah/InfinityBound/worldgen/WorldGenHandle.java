package Noah.InfinityBound.worldgen;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTrees;
import cpw.mods.fml.common.IWorldGenerator;
import Noah.InfinityBound.InfinityBound;
import Noah.InfinityBound.blockdata.Blocks;
import Noah.InfinityBound.itemdata.Items;

public class WorldGenHandle implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        switch (world.provider.dimensionId)
        {
            case -1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
                break;

            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
                break;

            case 1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
                break;
        }
    }
    private void generateEnd(World world, Random rand, int chunkX, int chunkZ)
    {
    }
    private void generateSurface(World world, Random rand, int chunkX, int chunkZ)
    {
        for (int k = 0; k < 4; k++)
        {
            int bedrockXCoord = chunkX + rand.nextInt(16);
            int bedrockYCoord = rand.nextInt(14);
            int bedrockZCoord = chunkZ + rand.nextInt(16);
            new WorldGenMinable(Blocks.oreBedrock.blockID, 4).generate(world, rand, bedrockXCoord, bedrockYCoord, bedrockZCoord);
        }

        for (int kk = 0; kk < 8; kk++)
        {
            int intangXCoord = chunkX + rand.nextInt(16);
            int intangYCoord = rand.nextInt(35);
            int intangZCoord = chunkZ + rand.nextInt(16);
            new WorldGenMinable(Blocks.oreIntangible.blockID, 4).generate(world, rand, intangXCoord, intangYCoord, intangZCoord);
        }

        for (int l = 0; l < 16; l++)
        {
            int tfarXCoord = chunkX + rand.nextInt(16);
            int tfarYCoord = rand.nextInt(40);
            int tfarZCoord = chunkZ + rand.nextInt(16);
            new WorldGenMinable(Blocks.oreTfarcenim.blockID, 4).generate(world, rand, tfarXCoord, tfarYCoord, tfarZCoord);
        }

        for (int i = 0; i < 8; i++)
        {
            int appleXCoord = chunkX + rand.nextInt(16);
            int appleYCoord = rand.nextInt(90);
            int appleZCoord = chunkZ + rand.nextInt(16);
            new WorldGenTree(false, 6, 0, 0, false).generate(world, rand, appleXCoord, appleYCoord, appleZCoord);
        }

        for (int ii = 0; ii < 1; ii++)
        {
            int apple2XCoord = chunkX + rand.nextInt(16);
            int apple2YCoord = rand.nextInt(90);
            int apple2ZCoord = chunkZ + rand.nextInt(16);
            new WorldGenBigTree(false).generate(world, rand, apple2XCoord, apple2YCoord, apple2ZCoord);
        }

        for (int ll = 0; ll < 1; ll++)
        {
            int rainXCoord = chunkX + rand.nextInt(16);
            int rainYCoord = rand.nextInt(90);
            int rainZCoord = chunkZ + rand.nextInt(16);
            new WorldGenRainbowFlower(false).generate(world, rand, rainXCoord, rainYCoord, rainZCoord);
        }
    }
    private void generateNether(World world, Random rand, int chunkX, int chunkZ)
    {
    }
}
