/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.Session
 *  net.minecraftforge.common.ForgeVersion
 */
package lumien.custommainmenu.lib;

import cpw.mods.fml.common.Loader;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.ForgeVersion;

public class StringReplacer {
    static final String TIME_FORMAT = "HH:mm";
    static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    static Field mcpversionField;
    public static String mcpversion;

    public static String replacePlaceholders(String source) {
        int tModCount = Loader.instance().getModList().size();
        int aModCount = Loader.instance().getActiveModList().size();
        Calendar calendar = Calendar.getInstance();
        String clock = timeFormat.format(calendar.getTime());
        DateFormat formatter = DateFormat.getDateInstance(2, Locale.getDefault());
        String date = formatter.format(new Date());
        Loader.instance();
        return source.replace("#date#", date)
                .replace("#time#", clock)
                .replace("#mcversion#", "1.7.10")
                .replace("#fmlversion#", Loader.instance().getFMLVersionString())
                .replace("#mcpversion#", mcpversion)
                .replace("#modsloaded#", tModCount + "")
                .replace("#modsactive#", aModCount + "")
                .replace("#forgeversion#", ForgeVersion.getVersion())
                .replace("#username#", Minecraft.getMinecraft().getSession().getUsername());
    }

    static {
        try {
            mcpversionField = Loader.class.getDeclaredField("mcpversion");
            mcpversionField.setAccessible(true);
            mcpversion = (String) mcpversionField.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
