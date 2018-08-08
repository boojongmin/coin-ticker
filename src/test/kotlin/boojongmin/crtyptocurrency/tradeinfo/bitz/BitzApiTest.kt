package boojongmin.crtyptocurrency.tradeinfo.bitz

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@Import(BitzApi::class)
class BitzApiTest {
    @Autowired
    lateinit var api: BitzApi

    @Test
    fun `ticker PLY BTC`() {
        val btc = api.ticker("PLY", "BTC")
        println(btc)
    }

    @Test
    fun `ticker BTC USD`() {
        val btc = api.ticker("BTC", "USDT")
        println(btc)
    }

    @Test
    fun `test tickerModel`() {
        var result = api.tickerModel()
        print(result)
    }
}
