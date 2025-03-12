package com.example.iptesttask.main_screen.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
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
fun EditDialog(
    isActive: MutableState<Boolean>,
    item: ListItem,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val buttonColors = ButtonDefaults.buttonColors(contentColor = Color.DarkGray, containerColor = Color.Transparent)
    val itemAmount = remember { mutableStateOf(item.amount) }

    AlertDialog(
        onDismissRequest = { isActive.value = false },
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ){
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = stringResource(R.string.editDialog_icon_settings),
                    tint = iconWarning
                )
                Text(text = stringResource(R.string.editDialog_title)) }
                },
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (itemAmount.value > 0) {
                            itemAmount.value--
                        }
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.outline_do_not_disturb_on_24),
                        contentDescription = stringResource(R.string.editDialog_icon_minus),
                        tint = Purple40,
                        modifier = Modifier.size(40.dp)
                    )
                }
                    Text(
                    text = "${itemAmount.value}",
                    fontSize = 22.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                )
                IconButton(
                    onClick = { itemAmount.value++ }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.twotone_add_circle_outline_24),
                        contentDescription = stringResource(R.string.editDialog_icon_plus),
                        tint = Purple40,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        },
        confirmButton = {
            TextButton (
                onClick = {
                    item.amount = itemAmount.value
                    mainViewModel.updateItem(item)
                    isActive.value = false
                },
                colors = buttonColors
            )
            {
                Text(
                    text = stringResource(R.string.editDialog_button_confirm),
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
                    text = stringResource(R.string.editDialog_button_dismiss),
                    fontSize = 14.sp,
                    color = Purple40
                )
            }
        },
        containerColor = alertDialogBackground
    )
}