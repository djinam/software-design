package raf.graffito.dsw.core.composite.abstraction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import raf.graffito.dsw.core.composite.concrete.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Workspace.class, name = "workspace"),
        @JsonSubTypes.Type(value = Project.class, name = "project"),
        @JsonSubTypes.Type(value = Presentation.class, name = "presentation"),
        @JsonSubTypes.Type(value = Slide.class, name = "slide"),
        @JsonSubTypes.Type(value = TextElement.class, name = "textElement"),
        @JsonSubTypes.Type(value = ImageElement.class, name = "imageElement"),
        @JsonSubTypes.Type(value = LogoElement.class, name = "imageElement")
})

@Getter
public abstract class GraffNode {
    private String name;
    @JsonIgnore
    private GraffNode parent;

    public GraffNode(){}

    public GraffNode(String name, GraffNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public abstract GraffNode findByName(String name);

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {
        return name;
    }

    public void setParent(GraffNode parent) {
        this.parent = parent;
    }
}
