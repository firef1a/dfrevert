package dev.dfrevert.utils;

import dev.dfrevert.DFrevert;
import net.minecraft.text.*;

import java.awt.*;
import java.util.Map;

import static java.util.Map.entry;

public class MiniMessage {
    public static Map<String, String> map_hex_colors = Map.ofEntries(
            entry("dark_red", "#AA0000"),
            entry("red", "#FF5555"),
            entry("gold", "#FFAA00"),
            entry("yellow", "#FFFF55"),
            entry("dark_green", "#00AA00"),
            entry("green", "#55FF55"),
            entry("aqua", "#55FFFF"),
            entry("dark_aqua", "#00AAAA"),
            entry("dark_blue", "#0000AA"),
            entry("blue", "#5555FF"),
            entry("light_purple", "#FF55FF"),
            entry("dark_purple", "#AA00AA"),
            entry("white", "#FFFFFF"),
            entry("gray", "#AAAAAA"),
            entry("dark_gray", "#555555"),
            entry("black", "#000000")
    );

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



                    if (!is_color) {
                        for (Map.Entry<String, String> entry : map_hex_colors.entrySet()) {
                            String k = entry.getKey();
                            String v = entry.getValue();
                            if (formatting.equals(k)) {
                                formatting = v;
                                is_color = true;
                                break;
                            }
                        }
                    }

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
    public static String parseHexFromInt(String string, int color){
        Color c = new Color(color);
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        return String.format("#%02x%02x%02x", r, g, b);
    }

}
