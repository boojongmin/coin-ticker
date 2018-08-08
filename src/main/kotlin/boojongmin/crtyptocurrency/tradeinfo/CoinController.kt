//package boojongmin.cobinhoodapi
//
//import org.springframework.stereotype.Controller
//import org.springframework.ui.Model
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.ResponseBody
//
//@Controller
//@RequestMapping("/coin")
//class CoinController(val service: Service) {
//
//    @RequestMapping("/won")
//    fun hello(TickerModel: Model): String {
//        TickerModel.addAttribute("TickerModel", service.cobinhoodTicker())
//        return "won"
//    }
//
//    @RequestMapping("/json")
//    @ResponseBody
//    fun json(): MutableMap<String, MutableMap<String, Any>> {
//        return service.coinOne()
//    }
//
//    @GetMapping("/json.html")
//    fun json2(TickerModel: Model): String {
//        TickerModel.addAttribute("TickerModel", service.coinOne())
//        return "json"
//    }
//
//}