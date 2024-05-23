package dev.fire.config;


import java.lang.reflect.Array;
import dev.fire.config.ChatTag;
import java.util.Arrays;
import java.util.List;

public class DefaultConfig {
    public static Config.CharSetOption SaveCharSet = Config.CharSetOption.UTF_8;
    public static Config.CharSetOption FileCharSet = Config.CharSetOption.UTF_8;

    public static boolean VipEnabled = true;

    public static boolean DisableMod = false;
    public static boolean ShortenedChatTags = true;

    public static ChatTag Vip =      new ChatTag(0xFFAA00,0xFFD47F,0xFFD47F,"⭐","VIP");

    public static ChatTag Overlord = new ChatTag(0x00AAAA, 0xAAAAAA, 0xAA0000, "◆", "Overlord");
    public static ChatTag Mythic =   new ChatTag(0x555555, 0x555555, 0xAA00AA, "", "Mythic");
    public static ChatTag Emperor =  new ChatTag(0x00AA00, 0x00AA00, 0x55FFFF, "", "Emperor");
    public static ChatTag Noble =    new ChatTag(0xFFAA00, 0xFFAA00, 0x55FF55, "", "Noble");

    public static ChatTag Owner =    new ChatTag(0x000000, 0x000000, 0xFF0000, "", "Owner");
    public static ChatTag Admin =    new ChatTag(0x555555, 0x555555, 0xFF0000, "", "Admin");
    public static ChatTag Dev =      new ChatTag(0x555555, 0x555555, 0xFF7FAA, "", "Dev");
    public static ChatTag SrMod =    new ChatTag(0x808080, 0x808080, 0xAAFF55, "", "SrMod");
    public static ChatTag Mod =      new ChatTag(0xAAAAAA, 0xAAAAAA, 0x55FF55, "", "Mod");
    public static ChatTag JrMod =    new ChatTag(0xAAAAAA, 0xAAAAAA, 0x2AD42A, "", "JrMod");
    public static ChatTag SrHelper = new ChatTag(0x808080, 0x808080, 0x2AFFAA, "", "SrHelper");
    public static ChatTag Helper =   new ChatTag(0xAAAAAA, 0xAAAAAA, 0x55FFFF, "", "Helper");
    public static ChatTag JrHelper = new ChatTag(0xAAAAAA, 0xAAAAAA, 0x55AAFF, "", "JrHelper");

    public static ChatTag Retired = new ChatTag(0xFFAA00, 0xFFAA00, 0x2AD4D4, "", "Retired");

}
