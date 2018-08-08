//package boojongmin.cobinhoodapi
//
//import boojongmin.cobinhoodapi.TickerModel.PlyBtcEthWon
//import org.springframework.stereotype.Service
//import reactor.util.function.Tuple2
//
//@Service
//class Service(val api: Api) {
//
//    fun cobinhoodTicker(): PlyBtcEthWon {
//
//        val (plyBtc) = api.`PLY-BTC`()
//        val (plyEth) = api.`PLY-ETH`()
//        var (btcUsdt) = api.`BTC-USDT`()
//        val (ethUsdt) = api.`ETH-USDT`()
//        val dollar = 1000
//
//        val btcWon = btcUsdt.lowest_ask * 1000
//        val ethWon = ethUsdt.lowest_ask * 1000
//        val plyBtcWon = btcUsdt.lowest_ask * dollar * plyBtc.lowest_ask
//        val plyEthWon = ethUsdt.lowest_ask * dollar * plyEth.lowest_ask
//
//        return PlyBtcEthWon(btcWon, ethWon, plyBtcWon, plyEthWon)
//    }
//
//    fun coinOne(): MutableMap<String, MutableMap<String, Any>> {
//
//        val (plyBtc) = api.`PLY-BTC`()
//        val (plyEth) = api.`PLY-ETH`()
//        var (btcUsdt) = api.`BTC-USDT`()
//        val (ethUsdt) = api.`ETH-USDT`()
//        val coinoneBTC = api.`cobinhoodTicker`("BTC")
//        val coinoneETH = api.`cobinhoodTicker`("ETH")
//        val coinCount = 27228
//
//        val coinOneBtcWon = coinCount * plyBtc.lowest_ask * coinoneBTC.last.toDouble()
//        val coinOneEthWon = coinCount * plyEth.lowest_ask * coinoneETH.last.toDouble()
//        val plyToBtcCount = coinCount * plyBtc.lowest_ask
//        val plyToEthCount = coinCount * plyEth.lowest_ask
//
//        val calc = mutableMapOf<String, Any>(
//                "coinOneBtcWon" to coinOneBtcWon.toInt(),
//                "coinOneEthWon" to coinOneEthWon.toInt(),
//                "plyToBtcCount" to plyToBtcCount.toInt(),
//                "plyToEthCount" to plyToEthCount.toInt(),
//                "coinCount" to 27228,
//                "plyBtc" to plyBtc.lowest_ask,
//                "plyEth" to plyEth.lowest_ask
//        )
//        val info = mutableMapOf<String, Any>(
//                "PLY-BTC" to plyBtc ,
//                "PLY-ETH" to plyEth ,
//                "BTC-USDT" to btcUsdt ,
//                "ETH-USDT" to ethUsdt ,
//                "COINONE-BTC" to coinoneBTC,
//                "COINONE-ETH" to coinoneETH
//                )
//        val map = mutableMapOf("calc" to calc, "info" to info)
//        return map
//    }
//}