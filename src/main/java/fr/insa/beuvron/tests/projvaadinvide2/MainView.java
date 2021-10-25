package fr.insa.beuvron.tests.projvaadinvide2;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
 
@Route("")
@PageTitle("Bonjour")
public class MainView extends VerticalLayout {

    private Button vbBonjour;

    public MainView() {
        this.vbBonjour = new Button("Dis Bonjour");
        this.vbBonjour.addClickListener((event) -> {
            Notification.show("Bonjour");
        }
        );
        this.add(this.vbBonjour);

    }

}
