/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.event.FMLInterModComms
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.common.MinecraftForge
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.Logger
 */
package lumien.custommainmenu;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import java.io.File;
import lumien.custommainmenu.configuration.Config;
import lumien.custommainmenu.configuration.ConfigurationLoader;
import lumien.custommainmenu.handler.CMMEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

@Mod(modid = CustomMainMenu.MOD_ID, name = CustomMainMenu.MOD_NAME, version = CustomMainMenu.VERSION)
public class CustomMainMenu {
    public static final String MOD_ID = "CustomMainMenu";
    public static final String MOD_NAME = "Custom Main Menu";
    public static final String MOD_VERSION = "GRADLETOKEN_VERSION";

    @Mod.Instance(value = "CustomMainMenu")
    public static CustomMainMenu INSTANCE;

    public static CMMEventHandler EVENT_HANDLER;
    private ConfigurationLoader configLoader;
    public Config config;
    public Logger logger;
    public File configFolder;
    static ResourceLocation transparentTexture;

    public static void bindTransparent() {
        Minecraft.getMinecraft().renderEngine.bindTexture(transparentTexture);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        this.configFolder = event.getModConfigurationDirectory();
        this.config = new Config();
        transparentTexture = new ResourceLocation("custommainmenu:textures/gui/transparent.png");
        EVENT_HANDLER = new CMMEventHandler();
        MinecraftForge.EVENT_BUS.register((Object) EVENT_HANDLER);
        FMLCommonHandler.instance().bus().register((Object) EVENT_HANDLER);
        this.logger = event.getModLog();
        this.configLoader = new ConfigurationLoader(this.config);
        try {
            this.configLoader.load();
        } catch (Exception e) {
            this.logger.log(Level.ERROR, "Error while loading config file. Will have to crash here :(.");
            throw new RuntimeException(e);
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString("curseProjectName", "226406-custom-main-menu");
        compound.setString("curseFilenameParser", "CustomMainMenu-MC1.8-[].jar");
        compound.setString("modDisplayName", MOD_NAME);
        FMLInterModComms.sendRuntimeMessage(
                (Object) MOD_ID, (String) "VersionChecker", (String) "addCurseCheck", (NBTTagCompound) compound);
    }

    public void reload() {
        Config backup = this.config;
        this.config = new Config();
        this.configLoader = new ConfigurationLoader(this.config);
        try {
            this.configLoader.load();
            CustomMainMenu.EVENT_HANDLER.displayMs = -1L;
        } catch (Exception e) {
            e.printStackTrace();
            CustomMainMenu.EVENT_HANDLER.displayMs = System.currentTimeMillis();
            this.logger.log(Level.ERROR, "Error while loading new config file, trying to keep the old one loaded.");
            this.config = backup;
        }
    }
}
