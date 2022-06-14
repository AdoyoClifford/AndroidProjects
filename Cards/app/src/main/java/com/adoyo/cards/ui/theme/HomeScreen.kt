package com.adoyo.cards.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.adoyo.cards.Items
import com.adoyo.cards.createDataList


@Composable
fun Grids(data: Items){
    Card(modifier = Modifier
        .padding(8.dp)
        .clip(RoundedCornerShape(8.dp))
        .aspectRatio(1f), elevation = 16.dp, backgroundColor = Color.White) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)) {
            Icon(imageVector = data.icon, contentDescription = data.text)
            Spacer(modifier = Modifier.height(16.dp))
            Text(data.text)
        }
    }
}

@Composable
fun Layout(){
    val list = createDataList()
    LazyVerticalGrid(columns = GridCells.Fixed(3), content = {
        items(list.size) {index ->
            Grids(data = list[index])
        }
    })
}