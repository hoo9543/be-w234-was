package webserver.controller;

import model.Board;
import model.LoginData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.BoardService;
import service.UserService;
import webserver.http.Constants;
import webserver.http.StatusCode;
import webserver.http.request.Request;
import webserver.http.response.Response;
import webserver.http.response.responseBody.DefaultResponseBody;
import webserver.http.util.UserParser;

import java.io.IOException;

import static webserver.http.util.UserParser.getBoardFromString;

public class BoardSaveController implements Controller{
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private BoardService boardService;

    public BoardSaveController(BoardService boardService){ this.boardService = boardService;}

    @Override
    public Response process(Request request) throws IOException {

        Response response = new Response(request.getHttpVersion(), StatusCode.FOUND,new DefaultResponseBody());

        logger.debug("request: " +request.getBody());

        Board board = getBoardFromString(request.getBody());
        boardService.save(board);
        response.setLocation(Constants.INDEX_PATH);
        response.setCookie(Constants.LOGIN_COOKIE);

        return response;
    }
}
