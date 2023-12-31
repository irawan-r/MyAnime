package com.amora.myanime.ui.posters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.amora.myanime.model.AnimeItem
import com.amora.myanime.ui.custom.StaggeredVerticalGrid
import com.amora.myanime.ui.theme.MyAnimeTheme
import com.amora.myanime.utils.NetworkImage

@Composable
fun LibraryAnimes(
	modifier: Modifier = Modifier,
	posters: List<AnimeItem>,
	selectAnime: (Long) -> Unit = {}
) {
	Column(
		modifier = modifier
			.verticalScroll(rememberScrollState())
			.background(MaterialTheme.colors.background)
	) {
		StaggeredVerticalGrid(
			maxColumnWidth = 330.dp,
			modifier = Modifier.padding(4.dp)
		) {
			posters.forEach { poster ->
				key(poster.id) {
					LibraryAnime(poster = poster, selectAnime = selectAnime)
				}
			}
		}
	}
}

@Composable
private fun LibraryAnime(
	modifier: Modifier = Modifier,
	poster: AnimeItem,
	selectAnime: (Long) -> Unit = {}
) {
	Surface(
		modifier = modifier
			.fillMaxWidth()
			.padding(4.dp)
			.clickable(
				onClick = { selectAnime(poster.id) }
			),
		color = MaterialTheme.colors.onBackground,
		elevation = 8.dp,
		shape = RoundedCornerShape(8.dp)
	) {
		ConstraintLayout(modifier = Modifier.padding(16.dp)) {
			val (image, title, content) = createRefs()
			NetworkImage(url = poster.posterUrl?.webp?.imageUrl, modifier = Modifier
				.constrainAs(image) {
					centerHorizontallyTo(parent)
					top.linkTo(parent.top)
					bottom.linkTo(title.top)
				}
				.height(112.dp)
				.aspectRatio(1.0f)
				.clip(CircleShape)
			)

			Text(text = poster.title, style = MaterialTheme.typography.h2,
				textAlign = TextAlign.Center, modifier = Modifier
					.constrainAs(title) {
						centerHorizontallyTo(parent)
						top.linkTo(image.bottom)
					}
					.padding(8.dp)
			)

			Text(
				text = poster.duration,
				style = MaterialTheme.typography.body1,
				textAlign = TextAlign.Center,
				modifier = Modifier
					.constrainAs(content) {
						centerHorizontallyTo(parent)
						top.linkTo(title.bottom)
					}
					.padding(horizontal = 8.dp)
			)
		}
	}
}

@Composable
@Preview(name = "LibraryPoster Light")
private fun LibraryPosterPreviewLight() {
	MyAnimeTheme(darkTheme = false) {
		LibraryAnime(poster = AnimeItem.mock())
	}
}

@Composable
@Preview(name = "LibraryPoster Dark")
private fun LibraryPosterPreviewDark() {
	MyAnimeTheme(darkTheme = true) {
		LibraryAnime(poster = AnimeItem.mock())
	}
}