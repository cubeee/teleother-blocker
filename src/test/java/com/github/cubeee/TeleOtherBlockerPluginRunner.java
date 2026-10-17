package com.github.cubeee;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TeleOtherBlockerPluginRunner
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TeleOtherBlockerPlugin.class);
		RuneLite.main(args);
	}
}
