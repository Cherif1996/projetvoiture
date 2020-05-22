package com.jeeProjectGb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeeProjectGb.entities.Categorie;
import com.jeeProjectGb.entities.Voiture;

 
 @Repository
 public interface VoitureRepository extends JpaRepository<Voiture, Long> {
public Voiture findById(long id);
public List<Voiture> findByCategorie(Categorie category);
public List<Voiture> findByMarque(String marque);
}
