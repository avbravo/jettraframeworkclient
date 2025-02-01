/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.jettraframework.controller;
import com.avbravo.jettraframework.model.User;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 *
 * @author avbravo
 */
public class UserHandler    implements HttpHandler {
    private static final List<User> users = new ArrayList<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();

            try {
                if (method.equalsIgnoreCase("GET") && path.equals("/users")) {
                    handleGetAllUsers(exchange);
                } else if (method.equalsIgnoreCase("GET") && path.startsWith("/users/")) {
                    handleGetUserById(exchange);
                } else if (method.equalsIgnoreCase("POST") && path.equals("/users")) {
                    handleCreateUser(exchange);
                } else if (method.equalsIgnoreCase("DELETE") && path.startsWith("/users/")) {
                    handleDeleteUser(exchange);
                } else {
                    sendResponse(exchange, 404, "Endpoint no encontrado");
                }
            } catch (Exception e) {
                sendResponse(exchange, 500, "Error interno del servidor: " + e.getMessage());
            }
        }

        private void handleGetAllUsers(HttpExchange exchange) throws IOException {
            
            users.add(new User("avbravo","aristides","avbravo@gmail.com"));
            String jsonResponse = objectMapper.writeValueAsString(users);
            sendResponse(exchange, 200, jsonResponse);
        }

        private void handleGetUserById(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            String id = path.substring(path.lastIndexOf("/") + 1);

            Optional<User> user = users.stream()
                    .filter(u -> u.getId().equals(id))
                    .findFirst();

            if (user.isPresent()) {
                String jsonResponse = objectMapper.writeValueAsString(user.get());
                sendResponse(exchange, 200, jsonResponse);
            } else {
                sendResponse(exchange, 404, "Usuario no encontrado");
            }
        }

        private void handleCreateUser(HttpExchange exchange) throws IOException {
            String body = new String(exchange.getRequestBody().readAllBytes());
            User user = objectMapper.readValue(body, User.class);
            users.add(user);

            sendResponse(exchange, 201, objectMapper.writeValueAsString(user));
        }

        private void handleDeleteUser(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            String id = path.substring(path.lastIndexOf("/") + 1);

            boolean removed = users.removeIf(user -> user.getId().equals(id));

            if (removed) {
                sendResponse(exchange, 204, "");
            } else {
                sendResponse(exchange, 404, "Usuario no encontrado");
            }
        }

        private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(statusCode, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

