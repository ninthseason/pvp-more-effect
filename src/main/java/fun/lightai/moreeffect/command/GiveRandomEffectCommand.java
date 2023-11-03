package fun.lightai.moreeffect.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.context.CommandContext;
import fun.lightai.moreeffect.More_effect;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GiveRandomEffectCommand implements CommandRegistrationCallback {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("random_effect")
                .requires(source -> source.hasPermissionLevel(1))
                .executes(GiveRandomEffectCommand::run));
    }

    private static int run(CommandContext<ServerCommandSource> context) {
        ServerPlayerEntity player = context.getSource().getPlayer();
        if (player != null) {
            List<StatusEffect> allEffect = Arrays.asList(More_effect.ALL_EFFECT);
            Collections.shuffle(allEffect);
            for (StatusEffect statusEffect : allEffect) {
                if (!player.hasStatusEffect(statusEffect)) {
                    player.addStatusEffect(new StatusEffectInstance(statusEffect, -1));
                    player.sendMessage(Text.translatable("effect.moreeffect.random_effect.tips").append(Text.translatable(statusEffect.getTranslationKey() + ".tips")));
                    break;
                }
            }
        }
        return 0;
    }
}
