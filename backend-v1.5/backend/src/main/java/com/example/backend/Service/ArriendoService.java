package com.example.backend.Service;


import com.example.backend.Repository.ArriendoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ArriendoService {

    @Autowired
    ArriendoRepository arriendoRepository;

    public void CambiarEstadoPendiente(Long idArriendo, Boolean estado) {
        try{
        arriendoRepository.ChangePendingStatusById(idArriendo, estado);
        }catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    public void CambiarEstado(Long idArriendo, Boolean estado) {
        try{
        arriendoRepository.ChangeState(idArriendo, estado);
        }catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
