package com.example.customapiwithmvvm.simpleMVVM.JetpackComposeMVVM

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider

class ComposeMVVM : ComponentActivity() {

    var viewmodel: ComposeViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel= ViewModelProvider(this)[ComposeViewModel::class.java]
        setContent {
            SimpleUI(this@ComposeMVVM,viewmodel!!)
        }
    }
}

//@Preview
@Composable
fun SimpleUI(
    context: ComposeMVVM,
    viewModel: ComposeViewModel
) {

    // 🔥 COLLECT STATE FROM VIEWMODEL (THIS IS DATA BINDING)
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        uiState.data?.lastName?.let {
            Text(
                text = it,

                )
        }
    }

}
