package dev.fire;

import dev.fire.config.ChatTag;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.PlainTextContent;
import net.minecraft.text.Text;
import dev.fire.config.Config;
import dev.fire.config.DefaultConfig;

public class TextUtil {
    public static final String MOD_NAME = "DFrevert";
    public static final String MOD_ID = "dfrevert";

    private static final Text VIP_PREFIX = Text.empty()
            .append(Text.literal("[").withColor(DefaultConfig.Vip.BracketColor))
            .append(Text.literal("⭐")
                    .styled(style -> style
                            .withColor(DefaultConfig.Vip.SymbolColor)
                            .withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/vip"))
                            .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("VIP").withColor(DefaultConfig.Vip.SymbolColor)))
                    ))
            .append(Text.literal("]").withColor(DefaultConfig.Vip.BracketColor));
    private static final Text VIP_WHOIS = Text.empty()
            .append(Text.literal("[").withColor(DefaultConfig.Vip.BracketColor))
            .append(Text.literal("VIP").withColor(DefaultConfig.Vip.SymbolColor))
            .append(Text.literal("]").withColor(DefaultConfig.Vip.BracketColor));
    private static final Text FOUNDING_BADGE = Text.literal("⭐ ").styled(style -> style.withColor(DefaultConfig.Vip.SymbolColor)
            .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("Founding VIP").withColor(DefaultConfig.Vip.SymbolColor))));

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

    public static Text getOldOverlord() {
        return Text.empty()
                .append(Text.literal("[").withColor(DefaultConfig.Overlord.BracketColor))
                .append(Text.empty().append(Text.literal(DefaultConfig.Overlord.Symbol).withColor(DefaultConfig.Overlord.SymbolColor))
                .append(Text.literal(DefaultConfig.Overlord.TextContent).withColor(DefaultConfig.Overlord.TextColor))
                .append(Text.literal(DefaultConfig.Overlord.Symbol).withColor(DefaultConfig.Overlord.SymbolColor)))
                .append(Text.literal("]").withColor(DefaultConfig.Overlord.BracketColor));
    }
    public static Text getOldMythic() {
        return Text.empty()
                .append(Text.literal("[").withColor(DefaultConfig.Mythic.BracketColor))
                .append(Text.literal(DefaultConfig.Mythic.TextContent).withColor(DefaultConfig.Mythic.TextColor))
                .append(Text.literal("]").withColor(DefaultConfig.Mythic.BracketColor));
    }
    public static Text getOldEmperor() {
        return Text.empty()
                .append(Text.literal("[").withColor(DefaultConfig.Emperor.BracketColor))
                .append(Text.literal(DefaultConfig.Emperor.TextContent).withColor(DefaultConfig.Emperor.TextColor))
                .append(Text.literal("]").withColor(DefaultConfig.Emperor.BracketColor));
    }

    public static Text getOldNoble() {
        return Text.empty()
                .append(Text.literal("[").withColor(DefaultConfig.Noble.BracketColor))
                .append(Text.literal(DefaultConfig.Noble.TextContent).withColor(DefaultConfig.Noble.TextColor))
                .append(Text.literal("]").withColor(DefaultConfig.Noble.BracketColor));
    }

    // Old Staff Chat Tags

    public static Text getOldOwner() {
        return Text.empty()
                .append(Text.literal("[").withColor(DefaultConfig.Owner.BracketColor))
                .append(Text.literal(DefaultConfig.Owner.TextContent).withColor(DefaultConfig.Owner.TextColor))
                .append(Text.literal("]").withColor(DefaultConfig.Owner.BracketColor));
    }
    public static Text getOldAdmin() {
        return Text.empty()
                .append(Text.literal("[").withColor(DefaultConfig.Admin.BracketColor))
                .append(Text.literal(DefaultConfig.Admin.TextContent).withColor(DefaultConfig.Admin.TextColor))
                .append(Text.literal("]").withColor(DefaultConfig.Admin.BracketColor));
    }
    public static Text getOldDev() {
        return Text.empty()
                .append(Text.literal("[").withColor(DefaultConfig.Dev.BracketColor))
                .append(Text.literal(DefaultConfig.Dev.TextContent).withColor(DefaultConfig.Dev.TextColor))
                .append(Text.literal("]").withColor(DefaultConfig.Dev.BracketColor));
    }
    public static Text getOldSrMod() {
        return Text.empty()
                .append(Text.literal("[").withColor(DefaultConfig.SrMod.BracketColor))
                .append(Text.literal(DefaultConfig.SrMod.TextContent).withColor(DefaultConfig.SrMod.TextColor))
                .append(Text.literal("]").withColor(DefaultConfig.SrMod.BracketColor));
    }
    public static Text getOldMod() {
        return Text.empty()
                .append(Text.literal("[").withColor(DefaultConfig.Mod.BracketColor))
                .append(Text.literal(DefaultConfig.Mod.TextContent).withColor(DefaultConfig.Mod.TextColor))
                .append(Text.literal("]").withColor(DefaultConfig.Mod.BracketColor));
    }
    public static Text getOldJrMod() {
        return Text.empty()
                .append(Text.literal("[").withColor(DefaultConfig.JrMod.BracketColor))
                .append(Text.literal(DefaultConfig.JrMod.TextContent).withColor(DefaultConfig.JrMod.TextColor))
                .append(Text.literal("]").withColor(DefaultConfig.JrMod.BracketColor));
    }
    public static Text getOldSrHelper() {
        return Text.empty()
                .append(Text.literal("[").withColor(DefaultConfig.SrHelper.BracketColor))
                .append(Text.literal(DefaultConfig.SrHelper.TextContent).withColor(DefaultConfig.SrHelper.TextColor))
                .append(Text.literal("]").withColor(DefaultConfig.SrHelper.BracketColor));
    }
    public static Text getOldHelper() {
        return Text.empty()
                .append(Text.literal("[").withColor(DefaultConfig.Helper.BracketColor))
                .append(Text.literal(DefaultConfig.Helper.TextContent).withColor(DefaultConfig.Helper.TextColor))
                .append(Text.literal("]").withColor(DefaultConfig.Helper.BracketColor));
    }
    public static Text getOldJrHelper() {
        return Text.empty()
                .append(Text.literal("[").withColor(DefaultConfig.JrHelper.BracketColor))
                .append(Text.literal(DefaultConfig.JrHelper.TextContent).withColor(DefaultConfig.JrHelper.TextColor))
                .append(Text.literal("]").withColor(DefaultConfig.JrHelper.BracketColor));
    }


    public static Text getCustomTag(ChatTag chattag, boolean add_space) {
        int bracket_color = chattag.BracketColor;
        int symbol_color = chattag.SymbolColor;
        int text_color = chattag.TextColor;
        String symbol = chattag.Symbol;
        String main_text = chattag.TextContent;
        if (symbol == "") {
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

        ChatTag custom_vip = new ChatTag(c.VipBracketColor, c.VipSymbolColor, c.VipTextColor, c.VipSymbol, c.VipTextContent);

        ChatTag custom_overlord = new ChatTag(c.OverlordBracketColor, c.OverlordSymbolColor, c.OverlordTextColor, c.OverlordSymbol, c.OverlordTextContent);
        ChatTag custom_mythic = new ChatTag(c.MythicBracketColor, c.MythicSymbolColor, c.MythicTextColor, c.MythicSymbol, c.MythicTextContent);
        ChatTag custom_emperor = new ChatTag(c.EmperorBracketColor, c.EmperorSymbolColor, c.EmperorTextColor, c.EmperorSymbol, c.EmperorTextContent);
        ChatTag custom_noble = new ChatTag(c.NobleBracketColor, c.NobleSymbolColor, c.NobleTextColor, c.NobleSymbol, c.NobleTextContent);

        ChatTag custom_owner = new ChatTag(c.OwnerBracketColor, c.OwnerSymbolColor, c.OwnerTextColor, c.OwnerSymbol, c.OwnerTextContent);
        ChatTag custom_admin = new ChatTag(c.AdminBracketColor, c.AdminSymbolColor, c.AdminTextColor, c.AdminSymbol, c.AdminTextContent);
        ChatTag custom_dev = new ChatTag(c.DevBracketColor, c.DevSymbolColor, c.DevTextColor, c.DevSymbol, c.DevTextContent);
        ChatTag custom_srmod = new ChatTag(c.SrModBracketColor, c.SrModSymbolColor, c.SrModTextColor, c.SrModSymbol, c.SrModTextContent);
        ChatTag custom_mod = new ChatTag(c.ModBracketColor, c.ModSymbolColor, c.ModTextColor, c.ModSymbol, c.ModTextContent);
        ChatTag custom_jrmod = new ChatTag(c.JrModBracketColor, c.JrModSymbolColor, c.JrModTextColor, c.JrModSymbol, c.JrModTextContent);
        ChatTag custom_srhelper = new ChatTag(c.SrHelperBracketColor, c.SrHelperSymbolColor, c.SrHelperTextColor, c.SrHelperSymbol, c.SrHelperTextContent);
        ChatTag custom_helper = new ChatTag(c.HelperBracketColor, c.HelperSymbolColor, c.HelperTextColor, c.HelperSymbol, c.HelperTextContent);
        ChatTag custom_jrhelper = new ChatTag(c.JrHelperBracketColor, c.JrHelperSymbolColor, c.JrHelperTextColor, c.JrHelperSymbol, c.JrHelperTextContent);

        // replace old tags
        text = replaceTextInternal(text, getOldOverlord(),  getCustomTag(custom_overlord, false), false);
        text = replaceTextInternal(text, getOldMythic(),    getCustomTag(custom_mythic, false), false);
        text = replaceTextInternal(text, getOldEmperor(),   getCustomTag(custom_emperor, false), false);
        text = replaceTextInternal(text, getOldNoble(),     getCustomTag(custom_noble, false), false);

        text = replaceTextInternal(text, getOldOwner(),     getCustomTag(custom_owner, false), false);
        text = replaceTextInternal(text, getOldAdmin(),     getCustomTag(custom_admin, false), false);
        text = replaceTextInternal(text, getOldDev(),     getCustomTag(custom_dev, false), false);
        text = replaceTextInternal(text, getOldSrMod(),     getCustomTag(custom_srmod, false), false);
        text = replaceTextInternal(text, getOldMod(),     getCustomTag(custom_mod, false), false);
        text = replaceTextInternal(text, getOldJrMod(),     getCustomTag(custom_jrmod, false), false);
        text = replaceTextInternal(text, getOldSrHelper(),     getCustomTag(custom_srhelper, false), false);
        text = replaceTextInternal(text, getOldHelper(),     getCustomTag(custom_helper, false), false);
        text = replaceTextInternal(text, getOldJrHelper(),     getCustomTag(custom_jrhelper, false), false);

        // replace old shortened tags
        boolean return_empty = !c.ShortenedChatTags && !isTabList;
        text = replaceTextInternal(text, getShortCustomTag(DefaultConfig.Overlord, isTabList, false),  getShortCustomTag(custom_overlord, isTabList, return_empty), false);
        text = replaceTextInternal(text, getShortCustomTag(DefaultConfig.Mythic, isTabList, false),    getShortCustomTag(custom_mythic, isTabList, return_empty), false);
        text = replaceTextInternal(text, getShortCustomTag(DefaultConfig.Emperor, isTabList, false),   getShortCustomTag(custom_emperor, isTabList, return_empty), false);
        text = replaceTextInternal(text, getShortCustomTag(DefaultConfig.Noble, isTabList, false),     getShortCustomTag(custom_noble, isTabList, return_empty), false);

        text = replaceTextInternal(text, getShortCustomTag(DefaultConfig.Owner, isTabList, false),     getShortCustomTag(custom_owner, isTabList, return_empty), false);
        text = replaceTextInternal(text, getShortCustomTag(DefaultConfig.Admin, isTabList, false),     getShortCustomTag(custom_admin, isTabList, return_empty), false);
        text = replaceTextInternal(text, getShortCustomTag(DefaultConfig.Dev, isTabList, false),     getShortCustomTag(custom_dev, isTabList, return_empty), false);
        text = replaceTextInternal(text, getShortCustomTag(DefaultConfig.SrMod, isTabList, false),     getShortCustomTag(custom_srmod, isTabList, return_empty), false);
        text = replaceTextInternal(text, getShortCustomTag(DefaultConfig.Mod, isTabList, false),     getShortCustomTag(custom_mod, isTabList, return_empty), false);
        text = replaceTextInternal(text, getShortCustomTag(DefaultConfig.JrMod, isTabList, false),     getShortCustomTag(custom_jrmod, isTabList, return_empty), false);
        text = replaceTextInternal(text, getShortCustomTag(DefaultConfig.SrHelper, isTabList, false),     getShortCustomTag(custom_srhelper, isTabList, return_empty), false);
        text = replaceTextInternal(text, getShortCustomTag(DefaultConfig.Helper, isTabList, false),     getShortCustomTag(custom_helper, isTabList, return_empty), false);
        text = replaceTextInternal(text, getShortCustomTag(DefaultConfig.JrHelper, isTabList, false),     getShortCustomTag(custom_jrhelper, isTabList, return_empty), false);

        if (isTabList) {
            text = replaceTextInternal(text, getCustomTag(DefaultConfig.Owner, true), getCustomTag(custom_owner, true), false);
            text = replaceTextInternal(text, getCustomTag(DefaultConfig.Admin, true), getCustomTag(custom_admin, true), false);
            text = replaceTextInternal(text, getCustomTag(DefaultConfig.Dev, true), getCustomTag(custom_dev, true), false);
            text = replaceTextInternal(text, getCustomTag(DefaultConfig.SrMod, true), getCustomTag(custom_srmod, true), false);
            text = replaceTextInternal(text, getCustomTag(DefaultConfig.Mod, true), getCustomTag(custom_mod, true), false);
            text = replaceTextInternal(text, getCustomTag(DefaultConfig.JrMod, true), getCustomTag(custom_jrmod, true), false);
            text = replaceTextInternal(text, getCustomTag(DefaultConfig.SrHelper, true), getCustomTag(custom_srhelper, true), false);
            text = replaceTextInternal(text, getCustomTag(DefaultConfig.Helper, true), getCustomTag(custom_helper, true), false);
            text = replaceTextInternal(text, getCustomTag(DefaultConfig.JrHelper, true), getCustomTag(custom_jrhelper, true), false);
        }

        // replace old profile lines
        boolean set_newline = false;
        for (int i = 0; i < 2; i++) {
            text = replaceTextInternal(text, getProfileLine(0xAA0000,set_newline), getProfileLine(custom_overlord.TextColor,set_newline), false);
            text = replaceTextInternal(text, getProfileLine(0xAA00AA,set_newline), getProfileLine(custom_mythic.TextColor,set_newline), false);
            text = replaceTextInternal(text, getProfileLine(0x55FFFF,set_newline), getProfileLine(custom_emperor.TextColor,set_newline), false);
            text = replaceTextInternal(text, getProfileLine(0xFFAA00,set_newline), getProfileLine(custom_noble.TextColor,set_newline), false);
            set_newline = true;
        }

        // replace old profile arrows
        text = replaceTextInternal(text, getProfileArrow(0xAA0000),   getProfileArrow(custom_overlord.TextColor), false);
        text = replaceTextInternal(text, getProfileArrow(0xAA00AA),   getProfileArrow(custom_mythic.TextColor), false);
        text = replaceTextInternal(text, getProfileArrow(0x55FFFF),   getProfileArrow(custom_emperor.TextColor), false);
        text = replaceTextInternal(text, getProfileArrow(0xFFAA00),   getProfileArrow(custom_noble.TextColor), false);

        // vipenabled/disabled logic
        if (!c.VipEnabled) {
            text = replaceTextInternal(text, VIP_PREFIX, Text.empty(), true);
            text = replaceTextInternal(text, VIP_WHOIS, Text.empty(), false);
            text = replaceTextInternal(text, FOUNDING_BADGE, Text.empty(), false);
        } else {
            text = replaceTextInternal(text, VIP_PREFIX, Text.empty()
                    .append(Text.literal("[").withColor(custom_vip.BracketColor))
                    .append(Text.literal(custom_vip.Symbol).withColor(custom_vip.SymbolColor))
                    .append(Text.literal("]").withColor(custom_vip.BracketColor)),
                    true);
            text = replaceTextInternal(text, VIP_WHOIS, Text.empty()
                            .append(Text.literal("[").withColor(custom_vip.BracketColor))
                            .append(Text.literal(custom_vip.TextContent).withColor(custom_vip.TextColor))
                            .append(Text.literal("]").withColor(custom_vip.BracketColor)),
                    true);
            text = replaceTextInternal(text, FOUNDING_BADGE, Text.literal(custom_vip.Symbol+" ")
                    .styled(style -> style.withColor(custom_vip.SymbolColor)
                    .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                            Text.literal("Founding VIP")
                            .withColor(custom_vip.SymbolColor))))
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