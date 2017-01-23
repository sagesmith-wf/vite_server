package com.vite.service;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;

public class ViteClient {
    private TTransport transport;
    private Service.Client client;
    private static Logger LOGGER = LoggerFactory.getLogger(ViteClient.class);

    public ViteClient() {
        try {
            LOGGER.debug("Creating Vite Client...");
            transport = new TSocket("localhost", 9090);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            client = new Service.Client(protocol);
            for (; ; ) {
                pingServer();
            }
        } catch (TException ex) {
            LOGGER.error("Could not open socket", ex);
        }
    }

    public void pingServer() {
        try {
            client.ping();
        } catch (TException ex) {
            LOGGER.error("Error Pinging the server", ex);
        }
    }

    @PreDestroy
    private void close() {
        if (transport.isOpen()) {
            close();
        }
    }
}