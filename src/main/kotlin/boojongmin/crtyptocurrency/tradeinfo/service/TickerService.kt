package boojongmin.crtyptocurrency.tradeinfo.service

import boojongmin.cobinhood.CobinhoodApi
import boojongmin.crtyptocurrency.tradeinfo.bitz.BitzApi
import boojongmin.crtyptocurrency.tradeinfo.coinone.CoinoneApi
import boojongmin.crtyptocurrency.tradeinfo.gateio.GateIoApi
import boojongmin.crtyptocurrency.tradeinfo.lbank.LbankApi
import boojongmin.crtyptocurrency.tradeinfo.model.USDTTickerModel
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class TickerService(val bitzApi: BitzApi,
                    val cobinhoodApi: CobinhoodApi,
                    val gateIoApi: GateIoApi,
                    val lbankApi: LbankApi,
                    val coinoneApi: CoinoneApi) {
    fun facade(): Map<String, USDTTickerModel> {
        val bitz = bitzApi.tickerModel()
        val cobinhood = cobinhoodApi.tickerModel()
//        val gateIo = gateIoApi.tickerModel()
        val lbank = lbankApi.tickerModel()
        return mapOf(bitz, cobinhood, lbank)
//        return mapOf(bitz, cobinhood, gateIo, lbank)
    }

    fun facadePretty(amount: Double): String {
        val (btc_krw, eth_krw) = coinoneApi.tickerModel()

        val result = facade().map {
            val btcUsdt = (it.value.ply_btc?.last ?: 0.0) * amount * it.value.btc_usdt.last
            val ethUsdt = (it.value.ply_eth?.last ?: 0.0) * amount * it.value.eth_usdt.last
            val btcKrw = (it.value.ply_btc?.last ?: 0.0) * amount * btc_krw.last
            val ethKrw = (it.value.ply_eth?.last ?: 0.0) * amount * eth_krw.last

            """--------------[${it.key}]----------------
                |ply->btc: ${if(it.value.ply_btc?.lastStr == null) "X" else  it.value.ply_btc!!.lastStr + "비트코인"}
                |ply->eth: ${if(it.value.ply_eth?.lastStr == null) "X" else  it.value.ply_eth!!.lastStr + "이더"}
                |
                """.trimMargin() +
//"""
//                |> btc_usdt: ${it.value.btc_usdt?.lastStr}
//                |> eth_usdt: ${it.value.eth_usdt?.lastStr}
//                |>> calc ply_usdt: ${ if(btcUsdt == 0.0) "X" else "%.0f 달러".format(btcUsdt)}
//               |>> calc ply_usdt: ${ if(ethUsdt == 0.0) "X" else "%.0f 달러".format(ethUsdt)}
//""" +
                """
                |ply->btc->krw: ${ if(btcKrw == 0.0) "X" else "%,.0f 원".format(btcKrw)}
                |ply->eth->krw: ${ if(ethKrw == 0.0) "X" else "%,.0f 원".format(ethKrw)}
                |
            """.trimMargin()
        }.reduce({ a, b -> a + b })

        return "${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))} 10000PLY 시세 정보(KRW by 코인원)\n" +result
    }

}
