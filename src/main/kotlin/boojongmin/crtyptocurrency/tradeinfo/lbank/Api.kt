package boojongmin.crtyptocurrency.tradeinfo.lbank

import boojongmin.crtyptocurrency.tradeinfo.bitz.getTickerModel
import boojongmin.crtyptocurrency.tradeinfo.model.USDTTickerModel
import boojongmin.crtyptocurrency.tradeinfo.model.TickerModel
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

/**
 *  inline fun
 */
//inline fun <reified T> lbankTypeRef() = object : ParameterizedTypeReference<T>() {}


/**
 *  data class
 */
//{"symbol": "ply_eth", "ticker": {"change": 12.606534090909083, "high": 0.0004933, "latest": 0.00047565, "low": 0.00041232, "turnover": 4008.73800506005, "vol": 8996242.459900009}, "timestamp": 1533648196049}
data class Ticker(val symbol: String, val ticker: TickerDetail, var timestamp: Long)
fun Ticker.getTickerModel(digit: Int) = TickerModel(this.ticker.latest, this.ticker.vol, "%.${digit}f".format(this.ticker.latest), "%f".format(this.ticker.vol))

data class TickerDetail(val change: Double, val high: Double, val latest: Double, val low: Double, val turnover: Double, val vol: Double)

// https://github.com/LBank-exchange/lbank-official-api-docs
@Component
class LbankApi {
    val mapper: ObjectMapper = jacksonObjectMapper()
    val webClient: WebClient = WebClient.builder()
            .baseUrl("http://api.lbank.info")
            .defaultHeaders {
                it.set(HttpHeaders.CONTENT_TYPE, "application / x-www-form-urlencoded")
            }.build()

    fun `ticker`(symbol: String, symbol2: String): Ticker {
        val json = webClient.get().uri("/v1/ticker.do?symbol=${symbol.toLowerCase()}_${symbol2.toLowerCase()}")
                .retrieve().bodyToMono(String::class.java)
                .block()!!
        return mapper.readValue(json)
    }

    fun tickerModel(): Pair<String, USDTTickerModel> {
        var ply_eth = ticker("PLY", "ETH").getTickerModel(8)
        val btc_usdt = ticker("BTC", "USDT").getTickerModel(0)
        val eth_usdt = ticker("ETH", "USDT").getTickerModel(0)
        return Pair("lbank", USDTTickerModel(null, ply_eth, btc_usdt, eth_usdt))
    }
}