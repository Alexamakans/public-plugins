package com.lucidplugins.lucidhotkeys.api.util;

import net.runelite.api.widgets.Widget;
import net.unethicalite.api.items.Inventory;
import net.unethicalite.api.magic.Magic;
import net.unethicalite.api.magic.Spell;
import net.unethicalite.api.magic.SpellBook;
import net.unethicalite.api.packets.MousePackets;
import net.unethicalite.client.Static;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class SpellUtils
{
    public static boolean isSpellbookOpen()
    {
        Widget widget = Static.getClient().getWidget(218, 3);
        if (widget == null)
        {
            return false;
        }

        return !widget.isHidden();
    }

    public static boolean isInventoryOpen()
    {
        Widget widget = Static.getClient().getWidget(149, 0);
        if (widget == null)
        {
            return false;
        }

        return !widget.isHidden();
    }

    @Nullable
    static Spell getSpell(@NotNull String spellName)
    {
        for (var spell : SpellBook.Standard.values())
        {
            if (spell.name().equalsIgnoreCase(spellName.strip().replaceAll(" ", "_")))
            {
                return spell;
            }
        }

        return null;
    }

    @Nullable
    public static Widget getSpellWidget(@NotNull String spellName)
    {
        var spellNames = new String[]{
                "Relic_Home_Teleport",
                "Wind_Strike",
                "Confuse",
                "Enchant_Crossbow_Bolt",
                "Water_Strike",
                "Lvl-1_Enchant",
                "Earth_Strike",
                "Weaken",
                "Fire_Strike",
                "Bones_to_Bananas",
                "Wind_Bolt",
                "Curse",
                "Bind",
                "Low_Level_Alchemy",
                "Water_Bolt",
                "Varrock_Teleport",
                "Lvl-2_Enchant",
                "Earth_Bolt",
                "Lumbridge_Teleport",
                "Telekinetic_Grab",
                "Fire_Bolt",
                "Falador_Teleport",
                "Crumble_Undead",
                "Teleport_to_House",
                "Wind_Blast",
                "Superheat_Item",
                "Camelot_Teleport",
                "Water_Blast",
                "Lvl-3_Enchant",
                "Iban_Blast",
                "Snare",
                "Magic_Dart",
                "Ardougne_Teleport",
                "Earth_Blast",
                "High_Level_Alchemy",
                "Charge_Water_Orb",
                "Lvl-4_Enchant",
                "Watchtower_Teleport",
                "Fire_Blast",
                "Charge_Earth_Orb",
                "Bones_to_Peaches",
                "Saradomin_Strike",
                "Claws_of_Guthix",
                "Flames_of_Zamorak",
                "Trollheim_Teleport",
                "Wind_Wave",
                "Charge_Fire_Orb",
                "Ape_Atoll_Teleport",
                "Water_Wave",
                "Charge_Air_Orb",
                "Vulnerability",
                "Lvl-5_Enchant",
                "Kourend_Castle_Teleport",
                "Earth_Wave",
                "Enfeeble",
                "Teleother_Lumbridge",
                "Fire_Wave",
                "Entangle",
                "Stun",
                "Charge",
                "Wind_Surge",
                "Teleother_Falador",
                "Water_Surge",
                "Tele_Block",
                "Teleport_to_Target",
                "Lvl-6_Enchant",
                "Teleother_Camelot",
                "Earth_Surge",
                "Lvl-7_Enchant",
                "Fire_Surge"
        };

        String sanitizedName = spellName.strip().replaceAll(" ", "_");
        int nameIndex = -1;
        for (int i = 0; i < spellNames.length; i++)
        {
            if (sanitizedName.equalsIgnoreCase(spellNames[i]))
            {
                nameIndex = i;
            }
        }

        int parentId = 218;
        int childId = 6 + nameIndex;

        return Static.getClient().getWidget(parentId, childId);
    }

    public static void castSpell(@NotNull String spellName)
    {
        Widget widget = getSpellWidget(spellName);
        if (widget == null)
        {
            return;
        }

        if (widget.getActions() == null)
        {
            // Manually click by opening spellbook, no idea why the actions are null
            var curTabContainer = MiscUtil.getContainerWidgetInventoryCurrentTab();
            if (curTabContainer == null)
            {
                return;
            }
            if (!isSpellbookOpen())
            {
                InteractionUtils.widgetInteract(161, 64, "Magic");
            }
            var p = widget.getClickPoint();
            Static.getClient().getMouseHandler().sendClickMouseEvent(p.getX(), p.getY());
            var prevTab = MiscUtil.getTabWidgetFromInventoryContainerWidget(curTabContainer);
            if (prevTab == null)
            {
                return;
            }
            p = prevTab.getClickPoint();
            Static.getClient().getMouseHandler().sendClickMouseEvent(p.getX(), p.getY());
        }
        else
        {
            // Use magic super haxx to not even have to open the spellbook
            InteractionUtils.widgetInteract(widget.getParentId(), widget.getId(), "Cast");
        }
    }

    public static void castSpellOnInventoryItem(String spellName, String itemName)
    {
        var spell = getSpell(spellName);
        if (spell == null)
        {
            return;
        }
        var targetItem = Inventory.getFirst(item -> item.getName().toLowerCase().contains(itemName.toLowerCase()));
        if (targetItem == null)
        {
            return;
        }
        MousePackets.queueClickPacket();
        Magic.cast(spell, targetItem);
    }
}
