package milo.opcua.server;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.ManagedNamespace;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.FolderTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.*;
import org.eclipse.milo.opcua.sdk.server.util.SubscriptionModel;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class CustomNamespace extends ManagedNamespace {


    public static final String URI = "urn:my:custom:namespace";
    private static final Logger logger = LoggerFactory.getLogger(CustomNamespace.class);
    private static final int DELAY_MS = 2000;
    private static final int INTERVAL_MS = 500;
    private final SubscriptionModel subscriptionModel;
    int i = 0;

    public CustomNamespace(final OpcUaServer server, final String uri) throws InterruptedException {
        super(server, uri);
        this.subscriptionModel = new SubscriptionModel(server, this);


        registerItems(getNodeContext());


    }

    private void registerItems(final UaNodeContext context) throws InterruptedException {
        System.out.println("Registering items");

        // create a folder
        final UaFolderNode folder = new UaFolderNode(
                context,
                newNodeId(1),
                newQualifiedName("FirstFolder"),
                LocalizedText.english("MainFolder"));
        context.getNodeManager().addNode(folder);

        // add the folder to the objects folder
        final Optional<UaNode> objectsFolder = context.getServer()
                .getAddressSpaceManager()
                .getManagedNode(Identifiers.ObjectsFolder);
        objectsFolder.ifPresent(node -> {
            ((FolderTypeNode) node).addComponent(folder);
        });

        // add several variables
        {
            UaVariableNode destinationPoint = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId("0-unique-identifier"))
                    .setAccessLevel(AccessLevel.READ_WRITE)
                    .setUserAccessLevel(AccessLevel.READ_WRITE)
                    .setBrowseName(newQualifiedName("DestinationPoint"))
                    .setDisplayName(LocalizedText.english("Destination Point Variable"))
                    .setDataType(Identifiers.String)
                    .setDescription(LocalizedText.english("Target Destination Point"))
                    .build();

            UaVariableNode destinationPoint1 = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId("1-unique-identifier"))
                    .setAccessLevel(AccessLevel.READ_WRITE)
                    .setUserAccessLevel(AccessLevel.READ_WRITE)
                    .setBrowseName(newQualifiedName("DestinationPoint1"))
                    .setDisplayName(LocalizedText.english("Destination Point Variable1"))
                    .setDataType(Identifiers.String)
                    .setDescription(LocalizedText.english("Target Destination Point1"))
                    .build();

            UaVariableNode destinationPoint2 = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId("2-unique-identifier"))
                    .setAccessLevel(AccessLevel.READ_WRITE)
                    .setUserAccessLevel(AccessLevel.READ_WRITE)
                    .setBrowseName(newQualifiedName("DestinationPoint2"))
                    .setDisplayName(LocalizedText.english("Destination Point Variable2"))
                    .setDataType(Identifiers.String)
                    .setDescription(LocalizedText.english("Target Destination Point2"))
                    .build();

            Vector3D vector = new Vector3D(1000, 1000, 0);

            UaVariableNode positionX = createUaVariableNode(newNodeId("6-unique-identifier"), AccessLevel.READ_WRITE, AccessLevel.READ_WRITE, Identifiers.Integer, "X Position", "Updating the X Position", "Get the X Position");
            UaVariableNode positionY = createUaVariableNode(newNodeId("7-unique-identifier"), AccessLevel.READ_WRITE, AccessLevel.READ_WRITE, Identifiers.Integer, "Y Position", "Updating the Y Position", "Get the Y Position");

            UaVariableNode positionX1 = createUaVariableNode(newNodeId("8-unique-identifier"), AccessLevel.READ_WRITE, AccessLevel.READ_WRITE, Identifiers.Integer, "X Position1", "Updating the X Position1", "Get the X Position1");
            UaVariableNode positionY1 = createUaVariableNode(newNodeId("9-unique-identifier"), AccessLevel.READ_WRITE, AccessLevel.READ_WRITE, Identifiers.Integer, "Y Position1", "Updating the Y Position1", "Get the Y Position1");

            UaVariableNode positionX2 = createUaVariableNode(newNodeId("10-unique-identifier"), AccessLevel.READ_WRITE, AccessLevel.READ_WRITE, Identifiers.Integer, "X Position2", "Updating the X Position2", "Get the X Position2");
            UaVariableNode positionY2 = createUaVariableNode(newNodeId("11-unique-identifier"), AccessLevel.READ_WRITE, AccessLevel.READ_WRITE, Identifiers.Integer, "Y Position2", "Updating the Y Position2", "Get the Y Position2");

            // Set the variable node values
            destinationPoint.setValue(new DataValue(new Variant(vector.toString())));
            destinationPoint1.setValue(new DataValue(new Variant(vector.toString())));
            destinationPoint2.setValue(new DataValue(new Variant(vector.toString())));

            // add all the variables to the main folder
            folder.addOrganizes(destinationPoint);
            context.getNodeManager().addNode(destinationPoint);
            folder.addOrganizes(positionY);
            context.getNodeManager().addNode(positionY);
            folder.addOrganizes(positionX);
            context.getNodeManager().addNode(positionX);

            // add all the variables to the main folder
            folder.addOrganizes(destinationPoint1);
            context.getNodeManager().addNode(destinationPoint1);
            folder.addOrganizes(positionY1);
            context.getNodeManager().addNode(positionY1);
            folder.addOrganizes(positionX1);
            context.getNodeManager().addNode(positionX1);

            // add all the variables to the main folder
            folder.addOrganizes(destinationPoint2);
            context.getNodeManager().addNode(destinationPoint2);
            folder.addOrganizes(positionY2);
            context.getNodeManager().addNode(positionY2);
            folder.addOrganizes(positionX2);
            context.getNodeManager().addNode(positionX2);


            // Create a timer
            Timer timer = new Timer();
            // Create a TimerTask
            // TODO: class haline getir
            TimerTask task = new TimerTask() {
                Vector3D vectorx = vector;
                @Override
                // Code to be executed repeatedly at fixed intervals
                public void run() {
//                    positionX.setValue(new DataValue(new Variant(positionX.getValue().getValue().getValue())));
//                    positionY.setValue(new DataValue(new Variant(positionY.getValue().getValue().getValue())));
//                    folder.addOrganizes(positionX);
//                    context.getNodeManager().addNode(positionX);
//                    folder.addOrganizes(positionY);
//                    context.getNodeManager().addNode(positionY)
                    try {



                        int[] sectors = CoordinateMapper.mapCoordinatesToMatrix((int) positionX.getValue().getValue().getValue(), (int) positionY.getValue().getValue().getValue());


                        int[] nextSector = MazeSolver.getNextSector(sectors[0], sectors[1]);


                        int[] nextCord = CoordinateMapper.mapMatrixToCoordinates(nextSector[0], nextSector[1]);


                        vectorx = new Vector3D(nextCord[0], nextCord[1], 0);

                        destinationPoint.setValue(new DataValue(new Variant(vectorx.toString())));

                        System.out.printf("--%s--\n\n%s\n%s\n%s\n%s\n--------------------------------------------\n",
                                "Robot 1",
                                String.format("Position update: (%s, %s)",positionX.getValue().getValue().getValue(),positionY.getValue().getValue().getValue()),
                                String.format("Current Sector: (%s, %s)", sectors[0], sectors[1]),
                                String.format("Next Sector: (%s, %s)", nextSector[0], nextSector[1]),
                                String.format("Next Cord: (%s, %s)", nextCord[0], nextCord[1])
                        );

                        if(nextSector[2] == 2){
                            this.cancel();
                        }


                    } catch (Exception e) {

                    }
                }
            };

            TimerTask task1 = new TimerTask() {
                Vector3D vectorx = vector;
                @Override
                // Code to be executed repeatedly at fixed intervals
                public void run() {
//                    positionX.setValue(new DataValue(new Variant(positionX.getValue().getValue().getValue())));
//                    positionY.setValue(new DataValue(new Variant(positionY.getValue().getValue().getValue())));
//                    folder.addOrganizes(positionX);
//                    context.getNodeManager().addNode(positionX);
//                    folder.addOrganizes(positionY);
//                    context.getNodeManager().addNode(positionY)
                    try {

                        int[] sectors = CoordinateMapper.mapCoordinatesToMatrix((int) positionX1.getValue().getValue().getValue(), (int) positionY1.getValue().getValue().getValue());


                        int[] nextSector = MazeSolver.getNextSector(sectors[0], sectors[1]);


                        int[] nextCord = CoordinateMapper.mapMatrixToCoordinates(nextSector[0], nextSector[1]);


                        vectorx = new Vector3D(nextCord[0], nextCord[1], 0);

                        destinationPoint1.setValue(new DataValue(new Variant(vectorx.toString())));

                        System.out.printf("--%s--\n\n%s\n%s\n%s\n%s\n--------------------------------------------\n",
                                "Robot 2",
                                String.format("Position update: (%s, %s)",positionX1.getValue().getValue().getValue(),positionY1.getValue().getValue().getValue()),
                                String.format("Current Sector: (%s, %s)", sectors[0], sectors[1]),
                                String.format("Next Sector: (%s, %s)", nextSector[0], nextSector[1]),
                                String.format("Next Cord: (%s, %s)", nextCord[0], nextCord[1])
                        );

                        if(nextSector[2] == 2){
                            this.cancel();
                        }

                    } catch (Exception e) {
                    }
                }
            };

            TimerTask task2 = new TimerTask() {
                Vector3D vectorx = vector;
                @Override
                // Code to be executed repeatedly at fixed intervals
                public void run() {
//                    positionX.setValue(new DataValue(new Variant(positionX.getValue().getValue().getValue())));
//                    positionY.setValue(new DataValue(new Variant(positionY.getValue().getValue().getValue())));
//                    folder.addOrganizes(positionX);
//                    context.getNodeManager().addNode(positionX);
//                    folder.addOrganizes(positionY);
//                    context.getNodeManager().addNode(positionY)
                    try {

                        int[] sectors = CoordinateMapper.mapCoordinatesToMatrix((int) positionX2.getValue().getValue().getValue(), (int) positionY2.getValue().getValue().getValue());


                        int[] nextSector = MazeSolver.getNextSector(sectors[0], sectors[1]);


                        int[] nextCord = CoordinateMapper.mapMatrixToCoordinates(nextSector[0], nextSector[1]);


                        vectorx = new Vector3D(nextCord[0], nextCord[1], 0);

                        destinationPoint2.setValue(new DataValue(new Variant(vectorx.toString())));

                        System.out.printf("--%s--\n\n%s\n%s\n%s\n%s\n--------------------------------------------\n",
                                "Robot 3",
                                String.format("Position update: (%s, %s)",positionX2.getValue().getValue().getValue(),positionY2.getValue().getValue().getValue()),
                                String.format("Current Sector: (%s, %s)", sectors[0], sectors[1]),
                                String.format("Next Sector: (%s, %s)", nextSector[0], nextSector[1]),
                                String.format("Next Cord: (%s, %s)", nextCord[0], nextCord[1])
                        );
                        if(nextSector[2] == 2){
                            this.cancel();
                        }


                    } catch (Exception e) {
                    }
                }
            };

            new Timer().scheduleAtFixedRate(task, 0, 1000);
            new Timer().scheduleAtFixedRate(task1, 0, 1000);
            new Timer().scheduleAtFixedRate(task2, 0, 1000);


        }
    }

    protected void onStartup() throws InterruptedException {
        registerItems(getNodeContext());
        System.out.println("URI = " + URI);
    }

    @Override
    public void onDataItemsCreated(final List<DataItem> dataItems) {
        this.subscriptionModel.onDataItemsCreated(dataItems);
    }

    @Override
    public void onDataItemsModified(final List<DataItem> dataItems) {
        this.subscriptionModel.onDataItemsModified(dataItems);
    }

    @Override
    public void onDataItemsDeleted(final List<DataItem> dataItems) {
        this.subscriptionModel.onDataItemsDeleted(dataItems);
    }

    @Override
    public void onMonitoringModeChanged(final List<MonitoredItem> monitoredItems) {
        this.subscriptionModel.onMonitoringModeChanged(monitoredItems);

    }


    public UaVariableNode createUaVariableNode(NodeId nodeId, ImmutableSet<AccessLevel> accessLevel, ImmutableSet<AccessLevel> userAccessLevel, NodeId dataType, String qualifiedName, String displayName, String description) {
        UaVariableNode uaVariableNode = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                .setNodeId(nodeId)
                .setAccessLevel(accessLevel)
                .setUserAccessLevel(userAccessLevel)
                .setDataType(dataType)
                .setBrowseName(newQualifiedName(qualifiedName))
                .setDisplayName(LocalizedText.english(displayName))
                .setDescription(LocalizedText.english(description))
                .build();
        return uaVariableNode;
    }
}
