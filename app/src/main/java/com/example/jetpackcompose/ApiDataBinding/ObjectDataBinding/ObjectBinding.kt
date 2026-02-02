package com.example.jetpackcompose.ApiDataBinding.ObjectDataBinding

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
import com.example.jetpackcompose.ApiDataBinding.ListCell
import com.example.jetpackcompose.ApiDataBinding.UserModel

class ObjectBinding : AppCompatActivity(), Nav {
    private val dataState = mutableStateOf<Data1?>(null)
    var datalist = mutableStateListOf<UserModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm: ObjectMVVM = viewModel()
            Binding(dataState, datalist, vm)
        }
    }

    override fun getData(data: Data1?) {
        dataState.value = data  //object way but we need to use mutablestateof in compose
    }

    override fun getDataList(list: List<UserModel>) {
        datalist.clear()
        datalist.addAll(list)   // 🔥 correct
    }


    override fun Message(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun Binding(
    dataState: MutableState<Data1?>, datalist: List<UserModel>, vm: ObjectMVVM
) {

    val context = LocalContext.current

    val users by vm.userobj.observeAsState() // by using livedata way

    val userlist by vm.users.observeAsState(emptyList())   //by using livedata way

    LaunchedEffect(Unit) {
        vm.navigation = context as Nav
        vm.GetAPIData()
        vm.getListData()
    }
    val data = dataState.value



    Column(modifier = Modifier.fillMaxSize()) {

        // Top bar
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(data?.firstName ?: "", color = Color.White)
                Text(users?.firstName ?: "", color = Color.White)
                Text(users?.maidenName ?: "", color = Color.White)

                if (datalist.isNotEmpty()) {
                    Text(datalist[0].name ?: "", color = Color.White)
                } else {
                    Text("Loading...", color = Color.White)
                }
                if (userlist.isNotEmpty()) {
                    Text(userlist[0].role ?: "", color = Color.White)
                } else {
                    Text("Data Loading...", color = Color.White)
                }
            }
        }

        // 👇 NOW LazyColumn is visible
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color.Yellow) // debug color

        ) {
            items(userlist) { item ->               //list with using livedata
                ApiListCell(
                    name = item.name,                       //datalist is from navigator
                    isActive = false
                )
            }
        }
    }
}

@Composable
fun ApiListCell(name: String?, isActive: Boolean) {
    Text(
        text = name ?: "",
        color = Color.Black,                // 👈 change
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0E0E0))  // 👈 light gray bg
            .padding(12.dp)
    )
    //border is use to add color for the whole text or box with border
    // stroke and shape of the design
}
