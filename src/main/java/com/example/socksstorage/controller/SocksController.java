package com.example.socksstorage.controller;

import com.example.socksstorage.model.Socks;
import com.example.socksstorage.repository.SocksRepository;
import com.example.socksstorage.service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/api/socks")
public class SocksController {
    private final SocksService socksService;
    private final SocksRepository socksRepository;

    public SocksController(SocksService socksService,
                           SocksRepository socksRepository) {
        this.socksService = socksService;
        this.socksRepository = socksRepository;
    }
    @PostMapping("/income")
    public ResponseEntity <Socks> saveSocks (@RequestBody Socks socks){
        if (socks.getQuantity()==0 || socks.getCottonPart().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(socksService.saveSocksIncome(socks));
    }

    @GetMapping("/getSocksByParameters")
    public ResponseEntity <Collection<Socks>> getSocks (String color, String operation, String cottonPart){
        if ((color.isBlank() || cottonPart.isBlank()) || !isOperationRequestOk(operation)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(socksService.getSocks(color, operation, cottonPart));
    }

    @PostMapping("/outcome/{id}")
    public ResponseEntity<Socks> editSocks (@RequestBody Long outcome, @PathVariable Long id) {
        return ResponseEntity.ok(socksService.editSocks (id, outcome));
    }

    @GetMapping("/getAllSocks")
    public Collection<Socks> getAllSocks (){
        return socksService.getAllSocks();
    }


    public boolean isOperationRequestOk (String operation){
        if (operation.equals("moreThan")){
            return true;
        }
        if (operation.equals("lessThan")){
            return true;
        }
        if (operation.equals("equal")){
            return true;
        } else
        return false;
    }
}
