package com.sos.graphviz;

import com.sos.graphviz.enums.RankType;

public class ClusterSubgraph extends Subgraph {

    private static final String CLUSTER_PREFIX = "cluster_";

    protected ClusterSubgraph(String subgraphId, Graph parent) {
        super(CLUSTER_PREFIX + subgraphId, RankType.min, parent);
    }

}
