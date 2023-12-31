package fun.lightai.moreeffect.mixin;

import fun.lightai.moreeffect.More_effect;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class MixinTakeDamage {
    @Shadow
    public abstract boolean hasStatusEffect(StatusEffect effect);

    @Shadow
    public abstract Random getRandom();

    @ModifyVariable(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At(value = "HEAD"), ordinal = 0, argsOnly = true)
    private float injected(float amount) {
        if (this.hasStatusEffect(More_effect.EFFECT_BLOOD_CONTRACT)) {
            if (getRandom().nextDouble() < 0.3) {
                amount *= 2;
            }
        }
        World world = ((Entity) (Object) this).getWorld();
        if (world != null) {
            long nowTime = world.getTimeOfDay();
            if (this.hasStatusEffect(More_effect.EFFECT_SUN_BLESS) && 1000 <= nowTime && nowTime < 13000) {
                amount -= 2;
            }
            if (this.hasStatusEffect(More_effect.EFFECT_MOON_BLESS) && (0 <= nowTime && nowTime < 1000 || 13000 <= nowTime && nowTime < 24000)) {
                amount -= 2;
            }
        }
        return amount;
    }
}
