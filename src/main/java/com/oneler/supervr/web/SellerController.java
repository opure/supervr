package com.oneler.supervr.web;

import okhttp3.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * /**
 * if(respText == "fb:ok"){
 * alert("出售成功！");
 * PSHref('v2_nav_MemberTradingHall.ph');
 * $("#sell_number").val("");
 * $("#sell_password").val("");
 * $("#sell_submit").removeAttr("disabled");
 * <p>
 * }else if(respText == "fb:passwordError"){
 * alert("安全密码错误！");
 * $("#sell_password").val("");
 * $("#sell_submit").removeAttr("disabled");
 * <p>
 * }else if(respText == "fb:VRCCountRemaind"){
 * alert("抱歉您出售的数量已经大于今日最高交易数量！");
 * $("#sell_password").val("");
 * $("#sell_submit").removeAttr("disabled");
 * <p>
 * }else if(respText == "fb:VRCInputRemainder"){
 * alert("您已经圆满完成该账号的股票收益了，感谢您的支持！");
 * $("#sell_password").val("");
 * $("#sell_submit").removeAttr("disabled");
 * <p>
 * }else if(respText == "fb:fail"){
 * alert("抱歉，不符合出售规则！");
 * $("#sell_password").val("");
 * $("#sell_submit").removeAttr("disabled");
 * }else if(respText == "fb:StockQuantityGreaterThan"){
 * alert("VRC挂售数量必须大于等于10！");
 * $("#sell_password").val("");
 * $("#sell_submit").removeAttr("disabled");
 * }else if(respText == "fb:StockQuantityGreaterThan10"){
 * alert("VRC挂售数量必须是10的倍数！");
 * $("#sell_password").val("");
 * $("#sell_submit").removeAttr("disabled");
 * } else if(respText == "fb:todayCantSellStock") {
 * alert("今日交易量封顶，请明日挂售。");
 * $("#sell_password").val("");
 * $("#sell_submit").removeAttr("disabled");
 * } else if(respText == "fb:hasSellingStock") {
 * alert("您尚有股票还未回购完成，请稍后挂售。");
 * $("#sell_password").val("");
 * $("#sell_submit").removeAttr("disabled");
 * }  else if(respText == "fb:le_stock_for_sale"){
 * alert("对不起，今日股票挂售暂未开启或已达上限，请稍后或明天挂售！");
 * $("#purchase_number").val("");
 * $("#purchase_password").val("");
 * } else if(respText == "fb:SystemQueue"){
 * alert("系统处理挂售队列中！");
 * $("#sell_password").val("");
 * } else if(respText == "fb:SystemBusy") {
 * alert("系统挂售繁忙，请稍后！");
 * $("#sell_password").val("");
 * }
 * vr_run = false;
 * }
 * </script>
 */

@RestController
public class SellerController {

    OkHttpClient client = new OkHttpClient();

    StringBuffer str = new StringBuffer();

    private volatile boolean flag = false;


    String run(String url, String cookie) throws IOException {

        RequestBody body = new FormBody.Builder()
                .add("t", "flagDRStock")
                .add("password", "315849")
                .add("sell_number", "1000")
                .build();

        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .addHeader("cookie", cookie)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                str.append(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
            }
        });


        return str.toString();
    }

    @GetMapping("/hello")
    public String hello() {
        return "helloWorld";
    }

    @GetMapping(value = "/s")
    public void result(String cookie) throws IOException, InterruptedException {
        while (true) {
            try {
                final LocalDateTime d1 = LocalDateTime.parse("2017-11-05T08:59:59");
                if (LocalDateTime.now().isAfter(d1)) {
                    run("https://www.supervr.co/v2_nav_MemberTradingHall.ph?v=" + Math.random(), cookie);
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        while (true) {
            for (int i = 0; i < 5; i++) {
                run("https://www.supervr.co/v2_nav_MemberTradingHall.ph?v=" + Math.random(), cookie);
                Thread.sleep(200);
            }
            break;
        }
    }

}

