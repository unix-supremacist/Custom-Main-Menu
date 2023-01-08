package lumien.custommainmenu.lib.actions;

import lumien.custommainmenu.CustomMainMenu;
import lumien.custommainmenu.gui.GuiCustom;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

public class ActionRefresh implements IAction {
    @Override
    public void perform(Object source, GuiCustom menu) {
        CustomMainMenu.INSTANCE.reload();
        if (Keyboard.isKeyDown((int) 42)) {
            menu.mc.refreshResources();
        }
        menu.mc.displayGuiScreen((GuiScreen) new GuiMainMenu());
    }
}
