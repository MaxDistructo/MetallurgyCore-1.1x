package com.teammetallurgy.metallurgycore.guiwidgets;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import com.teammetallurgy.metallurgycore.machines.GUIMetallurgy;
import com.teammetallurgy.metallurgycore.machines.GUIMetallurgyMachine;

public class FluidWidget
{
    public static void bindTexture(final ResourceLocation texture)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
    }

    private final FluidTank tank;

    private final int x, y, u, v, w, h;

    public FluidWidget(final FluidTank tank, final int x, final int y, final int u, final int v, final int w, final int h)
    {
        this.tank = tank;
        this.x = x;
        this.y = y;
        this.u = u;
        this.v = v;
        this.w = w;
        this.h = h;
    }

    public void drawLiquid(final GUIMetallurgy gui, final int guiX, final int guiY, final ResourceLocation texture)
    {
        if (this.tank == null) { return; }
        final FluidStack fluidStack = this.tank.getFluid();
        if (fluidStack == null || fluidStack.amount <= 0 || fluidStack.getFluid() == null) { return; }

        final IIcon liquidIcon = FluidRender.getFluidTexture(fluidStack, false);

        if (liquidIcon == null) { return; }

        double scale = (double)fluidStack.amount / this.tank.getCapacity();

        GUIMetallurgyMachine.bindTexture(FluidRender.getFluidSheet(fluidStack));

        for (int col = 0; col < this.w / 16; col++)
        {
            for (int row = 0; row <= this.h / 16; row++)
            {
                gui.drawTexturedModelRectFromIcon(guiX + this.x + col * 16, guiY + this.y + row * 16, liquidIcon, 16, 16);
            }
        }

        GUIMetallurgyMachine.bindTexture(texture);

        gui.drawTexturedModalRect(guiX + this.x, guiY + this.y - 1, this.x, this.y - 1, this.w, this.h - (int) Math.floor(this.h * scale));
        gui.drawTexturedModalRect(guiX + this.x, guiY + this.y + this.h - 1, 8, this.y + this.h -1, this.w, 20);

    }

}
