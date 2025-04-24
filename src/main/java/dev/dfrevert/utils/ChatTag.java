package dev.dfrevert.utils;

import com.google.gson.JsonObject;

import java.awt.*;
import java.io.Serializable;

public class ChatTag implements Serializable {
    public int BracketColor;
    public int SymbolColor;
    public int TextColor;
    public String Symbol;
    public String TextContent;
    public boolean addBoldSpace;
    public boolean isEnabled;

    public ChatTag(int bracketcolor, int symbolcolor, int textcolor, String sym, String textcontent, boolean addBoldSpace, boolean isEnabled) {
        this.BracketColor = bracketcolor;
        this.SymbolColor = symbolcolor;
        this.TextColor = textcolor;
        this.Symbol = sym;
        this.TextContent = textcontent;
        this.addBoldSpace = addBoldSpace;
        this.isEnabled = isEnabled;
    }

    public ChatTag(int bracketcolor, int symbolcolor, int textcolor, String sym, String textcontent, boolean addBoldSpace) {
        this(bracketcolor, symbolcolor, textcolor, sym, textcontent, addBoldSpace, true);
    }

    public String toJson(){
        JsonObject object = new JsonObject();
        object.addProperty("BracketColor", BracketColor);
        object.addProperty("SymbolColor", SymbolColor);
        object.addProperty("TextColor", TextColor);
        object.addProperty("Symbol", Symbol);
        object.addProperty("TextContent", TextContent);
        object.addProperty("addBoldSpace", addBoldSpace);
        object.addProperty("isEnabled", isEnabled);
        return object.toString();

    }

    public String toMainMiniMessage() {
        String minimessage = "";
        minimessage = minimessage + convertStringWithColorToMiniMessage("[", BracketColor);
        minimessage = minimessage + convertStringWithColorToMiniMessage(Symbol, SymbolColor);
        if ( addBoldSpace ) minimessage = minimessage + "<bold>\u200C</bold>";
        minimessage = minimessage + convertStringWithColorToMiniMessage(TextContent, TextColor);
        if ( addBoldSpace ) minimessage = minimessage + "<bold>\u200C</bold>";
        minimessage = minimessage + convertStringWithColorToMiniMessage(Symbol, SymbolColor);
        minimessage = minimessage + convertStringWithColorToMiniMessage("]", BracketColor);

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
        return new MiniMessageChatTag(this);
    }

    public static String convertStringWithColorToMiniMessage(String string, int color){
        Color c = new Color(color);
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        String hex = String.format("#%02x%02x%02x", r, g, b);
        return "<" + hex + ">" + string + "</" + hex + ">";
    }

}
