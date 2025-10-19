//package com.github.monkeywie.proxyee;
//
//import org.springframework.web.reactive.socket.WebSocketMessage;
//import org.springframework.web.reactive.socket.client.WebSocketClient;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.net.URI;
//import java.util.HashMap;
//import java.util.Map;
//
//public class WebSocketClientExample {
//
//    private final WebSocketClient webSocketClient;
//
//    public WebSocketClientExample(WebSocketClient webSocketClient) {
//        this.webSocketClient = webSocketClient;
//    }
//
//    public Mono<Void> sendAndReceiveMessages(String uri, Flux<String> messages) {
//        return webSocketClient.execute(URI.create(uri),
//                session -> session
//                        .send(messages.map(session::textMessage))
//                        .subscribe() // Subscribe to the send process
//                        .then(session.receive()
//                                .map(WebSocketMessage::getPayloadAsText)
//                                .subscribe(System.out::println))); // Subscribe to the receive process
//    }
//
//    public static void main(String[] args) {
//        WebSocketClientExample example = new WebSocketClientExample(new WebSocketClient());
//        Flux<String> messages = Flux.fromIterable(
//                new HashMap<>() {{
//                    put("Hello, server!");
//                    put("Another message");
//                }}
//                        .keySet()
//        );
//
//        example.sendAndReceiveMessages("ws://localhost:8080/websocket-endpoint", messages)
//                .block();
//    }
//
//}
