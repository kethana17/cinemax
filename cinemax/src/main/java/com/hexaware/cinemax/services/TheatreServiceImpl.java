package com.hexaware.cinemax.services;

import com.hexaware.cinemax.dto.TheatreDTO;
import com.hexaware.cinemax.entities.Theatre;
import com.hexaware.cinemax.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TheatreServiceImpl implements ITheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Override
    public TheatreDTO addTheatre(TheatreDTO theatreDTO) {
        Theatre newTheatre = new Theatre();
        newTheatre.setName(theatreDTO.getName());
        newTheatre.setLocation(theatreDTO.getLocation());
        newTheatre.setNumberOfRows(theatreDTO.getNumberOfRows());
        newTheatre.setNumberOfColumns(theatreDTO.getNumberOfColumns());

        Theatre savedTheatre = theatreRepository.save(newTheatre);

        return new TheatreDTO(
                savedTheatre.getName(),
                savedTheatre.getLocation(),
                savedTheatre.getNumberOfRows(),
                savedTheatre.getNumberOfColumns()
        );
    }

    @Override
    public List<TheatreDTO> getAllTheatres() {
        return theatreRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void removeTheatreById(int id) {
        theatreRepository.deleteById(id);
    }

    @Override
    public void removeTheatreByName(String name) {
        Theatre theatresToRemove = theatreRepository.findByName(name);
        theatreRepository.delete(theatresToRemove);
    }
    
    @Override
    public TheatreDTO getTheatreById(int id) {
        Optional<Theatre> optionalTheatre = theatreRepository.findById(id);
        if (optionalTheatre.isPresent()) {
            return convertToDTO(optionalTheatre.get());
        } else {
            throw new IllegalArgumentException("Theatre not found with ID: " + id);
        }
    }

    @Override
    public TheatreDTO getTheatreByName(String name) {
        Theatre theatre = theatreRepository.findByName(name);
        if (theatre != null) {
            return convertToDTO(theatre);
        } else {
            throw new IllegalArgumentException("Theatre not found with name: " + name);
        }
    }

    private TheatreDTO convertToDTO(Theatre theatre) {
        return new TheatreDTO(
                theatre.getName(),
                theatre.getLocation(),
                theatre.getNumberOfRows(),
                theatre.getNumberOfColumns()
        );
    }
}
