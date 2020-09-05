package com.skillone.designpattern.adapter.springmvc;

public interface Controller {
}

class HttpController implements Controller {
    public void doHttpHandler() {
        System.out.println("http...");
    }
}

class AnnotationController implements Controller {
    public void doAnnotationHandler() {
        System.out.println("annotation...");
    }
}
