package webserver.controller;

import webserver.http.Request;
import webserver.http.response.Response;

import java.io.IOException;

public interface Controller {
    Response process(Request request) throws IOException;
}
