package com.example.csc557.ui.theme.components.customdialog

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun customDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    properties: DialogProperties = DialogProperties()
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = properties
    ) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
//                Icon(
//                    modifier = Modifier
//                        .align(Alignment.End)
//                        .clickable(onClick = onDismiss),
//                    imageVector = Icons.Default.Close,
//                    contentDescription = "Close",
//                )
                Spacer(modifier = Modifier.padding(top = 5.dp))
                Text(
                    text = title,
                    fontSize = 25.sp,
                )
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = message,
                    fontSize = 19.sp,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.padding(top = 40.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextButton(onClick = onCancel) {
                        Text(text = "cancel")
                    }
                    TextButton(onClick = onConfirm) {
                        Text(text = "confirm")
                    }
                }
            }
        }
    }
}