package dev.dfrevert.utils;

import com.google.gson.JsonObject;
import net.minecraft.text.*;

import java.io.Serializable;

public class MiniMessageChatTag implements Serializable {
    public String mainvalue;
    public String shortvalue;
    public int ProfileColor;
    public boolean isEnabled;

    public MiniMessageChatTag(ChatTag chattag) {
        this.mainvalue = chattag.toMainMiniMessage();
        this.shortvalue = chattag.toShortValue();
        this.ProfileColor = chattag.TextColor;
        this.isEnabled = chattag.isEnabled;;
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
        object.addProperty("isEnabled", isEnabled);
        return object.toString();
    }
}
