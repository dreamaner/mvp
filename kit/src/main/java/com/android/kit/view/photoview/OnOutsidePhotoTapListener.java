package com.android.kit.view.photoview;

import android.widget.ImageView;

/**
 *
 * Created by Dreamaner on 2017/5/15.
 *
 * Callback when the user tapped outside of the photo
 */
public interface OnOutsidePhotoTapListener {

    /**
     * The outside of the photo has been tapped
     */
    void onOutsidePhotoTap(ImageView imageView);
}
