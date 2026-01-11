package com.example.jetpackcompose.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jetpackcompose.Model.UserData
import com.example.jetpackcompose.Rest.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//class DemoViewModel : ViewModel() {
//
//    var navigator: Navigator? = null
//    fun GetSummaryData() {
//        ApiClient.setBaseUrl().getData?.enqueue(object : retrofit2.Callback<UserData> {
//            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
//
//                val res = response.body()
//                if (res != null) {
//                    navigator?.getData(res)
//                }
//            }
//
//            override fun onFailure(
//                call: Call<UserData?>,
//                t: Throwable
//            ) {
//            }
//
//        })
//    }
//
//}

class DemoViewModel : ViewModel() {

    private val _userData = mutableStateOf<UserData?>(null)
    val userData: State<UserData?> = _userData

    fun getSummaryData() {
        ApiClient.setBaseUrl().getData().enqueue(object : Callback<UserData> {
            override fun onResponse(
                call: Call<UserData?>,
                response: Response<UserData?>
            ) {                                              //Best way to use API in Compose
                                                            // no need to use navigator (Not Recommended)
                _userData.value = response.body()
            }

            override fun onFailure(
                call: Call<UserData?>,
                t: Throwable
            ) {
            }
        })
    }

}
/*🎯 So which is BEST?
Method	Rating	When to Use
ViewModel + State (Compose-way)	⭐⭐⭐⭐⭐	Always (recommended by Google)
Navigator callback	⭐⭐⭐	Temporary, small apps, learning stage
API in Composable	⭐	Never

        */
