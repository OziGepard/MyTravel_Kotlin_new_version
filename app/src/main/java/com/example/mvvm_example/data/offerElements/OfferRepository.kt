package com.example.mvvm_example.data.offerElements


class OfferRepository private constructor(private val offerDao: OfferDao) {

    fun getOffers(callback: FirebaseCallbackOffer, city: String, country: String) = offerDao.getOffers(callback, city, country)

    companion object{
        @Volatile private var instance : OfferRepository? = null

        fun getInstance(offerDao: OfferDao) =
            instance ?: synchronized(this){
                instance ?: OfferRepository(offerDao).also { instance = it }
            }
    }
}