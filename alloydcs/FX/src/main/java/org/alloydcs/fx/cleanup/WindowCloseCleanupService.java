package org.alloydcs.fx.cleanup;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public final class WindowCloseCleanupService extends CleanupService {

	public WindowCloseCleanupService(final Node initializedNode) {
		initializedNode.sceneProperty().addListener(
				WindowCloseCleanupService.createSceneChangeListener(
						WindowCloseCleanupService.createWindowChangeListener(
								WindowCloseCleanupService.createCleanupHandler(
										this))));
	}

	private static EventHandler<WindowEvent> createCleanupHandler(
			final CleanupService cleanupService) {
		return event -> cleanupService.cleanup();
	}
	private static ChangeListener<Window> createWindowChangeListener(
			final EventHandler<WindowEvent> eventHandler) {
		return (observable, oldValue, newValue) -> {
			newValue.addEventHandler(WindowEvent.WINDOW_HIDDEN, eventHandler);
		};
	}
	private static ChangeListener<Scene> createSceneChangeListener(
			final ChangeListener<Window> windowChangeListener) {
		return (observable, oldValue, newValue) -> {
			newValue.windowProperty().addListener(windowChangeListener);
		};
	}

}
