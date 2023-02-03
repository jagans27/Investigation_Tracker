package com.jagan.investigationtracker.Menu


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jagan.investigationtracker.R
import com.jagan.investigationtracker.ui.theme.Darkblue100
import com.jagan.investigationtracker.ui.theme.Darkblue200
import com.jagan.investigationtracker.ui.theme.Darkblue300
import com.jagan.investigationtracker.ui.theme.Green100
import kotlinx.coroutines.selects.select


@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Darkblue200)
            .height(350.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .background(Darkblue100)
                    .height(150.dp)
                    .width(150.dp),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.logobig),
                    contentDescription = "App logo",
                    Modifier.size(100.dp)
                )

            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Investigation Tracker",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    onItemClick: (MenuItem) -> Unit,
    navController: NavController
) {
    LazyColumn(modifier = modifier) {
        items(items) { item ->
            val backStackEntry = navController.currentBackStackEntryAsState()

            val selected = item.route == backStackEntry.value?.destination?.route

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 25.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(if (selected) Green100 else Darkblue100)
            ) {
                Box(
                    modifier = Modifier
                        .clickable {
                            onItemClick(item)
                        }
                        .fillMaxSize()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(50.dp)
                    ) {
                        Spacer(modifier = Modifier.width(20.dp))
                        Icon(
                            imageVector = item.icon,
                            tint = Color.White,
                            contentDescription = item.contentDescription,
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            textAlign = TextAlign.Center,
                            text = item.title,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                            color = Color.White,
                            modifier = Modifier.fillMaxHeight(0.4f)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun AppBar(
    title :String,
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        backgroundColor = Darkblue100,
        contentColor = Color.White,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
        }
    )
}
