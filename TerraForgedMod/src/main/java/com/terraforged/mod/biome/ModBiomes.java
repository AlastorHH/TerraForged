/*
 *
 * MIT License
 *
 * Copyright (c) 2020 TerraForged
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.terraforged.mod.biome;

import com.terraforged.api.biome.BiomeVariant;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes {

    private static final ArrayList<BiomeVariant> biomes = new ArrayList<>();

    public static final Biome COLD_STEPPE = register(new ColdSteppe());
    public static final Biome SAVANNA_SCRUB = register(new SavannaScrub());
    public static final Biome SHATTERED_SAVANNA_SCRUB = register(new ShatteredSavannaScrub());
    public static final Biome SNOWY_TAIGA_SCRUB = register(new SnowyTaigaScrub());
    public static final Biome STEPPE = register(new Steppe());
    public static final Biome TAIGA_SCRUB = register(new TaigaScrub());
    public static final Biome WARM_BEACH = register(new WarmBeach());
    public static final Biome MARSHLAND = register(new Marshland());
//    public static final Biome FIR_FOREST = register(new FirForest());
//    public static final Biome FLOWER_PLAINS = register(new FlowerPlains());

    private static Biome register(BiomeVariant biome) {
        biomes.add(biome);
        return biome;
    }

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Biome> event) {
        biomes.forEach(biome -> {
            event.getRegistry().register(biome);
            BiomeDictionary.makeBestGuess(biome);
            BiomeDictionary.addTypes(biome, BiomeDictionary.Type.OVERWORLD);
            if (BiomeDictionary.getTypes(biome.getBase()).contains(BiomeDictionary.Type.RARE)) {
                BiomeDictionary.addTypes(biome, BiomeDictionary.Type.RARE);
            }
        });
        biomes.clear();
        biomes.trimToSize();
    }
}
