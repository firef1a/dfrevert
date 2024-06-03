package dev.fire;

import dev.fire.utils.ChatTag;
import dev.fire.utils.MiniMessageChatTag;
import net.minecraft.text.*;
import dev.fire.config.Config;
import dev.fire.config.DefaultConfig;

import java.util.Map;
import java.util.Objects;

public class TextUtil {
    public static final String MOD_NAME = "DFrevert";
    public static final String MOD_ID = "dfrevert";

    private static final Text VIP_PREFIX = Text.empty()
            .append(Text.literal("[").withColor(DefaultConfig.newChatTags.get("vip").BracketColor))
            .append(Text.literal("⭐")
                    .styled(style -> style
                            .withColor(DefaultConfig.newChatTags.get("vip").SymbolColor)
                            .withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/vip"))
                            .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("VIP").withColor(DefaultConfig.newChatTags.get("vip").SymbolColor)))
                    ))
            .append(Text.literal("]").withColor(DefaultConfig.newChatTags.get("vip").BracketColor));
    private static final Text VIP_WHOIS = Text.empty()
            .append(Text.literal("[").withColor(DefaultConfig.newChatTags.get("vip").BracketColor))
            .append(Text.literal("VIP").withColor(DefaultConfig.newChatTags.get("vip").SymbolColor))
            .append(Text.literal("]").withColor(DefaultConfig.newChatTags.get("vip").BracketColor));
    private static final Text FOUNDING_BADGE = Text.literal("⭐ ").styled(style -> style.withColor(DefaultConfig.newChatTags.get("vip").SymbolColor)
            .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("Founding VIP").withColor(DefaultConfig.newChatTags.get("vip").SymbolColor))));

    public static Text getProfileLine(int color, boolean custom_line) {
        String txt = "                                       ";
        if (custom_line) {
            txt = "\n                                       ";
        }
        return (Text.literal(txt).
                    styled(style -> style
                    .withColor(color)
                    .withStrikethrough(true)
                ));
    }
    public static Text getProfileArrow(int color) {
        String txt = "→";

        return Text.literal(txt).withColor(color);
    }

    // the reason im doing it like this is cus some tags have weird text literal sibling structures




    public static Text getCustomTag(ChatTag chattag, boolean add_space, boolean encase_inTextEmpty) {
        int bracket_color = chattag.BracketColor;
        int symbol_color = chattag.SymbolColor;
        int text_color = chattag.TextColor;
        String symbol = chattag.Symbol;
        String main_text = chattag.TextContent;

        if (chattag.addBoldSpace) {
            Text returnText;

            // has symbol
            if (Objects.equals(symbol, "")) {
                returnText = Text.empty()
                                    .append(Text.literal("[").withColor(bracket_color))
                                    .append(
                                        Text.empty()
                                            .append(Text.literal("\u200C").styled(style -> style.withBold(true)))
                                            .append(Text.literal(main_text).withColor(text_color))
                                            .append(Text.literal("\u200C").styled(style -> style.withBold(true)))
                                    )
                                    .append(Text.literal("]").withColor(bracket_color));
            } else {
                returnText = Text.empty()
                                        .append(Text.literal("[").withColor(bracket_color))
                                        .append(
                                                Text.empty()
                                                        .append(Text.literal(symbol).withColor(symbol_color))
                                                        .append(Text.literal("\u200C").styled(style -> style.withBold(true)))
                                                        .append(Text.literal(main_text).withColor(text_color))
                                                        .append(Text.literal("\u200C").styled(style -> style.withBold(true)))
                                                        .append(Text.literal(symbol).withColor(symbol_color))
                                        )
                                        .append(Text.literal("]").withColor(bracket_color));
            }
            // default
           if (encase_inTextEmpty) {
               returnText =  Text.empty().append(returnText);
           }
            return returnText;


        // old/staff -----
        } else {
            if (Objects.equals(symbol, "")) {
                if (add_space) {
                    return Text.empty()
                            .append(Text.literal("[").withColor(bracket_color))
                            .append(Text.literal(main_text).withColor(text_color))
                            .append(Text.literal("]").withColor(bracket_color))
                            .append(Text.literal(" "));
                }
                return Text.empty()
                        .append(Text.literal("[").withColor(bracket_color))
                        .append(Text.literal(main_text).withColor(text_color))
                        .append(Text.literal("]").withColor(bracket_color));
            }
            if (add_space) {
                return Text.empty()
                        .append(Text.literal("[").withColor(bracket_color))
                        .append(Text.literal(symbol).withColor(symbol_color))
                        .append(Text.literal(main_text).withColor(text_color))
                        .append(Text.literal(symbol).withColor(symbol_color))
                        .append(Text.literal("]").withColor(bracket_color))
                        .append(Text.literal(" "));

            }
            return Text.empty()
                    .append(Text.literal("[").withColor(bracket_color))
                    .append(Text.literal(symbol).withColor(symbol_color))
                    .append(Text.literal(main_text).withColor(text_color))
                    .append(Text.literal(symbol).withColor(symbol_color))
                    .append(Text.literal("]").withColor(bracket_color));
        }
}

    public static Text getShortCustomTag(ChatTag chattag, boolean add_space, boolean return_empty) {
        if (return_empty) {
            return Text.empty();
        }
        int bracket_color = chattag.BracketColor;
        int text_color = chattag.TextColor;
        String main_text = chattag.TextContent;
        if (add_space) {
            return Text.empty()
                    .append(Text.literal("[").withColor(bracket_color))
                    .append(Text.literal(main_text.substring(0,1)).withColor(text_color))
                    .append(Text.literal("]").withColor(bracket_color))
                    .append(Text.literal(" "));
        }
        return Text.empty()
                .append(Text.literal("[").withColor(bracket_color))
                .append(Text.literal(main_text.substring(0,1)).withColor(text_color))
                .append(Text.literal("]").withColor(bracket_color));
    }


    public static Text replaceTags(Text text, boolean isTabList) {
        Config c = Config.getConfig();

        if (c.DisableMod){
            return text;
        }

        // custom tags

        for (Map.Entry<String, ChatTag> entry : DefaultConfig.oldChatTags.entrySet()) {
            String key = entry.getKey();
            ChatTag chattag = entry.getValue();
            MiniMessageChatTag custom = c.chatTags.get(key);
            ChatTag replacetag = DefaultConfig.newChatTags.get(key);

            if (!Objects.equals(key, "vip")) {

                text = replaceTextInternal(text, getCustomTag(replacetag, false, false), custom.getAsFormatted(), false);
                text = replaceTextInternal(text, getCustomTag(replacetag, false, true), custom.getAsFormatted(), false);


                // replace shortened chat tags and tab list for normal tags
                boolean return_empty = !c.ShortenedChatTags && !isTabList;
                Text replace;
                if (return_empty) {
                    replace = Text.empty();
                } else {
                    replace = custom.getAsShortFormatted();
                    if (isTabList) replace = replace.copy().append(Text.literal(" "));
                }
                text = replaceTextInternal(text, getShortCustomTag(replacetag, isTabList, false),  replace, false);



                // check if its the tab list and if its a staff tag (staff tags in the tab list are displayed fully but with an add_space, different from above)
                if (isTabList) {
                    if (DefaultConfig.staffList.contains(key)) {
                        text = replaceTextInternal(text, getCustomTag(replacetag, true, false),  custom.getAsFormatted().copy().append(Text.literal(" ")), false);
                    }
                }



            }
        }


        // replace profile line and arrow colors
        for (String key : DefaultConfig.normalList) {

            ChatTag normal = DefaultConfig.newChatTags.get(key);
            MiniMessageChatTag custom = c.chatTags.get(key);

            int color = custom.ProfileColor;
            // replace
            text = replaceTextInternal(text, getProfileLine(normal.TextColor,false), getProfileLine(color,false), false);
            text = replaceTextInternal(text, getProfileLine(normal.TextColor, true), getProfileLine(color,true), false);

            text = replaceTextInternal(text, getProfileArrow(normal.TextColor),   getProfileArrow(color), false);

        }

        // vipenabled / disabled logic
        MiniMessageChatTag custom_vip = c.chatTags.get("vip");
        int color = custom_vip.ProfileColor;

        if (!c.VipEnabled) {
            text = replaceTextInternal(text, VIP_PREFIX, Text.empty(), true);
            text = replaceTextInternal(text, VIP_WHOIS, Text.empty(), false);
            text = replaceTextInternal(text, FOUNDING_BADGE, Text.empty(), false);
        } else {
            text = replaceTextInternal(text, VIP_PREFIX, custom_vip.getAsFormatted(), false);
            text = replaceTextInternal(text, VIP_WHOIS, custom_vip.getAsShortFormatted(), true);

            text = replaceTextInternal(text, FOUNDING_BADGE, Text.literal("⭐ ")
                    .styled(style -> style.withColor(color)
                    .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                            Text.literal("Founding VIP")
                            .withColor(color))))
                    , false);


        }


        return text;
    }

    private static Text replaceTextInternal(Text text, Text find, Text replace, Boolean skip) {
        MutableText newText = MutableText.of(text.getContent()).setStyle(text.getStyle());

        boolean empty = getContent(text).isEmpty();
        boolean hideSpace = false;
        for (Text sibling : text.getSiblings()) {
            String content = getContent(sibling);
            if (hideSpace) {
                hideSpace = false;
                if (content.equals(" ")) {
                    continue;
                }
            }

            if (sibling.equals(find)) {
                newText.append(Text.empty().append(replace));
                if (skip) {
                    hideSpace = true;
                }
                continue;
            }

            newText.append(replaceTextInternal(sibling, find, replace, skip));
        }
        return newText;
    }

    private static String getContent(Text text) {
        if (text.getContent() instanceof PlainTextContent content) {
            return content.string();
        }
        return "";
    }

}