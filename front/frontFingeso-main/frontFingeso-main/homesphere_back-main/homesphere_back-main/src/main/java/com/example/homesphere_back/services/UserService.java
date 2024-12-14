package com.example.homesphere_back.services;

import com.example.homesphere_back.models.Property;
import com.example.homesphere_back.models.Users;
import com.example.homesphere_back.repositories.PropertyRepository;
import com.example.homesphere_back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private PropertyRepository propertyRepository;

    public Users findByEmail(String email){return userRepository.findByEmail(email);}
    public Users findByRut(String rut){return userRepository.findByRut(rut);}
    public Users findByNumber(String number){return userRepository.findByNumber(number);}
    public List<Users> obtainAll() {return userRepository.obtainAll();}

    public Optional<Users> findById(Long id){return userRepository.findById(id);}

    public boolean saveUser(Users user){
            String userName = user.getFirstName();
            String subject = "Bienvenido/a :)" + userName;
            String body = "Es un gusto tenerte con nosotros, te ayudaremos a completar tu sueño de " +
                    "propiedad propia.";
            mailService.sendEmail(user.getEmail(), subject, body);
            userRepository.save(user);
            return true;
    }

    public boolean saveProperty(Property prop, Long id){
        Optional<Users> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()){
            Users user = optionalUser.get();

            prop.setLikes(0);

            List<Property> properties = user.getProperties();
            properties.add(prop);

            user.setProperties(properties);
            user.setLikedProperties("");
            userRepository.save(user);

            return true;
        }else {
            return false;
        }
    }

    public Boolean likeProperty(Long idProp, Long idUser){
        Users user = userRepository.findById(idUser).get();
        String likedProperties = user.getLikedProperties();
        String stringIdProp = Long.toString(idProp);

        Property prop = propertyRepository.findById(idProp).get();

        if (likedPropertyFlag(idProp, idUser)){
            return false;
        } else{
            if (likedProperties.isEmpty()){
                likedProperties = likedProperties + stringIdProp;
                user.setLikedProperties(likedProperties);

                saveUser(user);
                prop.setLikes(prop.getLikes() + 1);
                propertyRepository.save(prop);
            }
            else{
                likedProperties = likedProperties + "-" + stringIdProp;
                user.setLikedProperties(likedProperties);

                saveUser(user);
                prop.setLikes(prop.getLikes() + 1);
                propertyRepository.save(prop);
            }
        }
        return true;
    }

    public Boolean removeLike(Long idProp, Long idUser){
        Users user = userRepository.findById(idUser).get();
        String likedProperties = user.getLikedProperties();
        String stringIdProp = Long.toString(idProp);
        Property prop = propertyRepository.findById(idProp).get();

        prop.setLikes(prop.getLikes() - 1);
        propertyRepository.save(prop);

        List<String> propertiesList = new ArrayList<>(Arrays.asList(likedProperties.split("-")));
        propertiesList.remove(stringIdProp);
        String newLikedProperties = String.join("-", propertiesList);
        user.setLikedProperties(newLikedProperties);
        saveUser(user);

        return true;
    }

    public Boolean likedPropertyFlag(Long idProp, Long idUser){
        Users user = userRepository.findById(idUser).get();
        String likedProperties = user.getLikedProperties();
        String stringIdProp = Long.toString(idProp);

        if (likedProperties.isEmpty()) {
            return false;
        }else{
            likedProperties.split("-");

            if (likedProperties.contains(stringIdProp)) {
                return true;
            }else{
                return false;
            }
        }
    }

    public List<Property> getPropertiesByUser(Long id){
        Optional<Users> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()){
            Users user = optionalUser.get();
            return user.getProperties();
        }else {
            return null;
        }
    }

    public boolean login(String email, String password){
        return userRepository.findByEmailAndPassword(email, password) != null;
    }

    public boolean validateRut(String rut){
        // largo del rut
        int largo = rut.length();

        // recorre el string del rut buscando un punto, si encuentra uno retorna false
        for (int i = 0; i < largo; i++){
            if (rut.charAt(i) == '.'){
                return false;
            }
        }

        char dv;

        // se obtiene el digito verificador
        dv = rut.charAt(largo - 1);

        // se verifica que el digito verificador sea correcto con el rut respectivamente
        int suma = 0;
        int multiplicador = 2;
        for (int i = largo - 2; i >= 0; i--){

            if(rut.charAt(i) == '-'){

                // salta a la siguiente iteracion
                continue;
            }

            suma += multiplicador * Integer.parseInt(String.valueOf(rut.charAt(i)));
            multiplicador++;
            if (multiplicador == 8){
                multiplicador = 2;
            }
        }
        int resto = suma % 11;
        int resultado = 11 - resto;

        System.out.println(resultado);

        // se verifica que el digito verificador sea correcto
        if (resultado == 10){
            if (dv == 'k'){
                return true;
            }
        } else if (resultado == 11){
            if (dv == '0'){
                return true;
            }
        } else {
            // convierte dv al formato de numero
            int gd = Character.getNumericValue(dv);
            if (gd == resultado){

                return true;
            }
        }
        return false;
    }

    public boolean updateEmail(Long id, String email){
        Optional<Users> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()){
            Users user = optionalUser.get();
            user.setEmail(email);
            userRepository.save(user);
            return true;
        }else {
            return false;
        }
    }

    public boolean updateNumber(Long id, String number){
        Optional<Users> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()){
            Users user = optionalUser.get();
            user.setNumber(number);
            userRepository.save(user);
            return true;
        }else {
            return false;
        }
    }

    public boolean updatePassword(Long id, String password){
        Optional<Users> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()){
            Users user = optionalUser.get();
            user.setPassword(password);
            userRepository.save(user);
            return true;
        }else {
            return false;
        }
    }

    public List<Property> showLikedProperties(Long id){
        Users user = userRepository.findById(id).get();
        String likedProperties = user.getLikedProperties();
        List<Property> likedPropsList = new ArrayList<>();

        if (likedProperties.isEmpty()){
            return null;
        }else{
            List<String> propertiesList = new ArrayList<>(Arrays.asList(likedProperties.split("-")));

            for (int i = 0; i < propertiesList.size(); i++) {
                Property prop = propertyRepository.findById(Long.parseLong(propertiesList.get(i))).orElse(null);
                likedPropsList.add(prop);
            }
        }
        return likedPropsList;
    }

    public Boolean userOwnProperty(Long idUser, Long idProp){
        Users user = userRepository.findById(idUser).get();
        Optional<Property> optionalProp = propertyRepository.findById(idProp);

        if (optionalProp.isPresent()){
            Property prop = optionalProp.get();
            List<Property> userProperties = user.getProperties();
            return userProperties.contains(prop);
        }else{
            return false;
        }
    }

    public boolean addVisitUser(Long id_user, Long id_property, String date){
        Optional<Users> optionalUser = userRepository.findById(id_user);
        if(optionalUser.isPresent()){
            Users user = optionalUser.get();
            String visits = user.getVisits();
            visits += id_property + " - " + date + "; ";
            user.setVisits(visits);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    // muestra todas las visitas del usuario
    public List<Property> showAllVisitsUser(Long id){
        Users user = userRepository.findById(id).get();
        String visits = user.getVisits();
        List<Property> visitsList = new ArrayList<>();

        if (visits.isEmpty()){
            return null;
        }else{
            List<String> visitsListString = new ArrayList<>(Arrays.asList(visits.split(";")));

            for (int i = 0; i < visitsListString.size(); i++) {
                Property visit = propertyRepository.findById(Long.parseLong(visitsListString.get(i))).orElse(null);
                visitsList.add(visit);
            }
        }
        return visitsList;
    }
}
