package boojongmin.crtyptocurrency.tradeinfo.lbank

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@Import(LbankApi::class)
class LbankApiTest {
    @Autowired
    lateinit var api: LbankApi

    @Test
    fun `ticker PLY ETH`() {
        val btc = api.ticker("PLY", "ETH")
        println(btc)
    }

    @Test
    fun `ticker BTC USD`() {
        val btc = api.ticker("BTC", "USDT")
        println(btc)
    }
}
