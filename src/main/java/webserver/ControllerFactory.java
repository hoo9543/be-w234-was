package webserver;

import webserver.controller.Controller;

public interface ControllerFactory {
    public Controller create();
}
