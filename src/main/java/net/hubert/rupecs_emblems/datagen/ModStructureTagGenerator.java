package net.hubert.rupecs_emblems.datagen;

import com.sun.jna.Structure;
import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.item.ModItems;
import net.hubert.rupecs_emblems.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraft.data.worldgen.Structures;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModStructureTagGenerator extends StructureTagsProvider {


    public ModStructureTagGenerator(PackOutput p_256522_, CompletableFuture<HolderLookup.Provider> p_256661_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_256522_, p_256661_, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(ModTags.Structures.BASTION)
                .add(
                        BuiltinStructures.BASTION_REMNANT
                );
    }
}
