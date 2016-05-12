package org.alloydcs.I18n.lang;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectPropertyBase;

public final class LanguageBundle extends ResourceBundle {

	private final String				baseName;
	private final CurrentBundleProperty	currentBundle;

	public LanguageBundle(final String baseName,
			final ReadOnlyObjectProperty<Locale> locale) {
		this.baseName		= baseName;
		this.currentBundle	= new CurrentBundleProperty();
		locale.addListener((obs, oldValue, newValue) -> this.update(newValue));
	}

	private void update(final Locale newLocale) {
		this.currentBundle.set(ResourceBundle.getBundle(this.baseName, newLocale));
	}
	public void register(final Localizable localizable) {
		this.currentBundleProperty().addListener(
				obs -> localizable.updateText(this));
	}

	@Override
	protected Object handleGetObject(final String key) {
		return this.currentBundleProperty().get().getObject(key);
	}
	@Override
	public Enumeration<String> getKeys() {
		return this.currentBundleProperty().get().getKeys();
	}

	public final ReadOnlyObjectProperty<ResourceBundle> currentBundleProperty() {
		return this.currentBundle;
	}
	public final ResourceBundle getCurrentBundle() {
		return this.currentBundleProperty().get();
	}

	private class CurrentBundleProperty
			extends ReadOnlyObjectPropertyBase<ResourceBundle> {
		private ResourceBundle bundle;
		@Override
		public Object getBean() {
			return LanguageBundle.this;
		}
		@Override
		public String getName() {
			return "currentBundle";
		}
		private void set(final ResourceBundle newValue) {
			this.bundle = newValue;
			fireValueChangedEvent();
		}
		@Override
		public ResourceBundle get() {
			return bundle;
		}
	}

}
