package presentation.episode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeAction.Companion
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import presentation.theme.abuabumuda
import presentation.theme.black

class EpisodeScreen(val innerPaddingValues: PaddingValues) : Screen{

  init {
    println("Episode screen")
  }
  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  override fun Content() {
//    val viewModel: EpisodeViewModel = getScreenModel<EpisodeViewModel>()
//    val text by viewModel.message.collectAsState()

    var textOnChanged by remember { mutableStateOf("") }
    var textIsDone by remember { mutableStateOf("") }

//    LaunchedEffect(key1 = true){
//      viewModel.fetchData()
//    }

    Column(
      modifier = Modifier.padding(innerPaddingValues).fillMaxSize().padding(top = 8.dp).background(
        abuabumuda
      ),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
          focusedBorderColor = black,
          unfocusedBorderColor = black,
        ),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
          imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
          onSearch = {
            textIsDone = textOnChanged
          }
        ),
        leadingIcon = {
          Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null
          )
        },
        shape = RoundedCornerShape(18.dp),
        placeholder = {
          Text("Search Episode")
        },
        value = textOnChanged,
        onValueChange = {
          textOnChanged = it
        }
      )
//      Text("${text}")

    }
  }

}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun EpisodeScreen(innerPaddingValues: PaddingValues) {
//
//  val viewModel: EpisodeViewModel =
//
//  var textOnChanged by remember { mutableStateOf("") }
//  var textIsDone by remember { mutableStateOf("") }
//  Column(
//    modifier = Modifier.padding(innerPaddingValues).fillMaxSize().padding(top = 8.dp).background(
//      abuabumuda
//    ),
//    horizontalAlignment = Alignment.CenterHorizontally,
//  ) {
//    OutlinedTextField(
//      colors = TextFieldDefaults.outlinedTextFieldColors(
//        focusedBorderColor = black,
//        unfocusedBorderColor = black,
//      ),
//      singleLine = true,
//      maxLines = 1,
//      keyboardOptions = KeyboardOptions(
//        imeAction = ImeAction.Search
//      ),
//      keyboardActions = KeyboardActions(
//        onSearch = {
//          textIsDone = textOnChanged
//        }
//      ),
//      leadingIcon = {
//        Icon(
//          imageVector = Icons.Filled.Search,
//          contentDescription = null
//        )
//      },
//      shape = RoundedCornerShape(18.dp),
//      placeholder = {
//        Text("Search Episode")
//      },
//      value = textOnChanged,
//      onValueChange = {
//        textOnChanged = it
//      }
//    )
//    Text("${textIsDone}")
//
//  }
//}