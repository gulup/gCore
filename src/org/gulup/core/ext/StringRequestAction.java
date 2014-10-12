package org.gulup.core.ext;

import org.gulup.core.GBaseAction;
import org.json.JSONObject;

import android.content.Context;

public abstract class StringRequestAction extends GBaseAction {

	public StringRequestAction(Context context) {
		super(context);
	}

	@Override
	public abstract void requestBefore();

	@Override
	public void jsonRequestHandle(JSONObject response, int requestType) {
	}

	@Override
	public abstract void stringRequestHandle(String response, int requestType);
}
