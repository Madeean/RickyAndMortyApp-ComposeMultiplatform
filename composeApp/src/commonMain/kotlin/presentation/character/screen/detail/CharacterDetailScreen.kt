package presentation.character.screen.detail

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import domain.character.model.network.CharacterDetailModelDomain
import domain.episode.model.network.EpisodeDetailModelDomain
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import presentation.character.viewmodel.CharacterViewModel
import presentation.episode.screen.EpisodeItem
import presentation.episode.viewmodel.EpisodeViewModel
import presentation.theme.abuabumuda
import presentation.theme.biru
import presentation.theme.black
import presentation.theme.white
import presentation.util.DefaultAppBar
import presentation.util.DefaultFavoriteCard
import presentation.util.getIdFromUrl
import util.LoaderShow
import util.RequestState
import util.RequestState.Idle
import util.RequestState.Loading
import util.RequestState.Success

@Composable
fun CharacterDetailScreen(
  onBackClicked: NavController,
  episodeViewModel: EpisodeViewModel,
  characterViewModel: CharacterViewModel,
  characterId: Int,
  navigateToDetailEpisode: (Int) -> Unit,
  navigateToDetailLocation: (Int) -> Unit
) {
  var isCharacterFavorite by remember { mutableStateOf(false) }
  val detailCharacter by characterViewModel.detailCharacter.collectAsState()
  val listEpisode by episodeViewModel.listEpisodeFromUrl.collectAsState()

  val scrollState = rememberScrollState()

  var idEpisodeList by remember { mutableStateOf("") }

  LaunchedEffect(key1 = characterId) {
    characterViewModel.getDetailCharacter(characterId)
  }

  if (idEpisodeList.isNotBlank()) {
    LaunchedEffect(key1 = characterId) {
      episodeViewModel.getListEpisodeFromUrl(idEpisodeList)
    }
  }

  Scaffold(
    contentColor = abuabumuda,
    containerColor = abuabumuda,
    modifier = Modifier.fillMaxSize(),
    topBar = {
      DefaultAppBar("Detail Character", true, onBackClicked)
    }
  ) { innerPadding ->

    when (detailCharacter) {
      is Idle -> {
        LoaderShow()
      }

      is Loading -> {
        LoaderShow()
      }

      is Success -> {
        val data = detailCharacter as Success
        val urlEpisode = data.data.episode

        if (urlEpisode.isNotEmpty()) {
          idEpisodeList = getIdFromUrl(urlEpisode)
        }

        CharacterDetailSuccessView(
          navigateToDetailLocation,
          navigateToDetailEpisode,
          listEpisode,
          data.data,
          innerPadding,
          scrollState,
          isCharacterFavorite,
        ) { favorite ->
          isCharacterFavorite = favorite
        }
      }

      is RequestState.Error -> {
        val error = detailCharacter as RequestState.Error

        println("MASUK ERROR ${error.error}")
      }
    }

  }
}

@Composable
fun CharacterDetailSuccessView(
  navigateToDetailLocation: (Int) -> Unit,
  navigateToDetailEpisode: (Int) -> Unit,
  listEpisode: RequestState<List<EpisodeDetailModelDomain>>,
  data: CharacterDetailModelDomain,
  innerPaddingValues: PaddingValues,
  scrollState: ScrollState,
  isCharacterFavorite: Boolean,
  changeCharacterFavorite: (Boolean) -> Unit,
) {
  Column(
    modifier = Modifier.fillMaxSize().padding(innerPaddingValues).verticalScroll(scrollState)
  ) {

    Card(
      modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(12.dp),
      shape = RoundedCornerShape(12.dp),
      colors = CardDefaults.cardColors(
        containerColor = white
      ),
      elevation = CardDefaults.cardElevation(5.dp)
    ) {
      KamelImage(
        resource = asyncPainterResource(data = data.image),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.Crop
      )
    }

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

        Text("Status", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = black)
        Text(data.status, fontSize = 20.sp, color = black)

        Text("Spesies", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = black)
        Text(data.species, fontSize = 20.sp, color = black)

        Text("Type", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = black)
        Text(
          text = data.type.ifBlank { "Unknown" },
          fontSize = 20.sp, color = black
        )

        Text("Gender", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = black)
        Text(data.gender, fontSize = 20.sp, color = black)
      }
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
        modifier = Modifier.padding(15.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          "Location",
          color = black,
          fontWeight = FontWeight.Bold,
          fontSize = 22.sp,
        )
        Row {
          Text(
            data.location.name,
            fontSize = 18.sp,
            color = black,
            modifier = Modifier.weight(2f),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
          )
          Button(
            enabled = data.location.url.isNotBlank(),
            onClick = {
              val locationId = getIdFromUrl(data.location.url)
              navigateToDetailLocation(locationId)
            },
            colors = ButtonDefaults.buttonColors(
              containerColor = biru,
              contentColor = white,
            ),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.weight(1f).wrapContentWidth()
          ) {
            Text(
              text = "Detail",
              fontSize = 16.sp,
              color = if (data.location.url.isBlank()) black else white
            )
          }
        }
      }
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
        modifier = Modifier.padding(15.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          "Origin",
          color = black,
          fontWeight = FontWeight.Bold,
          fontSize = 22.sp,
        )
        Row {
          Text(
            data.origin.name,
            fontSize = 18.sp,
            color = black,
            modifier = Modifier.weight(2f),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
          )
          Button(
            enabled = data.origin.url.isNotBlank(),
            onClick = {
              val originId = getIdFromUrl(data.origin.url)
              navigateToDetailLocation(originId)
            },
            colors = ButtonDefaults.buttonColors(
              containerColor = biru,
              contentColor = white,
            ),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.weight(1f).wrapContentWidth()
          ) {
            Text(
              text = "Detail",
              fontSize = 16.sp,
              color = if (data.origin.url.isBlank()) black else white
            )
          }
        }
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


        when (listEpisode) {
          is Idle -> {
            LoaderShow()
          }

          is Loading -> {
            LoaderShow()
          }

          is Success -> {
            val dataList = listEpisode.data

            Column(
              modifier = Modifier.fillMaxWidth()
            ) {
              dataList.forEach {
                EpisodeItem(it, navigateToDetailEpisode)
              }
            }
          }

          is RequestState.Error -> {
            println("MASUK ERROR ${listEpisode.error}")
          }
        }
      }
    }

  }
}