package com.moka.mokatoyapp;


import com.moka.mokatoyapp.model.domain.Observe;
import com.moka.mokatoyapp.model.domain.User;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmQuery;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;


@Deprecated()
@RunWith( RobolectricGradleTestRunner.class )
@Config( constants = BuildConfig.class, sdk = 21 )
@PowerMockIgnore( { "org.mockito.*", "org.robolectric.*", "android.*" } )
@PrepareForTest( { Realm.class } )
public class ModelTestJava { // todo : realm 의 mocking 이 잘되지 않는다 .....

	Realm mockRealm;

	@Rule
	public PowerMockRule rule = new PowerMockRule();

	@Before
	public void setup() {
		mockRealm = mockRealm();
	}

	@Test
	public void realmShouldBeMocked() {
		final TestSubscriber<Observe.Data<User>> subscriber = TestSubscriber.create();

		User.Companion.getOb().setOnChangeObservable(new Function1<Observable<Observe.Data<User>>, Unit>() {

			@Override
			public Unit invoke(Observable<Observe.Data<User>> dataObservable) {
				dataObservable.subscribe(subscriber);
				return null;
			}
		});

		User.Companion.insert(mockRealm, new Function1<User, Unit>() {

			@Override
			public Unit invoke(User user) {
				user.setName("test_name");
				user.setEmail("test_email");
				return null;
			}
		});

		// assertion
		subscriber.awaitValueCount(1, 500, TimeUnit.MILLISECONDS);
		subscriber.assertValueCount(1);
		Observe.Data<User> result = subscriber.getOnNextEvents().get(0);

//		assertThat(result).isNotNull();
//		assertThat(result.isCreated()).isTrue();
//		assertThat(result.getData().getName()).isEqualTo("test_name");
//		assertThat(result.getData().getEmail()).isEqualTo("test_email");
	}

	/**
	 */

	public static Realm mockRealm() {
		mockStatic(Realm.class);

		Realm mockRealm = PowerMockito.mock(Realm.class);

		when(mockRealm.createObject(User.class)).thenReturn(new User());
		when(mockRealm.where(User.class)).thenReturn(RealmQuery.createQuery(mockRealm, User.class));
		when(mockRealm.where(User.class).max("id")).thenReturn(null);
		when(Realm.getDefaultInstance()).thenReturn(mockRealm);

		return mockRealm;
	}

}
