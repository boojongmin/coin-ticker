package boojongmin.crtyptocurrency.tradeinfo.cobinhood

import boojongmin.cobinhood.CobinhoodApi
import boojongmin.cobinhood.CobinhoodService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import java.time.OffsetDateTime

@RunWith(SpringRunner::class)
@Import(CobinhoodApi::class)
class CobinhoodApiTest {
    @Autowired
    lateinit var api: CobinhoodApi

    @Test
    fun `test BTC`() {
        val btc = api.ticker("PLY", "BTC")
        val t = btc.ticker

        println("%f%n".format(t.highest_bid))
        println("%f%n".format(t.last_trade_price))
    }

    @Test
    fun `test ETH`() {
        val btc = api.ticker("PLY", "ETH")
        val t = btc.ticker

        println("%f".format(t.highest_bid))
        println("%f".format(t.last_trade_price))
    }

    @Test
    fun `test trades`() {
        val ply = api.trades("PLY", "BTC")
        println(ply)
    }

    @Test
    fun `test orderbooks`() {
        val ply = api.orderbooks("PLY", "BTC")
        println(ply)

        println("""${ply.orderbook.bids.map { it[2].toDouble() }}""")

//        val bidsTotal = ply.orderbook.asks.map { it[2].toDouble() }.reduce { x, y -> x + y  }
//        println(bidsTotal)
    }


    //    @Test
//    fun `test tradingPairs`() {
//        val ply = api.tradingPaires()
//
//        println(ply)
//    }
//
    @Test
    fun `test chart`() {
        val endTime = OffsetDateTime.now().toEpochSecond() * 1000
        val startTime = endTime - (1000 * 60 * 60) // 1hour
        println("""${startTime} - ${endTime}""")

        val ply = api.chart("PLY", startTime, endTime, "1h")
        println(ply.candles)
    }
}


@RunWith(SpringRunner::class)
@Import(CobinhoodService::class, CobinhoodApi::class)
class CobinhoodSerciceTest {
    @Autowired
    lateinit var service: CobinhoodService

    @Test
    fun `test bids`() {
        val ply = service.lastTradePrice("PLY", "BTC")
        println("%f%n".format(ply))
    }

    @Test
    fun `test asksAverage`() {
        val ply = service.asksAverage("PLY", "BTC")
        println("%f%n".format(ply))
    }

    @Test
    fun `test bidsAverage`() {
        val ply = service.bidsAverage("PLY", "BTC")
        println("%f%n".format(ply))
    }

}

