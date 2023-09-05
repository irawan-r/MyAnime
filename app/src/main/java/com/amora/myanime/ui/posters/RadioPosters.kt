package com.amora.myanime.ui.posters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.amora.myanime.model.AnimeItem
import com.amora.myanime.ui.theme.MyAnimeTheme
import com.amora.myanime.utils.NetworkImage

@Composable
fun RadioAnimes(
	modifier: Modifier = Modifier,
	posters: List<AnimeItem>,
	selectPoster: (Long) -> Unit = {}
) {
	val listState = rememberLazyListState()
	Column(modifier.background(MaterialTheme.colors.background)) {
		LazyColumn(
			state = listState,
			contentPadding = PaddingValues(4.dp)
		) {
			items(
				items = posters,
				key = { it.id },
				itemContent = { poster ->
					RadioPoster(poster = poster, selectPoster = selectPoster)
				}
			)
		}
	}
}

@Composable
private fun RadioPoster(
	modifier: Modifier = Modifier,
	poster: AnimeItem,
	selectPoster: (Long) -> Unit = {}
) {
	Surface(
		modifier = modifier
			.fillMaxWidth()
			.padding(4.dp)
			.clickable {
				selectPoster(poster.id)
			},
		color = MaterialTheme.colors.background,
		elevation = 8.dp,
		shape = RoundedCornerShape(8.dp)
	) {
		ConstraintLayout(modifier = Modifier.padding(8.dp)) {
			val (image, title, content) = createRefs()

			NetworkImage(
				modifier = Modifier
					.constrainAs(image) {
						centerHorizontallyTo(parent)
						end.linkTo(title.start)
					}
					.height(64.dp)
					.aspectRatio(1f)
					.clip(RoundedCornerShape(4.dp)),
				url = poster.posterUrl?.webp?.imageUrl
			)

			Text(
				modifier = modifier
					.constrainAs(title) {
						start.linkTo(image.end)
					}
					.padding(horizontal = 12.dp),
				text = poster.title,
				textAlign = TextAlign.Start,
				style = MaterialTheme.typography.h2
			)

			Text(
				modifier = Modifier.constrainAs(content) {
					start.linkTo(image.end)
					top.linkTo(title.bottom)
				}
					.padding(start = 12.dp, top = 4.dp),
				text = poster.duration,
				style = MaterialTheme.typography.body2
			)
		}
	}
}

@Composable
@Preview(name = "RadioPoster Light")
private fun RadioPosterPreviewLight() {
	MyAnimeTheme(darkTheme = false) {
		RadioPoster(poster = AnimeItem.mock(), selectPoster = {})
	}
}

@Composable
@Preview(name = "RadioPoster Dark")
private fun RadioPosterPreviewDark() {
	MyAnimeTheme(darkTheme = true) {
		RadioPoster(poster = AnimeItem.mock(), selectPoster = {})
	}
}