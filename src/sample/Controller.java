package sample;

import impl.Logic;
import impl.Node;
import impl.Relation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller {

    int x = 50;
    int y = 300;
    int deltaX = 150;
    int radius = 8;
    public static int numbOfNodes;
    ArrayList<Double> positionsX;
    ArrayList<Double> positionsY;
    ArrayList<Node> nodes;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button constructButton;

    @FXML
    private TextField fromField;

    @FXML
    private Button plotButton;

    @FXML
    private ScrollPane pane;

    @FXML
    private Pane paneInPane;

    @FXML
    private TextField textPane;

    @FXML
    private TextField toField;

    @FXML
    private TextField weightField;


    @FXML
    private TextArea weightTextBox;

    @FXML
    void drawLineByDrag(MouseEvent event) {
        System.out.print(event.getX()+",");
        System.out.println(event.getY());
        pane.startFullDrag();

    }

    @FXML
    void drawFinalAfterDrag(MouseEvent event) {
        System.out.print(event.getX() + ",");
        System.out.println(event.getY());
    }

    @FXML
    void drawNodes(MouseEvent event) {
        nodes = new ArrayList<Node>();
        String numberOfNodes = textPane.getText();
        int numOfNodes = Integer.parseInt(numberOfNodes);
        numbOfNodes = numOfNodes;
        for(int i = 0; i < numOfNodes; i++) {
            Node node = new Node((char)(i + 97) + "");
            nodes.add(node);
        }
        paneInPane.getChildren().clear();
        x = 50;
        positionsX = new ArrayList();
        positionsY = new ArrayList();
        for(int i = 0; i < numOfNodes; i++) {
            Circle circle = new Circle();
            Circle circle2 = new Circle();
            circle2.setFill(Color.WHITE);
            circle.setFill(Color.BLACK);
            circle2.setLayoutX(x + deltaX);
            circle.setLayoutX(x + deltaX);
            positionsX.add(1.0 * x + deltaX);
            positionsY.add(1.0 * y);

            Label numLabel = new Label();
            numLabel.setText((char)(96 + i + 1) + "");
            numLabel.setLayoutX(x + deltaX - 5);
            numLabel.setLayoutY(y + 10);

            x += deltaX;
            circle.setLayoutY(y);
            circle.setRadius(radius);
            circle2.setLayoutY(y);
            circle2.setRadius(radius - 2);
            paneInPane.getChildren().add(circle);
            paneInPane.getChildren().add(circle2);
            paneInPane.getChildren().add(numLabel);

        }

    }


    @FXML
    void drawLines(MouseEvent event) {

        fromField.setText(String.valueOf((Integer.valueOf(fromField.getText().charAt(0)) - 96)));
        toField.setText(String.valueOf((Integer.valueOf(toField.getText().charAt(0)) - 96)));
        if(weightField.getText().equals("")) {
            weightField.setText("1");
        }

        int numOfInitNode = Integer.valueOf(fromField.getText());
        int numOfFinalNode = Integer.valueOf(toField.getText());
        int differenceBetweenNodes = numOfFinalNode - numOfInitNode;
        if(differenceBetweenNodes == 0) {
            Circle c = new Circle();
            c.setLayoutX(positionsX.get(numOfInitNode - 1));
            c.setLayoutY(positionsY.get(numOfInitNode - 1) - 30);
            c.setRadius(30);
            c.setFill(Color.TRANSPARENT);
            c.setStroke(Color.gray(0.1));
            c.setStrokeWidth(2);
            paneInPane.getChildren().add(c);

            String weight = weightField.getText();
            Label weightLabel = new Label();
            Font f;
            f = new Font(20);
            weightLabel.setLayoutY(positionsY.get(numOfInitNode - 1) - 90);
            weightLabel.setFont(f);
            weightLabel.setText(weight);
            weightLabel.setTextFill(Color.GREEN);
            weightLabel.setLayoutX(positionsX.get(numOfInitNode - 1));

            Line l1 = new Line();
            l1.setStartX(positionsX.get(numOfInitNode - 1));
            l1.setStartY(positionsY.get(numOfInitNode - 1) - 60);
            l1.setEndX(positionsX.get(numOfInitNode - 1) - 10);
            l1.setEndY(positionsY.get(numOfInitNode - 1) - 65);
            l1.setStrokeWidth(2);
            l1.setStroke(Color.gray(0.1,0.5));

            Line l2 = new Line();
            l2.setStartX(positionsX.get(numOfInitNode - 1));
            l2.setStartY(positionsY.get(numOfInitNode - 1) - 60);
            l2.setEndX(positionsX.get(numOfInitNode - 1) - 9);
            l2.setEndY(positionsY.get(numOfInitNode - 1) - 53);
            l2.setStrokeWidth(2);
            l2.setStroke(Color.gray(0.1,0.5));
            paneInPane.getChildren().add(l1);
            paneInPane.getChildren().add(l2);
            paneInPane.getChildren().add(weightLabel);
        } else {
            CubicCurve arc = new CubicCurve();
            //arc.setFill(Color.gray(0.96));
            arc.setFill(Color.TRANSPARENT);

            arc.setStrokeWidth(2);

            double startX = positionsX.get(numOfInitNode - 1);
            double endX = positionsX.get(numOfFinalNode - 1);

            double startY = positionsY.get(numOfInitNode - 1);
            double endY = positionsY.get(numOfFinalNode - 1);


            double Height = (endX - startX) / 4;
            if(differenceBetweenNodes == 1) {
                Line arcc = new Line();
                arcc.setFill(Color.TRANSPARENT);
                arcc.setStrokeWidth(2);
                arcc.setStartX(startX);
                arcc.setEndX(endX);
                arcc.setStartY(startY);
                arcc.setEndY(endY);
                paneInPane.getChildren().add(arcc);
                drawArrow((startX + endX) / 2 + 5, (startY + endY) / 2);

                Circle c = new Circle();
                c.setFill(Color.BLACK);
                c.setStroke(Color.BLACK);
                c.setRadius(3);
                c.setLayoutX(startX);
                c.setLayoutY(startY);
                paneInPane.getChildren().add(c);

            } else {
                if(differenceBetweenNodes < 0) {
                    endX += 7;
                    endY += 6;
                    arc.setStroke(Color.RED);
                    Circle c = new Circle();
                    c.setFill(Color.RED);
                    c.setStroke(Color.RED);
                    c.setRadius(3);
                    c.setLayoutX(startX);
                    c.setLayoutY(startY);
                    paneInPane.getChildren().add(c);
                }
                else if(differenceBetweenNodes > 0) {
                    endX -= 7;
                    endY -= 6;
                    arc.setStroke(Color.gray(0.1));
                    Circle c = new Circle();
                    c.setFill(Color.BLACK);
                    c.setStroke(Color.BLACK);
                    c.setRadius(3);
                    c.setLayoutX(startX);
                    c.setLayoutY(startY);
                    paneInPane.getChildren().add(c);
                }

                arc.setStartX(startX);
                arc.setEndX(endX);
                arc.setStartY(startY);
                arc.setEndY(endY);
                arc.setControlX1(startX + (endX - startX) / 3);
                arc.setControlY1(startY - Height);
                arc.setControlX2(endX - (endX - startX) / 3);
                arc.setControlY2(startY - Height);
                paneInPane.getChildren().add(arc);
                if(differenceBetweenNodes > 0) {
                    drawArrowRight(endX, endY);
                }
                else if (differenceBetweenNodes < 0 ){
                    drawArrowLeft(endX, endY);
                }
            }


            String weight = weightField.getText();
            Relation relation = new Relation(Integer.valueOf(weight), nodes.get(numOfFinalNode - 1));
            nodes.get(numOfInitNode - 1).relations.add(relation);
            Label weightLabel = new Label();
            Font f;
            if (differenceBetweenNodes == 1) {
                f = new Font(18);
                weightLabel.setLayoutY(startY - Height + 5);
            } else if (differenceBetweenNodes == 2) {
                f = new Font(20);
                weightLabel.setLayoutY(startY - Height - 11);
            } else {
                f = new Font(25);
                weightLabel.setLayoutY(startY - Height - 8);
            }
            weightLabel.setFont(f);
            weightLabel.setText(weight);
            weightLabel.setTextFill(Color.GREEN);
            weightLabel.setLayoutX(startX + (endX - startX) / 2);
            paneInPane.getChildren().add(weightLabel);

        }
        weightTextBox.setFont(new Font(18));
        weightTextBox.appendText((char)(numOfInitNode + 96) + "      ⟿");
        weightTextBox.setStyle("-fx-text-fill: blue;");
        weightTextBox.appendText(weightField.getText());
        weightTextBox.appendText("⟿      " + (char)(numOfFinalNode + 96) + "\n");
        fromField.clear();
        toField.clear();
        weightField.clear();
    }

    private void drawArrow(double v, double v1) {
        Line l1 = new Line();
        l1.setStartX(v);
        l1.setStartY(v1);
        l1.setEndX(v - 10);
        l1.setEndY(v1 - 5);
        paneInPane.getChildren().add(l1);

        Line l2 = new Line();
        l2.setStartX(v);
        l2.setStartY(v1);
        l2.setEndX(v - 10);
        l2.setEndY(v1 + 5);
        paneInPane.getChildren().add(l2);
    }


    private void drawArrowLeft(double endX, double endY) {
        Line l1 = new Line();
        l1.setStartX(endX);
        l1.setStartY(endY);
        l1.setEndX(endX + 10);
        l1.setEndY(endY - 1);
        l1.setStrokeWidth(2);
        l1.setStroke(Color.RED);

        Line l2 = new Line();
        l2.setStartX(endX);
        l2.setStartY(endY);
        l2.setEndX(endX + 8);
        l2.setEndY(endY + 13);
        l2.setStrokeWidth(2);
        l2.setStroke(Color.RED);

        paneInPane.getChildren().add(l1);
        paneInPane.getChildren().add(l2);
    }

    private void drawArrowRight(double endX, double endY) {
        Line l1 = new Line();
        l1.setStartX(endX);
        l1.setStartY(endY);
        l1.setEndX(endX - 10);
        l1.setEndY(endY - 15);
        l1.setStrokeWidth(2);
        l1.setStroke(Color.gray(0.1,0.5));

        Line l2 = new Line();
        l2.setStartX(endX);
        l2.setStartY(endY);
        l2.setEndX(endX - 15);
        l2.setEndY(endY);
        l2.setStrokeWidth(2);
        l2.setStroke(Color.gray(0.1,0.5));

        paneInPane.getChildren().add(l1);
        paneInPane.getChildren().add(l2);

    }
    @FXML
    private Button transferFunctionButton;

    @FXML
    void calculateTransferFunction(MouseEvent event) {
      Logic s = new Logic(nodes);
    }

}
