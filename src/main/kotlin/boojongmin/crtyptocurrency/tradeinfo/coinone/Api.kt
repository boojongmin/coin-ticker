package boojongmin.crtyptocurrency.tradeinfo.coinone

import boojongmin.crtyptocurrency.tradeinfo.model.TickerModel
import boojongmin.crtyptocurrency.tradeinfo.model.USDTTickerModel
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import java.util.*

/**
 *  inline fun
 */
inline fun <reified T> bitzTypeRef() = object : ParameterizedTypeReference<T>() {}

fun main(args: Array<String>) {
    val a = 0.00004
    println("%.2f".format(a))
}

/**
 *  data class
 */
// { "result": "success", "volume": "1176.1690", "last": "7612000", "yesterday_last": "8012000", "timestamp": "1533688395", "yesterday_low": "7901000", "high": "7765000", "currency": "btc", "low": "7588000", "errorCode": "0", "yesterday_first": "8595000", "yesterday_volume": "15556024.4424", "yesterday_high": "9451000", "first": "8001000" }
data class Ticker(val result: String, val volume: Double, val last: Int, val yesterday_last: Int, val timestamp: Int, val yesterday_low: Int, val high: Int, val currency: String, val low: Int, val errorCode: Int, val yesterday_first: Int, val yesterday_volume: Double, val yesterday_high: Int, val first: Int)
fun Ticker.getTickerModel(digit: Int) = TickerModel(this.last.toDouble(), this.volume, "%.${digit}f".format(this.last.toDouble()), "%f".format(this.volume))

//https://doc.coinone.co.kr/#api-Public-Ticker
@Component
class CoinoneApi {
    val webClient: WebClient = WebClient.builder()
            .baseUrl("https://api.coinone.co.kr")
            .defaultHeaders {
                it.set(HttpHeaders.ACCEPT, "application/json")
            }.build()

    fun `ticker`(symbol: String): Ticker {
        return webClient.get().uri("/ticker/?currency=${symbol}")
                .retrieve().bodyToMono(bitzTypeRef<Ticker>())
                .block()!!
    }

    fun tickerModel(): Pair<TickerModel, TickerModel> {
        var btc_krw = ticker( "BTC").getTickerModel(0)
        val eth_krw = ticker("ETH").getTickerModel(0)
        return Pair(btc_krw, eth_krw)
    }
}