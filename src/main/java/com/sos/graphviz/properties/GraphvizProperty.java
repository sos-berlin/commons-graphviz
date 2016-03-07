package com.sos.graphviz.properties;

public class GraphvizProperty implements IGraphvizProperty {

    public static final String NL = "\n";
    private Object value;
    private boolean set = false;
    private final String propertyName;

    public GraphvizProperty(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public String getContent() {
        return (isSet()) ? this.propertyName + " = \"" + getValue() + "\"" + NL : "";
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.set = true;
        this.value = value;
    }

    public boolean isSet() {
        return this.set;
    }

    public String getPropertyName() {
        return propertyName;
    }

}
