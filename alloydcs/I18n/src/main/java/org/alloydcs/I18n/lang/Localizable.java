package org.alloydcs.I18n.lang;

import java.util.ResourceBundle;

@FunctionalInterface
public interface Localizable {

	void updateText(final ResourceBundle bundle);

}
