package com.example.e_library.presentation.effects

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun AnimateShimmer( ) {
    //Shimmer = Brush.linearGradient + animation

    // Step 1: Define shimmer gradient colors with transparency
    // These colors will form the moving gradient light effect
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f ),
        Color.LightGray.copy(alpha = 0.2f ),
        Color.LightGray.copy(alpha = 0.6f )
    )

    //Step 2: Create an infinite animation transition
    // This handles automatic repeated animation
    val transition = rememberInfiniteTransition()

    //Step 3: Animate a float value from 0f to 100f in a loop
    // This value will control the movement of the gradient
    val translateAnimate = transition.animateFloat(
       initialValue  = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000, // One full shimmer cycle takes 1 second
                easing = FastOutSlowInEasing // Smooth start and end

            )
        ), label = ""
    )

    //Step 4: Create a dynamic Brush.linearGradient
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(10f, 10f),
        end = Offset(translateAnimate.value, translateAnimate.value)
    )

    ShimmerGridItem(brush = brush)
    
}

@Composable
fun ShimmerGridItem(brush: Brush) {


    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                // navHostController.navigate
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(brush)
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Spacer(modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(brush)
                .fillMaxWidth(0.5f)
                .height(16.dp)

            )

        }

    }
}

@Composable
fun imageAni(){
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f ),
        Color.LightGray.copy(alpha = 0.2f ),
        Color.LightGray.copy(alpha = 0.6f )
    )

    //Step 2: Create an infinite animation transition
    // This handles automatic repeated animation
    val transition = rememberInfiniteTransition()

    //Step 3: Animate a float value from 0f to 100f in a loop
    // This value will control the movement of the gradient
    val translateAnimate = transition.animateFloat(
        initialValue  = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000, // One full shimmer cycle takes 1 second
                easing = FastOutSlowInEasing // Smooth start and end

            )
        ), label = ""
    )

    //Step 4: Create a dynamic Brush.linearGradient
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(10f, 10f),
        end = Offset(translateAnimate.value, translateAnimate.value)
    )

    Spacer(modifier = Modifier.size(80.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(brush)
    )
}