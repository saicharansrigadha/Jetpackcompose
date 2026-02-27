package com.example.jetpackcompose.StateHoisting

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.Rest.ApiClient
import com.example.jetpackcompose.Rest.GetData
import com.example.jetpackcompose.Rest.SaveDataRequest
import com.example.jetpackcompose.Rest.SaveDataResponse
import com.google.firebase.crashlytics.FirebaseCrashlytics
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StateHoisting : AppCompatActivity() {
    var name1: String? = ""
    var result1: String? = ""
    var d:String?=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        setContent {
            Parent(this@StateHoisting, name1, result1,d)
        }
    }

    fun GetData() {
        ApiClient.setBaseUrl().getUser().enqueue(object : Callback<GetData> {
            override fun onResponse(call: Call<GetData?>, response: Response<GetData?>) {
                var res = response.body()
                if (res != null) {
                    d=res.data?.firstName
                }
            }

            override fun onFailure(call: Call<GetData?>, t: Throwable) {


            }
        })
    }


    fun SaveData() {
        var req = SaveDataRequest()
        req.firstName = name1
        req.lastName = result1
        ApiClient.setBaseUrl().saveUser(req).enqueue(object : Callback<SaveDataResponse> {
            override fun onResponse(call: Call<SaveDataResponse?>, response: Response<SaveDataResponse?>) {
                var res = response.body()
                if (res != null) {
                    Toast.makeText(this@StateHoisting, "Success", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SaveDataResponse?>, t: Throwable) {
                Toast.makeText(
                    this@StateHoisting,
                    "Failed: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })
    }
}

@Composable
fun Parent(context: StateHoisting, name1: String?, result1: String?, d: String?) {
    var name by remember { mutableStateOf(name1) }
    var result by remember { mutableStateOf(result1) }

    LaunchedEffect(Unit) {
        context.GetData()
    }

    val list = listOf(
        "Sai",
        "Charan",
        "Srigadha"
    )

    val withlist = listOf(
        User(1, "Saicharan", "Srigadha@gmail.com")
    )

    StateHoistingExample(
        name = name?:"",
        result = result?:"",
        onValueChange = { name = it },
        onResult = { result = it },
        list = list,
        withlist = withlist,
        onClearName = {
            name = ""
        },
        context = context,
        d=d
    )
}

@Composable
fun StateHoistingExample(
    name: String,
    onValueChange: (String) -> Unit,
    result: String,
    onResult: (String) -> Unit,
    list: List<String>,
    withlist: List<User>,
    onClearName: () -> Unit,
    context: StateHoisting,
    d: String?
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var value1 by remember { mutableStateOf("") }
        var user by remember { mutableStateOf(User(0)) }
        var userList = remember { mutableStateListOf<User>() }

        BasicTextField(
            value = name,
            onValueChange = {
                onValueChange(it)
            },
            textStyle = TextStyle.Default.copy(color = Color.Black),
            modifier = Modifier
                .padding(25.dp)
                .fillMaxWidth()
                .height(35.dp)
                .background(Color.Cyan)
                .border(1.dp, Color.Gray, RoundedCornerShape(6.dp))
                .padding(horizontal = 8.dp, vertical = 6.dp),

            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    if (name.isEmpty()) {
                        Text(
                            text = "Enter name",
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    innerTextField()
                }
            }
        )

        OutlinedTextField(
            value = value1,
            onValueChange = { value1 = it },
            placeholder = { Text("Enter name") },
            label = { Text("Name") },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )

        Button(
            onClick = {
                onResult(name)
                user = user.copy(id = 0, name = name, email = value1)
                userList?.add(user)
                onClearName()
                value1 = ""
                //FirebaseCrashlytics.getInstance().log("Test crash")
//                throw RuntimeException("Test Crash")
                context.SaveData()
            },
            modifier = Modifier
                .wrapContentWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text("Login")
        }

        Text(
            text = user.name.toString(),
            modifier = Modifier.padding(top = 20.dp),
            style = TextStyle(fontSize = 20.sp)
        )
        Text(
            text = user.email.toString(),
            modifier = Modifier.padding(top = 20.dp),
            style = TextStyle(fontSize = 20.sp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            itemsIndexed(list) { index, name ->
                TextCell(text = "$index - $name")
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            items(withlist) {
                TextCell(text = it.name.toString())
            }


        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            items(userList) {
                TextCell(text = it.name.toString() + "" + it.email.toString())
            }


        }

        Text(
            text = d?:"",
            modifier = Modifier.padding(top = 20.dp),
            style = TextStyle(fontSize = 25.sp)
        )

    }
}

@Composable
fun TextCell(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview
@Composable
fun Show() {
    StateHoistingExample(
        "",
        {

        },
        result = "",
        onResult = {

        },
        emptyList<String>(),
        emptyList<User>(),
        onClearName = {

        },
        StateHoisting(),
        ""

        )
}