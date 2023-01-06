
package lumien.custommainmenu.lib.actions;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.GuiOldSaveLoadConfirm;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.StartupQuery;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import lumien.custommainmenu.gui.GuiCustom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSettings;

public class ActionLoadWorld implements IAction {
    String dirName;
    String saveName;

    public ActionLoadWorld(String dirName, String saveName) {
        this.dirName = dirName;
        this.saveName = saveName;
    }

    @Override
    public void perform(Object source, GuiCustom menu) {
        NBTTagCompound leveldat;
        File dir = new File(FMLClientHandler.instance().getSavesDir(), this.dirName);
        try {
            leveldat =
                    CompressedStreamTools.readCompressed((InputStream) new FileInputStream(new File(dir, "level.dat")));
        } catch (Exception e) {
            try {
                leveldat = CompressedStreamTools.readCompressed(
                        (InputStream) new FileInputStream(new File(dir, "level.dat_old")));
            } catch (Exception e1) {
                FMLLog.warning(
                        (String) "There appears to be a problem loading the save %s, both level files are unreadable.",
                        (Object[]) new Object[] {this.dirName});
                return;
            }
        }
        NBTTagCompound fmlData = leveldat.getCompoundTag("FML");
        if (fmlData.hasKey("ModItemData")) {
            FMLClientHandler.instance()
                    .showGuiScreen((Object) new GuiOldSaveLoadConfirm(this.dirName, this.saveName, (GuiScreen) menu));
        } else {
            try {
                Minecraft.getMinecraft().launchIntegratedServer(this.dirName, this.saveName, (WorldSettings) null);
            } catch (StartupQuery.AbortedException e) {
                // empty catch block
            }
        }
    }
}
