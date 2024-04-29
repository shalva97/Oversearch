package com.example.oversearch.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.oversearch.R
import com.example.oversearch.domain.models.Player
import com.example.oversearch.domain.models.mock_data.player1
import com.example.oversearch.presentation.theme.OversearchTheme

@Composable
fun PlayerItem(player: Player) {
    ElevatedCard(
        modifier = Modifier
            .padding(16.dp)
            .height(60.dp)
    ) {
        Row {
            Image(
                modifier = Modifier.aspectRatio(1f),
                painter = painterResource(id = R.drawable.img_sample_player),
                contentDescription = null
            )
            Box {
                Image(
                    painter = painterResource(id = R.drawable.img_sample_player_background),
                    contentDescription = null
                )
                val brush = Brush.horizontalGradient(listOf(Color.White, Color.Transparent))
                Canvas(
                    modifier = Modifier.size(300.dp),
                    onDraw = {
                        drawRect(brush)
                    }
                )
                Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                    Text(
                        text = player.username,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )
                    Text(
                        text = player.title, modifier = Modifier
                            .padding(start = 16.dp)
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OversearchTheme {
        PlayerItem(player1)
    }
}
