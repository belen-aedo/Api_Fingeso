package com.example.backend.Service;


import com.example.backend.Repository.ArriendoRepository;
import org.springframework.stereotype.Service;

@Service
public class ArriendoService {

    ArriendoRepository arriendoRepository;

    public void CambiarEstadoPendiente(Long idArriendo, Boolean estado) {
        arriendoRepository.CambiarEstadoPendiennteById(idArriendo, estado);
    }
}
