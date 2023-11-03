package fun.lightai.moreeffect;

import fun.lightai.moreeffect.command.ClearEffectCommand;
import fun.lightai.moreeffect.command.GiveRandomEffectCommand;
import fun.lightai.moreeffect.effect.*;
import fun.lightai.moreeffect.event.AfterKillHandler;
import fun.lightai.moreeffect.event.AfterRespawnEvent;
import fun.lightai.moreeffect.event.AllowDamageHandler;
import fun.lightai.moreeffect.event.AllowDeathHandler;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class More_effect implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("moreeffect");
    public static final StatusEffect EFFECT_EXP = new ExpStatusEffect();
    public static final StatusEffect EFFECT_ESP = new ESPEffect();
    public static final StatusEffect EFFECT_PHOENIX = new PhoenixEffect();
    public static final StatusEffect EFFECT_RAGE = new RageEffect();
    public static final StatusEffect EFFECT_UNDEAD = new UndeadEffect();
    public static final StatusEffect EFFECT_FALL_GUARD = new FallGuardEffect();
    public static final StatusEffect EFFECT_BLOOD_CONTRACT = new BloodContractEffect();
    public static final StatusEffect EFFECT_ADAPTIVE_DEFENSE = new AdaptiveDefenseEffect();
    public static final StatusEffect EFFECT_ADAPTIVE_DEFENSE_CD = new AdaptiveDefenseEffectCD();
    public static final StatusEffect EFFECT_MOON_BLESS = new MoonBlessEffect();
    public static final StatusEffect EFFECT_SUN_BLESS = new SunBlessEffect();
    public static final StatusEffect EFFECT_REFLEX = new ReflexEffect();
    public static final StatusEffect EFFECT_CHARGE = new ChargeEffect();
    public static final StatusEffect EFFECT_GIANT_SLAYER = new GiantSlayerEffect();
    public static final StatusEffect EFFECT_LAST_STAND = new LastStandEffect();
    public static final StatusEffect EFFECT_FLAMING = new FlamingEffect();
    public static final StatusEffect EFFECT_SNEAK = new SneakEffect();
    public static final StatusEffect EFFECT_VAMPIRE = new VampireEffect();
    public static final StatusEffect EFFECT_INSTANT_KILL = new InstantKillEffect();
    public static final StatusEffect EFFECT_CURTAIN = new CurtainEffect();
    public static final StatusEffect EFFECT_LUCKY_COIN = new LuckyCoinEffect();
    public static final StatusEffect EFFECT_PEACEFUL = new PeacefulEffect();
    public static final StatusEffect[] ALL_EFFECT = {
            StatusEffect.byRawId(1),  // speed
            StatusEffect.byRawId(3),  // 急迫
            StatusEffect.byRawId(8),  // jump
            StatusEffect.byRawId(12), // 防火
            EFFECT_ESP, EFFECT_PHOENIX,
            EFFECT_RAGE, EFFECT_FALL_GUARD, EFFECT_BLOOD_CONTRACT,
            EFFECT_ADAPTIVE_DEFENSE, EFFECT_MOON_BLESS,
            EFFECT_SUN_BLESS, EFFECT_REFLEX, EFFECT_CHARGE, EFFECT_GIANT_SLAYER,
            EFFECT_LAST_STAND,EFFECT_FLAMING, EFFECT_SNEAK, EFFECT_VAMPIRE,
            EFFECT_INSTANT_KILL, EFFECT_CURTAIN, EFFECT_LUCKY_COIN, EFFECT_PEACEFUL};
    @Override
    public void onInitialize() {

        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "exp"), EFFECT_EXP);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "esp"), EFFECT_ESP);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "phoenix"), EFFECT_PHOENIX);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "rage"), EFFECT_RAGE);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "undead"), EFFECT_UNDEAD);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "fall_guard"), EFFECT_FALL_GUARD);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "blood_contract"), EFFECT_BLOOD_CONTRACT);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "adaptive_defense"), EFFECT_ADAPTIVE_DEFENSE);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "adaptive_defense_cd"), EFFECT_ADAPTIVE_DEFENSE_CD);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "moon_bless"), EFFECT_MOON_BLESS);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "sun_bless"), EFFECT_SUN_BLESS);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "reflex"), EFFECT_REFLEX);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "charge"), EFFECT_CHARGE);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "giant_slayer"), EFFECT_GIANT_SLAYER);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "last_stand"), EFFECT_LAST_STAND);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "flaming"), EFFECT_FLAMING);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "sneak"), EFFECT_SNEAK);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "vampire"), EFFECT_VAMPIRE);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "instant_kill"), EFFECT_INSTANT_KILL);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "curtain"), EFFECT_CURTAIN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "lucky_coin"), EFFECT_LUCKY_COIN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("moreeffect", "peaceful"), EFFECT_PEACEFUL);

//        AttackEntityCallback.EVENT.register(new AttackEntityHandler());
        ServerLivingEntityEvents.ALLOW_DEATH.register(new AllowDeathHandler());
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(new AllowDamageHandler());
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(new AfterKillHandler());
        ServerPlayerEvents.AFTER_RESPAWN.register(new AfterRespawnEvent());


        CommandRegistrationCallback.EVENT.register(new GiveRandomEffectCommand());
        CommandRegistrationCallback.EVENT.register(new ClearEffectCommand());
    }
}