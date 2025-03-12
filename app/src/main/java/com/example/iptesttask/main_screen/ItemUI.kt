package com.example.iptesttask.main_screen

import android.text.format.DateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iptesttask.R
import com.example.iptesttask.main_screen.dialogs.DeleteDialog
import com.example.iptesttask.main_screen.dialogs.EditDialog
import com.example.iptesttask.ui.theme.buttonDelete
import com.example.iptesttask.ui.theme.buttonEdit
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ItemUI(
    item: ListItem
) {
    val isActiveDelDialog = remember { mutableStateOf(false) }
    val isActiveEditDialog = remember { mutableStateOf(false) }
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp, end = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.name,
                    fontSize = 20.sp,
                )
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    if (isActiveDelDialog.value){
                        DeleteDialog(isActiveDelDialog, item)
                    }
                    if (isActiveEditDialog.value){
                        EditDialog(isActiveEditDialog, item)
                    }
                    IconButton(modifier = Modifier.size(35.dp),
                        onClick = {
                            isActiveEditDialog.value = true
                        }
                    ) {
                        Icon(
                            Icons.Filled.Create,
                            contentDescription = stringResource(R.string.itemUi_icon_edit),
                            tint = buttonEdit
                        )
                    }
                    IconButton(modifier = Modifier.size(35.dp),
                        onClick = {
                            isActiveDelDialog.value = true
                        }
                    ) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = stringResource(R.string.itemUi_icon_edit),
                            tint = buttonDelete
                        )
                    }
                }
            }

                FlowRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    val tags = item.tags
                        .replace(Regex("[\"\\[\\]]"), "")
                        .split(",")
                        .map { it.trim() }
                        .filter { it.isNotEmpty() }
                    tags.forEach { tag ->
                        SuggestionChip(
                            onClick = {},
                            label = { Text(text = tag) }
                        )
                    }
                }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, (-5).dp)) {

                Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                    var itemAmount = item.amount.toString()
                    if (itemAmount == "0") {
                        itemAmount = stringResource(R.string.itemUi_text_outOfStock)
                    }
                    Text(text = stringResource(R.string.itemUi_text_stock), fontSize = 15.sp)
                    Text(
                        text = itemAmount,
                        fontSize = 15.sp,
                        modifier = Modifier.offset(0.dp, (-5).dp)
                    )
                }
                Column {
                    val date = item.time
                    val calendar = Calendar.getInstance(Locale.getDefault())
                    calendar.timeInMillis = date
                    val formattedDate =
                        DateFormat.format(stringResource(R.string.itemUi_text_dateFormat), calendar)
                            .toString()

                    Text(text = stringResource(R.string.itemUi_text_date), fontSize = 15.sp)
                    Text(
                        text = formattedDate,
                        fontSize = 15.sp,
                        modifier = Modifier.offset(0.dp, (-5).dp)
                    )
                }
            }
        }
    }
}
