package com.ivandelic.prototype.warp;

import java.io.IOException;
import java.util.logging.LogManager;

import io.helidon.microprofile.server.Server;

public final class Main {

    private Main() { }

    public static void main(final String[] args) throws IOException {
        setupLogging();
        Server server = startServer();
    }

    static Server startServer() {
        return Server.create().start();
    }

    private static void setupLogging() throws IOException {
        LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("/logging.properties"));
    }
}
