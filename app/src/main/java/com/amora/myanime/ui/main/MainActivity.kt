package com.amora.myanime.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowCompat
import com.amora.myanime.ui.root.RootViewModel
import com.amora.myanime.ui.theme.MyAnimeTheme
import com.skydoves.landscapist.coil.LocalCoilImageLoader
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.annotations.VisibleForTesting

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	@delegate:VisibleForTesting
	internal val viewModel: RootViewModel by viewModels()


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		WindowCompat.setDecorFitsSystemWindows(window, false)
		setContent {
			MyAnimeTheme {
				CompositionLocalProvider( LocalCoilImageLoader provides viewModel.imageLoader) {
					MyAnimeTheme {
						MyAnimeMainScreen()
					}
				}
			}
		}
	}
}