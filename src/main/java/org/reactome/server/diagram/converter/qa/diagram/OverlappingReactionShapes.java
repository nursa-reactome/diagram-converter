package org.reactome.server.diagram.converter.qa.diagram;

import org.reactome.server.diagram.converter.layout.output.Diagram;
import org.reactome.server.diagram.converter.layout.output.Edge;
import org.reactome.server.diagram.converter.qa.common.DiagramTest;
import org.reactome.server.diagram.converter.utils.TestReportsHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("unused")
@DiagramTest
public class OverlappingReactionShapes implements DiagramQA {

    private static final List<String> lines = new ArrayList<>();

    @Override
    public String getName() {
        return "OverlappingReactionShapesOnReactomeCurator";
    }

    @Override
    public String getDescription() {
        return "Detects cases where the reaction shapes (Circle for binding etc) overlap in the diagram layout.";
    }

    @Override
    public List<String> getReport() {
        if (!lines.isEmpty()) lines.add(0, "Diagram,DiagramName,Reaction,ReactionName,Created,Modified");
        return lines;
    }

    @Override
    public void run(Diagram diagram) {
        if(diagram.getEdges() == null) return;
        for (Edge edge : diagram.getEdges()) {
            //Check for overlapping reaction shapes and arrows
            if (edge.segments.size() == 1 && edge.position.equals(edge.segments.get(0).to)) {
                lines.add(String.format("%s,\"%s\",%s,\"%s\",%s",
                        diagram.getStableId(),
                        diagram.getDisplayName(),
                        edge.reactomeId,
                        edge.displayName,
                        TestReportsHelper.getCreatedModified(edge.reactomeId)
                ));
            }
        }
    }
}
