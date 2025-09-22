package com.example.StudentManagementSite.views;


import com.example.StudentManagementSite.constants.Constants;
import com.example.StudentManagementSite.entity.Student;
import com.example.StudentManagementSite.services.SecurityService;
import com.example.StudentManagementSite.services.StudentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;
import jakarta.annotation.security.PermitAll;

//import javax.annotation.security.PermitAll;
import java.text.MessageFormat;

@PageTitle(value = "Home")
@Route(value = "")
@PermitAll
public class MainView extends VerticalLayout {

    // constructor injection !!!
    private final StudentService studentService;
    private final SecurityService securityService;

    private LogoLayout logoLayout;
    private Grid<Student> grid;
    private TextField filterField;
    private Checkbox themeToggle;
    private static boolean isChecked;

    public MainView(StudentService studentService,
                    SecurityService securityService) {
        this.studentService = studentService;
        this.securityService = securityService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        createFieldVariables();
        configureGrid();

        add(logoLayout, createToolbar(), grid);

        loadStudents();
    }

    private Checkbox createToggle() {
        themeToggle = new Checkbox("Dark Mode");
        themeToggle.setValue(isChecked);
        themeToggle.addValueChangeListener(e -> {
            MainView.isChecked = !isChecked;
            setTheme(isChecked);
        });

        return themeToggle;
    }

    private void setTheme(boolean dark) {
        var js = MessageFormat.format("""
			    document.documentElement.setAttribute("theme", "{0}")             
			""", dark ? Lumo.DARK : Lumo.LIGHT);

        // execute the JS
        getElement().executeJs(js);
    }

    private Component createToolbar() {
        filterField.setPlaceholder("Filter by name...");
        filterField.setClearButtonVisible(true);
        filterField.setValueChangeMode(ValueChangeMode.LAZY);
        filterField.addValueChangeListener(e -> updateStudents());

        Button addStudentButton = new Button(Constants.ADD_STUDENT);
        Button removeStudentButton = new Button(Constants.REMOVE_STUDENT);
        Button logout = new Button("Logout");

        addStudentButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("add-student")));
        removeStudentButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("remove-student")));
        logout.addClickListener(e -> securityService.logout());

        return new HorizontalLayout(filterField, addStudentButton, removeStudentButton, logout
                , createToggle());
    }

    private void updateStudents() {
        grid.setItems(studentService.find(filterField.getValue()));
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("country", "zipCode");
        grid.addColumn(s -> s.getName()).setHeader("Name");
        grid.addColumn(s -> s.getAge()).setHeader("Age");

        grid.addComponentColumn(s -> {
            Icon icon;

            if (s.getStatus().getName().equals("ACTIVE")) {
                icon = VaadinIcon.CIRCLE.create();
                icon.setColor("green");
            } else if (s.getStatus().getName().equals("PASSIVE")) {
                icon = VaadinIcon.CLOSE_CIRCLE.create();
                icon.setColor("red");
            } else {
                icon = VaadinIcon.CHECK_CIRCLE.create();
                icon.setColor("orange");
            }

            return icon;
        }).setHeader("Status");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void loadStudents() {
        grid.setItems(studentService.findAll());
    }

    private void createFieldVariables() {
        this.logoLayout = new LogoLayout();
        this.grid = new Grid<>(Student.class);
        this.filterField = new TextField();
    }
}
