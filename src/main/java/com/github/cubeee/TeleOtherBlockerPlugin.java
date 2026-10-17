package com.github.cubeee;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Teleother Blocker"
)
public class TeleOtherBlockerPlugin extends Plugin {
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Override
	protected void startUp() {
		if (client.getGameState() == GameState.LOGGED_IN) {
			clientThread.invoke(() -> hideAcceptButton(true));
		}
	}

	@Override
	protected void shutDown() {
		if (client.getGameState() == GameState.LOGGED_IN) {
			clientThread.invoke(() -> hideAcceptButton(false));
		}
	}

    @Subscribe
	@SuppressWarnings("unused")
	public void onWidgetLoaded(WidgetLoaded event) {
		if (event.getGroupId() != InterfaceID.TELEPORT_OTHER) {
			return;
		}
		hideAcceptButton(true);
	}

	private void hideAcceptButton(boolean hide) {
		Widget acceptButton = client.getWidget(InterfaceID.TeleportOther.YL);
		if (acceptButton == null) {
			return;
		}
		acceptButton.setHidden(hide);
	}
}
