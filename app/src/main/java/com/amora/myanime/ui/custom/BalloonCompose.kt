package com.amora.myanime.ui.custom

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.skydoves.balloon.Balloon
import com.skydoves.orchestra.tooltips.BalloonAnchor

@Composable
fun ConstraintLayoutScope.ImageBalloonAnchor(
	reference: ConstrainedLayoutReference,
	modifier: Modifier,
	content: String,
	onClick:(Balloon, View) -> Unit
) {
	val context = LocalContext.current
	val lifecycleOwner = LocalLifecycleOwner.current
	val balloon = remember { BalloonFactory.create(context, content, lifecycleOwner) }

	BalloonAnchor(
		reference = reference,
		modifier = modifier,
		balloon = balloon,
		onAnchorClick = onClick
	)
}