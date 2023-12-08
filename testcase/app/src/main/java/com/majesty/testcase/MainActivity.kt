package com.majesty.testcase

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majesty.testcase.person.PersonModel
import com.majesty.testcase.ui.theme.Purple80
import com.majesty.testcase.ui.theme.TestcaseTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestcaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple80),
                title = {
                    Text(
                        text = "Simple API Request",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                val person = remember {
                    mutableStateOf(PersonModel(
                        id = "",
                        idProp = "",
                        nik = "",
                        nama = "",
                        namaProp = "",
                        jumlahPenduduk = "",
                    ))
                }

                Spacer(modifier = Modifier.height(15.dp))

                Spacer(modifier = Modifier.height(15.dp))

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {
                            val data = sendRequest(
                                profileState = person
                            )

                            Log.d("Main Activity", person.toString())
                        }
                    ) {
                        Text(text = "Get Data")
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(text = "Id: " + person.component1().id, fontSize = 18.sp)
                Text(text = "Id Provinsi: " + person.component1().idProp, fontSize = 18.sp)
                Text(text = "NIK: " + person.component1().nik, fontSize = 18.sp)
                Text(text = "Nama: " + person.component1().nama, fontSize = 18.sp)
                Text(text = "Nama Provinsi: " + person.component1().namaProp, fontSize = 18.sp)
                Text(text = "Jumlah Penduduk: " + person.component1().jumlahPenduduk, fontSize = 18.sp)
            }
        }
    )
}

fun sendRequest(
    profileState: MutableState<PersonModel>
) {
    val retrofit = Retrofit.Builder()
        .baseUrl("YOUR_BASE_URL")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(ApiService::class.java)

    val call: Call<PersonModel?>? = api.getPerson();

    call!!.enqueue(object: Callback<PersonModel?> {
        override fun onResponse(call: Call<PersonModel?>, response: Response<PersonModel?>) {
            if(response.isSuccessful) {
                Log.d("Main", "success!" + response.body().toString())
                profileState.value = response.body()!!
            }
        }

        override fun onFailure(call: Call<PersonModel?>, t: Throwable) {
            Log.e("Main", "Failed mate " + t.message.toString())
        }
    })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestcaseTheme {
        MainScreen()
    }
}