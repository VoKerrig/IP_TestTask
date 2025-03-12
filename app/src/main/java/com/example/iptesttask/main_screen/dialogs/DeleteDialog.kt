package com.example.iptesttask.main_screen.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.iptesttask.R
import com.example.iptesttask.main_screen.ListItem
import com.example.iptesttask.roomdb.MainViewModel
import com.example.iptesttask.ui.theme.Purple40
import com.example.iptesttask.ui.theme.alertDialogBackground
import com.example.iptesttask.ui.theme.iconWarning

@Composable
fun DeleteDialog(
    isActive: MutableState<Boolean>,
    item: ListItem,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val buttonColors = ButtonDefaults.buttonColors(contentColor = Color.DarkGray, containerColor = Color.Transparent)
    AlertDialog(
                onDismissRequest = { isActive.value = false },
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ){
                Icon(
                    imageVector = Icons.Rounded.Warning,
                    contentDescription = stringResource(R.string.deleteDialog_icon_warning),
                    tint = iconWarning
                )
                Text(text = stringResource(R.string.deleteDialog_title)) }
                },
        text = { Text(text = stringResource(R.string.deleteDialog_text)) },
        confirmButton = {
            TextButton (
                onClick = {
                    isActive.value = false
                    mainViewModel.deleteItem(item)
                },
                colors = buttonColors
            )
            {
                Text(
                    text = stringResource(R.string.deleteDialog_button_confirm),
                    fontSize = 14.sp,
                    color = Purple40
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = { isActive.value = false },
                colors = buttonColors
            ) {
                Text(
                    text = stringResource(R.string.deleteDialog_button_dismiss),
                    fontSize = 14.sp,
                    color = Purple40
                )
            }
        },
        containerColor = alertDialogBackground
    )
}
