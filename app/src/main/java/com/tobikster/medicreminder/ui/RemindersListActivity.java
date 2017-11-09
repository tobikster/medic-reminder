package com.tobikster.medicreminder.ui;

import android.app.Activity;
import android.os.Bundle;

import com.tobikster.medicreminder.R;

import dagger.Binds;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerAppCompatActivity;
import dagger.multibindings.IntoMap;

public class RemindersListActivity extends DaggerAppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RemindersListFragment remindersListFragment = RemindersListFragment.newInstance();
		getSupportFragmentManager().beginTransaction().replace(R.id.content, remindersListFragment).commit();
	}

	@dagger.Subcomponent
	public interface Component extends AndroidInjector<RemindersListActivity> {
		@dagger.Subcomponent.Builder
		abstract class Builder extends AndroidInjector.Builder<RemindersListActivity> {}
	}

	@dagger.Module(subcomponents = Component.class)
	public interface Module {
		@Binds
		@IntoMap
		@ActivityKey(RemindersListActivity.class)
		AndroidInjector.Factory<? extends Activity> bind(Component.Builder builder);
	}
}
