package dev.dfrevert.config;

import com.google.gson.*;
import dev.dfrevert.DFrevert;
import dev.dfrevert.FileManager;
import dev.dfrevert.utils.ChatTag;
import dev.dfrevert.utils.MiniMessageChatTag;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import net.minecraft.text.Text;

import java.awt.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Config {
    public static CharSetOption SaveCharSet = DefaultConfig.SaveCharSet;
    public static CharSetOption FileCharSet = DefaultConfig.FileCharSet;

    public static boolean ShortenedChatTags = DefaultConfig.ShortenedChatTags;
    public static boolean DisableMod = DefaultConfig.DisableMod;
    public static boolean DebugMode = DefaultConfig.DebugMode;
    public static boolean VipEnabled = true;

    public static Map<String, MiniMessageChatTag> chatTags = Map.copyOf(convertToMinimessage(DefaultConfig.oldChatTags));

    public static Map<String, MiniMessageChatTag> convertToMinimessage(Map<String, ChatTag> map) {
        Map<String, MiniMessageChatTag> new_map = new HashMap<>();

        for (Map.Entry<String, ChatTag> entry : map.entrySet()) {
            String key = entry.getKey();
            ChatTag value = entry.getValue();

            MiniMessageChatTag mmobj = value.toMiniMessageClass();
            if (Objects.equals(key, "vip")) {
                mmobj.mainvalue = getVipMain();
                mmobj.shortvalue = getVipShort();
            }

            if (Objects.equals(key, "sponsor")) {
                mmobj.mainvalue = getSponsorMain();
                mmobj.shortvalue = getSponsorShort();
            }
            new_map.put(key, mmobj);
        }
        return Map.copyOf(new_map);
    }

    public static String getVipMain() {
        int bracketColor = DefaultConfig.newChatTags.get("vip").BracketColor;
        int symbolColor = DefaultConfig.newChatTags.get("vip").SymbolColor;
        return ChatTag.convertStringWithColorToMiniMessage("[", bracketColor) +
                ChatTag.convertStringWithColorToMiniMessage("⭐", symbolColor) +
                ChatTag.convertStringWithColorToMiniMessage("]", bracketColor);
    }

    public static String getVipShort() {
        int bracketColor = DefaultConfig.newChatTags.get("vip").BracketColor;
        int symbolColor = DefaultConfig.newChatTags.get("vip").SymbolColor;
        return ChatTag.convertStringWithColorToMiniMessage("[", bracketColor) +
                ChatTag.convertStringWithColorToMiniMessage("VIP", symbolColor) +
                ChatTag.convertStringWithColorToMiniMessage("]", bracketColor);
    }

    public static String getSponsorShort() {
        int bracketColor = DefaultConfig.newChatTags.get("sponsor").BracketColor;
        int textColor = DefaultConfig.newChatTags.get("sponsor").TextColor;
        return ChatTag.convertStringWithColorToMiniMessage("[", bracketColor) +
                ChatTag.convertStringWithColorToMiniMessage("Sponsor", textColor) +
                ChatTag.convertStringWithColorToMiniMessage("]", bracketColor);
    }

    public static String getSponsorMain() {
        int bracketColor = DefaultConfig.newChatTags.get("sponsor").BracketColor;
        int symbolColor = DefaultConfig.newChatTags.get("sponsor").SymbolColor;
        return ChatTag.convertStringWithColorToMiniMessage("[", bracketColor) +
                ChatTag.convertStringWithColorToMiniMessage("◇", symbolColor) +
                ChatTag.convertStringWithColorToMiniMessage("]", bracketColor);
    }

    public static void getConfig() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject object = new JsonParser().parse(FileManager.readConfig()).getAsJsonObject();


            // create chat tags
            Map<String, MiniMessageChatTag> chattaghashmap = new HashMap<String, MiniMessageChatTag>();

            JsonObject map_json_object = new JsonParser().parse(object.get("_JsonChatTags").getAsString()).getAsJsonObject();

            for (String chatkey : map_json_object.keySet()) {
                JsonElement map_chat_key = map_json_object.get(chatkey);
                MiniMessageChatTag new_value = gson.fromJson(map_chat_key.getAsString(), MiniMessageChatTag.class);
                chattaghashmap.put(chatkey, new_value);
            }

            Map<String, MiniMessageChatTag> copy_chatmap = Map.copyOf(chattaghashmap);
            chatTags = copy_chatmap;

            // load primitives
            VipEnabled = object.get("VipEnabled").getAsBoolean();
            ShortenedChatTags =  object.get("ShortenedChatTags").getAsBoolean();
            DisableMod = object.get("DisableMod").getAsBoolean();
            DebugMode = object.get("DebugMode").getAsBoolean();

            DFrevert.LOGGER.info("Successfully loaded config!");
        } catch (Exception exception) {
            DFrevert.LOGGER.info("Config didn't load: " + exception);
            DFrevert.LOGGER.info("Making a new one.");
            chatTags = Map.copyOf(convertToMinimessage(DefaultConfig.oldChatTags));
        }
    }

    private static void save() {
        try {
            JsonObject object = new JsonObject();

            object.addProperty("VipEnabled", VipEnabled);
            object.addProperty("ShortenedChatTags", ShortenedChatTags);
            object.addProperty("DisableMod", DisableMod);
            object.addProperty("DebugMode", DebugMode);


            JsonObject JsonChatTags = new JsonObject();

            for (Map.Entry<String, MiniMessageChatTag> entry : chatTags.entrySet()) {
                String key = entry.getKey();
                MiniMessageChatTag chattag = entry.getValue();

                JsonChatTags.addProperty(key, chattag.toJson());
            }
            object.addProperty("_JsonChatTags", JsonChatTags.toString());

            FileManager.writeConfig(object.toString());

        } catch (Exception e) {
            DFrevert.LOGGER.info("Couldn't save config: " + e);
        }
    }

    public static YetAnotherConfigLib getLibConfig() {
        YetAnotherConfigLib.Builder yacl =
                YetAnotherConfigLib.createBuilder()
                        .title(Text.literal("Used for narration. Could be used to render a title in the future."))
                            .category(normalChatTags().build())
                            .category(staffChatTags().build())
                            .category(specialChatTags().build())
                            .category(miscCategory().build());
        return yacl.save(Config::save).build();
    }


    private static OptionGroup.Builder groupBuilder(String key, String name) {
        String mainDefault = DefaultConfig.oldChatTags.get(key).toMainMiniMessage();
        String shortDefault = DefaultConfig.oldChatTags.get(key).toShortValue();
        if (Objects.equals(key, "vip")) {
            mainDefault = getVipMain();
            shortDefault = getVipShort();
        }

        if (Objects.equals(key, "sponsor")) {
            mainDefault = getSponsorMain();
            shortDefault = getSponsorShort();
        }
        OptionGroup.Builder optionGroup = OptionGroup.createBuilder()
                .name(Text.literal(name))
                .description(OptionDescription.of(Text.literal(name + " Chat Tag")));
        if (key.equals("vip")) {
            optionGroup.option(Option.createBuilder(boolean.class)
                    .name(Text.literal("Vip Enabled"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.literal("Toggles weather the VIP tag is shown."))
                            .text(Text.literal("This also affects the VIP tag and VIP founding badge in player's profiles when you /whois them."))
                            .text(Text.literal(""))
                            .build())
                    .binding(
                            DefaultConfig.VipEnabled,
                            () -> VipEnabled,
                            opt -> VipEnabled = opt
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build());
        }

        optionGroup
            .option(Option.createBuilder(String.class)
                    .name(Text.literal("Main Text Value"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.literal("Formatted in MiniMessage"))
                            .build())
                    .binding(
                            mainDefault,
                            () -> chatTags.get(key).mainvalue,
                            opt -> chatTags.get(key).mainvalue = opt
                    )
                    .controller(StringControllerBuilder::create)
                    .build())
            .option(Option.createBuilder(String.class)
                    .name(Text.literal("Short Tag Value"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.literal("Defines what the tag looks like when its compressed"))
                            .build())
                    .binding(
                            shortDefault,
                            () -> chatTags.get(key).shortvalue,
                            opt -> chatTags.get(key).shortvalue = opt
                    )
                    .controller(StringControllerBuilder::create)
                    .build())
            .option(Option.createBuilder(Boolean.class)
                    .name(Text.literal("Enabled"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.literal("If disabled, default chat tags will be used instead of the tags above."))
                            .build())
                    .binding(
                            true,
                            () -> chatTags.get(key).isEnabled,
                            opt -> chatTags.get(key).isEnabled = opt
                    )
                    .controller(BooleanControllerBuilder::create)
                    .build());


        return optionGroup;



    }


    private static ConfigCategory.Builder miscCategory() {
        return ConfigCategory.createBuilder()
                .name(Text.literal("Misc. Toggles"))
                .tooltip(Text.literal("Some random options."))
                .option(Option.createBuilder(boolean.class)
                        .name(Text.literal("Disable Mod"))
                        .description(OptionDescription.createBuilder()
                                .text(Text.literal("Disables all changes this mod makes (idk why you would want this)"))
                                .build())
                        .binding(
                                DefaultConfig.DisableMod,
                                () -> DisableMod,
                                opt -> DisableMod = opt
                        )
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                .option(Option.createBuilder(boolean.class)
                        .name(Text.literal("Enable Shortened Chat Tags"))
                        .description(OptionDescription.createBuilder()
                                .text(Text.literal("Toggles weather you can see shortened chat tags while on plots."))
                                .build())
                        .binding(
                                DefaultConfig.ShortenedChatTags,
                                () -> ShortenedChatTags,
                                opt -> ShortenedChatTags = opt
                        )
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                .option(Option.createBuilder(boolean.class)
                        .name(Text.literal("Debug Mode"))
                        .description(OptionDescription.createBuilder()
                                .text(Text.literal("Prints the Text Literal of chat messages to the console (for debugging duh)"))
                                .build())
                        .binding(
                                DefaultConfig.DebugMode,
                                () -> DebugMode,
                                opt -> DebugMode = opt
                        )
                        .controller(TickBoxControllerBuilder::create)
                        .build());
    }

    private static ConfigCategory.Builder staffChatTags() {
        ConfigCategory.Builder configBuilder = ConfigCategory.createBuilder()
                .name(Text.literal("Staff Chat Tags"))
                .tooltip(Text.literal("Modify the Staff chat tags."));

        ArrayList<String> staffChatTagList = new ArrayList<>(DefaultConfig.staffList);

        staffChatTagList.forEach(k -> {
            String name = DefaultConfig.newChatTags.get(k).TextContent;


            OptionGroup.Builder builder = groupBuilder(k,name);
            configBuilder.group(builder.build());
        });

        return configBuilder;
    }

    private static ConfigCategory.Builder normalChatTags() {
        ConfigCategory.Builder configBuilder = ConfigCategory.createBuilder()
                .name(Text.literal("Chat Tags"))
                .tooltip(Text.literal("Modify the default chat tags."));

        ArrayList<String> normalChatTagList = new ArrayList<>(DefaultConfig.normalList);
        normalChatTagList.forEach(k -> {
            String name = DefaultConfig.newChatTags.get((String) k).TextContent;

            OptionGroup.Builder builder = groupBuilder((String) k,name);
            builder.option(Option.createBuilder(Color.class)
                    .name(Text.literal("Profile Color"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.literal("Color of your /whois, dictated by the highest paid rank you have. ie: Overlord, Mythic, Emperor, or Noble. "))
                            .build())
                    .binding(
                            new Color(DefaultConfig.oldChatTags.get((String) k).TextColor),
                            () -> new Color(chatTags.get((String) k).ProfileColor),
                            opt -> chatTags.get((String) k).ProfileColor = opt.getRGB()
                    )
                    .controller(ColorControllerBuilder::create)
                    .build());
            configBuilder.group(builder.build());
        });

        return configBuilder;
    }

    private static ConfigCategory.Builder specialChatTags() {
        ConfigCategory.Builder configBuilder = ConfigCategory.createBuilder()
                .name(Text.literal("Special Chat Tags"))
                .tooltip(Text.literal("Modify the special chat tags."));

        ArrayList<String> specialChatTagList = new ArrayList<>(DefaultConfig.specialList);
        specialChatTagList.forEach(k -> {
            String name = DefaultConfig.newChatTags.get(k).TextContent;
            if (Objects.equals(k, "emeritus")) {
                name = "Emeritus";
            }

            OptionGroup.Builder builder = groupBuilder((String) k,name);
            if (Objects.equals((String) k, "vip")) {
                builder.option(Option.createBuilder(Color.class)
                        .name(Text.literal("Badge Color"))
                        .description(OptionDescription.createBuilder()
                                .text(Text.literal("Color of your vip badge ._."))
                                .build())
                        .binding(
                                new Color(DefaultConfig.oldChatTags.get(k).TextColor),
                                () -> new Color(chatTags.get(k).ProfileColor),
                                opt -> chatTags.get(k).ProfileColor = opt.getRGB()
                        )
                        .controller(ColorControllerBuilder::create)
                        .build());
            }
            configBuilder.group(builder.build());
        });

        return configBuilder;
    }



    public enum CharSetOption {
        ISO_8859_1(StandardCharsets.ISO_8859_1),
        UTF_16(StandardCharsets.UTF_16),
        UTF_16BE(StandardCharsets.UTF_16BE),
        UTF_16LE(StandardCharsets.UTF_16LE),
        UTF_8(StandardCharsets.UTF_8);

        public final Charset charSet;

        CharSetOption(Charset charSet) {
            this.charSet = charSet;
        }
    }
}
