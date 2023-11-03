package fun.lightai.moreeffect.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import fun.lightai.moreeffect.More_effect;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class ClearEffectCommand implements CommandRegistrationCallback {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("clear_effect")
                .requires(source -> source.hasPermissionLevel(1))
                .executes(ClearEffectCommand::run));
    }

    private static int run(CommandContext<ServerCommandSource> context) {
        ServerPlayerEntity player = context.getSource().getPlayer();
        if (player != null) {
            if (player.hasStatusEffect(More_effect.EFFECT_ADAPTIVE_DEFENSE_CD)) {
                player.removeStatusEffect(More_effect.EFFECT_ADAPTIVE_DEFENSE_CD);
            }
            if (player.hasStatusEffect(More_effect.EFFECT_RAGE)) {
                player.removeStatusEffect(More_effect.EFFECT_RAGE);
            }
            Object[] effectArray = player.getStatusEffects().toArray();
            for (Object statusEffectInstance : effectArray) {
                player.removeStatusEffect(((StatusEffectInstance) statusEffectInstance).getEffectType());
            }
        }
        return 0;
    }
}
