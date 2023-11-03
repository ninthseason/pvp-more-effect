package fun.lightai.moreeffect.effect;

import fun.lightai.moreeffect.More_effect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;


public class AdaptiveDefenseEffectCD extends StatusEffect {

    public AdaptiveDefenseEffectCD() {
        super(StatusEffectCategory.BENEFICIAL, 0x999999);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        entity.addStatusEffect(new StatusEffectInstance(More_effect.EFFECT_ADAPTIVE_DEFENSE, -1));
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
    }
}
