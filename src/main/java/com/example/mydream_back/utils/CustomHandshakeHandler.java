package com.example.mydream_back.utils;

import com.example.mydream_back.model.UserPrincipal;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        String rawUserId = null;

        // 优先从 headers 获取
        rawUserId = request.getHeaders().getFirst("user_id");

        if (rawUserId == null || rawUserId.isEmpty()) {
            // 回退从 URL 参数获取
            var params = parseQueryParams(request.getURI());
            rawUserId = params.get("user_id");
        }


        String userId = rawUserId != null ? rawUserId : "anonymous";
        return new UserPrincipal(userId);
    }

    private Map<String, String> parseQueryParams(URI uri) {
        Map<String, String> queryParams = new LinkedHashMap<>();
        String query = uri.getQuery();

        if (query != null) {
            for (String pair : query.split("&")) {
                int idx = pair.indexOf("=");
                String key = idx > 0 ? pair.substring(0, idx) : pair;
                String value = idx > 0 && pair.length() > idx + 1 ? pair.substring(idx + 1) : null;

                key = java.net.URLDecoder.decode(key, StandardCharsets.UTF_8);
                if (value != null) {
                    value = java.net.URLDecoder.decode(value, StandardCharsets.UTF_8);
                }

                queryParams.put(key, value);
            }
        }

        return queryParams;
    }
}