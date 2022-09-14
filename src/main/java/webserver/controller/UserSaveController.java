package webserver;

import db.Database;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ModelAndView;
import util.Request;
import util.Response;
import util.StatusCode;

import java.util.Map;

public class UserSaveController implements Controller{
    private static final Logger logger = LoggerFactory.getLogger(UserSaveController.class);
    @Override
    public ModelAndView process(Request request,Response response){
        Map<String,String> params = request.getParams();
        User user = new User(params.get("userId"),params.get("password"),params.get("name"),params.get("email"));
        ModelAndView modelAndView = new ModelAndView("/index.html");
        modelAndView.getParams().put("User",user);
        response.setStatusCode(StatusCode.FOUND);

        logger.info("------------");
        logger.info("userId: " + user.getUserId());

        return modelAndView;
    }
}
