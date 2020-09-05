package com.skillone.designpatterns.adapter.springmvc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DispatchServlet {

    List<HandlerAdapter> adapterList = new ArrayList<>();

    public DispatchServlet() {
        adapterList.add(new HttpHandlerAdapter());
        adapterList.add(new AnnotationHandlerAdapter());
    }

    @Test
    public void doDispatch() {
        HttpController controller = new HttpController();

        HandlerAdapter adapter = getHandler(controller);

        adapter.handle(controller);
    }

    private HandlerAdapter getHandler(HttpController controller) {
        for (HandlerAdapter handlerAdapter : adapterList) {
            if(handlerAdapter.supports(controller)) {
                return handlerAdapter;
            }
        }
        return null;
    }

}
