package com.lucidplugins.lucidhotkeys.api.util;

import net.runelite.api.Skill;
import net.unethicalite.client.Static;
import org.jetbrains.annotations.Nullable;

public class SkillUtils
{
    @Nullable
    public static Skill getSkill(String skillName)
    {
        for (Skill skill : Skill.values())
        {
            if (skill.getName().equalsIgnoreCase(skillName))
            {
                return skill;
            }
        }
        return null;
    }

    public static int getBoostedSkillLevel(String skillName)
    {
        return Static.getClient().getBoostedSkillLevel(getSkill(skillName));
    }

    public static int getRealSkillLevel(String skillName)
    {
        return Static.getClient().getRealSkillLevel(getSkill(skillName));
    }
}
