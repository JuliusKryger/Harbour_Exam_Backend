package dtos;

import entities.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnersDTO {

    List<OwnerDTO> owners = new ArrayList<>();

    public OwnersDTO(List<Owner> entity) {
        entity.forEach((owner -> {
            owners.add(new OwnerDTO(owner));
        }));
    }

    public List<OwnerDTO> getUsers() {
        return owners;
    }

    @Override
    public String toString() {
        return "OwnersDTO{" +
                "owners=" + owners +
                '}';
    }
}
