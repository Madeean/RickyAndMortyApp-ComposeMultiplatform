package presentation.episode.screen.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import domain.episode.model.network.EpisodeDetailModelDomain
import org.jetbrains.compose.resources.painterResource
import presentation.character.viewmodel.CharacterViewModel
import presentation.episode.viewmodel.EpisodeViewModel
import presentation.theme.abuabumuda
import presentation.theme.black
import presentation.theme.hitam
import presentation.theme.merah
import presentation.theme.white
import presentation.util.CharacterItem
import presentation.util.DefaultAppBar
import presentation.util.NonlazyGrid
import presentation.util.getIdFromUrl
import rickandmortyapp.composeapp.generated.resources.Res
import rickandmortyapp.composeapp.generated.resources.favorite_full
import rickandmortyapp.composeapp.generated.resources.favorite_outline
import util.LoaderShow
import util.RequestState

@Composable
fun EpisodeDetailScreen(
  onBackClicked: NavController,
  episodeViewModel: EpisodeViewModel,
  characterViewModel: CharacterViewModel,
  episodeId: Int,
  navigateToCharacterDetailScreen: (Int) -> Unit
) {
  var isEpisodeFavorite by remember { mutableStateOf(false) }
  val scrollState = rememberScrollState()
  val dataDetailEpisode by episodeViewModel.episodeDetail.collectAsState()
  val characterList by characterViewModel.listCharacter.collectAsState()

  var idCharacterList by remember { mutableStateOf("") }

  LaunchedEffect(key1 = episodeId) {
    episodeViewModel.getDetailEpisode(episodeId)
  }

  if (idCharacterList.isNotBlank()) {
    LaunchedEffect(key1 = episodeId) {
      characterViewModel.getListCharacter(idCharacterList)
    }
  }

  Scaffold(
    contentColor = abuabumuda,
    containerColor = abuabumuda,
    modifier = Modifier.fillMaxSize(),
    topBar = {
      DefaultAppBar("Detail Episode", true, onBackClicked)
    },
  ) { innerPadding ->
    when (dataDetailEpisode) {
      is RequestState.Idle -> {
        LoaderShow()
      }

      is RequestState.Loading -> {
        LoaderShow()
      }

      is RequestState.Success -> {
        val data = dataDetailEpisode as RequestState.Success
        val urlCharacter = data.data.characterList

        if (urlCharacter.isNotEmpty()) {
          idCharacterList = getIdFromUrl(urlCharacter)
        }

        EpisodeDetailSuccessView(
          data.data,
          innerPadding,
          scrollState,
          isEpisodeFavorite,
          characterList,
          navigateToCharacterDetailScreen
        ) { favorite ->
          isEpisodeFavorite = favorite
        }
      }

      is RequestState.Error -> {
        val error = dataDetailEpisode as RequestState.Error

        println("MASUK ERROR ${error.error}")
      }
    }

  }
}

@Composable
private fun EpisodeDetailSuccessView(
  data: EpisodeDetailModelDomain,
  innerPaddingValues: PaddingValues,
  scrollState: ScrollState,
  isEpisodeFavorite: Boolean,
  characterList: RequestState<List<CharacterDetailModelDomain>>,
  navigateToCharacterDetailScreen: (Int) -> Unit,
  changeEpisodeFavorite: (Boolean) -> Unit,
) {
  Column(
    modifier = Modifier.padding(innerPaddingValues).fillMaxSize().verticalScroll(scrollState)
  ) {

//      FAVORITE SIDE
    Surface(
      modifier = Modifier.align(Alignment.End).padding(top = 20.dp, end = 20.dp),
      shape = RoundedCornerShape(12.dp),
      color = white
    ) {
      Row(
        modifier = Modifier.padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text("Favorite", color = black, fontSize = 20.sp)
        Spacer(modifier = Modifier.width(10.dp))
        if (isEpisodeFavorite) {
          IconButton(
            onClick = {
              changeEpisodeFavorite(isEpisodeFavorite.not())
            },
          ) {
            Icon(
              painter = painterResource(Res.drawable.favorite_full),
              contentDescription = null,
              modifier = Modifier.size(30.dp),
              tint = merah
            )
          }
        } else {
          IconButton(
            onClick = {
              changeEpisodeFavorite(isEpisodeFavorite.not())
            },
          ) {
            Icon(
              painter = painterResource(Res.drawable.favorite_outline),
              contentDescription = null,
              modifier = Modifier.size(30.dp),
              tint = merah
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

//    DESCRIPTION SIDE
    Card(
      modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
      colors = CardDefaults.cardColors(
        containerColor = white
      ),
      shape = RoundedCornerShape(12.dp),
    ) {
      Column(
        modifier = Modifier.padding(15.dp)
      ) {
        Text("Name", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = hitam)
        Text(text = data.name, fontSize = 20.sp, color = hitam)

        Spacer(modifier = Modifier.height(5.dp))

        Text("Air Date", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = hitam)
        Text(data.airDate, fontSize = 20.sp, color = hitam)

        Spacer(modifier = Modifier.height(5.dp))

        Text("Episode", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = hitam)
        Text(data.episode, fontSize = 20.sp, color = hitam)
      }
    }

//      Spacer(modifier = Modifier.height(20.dp))
//
//      Card(
//        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
//        colors = CardDefaults.cardColors(
//          containerColor = white
//        ),
//        shape = RoundedCornerShape(12.dp),
//      ) {
//        Column(
//          modifier = Modifier.padding(15.dp)
//        ) {
//          Text("Overview", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = hitam)
//          Text("Overview", fontSize = 20.sp, color = hitam, modifier = Modifier.padding(top = 8.dp))
//
//        }
//      }
    Spacer(modifier = Modifier.height(20.dp))

    Surface(
      modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
      shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
      color = white
    ) {
      Column(
        modifier = Modifier.padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text("Character", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = black)


        when (characterList) {
          is RequestState.Idle -> {
            LoaderShow()
          }

          is RequestState.Loading -> {
            LoaderShow()
          }

          is RequestState.Success -> {
            val dataList = characterList.data
            NonlazyGrid(
              columns = 2,
              itemCount = dataList.size,
            ) {
              CharacterItem(dataList[it], navigateToCharacterDetailScreen)
            }
          }

          is RequestState.Error -> {
            println("MASUK ERROR ${characterList.error}")
          }
        }
      }
    }
  }
}