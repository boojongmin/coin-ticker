package boojongmin.crtyptocurrency.tradeinfo

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Service
//@EnableScheduling
@Configuration
class ScheduledService {

//    @Bean
//    fun aaa(): ExecutorService {
//        var newFixedThreadPool = Executors.newFixedThreadPool(10)
//        return newFixedThreadPool
//    }

//    val logger = LoggerFactory.getLogger("")
//    @Scheduled(fixedDelay = 1000)
//    fun a() {
//        logger.error("asdf")
//    }
}