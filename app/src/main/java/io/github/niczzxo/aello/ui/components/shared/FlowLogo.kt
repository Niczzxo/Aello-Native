package io.github.niczzxo.aello.ui.components.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import io.github.niczzxo.aello.R

/** The app's logo. Size it by width. */
@Composable
fun FlowLogo(modifier: Modifier = Modifier) {
    Box(modifier.aspectRatio(1f)) {
        Image(
            painter = painterResource(R.drawable.ic_aello_logo),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
