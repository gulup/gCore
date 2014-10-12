package org.gulup.core.ext;

import org.gulup.core.GBaseAction;
import org.json.JSONObject;

import android.content.Context;

public abstract class JsonRequestAction extends GBaseAction {

	public JsonRequestAction(Context context) {
		super(context);
	}

	@Override
	public abstract void requestBefore();

	@Override
	public abstract void jsonRequestHandle(JSONObject response, int requestType);

	@Override
	public void stringRequestHandle(String response, int requestType) {
	}

}
