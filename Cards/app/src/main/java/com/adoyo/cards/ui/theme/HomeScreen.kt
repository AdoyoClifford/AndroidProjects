package com.adoyo.cards.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
            Icon(
                painter = painterResource(id = data.image),
                contentDescription = data.text,
                tint = Color(0xffff66cc),
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = data.text, fontWeight = FontWeight.W400)
            }
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
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview1(){
    Layout()
}