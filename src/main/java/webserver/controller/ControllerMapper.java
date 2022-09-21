package webserver.controller;

import webserver.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ControllerMapper {

    private Map<Key, Controller> controllerMap= new HashMap<>();

    public ControllerMapper(){
        controllerMap.put(new Key(HttpMethod.GET, "/user/create"),new UserSaveController());
        controllerMap.put(new Key(HttpMethod.POST, "/user/create"),new PostUserSaveController());
        controllerMap.put(new Key(HttpMethod.POST, "/user/login"),new LoginController());
    }

    public Controller getController(HttpMethod httpMethod, String path){
        Controller controller = controllerMap.get(new Key<>(httpMethod, path));
        return controller != null ? controller : new DefaultController();
    }

    private class Key<K1, K2>
    {
        public K1 key1;
        public K2 key2;

        public Key(K1 key1, K2 key2)
        {
            this.key1 = key1;
            this.key2 = key2;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Key key = (Key) o;
            if (!Objects.equals(key1, key.key1)) {
                return false;
            }

            if (!Objects.equals(key2, key.key2)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode()
        {
            int result = key1 != null ? key1.hashCode() : 0;
            result = 31 * result + (key2 != null ? key2.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "[" + key1 + ", " + key2 + "]";
        }
    }
}
