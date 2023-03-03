package com.coherent.reservation.service;

import com.coherent.reservation.model.FileProperties;
import com.coherent.reservation.model.Reservation;
import com.coherent.reservation.repository.ReservationRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ReservationDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

@Service
public class FileServices {

    @Autowired
    SearchServices searchServices;
    @Autowired
    ReservationRepository rsvRepository;
    @Autowired
    private ResourceLoader resourceLoader;

    private final Gson gson = new GsonBuilder().create();

    public void writeOnRsvDB(ReservationDTO inRsv) {
        try {
            FileWriter fw = new FileWriter(FileProperties.ADD_TO_FILE_DB.getPathWithName(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(gson.toJson(inRsv));
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateRsvDB(String oldRsv, ReservationDTO newRsv) {
        File fileToBeModified = new File(FileProperties.ADD_TO_FILE_DB.getPathWithName());
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        ReservationDTO oldRsvDTO = gson.fromJson( oldRsv, ReservationDTO.class);
        try        {
            reader = new BufferedReader(new FileReader(fileToBeModified));

            String line = reader.readLine();
            while (line != null)            {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }

            //Replacing oldString with newString in the oldContent
            String newContent = oldContent.replace(gson.toJson(oldRsvDTO), gson.toJson(newRsv));

            //Rewriting the input text file with newContent
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);

            reader.close();

            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void loadFileReservationDB() {
        Set<Reservation> reservationsFromFile = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FileProperties.ADD_TO_FILE_DB.getPathWithName()));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                ReservationDTO currentRSv = gson.fromJson(currentLine, ReservationDTO.class );
                Reservation rsvFromFile = Reservation.builder()
                        .id(currentRSv.getId())
                        .clientFullName(currentRSv.getClientFullName())
                        .roomNumber(currentRSv.getRoomNumber())
                        .reservationDates(searchServices.stringToLocalDate(currentRSv.getReservationDates()))
                        .build();
                reservationsFromFile.add(rsvFromFile);
            }

            rsvRepository.setReservations(reservationsFromFile);

            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
