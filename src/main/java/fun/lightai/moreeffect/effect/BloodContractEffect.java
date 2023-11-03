package fun.lightai.moreeffect.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class BloodContractEffect extends StatusEffect {
    public BloodContractEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xF3FF00);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity != null && entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH) != null) {
            entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addPersistentModifier(new EntityAttributeModifier("thisnamenotmatter", entity.getMaxHealth(), EntityAttributeModifier.Operation.ADDITION));
            entity.setHealth(entity.getHealth() + entity.getMaxHealth() / 2);
        }
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity != null && entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH) != null) {
            entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addPersistentModifier(new EntityAttributeModifier("thisnamenotmatter", -entity.getMaxHealth() / 2, EntityAttributeModifier.Operation.ADDITION));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
    }
}
