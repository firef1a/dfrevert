package dev.fire.config;

import com.google.gson.JsonObject;
import dev.fire.DFrevert;
import dev.fire.FileManager;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import dev.isxander.yacl3.gui.controllers.cycling.EnumController;
import dev.isxander.yacl3.impl.controller.IntegerFieldControllerBuilderImpl;
import io.netty.handler.ssl.IdentityCipherSuiteFilter;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import dev.fire.config.DefaultConfig;

import dev.fire.config.ChatTag;
import java.awt.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Config {
    private static Config instance;
    public CharSetOption SaveCharSet = DefaultConfig.SaveCharSet;
    public CharSetOption FileCharSet = DefaultConfig.FileCharSet;

    public boolean ShortenedChatTags = DefaultConfig.ShortenedChatTags;
    public boolean DisableMod = DefaultConfig.DisableMod;
    public boolean VipEnabled = true;

    public int VipBracketColor = DefaultConfig.Vip.BracketColor;
    public int VipSymbolColor = DefaultConfig.Vip.SymbolColor;
    public int VipTextColor = DefaultConfig.Vip.TextColor;
    public String VipSymbol = DefaultConfig.Vip.Symbol;
    public String VipTextContent = DefaultConfig.Vip.TextContent;

    public int OverlordBracketColor = DefaultConfig.Overlord.BracketColor;
    public int OverlordSymbolColor = DefaultConfig.Overlord.SymbolColor;
    public int OverlordTextColor = DefaultConfig.Overlord.TextColor;
    public String OverlordSymbol = DefaultConfig.Overlord.Symbol;
    public String OverlordTextContent = DefaultConfig.Overlord.TextContent;

    public int MythicBracketColor = DefaultConfig.Mythic.BracketColor;
    public int MythicSymbolColor = DefaultConfig.Mythic.SymbolColor;
    public int MythicTextColor = DefaultConfig.Mythic.TextColor;
    public String MythicSymbol = DefaultConfig.Mythic.Symbol;
    public String MythicTextContent = DefaultConfig.Mythic.TextContent;

    public int EmperorBracketColor = DefaultConfig.Emperor.BracketColor;
    public int EmperorSymbolColor = DefaultConfig.Emperor.SymbolColor;
    public int EmperorTextColor = DefaultConfig.Emperor.TextColor;
    public String EmperorSymbol = DefaultConfig.Emperor.Symbol;
    public String EmperorTextContent = DefaultConfig.Emperor.TextContent;

    public int NobleBracketColor = DefaultConfig.Noble.BracketColor;
    public int NobleSymbolColor = DefaultConfig.Noble.SymbolColor;
    public int NobleTextColor = DefaultConfig.Noble.TextColor;
    public String NobleSymbol = DefaultConfig.Noble.Symbol;
    public String NobleTextContent = DefaultConfig.Noble.TextContent;



    public int OwnerBracketColor = DefaultConfig.Owner.BracketColor;
    public int OwnerSymbolColor = DefaultConfig.Owner.SymbolColor;
    public int OwnerTextColor = DefaultConfig.Owner.TextColor;
    public String OwnerSymbol = DefaultConfig.Owner.Symbol;
    public String OwnerTextContent = DefaultConfig.Owner.TextContent;

    public int AdminBracketColor = DefaultConfig.Admin.BracketColor;
    public int AdminSymbolColor = DefaultConfig.Admin.SymbolColor;
    public int AdminTextColor = DefaultConfig.Admin.TextColor;
    public String AdminSymbol = DefaultConfig.Admin.Symbol;
    public String AdminTextContent = DefaultConfig.Admin.TextContent;

    public int DevBracketColor = DefaultConfig.Dev.BracketColor;
    public int DevSymbolColor = DefaultConfig.Dev.SymbolColor;
    public int DevTextColor = DefaultConfig.Dev.TextColor;
    public String DevSymbol = DefaultConfig.Dev.Symbol;
    public String DevTextContent = DefaultConfig.Dev.TextContent;

    public int SrModBracketColor = DefaultConfig.SrMod.BracketColor;
    public int SrModSymbolColor = DefaultConfig.SrMod.SymbolColor;
    public int SrModTextColor = DefaultConfig.SrMod.TextColor;
    public String SrModSymbol = DefaultConfig.SrMod.Symbol;
    public String SrModTextContent = DefaultConfig.SrMod.TextContent;

    public int ModBracketColor = DefaultConfig.Mod.BracketColor;
    public int ModSymbolColor = DefaultConfig.Mod.SymbolColor;
    public int ModTextColor = DefaultConfig.Mod.TextColor;
    public String ModSymbol = DefaultConfig.Mod.Symbol;
    public String ModTextContent = DefaultConfig.Mod.TextContent;

    public int JrModBracketColor = DefaultConfig.JrMod.BracketColor;
    public int JrModSymbolColor = DefaultConfig.JrMod.SymbolColor;
    public int JrModTextColor = DefaultConfig.JrMod.TextColor;
    public String JrModSymbol = DefaultConfig.JrMod.Symbol;
    public String JrModTextContent = DefaultConfig.JrMod.TextContent;

    public int SrHelperBracketColor = DefaultConfig.SrHelper.BracketColor;
    public int SrHelperSymbolColor = DefaultConfig.SrHelper.SymbolColor;
    public int SrHelperTextColor = DefaultConfig.SrHelper.TextColor;
    public String SrHelperSymbol = DefaultConfig.SrHelper.Symbol;
    public String SrHelperTextContent = DefaultConfig.SrHelper.TextContent;

    public int HelperBracketColor = DefaultConfig.Helper.BracketColor;
    public int HelperSymbolColor = DefaultConfig.Helper.SymbolColor;
    public int HelperTextColor = DefaultConfig.Helper.TextColor;
    public String HelperSymbol = DefaultConfig.Helper.Symbol;
    public String HelperTextContent = DefaultConfig.Helper.TextContent;

    public int JrHelperBracketColor = DefaultConfig.JrHelper.BracketColor;
    public int JrHelperSymbolColor = DefaultConfig.JrHelper.SymbolColor;
    public int JrHelperTextColor = DefaultConfig.JrHelper.TextColor;
    public String JrHelperSymbol = DefaultConfig.JrHelper.Symbol;
    public String JrHelperTextContent = DefaultConfig.JrHelper.TextContent;


    public Config() { }

    public static Config getConfig() {
        if (instance == null) {
            try {
                instance = DFrevert.gson.fromJson(FileManager.readConfig(), Config.class);
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

    private JsonObject addChatTag(JsonObject object, String name, int BracketColor, int SymbolColor, int TextColor, String Symbol, String TextContent) {
        object.addProperty(name+"BracketColor", BracketColor);
        object.addProperty(name+"SymbolColor", SymbolColor);
        object.addProperty(name+"TextColor", TextColor);
        object.addProperty(name+"Symbol", Symbol);
        object.addProperty(name+"TextContent", TextContent);
        return object;
    }

    private void save() {
        try {
            JsonObject object = new JsonObject();
            String name = "";

            object.addProperty("VipEnabled", VipEnabled);

            object = addChatTag(object, "Vip", VipBracketColor, VipSymbolColor, VipTextColor, VipSymbol, VipTextContent);
            object = addChatTag(object, "Overlord", OverlordBracketColor, OverlordSymbolColor, OverlordTextColor, OverlordSymbol, OverlordTextContent);
            object = addChatTag(object, "Mythic", MythicBracketColor, MythicSymbolColor, MythicTextColor, MythicSymbol, MythicTextContent);
            object = addChatTag(object, "Emperor", EmperorBracketColor, EmperorSymbolColor, EmperorTextColor, EmperorSymbol, EmperorTextContent);
            object = addChatTag(object, "Noble", NobleBracketColor, NobleSymbolColor, NobleTextColor, NobleSymbol, NobleTextContent);

            object = addChatTag(object, "Owner", OwnerBracketColor, OwnerSymbolColor, OwnerTextColor, OwnerSymbol, OwnerTextContent);
            object = addChatTag(object, "Admin", AdminBracketColor, AdminSymbolColor, AdminTextColor, AdminSymbol, AdminTextContent);
            object = addChatTag(object, "Dev", DevBracketColor, DevSymbolColor, DevTextColor, DevSymbol, DevTextContent);
            object = addChatTag(object, "SrMod", SrModBracketColor, SrModSymbolColor, SrModTextColor, SrModSymbol, SrModTextContent);
            object = addChatTag(object, "Mod", ModBracketColor, ModSymbolColor, ModTextColor, ModSymbol, ModTextContent);
            object = addChatTag(object, "JrMod", JrModBracketColor, JrModSymbolColor, JrModTextColor, JrModSymbol, JrModTextContent);
            object = addChatTag(object, "SrHelper", SrHelperBracketColor, SrHelperSymbolColor, SrHelperTextColor, SrHelperSymbol, SrHelperTextContent);
            object = addChatTag(object, "Helper", HelperBracketColor, HelperSymbolColor, HelperTextColor, HelperSymbol, HelperTextContent);
            object = addChatTag(object, "JrHelper", JrHelperBracketColor, JrHelperSymbolColor, JrHelperTextColor, JrHelperSymbol, JrHelperTextContent);




            FileManager.writeConfig(object.toString());
        } catch (Exception e) {
            DFrevert.LOGGER.info("Couldn't save config: " + e);
        }
    }

    public YetAnotherConfigLib getLibConfig() {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("Used for narration. Could be used to render a title in the future."))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Chat Tags"))
                        .tooltip(Text.literal("Modify the default chat tags."))

                        // VIP
                        .group(OptionGroup.createBuilder()
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
                                                new Color(DefaultConfig.Vip.BracketColor),
                                                () -> new Color(VipBracketColor),
                                                opt -> VipBracketColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Symbol Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of symbols"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Vip.SymbolColor),
                                                () -> new Color(VipSymbolColor),
                                                opt -> VipSymbolColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Text Content Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of the main tag text"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Vip.TextColor),
                                                () -> new Color(VipTextColor),
                                                opt -> VipTextColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Symbol"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("The main text of the chat tag, also affects the founding badge."))
                                                .build())
                                        .binding(
                                                DefaultConfig.Vip.Symbol,
                                                () -> VipSymbol,
                                                opt -> VipSymbol = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Text Content"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("The main text content, this only appears in the /whois"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Vip.TextContent,
                                                () -> VipTextContent,
                                                opt -> VipTextContent = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())

                                .build())

                        //Overlord
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Overlord"))
                                .description(OptionDescription.of(Text.literal("Overlord Chat Tag")))

                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Bracket Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of brackets"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Overlord.BracketColor),
                                                () -> new Color(OverlordBracketColor),
                                                opt -> OverlordBracketColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Symbol Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of symbols"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Overlord.SymbolColor),
                                                () -> new Color(OverlordSymbolColor),
                                                opt -> OverlordSymbolColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Text Content Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of the main tag text"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Overlord.TextColor),
                                                () -> new Color(OverlordTextColor),
                                                opt -> OverlordTextColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Symbol"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Symbol(s) that go on the left and right side of the main text content"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Overlord.Symbol,
                                                () -> OverlordSymbol,
                                                opt -> OverlordSymbol = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Text Content"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("The main text of the tag"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Overlord.TextContent,
                                                () -> OverlordTextContent,
                                                opt -> OverlordTextContent = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())

                                .build())

                        // Mythic
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Mythic"))
                                .description(OptionDescription.of(Text.literal("Mythic Chat Tag")))

                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Bracket Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of brackets"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Mythic.BracketColor),
                                                () -> new Color(MythicBracketColor),
                                                opt -> MythicBracketColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Symbol Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of symbols"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Mythic.SymbolColor),
                                                () -> new Color(MythicSymbolColor),
                                                opt -> MythicSymbolColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Text Content Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of the main tag text"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Mythic.TextColor),
                                                () -> new Color(MythicTextColor),
                                                opt -> MythicTextColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Symbol"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Symbol(s) that go on the left and right side of the main text content"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Mythic.Symbol,
                                                () -> MythicSymbol,
                                                opt -> MythicSymbol = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Text Content"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("The main text of the tag"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Mythic.TextContent,
                                                () -> MythicTextContent,
                                                opt -> MythicTextContent = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())

                                .build())
                        // Emperor
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Emperor"))
                                .description(OptionDescription.of(Text.literal("Emperor Chat Tag")))

                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Bracket Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of brackets"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Emperor.BracketColor),
                                                () -> new Color(EmperorBracketColor),
                                                opt -> EmperorBracketColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Symbol Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of symbols"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Emperor.SymbolColor),
                                                () -> new Color(EmperorSymbolColor),
                                                opt -> EmperorSymbolColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Text Content Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of the main tag text"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Emperor.TextColor),
                                                () -> new Color(EmperorTextColor),
                                                opt -> EmperorTextColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Symbol"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Symbol(s) that go on the left and right side of the main text content"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Emperor.Symbol,
                                                () -> EmperorSymbol,
                                                opt -> EmperorSymbol = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Text Content"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("The main text of the tag"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Emperor.TextContent,
                                                () -> EmperorTextContent,
                                                opt -> EmperorTextContent = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())

                                .build())
                        // Noble
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Noble"))
                                .description(OptionDescription.of(Text.literal("Noble Chat Tag")))

                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Bracket Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of brackets"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Noble.BracketColor),
                                                () -> new Color(NobleBracketColor),
                                                opt -> NobleBracketColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Symbol Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of symbols"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Noble.SymbolColor),
                                                () -> new Color(NobleSymbolColor),
                                                opt -> NobleSymbolColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Text Content Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of the main tag text"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Noble.TextColor),
                                                () -> new Color(NobleTextColor),
                                                opt -> NobleTextColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Symbol"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Symbol(s) that go on the left and right side of the main text content"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Noble.Symbol,
                                                () -> NobleSymbol,
                                                opt -> NobleSymbol = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Text Content"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("The main text of the tag"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Noble.TextContent,
                                                () -> NobleTextContent,
                                                opt -> NobleTextContent = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())

                                .build())
                        .build())





                // Staff Chat Tags --------------------------------------------------------------------------------------------
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Staff Chat Tags"))
                        .tooltip(Text.literal("Modify the Staff chat tags."))

                        // Owner
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Owner"))
                                .description(OptionDescription.of(Text.literal("Owner Chat Tag")))

                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Bracket Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of brackets"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Owner.BracketColor),
                                                () -> new Color(OwnerBracketColor),
                                                opt -> OwnerBracketColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Symbol Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of symbols"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Owner.SymbolColor),
                                                () -> new Color(OwnerSymbolColor),
                                                opt -> OwnerSymbolColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Text Content Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of the main tag text"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Owner.TextColor),
                                                () -> new Color(OwnerTextColor),
                                                opt -> OwnerTextColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Symbol"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Symbol(s) that go on the left and right side of the main text content"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Owner.Symbol,
                                                () -> OwnerSymbol,
                                                opt -> OwnerSymbol = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Text Content"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("The main text of the tag"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Owner.TextContent,
                                                () -> OwnerTextContent,
                                                opt -> OwnerTextContent = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())

                                .build())
                        // Admin
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Admin"))
                                .description(OptionDescription.of(Text.literal("Admin Chat Tag")))

                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Bracket Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of brackets"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Admin.BracketColor),
                                                () -> new Color(AdminBracketColor),
                                                opt -> AdminBracketColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Symbol Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of symbols"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Admin.SymbolColor),
                                                () -> new Color(AdminSymbolColor),
                                                opt -> AdminSymbolColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Text Content Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of the main tag text"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Admin.TextColor),
                                                () -> new Color(AdminTextColor),
                                                opt -> AdminTextColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Symbol"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Symbol(s) that go on the left and right side of the main text content"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Admin.Symbol,
                                                () -> AdminSymbol,
                                                opt -> AdminSymbol = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Text Content"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("The main text of the tag"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Admin.TextContent,
                                                () -> AdminTextContent,
                                                opt -> AdminTextContent = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())

                                .build())
                        // Dev
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Dev"))
                                .description(OptionDescription.of(Text.literal("Dev Chat Tag")))

                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Bracket Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of brackets"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Dev.BracketColor),
                                                () -> new Color(DevBracketColor),
                                                opt -> DevBracketColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Symbol Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of symbols"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Dev.SymbolColor),
                                                () -> new Color(DevSymbolColor),
                                                opt -> DevSymbolColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Text Content Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of the main tag text"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Dev.TextColor),
                                                () -> new Color(DevTextColor),
                                                opt -> DevTextColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Symbol"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Symbol(s) that go on the left and right side of the main text content"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Dev.Symbol,
                                                () -> DevSymbol,
                                                opt -> DevSymbol = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Text Content"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("The main text of the tag"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Dev.TextContent,
                                                () -> DevTextContent,
                                                opt -> DevTextContent = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())

                                .build())
                        // SrMod
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("SrMod"))
                                .description(OptionDescription.of(Text.literal("SrMod Chat Tag")))

                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Bracket Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of brackets"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.SrMod.BracketColor),
                                                () -> new Color(SrModBracketColor),
                                                opt -> SrModBracketColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Symbol Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of symbols"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.SrMod.SymbolColor),
                                                () -> new Color(SrModSymbolColor),
                                                opt -> SrModSymbolColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Text Content Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of the main tag text"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.SrMod.TextColor),
                                                () -> new Color(SrModTextColor),
                                                opt -> SrModTextColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Symbol"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Symbol(s) that go on the left and right side of the main text content"))
                                                .build())
                                        .binding(
                                                DefaultConfig.SrMod.Symbol,
                                                () -> SrModSymbol,
                                                opt -> SrModSymbol = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Text Content"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("The main text of the tag"))
                                                .build())
                                        .binding(
                                                DefaultConfig.SrMod.TextContent,
                                                () -> SrModTextContent,
                                                opt -> SrModTextContent = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())

                                .build())
                        // Mod
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Mod"))
                                .description(OptionDescription.of(Text.literal("Mod Chat Tag")))

                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Bracket Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of brackets"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Mod.BracketColor),
                                                () -> new Color(ModBracketColor),
                                                opt -> ModBracketColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Symbol Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of symbols"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Mod.SymbolColor),
                                                () -> new Color(ModSymbolColor),
                                                opt -> ModSymbolColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Text Content Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of the main tag text"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Mod.TextColor),
                                                () -> new Color(ModTextColor),
                                                opt -> ModTextColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Symbol"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Symbol(s) that go on the left and right side of the main text content"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Mod.Symbol,
                                                () -> ModSymbol,
                                                opt -> ModSymbol = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Text Content"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("The main text of the tag"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Mod.TextContent,
                                                () -> ModTextContent,
                                                opt -> ModTextContent = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())

                                .build())
                        // JrMod
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("JrMod"))
                                .description(OptionDescription.of(Text.literal("JrMod Chat Tag")))

                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Bracket Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of brackets"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.JrMod.BracketColor),
                                                () -> new Color(JrModBracketColor),
                                                opt -> JrModBracketColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Symbol Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of symbols"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.JrMod.SymbolColor),
                                                () -> new Color(JrModSymbolColor),
                                                opt -> JrModSymbolColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Text Content Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of the main tag text"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.JrMod.TextColor),
                                                () -> new Color(JrModTextColor),
                                                opt -> JrModTextColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Symbol"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Symbol(s) that go on the left and right side of the main text content"))
                                                .build())
                                        .binding(
                                                DefaultConfig.JrMod.Symbol,
                                                () -> JrModSymbol,
                                                opt -> JrModSymbol = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Text Content"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("The main text of the tag"))
                                                .build())
                                        .binding(
                                                DefaultConfig.JrMod.TextContent,
                                                () -> JrModTextContent,
                                                opt -> JrModTextContent = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())

                                .build())
                        // SrHelper
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("SrHelper"))
                                .description(OptionDescription.of(Text.literal("SrHelper Chat Tag")))

                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Bracket Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of brackets"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.SrHelper.BracketColor),
                                                () -> new Color(SrHelperBracketColor),
                                                opt -> SrHelperBracketColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Symbol Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of symbols"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.SrHelper.SymbolColor),
                                                () -> new Color(SrHelperSymbolColor),
                                                opt -> SrHelperSymbolColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Text Content Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of the main tag text"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.SrHelper.TextColor),
                                                () -> new Color(SrHelperTextColor),
                                                opt -> SrHelperTextColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Symbol"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Symbol(s) that go on the left and right side of the main text content"))
                                                .build())
                                        .binding(
                                                DefaultConfig.SrHelper.Symbol,
                                                () -> SrHelperSymbol,
                                                opt -> SrHelperSymbol = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Text Content"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("The main text of the tag"))
                                                .build())
                                        .binding(
                                                DefaultConfig.SrHelper.TextContent,
                                                () -> SrHelperTextContent,
                                                opt -> SrHelperTextContent = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())

                                .build())
                        // Helper
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Helper"))
                                .description(OptionDescription.of(Text.literal("Helper Chat Tag")))

                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Bracket Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of brackets"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Helper.BracketColor),
                                                () -> new Color(HelperBracketColor),
                                                opt -> HelperBracketColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Symbol Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of symbols"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Helper.SymbolColor),
                                                () -> new Color(HelperSymbolColor),
                                                opt -> HelperSymbolColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Text Content Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of the main tag text"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.Helper.TextColor),
                                                () -> new Color(HelperTextColor),
                                                opt -> HelperTextColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Symbol"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Symbol(s) that go on the left and right side of the main text content"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Helper.Symbol,
                                                () -> HelperSymbol,
                                                opt -> HelperSymbol = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Text Content"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("The main text of the tag"))
                                                .build())
                                        .binding(
                                                DefaultConfig.Helper.TextContent,
                                                () -> HelperTextContent,
                                                opt -> HelperTextContent = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())

                                .build())
                        // JrHelper
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("JrHelper"))
                                .description(OptionDescription.of(Text.literal("JrHelper Chat Tag")))

                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Bracket Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of brackets"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.JrHelper.BracketColor),
                                                () -> new Color(JrHelperBracketColor),
                                                opt -> JrHelperBracketColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Symbol Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of symbols"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.JrHelper.SymbolColor),
                                                () -> new Color(JrHelperSymbolColor),
                                                opt -> JrHelperSymbolColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Text Content Color"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Color of the main tag text"))
                                                .build())
                                        .binding(
                                                new Color(DefaultConfig.JrHelper.TextColor),
                                                () -> new Color(JrHelperTextColor),
                                                opt -> JrHelperTextColor = opt.getRGB()
                                        )
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Symbol"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("Symbol(s) that go on the left and right side of the main text content"))
                                                .build())
                                        .binding(
                                                DefaultConfig.JrHelper.Symbol,
                                                () -> JrHelperSymbol,
                                                opt -> JrHelperSymbol = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.createBuilder(String.class)
                                        .name(Text.literal("Text Content"))
                                        .description(OptionDescription.createBuilder()
                                                .text(Text.literal("The main text of the tag"))
                                                .build())
                                        .binding(
                                                DefaultConfig.JrHelper.TextContent,
                                                () -> JrHelperTextContent,
                                                opt -> JrHelperTextContent = opt
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())

                                .build())


                        .build())
                // MISC ------------------------------------------------------------

                .category(ConfigCategory.createBuilder()
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
                                .build())

                        .build())
                .save(this::save)
                .build();
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
