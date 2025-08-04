package com.example.e_library.presentation.HomeScreen


import android.R.attr.navigationIcon
import android.R.attr.onClick
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.e_library.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController) {

    //initializing drawer with its initial state
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val uriHandler = LocalUriHandler.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp)
                        .width(250.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Divider()

                    NavigationDrawerItem(

                        label = { Text("Home") },
                        selected = true,
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        }
                    )

                    Divider()

                    NavigationDrawerItem(

                        label = { Text("Version 1.O") },
                        selected = false,
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Version"
                            )
                        },
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            Toast.makeText(context, "Version 1.O", Toast.LENGTH_SHORT).show()
                        }
                    )

                    Divider()

                    NavigationDrawerItem(

                        label = { Text("Source Code") },
                        selected = false,
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.github),
                                contentDescription = "Github",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = {
                            uriHandler.openUri("https://github.com/n-aryanshi/E-Library")
                        }
                        // Toast.makeText(context, "Version 1.O", Toast.LENGTH_SHORT).show()

                    )

                    Divider()

                    NavigationDrawerItem(

                        label = { Text("Bug Report") },
                        selected = false,
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.bugg),
                                contentDescription = "Source Code Report",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = {
                            uriHandler.openUri("https://github.com/n-aryanshi")
                        }
                        // Toast.makeText(context, "Version 1.O", Toast.LENGTH_SHORT).show()

                    )

                }
            }


        }
    ) {

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar =
                {
                    TopAppBar(
                        title = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    "Book Library",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )

                            }
                        }
                    )
                },
            navigationIcon = {
                IconButton(
                    onClick = { })
            }


        ) {

        }

    }
}