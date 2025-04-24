package dev.dfrevert.config;

import dev.dfrevert.utils.ChatTag;

import java.util.*;

import static java.util.Map.entry;

public class DefaultConfig {
    public static Config.CharSetOption SaveCharSet = Config.CharSetOption.UTF_8;
    public static Config.CharSetOption FileCharSet = Config.CharSetOption.UTF_8;

    public static boolean VipEnabled = true;

    public static boolean DisableMod = false;
    public static boolean ShortenedChatTags = true;
    public static boolean DebugMode = false;

    public static Map<String, ChatTag> oldChatTags = Map.ofEntries(
            entry("vip",       new ChatTag(0xFFAA00, 0xFFD47F, 0xFFD47F, "⭐","VIP", false)),
            entry("overlord",  new ChatTag(0x00AAAA, 0xAAAAAA, 0xAA0000, "◆", "Overlord", false)),
            entry("mythic",    new ChatTag(0x555555, 0x555555, 0xAA00AA, "", "Mythic", false)),
            entry("emperor",   new ChatTag(0x00AA00, 0x00AA00, 0x55FFFF, "", "Emperor", false)),
            entry("noble",     new ChatTag(0xFFAA00, 0xFFAA00, 0x55FF55, "", "Noble", false)),

            entry("owner",     new ChatTag(0x000000, 0x000000, 0xFF0000, "", "Owner", false)),
            entry("admin",     new ChatTag(0x555555, 0x555555, 0xFF0000, "", "Admin", false)),
            entry("dev",       new ChatTag(0x555555, 0x555555, 0xFF7FAA, "", "Dev", false)),
            entry("srmod",     new ChatTag(0x808080, 0x808080, 0xAAFF55, "", "SrMod", false)),
            entry("mod",       new ChatTag(0xAAAAAA, 0xAAAAAA, 0x55FF55, "", "Mod", false)),
            entry("jrmod",     new ChatTag(0xAAAAAA, 0xAAAAAA, 0x2AD42A, "", "JrMod", false)),
            entry("srhelper",  new ChatTag(0x808080, 0x808080, 0x2AFFAA, "", "SrHelper", false)),
            entry("helper",    new ChatTag(0xAAAAAA, 0xAAAAAA, 0x55FFFF, "", "Helper", false)),
            entry("jrhelper",  new ChatTag(0xAAAAAA, 0xAAAAAA, 0x55AAFF, "", "JrHelper", false)),
            entry("emeritus", new ChatTag(0xFFAA00, 0xFFAA00, 0x2AD4D4, "", "Retired", false)),
            entry("retired",   new ChatTag(0x005555, 0x005555, 0x00AAAA, "", "Retired", false)),
            entry("builder",   new ChatTag(0x80552A, 0x80552A, 0xFFAA00, "", "Builder", false)),
            entry("sponsor", new ChatTag(0x7FFF7F, 0xAAFFAA, 0xAAFFAA, "", "Sponsor", false))
    );

    public static Map<String, ChatTag> newChatTags = Map.ofEntries(
            entry("vip",       new ChatTag(0xFFAA00, 0xFFD47F, 0xFFD47F, "⭐","VIP", false)),
            entry("overlord",  new ChatTag(0xAA002A, 0xFF7F7F, 0xFF5555, "◆", "Overlord", true)),
            entry("mythic",    new ChatTag(0x7F00AA, 0xFF7FD4, 0xD42AD4, "◇", "Mythic", true)),
            entry("emperor",   new ChatTag(0x2A70D4, 0xAAFFFF, 0x55AAFF, "◦", "Emperor", true)),
            entry("noble",     new ChatTag(0x00AA00, 0x00AA00, 0x7FFF7F, "", "Noble", true)),

            entry("owner",     new ChatTag(0x000000, 0x000000, 0xFF0000, "", "Owner", false)),
            entry("admin",     new ChatTag(0x555555, 0x555555, 0xFF0000, "", "Admin", false)),
            entry("dev",       new ChatTag(0x555555, 0x555555, 0xFF7FAA, "", "Dev", false)),
            entry("srmod",     new ChatTag(0x808080, 0x808080, 0xAAFF55, "", "SrMod", false)),
            entry("mod",       new ChatTag(0xAAAAAA, 0xAAAAAA, 0x55FF55, "", "Mod", false)),
            entry("jrmod",     new ChatTag(0xAAAAAA, 0xAAAAAA, 0x2AD42A, "", "JrMod", false)),
            entry("srhelper",  new ChatTag(0x808080, 0x808080, 0x2AFFAA, "", "SrHelper", false)),
            entry("helper",    new ChatTag(0xAAAAAA, 0xAAAAAA, 0x55FFFF, "", "Helper", false)),
            entry("jrhelper",  new ChatTag(0xAAAAAA, 0xAAAAAA, 0x55AAFF, "", "JrHelper", false)),
            entry("emeritus",  new ChatTag(0xFFAA00, 0xFFAA00, 0x2AD4D4, "", "Retired", false)),
            entry("retired",   new ChatTag(0x005555, 0x005555, 0x00AAAA, "", "Retired", false)),
            entry("builder",   new ChatTag(0x80552A, 0x80552A, 0xFFAA00, "", "Builder", false)),
            entry("sponsor", new ChatTag(0x7FFF7F, 0xAAFFAA, 0xAAFFAA, "", "Sponsor", false))
    );

    public static ArrayList<String> normalList = new ArrayList<String>(List.of(
            "overlord",
            "mythic",
            "emperor",
            "noble"
    ));

    public static ArrayList<String> staffList = new ArrayList<String>(List.of(
            "owner",
            "admin",
            "dev",
            "srmod",
            "mod",
            "jrmod",
            "srhelper",
            "helper",
            "jrhelper"
    ));

    public static ArrayList<String> specialList = new ArrayList<String>(List.of(
            "vip",
            "emeritus",
            "retired",
            "builder",
            "sponsor"
    ));
}
