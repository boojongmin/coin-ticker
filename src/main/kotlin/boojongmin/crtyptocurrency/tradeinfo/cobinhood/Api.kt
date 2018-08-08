package boojongmin.cobinhood

import boojongmin.crtyptocurrency.tradeinfo.bitz.getTickerModel
import boojongmin.crtyptocurrency.tradeinfo.model.USDTTickerModel
import boojongmin.crtyptocurrency.tradeinfo.model.TickerModel
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import java.util.*

/**
 *  inline fun
 */
inline fun <reified T> cobinhoodTypeRef() = object : ParameterizedTypeReference<T>() {}


/**
 *  data class
 */

data class ApiModel<T>(val success: Boolean, val result: T)

data class Ticker(val ticker: TickerDetail)

fun Ticker.getTickerModel(digit: Int) = TickerModel(this.ticker.last_trade_price, this.ticker.`24h_volume`, "%.${digit}f".format(this.ticker.last_trade_price), "%f".format(this.ticker.`24h_volume`))
data class TickerDetail(val trading_pair_id: String,
                        val timestamp: Date,
                        val `24h_high`: Double,
                        val `24h_low`: Double,
                        val `24h_open`: Double,
                        val `24h_volume`: Double,
                        val last_trade_price: Double,
                        val highest_bid: Double,
                        val lowest_ask: Double)

data class Trade(val limit: Int, val page: Int, val total_page: Int, val trades: List<TradeDetail>)
data class TradeDetail(val id: String, val maker_side: String, val timestamp: Long, val price: String, val size: String)

data class OrderBook(val orderbook: OrderBookDetail)
data class OrderBookDetail(val sequence: Int, val bids: Array<Array<String>>, val asks: Array<Array<String>>)


data class Chart(val candles: List<ChartDetail>)
data class ChartDetail(val timeframe: String, val trading_pair_id: String, val timestamp: Long,
                       val volume: String, val open: String, val close: String, val high: String, val low: String)


// referred https://cobinhood.github.io/api-public/
@Component
class CobinhoodApi {
    val webClient: WebClient = WebClient.builder()
            .baseUrl("https://api.cobinhood.com")
            .defaultHeaders {
                it.set(HttpHeaders.ACCEPT, "application/json")
            }.build()


    fun `ticker`(symbol: String, symbol2: String): Ticker {
        return webClient.get().uri("/v1/market/tickers/${symbol}-${symbol2}")
                .retrieve().bodyToMono(cobinhoodTypeRef<ApiModel<Ticker>>())
                .block()!!.result
    }

    fun tickerModel(): Pair<String, USDTTickerModel> {
        var ply_btc = ticker("PLY", "BTC").getTickerModel(8)
        var ply_eth = ticker("PLY", "ETH").getTickerModel(8)
        val btc_usdt = ticker("BTC", "USDT").getTickerModel(0)
        val eth_usdt = ticker("ETH", "USDT").getTickerModel(0)
        return Pair("cobinhood", USDTTickerModel(ply_btc, ply_eth, btc_usdt, eth_usdt))
    }

    fun `trades`(symbol1: String, symbol2: String): Trade {
        return webClient.get().uri("/v1/market/trades/${symbol1}-${symbol2}")
                .retrieve().bodyToMono(cobinhoodTypeRef<ApiModel<Trade>>()).block()!!.result
    }

    fun `orderbooks`(symbol1: String, symbol2: String): OrderBook {
        return webClient.get().uri("/v1/market/orderbooks/${symbol1}-${symbol2}")
                .retrieve().bodyToMono(cobinhoodTypeRef<ApiModel<OrderBook>>()).block()!!.result
    }

//    fun `tradingPaires`(): String {
//        return webClient.get().uri("/v1/market/trading_pairs")
//                .retrieve().bodyToMono(String::class.java).block()!!
//    }

    // 1m, 5m, 15m, 30m, 1h, 3h, 6h, 12h, 1D, 7D, 14D, 1M
    fun chart(symbol: String, startTime: Long, endTime: Long, timeframe: String): Chart {
        val url = "/v1/chart/candles/${symbol}-BTC?startTime=${startTime}&endTime=${endTime}&timeframe=${timeframe}"
        return webClient.get().uri(url)
                .retrieve().bodyToMono(cobinhoodTypeRef<ApiModel<Chart>>()).block()!!.result
    }
//
//    fun `volume`(currencyId: String, startTime: Long, endTime: Long): Map<*, *> {
//        return webClient.get().uri("/v1/trading/volume?currency_id=${currencyId}&startTime=${startTime}&endTime=${endTime}")
//                .retrieve().bodyToMono(cobinhoodTypeRef<Map<*, *>>()).block()!!.result
//    }

//    fun `ETH-USDT`(): Ticker {
//        return webClient.get().uri("/v1/market/tickers/ETH-USDT")
//                .retrieve().bodyToMono(object:ParameterizedTypeReference<ApiModel<Ticker>>(){})
//                .block()!!.result
//    }
//
//    fun `BTC-USDT`(): Ticker {
//        return webClient.get().uri("https://api.cobinhood.com/v1/market/tickers/BTC-USDT")
//                .retrieve().bodyToMono(object:ParameterizedTypeReference<ApiModel<Ticker>>(){})
//                .block()!!.result
//    }

}

@Service
class CobinhoodService(val api: CobinhoodApi) {

    fun lastTradePrice(symbol1: String, symbol2: String): Double {
        val ply = api.ticker(symbol1, symbol2)
        return ply.ticker.last_trade_price
    }

    fun bidsAverage(symbol1: String, symbol2: String): Double {
        val ply = api.orderbooks(symbol1, symbol2)
        val bidsCount = ply.orderbook.bids.map { it[2].toDouble() }.reduce { x, y -> x + y }
        val bidsTotal = ply.orderbook.bids.map { it[0].toDouble() * it[2].toDouble() }.reduce { x, y -> x + y }
        return bidsTotal / bidsCount
    }

    fun asksAverage(symbol1: String, symbol2: String): Double {
        val ply = api.orderbooks(symbol1, symbol2)
        val asksCount = ply.orderbook.asks.map { it[2].toDouble() }.reduce { x, y -> x + y }
        val asksTotal = ply.orderbook.asks.map { it[0].toDouble() * it[2].toDouble() }.reduce { x, y -> x + y }
        return asksTotal / asksCount
    }

}













