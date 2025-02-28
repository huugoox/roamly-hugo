package com.example.navigation.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navigation.R

@Composable
fun AboutScreen(navController: NavController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logoroamly2), // Usa tu logo aquí
            contentDescription = "Roamly Logo",
            modifier = Modifier.size(120.dp)
        )

        Text(
            text = "Roamly",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Versión 1.0.0",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )

        Text(
            text = "Roamly es una app diseñada para mejorar tus viajes. Conectamos experiencias y exploraciones en una sola plataforma.",
            fontSize = 14.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/pol-marsol/"))
                context.startActivity(intent)
            },
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Visita nuestro LinkedIn")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.allphysics.me"))
                context.startActivity(intent)
            },
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Visita nuestra web")
        }


        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:roamly@contact.com")
                    putExtra(Intent.EXTRA_SUBJECT, "Consulta sobre Roamly")
                }
                context.startActivity(intent)
            },
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Contact us")
        }
    }
}
