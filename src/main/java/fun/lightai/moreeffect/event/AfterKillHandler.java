package fun.lightai.moreeffect.event;

import fun.lightai.moreeffect.More_effect;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.world.ServerWorld;

public class AfterKillHandler implements ServerEntityCombatEvents.AfterKilledOtherEntity{
    @Override
    public void afterKilledOtherEntity(ServerWorld world, Entity entity, LivingEntity killedEntity) {
        if (entity instanceof LivingEntity) {
            if (((LivingEntity) entity).hasStatusEffect(More_effect.EFFECT_CURTAIN)) {
                StatusEffect invisibilityEffect = StatusEffect.byRawId(14);
                if (invisibilityEffect != null) {
                    ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(invisibilityEffect, 5 * 20));
                }
            }
        }
    }
}
