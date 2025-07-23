package com.example.e_library.presentation.UiComp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState

@Composable
fun PdfViewerScreen(
    pdfUrl: String
) {

    var isDarkMode = remember { mutableStateOf(false) }
    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote(pdfUrl),
        isZoomEnable = true)
    VerticalPDFReader(
        state = pdfState,
        modifier = Modifier.fillMaxSize().background(color = Color.Gray)
    )

    
}