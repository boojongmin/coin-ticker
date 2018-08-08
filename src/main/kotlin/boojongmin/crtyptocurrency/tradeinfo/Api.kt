//package boojongmin.cobinhoodapi
//
//import org.springframework.core.ParameterizedTypeReference
//import org.springframework.http.HttpHeaders
//import org.springframework.stereotype.Component
//import org.springframework.web.reactive.function.client.WebClient
//
///**
// *  inline fun
// */
//inline fun <reified T> coinoneTypeRef() = object:ParameterizedTypeReference<T>(){}
//inline fun <reified T> lbankTypeRef() = object:ParameterizedTypeReference<T>(){}
//
///**
// *  data class
// */
//
//
//data class CoinOneTickerModel(
//        val result: String,
//        val volume: Double,
//        val last: Integer,
//        val yesterday_last: Integer,
//        val timestamp: Long,
//        val yesterday_low: Integer,
//        val high: Integer,
//        val currency: String,
//        val low: Integer,
//        val errorCode: Integer,
//        val yesterday_first: Integer,
//        val yesterday_volume: Double,
//        val yesterday_high: Integer,
//        val first: Integer
//)
//
//@Component
//class CoinoneApi {
//    val webClient: WebClient = WebClient.builder()
//            .baseUrl("https://api.coinone.co.kr")
//            .defaultHeader(HttpHeaders.ACCEPT, "application/json")
//            .build()
//
//    fun ticker(param: String): CoinOneTickerModel {
//        return webClient.get().uri("/cobinhoodTicker/?currency=${param}")
//                .retrieve().bodyToMono(CoinOneTickerModel::class.java)
//                .block()!!
//    }
//
//    fun orderbook(param: String): Map<*, *> {
//        return webClient.get().uri("/orderbook/?currency=${param}")
//                .retrieve().bodyToMono(coinoneTypeRef<Map<*,*>>())
//                .block()!!
//    }
//}
//
