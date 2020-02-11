package com.gate.zml.filter;

import com.gate.zml.jwt.JwtUtil;
import io.jsonwebtoken.SignatureException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

public class JwtCheckGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtCheckGatewayFilterFactory.Config> {

    public JwtCheckGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String jwtToken = exchange.getRequest().getHeaders().getFirst("Authorization");
//            String path = exchange.getRequest().getURI().getPath();

            //Jwt合法（继续传递请求)
//            if (jwtToken != null || path.equalsIgnoreCase(""))
            if (jwtToken != null) {
                try {
                    Map<String, String> map = JwtUtil.validateToken(jwtToken);
                    if (map != null) {
                        String username = map.get("userId");
                        if (username != null) {
                            System.out.println("-------------jwtToken认证成功------------" + username);
                            //使用redias进行一个缓存操作

                            //redias缓存操作里面没有则使用数据库

                            return chain.filter(exchange);
                        }
                    }

                } catch (SignatureException signatureException) {


                }


            }

            //Jwt不合法(响应未登录的异常)
            ServerHttpResponse response = exchange.getResponse();
            //设置headers
            HttpHeaders httpHeaders = response.getHeaders();
            httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
            httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            //设置body
            String warningStr = "{\"code\":\"00000001\",\n" +
                    "\n" +
                    "\"message\":\"未登录或登录超时\"\n" +
                    "}";
            DataBuffer bodyDataBuffer = response.bufferFactory().wrap(warningStr.getBytes());
            return response.writeWith(Mono.just(bodyDataBuffer));
        };
    }

    public static class Config {
        //Put the configuration properties for your filter here
    }
}
