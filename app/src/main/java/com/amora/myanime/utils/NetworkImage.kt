package com.amora.myanime.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.amora.myanime.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PaletteLoadedListener
import com.skydoves.landscapist.palette.PalettePlugin


/**
 * A wrapper around [CoilImage] setting a default [contentScale] and showing
 * an indicator when loading disney poster images.
 *
 * @see CoilImage https://github.com/skydoves/landscapist#coil
 */

@Composable
fun NetworkImage (
	url: String?,
	modifier: Modifier = Modifier,
	circularRevealEnabled: Boolean = false,
	contentScale: ContentScale = ContentScale.Crop,
	paletteLoadedListener: PaletteLoadedListener? = null
) {
	CoilImage(
		imageModel = { url },
		modifier = modifier,
		imageOptions = ImageOptions(contentScale = contentScale),
		component = rememberImageComponent {
			if (circularRevealEnabled) {
				// add plugin with 'plus' (+) sign
				+CircularRevealPlugin()
			}
			if (paletteLoadedListener != null) {
				+PalettePlugin(paletteLoadedListener = paletteLoadedListener)
			}
			+CrossfadePlugin(duration = 1000)
		},
		previewPlaceholder = R.drawable.poster,
		failure = {
			Column(
				modifier = modifier,
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Text(
					text = "Image request failed",
					style = MaterialTheme.typography.body2
				)
			}
		}
	)
}
