package boojongmin.crtyptocurrency.tradeinfo.coinone

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@Import(CoinoneApi::class)
class CoinoneApiTest {
    @Autowired
    lateinit var api: CoinoneApi

    @Test
    fun `ticker BTC`() {
        val btc = api.ticker("BTC")
        println(btc)
    }

    @Test
    fun `ticker ETH`() {
        val btc = api.ticker("ETH")
        println(btc)
    }
}

