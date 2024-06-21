package presentation.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons.AutoMirrored
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import domain.character.model.network.CharacterDetailModelDomain
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.painterResource
import presentation.theme.biru
import presentation.theme.black
import presentation.theme.merah
import presentation.theme.white
import rickandmortyapp.composeapp.generated.resources.Res.drawable
import rickandmortyapp.composeapp.generated.resources.favorite_full
import rickandmortyapp.composeapp.generated.resources.favorite_outline

@Composable
fun NonlazyGrid(
  columns: Int,
  itemCount: Int,
  modifier: Modifier = Modifier,
  content: @Composable (Int) -> Unit
) {
  Column(modifier = modifier) {
    var rows = (itemCount / columns)
    if (itemCount.mod(columns) > 0) {
      rows += 1
    }

    for (rowId in 0 until rows) {
      val firstIndex = rowId * columns

      Row {
        for (columnId in 0 until columns) {
          val index = firstIndex + columnId
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .weight(1f)
          ) {
            if (index < itemCount) {
              content(index)
            }
          }
        }
      }
    }
  }
}

@Composable
fun CharacterItem(
  data: CharacterDetailModelDomain,
  navigateToCharacterDetailScreen: (Int) -> Unit
) {
  Card(
    modifier = Modifier.fillMaxWidth().height(210.dp).padding(5.dp).clickable {
      navigateToCharacterDetailScreen(data.id)
    },
    shape = RoundedCornerShape(12.dp),
    elevation = CardDefaults.cardElevation(5.dp),
    colors = CardDefaults.cardColors(containerColor = white)
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(15.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      KamelImage(
        resource = asyncPainterResource(data = data.image),
        contentDescription = null,
        modifier = Modifier
          .size(100.dp)
          .padding(bottom = 15.dp),
        contentScale = ContentScale.Fit,
      )

      Text(
        text = data.name,
        fontSize = 20.sp,
        color = black,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 15.dp)
      )

      Text(
        text = data.gender,
        fontSize = 18.sp,
        color = Color.Gray,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 15.dp, vertical = 10.dp)
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
  value: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  placeholderText: String,
) {
  OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 15.dp),
    colors = TextFieldDefaults.outlinedTextFieldColors(
      focusedBorderColor = black,
      unfocusedBorderColor = black,
    ),
    singleLine = true,
    maxLines = 1,
    keyboardOptions = KeyboardOptions(
      imeAction = ImeAction.Search
    ),
    leadingIcon = {
      Icon(
        imageVector = Filled.Search,
        contentDescription = null
      )
    },
    shape = RoundedCornerShape(18.dp),
    placeholder = {
      Text(text = placeholderText)
    }
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownGender(
  placeholderText: String,
  listData: List<String>,
  onGenderSelected: (String) -> Unit
) {
  var selectedGender by remember { mutableStateOf("") }
  var isExpanded by remember { mutableStateOf(false) }

  ExposedDropdownMenuBox(
    expanded = isExpanded,
    onExpandedChange = { isExpanded = !isExpanded }
  ) {
    TextField(
      placeholder = {
        Text(placeholderText)
      },
      value = selectedGender,
      onValueChange = {},
      readOnly = true,
      trailingIcon = {
        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
      },
      modifier = Modifier.menuAnchor()
    )

    ExposedDropdownMenu(
      expanded = isExpanded,
      onDismissRequest = {
        isExpanded = false
      }
    ) {
      listData.forEach { gender ->
        DropdownMenuItem(
          text = { Text(text = gender) },
          onClick = {
            selectedGender = gender
            isExpanded = false
            onGenderSelected(gender)
          },
          contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
        )
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAppBar(title: String, useIconBack: Boolean, navController: NavController? = null) {
  Surface(
    shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp),
  ) {
    CenterAlignedTopAppBar(
      colors = TopAppBarColors(
        containerColor = biru,
        scrolledContainerColor = biru,
        navigationIconContentColor = white,
        titleContentColor = white,
        actionIconContentColor = white
      ),
      title = {
        Text(title)
      },
      navigationIcon = {
        if(useIconBack && navController != null){
          IconButton(
            onClick = {
            navController.navigateUp()
            }
          ) {
            Icon(imageVector = AutoMirrored.Filled.ArrowBack, contentDescription = null)
          }
        }
      }
    )
  }
}

@Composable
fun ColumnScope.DefaultFavoriteCard(
  isFavorite: Boolean,
  changeFavorite: (Boolean) -> Unit
) {
  Card(
    modifier = Modifier.align(Alignment.End).padding(end = 20.dp),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = white
    )
  ) {
    Row(
      modifier = Modifier.padding(15.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text("Favorite", color = black, fontSize = 20.sp)
      Spacer(modifier = Modifier.width(10.dp))
      if (isFavorite) {
        IconButton(
          onClick = {
            changeFavorite(isFavorite.not())
          },
        ) {
          Icon(
            painter = painterResource(drawable.favorite_full),
            contentDescription = null,
            modifier = Modifier.size(30.dp),
            tint = merah
          )
        }
      } else {
        IconButton(
          onClick = {
            changeFavorite(isFavorite.not())
          },
        ) {
          Icon(
            painter = painterResource(drawable.favorite_outline),
            contentDescription = null,
            modifier = Modifier.size(30.dp),
            tint = merah
          )
        }
      }
    }
  }
}