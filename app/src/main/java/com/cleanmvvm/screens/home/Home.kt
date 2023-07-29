package com.cleanmvvm.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.domain.model.Blog


@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {/* var res = viewModel.blogs.value

     if (res.isLoading) {
         Box(modifier = Modifier.fillMaxSize()) {
             CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
         }
     }
     if (res.error.isNotBlank()) {
         Box(modifier = Modifier.fillMaxSize()) {
             Text(
                 text = res.error.toString(),
                 style = MaterialTheme.typography.titleMedium,
                 color = MaterialTheme.colorScheme.error,
                 modifier = Modifier.align(Alignment.Center)
             )
         }
     }*/

    val list = viewModel.pager.collectAsLazyPagingItems()

    LazyColumn {
        items(list.itemCount) {
            PostItem(it = list[it]!!)
        }

    }

}

@Composable
fun PostItem(it: Blog) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // CircularImage(50.0, 50.0, 25.0, it.owner.picture)
            AsyncImage(
                model = it.owner.picture,
                contentDescription = "",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "${it.owner.firstName} ${it.owner.lastName}")
        }
        Spacer(modifier = Modifier.size(8.dp))
        AsyncImage(
            model = it.image,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = it.text,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))

    }
}

@Composable
fun CircularImage(width: Double, height: Double, radius: Double, imageUrl: String) {

    Card(
        modifier = Modifier
            .height(height.dp)
            .width(width.dp), shape = RoundedCornerShape(radius.dp)
    ) {
        AsyncImage(model = imageUrl, contentDescription = "", modifier = Modifier.clip(CircleShape))

    }

}

