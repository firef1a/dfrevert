package dev.fire.utils;

import com.google.gson.JsonObject;
import net.minecraft.text.*;

import java.io.Serializable;

public class MiniMessageChatTag implements Serializable {
    public String mainvalue;
    public String shortvalue;
    public int ProfileColor;

    public MiniMessageChatTag(ChatTag chattag) {
        this.mainvalue = chattag.toMainMiniMessage();
        this.shortvalue = chattag.toShortValue();
        this.ProfileColor = chattag.TextColor;
    }

    public Text getAsFormatted() {
        return MiniMessage.format(mainvalue, false);
    }
    public Text getAsFormatted(String input) {
        return MiniMessage.format(input, false);
    }
    public Text getAsShortFormatted() {
        return MiniMessage.format(shortvalue, false);
    }
    public String toJson(){
        JsonObject object = new JsonObject();
        object.addProperty("mainvalue", mainvalue);
        object.addProperty("shortvalue", shortvalue);
        object.addProperty("ProfileColor", ProfileColor);
        return object.toString();
    }
}
