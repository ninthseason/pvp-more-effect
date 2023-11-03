package fun.lightai.moreeffect.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ReflexEffect extends StatusEffect {
    public ReflexEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x00FFAA);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
    }
}
