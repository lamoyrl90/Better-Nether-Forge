package com.redd90.betternether.item;

import java.util.function.Supplier;

import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;

public class BNSpawnEggItem extends SpawnEggItem {
    private final Supplier<EntityType<?>> entityTypeSupplier;

    public BNSpawnEggItem(Supplier<EntityType<?>> entityTypeSupplier, int primaryColor, int secondaryColor, Properties properties)
    {
        super(null, primaryColor, secondaryColor, properties);
        this.entityTypeSupplier = entityTypeSupplier;
    }

    @Override
    public EntityType<?> getType(CompoundNBT compound)
    {
        if(compound != null && compound.contains("EntityTag", 10))
        {
            CompoundNBT entityTag = compound.getCompound("EntityTag");

            if(entityTag.contains("id", 8))
            {
                return EntityType.byKey(entityTag.getString("id")).orElse(this.entityTypeSupplier.get());
            }
        }

        return this.entityTypeSupplier.get();
    }
}
