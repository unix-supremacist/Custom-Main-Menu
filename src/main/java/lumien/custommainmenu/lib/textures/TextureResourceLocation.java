/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.util.ResourceLocation
 */
package lumien.custommainmenu.lib.textures;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class TextureResourceLocation extends ResourceLocation implements ITexture {
    public TextureResourceLocation(String resourceString) {
        super(resourceString);
    }

    @Override
    public void bind() {
        Minecraft.getMinecraft().renderEngine.bindTexture((ResourceLocation) this);
    }
}
