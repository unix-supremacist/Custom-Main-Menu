/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiMainMenu
 */
package lumien.custommainmenu.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;

public class GuiFakeMain extends GuiMainMenu {
    public GuiFakeMain() {
        this.mc = Minecraft.getMinecraft();
    }

    public void initGui() {}

    public List<GuiButton> getButtonList() {
        return this.buttonList;
    }
}
