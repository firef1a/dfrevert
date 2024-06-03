package dev.fire.config;

import dev.fire.DFrevert;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.minecraft.text.*;

import java.awt.*;
import java.util.ArrayList;

public class MiniMessage {
    public static Text format(String input, boolean keep_minimessage_tags) {
        MutableText text = Text.empty();

        MiniMessageStyle styling = new MiniMessageStyle();

        boolean in_arrow = false;
        boolean formatting_error = false;
        String formatting = "";

        for (int i = 0; i < input.length(); i++) {
            String value = String.valueOf(input.charAt(i));

            if (value.equals("<")) {
                if (keep_minimessage_tags) text.append(Text.literal(value).withColor(0xffffff));
                if (!in_arrow) {
                    in_arrow = true;
                    formatting = "";
                } else {
                    formatting_error = true;
                    break;
                }
            } else if (value.equals(">")) {
                if (in_arrow) {
                    if (keep_minimessage_tags) text.append(Text.literal(value).withColor(0xffffff));
                    boolean invert = false;
                    boolean is_color = false;

                    boolean is_bold = false;
                    boolean is_italic = false;
                    boolean is_underline = false;
                    boolean is_strikethrough = false;
                    boolean is_obfuscated = false;


                    in_arrow = false;
                    // process formatting and apply
                    if (formatting.startsWith("/")) invert = true;
                    if (invert) formatting = formatting.substring( 1);

                    if (formatting.equals("bold") || formatting.equals("b") || formatting.equals("&l")) is_bold = true;
                    if (formatting.equals("italic") || formatting.equals("i") || formatting.equals("&o")) is_italic = true;
                    if (formatting.equals("underline") || formatting.equals("u") || formatting.equals("&n")) is_underline = true;
                    if (formatting.equals("strikethrough") || formatting.equals("strike") || formatting.equals("str") || formatting.equals("&m")) is_strikethrough = true;
                    if (formatting.equals("obfuscated") || formatting.equals("obf") || formatting.equals("o") || formatting.equals("&k")) is_obfuscated = true;
                    if (formatting.startsWith("#") && formatting.length() > 1) is_color = true;

                    // add /remove colors from stack
                    if (is_color) {
                        formatting = formatting.substring( 1);
                        int cr;
                        try {
                            cr = parseIntFromHex(formatting);
                            if (!invert) styling.addColor(cr);
                            else styling.removeColor(cr);
                        } catch (Exception exception) {
                            formatting_error = true;
                            break;
                        }
                    }
                    // check for bold
                    if (is_bold && !invert) styling.bold += 1;
                    else if (is_bold && invert) styling.bold -= 1;
                    if (styling.bold < 0) {formatting_error = true;break;}

                    if (is_italic && !invert) styling.italic += 1;
                    else if (is_italic && invert) styling.italic -= 1;
                    if (styling.italic < 0) {formatting_error = true;break;}

                    if (is_underline && !invert) styling.underline += 1;
                    else if (is_underline && invert) styling.underline -= 1;
                    if (styling.underline < 0) {formatting_error = true;break;}

                    if (is_strikethrough && !invert) styling.strikethrough += 1;
                    else if (is_strikethrough && invert) styling.strikethrough -= 1;
                    if (styling.strikethrough < 0) {formatting_error = true;break;}

                    if (is_obfuscated && !invert) styling.obfuscated += 1;
                    else if (is_obfuscated && invert) styling.obfuscated -= 1;
                    if (styling.obfuscated < 0) {formatting_error = true;break;}

                } else {
                    formatting_error = true;
                    break;
                }
            } else if (in_arrow) {
                if (keep_minimessage_tags) text.append(Text.literal(value).withColor(0xffffff));
                formatting = formatting + value;
            } else {
                int color;
                if (!styling.colorsList.isEmpty()) {
                    color = styling.colorsList.get(styling.colorsList.size()-1);
                } else {
                    color = 0xffffff;
                }

                Text appendText = Text.literal(value)
                        .styled(style -> style
                                .withColor(color)
                                .withBold(styling.bold > 0)
                                .withItalic(styling.italic > 0)
                                .withUnderline(styling.underline > 0)
                                .withStrikethrough(styling.strikethrough > 0)
                                .withObfuscated(styling.obfuscated > 0 && !keep_minimessage_tags)
                        );
                //append text with formatting if any
                text.append(appendText);

            }

        }

        // return
        if (formatting_error) {
            DFrevert.LOGGER.info("big error!");
            return Text.literal(input);
        } else {
            return text;
        }
    }



    public static int parseIntFromHex(String hexString) {
        return Integer.parseInt(hexString, 16);
    }
    public String parseHexFromInt(String string, int color){
        Color c = new Color(color);
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        return String.format("#%02x%02x%02x", r, g, b);
    }

}
