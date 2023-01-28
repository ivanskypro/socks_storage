package com.example.socksstorage.repository;


import com.example.socksstorage.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {
   Collection<Socks> getSocksByColorAndCottonPartGreaterThan (String color, String cottonPart);
   Collection<Socks> getSocksByColorAndCottonPartLessThan (String color, String cottonPart);
   Collection <Socks> getSocksByColorAndCottonPartLike (String color, String cottonPart);
   Socks getSocksByColorAndCottonPart (String color, String cottonPart);
   boolean existsByColorAndCottonPart (String color, String cottonPart);





}
