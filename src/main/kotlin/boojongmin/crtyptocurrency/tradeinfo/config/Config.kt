package boojongmin.crtyptocurrency.tradeinfo.config

import boojongmin.cobinhood.CobinhoodApi
import boojongmin.crtyptocurrency.tradeinfo.bitz.BitzApi
import boojongmin.crtyptocurrency.tradeinfo.coinone.CoinoneApi
import boojongmin.crtyptocurrency.tradeinfo.gateio.GateIoApi
import boojongmin.crtyptocurrency.tradeinfo.lbank.LbankApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiConfiguration {
    @Bean
    fun bitz() = BitzApi()

    @Bean
    fun cobinhood() = CobinhoodApi()

    @Bean
    fun gateio() = GateIoApi()

    @Bean
    fun lbank() = LbankApi()

    @Bean
    fun coinone() = CoinoneApi()
}