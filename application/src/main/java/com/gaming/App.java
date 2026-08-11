package com.gaming;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class App {

    public static void main(String[] args) throws IOException {

        int port = 8080;

        HttpServer server = HttpServer.create(
                new InetSocketAddress("0.0.0.0", port), 0
        );

        server.createContext("/", App::handleRequest);

        server.setExecutor(null);
        server.start();

        System.out.println("Gaming Hub started on port " + port);
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {

        String html = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">

                    <title>Gaming DevOps Hub</title>

                    <style>

                        * {
                            box-sizing: border-box;
                            margin: 0;
                            padding: 0;
                        }

                        body {
                            font-family: Arial, sans-serif;
                            background: #0b1020;
                            color: white;
                            min-height: 100vh;
                        }

                        .hero {
                            min-height: 100vh;
                            display: flex;
                            align-items: center;
                            justify-content: center;
                            padding: 40px 20px;
                            background:
                                radial-gradient(circle at top right, #243b75, transparent 40%),
                                radial-gradient(circle at bottom left, #32145f, transparent 40%),
                                #0b1020;
                        }

                        .container {
                            width: 100%;
                            max-width: 1050px;
                        }

                        .badge {
                            display: inline-block;
                            padding: 8px 16px;
                            border: 1px solid #4f8cff;
                            border-radius: 30px;
                            color: #72a7ff;
                            margin-bottom: 20px;
                            font-size: 14px;
                        }

                        h1 {
                            font-size: 56px;
                            line-height: 1.1;
                            margin-bottom: 20px;
                        }

                        h1 span {
                            color: #4f8cff;
                        }

                        .subtitle {
                            color: #b8c2d9;
                            font-size: 19px;
                            max-width: 700px;
                            line-height: 1.7;
                            margin-bottom: 35px;
                        }

                        .buttons {
                            display: flex;
                            gap: 15px;
                            flex-wrap: wrap;
                            margin-bottom: 45px;
                        }

                        .button {
                            padding: 14px 24px;
                            border-radius: 10px;
                            text-decoration: none;
                            font-weight: bold;
                            display: inline-block;
                        }

                        .primary {
                            background: #4f8cff;
                            color: white;
                        }

                        .secondary {
                            border: 1px solid #46516d;
                            color: white;
                        }

                        .cards {
                            display: grid;
                            grid-template-columns: repeat(3, 1fr);
                            gap: 20px;
                        }

                        .card {
                            background: rgba(255,255,255,0.06);
                            border: 1px solid rgba(255,255,255,0.1);
                            border-radius: 16px;
                            padding: 25px;
                            backdrop-filter: blur(10px);
                        }

                        .icon {
                            font-size: 32px;
                            margin-bottom: 15px;
                        }

                        .card h3 {
                            margin-bottom: 10px;
                        }

                        .card p {
                            color: #9da9c2;
                            line-height: 1.6;
                        }

                        .status {
                            margin-top: 30px;
                            padding: 15px 20px;
                            border-radius: 12px;
                            background: rgba(34, 197, 94, 0.1);
                            border: 1px solid rgba(34, 197, 94, 0.3);
                            color: #72e6a0;
                        }

                        @media (max-width: 750px) {

                            h1 {
                                font-size: 40px;
                            }

                            .cards {
                                grid-template-columns: 1fr;
                            }

                        }

                    </style>
                </head>

                <body>

                    <section class="hero">

                        <div class="container">

                            <div class="badge">
                                🚀 DEVOPS PROJECT
                            </div>

                            <h1>
                                Gaming <span>DevOps Hub</span>
                            </h1>

                            <p class="subtitle">
                                A containerized Java application deployed through
                                an automated CI/CD pipeline using GitHub, Jenkins,
                                Maven, Docker, Docker Hub and AWS.
                            </p>

                            <div class="buttons">

                                <a class="button primary" href="#">
                                    🎮 Launch Platform
                                </a>

                                <a class="button secondary" href="#">
                                    ⚙️ CI/CD Pipeline
                                </a>

                            </div>

                            <div class="cards">

                                <div class="card">
                                    <div class="icon">☁️</div>
                                    <h3>AWS</h3>
                                    <p>
                                        Cloud infrastructure and application
                                        deployment running on AWS EC2.
                                    </p>
                                </div>

                                <div class="card">
                                    <div class="icon">🔧</div>
                                    <h3>Jenkins</h3>
                                    <p>
                                        Automated CI/CD pipeline for testing,
                                        building and deploying the application.
                                    </p>
                                </div>

                                <div class="card">
                                    <div class="icon">🐳</div>
                                    <h3>Docker</h3>
                                    <p>
                                        Application packaged into a portable
                                        container and published to Docker Hub.
                                    </p>
                                </div>

                            </div>

                            <div class="status">
                                ● Application Online &nbsp; | &nbsp;
                                Java 21 &nbsp; | &nbsp;
                                Dockerized &nbsp; | &nbsp;
                                CI/CD Enabled
                            </div>

                        </div>

                    </section>

                </body>
                </html>
                """;

        byte[] response = html.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set(
                "Content-Type",
                "text/html; charset=UTF-8"
        );

        exchange.sendResponseHeaders(200, response.length);

        try (OutputStream output = exchange.getResponseBody()) {
            output.write(response);
        }
    }
}