package dev.fire.config;

import com.google.gson.JsonObject;
import net.minecraft.network.message.SentMessage;

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

}
