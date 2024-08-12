package com.example.HotelManagerment.HotelInformation.HotelConvenient;

import com.example.HotelManagerment.Exceptions.HotelConvenientNotFoundException;
import com.example.HotelManagerment.Exceptions.HotelNotFoundException;
import com.example.HotelManagerment.HotelInformation.HotelInformation;
import com.example.HotelManagerment.HotelInformation.HotelRepository;
import com.example.HotelManagerment.HotelInformation.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HotelConvenientService implements IHotelConvenientService {

    private final HotelConvenientRepository hotelConvenientRepository;

    private final HotelRepository hotelRepository;

    @Override
    public HotelConvenient addNewConvenient(Long id, List<String> convenient) {
        Optional<HotelInformation> hotelInformation = hotelRepository.findById(id);
        if (hotelInformation.isPresent()) {
            HotelConvenient hotelConvenient = hotelConvenientRepository.findById(id)
                    .orElse(new HotelConvenient(id));
            for (var con : convenient){
                hotelConvenient.addConvenient(con);
            }
            hotelConvenient = hotelConvenientRepository.save(hotelConvenient);
            return hotelConvenient;
        } else {
            throw new HotelNotFoundException("Hotel not found");
        }
    }

    @Override
    public HotelConvenientResponse convertToHotelConvenientResponse(HotelConvenient hotelConvenient) {
        HotelConvenientResponse response = new HotelConvenientResponse();
        response.setId(hotelConvenient.getId());
        response.setConvenients(hotelConvenient.getConvenients());
        response.setHotelID(hotelConvenient.getHotelID());
        response.setEating(hotelConvenient.getEating());
        response.setChildrenConvenient(hotelConvenient.getChildrenConvenient());
        response.setEntertainment(hotelConvenient.getEntertainment());
        response.setRemoteWorking(hotelConvenient.getRemoteWorking());
        response.setService(hotelConvenient.getService());
        response.setFacilities(hotelConvenient.getFacilities());
        response.setLanguage(hotelConvenient.getLanguage());
        response.setDisabilitiesPeople(hotelConvenient.getDisabilitiesPeople());
        return response;
    }

    @Override
    public HotelConvenient getAllConvenient(Long id) {
        return hotelConvenientRepository.findById(id).orElseThrow(() -> new HotelConvenientNotFoundException("Convenient not found on this hotel")) ;
    }

    @Override
    public HotelConvenient updateConvenient(Long id, String type, List<String> convenient) {
        Optional<HotelConvenient> hotelConvenientOptional = hotelConvenientRepository.findById(id);

        if (hotelConvenientOptional.isPresent()) {
            HotelConvenient hotelConvenient = hotelConvenientOptional.get();
            List<String> newConvenientList = new ArrayList<>(convenient);

            switch (type) {
                case "eating":
                    hotelConvenient.setEating(updateConvenientField(hotelConvenient.getEating(), newConvenientList));
                    break;
                case "childrenConvenient":
                    hotelConvenient.setChildrenConvenient(updateConvenientField(hotelConvenient.getChildrenConvenient(), newConvenientList));
                    break;
                case "entertainment":
                    hotelConvenient.setEntertainment(updateConvenientField(hotelConvenient.getEntertainment(), newConvenientList));
                    break;
                case "remoteWorking":
                    hotelConvenient.setRemoteWorking(updateConvenientField(hotelConvenient.getRemoteWorking(), newConvenientList));
                    break;
                case "service":
                    hotelConvenient.setService(updateConvenientField(hotelConvenient.getService(), newConvenientList));
                    break;
                case "facilities":
                    hotelConvenient.setFacilities(updateConvenientField(hotelConvenient.getFacilities(), newConvenientList));
                    break;
                case "language":
                    hotelConvenient.setLanguage(updateConvenientField(hotelConvenient.getLanguage(), newConvenientList));
                    break;
                case "disabilitiesPeople":
                    hotelConvenient.setDisabilitiesPeople(updateConvenientField(hotelConvenient.getDisabilitiesPeople(), newConvenientList));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type: " + type);
            }

            hotelConvenientRepository.save(hotelConvenient);
            return hotelConvenient;
        } else {
            throw new NoSuchElementException("HotelConvenient with id " + id + " not found");
        }
    }

    private String updateConvenientField(String existingConvenient, List<String> newConvenientList) {
        if (existingConvenient == null || existingConvenient.isEmpty()) {
            return String.join(", ", newConvenientList);
        } else {
            List<String> existingList = new ArrayList<>(Arrays.asList(existingConvenient.split(", ")));

            // Add new items
            for (String item : newConvenientList) {
                if (!existingList.contains(item)) {
                    existingList.add(item);
                }
            }

            // Remove items not in new list
            existingList.removeIf(item -> !newConvenientList.contains(item));

            return String.join(", ", existingList);
        }
    }


}
