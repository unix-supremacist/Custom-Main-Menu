/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  com.google.common.io.ByteStreams
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  com.google.gson.stream.JsonReader
 *  org.apache.commons.io.IOUtils
 */
package lumien.custommainmenu.configuration;

import com.google.common.io.ByteStreams;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import lumien.custommainmenu.CustomMainMenu;
import lumien.custommainmenu.gui.GuiCustom;
import org.apache.commons.io.IOUtils;

public class ConfigurationLoader {
    Config config;

    public ConfigurationLoader(Config config) {
        this.config = config;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public void load() throws Exception {
        JsonReader reader;
        File[] jsonFiles;
        JsonObject jsonObject;
        File mainmenuConfig;
        String name;
        JsonElement jsonElement;
        GuiConfig guiConfig;
        JsonParser jsonParser = new JsonParser();
        File configFolder = new File(CustomMainMenu.INSTANCE.configFolder, "CustomMainMenu");
        if (!configFolder.exists()) {
            configFolder.mkdir();
        }
        if (!(mainmenuConfig = new File(configFolder, "mainmenu.json")).exists()) {
            InputStream input = null;
            FileOutputStream output = null;
            try {
                output = new FileOutputStream(mainmenuConfig);
                input = this.getClass().getResourceAsStream("/assets/custommainmenu/mainmenu_default.json");
                ByteStreams.copy((InputStream) input, (OutputStream) output);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                IOUtils.closeQuietly((OutputStream) output);
                IOUtils.closeQuietly(input);
            } catch (IOException e) {
                e.printStackTrace();
                IOUtils.closeQuietly((OutputStream) output);
                IOUtils.closeQuietly(input);
            }
            IOUtils.closeQuietly((OutputStream) output);
            IOUtils.closeQuietly((InputStream) input);
        }
        for (File guiFile : jsonFiles = configFolder.listFiles()) {
            if (!guiFile.getName().equals("mainmenu.json")) continue;
            guiConfig = new GuiConfig();
            name = guiFile.getName().replace(".json", "");
            reader = null;
            try {
                reader = new JsonReader((Reader) new FileReader(guiFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                jsonElement = jsonParser.parse(reader);
                jsonObject = jsonElement.getAsJsonObject();
                guiConfig.load(name, jsonObject);
            } catch (Exception e) {
                try {
                    reader.close();
                    throw e;
                } catch (IOException io) {
                    io.printStackTrace();
                }
                throw e;
            }
            try {
                reader.close();
            } catch (IOException io) {
                io.printStackTrace();
            }
            this.config.addGui(guiConfig.name, new GuiCustom(guiConfig));
        }
        File[] arr$ = jsonFiles;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            File guiFile;
            guiFile = arr$[i$];
            if (!guiFile.getName().equals("mainmenu.json") && guiFile.getName().endsWith(".json")) {
                guiConfig = new GuiConfig();
                name = guiFile.getName().replace(".json", "");
                reader = null;
                try {
                    reader = new JsonReader((Reader) new FileReader(guiFile));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    jsonElement = jsonParser.parse(reader);
                    jsonObject = jsonElement.getAsJsonObject();
                    guiConfig.load(name, jsonObject);
                } catch (Exception e) {
                    try {
                        reader.close();
                        throw e;
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                    throw e;
                }
                try {
                    reader.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
                this.config.addGui(guiConfig.name, new GuiCustom(guiConfig));
            }
            ++i$;
        }
    }
}
