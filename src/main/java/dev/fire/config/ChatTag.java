package dev.fire.config;

import com.google.gson.JsonObject;
import net.minecraft.network.message.SentMessage;
import net.minecraft.text.Text;

import java.awt.*;
import java.io.Serializable;
import java.util.Map;

public class ChatTag implements Serializable {
    public int BracketColor;
    public int SymbolColor;
    public int TextColor;
    public String Symbol;
    public String TextContent;
    public boolean addBoldSpace;
    public ChatTag(int bracketcolor, int symbolcolor, int textcolor, String sym, String textcontent, boolean addBoldSpace) {
        this.BracketColor = bracketcolor;
        this.SymbolColor = symbolcolor;
        this.TextColor = textcolor;
        this.Symbol = sym;
        this.TextContent = textcontent;
        this.addBoldSpace = addBoldSpace;
    }

    public String toJson(){
        JsonObject object = new JsonObject();
        object.addProperty("BracketColor", BracketColor);
        object.addProperty("SymbolColor", SymbolColor);
        object.addProperty("TextColor", TextColor);
        object.addProperty("Symbol", Symbol);
        object.addProperty("TextContent", TextContent);
        object.addProperty("addBoldSpace", addBoldSpace);
        return object.toString();
    }

    public String toLeftMiniMessage() {
        String minimessage = "";
        minimessage = minimessage + convertStringWithColorToMiniMessage("[", BracketColor);
        minimessage = minimessage + convertStringWithColorToMiniMessage(Symbol, SymbolColor);
        if ( addBoldSpace ) minimessage = minimessage + "<bold>\u200C</bold>";
        return minimessage;
    }
    public String toRightMiniMessage() {
        String minimessage = "";
        if ( addBoldSpace ) minimessage = minimessage + "<bold>\u200C</bold>";
        minimessage = minimessage + convertStringWithColorToMiniMessage(Symbol, SymbolColor);
        minimessage = minimessage + convertStringWithColorToMiniMessage("]", BracketColor);
        return minimessage;
    }

    public String toMainMiniMessage() {
        String minimessage = "";
        minimessage = minimessage + convertStringWithColorToMiniMessage(TextContent, TextColor);

        return minimessage;
    }

    public String toShortValue() {
        String minimessage = "";
        String subString = TextContent.substring(0,1);

        minimessage = minimessage + convertStringWithColorToMiniMessage("[", BracketColor);
        minimessage = minimessage + convertStringWithColorToMiniMessage(subString, TextColor);
        minimessage = minimessage + convertStringWithColorToMiniMessage("]", BracketColor);

        return minimessage;
    }

    public MiniMessageChatTag toMiniMessageClass() {
        String minimessage = "";

        return new MiniMessageChatTag(this);
    }

    private String convertStringWithColorToMiniMessage(String string, int color){
        Color c = new Color(color);
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        String hex = String.format("#%02x%02x%02x", r, g, b);
        String minimessage = "<" + hex + ">" + string + "</" + hex + ">";
        return minimessage;
    }

}
