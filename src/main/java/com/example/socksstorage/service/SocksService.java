package com.example.socksstorage.service;

import com.example.socksstorage.model.Socks;
import com.example.socksstorage.repository.SocksRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class SocksService {

    private final SocksRepository socksRepository;

    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public Socks saveSocksIncome(Socks socks) {
        boolean areSameSocksExist = socksRepository.existsByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (areSameSocksExist){
            Socks foundSocks = socksRepository.getSocksByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
            foundSocks.setQuantity(socks.getQuantity());
            return socksRepository.save(foundSocks);
        }
        return socksRepository.save(socks);
    }
    public Collection<Socks> getSocks (String color, String operation, String cottonPart){
        if (operation.equals("moreThan")){
            return socksRepository.getSocksByColorAndCottonPartGreaterThan(color,cottonPart);
        }
        if (operation.equals("lessThan")){
            return socksRepository.getSocksByColorAndCottonPartLessThan(color, cottonPart);
        }
        if (operation.equals("equal")){
            return socksRepository.getSocksByColorAndCottonPartLike(color, cottonPart);
        }
        return null;
    }

    public Socks editSocks(Long id, Long outcome) {
        Socks socks;
        socks = socksRepository.findById(id).get();
        Long leftInStorage = socks.getQuantity();
        Long leftAfterSubtraction = leftInStorage - outcome;
        socks.setQuantity(leftAfterSubtraction);
        return socksRepository.save(socks);
    }

    public Collection<Socks> getAllSocks (){
       return socksRepository.findAll();
    }



}
