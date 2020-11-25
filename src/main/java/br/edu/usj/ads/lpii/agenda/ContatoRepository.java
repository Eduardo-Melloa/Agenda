package br.edu.usj.ads.lpii.agenda;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ContatoRepository extends CrudRepository<Contato,Long>{
    List<Contato> findAll();
    List<Contato> findByNome(String nome);
    List<Contato> findByNomeContainingIgnoreCaseOrderByNome(String nome);

}

