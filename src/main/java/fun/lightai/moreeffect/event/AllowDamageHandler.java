package fun.lightai.moreeffect.event;

import fun.lightai.moreeffect.More_effect;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;

public class AllowDamageHandler implements ServerLivingEntityEvents.AllowDamage {

    @Override
    public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
        if (entity.hasStatusEffect(More_effect.EFFECT_ADAPTIVE_DEFENSE)) {
            entity.removeStatusEffect(More_effect.EFFECT_ADAPTIVE_DEFENSE);
            entity.addStatusEffect(new StatusEffectInstance(More_effect.EFFECT_ADAPTIVE_DEFENSE_CD, 10*20));
            return false;
        }
        if (entity.hasStatusEffect(More_effect.EFFECT_UNDEAD)) {
            return false;
        }
        if (entity.hasStatusEffect(More_effect.EFFECT_FALL_GUARD) && source.getType().msgId().equals("fall")) {
            return false;
        }
        if (entity.hasStatusEffect(More_effect.EFFECT_REFLEX)) {
            if (source.getAttacker() != null) {
                source.getAttacker().damage(entity.getDamageSources().cactus(), 2);
            }
        }
        if (source.getAttacker() instanceof LivingEntity) {
            if (((LivingEntity) source.getAttacker()).hasStatusEffect(More_effect.EFFECT_FLAMING)) {
                entity.setOnFireFor(1);
            }
            if (entity.hasStatusEffect(More_effect.EFFECT_FLAMING)) {
                source.getAttacker().setOnFireFor(1);
            }
            if (((LivingEntity) source.getAttacker()).hasStatusEffect(More_effect.EFFECT_SNEAK)) {
                if (entity.getMaxHealth() > 1) {
                    entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addPersistentModifier(new EntityAttributeModifier("thisnamenotmatter", -1, EntityAttributeModifier.Operation.ADDITION));
                }
            }
            if (((LivingEntity) source.getAttacker()).hasStatusEffect(More_effect.EFFECT_VAMPIRE)) {
                ((LivingEntity) source.getAttacker()).setHealth(((LivingEntity) source.getAttacker()).getHealth() + 1);
            }
        }
        if (entity.hasStatusEffect(More_effect.EFFECT_ADAPTIVE_DEFENSE_CD)) {
            entity.getActiveStatusEffects().remove(More_effect.EFFECT_ADAPTIVE_DEFENSE_CD);
            entity.addStatusEffect(new StatusEffectInstance(More_effect.EFFECT_ADAPTIVE_DEFENSE_CD, 10*20));
        }
        return true;
    }
}
