package boojongmin.crtyptocurrency.tradeinfo.service

import boojongmin.crtyptocurrency.tradeinfo.config.ApiConfiguration
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@Import(ApiConfiguration::class, TickerService::class)
class LbankApiTest {
    @Autowired
    lateinit var tickerService: TickerService

    @Test
    fun `facade`() {
        val result = tickerService.facade()
        println(result)
    }

    @Test
    fun `facadePretty`() {
        val result = tickerService.facadePretty(10000.0)
        println(result)
    }

}
