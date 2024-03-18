package com.lucidplugins.lucidhotkeys.overlay;

import com.lucidplugins.lucidhotkeys.LocalRegionTile;
import com.lucidplugins.lucidhotkeys.LucidHotkeysConfig;
import com.lucidplugins.lucidhotkeys.LucidHotkeysPlugin;
import net.runelite.api.*;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.unethicalite.client.Static;

import javax.inject.Inject;
import java.awt.*;
import java.util.Map;

public class MouseOverlay extends Overlay
{

    private final Client client;
    private final LucidHotkeysConfig config;

    @Inject
    MouseOverlay(final Client client, final LucidHotkeysPlugin plugin, final LucidHotkeysConfig config)
    {
        super(plugin);
        this.client = client;
        this.config = config;

        setPosition(OverlayPosition.DYNAMIC);
        setPriority(OverlayPriority.HIGHEST);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D g)
    {
        if (!config.renderMouse())
        {
            return null;
        }

        var mousePosX = client.getMouseHandler().getCurrentX();
        var mousePosY = client.getMouseHandler().getCurrentY();

        g.setColor(Color.CYAN);
        g.drawLine(mousePosX - 5, mousePosY - 5, mousePosX + 5, mousePosY + 5);
        g.drawLine(mousePosX - 5, mousePosY + 5, mousePosX + 5, mousePosY - 5);

        return null;
    }
}