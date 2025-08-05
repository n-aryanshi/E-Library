package com.example.e_library.presentation.UiComp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.SubcomposeAsyncImage
import com.example.e_library.presentation.effects.imageAni
import com.example.e_library.presentation.navigation.Routes

@Composable
fun BookCard(
    name: String,
    description: String,
    author: String,
    category: String,
    imageUrl: String,
    pdfUrl: String,
    navHostController: NavHostController

) {
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .fillMaxWidth()
                .clickable {
                    navHostController.navigate(Routes.ShowPdfScreen(url = pdfUrl))
                },
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                //image
                SubcomposeAsyncImage(
                    model = imageUrl,
                    contentDescription = "Book Image",
                    loading = {
                        imageAni()
                    },
                    error = {
                        Text("Image failed to load", fontSize = 12.sp)

                    },

                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                //space
                Spacer(modifier = Modifier.width(10.dp))

                //column
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                ) {

                    //title name
                    Text(
                        text = name,
                        fontSize = 16.sp,
                        color = Color(0xFF212121),
                        fontWeight = FontWeight.SemiBold,
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    //description
                    Text(
                        text = description,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis, //shows ... at end
                        modifier = Modifier.padding(4.dp)

                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    //author name
                    Text(
                        text = "-${author}",
                        fontSize = 12.sp,
                        color = Color(0xFF424242),
                        //italic text
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }

}





