package com.example.StudentManagementSite.views;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Login")
@Route(value = LoginView.PATH)
@AnonymousAllowed
public class LoginView extends VerticalLayout {

    public static final String PATH = "/login";

    public LoginView(LoginViewFactory loginFactory) {
        add(loginFactory.createComponent());
    }
}
