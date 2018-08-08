package boojongmin.crtyptocurrency.tradeinfo.gateio

import boojongmin.crtyptocurrency.tradeinfo.model.TickerModel
import boojongmin.crtyptocurrency.tradeinfo.model.USDTTickerModel
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

/**
 *  inline fun
 */
inline fun <reified T> gateIoTypeRef() = object : ParameterizedTypeReference<T>() {}


/**
 *  data class
 */
//{"quoteVolume":"2434.3254839","baseVolume":"1.320248400044115","highestBid":"0.000373","high24hr":"0.000767","last":"0.0005","lowestAsk":"0.000495","elapsed":"4000ms","result":"true","low24hr":"0.0005","percentChange":"-34.725848563969"}
data class Ticker(val quoteVolume: Double, val baseVolume: Double, val highestBid: Double, val high24hr: Double, val last: Double, val lowestAsk: Double, val elapsed: String, val result: Boolean, val low24hr: Double, val percentChange: Double)

fun Ticker.getTickerModel(digit: Int) = TickerModel(this.last, this.baseVolume, "%.${digit}f".format(this.last), "%f".format(this.baseVolume))

// https://gate.io/api2
@Component
class GateIoApi {
    val webClient: WebClient = WebClient.builder()
            .baseUrl("https://data.gate.io/")
            .defaultHeaders {
                it.set(HttpHeaders.ACCEPT, "application/json")
            }.build()

    fun `ticker`(symbol: String, symbol2: String): Ticker {
        val url = "/api2/1/ticker/${symbol.toLowerCase()}_${symbol2.toLowerCase()}"
        return webClient.get().uri(url)
                .retrieve().bodyToMono(gateIoTypeRef<Ticker>())
                .block()!!
    }

    fun tickerModel(): Pair<String, USDTTickerModel> {
        var ply_eth = ticker("PLY", "ETH").getTickerModel(8)
        val btc_usdt = ticker("BTC", "USDT").getTickerModel(0)
        val eth_usdt = ticker("ETH", "USDT").getTickerModel(0)
        return Pair("gateio", USDTTickerModel(null, ply_eth, btc_usdt, eth_usdt))
    }
}