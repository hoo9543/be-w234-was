package webserver.controller;

import exception.NoAuthorizationException;
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
import java.util.HashMap;
import java.util.Map;

import static webserver.http.util.UserParser.getBoardFromString;

public class BoardSaveController implements Controller{
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private BoardService boardService;

    public BoardSaveController(BoardService boardService){ this.boardService = boardService;}

    @Override
    public Response process(Request request) throws IOException {

        if (!request.logined()){ throw new NoAuthorizationException("No Auth");}
        Board board = getBoardFromString(request.getBody());
        boardService.save(board);

        Map<String,String> headers = new HashMap<>();
        headers.put(Constants.SET_LOCATION,Constants.INDEX_PATH);
        headers.put(Constants.SET_COOKIE,Constants.LOGIN_COOKIE);

        return new Response(request.getHttpVersion(), StatusCode.FOUND,headers,new DefaultResponseBody());
    }
}
