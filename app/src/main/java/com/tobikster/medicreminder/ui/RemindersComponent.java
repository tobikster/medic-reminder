package com.tobikster.medicreminder.ui;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = RemindersModule.class)
public interface RemindersComponent extends AndroidInjector<MainActivity> {
	@Subcomponent.Builder
	abstract class Builder extends AndroidInjector.Builder<MainActivity>{}
}