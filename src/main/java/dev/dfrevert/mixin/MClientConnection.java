package dev.dfrevert.mixin;

import dev.dfrevert.TextUtil;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ClientConnection.class)
public class MClientConnection {
    @ModifyVariable(method = "handlePacket", at = @At("HEAD"), argsOnly = true)
    private static <T extends PacketListener> Packet handlePacket(Packet<T> packet) {
        if (packet instanceof GameMessageS2CPacket(Text content, boolean overlay)) {
            Text new_context = TextUtil.replaceTags(content, false);
            return new GameMessageS2CPacket(new_context, overlay);
        }
        return packet;
    }
}