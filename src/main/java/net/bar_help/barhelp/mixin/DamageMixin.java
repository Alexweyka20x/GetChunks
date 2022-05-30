package net.bar_help.barhelp.mixin;

import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;

@Mixin(PlayerEntity.class)
public class DamageMixin {

    @Inject(at = @At("RETURN"), method = "damage")
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable info) {
        PlayerEntity self = (PlayerEntity)(Object)(this);
        self.sendSystemMessage(new LiteralText("Name: " + source.name), Util.NIL_UUID);
        System.out.printf("Name: %s\nAmount: %f", source.name, amount);
    }
}