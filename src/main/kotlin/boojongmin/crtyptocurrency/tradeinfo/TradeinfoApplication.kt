package boojongmin.crtyptocurrency.tradeinfo

import boojongmin.crtyptocurrency.tradeinfo.bitz.BitzApi
import boojongmin.crtyptocurrency.tradeinfo.gateio.GateIoApi
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans

@SpringBootApplication
class TradeinfoApplication

fun main(args: Array<String>) {
    runApplication<TradeinfoApplication>(*args)
}
