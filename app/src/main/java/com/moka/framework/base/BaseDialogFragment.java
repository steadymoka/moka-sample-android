package com.moka.framework.base;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BaseDialogFragment extends AppCompatDialogFragment {

	private OnDismissDialogListener onDismissDialogListener;

	/**
	 * Dialog LifeCycle
	 * onCreate -> onCreateDialog -> onCreateView -> onViewCreated -> onResume
	 */

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		setStyle(DialogFragment.STYLE_NO_TITLE, 0);
		super.onCreate(savedInstanceState);
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return super.onCreateDialog(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	/**
	 */

	private BaseActivity getBaseActivity() {
		FragmentActivity activity = getActivity();

		if ( activity instanceof BaseActivity )
			return (BaseActivity) activity;
		else
			return null;
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		super.onDismiss(dialog);

		if ( null != onDismissDialogListener )
			onDismissDialogListener.onDismiss();
	}

	public void setOnDismissDialogListener(OnDismissDialogListener onDismissDialogListener) {
		this.onDismissDialogListener = onDismissDialogListener;
	}

	public interface OnDismissDialogListener {

		void onDismiss();

	}

}
