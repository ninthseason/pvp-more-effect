package fun.lightai.moreeffect.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class SunBlessEffect extends StatusEffect {
    public SunBlessEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xFF3B00);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
    }
}
