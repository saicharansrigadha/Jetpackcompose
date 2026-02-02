package com.example.jetpackcompose.ApiDataBinding


import androidx.compose.runtime.livedata.observeAsState
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.collections.emptyList

class DataBindingApi : ComponentActivity(), Navigation {

    private val userslist = mutableStateListOf<UserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DataBindingExample(userslist)
        }
    }


    override fun getData(list: List<UserModel>) {
        userslist.clear()
        userslist.addAll(list) // 🔥 triggers recomposition
    }

    override fun Message(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }
}


@Composable
fun DataBindingExample(userslist: List<UserModel>, viewModel: ApiMVVM = viewModel()) {

    val user by viewModel.users.observeAsState(emptyList())
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.navigation = context as Navigation
        viewModel.GetAPIData()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.Red),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "BACK",
                    color = Color.White,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp

                )
                Text(
                    text = "Column",
                    color = Color.White,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                )

                Text(
                    text = userslist?.get(0)?.name ?: "",
                    color = Color.White,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                )

            }

        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            itemsIndexed(user) { index, item ->
                ListCell(
                    name = item.role,
                    isActive = false,
                )
            }
        }
    }
}

@Composable
fun ListCell(name: String?, isActive: Boolean) {
    Text(
        text = name ?: "",
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        color = Color.Black,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(50.dp))
            .padding(10.dp)
            .clickable {
            }
    )

    //border is use to add color for the whole text or box with border
    // stroke and shape of the design
}