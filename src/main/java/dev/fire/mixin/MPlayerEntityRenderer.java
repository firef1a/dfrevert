package dev.fire.mixin;

import dev.fire.DFrevert;
import dev.fire.TextUtil;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerEntityRenderer.class)
public class MPlayerEntityRenderer {
    @ModifyVariable(method = "renderLabelIfPresent*", at = @At("HEAD"), argsOnly = true)
    public Text inject(Text text) {
        //DFrevert.LOGGER.info(String.valueOf(text));
        return TextUtil.replaceTags(text, true);

    }
}