package fun.lightai.moreeffect.event;

import fun.lightai.moreeffect.More_effect;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;

public class AllowDeathHandler implements ServerLivingEntityEvents.AllowDeath {
    @Override
    public boolean allowDeath(LivingEntity entity, DamageSource damageSource, float damageAmount) {
        if (entity.hasStatusEffect(More_effect.EFFECT_PHOENIX) && entity.getHealth() <= 0.0) {
            entity.setHealth(1.0f);
            entity.removeStatusEffect(More_effect.EFFECT_PHOENIX);
            return false;
        }
        if (entity.hasStatusEffect(More_effect.EFFECT_RAGE) && entity.getHealth() <= 0.0) {
            entity.setHealth(1.0f);
            entity.removeStatusEffect(More_effect.EFFECT_RAGE);
            entity.addStatusEffect(new StatusEffectInstance(StatusEffect.byRawId(20), 5*20));
            return false;
        }
        return true;
    }
}
