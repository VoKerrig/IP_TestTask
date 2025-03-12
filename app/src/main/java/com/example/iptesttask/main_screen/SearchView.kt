package com.example.iptesttask.main_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.example.iptesttask.R
import com.example.iptesttask.roomdb.MainViewModel
import com.example.iptesttask.ui.theme.Purple40

@Composable
fun SearchView(
    searchText: String,
    keyboardController: SoftwareKeyboardController?,
    mainViewModel: MainViewModel,
    onSearch: (String) -> Unit
) {
    val isVisible = searchText.isNotBlank()
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = searchText,
        onValueChange = mainViewModel::onSearchTextChange,
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(R.string.searchView_icon_search)
            ) },
        trailingIcon = {
            if (isVisible){
                IconButton(onClick = { mainViewModel.onSearchTextChange("") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = stringResource(R.string.searchView_icon_clear)
                    )
                }
            } },
        label = { Text( text = stringResource(R.string.searchView_label), color = Color.DarkGray) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Purple40,
            unfocusedBorderColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(searchText)
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        )
    )
}