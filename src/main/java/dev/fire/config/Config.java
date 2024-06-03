package dev.fire.config;

import com.google.gson.*;
import dev.fire.DFrevert;
import dev.fire.FileManager;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import net.minecraft.text.Text;

import java.awt.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Config {
    private static Config instance;
    public CharSetOption SaveCharSet = DefaultConfig.SaveCharSet;
    public CharSetOption FileCharSet = DefaultConfig.FileCharSet;

    public boolean ShortenedChatTags = DefaultConfig.ShortenedChatTags;
    public boolean DisableMod = DefaultConfig.DisableMod;
    public boolean DebugMode = DefaultConfig.DebugMode;
    public boolean VipEnabled = true;

    public Map<String, MiniMessageChatTag> chatTags = convertToMinimessage(DefaultConfig.oldChatTags);

    public static Map<String, MiniMessageChatTag> convertToMinimessage(Map<String, ChatTag> map) {
        Map<String, MiniMessageChatTag> new_map = new HashMap<String, MiniMessageChatTag>();

        for (Map.Entry<String, ChatTag> entry : map.entrySet()) {
            String key = entry.getKey();
            ChatTag value = entry.getValue();
            new_map.put(key, value.toMiniMessageClass());
        }
        return new_map;
    }

    public Config() { }

    public static Config getConfig() {
        if (instance == null) {
            try {
                instance = new Config();

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonObject object = new JsonParser().parse(FileManager.readConfig()).getAsJsonObject();


                // create chat tags
                Map<String, MiniMessageChatTag> chattaghashmap = new HashMap<String, MiniMessageChatTag>();

                JsonObject map_json_object = new JsonParser().parse(object.get("_JsonChatTags").getAsString()).getAsJsonObject();

                map_json_object.keySet().forEach(chatkey -> {
                    try {
                        JsonElement map_chat_key = map_json_object.get(chatkey);
                        MiniMessageChatTag new_value = gson.fromJson(map_chat_key.getAsString(), MiniMessageChatTag.class);
                        chattaghashmap.put(chatkey, new_value);
                    } catch (Exception exception) {
                        try {
                            throw new DfrevertException("Invalid Config!");
                        } catch (DfrevertException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

                Map<String, MiniMessageChatTag> copy_chatmap = Map.copyOf(chattaghashmap);

                if (copy_chatmap.size() != DefaultConfig.newChatTags.size()) {
                    throw new DfrevertException("Invalid Config!");
                }
                //instance.chatTags = copy_chatmap;

                // load primitives
                instance.VipEnabled = object.get("VipEnabled").getAsBoolean();
                instance.ShortenedChatTags =  object.get("ShortenedChatTags").getAsBoolean();
                instance.DisableMod = object.get("DisableMod").getAsBoolean();
                instance.DebugMode = object.get("DebugMode").getAsBoolean();

            } catch (Exception exception) {
                DFrevert.LOGGER.info("Config didn't load: " + exception);
                DFrevert.LOGGER.info("Making a new one.");
                instance = new Config();
                instance.save();
            }
        }
        return instance;
    }

    public static void clear() {
        instance = null;
    }

    private void save() {
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

    public YetAnotherConfigLib getLibConfig() {
        YetAnotherConfigLib.Builder yacl =
                YetAnotherConfigLib.createBuilder()
                        .title(Text.literal("Used for narration. Could be used to render a title in the future."))
                            .category(normalChatTags().build())
                            .category(staffChatTags().build())
                            .category(specialChatTags().build())
                            .category(miscCategory().build());
        return yacl.save(this::save).build();
    }


    private OptionGroup groupBuilder(String key, String name) {
        return OptionGroup.createBuilder()
                .name(Text.literal(name))
                .description(OptionDescription.of(Text.literal(name + " Chat Tag")))
                .option(Option.createBuilder(String.class)
                        .name(Text.literal("Left Text Value"))
                        .description(OptionDescription.createBuilder()
                                .text(Text.literal("Formatted in MiniMessage"))
                                .build())
                        .binding(
                                DefaultConfig.oldChatTags.get(key).toLeftMiniMessage(),
                                () -> chatTags.get(key).leftvalue,
                                opt -> chatTags.get(key).leftvalue = opt
                        )
                        .controller(StringControllerBuilder::create)
                        .build())
                .option(Option.createBuilder(String.class)
                        .name(Text.literal("Main Text Value"))
                        .description(OptionDescription.createBuilder()
                                .text(Text.literal("Formatted in MiniMessage"))
                                .build())
                        .binding(
                                DefaultConfig.oldChatTags.get(key).toMainMiniMessage(),
                                () -> chatTags.get(key).mainvalue,
                                opt -> chatTags.get(key).mainvalue = opt
                        )
                        .controller(StringControllerBuilder::create)
                        .build())
                .option(Option.createBuilder(String.class)
                        .name(Text.literal("Right Text Value"))
                        .description(OptionDescription.createBuilder()
                                .text(Text.literal("Formatted in MiniMessage"))
                                .build())
                        .binding(
                                DefaultConfig.oldChatTags.get(key).toRightMiniMessage(),
                                () -> chatTags.get(key).rightvalue,
                                opt -> chatTags.get(key).rightvalue = opt
                        )
                        .controller(StringControllerBuilder::create)
                        .build())
                .option(Option.createBuilder(String.class)
                        .name(Text.literal("Short Tag Value"))
                        .description(OptionDescription.createBuilder()
                                .text(Text.literal("Defines what the tag looks like when its compressed"))
                                .build())
                        .binding(
                                DefaultConfig.oldChatTags.get(key).toShortValue(),
                                () -> chatTags.get(key).shortValue,
                                opt -> chatTags.get(key).shortValue = opt
                        )
                        .controller(StringControllerBuilder::create)
                        .build())
                .build();

    }


    private ConfigCategory.Builder miscCategory() {
        return ConfigCategory.createBuilder()
                .name(Text.literal("Misc. Toggles"))
                .tooltip(Text.literal("Toggle visibility of chat tags."))
                .option(Option.createBuilder(boolean.class)
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
                        .build())
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

    private ConfigCategory.Builder staffChatTags() {
        ConfigCategory.Builder configBuilder = ConfigCategory.createBuilder()
                .name(Text.literal("Staff Chat Tags"))
                .tooltip(Text.literal("Modify the Staff chat tags."));

        ArrayList staffChatTagList = new ArrayList<>();
        staffChatTagList.addAll(DefaultConfig.staffList);

        staffChatTagList.forEach(k -> {
            String key = (String) k;
            String name = DefaultConfig.newChatTags.get(key).TextContent;
            if (key == "emeritus") {
                name = "Emeritus";
            }
            configBuilder.group(groupBuilder(key,name));
        });

        return configBuilder;
    }

    private ConfigCategory.Builder normalChatTags() {
        ConfigCategory.Builder configBuilder = ConfigCategory.createBuilder()
                .name(Text.literal("Chat Tags"))
                .tooltip(Text.literal("Modify the default chat tags."));

        ArrayList normalChatTagList = new ArrayList<>();
        normalChatTagList.addAll(DefaultConfig.normalList);
        normalChatTagList.forEach(k -> {
            String key = (String) k;
            String name = DefaultConfig.newChatTags.get(key).TextContent;
            configBuilder.group(groupBuilder(key,name));
        });

        return configBuilder;
    }

    private ConfigCategory.Builder specialChatTags() {
        ConfigCategory.Builder configBuilder = ConfigCategory.createBuilder()
                .name(Text.literal("Special Chat Tags"))
                .tooltip(Text.literal("Modify the special chat tags."));

        ArrayList specialChatTagList = new ArrayList<>();
        specialChatTagList.addAll(DefaultConfig.specialList);
        specialChatTagList.forEach(k -> {
            String key = (String) k;
            String name = DefaultConfig.newChatTags.get(key).TextContent;
            configBuilder.group(groupBuilder(key,name));
        });

        return configBuilder;
    }



    public enum CharSetOption {
        ISO_8859_1(StandardCharsets.ISO_8859_1),
        UTF_8(StandardCharsets.UTF_8);

        public final Charset charSet;

        CharSetOption(Charset charSet) {
            this.charSet = charSet;
        }
    }
}
