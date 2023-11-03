package fun.lightai.moreeffect.mixin;

import fun.lightai.moreeffect.More_effect;
import fun.lightai.moreeffect.utils.Calc;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public abstract class MixinMakeDamage {
    @Shadow public abstract boolean canModifyBlocks();

    @Redirect(method = "attack(Lnet/minecraft/entity/Entity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private boolean injected(Entity target, DamageSource source, float amount) {
        PlayerEntity self = ((PlayerEntity) (Object) this);
        if (self.hasStatusEffect(More_effect.EFFECT_CHARGE)) {
            amount *= self.getMovementSpeed() * 10;
        }
        if (self.hasStatusEffect(More_effect.EFFECT_GIANT_SLAYER)) {
            if (target instanceof LivingEntity && ((LivingEntity) target).getHealth() > self.getHealth()) {
                float healthDiff = ((LivingEntity) target).getHealth() - self.getHealth();
                if (healthDiff > 10.0) {
                    healthDiff = 10.0f;
                }
                amount += 6 * healthDiff / 10;
            }
        }
        if (self.hasStatusEffect(More_effect.EFFECT_PEACEFUL)) {
            double disSquare = Calc.distanceSquare(self.getPos(), target.getPos());
            if (disSquare > 100) {
                disSquare = 100;
            }
            amount += 6 * (float)disSquare / 100;
        }
        if (self.hasStatusEffect(More_effect.EFFECT_LAST_STAND)) {
            amount += 6 * (self.getMaxHealth() - self.getHealth()) / self.getMaxHealth();
        }

        if (self.hasStatusEffect(More_effect.EFFECT_LUCKY_COIN)) {
            double threw = self.getRandom().nextDouble();
            if (0 <= threw && threw < 0.2) {
                amount *= 0.5f;
            } else if (0.2 <= threw && threw < 0.4) {
                amount *= 0.75f;
            } else if (0.6 <= threw && threw < 0.8) {
                amount *= 1.25f;
            } else if (0.8 <= threw && threw < 1) {
                amount *= 1.5f;
            }
        }
        if (self.hasStatusEffect(More_effect.EFFECT_INSTANT_KILL)) {
            if (self.getRandom().nextDouble() < 0.01) {
                amount += 10000;
            }
        }
        return target.damage(source, amount);
    }
}
