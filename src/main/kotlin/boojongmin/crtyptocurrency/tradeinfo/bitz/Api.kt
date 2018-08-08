package boojongmin.crtyptocurrency.tradeinfo.bitz

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
data class Ticker(val code: Int, val msg: String, val data: TickerData)

fun Ticker.getTickerModel(digit: Int) = TickerModel(this.data.last, this.data.vol, "%.${digit}f".format(this.data.last), "%f".format(this.data.vol))

data class TickerData(val date: Long, val last: Double, val buy: Double, val sell: Double, val high: Double, val low: Double, val vol: Double)

// https://www.bit-z.pro/api.html
@Component
class BitzApi {
    val webClient: WebClient = WebClient.builder()
            .baseUrl("https://api.bit-z.com")
            .defaultHeaders {
                it.set(HttpHeaders.ACCEPT, "application/json")
            }.build()

    fun `ticker`(symbol: String, symbol2: String): Ticker {
        return webClient.get().uri("/api_v1/ticker?coin=${symbol.toLowerCase()}_${symbol2.toLowerCase()}")
                .retrieve().bodyToMono(bitzTypeRef<Ticker>())
                .block()!!
    }

    fun tickerModel(): Pair<String, USDTTickerModel> {
        var ply_btc = ticker("PLY", "BTC").getTickerModel(8)
        val btc_usdt = ticker("BTC", "USDT").getTickerModel(0)
        val eth_usdt = ticker("ETH", "USDT").getTickerModel(0)
        return Pair("bitz", USDTTickerModel(ply_btc, null, btc_usdt, eth_usdt))
    }
}