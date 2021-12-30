package project;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.html.Paragraph;

// =======================================================================================
// Permet la création d'un Layout Horizontal
// =======================================================================================

public class MyHorizontalLayout extends HorizontalLayout {
    public MyHorizontalLayout() {
        this.add(new Paragraph("HorizontalLayout"));
    }
}
