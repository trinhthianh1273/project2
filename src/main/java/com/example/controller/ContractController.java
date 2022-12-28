package com.example.controller;

import com.example.entity.Apartment;
import com.example.entity.Contract;
import com.example.model.ApartmentModel;
import com.example.model.ContractModel;
import com.example.model.RoomModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ContractController implements Initializable {

    @FXML
    private TableColumn<?, ?> action;

    @FXML
    private TableColumn<?, ?> apartment_name;

    @FXML
    private TableColumn<?, ?> contract_id;

    @FXML
    private Label contract_label;

    @FXML
    private TableView<Contract> contract_table;

    @FXML
    private TableColumn<?, ?> endDate;

    @FXML
    private TableColumn<?, ?> owner;

    @FXML
    private TableColumn<?, ?> room_name;

    @FXML
    private TableColumn<?, ?> startDate;

    @FXML
    private TableColumn<?, ?> status;

    @FXML
    private ComboBox<String> roomBox;

    @FXML
    private ComboBox<String> apartmentBox;
    @FXML
    private Button search;

    private String nameApartmentSelected;
    private String nameRoomSelected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //contract_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        contract_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        apartment_name.setCellValueFactory(new PropertyValueFactory<>("apartment_name"));
        room_name.setCellValueFactory(new PropertyValueFactory<>("room_name"));
        owner.setCellValueFactory(new PropertyValueFactory<>("owner"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));


        ObservableList<Contract> list = new ContractModel().getAllBrief();
        System.out.println(list);
        contract_table.setItems(list);

        ObservableList<String> optionsName = new ApartmentModel().getApartmentName();
        apartmentBox.setPromptText("Search Apartment");
        apartmentBox.setItems(optionsName);
        apartmentBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                nameApartmentSelected = apartmentBox.getValue();
                ObservableList<String> roomName = new RoomModel().getRoomNameByApartment(nameApartmentSelected);

                roomBox.setItems(roomName);
                System.out.println(roomName);
            }
        });

        roomBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                nameRoomSelected = roomBox.getValue();
            }
        });

        roomBox.setPromptText("Search Room");
    }


    @FXML
    void add(MouseEvent event) {

    }

    @FXML
    void refesh(MouseEvent event) {
        ObservableList<Contract> list = new ContractModel().getAll();
        contract_table.getItems().setAll(list);
    }

    @FXML
    void search(MouseEvent event) {
        System.out.println(nameApartmentSelected);
        System.out.println(nameRoomSelected);
        if (nameApartmentSelected != null) {
            if(nameRoomSelected != null) {

            } else {
                ObservableList<Contract> list = new ContractModel().getAllBriefByApartment(nameApartmentSelected);
                contract_table.setItems(list);
            }
        }
    }
}
