package alt.portfolio.builder.services;

import alt.portfolio.builder.entities.Rubrique;
import alt.portfolio.builder.repositories.RubriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RubriqueService {

    @Autowired
    private RubriqueRepository rubriqueRepository;


    public Rubrique findByProfileId(UUID id){
        return rubriqueRepository.findRubriqueByProfileId(id);
    }

    public Rubrique createRubrique(Rubrique rubrique){
        return rubriqueRepository.save(rubrique);
    }

}
