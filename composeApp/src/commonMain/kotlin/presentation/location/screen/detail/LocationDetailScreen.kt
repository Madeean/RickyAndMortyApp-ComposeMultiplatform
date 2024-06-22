package presentation.location.screen.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import domain.character.model.network.CharacterDetailModelDomain
import domain.location.model.network.LocationDetailModelDomain
import presentation.character.viewmodel.CharacterViewModel
import presentation.location.viewmodel.LocationViewModel
import presentation.theme.abuabumuda
import presentation.theme.black
import presentation.theme.white
import presentation.util.CharacterItem
import presentation.util.DefaultAppBar
import presentation.util.DefaultFavoriteCard
import presentation.util.NonlazyGrid
import presentation.util.getIdFromUrl
import util.LoaderShow
import util.RequestState
import util.RequestState.Idle
import util.RequestState.Loading
import util.RequestState.Success

@Composable
fun LocationDetailScreen (
  onBackClicked: NavController,
  locationViewModel: LocationViewModel,
  characterViewModel: CharacterViewModel,
  locationId: Int,
  navigateToCharacterDetailScreen: (Int) -> Unit
){
  val scrollState = rememberScrollState()
  var isCharacterFavorite by remember { mutableStateOf(false) }
  val detailLocation by locationViewModel.detailLocation.collectAsState()
  val listCharacter by characterViewModel.listCharacter.collectAsState()

  var idCharacter by remember { mutableStateOf("") }

  LaunchedEffect(key1 = locationId){
    locationViewModel.getDetailLocation(locationId)
  }

  if(idCharacter.isNotBlank()){
    LaunchedEffect(key1 = locationId){
      characterViewModel.getListCharacter(idCharacter)
    }
  }

  Scaffold(
  contentColor = abuabumuda,
  containerColor = abuabumuda,
  modifier = Modifier.fillMaxSize(),
  topBar = {
    DefaultAppBar("Detail Episode",true, onBackClicked )
  },
  ) { innerPadding ->

    when (detailLocation) {
      is Idle -> {
        LoaderShow()
      }

      is Loading -> {
        LoaderShow()
      }

      is Success -> {
        val data = detailLocation as Success
        val urlCharacter = data.data.residents

        if (urlCharacter.isNotEmpty()) {
          idCharacter = getIdFromUrl(urlCharacter)
        }

        LocationDetailSuccessView(
          navigateToCharacterDetailScreen,
          listCharacter,
          data.data,
          innerPadding,
          scrollState,
          isCharacterFavorite,
        ) { favorite ->
          isCharacterFavorite = favorite
        }
      }

      is RequestState.Error -> {
        val error = detailLocation as RequestState.Error

        println("MASUK ERROR ${error.error}")
      }
    }

  }
}

@Composable
fun LocationDetailSuccessView(
  navigateToDetailCharacter: (Int) -> Unit,
  listCharacter: RequestState<List<CharacterDetailModelDomain>>,
  data: LocationDetailModelDomain,
  innerPaddingValues: PaddingValues,
  scrollState: ScrollState,
  isCharacterFavorite: Boolean,
  changeCharacterFavorite: (Boolean) -> Unit,
) {
  Column(
    modifier = Modifier.fillMaxSize().padding(innerPaddingValues).verticalScroll(scrollState)
  ) {

    Spacer(modifier = Modifier.height(20.dp))

    DefaultFavoriteCard(isCharacterFavorite) {
      changeCharacterFavorite(isCharacterFavorite.not())
    }

    Spacer(modifier = Modifier.height(20.dp))

    Card(
      modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
      shape = RoundedCornerShape(12.dp),
      colors = CardDefaults.cardColors(
        containerColor = white
      )
    ) {
      Column(
        modifier = Modifier.padding(15.dp)
      ) {
        Text("Name", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = black)
        Text(data.name, fontSize = 20.sp, color = black)

        Text("Type", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = black)
        Text(data.type, fontSize = 20.sp, color = black)

        Text("Dimension", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = black)
        Text(data.dimension, fontSize = 20.sp, color = black)
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
      colors = CardDefaults.cardColors(
        containerColor = white
      )
    ) {
      Column(
        modifier = Modifier.padding(15.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text("Character", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = black)


          when (listCharacter) {
            is Idle -> {
              LoaderShow()
            }

            is Loading -> {
              LoaderShow()
            }

            is Success -> {
              val dataList = listCharacter.data

              NonlazyGrid(
                columns = 2,
                itemCount = dataList.size
              ){
                CharacterItem(dataList[it], navigateToDetailCharacter)
              }
            }

            is RequestState.Error -> {
              println("MASUK ERROR ${listCharacter.error}")
            }
          }
      }
    }

  }
}