package dev.fire.utils;

import com.google.gson.JsonObject;
import net.minecraft.text.*;

public class MiniMessageChatTag {
    public String mainvalue;
    public String leftvalue;
    public String rightvalue;
    public String shortvalue;

    public MiniMessageChatTag(String leftvalue, String mainvalue, String rightvalue, String shortvalue) {
        this.leftvalue = leftvalue;
        this.mainvalue = mainvalue;
        this.rightvalue = rightvalue;
        this.shortvalue = shortvalue;
    }

    public MiniMessageChatTag(ChatTag chattag) {
        this.leftvalue = chattag.toLeftMiniMessage();
        this.mainvalue = chattag.toMainMiniMessage();
        this.rightvalue = chattag.toRightMiniMessage();
        this.shortvalue = chattag.toShortValue();
    }

    public Text getAsFormatted() {
        return MiniMessage.format(leftvalue +mainvalue+ rightvalue, false);
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
        object.addProperty("leftvalue", leftvalue);
        object.addProperty("rightvalue", rightvalue);
        object.addProperty("shortvalue", shortvalue);
        return object.toString();
    }
}
