package dev.fire.config;

import com.google.gson.JsonObject;
import net.minecraft.text.*;

public class MiniMessageChatTag {
    public String mainvalue;
    public String leftvalue;
    public String rightvalue;
    public String shortValue;

    public MiniMessageChatTag(String leftvalue, String mainvalue, String rightvalue, String shortValue) {
        this.leftvalue = leftvalue;
        this.mainvalue = mainvalue;
        this.rightvalue = rightvalue;
        this.shortValue = shortValue;
    }

    public MiniMessageChatTag(ChatTag chattag) {
        this.leftvalue = chattag.toLeftMiniMessage();
        this.mainvalue = chattag.toMainMiniMessage();
        this.rightvalue = chattag.toRightMiniMessage();
        this.shortValue = chattag.toShortValue();
    }

    public Text getAsFormatted() {
        return MiniMessage.format(leftvalue +mainvalue+ rightvalue, false);
    }
    public Text getAsShortFormatted() {
        return MiniMessage.format(shortValue, false);
    }
    public String toJson(){
        JsonObject object = new JsonObject();
        object.addProperty("mainvalue", mainvalue);
        object.addProperty("leftvalue", leftvalue);
        object.addProperty("rightvalue", rightvalue);
        object.addProperty("shortValue", shortValue);
        return object.toString();
    }
}
