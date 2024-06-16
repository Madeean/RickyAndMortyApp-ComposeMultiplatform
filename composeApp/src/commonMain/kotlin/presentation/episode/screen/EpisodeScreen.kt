package presentation.episode.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.episode.model.network.EpisodeDetailModelDomain
import presentation.episode.viewmodel.EpisodeViewModel
import presentation.theme.abuabumuda
import presentation.theme.biru
import presentation.theme.black
import util.RequestState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodeScreen(innerPaddingValues: PaddingValues, viewModel: EpisodeViewModel) {
  val dataState by viewModel.episode.collectAsState()

  var textOnChanged by remember { mutableStateOf("") }
  var textIsDone by remember { mutableStateOf("") }

  LaunchedEffect(key1 = true){
    viewModel.getAllData()
  }

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

    when(dataState) {
      is RequestState.Idle ->{

      }
      is RequestState.Loading ->{

      }
      is RequestState.Error -> {

      }
      is RequestState.Success -> {
        val successState = dataState as RequestState.Success
        LazyColumn {
          items(items = successState.data, key = {data ->
            data.episode
          }){data ->
            EpisodeItem(data)
          }
        }
      }
    }
  }
}

@Composable
fun EpisodeItem(data: EpisodeDetailModelDomain) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .height(140.dp)
      .padding(horizontal = 15.dp, vertical = 10.dp),
    shape = RoundedCornerShape(12.dp),
    elevation = CardDefaults.cardElevation(5.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color.White
    )
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)
    ) {
      Column(
        modifier = Modifier.align(Alignment.TopStart)
      ) {
        Text(
          text = data.name, // Gunakan string resource sesuai kebutuhan
          color = black,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = data.episode, // Gunakan string resource atau data dinamis sesuai kebutuhan
          color = Color.Black,
          fontSize = 16.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = data.airDate,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          fontSize = 14.sp,
          fontWeight = FontWeight.Thin
        )
      }
      Button(
        onClick = { /* Handle button click */ },
        modifier = Modifier.align(Alignment.BottomEnd),
        colors = ButtonDefaults.buttonColors(
          containerColor = biru,
          contentColor = abuabumuda
        ),
        shape = RoundedCornerShape(18.dp)
      ) {
        Text(
          text = "Detail", // Gunakan string resource sesuai kebutuhan
          fontSize = 16.sp,
          color = abuabumuda
        )
      }
    }
  }
}