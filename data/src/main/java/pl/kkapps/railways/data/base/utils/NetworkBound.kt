package pl.kkapps.railways.data.base.utils

import kotlinx.coroutines.flow.flow

inline fun <ResultType, RemoteResult> networkBound(
    crossinline localQuery: suspend () -> ResultType,
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
    crossinline remoteFetch: suspend () -> RemoteResult,
    crossinline saveRemoteResult: suspend (RemoteResult) -> Unit,
    crossinline onError: (Throwable) -> Unit,
) = flow {
    val data = localQuery()
    emit(data)
    if (shouldFetch(data)) {
        try {
            val remoteResult = remoteFetch()
            saveRemoteResult(remoteResult)
            emit(localQuery())
        } catch (throwable: Throwable) {
            onError(throwable)
        }
    }
}