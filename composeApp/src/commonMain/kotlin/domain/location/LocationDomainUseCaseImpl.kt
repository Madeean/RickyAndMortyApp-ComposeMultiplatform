package domain.location

import app.cash.paging.PagingData
import domain.location.model.network.LocationDetailModelDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import util.RequestState

class LocationDomainUseCaseImpl(
  private val repository: LocationDomainRepository
): LocationDomainUseCase {
  override fun getLocationPaging(
    scope: CoroutineScope,
    name: String,
    type: String,
    dimension: String
  ): Flow<PagingData<LocationDetailModelDomain>> {
    return repository.getLocationPaging(scope, name, type, dimension)
  }

  override fun getDetailLocation(locationId: Int): Flow<RequestState<LocationDetailModelDomain>> {
    return repository.getDetailLocation(locationId)
  }

}