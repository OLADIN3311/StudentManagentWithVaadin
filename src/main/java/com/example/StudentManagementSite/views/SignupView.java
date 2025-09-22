package com.example.StudentManagementSite.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Signup")
@Route(value = SignupView.PATH)
@AnonymousAllowed
public class SignupView extends VerticalLayout {

    public static final String PATH = "/signup";

    public SignupView(SignupViewFactory signupFactory) {
        add(signupFactory.create());
    }
}
