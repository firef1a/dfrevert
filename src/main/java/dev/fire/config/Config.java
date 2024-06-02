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
    public boolean VipEnabled = true;

    public Map<String, ChatTag> chatTags = Map.copyOf(DefaultConfig.oldChatTags);

    public Config() { }

    public static Config getConfig() {
        if (instance == null) {
            try {
                instance = new Config();

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonObject object = new JsonParser().parse(FileManager.readConfig()).getAsJsonObject();


                // create chat tags
                Map<String, ChatTag> chattaghashmap = new HashMap<String, ChatTag>();

                JsonObject map_json_object = new JsonParser().parse(object.get("_JsonChatTags").getAsString()).getAsJsonObject();

                map_json_object.keySet().forEach(chatkey -> {
                    try {
                        JsonElement map_chat_key = map_json_object.get(chatkey);
                        ChatTag new_value = gson.fromJson(map_chat_key.getAsString(), ChatTag.class);
                        chattaghashmap.put(chatkey, new_value);
                    } catch (Exception exception) {
                        try {
                            throw new DfrevertException("Invalid Config!");
                        } catch (DfrevertException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

                Map<String, ChatTag> copy_chatmap = Map.copyOf(chattaghashmap);

                if (copy_chatmap.size() != DefaultConfig.newChatTags.size()) {
                    throw new DfrevertException("Invalid Config!");
                }
                instance.chatTags = copy_chatmap;

                // load primitives
                instance.VipEnabled = object.get("VipEnabled").getAsBoolean();
                instance.ShortenedChatTags =  object.get("ShortenedChatTags").getAsBoolean();
                instance.DisableMod = object.get("DisableMod").getAsBoolean();

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

            JsonObject JsonChatTags = new JsonObject();

            for (Map.Entry<String, ChatTag> entry : chatTags.entrySet()) {
                String key = entry.getKey();
                ChatTag chattag = entry.getValue();

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
                            .category(miscCategory().build());
        return yacl.save(this::save).build();
    }


    private ConfigCategory.Builder miscCategory() {
        return ConfigCategory.createBuilder()
                .name(Text.literal("Misc. Toggles"))
                .tooltip(Text.literal("Toggle visibility of chat tags."))

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
                .build());

    }

    private ConfigCategory.Builder staffChatTags() {
        ConfigCategory.Builder configBuilder = ConfigCategory.createBuilder()
                .name(Text.literal("Staff Chat Tags"))
                .tooltip(Text.literal("Modify the Staff chat tags."));

        ArrayList staffChatTagList = new ArrayList<>();
        staffChatTagList.addAll(DefaultConfig.staffList);

        staffChatTagList.forEach(key -> {
            String name = DefaultConfig.newChatTags.get(key).TextContent;
            if (key == "emeritus") {
                name = "Emeritus";
            }
            configBuilder.group(OptionGroup.createBuilder()
                    .name(Text.literal(name))
                    .description(OptionDescription.of(Text.literal(name + " Chat Tag")))

                    .option(Option.createBuilder(Color.class)
                            .name(Text.literal("Bracket Color"))
                            .description(OptionDescription.createBuilder()
                                    .text(Text.literal("Color of brackets"))
                                    .build())
                            .binding(
                                    new Color(DefaultConfig.oldChatTags.get(key).BracketColor),
                                    () -> new Color(chatTags.get(key).BracketColor),
                                    opt -> chatTags.get(key).BracketColor = opt.getRGB()
                            )
                            .controller(ColorControllerBuilder::create)
                            .build())
                    .option(Option.createBuilder(Color.class)
                            .name(Text.literal("Symbol Color"))
                            .description(OptionDescription.createBuilder()
                                    .text(Text.literal("Color of symbols"))
                                    .build())
                            .binding(
                                    new Color(DefaultConfig.oldChatTags.get(key).SymbolColor),
                                    () -> new Color(chatTags.get(key).SymbolColor),
                                    opt -> chatTags.get(key).SymbolColor = opt.getRGB()
                            )
                            .controller(ColorControllerBuilder::create)
                            .build())
                    .option(Option.createBuilder(Color.class)
                            .name(Text.literal("Text Content Color"))
                            .description(OptionDescription.createBuilder()
                                    .text(Text.literal("Color of the main tag text"))
                                    .build())
                            .binding(
                                    new Color(DefaultConfig.oldChatTags.get(key).TextColor),
                                    () -> new Color(chatTags.get(key).TextColor),
                                    opt -> chatTags.get(key).TextColor = opt.getRGB()
                            )
                            .controller(ColorControllerBuilder::create)
                            .build())
                    .option(Option.createBuilder(String.class)
                            .name(Text.literal("Symbol"))
                            .description(OptionDescription.createBuilder()
                                    .text(Text.literal("Symbol(s) that go on the left and right side of the main text content"))
                                    .build())
                            .binding(
                                    DefaultConfig.oldChatTags.get(key).Symbol,
                                    () -> chatTags.get(key).Symbol,
                                    opt -> chatTags.get(key).Symbol = opt
                            )
                            .controller(StringControllerBuilder::create)
                            .build())
                    .option(Option.createBuilder(String.class)
                            .name(Text.literal("Text Content"))
                            .description(OptionDescription.createBuilder()
                                    .text(Text.literal("The main text of the tag"))
                                    .build())
                            .binding(
                                    DefaultConfig.oldChatTags.get(key).TextContent,
                                    () -> chatTags.get(key).TextContent,
                                    opt -> chatTags.get(key).TextContent = opt
                            )
                            .controller(StringControllerBuilder::create)
                            .build())
                    .build());
        });

        return configBuilder;
    }






    private ConfigCategory.Builder normalChatTags() {
        ConfigCategory.Builder configBuilder = ConfigCategory.createBuilder()
                .name(Text.literal("Chat Tags"))
                .tooltip(Text.literal("Modify the default chat tags."));

        ArrayList normalChatTagList = new ArrayList<>();
        normalChatTagList.addAll(DefaultConfig.normalList);

        OptionGroup vipGroup = OptionGroup.createBuilder()
                .name(Text.literal("⭐ VIP"))
                .description(OptionDescription.of(Text.literal("VIP Chat Tag")))

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

                .option(Option.createBuilder(Color.class)
                        .name(Text.literal("Bracket Color"))
                        .description(OptionDescription.createBuilder()
                                .text(Text.literal("Color of brackets"))
                                .build())
                        .binding(
                                new Color(DefaultConfig.oldChatTags.get("vip").BracketColor),
                                () -> new Color(chatTags.get("vip").BracketColor),
                                opt -> chatTags.get("vip").BracketColor = opt.getRGB()
                        )
                        .controller(ColorControllerBuilder::create)
                        .build())
                .option(Option.createBuilder(Color.class)
                        .name(Text.literal("Symbol Color"))
                        .description(OptionDescription.createBuilder()
                                .text(Text.literal("Color of symbols"))
                                .build())
                        .binding(
                                new Color(DefaultConfig.oldChatTags.get("vip").SymbolColor),
                                () -> new Color(chatTags.get("vip").SymbolColor),
                                opt -> chatTags.get("vip").SymbolColor = opt.getRGB()
                        )
                        .controller(ColorControllerBuilder::create)
                        .build())
                .option(Option.createBuilder(Color.class)
                        .name(Text.literal("Text Content Color"))
                        .description(OptionDescription.createBuilder()
                                .text(Text.literal("Color of the main tag text"))
                                .build())
                        .binding(
                                new Color(DefaultConfig.oldChatTags.get("vip").TextColor),
                                () -> new Color(chatTags.get("vip").TextColor),
                                opt -> chatTags.get("vip").TextColor = opt.getRGB()
                        )
                        .controller(ColorControllerBuilder::create)
                        .build())
                .option(Option.createBuilder(String.class)
                        .name(Text.literal("Symbol"))
                        .description(OptionDescription.createBuilder()
                                .text(Text.literal("The main text of the chat tag, also affects the founding badge."))
                                .build())
                        .binding(
                                DefaultConfig.oldChatTags.get("vip").Symbol,
                                () -> chatTags.get("vip").Symbol,
                                opt -> chatTags.get("vip").Symbol = opt
                        )
                        .controller(StringControllerBuilder::create)
                        .build())
                .option(Option.createBuilder(String.class)
                        .name(Text.literal("Text Content"))
                        .description(OptionDescription.createBuilder()
                                .text(Text.literal("The main text content, this only appears in the /whois"))
                                .build())
                        .binding(
                                DefaultConfig.oldChatTags.get("vip").TextContent,
                                () -> chatTags.get("vip").TextContent,
                                opt -> chatTags.get("vip").TextContent = opt
                        )
                        .controller(StringControllerBuilder::create)
                        .build())
                .build();

        configBuilder.group(vipGroup);

        normalChatTagList.forEach(key -> {
            String name = DefaultConfig.newChatTags.get(key).TextContent;
            configBuilder.group(OptionGroup.createBuilder()
                    .name(Text.literal(name))
                    .description(OptionDescription.of(Text.literal(name + " Chat Tag")))

                    .option(Option.createBuilder(Color.class)
                            .name(Text.literal("Bracket Color"))
                            .description(OptionDescription.createBuilder()
                                    .text(Text.literal("Color of brackets"))
                                    .build())
                            .binding(
                                    new Color(DefaultConfig.oldChatTags.get(key).BracketColor),
                                    () -> new Color(chatTags.get(key).BracketColor),
                                    opt -> chatTags.get(key).BracketColor = opt.getRGB()
                            )
                            .controller(ColorControllerBuilder::create)
                            .build())
                    .option(Option.createBuilder(Color.class)
                            .name(Text.literal("Symbol Color"))
                            .description(OptionDescription.createBuilder()
                                    .text(Text.literal("Color of symbols"))
                                    .build())
                            .binding(
                                    new Color(DefaultConfig.oldChatTags.get(key).SymbolColor),
                                    () -> new Color(chatTags.get(key).SymbolColor),
                                    opt -> chatTags.get(key).SymbolColor = opt.getRGB()
                            )
                            .controller(ColorControllerBuilder::create)
                            .build())
                    .option(Option.createBuilder(Color.class)
                            .name(Text.literal("Text Content Color"))
                            .description(OptionDescription.createBuilder()
                                    .text(Text.literal("Color of the main tag text"))
                                    .build())
                            .binding(
                                    new Color(DefaultConfig.oldChatTags.get(key).TextColor),
                                    () -> new Color(chatTags.get(key).TextColor),
                                    opt -> chatTags.get(key).TextColor = opt.getRGB()
                            )
                            .controller(ColorControllerBuilder::create)
                            .build())
                    .option(Option.createBuilder(String.class)
                            .name(Text.literal("Symbol"))
                            .description(OptionDescription.createBuilder()
                                    .text(Text.literal("Symbol(s) that go on the left and right side of the main text content"))
                                    .build())
                            .binding(
                                    DefaultConfig.oldChatTags.get(key).Symbol,
                                    () -> chatTags.get(key).Symbol,
                                    opt -> chatTags.get(key).Symbol = opt
                            )
                            .controller(StringControllerBuilder::create)
                            .build())
                    .option(Option.createBuilder(String.class)
                            .name(Text.literal("Text Content"))
                            .description(OptionDescription.createBuilder()
                                    .text(Text.literal("The main text of the tag"))
                                    .build())
                            .binding(
                                    DefaultConfig.oldChatTags.get(key).TextContent,
                                    () -> chatTags.get(key).TextContent,
                                    opt -> chatTags.get(key).TextContent = opt
                            )
                            .controller(StringControllerBuilder::create)
                            .build())
                    .build());
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
