/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.GameSettings
 */
package lumien.custommainmenu.configuration;

import lumien.custommainmenu.gui.GuiCustom;
import net.minecraft.client.Minecraft;

public class GuiEntry {
    GuiCustom standard;
    GuiCustom auto;
    GuiCustom small;
    GuiCustom normal;
    GuiCustom large;

    public GuiCustom getCurrentGUI() {
        int guiScale = Minecraft.getMinecraft().gameSettings.guiScale;
        if (guiScale == 0 && this.auto != null) {
            return this.auto;
        }
        if (guiScale == 1 && this.small != null) {
            return this.small;
        }
        if (guiScale == 2 && this.normal != null) {
            return this.normal;
        }
        if (guiScale == 3 && this.large != null) {
            return this.large;
        }
        return this.standard;
    }
}
