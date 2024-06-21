package presentation.character.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.collectAsLazyPagingItems
import presentation.character.viewmodel.CharacterViewModel
import presentation.theme.abuabumuda
import presentation.theme.biru
import presentation.theme.black
import presentation.util.CharacterItem
import presentation.util.CustomOutlinedTextField
import presentation.util.DropdownGender
import util.ErrorView
import util.LoaderShow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterScreen(
  innerPaddingValues: PaddingValues,
  viewModel: CharacterViewModel,
  navigateToCharacterDetailScreen: (Int) -> Unit
) {
  val dataPaging by rememberUpdatedState(viewModel.character.collectAsLazyPagingItems())
  var nameOnChanged by remember { mutableStateOf("") }
  var speciesOnChanged by remember { mutableStateOf("") }
  var typeOnChanged by remember { mutableStateOf("") }
  var selectedGender by remember { mutableStateOf("") }
  var selectedStatus by remember { mutableStateOf("") }

  var showLoading by rememberSaveable { mutableStateOf(true) }

  val listGender = listOf("all", "male", "female", "genderless", "unknown")
  val listStatus = listOf("all", "alive", "dead", "unknown")

  LaunchedEffect(key1 = true) {
    viewModel.getCharacterPaging()
  }

  val sheetState = rememberModalBottomSheetState()
  var showBottomSheet by remember { mutableStateOf(false) }

  Box(
    modifier = Modifier.padding(innerPaddingValues).fillMaxSize().background(abuabumuda)
  ) {

    if (showBottomSheet) {
      ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { showBottomSheet = false }
      ) {
        CustomOutlinedTextField(
          value = nameOnChanged,
          onValueChange = { nameOnChanged = it },
          placeholderText = "Search Name"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
          modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Box(modifier = Modifier.weight(1f)) {
            DropdownGender("Gender", listGender) { gender ->
              selectedGender = gender
            }
          }
          Spacer(modifier = Modifier.width(8.dp))
          Box(modifier = Modifier.weight(1f)) {
            DropdownGender("Status", listStatus) { status ->
              selectedStatus = status
            }
          }

        }
        Spacer(modifier = Modifier.height(20.dp))
        CustomOutlinedTextField(
          value = speciesOnChanged,
          onValueChange = { speciesOnChanged = it },
          placeholderText = "Search Species"
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomOutlinedTextField(
          value = typeOnChanged,
          onValueChange = { typeOnChanged = it },
          placeholderText = "Search Type"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
          modifier = Modifier.padding(horizontal = 15.dp).fillMaxWidth().wrapContentHeight(),
          colors = ButtonDefaults.buttonColors(
            containerColor = biru,
            contentColor = abuabumuda
          ),
          onClick = {
            val genderData = if (selectedGender == "all") "" else selectedGender
            val statusData = if (selectedStatus == "all") "" else selectedStatus
            viewModel.getCharacterPaging(
              name = nameOnChanged,
              status = statusData,
              species = speciesOnChanged,
              type = typeOnChanged,
              gender = genderData
            )
            showBottomSheet = false
          },
          shape = RoundedCornerShape(18.dp)
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Text("Search Character")
          }
        }
        Spacer(modifier = Modifier.height(20.dp))
      }
    }

    Column(
      modifier = Modifier.fillMaxSize().padding(top = 8.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Button(
        modifier = Modifier.padding(horizontal = 15.dp).fillMaxWidth().wrapContentHeight(),
        colors = ButtonDefaults.buttonColors(
          containerColor = biru,
          contentColor = abuabumuda
        ),
        onClick = {
          showBottomSheet = true
        },
        shape = RoundedCornerShape(18.dp)
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text("Open Filter")
        }
      }

      if (showLoading) {
        LoaderShow()
      }

      LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
      ) {
        items(dataPaging.itemCount) { index ->
          val item = dataPaging[index]
          item?.let {
            CharacterItem(it,navigateToCharacterDetailScreen)
          }.let {
            showLoading = false
          }

        }

        dataPaging.loadState.apply {
          when {
            refresh is LoadStateNotLoading && dataPaging.itemCount < 1 -> {
              item {
                Box(
                  modifier = Modifier.fillMaxWidth(1f),
                  contentAlignment = Alignment.TopCenter
                ) {
                  Text(
                    text = "No Items",
                    color = black,
                    fontSize = 18.sp
                  )
                }
              }
            }

            refresh is LoadStateLoading -> {
              item {
                showLoading = true

              }
            }

            append is LoadStateLoading -> {
              item {
                showLoading = true

              }
            }

            refresh is LoadStateError -> {
              item {

                ErrorView(
                  message = "No Internet Connection.",
                  onClickRetry = { dataPaging.retry() },
                  modifier = Modifier.fillMaxWidth(1f)
                )
              }
            }
          }
        }
      }
    }
  }
}