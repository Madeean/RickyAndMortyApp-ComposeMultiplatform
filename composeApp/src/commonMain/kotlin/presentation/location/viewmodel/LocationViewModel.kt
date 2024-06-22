package presentation.location.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.PagingData
import domain.location.LocationDomainUseCase
import domain.location.model.network.LocationDetailModelDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import util.RequestState

class LocationViewModel(private val useCase: LocationDomainUseCase) : ViewModel() {
  private var _location: MutableStateFlow<PagingData<LocationDetailModelDomain>> =
    MutableStateFlow(PagingData.empty())
  val location: StateFlow<PagingData<LocationDetailModelDomain>> = _location

  private var _detailLocation: MutableStateFlow<RequestState<LocationDetailModelDomain>> =
    MutableStateFlow(RequestState.Idle)
  val detailLocation: StateFlow<RequestState<LocationDetailModelDomain>> = _detailLocation


  fun getLocationPaging(name:String = "", type:String = "", dimension:String = ""){
    viewModelScope.launch {
      useCase.getLocationPaging(viewModelScope, name,type,dimension).collectLatest { it ->
        _location.value = it
      }
    }
  }

  fun getDetailLocation(locationId: Int){
    viewModelScope.launch {
      useCase.getDetailLocation(locationId).collect{
        _detailLocation.value = it
      }
    }
  }
}