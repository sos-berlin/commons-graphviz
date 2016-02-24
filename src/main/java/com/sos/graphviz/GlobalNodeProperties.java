package com.sos.graphviz;

import com.sos.graphviz.enums.Shape;

public class GlobalNodeProperties extends NodeProperties {

    private static final String CONST_PREFIX = "node ";

    public GlobalNodeProperties(Shape shape) {
        super(CONST_PREFIX, shape);
    }

    public GlobalNodeProperties() {
        super(CONST_PREFIX);
    }

}
