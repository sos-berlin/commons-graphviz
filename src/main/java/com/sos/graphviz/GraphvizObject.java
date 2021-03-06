package com.sos.graphviz;

/** A general implementation of an Graphviz object. */
abstract class GraphvizObject implements IGraphvizObject {

    public static final String NL = "\n";

    private final String prolog;
    private final String epilog;

    protected GraphvizObject(String prolog, String epilog) {
        this.prolog = prolog;
        this.epilog = epilog;
    }

    public String getSource() {
        StringBuilder sb = new StringBuilder();
        String content = getContent();
        if (!content.isEmpty()) {
            if (!prolog.isEmpty()) {
                sb.append(prolog + NL);
            }
            sb.append(content);
            if (!epilog.isEmpty()) {
                sb.append(epilog + NL);
            }
        }
        return sb.toString();
    }

    public String getContent() {
        return "";
    }

}
