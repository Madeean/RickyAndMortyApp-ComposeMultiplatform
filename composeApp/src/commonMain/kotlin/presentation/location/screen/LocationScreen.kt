package presentation.location.screen

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.collectAsLazyPagingItems
import domain.location.model.network.LocationDetailModelDomain
import presentation.character.screen.CustomOutlinedTextField
import presentation.location.viewmodel.LocationViewModel
import presentation.theme.abuabumuda
import presentation.theme.biru
import presentation.theme.black
import util.ErrorItem
import util.ErrorView
import util.LoaderShow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(innerPaddingValues: PaddingValues, viewModel: LocationViewModel) {
    val dataPaging by rememberUpdatedState(viewModel.location.collectAsLazyPagingItems())
    var nameOnChanged by remember { mutableStateOf("") }
    var typeOnChanged by remember { mutableStateOf("") }
    var dimensionOnChanged by remember { mutableStateOf("") }

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true){
        viewModel.getLocationPaging()
    }

    Column(
        modifier = Modifier.padding(innerPaddingValues).fillMaxSize().padding(top = 8.dp).background(
            abuabumuda
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){

        if (showBottomSheet) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false }
            ) {
                CustomOutlinedTextField(
                    value = nameOnChanged,
                    onValueChange = { nameOnChanged = it },
                    placeholderText = "Search Name Location"
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomOutlinedTextField(
                    value = typeOnChanged,
                    onValueChange = { typeOnChanged = it },
                    placeholderText = "Search Type Location"
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomOutlinedTextField(
                    value = dimensionOnChanged,
                    onValueChange = { dimensionOnChanged = it },
                    placeholderText = "Search Dimension Location"
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier.padding(horizontal = 15.dp).fillMaxWidth().wrapContentHeight(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = biru,
                        contentColor = abuabumuda
                    ),
                    onClick = {
                        viewModel.getLocationPaging(
                            name = nameOnChanged,
                            type = typeOnChanged,
                            dimension = dimensionOnChanged
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
                        Row {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Search Character", fontSize = 18.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }


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
                androidx.compose.material3.Text("Open Filter")
            }
        }

        LazyColumn {
            items(dataPaging.itemCount) { index ->
                val item = dataPaging[index]
                item?.let {
                    LocationItem(it)
                }
            }

            dataPaging.loadState.apply {
                when {
                    refresh is LoadStateNotLoading && dataPaging.itemCount < 1 -> {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No Items",
                                    color = black,
                                    modifier = Modifier.align(Alignment.Center),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    refresh is LoadStateLoading -> {
                        item {
                            LoaderShow()
                        }
                    }

                    append is LoadStateLoading -> {
                        item {
//              CircularProgressIndicator(
//                color = Color.Red,
//                modifier = Modifier.fillMaxWidth(1f)
//                  .padding(20.dp)®
//                  .wrapContentWidth(Alignment.CenterHorizontally)
//              )
                            LoaderShow()
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
                    append is LoadStateError -> {
                        item {
                            ErrorItem(
                                message = "No Internet Connection",
                                onClickRetry = { dataPaging.retry() },
                            )
                        }
                    }
                }
            }
        }

    }

}

@Composable
fun LocationItem(data: LocationDetailModelDomain) {
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
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = data.type, // Gunakan string resource atau data dinamis sesuai kebutuhan
                    color = Color.Black,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = data.dimension,
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
                    text = "Detail",
                    fontSize = 16.sp,
                    color = abuabumuda
                )
            }
        }
    }
}