package com.example.StudentManagementSite.views;

import com.example.StudentManagementSite.constants.Constants;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

@org.springframework.stereotype.Component
public class LoginViewFactory {


    @Autowired
    private DaoAuthenticationProvider authenticationProvider;

    private class LoginForm {

        private VerticalLayout root;
        private TextField username;
        private PasswordField password;
        private Button login;
        private Button signup;

        public LoginForm init() {
            username = new TextField("Username");
            password = new PasswordField("Password");
            login = new Button("Login");
            signup = new Button("Signup");

            login.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            signup.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

            root = new VerticalLayout();
            root.setAlignItems(FlexComponent.Alignment.CENTER);
            root.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
            root.setMargin(true);

            return this;
        }

        public Component layout() {
            root.add(new Image(Constants.LOGIN_URL, "Login Image"));
            root.add(username);
            root.add(password);
            root.add(new HorizontalLayout(login, signup));

            login.addClickListener(e -> login());
            signup.addClickListener(e -> signup());

            return root;
        }

        private void signup() {
            login.getUI().ifPresent(ui -> ui.navigate("signup"));
        }

        private void login() {
            try {
                Authentication auth = new UsernamePasswordAuthenticationToken(username.getValue(), password.getValue());
                Authentication authenticated = authenticationProvider.authenticate(auth);
                SecurityContextHolder.getContext().setAuthentication(authenticated);
                login.getUI().ifPresent(ui -> ui.navigate(""));
            }catch (AuthenticationException e) {
                Notification notification = Notification.
                        show("Incorrect username or password...");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.TOP_CENTER);
                e.printStackTrace();
            }
        }
    }

    public Component createComponent() {
        return new LoginForm().init().layout();
    }
}
