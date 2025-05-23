package dev.dfrevert.mixin;

import dev.dfrevert.TextUtil;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerListHud.class)
public class PlayerListHudMixin {
    @Inject(method = "getPlayerName*", at = @At("RETURN"), cancellable = true)
    protected void getPlayerName(PlayerListEntry playerListEntry, CallbackInfoReturnable<Text> cir) {
        Text text = cir.getReturnValue();
        //DFrevert.LOGGER.info(String.valueOf(text));
        Text newText = TextUtil.replaceTags(text, true);
        cir.setReturnValue(newText);
        //playerListEntry.setDisplayName(Text.literal("hello world"));
    }
}