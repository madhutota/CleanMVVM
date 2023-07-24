package com.cleanmvvm.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.domain.model.Blog


@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    var res = viewModel.blogs.value

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
    }


    LazyColumn {


        res.data?.let { blogList ->
            items(blogList) {
                PostItem(it = it)
            }
        }


    }

}

@Composable
fun PostItem(it: Blog) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {



        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
           // CircularImage(50.0, 50.0, 25.0, it.owner.picture)
            AsyncImage(model = it.owner.picture, contentDescription = "")
        }

    }
}

@Composable
fun CircularImage(width: Double, height: Double, radius: Double, imageUrl: String) {

    Card(
        modifier = Modifier
            .height(height.dp)
            .width(width.dp), shape = RoundedCornerShape(radius.dp)
    ) {
        AsyncImage(model = imageUrl, contentDescription = "")

    }

}

