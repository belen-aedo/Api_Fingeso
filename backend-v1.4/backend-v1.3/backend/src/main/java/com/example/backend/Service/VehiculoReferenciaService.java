package com.example.backend.Service;


import com.example.backend.Entity.VehiculoReferencia;
import com.example.backend.Repository.VehiculoReferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class VehiculoReferenciaService {

    @Autowired
    private VehiculoReferenciaRepository vehiculoReferenciaRepository;

    public VehiculoReferencia saveVehiculoReferencia(VehiculoReferencia vehiculoReferencia) {
        return vehiculoReferenciaRepository.save(vehiculoReferencia);
    }

    public VehiculoReferencia saveUrlForVehiculo(long idVehiculoReferencia, String url) {
        Optional<VehiculoReferencia> vehiculoReferenciaOpt = vehiculoReferenciaRepository.findById(idVehiculoReferencia);
        if (vehiculoReferenciaOpt.isPresent()) {
            VehiculoReferencia vehiculoReferencia = vehiculoReferenciaOpt.get();
            vehiculoReferencia.setUrl(url);
            return vehiculoReferenciaRepository.save(vehiculoReferencia);
        }
        throw new IllegalArgumentException("VehiculoReferencia no encontrada");
    }
}