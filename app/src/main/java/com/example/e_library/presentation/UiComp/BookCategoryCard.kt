package com.example.e_library.presentation.UiComp

import android.R.attr.category
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.SubcomposeAsyncImage
import com.example.e_library.presentation.navigation.Routes


@Composable
fun BookCategoryCard(
    cImageUrl: String,
    cName: String,
    navHostController: NavHostController
) {
    Card(
        modifier = Modifier.padding(4.dp)
            .clickable {
                navHostController.navigate(Routes.BooksByCategory(cName))
            },

        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))

    ) {

        Column(
            modifier = Modifier.size(120.dp)
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.spacedBy(6.dp)

        ) {
            //image
            SubcomposeAsyncImage(
                model = cImageUrl,
                contentDescription = "Category Image",
                loading = {
                    CircularProgressIndicator()
                },
                error = {
                    Text("Image failed to load", fontSize = 12.sp)

                },

                //contentScale = ContentScale.Crop,
                modifier = Modifier.padding(10.dp)
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))

            )

            //spacer
            Spacer(modifier = Modifier.width(4.dp))
            //text
            Text(
                text = cName,
                fontSize = 12.sp,
                color = Color(0xFF212121),
                fontWeight = FontWeight.SemiBold,
            )


        }

    }

}

@Preview(showBackground = true)
@Composable
fun BookCategoryScreenPreview() {
    BookCategoryCard(
        cImageUrl = "https://example.com/sample-image.jpg",  // Replace with real URL or leave
        cName = "Science Fiction",
        navHostController = rememberNavController()
    )
}