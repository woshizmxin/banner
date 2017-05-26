/*
 *     Copyright (c) 2016 Meituan Inc.
 *
 *     The right to copy, distribute, modify, or otherwise make use
 *     of this software may be licensed only pursuant to the terms
 *     of an applicable Meituan license agreement.
 *
 */

package com.marsthink.banner.transformer;

import android.view.View;

public class ZoomInTransformer extends ABaseTransformer {

	@Override
	protected void onTransform(View view, float position) {
		final float scale = position < 0 ? position + 1f : Math.abs(1f - position);
		view.setScaleX(scale);
		view.setScaleY(scale);
		view.setPivotX(view.getWidth() * 0.5f);
		view.setPivotY(view.getHeight() * 0.5f);
		view.setAlpha(position < -1f || position > 1f ? 0f : 1f - (scale - 1f));
	}

}
