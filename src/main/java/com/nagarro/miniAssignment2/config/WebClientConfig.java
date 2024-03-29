package com.nagarro.miniAssignment2.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClientRandomUser(WebClient.Builder builder) {
        return createWebClient(builder, "https://randomuser.me", 2000);
    }

    @Bean
    public WebClient webClientNationalize(WebClient.Builder builder) {
        return createWebClient(builder, "https://api.nationalize.io", 1000);
    }

    @Bean
    public WebClient webClientGenderize(WebClient.Builder builder) {
        return createWebClient(builder, "https://api.genderize.io", 1000);
    }

    private WebClient createWebClient(WebClient.Builder builder, String baseUrl, int timeoutMillis) {
        TcpClient tcpClient = TcpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeoutMillis)
            .doOnConnected(connection ->
                connection.addHandlerLast(new ReadTimeoutHandler(timeoutMillis, TimeUnit.MILLISECONDS))
                          .addHandlerLast(new WriteTimeoutHandler(timeoutMillis, TimeUnit.MILLISECONDS)));

        return builder.baseUrl(baseUrl)
                      .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                      .build();
    }
}
