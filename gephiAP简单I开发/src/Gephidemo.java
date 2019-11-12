package gephitestdemo;
 
import java.io.File;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.EdgeMergeStrategy;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.AppendProcessor;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;
import org.openide.util.Utilities;

public class GephiDemo {
    
    GephiDemo(){
        
    }
    
    /**
     *‘À––≥Ã–Ú
     */
    public void Run(){
         //Init a project - and therefore a workspace
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        Workspace workspace = pc.getCurrentWorkspace();
        //Get controllers and models
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
        //Get models and controllers for this new workspace - will be useful later
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
        //Import file
        Container container,container2;
        try {
            File file_node = new File(getClass().getResource("./resources/Critical Power for Asymptotic Connectivity in Wireless Networks/Nodes.csv").toURI());
            container = importController.importFile(file_node);
            container.getLoader().setEdgeDefault(EdgeDirectionDefault.DIRECTED);   //Force DIRECTED
            container.getLoader().setAllowAutoNode(false);  //create missing nodes
            container.getLoader().setEdgesMergeStrategy(EdgeMergeStrategy.SUM);
            container.getLoader().setAutoScale(true);
 
            File file_edge = new File(getClass().getResource("./resources/Critical Power for Asymptotic Connectivity in Wireless Networks/Edges.csv").toURI());
            container2 = importController.importFile(file_edge);
            container2.getLoader().setEdgeDefault(EdgeDirectionDefault.DIRECTED);   //Force DIRECTED
            container2.getLoader().setAllowAutoNode(false);  //create missing nodes
            container2.getLoader().setEdgesMergeStrategy(EdgeMergeStrategy.SUM);
            container2.getLoader().setAutoScale(true);
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        //Append imported data to GraphAPI
        importController.process((org.gephi.io.importer.api.Container) container, new DefaultProcessor(), workspace);
        importController.process((org.gephi.io.importer.api.Container) container2, new AppendProcessor(), workspace); //Use AppendProcessor to append to current workspace
        //See if graph is well imported
        DirectedGraph graph = graphModel.getDirectedGraph();
        System.out.println("Nodes: " + graph.getNodeCount());
        System.out.println("Edges: " + graph.getEdgeCount());
    }
    
}