package dev.fire.config;

import net.minecraft.network.message.SentMessage;

public class ChatTag {
    public int BracketColor;
    public int SymbolColor;
    public int TextColor;
    public String Symbol;
    public String TextContent;
    public ChatTag(int bracketcolor, int symbolcolor, int textcolor, String sym, String textcontent) {
        BracketColor = bracketcolor;
        SymbolColor = symbolcolor;
        TextColor = textcolor;
        Symbol = sym;
        TextContent = textcontent;
    }
}
