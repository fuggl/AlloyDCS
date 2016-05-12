package org.alloydcs.I18n.locale;

import java.util.Locale;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class LocaleDomain {

	private final ObjectProperty<Locale>	currentLocale;
	private final ObservableList<Locale>	availableLocales;

	public LocaleDomain() {
		this.currentLocale		= new SimpleObjectProperty<Locale>(
				this, "locale", Locale.getDefault());
		this.availableLocales	= FXCollections.observableArrayList();
		this.updateAvailableLocales();
	}

	public final ObjectProperty<Locale> currentLocaleProperty() {
		return this.currentLocale;
	}
	public final Locale getCurrentLocale() {
		return this.currentLocaleProperty().get();
	}
	public void setCurrentLocale(final Locale newValue) {
		this.currentLocaleProperty().set(newValue);
	}

	public void updateAvailableLocales() {

	}

}
