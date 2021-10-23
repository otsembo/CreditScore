package com.ian.clearscoreinterview.common


//class to handle data retrieval from endpoints
sealed class Resource<T> (val data:T? = null, val message:String? = null){

    //successful retrieval
    class Success<T>(data: T) : Resource<T>(data)
    //error when loading
    class Error<T>(message: String?, data: T? = null) : Resource<T>(data, message)
    //please wait as we load
    class Loading<T>(data: T? = null) : Resource<T>(data)

}
