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
                new InetSocketAddress("0.0.0.0", port),
                0
        );

        server.createContext("/", App::handleHome);
        server.createContext("/health", App::handleHealth);

        server.setExecutor(null);
        server.start();

        System.out.println("Gaming App started on port " + port);
    }

    private static void handleHome(HttpExchange exchange) throws IOException {

        String html = """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>DevOps Gaming Platform</title>

                    <style>
                        * {
                            box-sizing: border-box;
                            margin: 0;
                            padding: 0;
                        }

                        body {
                            font-family: Arial, sans-serif;
                            background: #0f172a;
                            color: white;
                            min-height: 100vh;
                        }

                        nav {
                            display: flex;
                            justify-content: space-between;
                            align-items: center;
                            padding: 20px 8%;
                            background: #111827;
                            border-bottom: 1px solid #334155;
                        }

                        .logo {
                            font-size: 22px;
                            font-weight: bold;
                        }

                        .logo span {
                            color: #38bdf8;
                        }

                        nav a {
                            color: #cbd5e1;
                            text-decoration: none;
                            margin-left: 25px;
                        }

                        nav a:hover {
                            color: #38bdf8;
                        }

                        .hero {
                            text-align: center;
                            padding: 90px 20px 60px;
                        }

                        .badge {
                            display: inline-block;
                            padding: 8px 16px;
                            border-radius: 20px;
                            background: #0c4a6e;
                            color: #7dd3fc;
                            margin-bottom: 25px;
                        }

                        h1 {
                            font-size: 52px;
                            margin-bottom: 15px;
                        }

                        .highlight {
                            color: #38bdf8;
                        }

                        .subtitle {
                            color: #94a3b8;
                            font-size: 19px;
                            max-width: 700px;
                            margin: auto;
                            line-height: 1.6;
                        }

                        .buttons {
                            margin-top: 35px;
                        }

                        .button {
                            display: inline-block;
                            padding: 13px 25px;
                            margin: 8px;
                            border-radius: 8px;
                            text-decoration: none;
                            font-weight: bold;
                        }

                        .primary {
                            background: #38bdf8;
                            color: #082f49;
                        }

                        .secondary {
                            border: 1px solid #475569;
                            color: white;
                        }

                        .section {
                            max-width: 1100px;
                            margin: auto;
                            padding: 40px 20px 80px;
                        }

                        .section-title {
                            text-align: center;
                            margin-bottom: 35px;
                        }

                        .cards {
                            display: grid;
                            grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
                            gap: 20px;
                        }

                        .card {
                            background: #1e293b;
                            border: 1px solid #334155;
                            padding: 25px;
                            border-radius: 12px;
                            text-align: center;
                        }

                        .card h3 {
                            margin-bottom: 10px;
                        }

                        .card p {
                            color: #94a3b8;
                        }

                        .status {
                            margin-top: 40px;
                            background: #1e293b;
                            border: 1px solid #334155;
                            border-radius: 12px;
                            padding: 30px;
                            text-align: center;
                        }

                        .online {
                            color: #4ade80;
                            font-weight: bold;
                            font-size: 20px;
                            margin-bottom: 20px;
                        }

                        footer {
                            text-align: center;
                            padding: 30px;
                            color: #64748b;
                            border-top: 1px solid #334155;
                        }
                    </style>
                </head>

                <body>

                    <nav>
                        <div class="logo">⚡ <span>Gaming</span> DevOps</div>

                        <div>
                            <a href="/">Home</a>
                            <a href="/health">Health</a>
                        </div>
                    </nav>

                    <section class="hero">

                        <div class="badge">☁ Cloud & DevOps Project</div>

                        <h1>
                            Build. Deploy. <span class="highlight">Scale.</span>
                        </h1>

                        <p class="subtitle">
                            A containerized Java application deployed through
                            an automated CI/CD pipeline using GitHub, Jenkins,
                            Maven, Docker, Docker Hub and AWS.
                        </p>

                        <div class="buttons">
                            <a class="button primary" href="/health">
                                🚀 Check Application
                            </a>

                            <a class="button secondary" href="#stack">
                                ⚙ Technology Stack
                            </a>
                        </div>

                    </section>

                    <section class="section" id="stack">

                        <div class="section-title">
                            <h2>DevOps Technology Stack</h2>
                        </div>

                        <div class="cards">

                            <div class="card">
                                <h3>🔀 GitHub</h3>
                                <p>Source Control</p>
                            </div>

                            <div class="card">
                                <h3>🔧 Jenkins</h3>
                                <p>CI/CD Automation</p>
                            </div>

                            <div class="card">
                                <h3>☕ Maven</h3>
                                <p>Build & Testing</p>
                            </div>

                            <div class="card">
                                <h3>🐳 Docker</h3>
                                <p>Containerization</p>
                            </div>

                            <div class="card">
                                <h3>📦 Docker Hub</h3>
                                <p>Image Registry</p>
                            </div>

                            <div class="card">
                                <h3>☁ AWS</h3>
                                <p>Cloud Deployment</p>
                            </div>

                        </div>

                        <div class="status">

                            <div class="online">
                                ● APPLICATION ONLINE
                            </div>

                            <p>
                                Java 11 &nbsp; | &nbsp;
                                Docker &nbsp; | &nbsp;
                                AWS EC2
                            </p>

                        </div>

                    </section>

                    <footer>
                        DevOps Gaming Platform • Built by Fayaz Ahamed
                    </footer>

                </body>
                </html>
                """;

        sendResponse(exchange, html, "text/html");
    }

    private static void handleHealth(HttpExchange exchange) throws IOException {

        String response = """
                {
                    "application": "Gaming Application",
                    "status": "UP",
                    "version": "1.0",
                    "environment": "AWS",
                    "container": "Docker",
                    "deployment": "Jenkins CI/CD"
                }
                """;

        sendResponse(exchange, response, "application/json");
    }

    private static void sendResponse(
            HttpExchange exchange,
            String response,
            String contentType) throws IOException {

        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set(
                "Content-Type",
                contentType + "; charset=UTF-8"
        );

        exchange.sendResponseHeaders(200, bytes.length);

        try (OutputStream outputStream = exchange.getResponseBody()) {
            outputStream.write(bytes);
        }
    }
}