package fun.lightai.moreeffect.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;

public class AfterRespawnEvent implements ServerPlayerEvents.AfterRespawn {

    @Override
    public void afterRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        for (StatusEffectInstance effectInstance : oldPlayer.getStatusEffects()) {
            newPlayer.addStatusEffect(effectInstance);
        }
    }
}
