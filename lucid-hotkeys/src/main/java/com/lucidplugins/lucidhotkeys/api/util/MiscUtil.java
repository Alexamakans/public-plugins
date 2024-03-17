package com.lucidplugins.lucidhotkeys.api.util;

import net.runelite.api.widgets.Widget;
import net.unethicalite.client.Static;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MiscUtil
{
    public static boolean compareIntegersUsingStringOperator(int left, @NotNull String operator, int right) throws IllegalArgumentException
    {
        switch (operator)
        {
            case "<":
                return left < right;
            case ">":
                return left > right;
            case "==":
                return left == right;
            case "<=":
                return left <= right;
            case ">=":
                return left >= right;
            case "!=":
                return left != right;
        }

        throw new IllegalArgumentException();
    }

    @Nullable
    public static Widget getContainerWidgetInventoryCurrentTab()
    {
        var combatIds = new int[]{
                593,
                0
        };
        var skillIds = new int[]{
                320,
                0
        };
        var questIds = new int[]{
                629,
                0
        };
        var inventoryIds = new int[]{
                149,
                0
        };
        var equipmentIds = new int[]{
                387,
                0
        };
        var prayerIds = new int[]{
                541,
                0
        };
        var spellbookIds = new int[]{
                218,
                1
        };
        var clanIds = new int[]{
                707,
                0
        };
        var friendsIds = new int[]{
                429,
                0
        };
        var gameNoticeboardIds = new int[]{
                161,
                83
        };
        var logoutIds = new int[]{
                182,
                0
        };
        var settingsIds = new int[]{
                116,
                0
        };
        var emotesIds = new int[]{
                216,
                0
        };
        var musicIds = new int[]{
                239,
                0
        };

        var allIds = new int[][]{
                combatIds,
                skillIds,
                questIds,
                inventoryIds,
                equipmentIds,
                prayerIds,
                spellbookIds,
                clanIds,
                friendsIds,
                gameNoticeboardIds,
                logoutIds,
                settingsIds,
                emotesIds,
                musicIds
        };

        var client = Static.getClient();
        for (var idSet : allIds)
        {
            if (!InteractionUtils.isWidgetHidden(idSet[0], idSet[1]))
            {
                return client.getWidget(idSet[0], idSet[1]);
            }
        }

        return null;
    }

    @Nullable
    public static Widget getTabWidgetFromInventoryContainerWidget(@NotNull Widget widget)
    {
        var containerIds = new int[]{
                widget.getParentId(),
                widget.getId()
        };

        var combatIds = new int[]{
                161,
                58
        };
        var skillIds = new int[]{
                161,
                59
        };
        var questIds = new int[]{
                161,
                60
        };
        var inventoryIds = new int[]{
                161,
                61
        };
        var equipmentIds = new int[]{
                161,
                62
        };
        var prayerIds = new int[]{
                161,
                63
        };
        var spellbookIds = new int[]{
                161,
                64
        };
        var clanIds = new int[]{
                161,
                42
        };
        var friendsIds = new int[]{
                161,
                44
        };
        var gameNoticeboardIds = new int[]{
                161,
                43
        };
        var logoutIds = new int[]{
                161,
                45
        };
        var settingsIds = new int[]{
                161,
                46
        };
        var emotesIds = new int[]{
                161,
                47
        };
        var musicIds = new int[]{
                161,
                48
        };

        var allIds = new int[][]{
                combatIds,
                skillIds,
                questIds,
                inventoryIds,
                equipmentIds,
                prayerIds,
                spellbookIds,
                clanIds,
                friendsIds,
                gameNoticeboardIds,
                logoutIds,
                settingsIds,
                combatIds,
                emotesIds,
                musicIds
        };

        var client = Static.getClient();
        for (var idSet : allIds)
        {
            if (idSet[0] == containerIds[0] && idSet[1] == containerIds[1])
            {
                if (!InteractionUtils.isWidgetHidden(idSet[0], idSet[1]))
                {
                    return client.getWidget(idSet[0], idSet[1]);
                }
            }
        }

        return null;
    }
}
