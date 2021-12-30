package project;

import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

// =======================================================================================
// Permet la création d'un Layout Vertical
// =======================================================================================

public class MyVerticalLayout extends VerticalLayout {
    public MyVerticalLayout() {
        this.add(new Paragraph("VerticalLayout"));
    }
}