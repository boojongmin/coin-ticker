package boojongmin.crtyptocurrency.tradeinfo.model

data class TickerModel(val last: Double, val volumn: Double, val lastStr: String, val volumnStr: String)

data class USDTTickerModel(var ply_btc: TickerModel?, var ply_eth: TickerModel?, var btc_usdt: TickerModel, var eth_usdt: TickerModel)
data class KRWTickerModel(var ply_btc: TickerModel?, var ply_eth: TickerModel?, var btc_won: TickerModel, var eth_won: TickerModel)
