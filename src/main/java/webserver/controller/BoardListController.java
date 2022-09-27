package webserver.controller;

import exception.NoAuthorizationException;
import model.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.BoardService;
import webserver.http.StatusCode;
import webserver.http.request.Request;
import webserver.http.response.Response;
import webserver.http.response.responseBody.HtmlResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;

public class BoardListController implements Controller{
    private static final Logger logger = LoggerFactory.getLogger(UserListController.class);
    private BoardService boardService;

    public BoardListController(BoardService boardService){this.boardService = boardService;}

    @Override
    public Response process(Request request) throws IOException {

        if (!request.logined()){ throw new NoAuthorizationException("No Auth");}
        Collection<Board> boards = boardService.findAll();

        return new Response(request.getHttpVersion(), StatusCode.OK, new HashMap<>() ,new HtmlResponseBody(createHtmlBodyFrom(boards)));
    }

    private String createHtmlBodyFrom(Collection<Board> boards) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<tr>");
        boards.forEach(board -> {
            stringBuilder.append("<th scope=\"row\">"+board.getWriter()+"</th><td>"+board.getTitle()+"</td> <td>"+board.getContents()+"</td></tr>");
        });

        String fileData = new String(Files.readAllBytes(new File("./webapp/qna/showlist.html").toPath()));
        return fileData.replace("%board_list%", URLDecoder.decode(stringBuilder.toString(), "UTF-8"));
    }

}
