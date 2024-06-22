package domain.location

import app.cash.paging.PagingData
import domain.location.model.network.LocationDetailModelDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import util.RequestState

interface LocationDomainUseCase {
  fun getLocationPaging(
    scope: CoroutineScope,
    name: String,
    type: String,
    dimension: String): Flow<PagingData<LocationDetailModelDomain>>

  fun getDetailLocation(
    locationId: Int
  ): Flow<RequestState<LocationDetailModelDomain>>
}