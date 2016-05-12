package org.alloydcs.I18n.lang;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
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
		this.currentBundle.set(this.getBundle(newLocale));
	}
	private ResourceBundle getBundle(final Locale locale) {
		return ResourceBundle.getBundle(this.baseName, locale);
	}

	public void register(final Localizable localizable) {
		this.addListener(obs -> localizable.updateText(this));
	}
	private void addListener(final InvalidationListener listener) {
		this.currentBundleProperty().addListener(listener);
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
